package Datos;
import java.sql.*;
import java.util.LinkedList;

import Entidades.Jugador;
import Entidades.Pais;
import Entidades.TipoTorneo;

public class DataJugador {
	
	public static LinkedList<Jugador> list(){

		Connection conn = null;

		ResultSet rs = null;

		Statement stmt = null;
		
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();

		try {

			conn = DbConnector.getInstancia().getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select j.id, j.nombre, apellido, usuario, contraseña, p.id, p.nombre from jugadores j INNER JOIN paises p ON p.id = j.id_pais");

			if (rs != null) {

				while (rs.next()) {
					Jugador j = new Jugador();
					Pais p = new Pais();
					j.setId(rs.getInt("id"));
					j.setNombre(rs.getString("j.nombre"));
					j.setApellido(rs.getString("apellido"));
					j.setUsuario(rs.getString("usuario"));
					j.setContraseña(rs.getString("contraseña"));
					p.setId(rs.getInt("p.id"));
					p.setNombre(rs.getString("p.nombre"));
					j.setPais(p);
					jugadores.add(j);	
				}
			}
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
		return jugadores;
	}
	
	public static Jugador search(String usuario) {
		Jugador j = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// conexion
			conn = DbConnector.getInstancia().getConn();

			stmt = conn.prepareStatement("SELECT * FROM jugadores WHERE usuario=?");
			//setear parametros
			stmt.setString(1, usuario);

			j = new Jugador();
			Pais p = new Pais();

			//resultados
			rs = stmt.executeQuery();

			//mapear
			if(rs.next()) {

				j.setId(rs.getInt("id"));
				j.setUsuario(rs.getString("usuario"));
				j.setContraseña(rs.getString("contraseña"));
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
				p.setId(rs.getInt("id_pais"));
				j.setPais(p);
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
	
	public static void create(String usuario, String contraseña, String nombre, String apellido, int idPais) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Jugador jNuevo = new Jugador();
		Pais p = new Pais();
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			jNuevo.setUsuario(usuario);
			jNuevo.setContraseña(contraseña);
			jNuevo.setNombre(nombre);
			jNuevo.setApellido(apellido);
			p.setId(idPais);
			jNuevo.setPais(p);
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO jugadores(id, usuario, nombre, apellido, contraseña, id_pais) VALUES(?,?,?,?,?,?)");
			
			pstmt.setInt(1, jNuevo.getId());
			pstmt.setString(2, jNuevo.getUsuario());
			pstmt.setString(3, jNuevo.getNombre());
			pstmt.setString(4, jNuevo.getApellido());
			pstmt.setString(5, jNuevo.getContraseña());
			pstmt.setInt(6, jNuevo.getPais().getId());
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

	public static void delete(String usuario) {
		
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
	public static void update(int id, String usuario, String contraseña, String nombre, String apellido, Pais p) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		Jugador jNuevo = new Jugador();
		jNuevo.setId(id);
		jNuevo.setUsuario(usuario);
		jNuevo.setContraseña(contraseña);
		jNuevo.setNombre(nombre);
		jNuevo.setApellido(apellido);
		jNuevo.setPais(p);
		

		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();

			//query
			pstmt = conn.prepareStatement(
					"Update jugadores "
							+ "SET usuario=?, contraseña=?, nombre=?, apellido=?, id_pais=?"
							+ "WHERE id=? " 
					);

			pstmt.setInt(5, jNuevo.getId());
			pstmt.setString(1, jNuevo.getUsuario());
			pstmt.setString(2, jNuevo.getUsuario());
			pstmt.setString(3, jNuevo.getNombre());
			pstmt.setString(4, jNuevo.getApellido());
			pstmt.setInt(5, jNuevo.getPais().getId());
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
	
	public static LinkedList<Jugador> listaPais(String nombrePais){
		
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

	public static LinkedList<Jugador> listaJugadoresJuego(String nombreJuego) throws SQLException {

		Connection conn = null;

		ResultSet rs = null;

		PreparedStatement stmt = null;

		try {

			LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("Select id_jugador, id_pais, usuario, nombre, apellido from partidas p "
					+ "Inner join jugadores j on j.id = p.id_jugador "
					+ "inner join juegos ju on ju.id = p.id_juego "
					+ "where denominacion = ?");
			stmt.setString(1, nombreJuego);
			rs = stmt.executeQuery();
			if (rs != null) {

				while (rs.next()) {

					Jugador jugador = new Jugador();
					Pais pais = new Pais();
					pais.setId(rs.getInt("id_pais"));
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
