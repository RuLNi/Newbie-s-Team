import java.net.*;
import java.util.*;
import java.io.*;


public class ServidorChat{

	static ServerSocket servidor;	
	static Socket cliente;
	static int totalUsuarios = 0;	
	static final int MAX_USERS = 6;
	public static List usuariosConectados = new ArrayList();
	//public static List newbies = new ArrayList();
	
	
	public static void main(String args[]){

		try{
			servidor = new ServerSocket(8000);		//creamos un servicio de servidor puerto 8000
			System.out.println("...__Servidor en linea__...");
		}catch(IOException e){
			e.printStackTrace();	//capturamos una IOException y mostramos toda la traza del error
		}
		
		//Creamos y abrimos archivo de escritura...usando la clase bitacora
		Bitacora bit = new Bitacora();
		

		while(true){
			try{
				if(totalUsuarios==MAX_USERS+1)
					break;
				
				cliente = servidor.accept();	//genera el socket y modo listen
				System.out.println("Un cliente se ha conectado.");
				ClienteHilo ch = new ClienteHilo(cliente, usuariosConectados, bit);	//crea un nuevo hilo con cliente, usa arraylist y la bitacora
				
				usuariosConectados.add(ch);		//agregamos el cliente a la lista
				ch.start(); 					//generamos hilo para cada usuario
				
				//newbies.add(ch.nombre);
				totalUsuarios++;

				//Para mostrar en servidor cuantos usuarios estan conectados
				System.out.println("Usuarios: " + totalUsuarios);

				
				//condicion para terminar el servicio del servidor
				if(totalUsuarios==5){
					System.out.println("Cerrando archivo....");
					bit.cerrarArchivo();
					//System.out.println(newbies);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		System.out.println("...__Se termino el servicio___...");
		//Cerramos el archivo log para que se pueda quedar guardada la informacion en el archivo.
		//bit.cerrarArchivo();
		
		
		try {
			servidor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
