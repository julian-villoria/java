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
import Entidades.Jugador;

public class DataTorneo {

	// Listar 
	public static LinkedList<Torneo> list(){
		
		LinkedList<Torneo> torneos= new LinkedList<Torneo>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			
			conn = DbConnector.getInstancia().getConn();
			
			// Ejecutar querys
			stmt = conn.createStatement();

			rs = stmt.executeQuery("SELECT t.id_tipo, tt.id, tt.denominacion, j.id, j.denominacion, fecha_inicio, fecha_fin, intentos, cupo, ganador, monto_insc "
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
				t.setMontoInsc(rs.getFloat("monto_insc"));
				t.setJuego(j);
				t.setTipoTorneo(tt);
				torneos.add(t);
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
		
		return torneos;
	}
	
public static LinkedList<Torneo> proximos(){
		
		LinkedList<Torneo> torneos= new LinkedList<Torneo>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			
			conn = DbConnector.getInstancia().getConn();
			
			// Ejecutar querys
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT tt.id, tt.denominacion, j.id, j.denominacion, fecha_inicio, fecha_fin, intentos, cupo, ganador, monto_insc "
					+ "FROM torneos t "
					+ "INNER JOIN tipo_torneo tt ON t.id_tipo = tt.id INNER JOIN juegos j ON t.id_juego = j.id "
					+ "WHERE (t.fecha_inicio >= curdate() and ganador = 'Vacante') ");
			
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
				t.setMontoInsc(rs.getFloat("monto_insc"));
				t.setJuego(j);
				t.setTipoTorneo(tt);
				torneos.add(t);
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
		
		return torneos;
	}
	
	//Búsqueda
	public static Torneo search(int idJuego, int idTipo, LocalDate fechaInicio) {
		
		Torneo t = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// conexion
			conn = DbConnector.getInstancia().getConn();
			
			stmt = conn.prepareStatement("SELECT tt.id, tt.denominacion, j.id, j.denominacion, fecha_inicio, fecha_fin, intentos, cupo, ganador "
					+ "FROM torneos t INNER JOIN tipo_torneo tt ON t.id_tipo = tt.id INNER JOIN juegos j ON t.id_juego = j.id "
					+ "WHERE (j.id=? AND tt.id=? AND t.fecha_inicio=?)");
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
				tt.setId(rs.getInt("tt.id"));
				tt.setDenominacion(rs.getString("tt.denominacion"));
				t.setJuego(j);
				t.setTipoTorneo(tt);
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
			
		}catch(SQLException ex){
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
	public static void create(Juego j, TipoTorneo tt, LocalDate fechaInicio, LocalDate fechaFin, int intentos, int cupo, String ganador, float montoInsc) {
		
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
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO torneos(id_juego, id_tipo, fecha_inicio, fecha_fin, intentos, cupo, ganador, monto_insc) VALUES(?,?,?,?,?,?,?,?)");
			
			pstmt.setInt(1, tNuevo.getJuego().getId());
			pstmt.setInt(2, tNuevo.getTipoTorneo().getId());
			pstmt.setObject(3, tNuevo.getFechaInicio());
			pstmt.setObject(4, tNuevo.getFechaFin());
			pstmt.setInt(5, tNuevo.getIntentos());
			pstmt.setInt(6, tNuevo.getCupo());
			pstmt.setString(7, tNuevo.getGanador());
			pstmt.setDouble(8, montoInsc);
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
	
	//borrar
	public static void delete(int idJuego, int idTipo, LocalDate fechaInicio) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
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
	
	//actualizar
	public static void update(Juego j, TipoTorneo tt, LocalDate fechaInicio, LocalDate fechaFin, int intentos, int cupo, String ganador, float montoInsc) {
		
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
		tNuevo.setMontoInsc(montoInsc);
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"Update torneos "
			+ "SET fecha_fin=?, intentos=? , cupo=?, ganador=?, monto_insc = ?"
			+ "WHERE id_juego=? AND id_tipo=? AND fecha_inicio=?" 
					);
			
			pstmt.setInt(5, tNuevo.getJuego().getId());
			pstmt.setInt(6, tNuevo.getTipoTorneo().getId());
			pstmt.setObject(7, tNuevo.getFechaInicio());
			pstmt.setObject(1, tNuevo.getFechaFin());
			pstmt.setInt(2, tNuevo.getIntentos());
			pstmt.setInt(3, tNuevo.getCupo());
			pstmt.setString(4, tNuevo.getGanador());
			pstmt.setDouble(5, tNuevo.getMontoInsc());
			pstmt.executeQuery();
			
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
	
	public static Torneo getTorneoJugadorActual(Jugador jug) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Torneo t = new Torneo();
		Juego j = new Juego();
		TipoTorneo tt = new TipoTorneo();
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
					"SELECT jue.id, jue.denominacion, tt.id, tt.denominacion, fecha_inicio, fecha_fin, intentos, cupo, ganador, monto_insc " +
					"FROM inscripciones i " +
					"INNER JOIN torneos t ON i.id_juego = t.id_juego AND i.id_tipo = t.id_tipo AND t.fecha_inicio = i.fecha_inicio_torneo "+
					"INNER JOIN juegos jue ON jue.id = t.id_juego "+
					"INNER JOIN tipo_torneo tt ON tt.id = t.id_tipo "+
					"INNER JOIN jugadores j ON j.id = i.id_jugador " +
					"WHERE (j.id = ?) AND (curdate() BETWEEN fecha_inicio AND fecha_fin)"
					);
			
			pstmt.setInt(1, jug.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				j.setId(rs.getInt("jue.id"));
				j.setDenominacion(rs.getString("jue.denominacion"));
				tt.setId(rs.getInt("tt.id"));
				tt.setDenominacion(rs.getString("tt.denominacion"));
				t.setFechaInicio(rs.getObject("fecha_inicio", LocalDate.class));
				t.setFechaFin(rs.getObject("fecha_fin", LocalDate.class));
				t.setIntentos(rs.getInt("intentos"));
				t.setCupo(rs.getInt("cupo"));
				t.setGanador(rs.getString("ganador"));
				t.setMontoInsc(rs.getFloat("monto_insc"));
				t.setJuego(j);
				t.setTipoTorneo(tt);
			}
			
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
		
		return t;
		
	}
	
}
