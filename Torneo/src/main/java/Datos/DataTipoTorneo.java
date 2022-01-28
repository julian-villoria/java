package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entidades.TipoTorneo;

public class DataTipoTorneo {

	// Listar 
	public static LinkedList<TipoTorneo> list(){
		
		LinkedList<TipoTorneo> tipos= new LinkedList<TipoTorneo>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = DbConnector.getInstancia().getConn();

			// Ejecutar querys
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tipo_torneo");

			while(rs.next()) /*Empieza apuntando en -1*/ {

				TipoTorneo tt = new TipoTorneo();

				tt.setId(rs.getInt("id"));
				tt.setDenominacion(rs.getString("denominacion"));
				tipos.add(tt);
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

		return tipos;
	}

	//Búsqueda

	public static TipoTorneo search(String denominacion) {
		
		TipoTorneo tt = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// conexion
			conn = DbConnector.getInstancia().getConn();

			stmt = conn.prepareStatement("SELECT * FROM tipo_torneo WHERE denominacion=?");
			//setear parametros
			stmt.setString(1, denominacion);

			tt = new TipoTorneo();

			//resultados
			rs = stmt.executeQuery();

			//mapear
			if(rs.next()) {

				tt.setId(rs.getInt("id"));
				tt.setDenominacion(rs.getString("denominacion"));
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
		return tt;
	}
	
public static TipoTorneo search(int idTipo) {
		
		TipoTorneo tt = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// conexion
			conn = DbConnector.getInstancia().getConn();
			
			stmt = conn.prepareStatement("SELECT * FROM tipo_torneo WHERE id=?");
			//setear parametros
			stmt.setInt(1, idTipo);
			
			tt = new TipoTorneo();
			
			//resultados
			rs = stmt.executeQuery();
			
			//mapear
			if(rs.next()) {
				
				tt.setId(rs.getInt("id"));
				tt.setDenominacion(rs.getString("denominacion"));
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
		return tt;
	}
	
	public static void create(TipoTorneo nuevoTipo) {
		
		Connection conn = null;

		ResultSet keyrs = null;

		PreparedStatement stmt = null;

		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("insert into tipo_torneo (denominacion) values (?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nuevoTipo.getDenominacion()); 
			stmt.executeUpdate();
			keyrs = stmt.getGeneratedKeys();

			if (keyrs != null && keyrs.next()) {

				nuevoTipo.setId(keyrs.getInt(1));
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

	public void create(String denominacion) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		TipoTorneo ttNuevo = new TipoTorneo();
		ttNuevo.setDenominacion(denominacion);
		ResultSet keyrs = null;
		
		try {
			conn = DbConnector.getInstancia().getConn();
			pstmt = conn.prepareStatement("insert into tipo_torneo (denominacion) values (?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, ttNuevo.getDenominacion()); 
			pstmt.executeUpdate();
			keyrs = pstmt.getGeneratedKeys();
			
			if (keyrs != null && keyrs.next()) {
				
				ttNuevo.setId(keyrs.getInt(1));
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {	
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (keyrs != null) keyrs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	//borrarID
	public static void delete(int id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"DELETE FROM tipo_torneo WHERE id = ?;" 
					);
			
			pstmt.setInt(1, id);
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
	
	//borrarDenom
	public static void delete(String denominacionEliminar) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();

			//query
			pstmt = conn.prepareStatement(
					"DELETE FROM tipo_torneo WHERE denominacion = ?;" 
					);

			pstmt.setString(1, denominacionEliminar);
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

	public static void update(int id, String denominacion) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		TipoTorneo ttNuevo = new TipoTorneo();
		ttNuevo.setId(id);
		ttNuevo.setDenominacion(denominacion);

		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();

			//query
			pstmt = conn.prepareStatement(
					"Update tipo_torneo SET denominacion=? WHERE id=?;" 
					);

			pstmt.setObject(1, ttNuevo.getDenominacion());
			pstmt.setInt(2, ttNuevo.getId());
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
