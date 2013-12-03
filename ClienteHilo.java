import java.net.*;
import java.util.*;
import java.io.*;


public class ClienteHilo extends Thread{

	DataInputStream entrada;
	PrintStream salida;	
	Socket clienteSocket;
	String nombre;
	String pass;
	String comentario;
	//ClienteHilo t[];
	List usuariosConectados;
	List newbies;
	static int j=0;
	Bitacora bit;
	Boolean intento;

	
	
	//metodo para crear un nuevo cliente
	public ClienteHilo(Socket cliente, List usuariosConectados, Bitacora bit){
		this.clienteSocket = cliente;
		//this.t = t;
		this.usuariosConectados = usuariosConectados;
		this.bit = bit;
	}	

	

	public void run(){
			
		try{
			entrada = new DataInputStream(clienteSocket.getInputStream());
			salida 	= new PrintStream(clienteSocket.getOutputStream());
			
		do{	
			salida.println("Hola :)");
			salida.println("Teclea tu nick: ");
			nombre = entrada.readLine();
			bit.escribe("Hola :)");
			bit.escribe("Teclea tu nombre: ");
			salida.println("Ingresa tu password   ");
			pass = entrada.readLine();
			
			
			if(Usuarios.validaCredenciales(nombre, pass) == true)		//casting para ver si da acceso o no
			{
				intento = true;
				ServidorChat.totalUsuarios++;
				System.out.println("Usuarios: " + ServidorChat.totalUsuarios);

				salida.println("Bienvenido " + nombre);
				bit.escribe("Bienvenido " + nombre);
				//Usuarios.enLinea.add(nombre);
		
				for(int i=0; i< usuariosConectados.size(); i++){
					if(usuariosConectados.get(i)!=this){				
						((ClienteHilo)usuariosConectados.get(i)).salida.println("Llego el usuario" + nombre);
						bit.escribe("Llego el usuario" + nombre);
					}
				}
	
				while(true){
					
					comentario = entrada.readLine();
					System.out.println("COMENTARIO: [" + comentario + "]");
					if(comentario.startsWith("/salir"))				
						break;
					
					
					if(comentario.startsWith("/showUsers")){
						salida.println("Los usuarios conectados son:");
						salida.println(Usuarios.enLinea);
					}
					
					

					for(int i=0; i< usuariosConectados.size(); i++){
						if(usuariosConectados.get(i)!=this){
							((ClienteHilo)usuariosConectados.get(i)).salida.println(nombre + " dijo: " + comentario);
							bit.escribe(nombre + " dijo: " + comentario);
						}
					}
		
				}
			
				for(int i=0; i< usuariosConectados.size(); i++){
					if(usuariosConectados.get(i)!=this){
						((ClienteHilo)usuariosConectados.get(i)).salida.println(nombre + " se fue.. :(");
						bit.escribe(nombre + " se fue.. :(");
					}
				}
				
				/*
				for(int i=0; i< usuariosConectados.size(); i++){
					if(usuariosConectados.get(i)==this){
						//ClienteHilo ch = (ClienteHilo)usuariosConectados.get(i));
					
					}
				}
				*/	
			
			
				entrada.close();
				salida.close();
				clienteSocket.close();
				ServidorChat.totalUsuarios--;
				System.out.println("Cerrando socket para este hilo: " + nombre);
				//Usuarios.enLinea.remove(nombre);
			}
			else{
				ServidorChat.totalUsuarios--;
				//System.out.println("Usuarios: " + ServidorChat.totalUsuarios);
				salida.println("Password o nick incorrecto intenta nuevamente \n\n\n");
				intento = false;
			}
		}while(intento == false);

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

