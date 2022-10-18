package it.fi.em;

import java.net.*;
import java.io.*;


public class ServerThread extends Thread{

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public ServerThread(Socket socket){
        this.client = socket;
    }

    public void comunica() throws Exception{
        //oggetti per lettura e scrittura
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        for(;;){
            stringaRicevuta = inDalClient.readLine();
            
            if(stringaRicevuta == null || stringaRicevuta.equals("FINE")){
                outVersoClient.writeBytes(stringaRicevuta +" (=> server in chiusura...)"+ '\n');
                System.out.println("Echo sul server in chiusura: " + stringaRicevuta);
                break;
            }else{
                outVersoClient.writeBytes(stringaRicevuta + " ricevuta e ritrasmessa" + '\n');
                System.out.println("Echo sul server: " + stringaRicevuta);
            }
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("Chiusura socket" + client);
        client.close();
    }

    public void run(){
        try{
            comunica();

        }catch(Exception e){
            e.printStackTrace(System.out);
        }

    }
}
