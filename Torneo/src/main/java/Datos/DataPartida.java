package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

import Entidades.*;

public class DataPartida {

	public static void create(LocalDateTime fechaHora, Jugador jugador, Juego juego, int puntaje) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			Partida pNueva = new Partida();
			pNueva.setFechaHora(fechaHora);
			pNueva.setJugador(jugador);
			pNueva.setJuego(juego);
			pNueva.setPuntaje(puntaje);
			
			try {
				// crear conexion
				conn = DbConnector.getInstancia().getConn();
				
				//query
				pstmt = conn.prepareStatement(
				"INSERT INTO partidas(fecha_hora, id_jugador, id_juego, puntaje) "
				+ "VALUES(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
				);
				
				pstmt.setObject(1, pNueva.getFechaHora());
				pstmt.setInt(2, pNueva.getJugador().getId());
				pstmt.setInt(3, pNueva.getJuego().getId());
				pstmt.setInt(4, pNueva.getPuntaje());
				pstmt.executeUpdate();
				// Autoincrement
				ResultSet keyResultSet=null;	
				keyResultSet=pstmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	            	pNueva.setId(keyResultSet.getInt(1));
	            }
				
				
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
	
}
