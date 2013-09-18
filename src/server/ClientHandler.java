/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Andrew
 */
public class ClientHandler extends Thread{
    EccoServer serv;
    Scanner input;
    PrintWriter output;
    Socket socket;
   private String name;
   private String message;
   public static boolean connected;
//    clientlist= new ArrayList();
    /*Need to have evt listeners for sending commands to the server. This needs a fuck ton of logic to handle all the incoming commands and what to do with them.*/
    
   public void handleClient(Socket socket, EccoServer server){
        try {
            this.socket= socket;
            this.serv=server;
              connected=true;
              input = new Scanner(socket.getInputStream());
               output = new PrintWriter(socket.getOutputStream(),true);
               
                   start();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @Override
    public void run() {
        //important: Blocking call
       String message1 = input.nextLine();
    while(connected){
        
            //replace these souts with logger writes. 
            System.out.println("Got a message from the client");
            messageIn(message1);
            message1 = input.nextLine();
        }
        
        
        input.close();
        output.close();
    }
    
    public void sendMessage(String message){
        output.println(message);
    }
    
  
        
    public void messageIn(String message){
        String [] messageSplit;
        String temp = "None";
        String tempMessage="None";
        String tempMessage2="None";
       
        System.out.println("Message before split"+message);
            
              messageSplit = message.split("#");
              temp = messageSplit[0];
              tempMessage=messageSplit[1];
                   System.out.println("Split 0: ------>"+temp);
                   System.out.println("Split 1:  ------>"+tempMessage);
           System.out.println("Size of MergeSplit:" + messageSplit.length);
                   /*Need to split the stream here.
                    else{
                   while(tempMessage.contains("#")){
                    String [] messageSplit2;
                        messageSplit2 =tempMessage.split("#");
                        String nameOfReciver = messageSplit2[0];
                        String messageToReciver = messageSplit2[1];
                        serv.sendMessageTo(nameOfReciver, messageToReciver, name);
                   }
                    * 
                    */
               
        switch(temp)
        {
            case "CONNECT":
                this.name=tempMessage;
                serv.addToList(name, this);
                sendMessage(name);
                break;
            case "SEND":
                if(tempMessage.contains("*")){
                    String temo=tempMessage.substring(1,tempMessage.length());                     
                serv.messageAll(temo,name);
                }
                break;
            case "MESSAGE":
                serv.sendMessageTo(tempMessage, tempMessage2, name);
                break;
            case "CLOSE":
                serv.removeFromList(name, this);
                sendMessage("closed");
                connected=false;
                break;
                
            }
        }
    }
   
   
    
    
    

