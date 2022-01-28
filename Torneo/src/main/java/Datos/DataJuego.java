package Datos;

import java.sql.*;
import java.util.LinkedList;
import Entidades.Juego;

public class DataJuego {

	// Listar 
		public static LinkedList<Juego> list(){
			
			LinkedList<Juego> juegos= new LinkedList<Juego>();
			Statement stmt = null;
			ResultSet rs = null;
			Connection conn = null;
			
			try {
				
				conn = DbConnector.getInstancia().getConn();
				
				// Ejecutar querys
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM Juegos");
				
				while(rs.next()) /*Empieza apuntando en -1*/ {
					
					Juego j = new Juego();
					
					j.setId(rs.getInt("id"));
					j.setDenominacion(rs.getString("denominacion"));
					
					juegos.add(j);
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
			
			return juegos;
		}
		
		//Búsqueda
		public static Juego search(String denominacion) {
			
			Juego j = null;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				// conexion
				conn = DbConnector.getInstancia().getConn();
				
				stmt = conn.prepareStatement("SELECT * FROM Juegos WHERE denominacion=?");
				//setear parametros
				stmt.setString(1, denominacion);
				
				j = new Juego();
				
				//resultados
				rs = stmt.executeQuery();
				
				//mapear
				if(rs.next()) {
					
					j.setId(rs.getInt("id"));
					j.setDenominacion(rs.getString("denominacion"));
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
		
		public static Juego search(int idJuego) {
			
			Juego j = null;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				// conexion
				conn = DbConnector.getInstancia().getConn();
				
				stmt = conn.prepareStatement("SELECT * FROM Juegos WHERE id=?");
				//setear parametros
				stmt.setInt(1, idJuego);
				
				j = new Juego();
				
				//resultados
				rs = stmt.executeQuery();
				
				//mapear
				if(rs.next()) {
					
					j.setId(rs.getInt("id"));
					j.setDenominacion(rs.getString("denominacion"));
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
		
		//cargar
		public static void create(int id, String denominacion) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			Juego jNuevo = new Juego();
			jNuevo.setId(id);
			jNuevo.setDenominacion(denominacion);
			
			try {
				// crear conexion
				conn = DbConnector.getInstancia().getConn();
				
				//query
				pstmt = conn.prepareStatement(
				"INSERT INTO juegos(id, denominacion) VALUES(?,?);");
				
				pstmt.setInt(1, jNuevo.getId());
				pstmt.setString(2, jNuevo.getDenominacion());
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
		public static void delete(int id) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				// crear conexion
				conn = DbConnector.getInstancia().getConn();
				
				//query
				pstmt = conn.prepareStatement(
				"DELETE FROM juegos WHERE id = ?;" 
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
		
		//actualizar
		public static void update(int id, String denominacion) {
			
			PreparedStatement pstmt = null;
			Connection conn = null;
			Juego jNuevo = new Juego();
			jNuevo.setId(id);
			jNuevo.setDenominacion(denominacion);
			
			try {
				// crear conexion
				conn = DbConnector.getInstancia().getConn();
				
				//query
				pstmt = conn.prepareStatement(
						"Update juegos SET denominacion=? WHERE id=?;" 
						);
				
				pstmt.setString(1, jNuevo.getDenominacion());
				pstmt.setInt(2, jNuevo.getId());
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
