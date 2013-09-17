/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
public class EccoServer{

   
    List<Client> clientlist= new ArrayList();
    
    public static void main(String[] args) {           
         EccoServer ec = new EccoServer();
          try {
            ServerSocket serverSocket = new ServerSocket(8888);
            /*Important: Blocking call, get around these blocking calls by using threads*/
            System.out.println("Waiting for client");
            Socket socket =serverSocket.accept();
            System.out.println("--------------A Clint connected!-----------------");
            new ClientHandler().handleClient(socket);
                }catch (IOException ex) {
            Logger.getLogger(EccoServer.class.getName()).log(Level.SEVERE, null, ex);
        
     
            }       
         
        
  
    }
    
     public void addClientToList(String clientname,String clientip,int clientport){
       clientlist.add(new Client(clientname,clientip,clientport));
    }
    
     public String getClientName(int index){
         return clientlist.get(index).getClientname();
     }
     
    
    public String getClientIP(int index){
        return clientlist.get(index).getClientip();
    }
     public int getClientPort(int index){
        return clientlist.get(index).getClientport();
    }
 
    
}
