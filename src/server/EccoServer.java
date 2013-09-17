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
public class EccoServer extends Thread{

   
   static List<ClientFactory> clientlist= new ArrayList();
      static String name;
    public static void main(String[] args) {           
              
             try {
             
             ServerSocket serverSocket = new ServerSocket(8888);
            /*Important: Blocking call, get around these blocking calls by using threads*/
           while(true){
             System.out.println("Waiting for client");
            Socket socket =serverSocket.accept();
              
            
                  System.out.println("Client Connected");
                  new ClientHandler().handleClient(socket);
              
           }
            
                }catch (IOException ex) {
            Logger.getLogger(EccoServer.class.getName()).log(Level.SEVERE, null, ex);
        
     
            }       
         
         }
  
    }
    
    
     
 
   

