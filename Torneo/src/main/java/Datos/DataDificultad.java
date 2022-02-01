package Datos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Entidades.Dificultad;
import Entidades.Juego;

public class DataDificultad {

public LinkedList<Dificultad> list() throws SQLException {
		
		Connection conn = null;
		
		ResultSet rs = null;
		
		Statement stmt = null;
		
		try {
			
			LinkedList<Dificultad> dificultades = new LinkedList<Dificultad>();
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from dificultad");
			
			if (rs != null) {
				
				while (rs.next()) {
					
					Dificultad dificultad = new Dificultad();
					Juego juego = new Juego();
					dificultad.setNombre(rs.getString("nombre"));
					dificultad.setRangoMinPuntajes(rs.getInt("rango_min_puntaje"));
					dificultad.setRangoMaxPuntajes(rs.getInt("rango_max_puntaje"));
					dificultad.setRangoMinVictorias(rs.getInt("rango_min_victoria"));
					dificultad.setRangoMaxVictorias(rs.getInt("rango_max_victoria"));
					juego.setId(rs.getInt("id_juego"));	
					dificultad.setJuego(juego);
					dificultades.add(dificultad);
				}
			}
			return dificultades;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
			if (rs != null) rs.close();
			if (stmt!=null) stmt.close();
			if (conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
	}

	public Object buscar(Dificultad buscaDificultad, Juego buscaJuego) { // aca pasamos los objetos o los nombres?
		
		Dificultad d = null;
	
		Juego j = null;
		
		Connection conn = null;
		
		ResultSet rs = null;
		
		PreparedStatement stmt = null;
		
		try {
			
			conn = DbConnector.getInstancia().getConn();
			
			stmt = conn.prepareStatement("selecct * from dificultad d inner join juego j on j.id = d.id_juego "
					+ "where d.nombre = ? and j.denominacion = ?");
			
			stmt.setString(1, buscaDificultad.getNombre());
			stmt.setString(2, buscaJuego.getDenominacion());
			
			rs = stmt.executeQuery();
			
			if (rs != null && rs.next()) {
				d = new Dificultad();
				d.setNombre(rs.getString("nombre"));
				d.setRangoMinPuntajes(rs.getInt("rango_min_puntaje"));
				d.setRangoMaxPuntajes(rs.getInt("rango_max_puntaje"));
				d.setRangoMinVictorias(rs.getInt("rango_min_victoria"));
				d.setRangoMaxVictorias(rs.getInt("rango_max_victoria"));
				j = new Juego();
				j.setId(rs.getInt("id"));
				j.setDenominacion(rs.getString("denominacion"));
				d.setJuego(j);
			}
			
			return d;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return d;
	}

	public void nuevo(Dificultad nuevaDificultad) {
		
		Connection conn = null;
		
		ResultSet keyrs = null;
		
		PreparedStatement stmt = null;
		
		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("insert into dificultad (nombre, rango_min_puntaje, rango_max_puntaje, rango_min_victoria, rango_max_victoria, id_juego) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nuevaDificultad.getNombre());
			stmt.setInt(2, nuevaDificultad.getRangoMinPuntajes());
			stmt.setInt(3, nuevaDificultad.getRangoMaxPuntajes());
			stmt.setInt(4, nuevaDificultad.getRangoMinVictorias());
			stmt.setInt(5, nuevaDificultad.getRangoMaxVictorias());
			stmt.setInt(6, nuevaDificultad.getJuego().getId());
			
			stmt.executeUpdate();
			keyrs = stmt.getGeneratedKeys();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {	
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (keyrs != null) keyrs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void borrar(Dificultad borrarDificultad, Juego borrarJuego) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("delete from dificultad d "
					+ "inner join juego j on j.id = d.id_juego "
					+ "where d.nombre = ? and j.denominacion = ?");
			
			stmt.setString(1, borrarDificultad.getNombre());
			stmt.setString(2, borrarJuego.getDenominacion());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public void borrar(String borrarDificultad, String borrarJuego) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("delete d from dificultad d "
					+ "inner join juegos j on j.id = d.id_juego "
					+ "where nombre = ? and denominacion = ?");
			
			stmt.setString(1, borrarDificultad);
			stmt.setString(2, borrarJuego);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public void update(Dificultad updateDificultad, Juego updateJuego) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
					"Update dificultad d INNER JOIN juegos j ON j.id=d.id_juego "
					+ "SET d.rango_min_puntaje=?, rango_max_puntaje=?, d.rango_min_victoria=?, d.rango_max_victoria=? WHERE nombre=? AND denominacion=?"
					);
			pstmt.setInt(1, updateDificultad.getRangoMinPuntajes());
			pstmt.setInt(2, updateDificultad.getRangoMaxPuntajes());
			pstmt.setInt(3, updateDificultad.getRangoMinVictorias());
			pstmt.setInt(4, updateDificultad.getRangoMaxVictorias());
			pstmt.setString(5, updateDificultad.getNombre());
			pstmt.setString(6, updateJuego.getDenominacion());
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
	
	public void update(Dificultad updateDificultad) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
					"Update dificultad d INNER JOIN juegos j ON j.id=d.id_juego "
					+ "SET d.rango_min_puntaje=?, rango_max_puntaje=?, d.rango_min_victoria=?, d.rango_max_victoria=? WHERE nombre=? AND denominacion=?" 
					);
			
			pstmt.setInt(1, updateDificultad.getRangoMinPuntajes());
			pstmt.setInt(2, updateDificultad.getRangoMaxPuntajes());
			pstmt.setInt(3, updateDificultad.getRangoMinVictorias());
			pstmt.setInt(4, updateDificultad.getRangoMaxVictorias());
			pstmt.setString(5, updateDificultad.getNombre());
			pstmt.setString(4, updateDificultad.getJuego().getDenominacion());
			
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
