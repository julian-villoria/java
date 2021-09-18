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
	public LinkedList<TipoTorneo> list(){
		
		LinkedList<TipoTorneo> tipos= new LinkedList<TipoTorneo>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			
			conn = ConectionFactory.getConnection();
			
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
		
		return tipos;
	}
	
	//Búsqueda
	public TipoTorneo search(String denominacion) {
		
		TipoTorneo tt = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// conexion
			conn = ConectionFactory.getConnection();
			
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
		return tt;
	}
	
	//cargar
	public void create(int id, String denominacion) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		TipoTorneo ttNuevo = new TipoTorneo();
		ttNuevo.setId(id);
		ttNuevo.setDenominacion(denominacion);
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO tipo_torneo(id, denominacion) VALUES(?,?);");
			
			pstmt.setInt(1, ttNuevo.getId());
			pstmt.setString(2, ttNuevo.getDenominacion());
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
	public void delete(int id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"DELETE FROM tipo_torneo WHERE id = ?;" 
					);
			
			pstmt.setInt(1, id);
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
	public void update(int id, String denominacion) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		TipoTorneo ttNuevo = new TipoTorneo();
		ttNuevo.setId(id);
		ttNuevo.setDenominacion(denominacion);
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
					"Update PeriodoInscripcion SET denominacion=? WHERE id=?;" 
					);
			
			pstmt.setObject(1, ttNuevo.getDenominacion());
			pstmt.setInt(2, ttNuevo.getId());
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
