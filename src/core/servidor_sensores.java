package core;

import bbdd.Conexion;

public class servidor_sensores {

	static ClaseServidor servidor;
	static Conexion con;
	public static void main(String[] args) {
	
		
		con=new Conexion();
		servidor=new ClaseServidor(9000,con);
		servidor.start();
	
	

	}

		
		
		
		
		
		
			}


