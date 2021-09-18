package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import Entidades.Torneo;
import Entidades.TipoTorneo;
import Entidades.Juego;

public class DataTorneo {

	// Listar 
	public LinkedList<Torneo> list(){
		
		LinkedList<Torneo> torneos= new LinkedList<Torneo>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			
			conn = ConectionFactory.getConnection();
			
			// Ejecutar querys
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT tt.id, tt.denominacion, j.id, j.denominacion, fecha_inicio, fecha_fin, intentos, cupo, ganador "
					+ "FROM torneos t INNER JOIN tipo_torneo tt ON t.id_tipo = tt.id INNER JOIN juegos j ON t.id_juego = j.id");
			
			while(rs.next()) /*Empieza apuntando en -1*/ {
				
				Torneo t = new Torneo();
				Juego j = new Juego();
				TipoTorneo tt = new TipoTorneo();
				
				j.setId(rs.getInt("j.id"));
				j.setDenominacion(rs.getString("j.denominacion"));
				tt.setId(rs.getInt("tt.id"));
				tt.setDenominacion(rs.getString("tt.denominacion"));
				t.setFechaInicio(rs.getObject("fecha_inicio", LocalDate.class));
				t.setFechaFin(rs.getObject("fecha_fin", LocalDate.class));
				t.setIntentos(rs.getInt("intentos"));
				t.setCupo(rs.getInt("cupo"));
				t.setGanador(rs.getString("ganador"));
				t.setJuego(j);
				t.setTipoTorneo(tt);
				torneos.add(t);
			}
			
		//cerrar conexion
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		conn.close();
		
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
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
		
		return torneos;
	}
	
	//Búsqueda
	public Torneo search(int idJuego, int idTipo, LocalDate fechaInicio) {
		
		Torneo t = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// conexion
			conn = ConectionFactory.getConnection();
			
			stmt = conn.prepareStatement("SELECT tt.id, tt.denominacion, j.id, j.denominacion, fecha_inicio, fecha_fin, intentos, cupo, ganador "
					+ "FROM torneos INNER JOIN tipo_torneo tt ON t.id_tipo = tt.id INNER JOIN juegos j ON t.id_juego = j.id"
					+ "WHERE id_juego=? AND id_tipo=? AND fecha_inicio=?");
			//setear parametros
			stmt.setInt(1,idJuego);
			stmt.setInt(2,idTipo);
			stmt.setObject(3,fechaInicio);
			
			t = new Torneo();
			Juego j = new Juego();
			TipoTorneo tt = new TipoTorneo();
			
			//resultados
			rs = stmt.executeQuery();
			
			//mapear
			if(rs.next()) {
				
				j.setId(rs.getInt("j.id"));
				j.setDenominacion(rs.getString("j.denominacion"));
				tt.setId(rs.getInt("id_tipo"));
				tt.setDenominacion(rs.getString("tt.denominacion"));
				t.setFechaInicio(rs.getObject("fecha_inicio", LocalDate.class));
				t.setFechaFin(rs.getObject("fecha_fin", LocalDate.class));
				t.setIntentos(rs.getInt("intentos"));
				t.setCupo(rs.getInt("cupo"));
				t.setGanador(rs.getString("ganador"));
				t.setJuego(j);
				t.setTipoTorneo(tt);
			}
			
			//cerrar conexion
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			
			conn.close();
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
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
		return t;
	}
	
	//cargar
	public void create(Juego j, TipoTorneo tt, LocalDate fechaInicio, LocalDate fechaFin, int intentos, int cupo, String ganador) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Torneo tNuevo = new Torneo();
		tNuevo.setJuego(j);
		tNuevo.setTipoTorneo(tt);
		tNuevo.setFechaInicio(fechaInicio);
		tNuevo.setFechaFin(fechaFin);
		tNuevo.setIntentos(intentos);
		tNuevo.setCupo(cupo);
		tNuevo.setGanador(ganador);
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO torneos(id_juego, id_tipo, fecha_inicio, fecha_fin, intentos, cupo, ganador) VALUES(?,?,?,?,?,?,?)");
			
			pstmt.setInt(1, tNuevo.getJuego().getId());
			pstmt.setInt(2, tNuevo.getTipoTorneo().getId());
			pstmt.setObject(3, tNuevo.getFechaInicio());
			pstmt.setObject(4, tNuevo.getFechaFin());
			pstmt.setInt(5, tNuevo.getIntentos());
			pstmt.setInt(6, tNuevo.getCupo());
			pstmt.setString(7, tNuevo.getGanador());
			pstmt.executeUpdate();
			
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
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
	
	//borrar
	public void delete(int idJuego, int idTipo, LocalDate fechaInicio) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"DELETE FROM torneos WHERE id_juego=? AND id_tipo=? AND fecha_inicio=?;" 
					);
			
			pstmt.setInt(1,idJuego);
			pstmt.setInt(2,idTipo);
			pstmt.setObject(3,fechaInicio);
			pstmt.executeUpdate();
			
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
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
	
	//actualizar
	public void update(Juego j, TipoTorneo tt, LocalDate fechaInicio, LocalDate fechaFin, int intentos, int cupo, String ganador) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		Torneo tNuevo = new Torneo();
		tNuevo.setJuego(j);
		tNuevo.setTipoTorneo(tt);
		tNuevo.setFechaInicio(fechaInicio);
		tNuevo.setFechaFin(fechaFin);
		tNuevo.setIntentos(intentos);
		tNuevo.setCupo(cupo);
		tNuevo.setGanador(ganador);
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"Update torneos "
			+ "SET fecha_fin=?, intentos=? , cupo=?, ganador=?"
			+ "WHERE id_juego=? AND id_tipo=? AND fecha_inicio=?" 
					);
			
			pstmt.setInt(5, tNuevo.getJuego().getId());
			pstmt.setInt(6, tNuevo.getTipoTorneo().getId());
			pstmt.setObject(7, tNuevo.getFechaInicio());
			pstmt.setObject(1, tNuevo.getFechaFin());
			pstmt.setInt(2, tNuevo.getIntentos());
			pstmt.setInt(3, tNuevo.getCupo());
			pstmt.setString(4, tNuevo.getGanador());
			pstmt.executeUpdate();
			
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
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
