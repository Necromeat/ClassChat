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
 * @author Andrew v1.
 */
public class EccoClient extends Thread{
   
    Scanner input;
    PrintWriter output;
    List<MessageArrivedListener> listeners = new ArrayList();
    public void connect(String ip, int port) throws UnknownHostException, IOException{
        Socket socket = new Socket(ip,port);
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(),true);
        
        start();
        System.out.println("Started Listning");
    }
    
    public void disconnect(){
        output.println("close");
        //We will come back to this.
        
    }
    public void sendMessage(String message){
        output.println(message);
    }
    
    public void run(){
      String message = input.nextLine();
      while(!message.equals("closed")){
          System.out.println("Recived a message: " + message);
          notifyObservers(message);
          message = input.nextLine();
      }
      input.close();
      output.close();
      
    }
    
   private void notifyObservers(String message){
       MessageArrivedEvent evt = new MessageArrivedEvent(this,message);
       for(MessageArrivedListener listener:listeners){
           listener.messageArrived(evt);
       }
   }
    public void addMessageArrivedListener(MessageArrivedListener mal){
        listeners.add(mal);
    }
    
    public void removeMessageArrivedListener(MessageArrivedListener mal){
        listeners.remove(mal);
    }
    
    public static void main(String[] args){
        try {
          EccoClient client = new EccoClient();
             
          client.connect("localhost", 8888);
          client.addMessageArrivedListener(new MessageArrivedListener() {

              @Override
              public void messageArrived(MessageArrivedEvent evt) {
                  System.out.println(evt.getMessage());
              }
          });
         
               
            
       
       
          client.disconnect();
          
        } catch (UnknownHostException ex) {
            Logger.getLogger(EccoClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EccoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public void messageArrived(MessageArrivedEvent evt) {
        System.out.println(evt.getMessage());
    }
    
}
