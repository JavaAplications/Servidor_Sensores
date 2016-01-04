package core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;



import javax.swing.JTextArea;

import bbdd.Conexion;



public class ClaseServidor extends Thread{
	
	private ServerSocket sk = null;
	Socket socketclient;
	Servidor_Hilo  Cliente_Hilo;
    private String NombreCliente;
    private boolean BoolCliente=true;
    private int Puerto;
    Conexion con;
 
    private boolean continuar = true;
  
    public ClaseServidor(int Puerto,Conexion con) {
      
    	this.Puerto=Puerto;
        this.con=con;
       
    }
    
    public void StopServer(){
    	  continuar = false;
    	  try {
			sk.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void run() {
    	
		   try {
		         sk = new ServerSocket(Puerto);
		         System.out.println();  
	        	 System.out.println("**************************************************");
			     System.out.println("*********      Servidor Sensores      ************");
	             System.out.println("*********    IP: "+InetAddress.getLocalHost().getHostAddress()+":"+Puerto+"     **********");
	             System.out.println("**************************************************");
	             System.out.println();
		         
	          
	             while (continuar) { 
		        	 // System.out.println("Esperando cliente:");
		        	  
		        	  socketclient = sk.accept();// se queda a la espera de un cliente
		              //System.out.println("Ingreso Cliente por el Puerto:"+socketclient.getPort());
		             Cliente_Hilo= new Servidor_Hilo(socketclient,con);
		             Cliente_Hilo.start();
		         }
		   } catch (IOException e) {
		          System.out.println("El Puerto se encuentra ocupado");
		          
		            
		          
		          
		         
		   }
    	
    
    }

}
