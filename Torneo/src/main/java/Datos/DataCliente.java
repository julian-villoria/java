package Datos;

import java.sql.*;
import java.util.ArrayList;

import Entidades.*;

public abstract class DataCliente {
	
	@SuppressWarnings("finally")
	public static ArrayList<Cliente> getAll(){
		Connection  conn = null;
		Statement st=null;
		ResultSet rs=null;
		Cliente a=null ;
		ArrayList <Cliente> losClientes = new ArrayList<Cliente>();
		    // Do something with the Connection
	    try {
	      conn = ConectionFactory.getConnection();
		  st = conn.createStatement();
		  rs = st.executeQuery("SELECT * FROM CLIENTES");
		  while(rs.next()) {
		  a = new Cliente(rs.getInt("codigo"), 
					      rs.getString("nombre"), 
				          rs.getString("domicilio"),
				          rs.getFloat("saldo"));
		  losClientes.add(a);
		  }
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	    finally {
	    	try {
	    	if (rs!=null) {rs.close();};
	    	if (st!=null) {st.close();};
	    	if (conn!=null) {conn.close();};
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	return losClientes;
	    }
	}
	
	@SuppressWarnings("finally")
	public static Cliente getCliente(int id) {
	Connection  conn = null;
	Statement st=null;
	ResultSet rs=null;
	Cliente a=null ;
	    // Do something with the Connection
    try {
      conn = ConectionFactory.getConnection();
	  st = conn.createStatement();
	  rs = st.executeQuery("SELECT * FROM CLIENTES WHERE codigo = "+id);
	  rs.next();
	  a = new Cliente(rs.getInt("codigo"), 
				                  rs.getString("nombre"), 
			                      rs.getString("domicilio"),
			                      rs.getFloat("saldo"));
				                  
	
	 
	} catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
    finally {
    	try {
    	if (rs!=null) {rs.close();};
    	if (st!=null) {st.close();};
    	if (conn!=null) {conn.close();};
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return a;
    }
	}
}
