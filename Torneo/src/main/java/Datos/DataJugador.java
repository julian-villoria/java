package Datos;
import java.sql.*;
import java.util.LinkedList;

import Entidades.Jugador;
import Entidades.Pais;

public class DataJugador {
	
	public static void nuevo(Jugador nuevoJugador) {
		
		Connection conn = null;
		
		ResultSet keyrs = null;
		
		PreparedStatement stmt = null;
		
		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("INSERT INTO jugadores (usuario, nombre, apellido, contraseña, id_pais) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nuevoJugador.getUsuario());
			stmt.setString(2, nuevoJugador.getNombre());
			stmt.setString(3, nuevoJugador.getApellido());
			stmt.setString(4, nuevoJugador.getContraseña());
			stmt.setInt(5, nuevoJugador.getPais().getId());
			stmt.executeUpdate();
			keyrs = stmt.getGeneratedKeys();
			
			if (keyrs != null && keyrs.next()) {
				
				nuevoJugador.setId(keyrs.getInt(1));
			}	
			
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
	
	public void create(int id, String usuario, String contraseña, String nombre, String apellido) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Jugador jNuevo = new Jugador();
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			jNuevo.setId(id);
			jNuevo.setUsuario(usuario);
			jNuevo.setContraseña( contraseña );
			jNuevo.setNombre(nombre);
			jNuevo.setApellido(apellido);
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO jugadores(id, usuario, nombre, apellido, contraseña) VALUES(?,?,?,?,?)");
			
			pstmt.setInt(1, jNuevo.getId());
			pstmt.setString(2, jNuevo.getUsuario());
			pstmt.setString(3, jNuevo.getNombre());
			pstmt.setString(4, jNuevo.getApellido());
			pstmt.setString(5, jNuevo.getContraseña());
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
	
	//Delete otro
	public static void borrar(Jugador borrarJugador) {
	
		Connection conn = null;
		PreparedStatement stmt = null;
	
		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("delete from tipo_torneo where usuario = ?");
			stmt.setString(1, borrarJugador.getUsuario());
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

	public void delete(String usuario) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"DELETE FROM jugadores WHERE usuario = ?" 
					);
			
			pstmt.setString(1,usuario);
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
	public static void update(int id, String usuario, String contraseña, String nombre, String apellido) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		Jugador jNuevo = new Jugador();
		jNuevo.setId(id);
		jNuevo.setUsuario(usuario);
		jNuevo.setContraseña(contraseña);
		jNuevo.setNombre(nombre);
		jNuevo.setApellido(apellido);
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"Update jugadores "
			+ "SET usuario=?, contraseña=?, nombre=?, apellido=?"
			+ "WHERE id=? " 
					);
			
			pstmt.setInt(5, jNuevo.getId());
			pstmt.setString(1, jNuevo.getUsuario());
			pstmt.setString(2, jNuevo.getUsuario());
			pstmt.setString(3, jNuevo.getNombre());
			pstmt.setString(4, jNuevo.getApellido());
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

	public static Jugador login(String usuario, String contraseña) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Jugador j = new Jugador();
		
		try {
			// conexion
			conn = DbConnector.getInstancia().getConn();
			
			stmt = conn.prepareStatement("SELECT * FROM Jugadores j WHERE j.usuario = ? AND j.contraseña = ?");
			//setear parametros
			stmt.setString(1, usuario);
			stmt.setString(2, contraseña);
			
			//resultados
			rs = stmt.executeQuery();
			
			//mapear
			if(rs.next()) {
				j.setId(rs.getInt("id"));
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
				j.setUsuario(rs.getString("usuario"));
				j.setContraseña(rs.getString("contraseña"));
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
		return j;
	}
	
	public static LinkedList<Jugador> listaPais(String nombrePais) throws SQLException {
		
		Connection conn = null;
		
		ResultSet rs = null;
		
		PreparedStatement stmt = null;
		
		try {
			
			LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("select * from jugadores j inner join paises p on p.id = j.id_pais where p.nombre = ?");
			stmt.setString(1, nombrePais);
			rs = stmt.executeQuery();
			if (rs != null) {
				
				while (rs.next()) {
					
					Jugador jugador = new Jugador();
					Pais pais = new Pais();
					pais.setId(rs.getInt("id_pais"));
					pais.setNombre("nombre");
					jugador.setId(rs.getInt("id"));
					jugador.setUsuario(rs.getString("usuario"));
					jugador.setNombre(rs.getString("nombre"));
					jugador.setApellido(rs.getString("apellido"));
					jugador.setPais(pais);
					jugadores.add(jugador);	
				}
			}
			return jugadores;
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
	
}
