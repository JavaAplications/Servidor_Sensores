package bbdd;

import java.sql.*;

public class Conexion {
	Connection con=null;
	
public Conexion() {
		
	}

public  Connection Conectar(){
	
	
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con= DriverManager.getConnection("jdbc:mysql://localhost/bd_estacionamiento","root","");	
		//System.out.println("Conexion a BBDD.");
	} catch (Exception e) {
		System.out.println("No se pudo conectar a la BBDD");
	}
	return con;
	
}

public void Insertar_Evento(int ID_Sensor,boolean Estado){
	
	con=Conectar();
	
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO eventos (ID_Sensor,Estado) VALUES (?,?)");
		
			pst.setInt(1,ID_Sensor);
			pst.setBoolean(2,Estado);
			
			pst.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Desconectar();
	

}

public void Update_Status_Sensor(int ID_Sensor,boolean Estado){
	
	con=Conectar();
	
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE status_sensor SET Estado=(?) WHERE ID_Sensor="+ID_Sensor);
			
			pst.setBoolean(1,Estado);
			
			pst.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Desconectar();
	

}

public int Consultar_ID_Sensor(String IP)
{
	con=Conectar();
	Statement st;
	ResultSet rs=null;
	int ID_Sensor = 0 ;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT `ID_Sensor` FROM `sensores` WHERE `IP_Sensor`='"+IP+"'");
		while(rs.next()){
			ID_Sensor=  rs.getInt("ID_Sensor");				
		}
			
	
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			 System.out.println("error al consultar: "+IP);
	}
	Desconectar();
	return ID_Sensor;
	
	
}

public int Consultar_Lugares(int ID_Cartel)
{
	con=Conectar();
	Statement st;
	ResultSet rs=null;
	
	int Cant_Lugares=0;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT `ID_Sensor` FROM `sensores` WHERE `ID_Cartel`='"+ID_Cartel+"'");
		while(rs.next()){
			ID_Cartel=  rs.getInt("ID_Sensor");				
		}
			
	
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			 System.out.println("error al consultar: "+ID_Cartel);
	}
	Desconectar();
	return Cant_Lugares;
	
	
}


public void Desconectar(){
	
	try {
		con.close();
	//	System.out.println("Desconexion");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}
