package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import Entidades.Inscripcion;
import Entidades.Jugador;
import Entidades.Torneo;

public class DataInscripcion {

public void create(Torneo t, Jugador jug, LocalDate fecha) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Inscripcion iNueva = new Inscripcion();
		iNueva.setTorneo(t);
		iNueva.setFecha(fecha);
		iNueva.setJugador(jug);
		
		try {
			// crear conexion
			conn = ConectionFactory.getConnection();
			
			//query
			pstmt = conn.prepareStatement(
			"INSERT INTO inscripciones(id_juego, id_tipo, id_jugador, fecha_inicio_torneo, fecha) VALUES(?,?,?,?,?)");
			
			pstmt.setInt(1, iNueva.getTorneo().getJuego().getId());
			pstmt.setInt(2, iNueva.getTorneo().getTipoTorneo().getId());
			pstmt.setInt(3, iNueva.getJugador().getId());
			pstmt.setObject(5, iNueva.getTorneo().getFechaInicio());
			pstmt.setObject(4, iNueva.getFecha());
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
	
}
