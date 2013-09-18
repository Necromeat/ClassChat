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

/**
 *
 * @author Andrew
 */
public class Client extends Thread{
    
    Scanner input;
    PrintWriter output,outputName;
    List<MessageArrivedListener> listeners = new ArrayList();
    
    
    public void connect(String ip, int port) throws UnknownHostException, IOException{
        Socket socket = new Socket(ip,port);
        
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(),true);
        start();
        System.out.println("Started Listning");
    }
    
//    public void disconnect(){
//        output.println("CLOSE");
//        //We will come back to this.
//        
//    }
    public void sendMessage(String message){
        output.println(message);
    }
    
    public void run(){
      String message = input.nextLine();
      while(!message.equals("closed")){
          System.out.println("Recived a message: ");
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
    
    
  
}
