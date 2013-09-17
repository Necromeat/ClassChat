/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.EventListener;

/**
 *
 * @author Andrew
 */
public interface MessageArrivedListener extends EventListener{
    void messageArrived(MessageArrivedEvent evt);
    
}
