package Entidades;

public class Cliente {
	
	private int id;
	private String nombre;
	private String direccion;
	private float saldo;
	
	
	public Cliente(int codigo, String nombre, String domicilio, float saldo) {
		super();
		this.id = codigo;
		this.nombre = nombre;
		this.direccion = domicilio;
		this.saldo = saldo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int codigo) {
		this.id = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String domicilio) {
		this.direccion = domicilio;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", saldo=" + saldo + "]";
	}

	
	

}
