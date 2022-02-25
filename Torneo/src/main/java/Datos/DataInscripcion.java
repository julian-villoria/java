package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import Entidades.Dificultad;
import Entidades.Inscripcion;
import Entidades.Juego;
import Entidades.Jugador;
import Entidades.TipoTorneo;
import Entidades.Torneo;

public class DataInscripcion {
	
	public LinkedList<Inscripcion> list(){

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Inscripcion> inscripciones = new LinkedList<Inscripcion>();
		
		try {

			conn = DbConnector.getInstancia().getConn();
			
			// Ejecutar querys
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT tt.denominacion, t.fecha_inicio, t.fecha_fin, jug.usuario, j.denominacion, d.nombre, i.fecha FROM inscripciones i "
					+ "INNER JOIN torneos t ON t.id_juego = i.id_juego AND t.id_tipo = i.id_tipo AND t.fecha_inicio = i.fecha_inicio_torneo "
					+ "INNER JOIN juegos j ON j.id = t.id_juego "
					+ "INNER JOIN tipo_torneo tt ON tt.id = t.id_tipo "
					+ "INNER JOIN jugadores jug ON jug.id = i.id_jugador "
					+ "INNER JOIN dificultad d ON d.id_juego = j.id");

			while(rs.next()) /*Empieza apuntando en -1*/ {
				Inscripcion i = new Inscripcion();
				Torneo t = new Torneo();
				Juego j = new Juego();
				TipoTorneo tt = new TipoTorneo();
				Jugador jug = new Jugador();
				Dificultad d = new Dificultad();
				t.setFechaInicio(rs.getObject("t.fecha_inicio", LocalDate.class));
				t.setFechaFin(rs.getObject("t.fecha_fin", LocalDate.class));
				tt.setDenominacion(rs.getString("tt.denominacion"));
				j.setDenominacion(rs.getString("j.denominacion"));
				i.setFechaInscripcion(rs.getObject("i.fecha", LocalDate.class));
				jug.setUsuario(rs.getString("jug.usuario"));
				d.setNombre(rs.getString("d.nombre"));
				i.setTorneo(t);
				i.getTorneo().setJuego(j);
				i.getTorneo().setTipoTorneo(tt);
				i.getTorneo().getJuego().setDificultad(d);
				i.setJ(jug);
				inscripciones.add(i);
			}

			//cerrar conexion
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			conn.close();

		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return inscripciones;
	}
	
	public int contador(Jugador j, Torneo t){
		int cont = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			conn = DbConnector.getInstancia().getConn();

			// Ejecutar querys
			pstmt = conn.prepareStatement("SELECT count(*) FROM inscripciones"
					+ " WHERE id_jugador = ? AND id_tipo = ? AND id_juego = ? AND fecha_inicio_torneo = ?");
			pstmt.setInt(1, j.getId());
			pstmt.setInt(2, t.getTipoTorneo().getId());
			pstmt.setInt(3, t.getJuego().getId());
			pstmt.setObject(4, t.getFechaInicio());
			rs = pstmt.executeQuery();

			while(rs.next()) /*Empieza apuntando en -1*/ {
				cont = rs.getInt("count(*)");
			}

			//cerrar conexion
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			conn.close();

		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return cont;
	}

	public int create(Inscripcion iNueva) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int success = 0;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO inscripciones (id_jugador, id_juego, id_tipo, fecha_inicio_torneo, fecha) "
			+ "VALUES(?,?,?,?,current_date())");
			
			pstmt.setInt(1, iNueva.getJugador().getId());
			pstmt.setInt(2, iNueva.getTorneo().getJuego().getId());
			pstmt.setInt(3, iNueva.getTorneo().getTipoTorneo().getId());
			pstmt.setObject(4, iNueva.getTorneo().getFechaInicio());
			success = pstmt.executeUpdate(); 
			
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return success;
	}
	
	public void delete(Torneo t, Jugador j) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"DELETE FROM inscripciones WHERE id_juego=? AND id_tipo=? AND fecha_inicio_torneo=? AND id_jugador=?;" 
					);
			
			pstmt.setInt(1,t.getJuego().getId());
			pstmt.setInt(2,t.getTipoTorneo().getId());
			pstmt.setObject(3,t.getFechaInicio());
			pstmt.setInt(4, j.getId());
			pstmt.executeUpdate();
			
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}finally {
			try {
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void update(Inscripcion iNuevo) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"Update inscripciones "
			+ "SET fecha_inicio_torneo= ?, id_jugador=? , id_juego = ?, fecha=?, id_tipo=?"
			+ "WHERE id_juego=? AND id_tipo=? AND fecha_inicio_torneo=? AND id_jugador=?" 
					);

			pstmt.setObject(1, iNuevo.getTorneo().getFechaInicio());
			pstmt.setInt(2, iNuevo.getJugador().getId());
			pstmt.setInt(3, iNuevo.getTorneo().getJuego().getId());
			pstmt.setObject(4, iNuevo.getFechaInscripcion());
			pstmt.setDouble(5, iNuevo.getTorneo().getTipoTorneo().getId());
			
			pstmt.setInt(6, iNuevo.getTorneo().getJuego().getId());
			pstmt.setInt(7, iNuevo.getTorneo().getTipoTorneo().getId());
			pstmt.setObject(8, iNuevo.getTorneo().getFechaInicio());
			pstmt.setObject(9, iNuevo.getJugador().getId());
			pstmt.executeUpdate();
			
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
