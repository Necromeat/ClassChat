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
   
   
    public static void main(String[] args){
       
          ClientController client = new ClientController();
          ClientController client1 = new ClientController();
             
          client.connectToServer("Bob","localhost", 8888);          
          client1.connectToServer("Bill","localhost", 8888);
          client.sendMessageTo("Bill","BOB Say hi");
          client1.sendMessageAll("BOB2 Says hi");
//          client.disconnect();
//          client1.disconnect();
               
        
    }

  
   
    
}
