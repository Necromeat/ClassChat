/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.MessageArrivedEvent;
import interfaces.MessageArrivedListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
public class ClientController extends Thread{
    Client client;
    
    
    public void connectToServer(String name,String ip, int port){
         try {
         client = new Client();
             
          client.connect(ip, port);
          client.sendMessage("CONNECT#"+name);
          client.addMessageArrivedListener(new MessageArrivedListener() {

              @Override
              public void messageArrived(MessageArrivedEvent evt) {
                  System.out.println(evt.getMessage());
              }
          });       
         
          
        } catch (UnknownHostException ex) {
            Logger.getLogger(EccoClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EccoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public void sendMessageAll(String message){
       client.sendMessage("SEND#"+"*"+message);
   }
   
   public void sendMessageTo(String reciver,String message){
       client.sendMessage("SEND#"+reciver+"MESSAGE#"+message);
   }
    
   public void disconnect(){
      client.sendMessage("CLOSE#"+"Connection Clossed");
   }
    
}
