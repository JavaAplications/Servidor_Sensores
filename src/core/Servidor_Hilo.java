package core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

import bbdd.Conexion;

public class Servidor_Hilo extends Thread{
	

    private Socket socketclient;
    private DataOutputStream dos;
    private DataInputStream dis;
    Conexion Conectar;
    private static final Pattern SPACE = Pattern.compile(" ");
    String NombreCliente;
    BufferedReader entrada ;
    int ID_Sensor,Estado;
    String MensajeValido;
    PrintWriter salida;
 
   
   
    public Servidor_Hilo(Socket socket,Conexion con) {
        this.socketclient = socket;
        this.Conectar=con;
    }
    
    
    public void desconnectar() {
        try {
        	socketclient.close();
        } catch (IOException ex) {
           
        }
    }
   
    public void run() {
    	  String datos;
    	  
		try {
			entrada = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));  
			datos = entrada.readLine();
			System.out.println("Dato Recibido:"+"'"+datos+"'");
			String[] arr = SPACE.split(datos); // str is the string to be split
			MensajeValido=arr[0];
			ID_Sensor=Integer.parseInt(arr[1]);
			Estado=Integer.parseInt(arr[2]);
			// cuando la alarma es '1' es una keep alive.
			// caso contrario es una alarma de algun tipo.
			
			System.out.print("MensajeValido: "+MensajeValido);
			System.out.print(" ID_Sensor: "+ID_Sensor);
			System.out.println(" Estado: "+Estado);
			
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
    
		desconnectar();
    }

}
