package Datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.*;

import Entidades.Jugador;

public class DataJugador {
	
public void create(int id, String usuario, String contraseña, String nombre, String apellido) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Jugador jNuevo = new Jugador();
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			jNuevo.setId(id);
			jNuevo.setUsuario(usuario);
			jNuevo.setContraseña( md.digest(contraseña.getBytes()).toString() );
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
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchAlgorithmException ex) {
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
	public void delete(String usuario) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"DELETE FROM jugadores WHERE usuario = ?" 
					);
			
			pstmt.setString(1,usuario);
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
	public void update(int id, String usuario, String contraseña, String nombre, String apellido) {
		
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
			conn = ConectionFactory.getConnection();
			
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

	public Jugador login(String usuario, String contraseña) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Jugador j = new Jugador();
		
		try {
			// conexion
			conn = ConectionFactory.getConnection();
			
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
		return j;
	}
	
}
