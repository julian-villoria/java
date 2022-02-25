package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.LinkedList;

import Entidades.*;

public class DataPartida {

	public void create(LocalDateTime fechaHora, Jugador jugador, Juego juego, int puntaje) {
			
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

	public LinkedList<Partida> listPartidasTorneo(Torneo t) {
		
		Connection conn = null;

		ResultSet rs = null;
		
		PreparedStatement stmt = null;
		
		LinkedList<Partida> partidas = new LinkedList<Partida>();

		try {

			conn = DbConnector.getInstancia().getConn();
			stmt = conn.prepareStatement("select jug.usuario, j.denominacion, p.puntaje "
					+ "FROM partidas p "
					+ "INNER JOIN juegos j ON j.id = p.id_juego "
					+ "INNER JOIN jugadores jug ON jug.id = p.id_jugador "
					+ "INNER JOIN torneos t ON t.id_juego = j.id "
					+ "INNER JOIN tipo_torneo tt ON t.id_tipo = tt.id "
					+ "WHERE t.id_juego = ? AND t.id_tipo = ? AND t.fecha_inicio = ? ORDER BY p.puntaje DESC;");
			
			stmt.setInt(1, t.getJuego().getId());
			stmt.setInt(2, t.getTipoTorneo().getId());
			stmt.setObject(3, t.getFechaInicio());
			
			rs = stmt.executeQuery();

			if (rs != null) {

				while (rs.next()) {
					Partida p = new Partida();
					Juego j = new Juego();
					Jugador jug = new Jugador();
					jug.setUsuario(rs.getString("jug.usuario"));
					j.setDenominacion(rs.getString("j.denominacion"));
					p.setPuntaje(rs.getInt("p.puntaje"));
					p.setJugador(jug);
					p.setJuego(j);
					partidas.add(p);	
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return partidas;
	}
	
	public int contador(Torneo t, Jugador j) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cont = 0;
		
		try {
			// crear conexion
			conn = DbConnector.getInstancia().getConn();
			
			//query
			pstmt = conn.prepareStatement(
							"SELECT count(*) "
							+ "FROM partidas p "
							+ "INNER JOIN juegos j ON j.id = p.id_juego "
							+ "INNER JOIN torneos t ON t.id_juego = j.id "
							+ "INNER JOIN tipo_torneo tt ON t.id_tipo = tt.id "
							+ "WHERE t.id_juego = ? AND t.id_tipo = ? AND t.fecha_inicio = ? AND p.id_jugador = ?"
					);
			
			pstmt.setInt(1, t.getJuego().getId());
			pstmt.setInt(2, t.getTipoTorneo().getId());
			pstmt.setObject(3, t.getFechaInicio());
			pstmt.setInt(4, j.getId());
			rs = pstmt.executeQuery();
			
			if(rs != null){
				while(rs.next()) /*Empieza apuntando en -1*/ {
					cont = rs.getInt("count(*)");
				}
			}
			
			if(pstmt!=null) {pstmt.close();}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cont;
	}
	
}

