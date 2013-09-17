/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.EccoServer.clientlist;


/**
 *
 * @author Andrew
 */
public class ClientHandler extends Thread{
    EccoServer serv;
    Scanner input;
    PrintWriter output;
   
//    clientlist= new ArrayList();
    
     public void handleClient(Socket socket){
     
        try {
            input = new Scanner(socket.getInputStream());
            output= new PrintWriter(socket.getOutputStream(),true);
            serv.clientlist.add(new ClientFactory("",""+socket.getInetAddress(), socket.getPort()));
            start();
            System.out.println(""+clientlist.toString());
        } catch (IOException ex) {
            Logger.getLogger(EccoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void run() {
        //important: Blocking call
        String message = input.nextLine();
        while(!message.equals("close")){
            message = message.toUpperCase();
            //replace these souts with logger writes. 
            System.out.println("Got a message from the client");
            System.out.println(message);
            output.println(message);
             message = input.nextLine();
             
        }
        
        output.println(message);
        input.close();
        output.close();
    }
   
   
  
    
}
