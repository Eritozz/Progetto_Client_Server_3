package it.fi.em;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    public void avvia(){
        try{
            ServerSocket serverSocket = new ServerSocket(6789); // lo inserisco al di fuori del "for" perché la porta si apre una sola volta
            for(;;){
                System.out.println("Server in attesa...");
                Socket socket = serverSocket.accept(); 
                System.out.println("Server socket " + socket);
                ServerThread serverThread = new ServerThread(socket); //crea un nuovo thread passandogli il socket -->
                serverThread.start(); // --> e poi lo avvia
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
    }
}
