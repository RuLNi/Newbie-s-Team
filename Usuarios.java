import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.io.*;

public class Usuarios {

    public static List enLinea = new ArrayList();

    public static boolean validaCredenciales(String usuario, String password){  
        boolean usuarioEncontrado = false;
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));        
        File f= new File("/home/rulni/Escritorio/Chat/claves.txt");
        FileReader fr = null;
        StringTokenizer st,stp;
        Scanner entrada = null;
        String cadena,person,nipuser;
        ArrayList<String> usager = new ArrayList<String>();
        ArrayList<String> nip = new ArrayList<String>();
        int i,js;
        Scanner escaner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);

        i=1;

        try {
            entrada = new Scanner(f);
            while (entrada.hasNext()){
                cadena = entrada.nextLine();
                st = new StringTokenizer(cadena,","); 
                while(st.hasMoreTokens()){
                    //los impares seran los usuarios
                    //pares seran contras#as
                    if (i%2==1){
                        person = st.nextToken();
                        usager.add(person);
                    }

                    else{
                        nipuser = st.nextToken();
                        nip.add(nipuser);
                    }
                    i+=1;                
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            entrada.close();
        }
        Iterator<String> miiterator = usager.iterator();
        while(miiterator.hasNext()){
            String nombre = miiterator.next();
        }

        Iterator<String> minipiterator = nip.iterator();
        while(minipiterator.hasNext()){
            String nipp = minipiterator.next();
        }

        Iterator<String> mioiterator = usager.iterator();
        Iterator<String> miniterator = nip.iterator();
        while(mioiterator.hasNext()){
            String nombre = mioiterator.next();
            String nipo = miniterator.next();
            if ((usuario.equals(nombre))&&(password.equals(nipo))){
                enLinea.add(usuario);
                return true;
            }
        }
        return false;
    }
    
}
