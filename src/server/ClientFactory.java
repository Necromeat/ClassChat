/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Andrew
 */
public class ClientFactory {
    private String clientname;
    private String clientip;
    private int clientport;
    
    public ClientFactory(){
        
    }
    
    public ClientFactory(String clientname,String clientip,int clientport){
        this.clientname= clientname;
        this.clientip = clientip;
        this.clientport = clientport;
    }

    public String getClientname() {
        return clientname;
    }

    public String getClientip() {
        return clientip;
    }

    public int getClientport() {
        return clientport;
    }

    @Override
    public String toString() {
        return "Client{" + "clientname=" + clientname + ", clientip=" + clientip + ", clientport=" + clientport + '}';
    }
    
    
}
