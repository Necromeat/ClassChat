/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.MessageArrivedEvent;
import interfaces.MessageArrivedListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
public class ClientController {
    Client client;
    
    
    public void connectToServer(String name,String ip, int port){
         try {
         client = new Client();
             
          client.connect(name,ip, port);
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
    
    public void disconnectFromServer(){
         client.disconnect();
    }
    
   
}
