import java.io.*;
import java.util.*;


public class Bitacora{
	
	FileWriter w 		= null;
	BufferedWriter bw 	= null;
	PrintWriter wr 		= null;
	
	public Bitacora(){
		try{
			
			GregorianCalendar fechaActual = new GregorianCalendar();
			int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
			int mes = fechaActual.get(Calendar.MONTH)+1;
			int ano = fechaActual.get(Calendar.YEAR);
			int hora = fechaActual.get(Calendar.HOUR);
			int minuto = fechaActual.get(Calendar.MINUTE);
			int segundo  = fechaActual.get(Calendar.SECOND);
			String dia1 = Integer.toString(dia);
			String mes1 = Integer.toString(mes);
			String ano1 = Integer.toString(ano);
			String hora1 = Integer.toString(hora);
			String minuto1 = Integer.toString(minuto);
			String segundo1 = Integer.toString(segundo);
			String hoy = dia1+"."+mes1+"."+ano1+"."+hora1+"."+minuto1+"."+segundo1+".txt";



			File f = new File("/home/rulni/Escritorio/Chat/"+hoy);	//Se define ruta y nombre del archivo
			w 	= new FileWriter(f);
			bw 	= new BufferedWriter(w);
			wr 	= new PrintWriter(bw);
			System.out.println("Archivo de escritura abierto....");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("No es posible generar el archivo de escritura");
		}
		
	}

	
	public void escribe(String mensaje){			//metodo para escritura, si usamos el primero ecribira corrido	 
				
		//wr.write(mensaje);	//escribimos en el archivo
		wr.println(mensaje); 		//escribimos en el archivo
		
		//wr.append(" - y aqui continua"); 			//para ir concatenando en el archivo sin borrar lo existente		
	}
	
	
	public void cerrarArchivo(){
		//ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedara guardado con informacion escrita
		//de no hacerlo no se escribira nada en el archivo
		try{
			wr.close();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
		 
	
	
}
