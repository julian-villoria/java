package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import Entidades.PeriodoInscripcion;

public class DataPeriodoInscripcion {
	
	// Listar 
	public LinkedList<PeriodoInscripcion> list(){
		
		LinkedList<PeriodoInscripcion> periodos= new LinkedList<PeriodoInscripcion>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			
			conn = ConectionFactory.getConnection();
			
			// Ejecutar querys
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Periodo_Inscripcion");
			
			while(rs.next()) /*Empieza apuntando en -1*/ {
				
				PeriodoInscripcion pi = new PeriodoInscripcion();
				
				pi.setId(rs.getInt("id"));
				pi.setFechaDesde(rs.getObject("fecha_desde", LocalDate.class));
				pi.setFechaHasta(rs.getObject("fecha_hasta", LocalDate.class));
				
				periodos.add(pi);
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
		
		return periodos;
	}
	
	//Búsqueda
	public PeriodoInscripcion search(int id) {
		
		PeriodoInscripcion pi = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// conexion
			conn = ConectionFactory.getConnection();
			
			stmt = conn.prepareStatement("SELECT * FROM Periodo_Inscripcion WHERE id=?");
			//setear parametros
			stmt.setInt(1,id);
			
			pi = new PeriodoInscripcion();
			
			//resultados
			rs = stmt.executeQuery();
			
			//mapear
			if(rs.next()) {
				
				pi.setId(rs.getInt("id"));
				pi.setFechaDesde(rs.getObject("fecha_desde", LocalDate.class));
				pi.setFechaHasta(rs.getObject("fecha_hasta", LocalDate.class));
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
		return pi;
	}
	
	//cargar
	public void create(LocalDate fechaDesde, LocalDate fechaHasta) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PeriodoInscripcion piNuevo = new PeriodoInscripcion();
		piNuevo.setFechaDesde(fechaDesde);
		piNuevo.setFechaHasta(fechaHasta);
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO periodo_inscripcion(fecha_desde, fecha_hasta) VALUES(?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setObject(1, piNuevo.getFechaDesde());
			pstmt.setObject(2, piNuevo.getFechaHasta());
			
			
			pstmt.executeUpdate();
			
			//set id autoincremental
			ResultSet keyResultSet=null;	
			keyResultSet=pstmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	piNuevo.setId(keyResultSet.getInt(1));
            }
			
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
			"DELETE FROM Periodo_Inscripcion WHERE id = ?;" 
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
	public void update(int id, LocalDate fechaDesde, LocalDate fechaHasta) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		PeriodoInscripcion piNuevo = new PeriodoInscripcion();
		piNuevo.setId(id);
		piNuevo.setFechaDesde(fechaDesde);
		piNuevo.setFechaHasta(fechaHasta);
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
					"Update Periodo_Inscripcion SET fecha_desde=?, fecha_hasta=? WHERE id=?;" 
					);
			
			pstmt.setObject(1, piNuevo.getFechaDesde());
			pstmt.setObject(2, piNuevo.getFechaHasta());
			pstmt.setInt(3, piNuevo.getId());
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
