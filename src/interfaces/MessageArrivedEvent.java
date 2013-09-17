/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.EventObject;

/**
 *
 * @author Andrew
 */
public class MessageArrivedEvent extends EventObject {
    private String message;
    
    public MessageArrivedEvent(Object source){
        super(source);
    }

    public String getMessage() {
        return message;
    }
    
     public MessageArrivedEvent(Object source, String message){
                super(source);
                this.message= message;
    }
    
}
