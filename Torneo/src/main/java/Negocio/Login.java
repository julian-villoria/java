package Negocio;

import Datos.DataJugador;
import Entidades.Encrypt;
import Entidades.Jugador;

public class Login {
	
	public static Jugador validate(String usuario, String contrase�a) {
		
		Jugador j = new Jugador();
		DataJugador dj = new DataJugador();
		String hash = Encrypt.convertirSHA256(contrase�a);
		j = dj.login(usuario, hash);
		return j;
	}
}
