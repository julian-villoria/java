package Negocio;

import Datos.DataJugador;
import Entidades.Encrypt;
import Entidades.Jugador;

public class Login {
	
	public static Jugador validate(String usuario, String contrase�a) {
		
		Jugador j = new Jugador();
		String hash = Encrypt.convertirSHA256(contrase�a);
		System.out.println(hash);
		j = DataJugador.login(usuario, hash);
		return j;
	}
}
