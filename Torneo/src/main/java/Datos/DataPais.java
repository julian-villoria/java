package Datos;

import java.sql.*;


import java.util.LinkedList;

import Entidades.Pais;
import Entidades.TipoTorneo;

public class DataPais {
	
		public LinkedList<Pais> list() throws SQLException {
		
		Connection conn = null;
		
		ResultSet rs = null;
		
		Statement stmt = null;
		
		try {
			
			LinkedList<Pais> paises = new LinkedList<Pais>();
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from paises");
			
			if (rs != null) {
				
				while (rs.next()) {
					
					Pais pais = new Pais();
					pais.setId(rs.getInt("id"));
					pais.setNombre(rs.getString("nombre"));
					paises.add(pais);	
				}
			}
			return paises;
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

	public Object buscar(Pais buscarPais) {
		
		Pais p = null;
		
		Connection conn = null;
		
		ResultSet rs = null;
		
		PreparedStatement stmt = null;
		
		try {
			
			conn = DbConnector.getInstancia().getConn();
			
			stmt = conn.prepareStatement("selecct * from tipo_torneo where nombre = ?");
			
			stmt.setString(1, buscarPais.getNombre());
			
			rs = stmt.executeQuery();
			
			if (rs != null && rs.next()) {
				p = new Pais();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
			}
			
			return p;
			
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
		
		return p;
	}

	public void nuevo(Pais nuevoPais) {
		
		Connection conn = null;
		
		ResultSet keyrs = null;
		
		PreparedStatement stmt = null;
		
		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("insert into paises (nombre) values (?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nuevoPais.getNombre());
			stmt.executeUpdate();
			keyrs = stmt.getGeneratedKeys();
			
			if (keyrs != null && keyrs.next()) {
				
				nuevoPais.setId(keyrs.getInt(1));
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

	public void borrar(Pais borrarPais) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("delete from paises where id = ?");
			stmt.setInt(1, borrarPais.getId());
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
	
	public void update(int id, String denominacion) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		Pais pNuevo = new Pais();
		pNuevo.setId(id);
		pNuevo.setNombre(denominacion);
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
					"Update paises SET nombre=? WHERE id=?;" 
					);
			
			pstmt.setObject(1, pNuevo.getNombre());
			pstmt.setInt(2, pNuevo.getId());
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
