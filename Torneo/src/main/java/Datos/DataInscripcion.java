package Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import Entidades.Inscripcion;
import Entidades.Jugador;
import Entidades.Torneo;

public class DataInscripcion {

public void create(Torneo t, Jugador jug) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Inscripcion iNueva = new Inscripcion();
		iNueva.setTorneo(t);
		iNueva.setJugador(jug);
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO inscripciones (id_jugador, id_juego, id_tipo, fecha_inicio_torneo, fecha_inscripcion) "
			+ "VALUES(?,?,?,?,current_date())");
			
			pstmt.setInt(1, iNueva.getJugador().getId());
			pstmt.setInt(2, iNueva.getTorneo().getJuego().getId());
			pstmt.setInt(3, iNueva.getTorneo().getTipoTorneo().getId());
			pstmt.setDate(4, iNueva.getTorneo().getFechaInicio());
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
	
}
