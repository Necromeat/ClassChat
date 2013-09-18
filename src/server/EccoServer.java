/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
public class EccoServer extends Thread{

   
      public HashMap<String, ClientHandler> clientlist= new HashMap();
      static String name;
      EccoServer server;
      /*The main needs a thread to run everything. It needs event listeners to be able to update instances. The CLient handlers need to be sent to a list. Needs methods to send commads to the client handlers*/
      
      
    public static void main(String[] args) {           
          EccoServer eco = new EccoServer();
          eco.runner();
         
         }
  
    public void runner(){
            
             try {
             
             ServerSocket serverSocket = new ServerSocket(8888);
             
            /*Important: Blocking call, get around these blocking calls by using threads*/
           while(true){
             System.out.println("Waiting for client");
            Socket socket =serverSocket.accept();
            System.out.println("Client Connected");
                  new ClientHandler().handleClient(socket,this);
              
           }
            
                }catch (IOException ex) {
            Logger.getLogger(EccoServer.class.getName()).log(Level.SEVERE, null, ex);
        
     
            }       
        }
    
        public void addToList(String Name,ClientHandler clienthandler){
             clientlist.put(Name, clienthandler);
        }
         public void removeFromList(String Name,ClientHandler clienthandler){
             clientlist.remove(Name);
        }
         
         public void messageAll(String message,String name){
             for (ClientHandler values : clientlist.values()) {
                 values.output.println(name+" says : "+message);
             }
         }
                 
         public void sendMessageTo(String keyName,String message,String nameFrom){
             if(clientlist.containsKey(keyName)){
                clientlist.get(keyName).sendMessage(message+","+nameFrom);
             }
         }
        
        
    }
    
    
     
 
   

