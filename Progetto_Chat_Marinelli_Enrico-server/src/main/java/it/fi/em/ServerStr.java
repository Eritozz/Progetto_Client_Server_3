package it.fi.em;
import java.io.*;
import java.net.*;
import java.util.*;

//classe utilizzata per il passaggio di una stringa da minuscolo a maiuscolo, in questo momento inutilizzata, utilizzata invece la classe multiserver.

public class ServerStr {
    ServerSocket server = null; //socket esclusivamente del server 
    Socket client = null; 
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;


    public ServerStr(){
        try{
            server = new ServerSocket(6789);
            System.out.println("Server inizio esecuzione");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



   public Socket attendi(){
       try
       {
           System.out.println("SERVER partito in esecuzione....");

           

           client = server.accept(); // il server si mette in attesa di un client

          // server.close(); effettuto la chiusura del server in caso io voglia evitare l'arrivo di altri client, a differenza di questo caso dove lavoriamo con più client

          //oggetti per scrittura e lettura
           inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
           outVersoClient = new DataOutputStream((client.getOutputStream()));
       }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server: ");
            System.exit(1);



       }
       return client;
   }

   public void comunica(){
       try{
           //aspetto che arrivi la stringa mandata dal client
           System.out.println("Benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
           stringaRicevuta = inDalClient.readLine();
           System.out.println("Ricevuta la stringa dal cliente: " + stringaRicevuta);

           //effettuo la sua modifica
           stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("Invio la stringa modificata a client...");
            outVersoClient.writeBytes(stringaModificata + '\n');

            System.out.println("SERVER: fine elaborazione.");
            client.close();
       }catch(Exception e){
           
       }
   }
    
}
