package Datos;

import java.sql.*;

public abstract class ConectionFactory {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
	
		 Class.forName("com.mysql.cj.jdbc.Driver");  
        // System.out.println("Driver successfully registered !");  
         return DriverManager.getConnection("jdbc:mysql://localhost/torneo?" +
	                                   "user=root&password=");
// probando cambios 1234213532145afsadgsdag
	}
	
}