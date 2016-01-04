package core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
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
    Conexion Con;
    private static final Pattern SPACE = Pattern.compile(" ");
    String NombreCliente;
    BufferedReader entrada ;
    int ID_Sensor;
    boolean Estado;
    String EstadoString;
    String MensajeValido;
    PrintWriter salida;
    String timeStamp;
    String IP;
   
   
    public Servidor_Hilo(Socket socket,Conexion con) {
        this.socketclient = socket;
        this.Con=con;
    }
    
    
    public void desconnectar() {
        try {
        	socketclient.close();
        	System.out.println("Cliente del puerto "+ socketclient.getPort()+" desconectado ");
        } catch (IOException ex) {
           
        }
    }
   
    public void run() {
    	  String datos;
    	 
		try {
			entrada = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));  
			datos = entrada.readLine();
			
			if(!datos.equals("")){
				
				
				System.out.println("Dato Recibido:"+"'"+datos+"'");
				String[] arr = SPACE.split(datos); // str is the string to be split
				MensajeValido=arr[0];
				
				// Check mensaje valido "$"
				if(MensajeValido.equals("$")){
				timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
				IP=socketclient.getInetAddress().getHostName();
				ID_Sensor=Con.Consultar_ID_Sensor(IP);
				ConversorBooleanoEstado(Integer.parseInt(arr[1]));
				System.out.println(timeStamp+" "+IP+" ID:"+ID_Sensor+" Estado:"+EstadoString );
				 
				// Insertar evento en la BBDD
				
				Con.Insertar_Evento(ID_Sensor, Estado);
				Con.Update_Status_Sensor(ID_Sensor, Estado);				}
					
				
			}else{
				System.out.println(" Dato Nulo");
			}
				} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}          
    
		desconnectar();
    }
    
    
    private boolean ConversorBooleanoEstado(int entrada){
		
    	switch (entrada) {
		case 1:
			Estado=true;
			EstadoString="Ocupado";
			break;

		default:
			Estado=false;
			EstadoString="Libre";
			break;
		}
    	
    
    	return Estado;
    	
   }
    

}
