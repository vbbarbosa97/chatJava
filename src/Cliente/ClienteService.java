
package Cliente;

import Mensagem.Mensagem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteService {
    private Socket cliente;
    private ObjectOutputStream saida;
    
    public Socket connect(){
        try {
            this.cliente  = new Socket("localhost", 5000);
            this.saida = new ObjectOutputStream(cliente.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cliente;
    }
    
    public void send(Mensagem mensagem){
        try {
            System.out.println("\n");
            System.out.println("Cliente Service diz:");
            System.out.println("Recebi uma solicitação!");
            saida.writeObject(mensagem);
            System.out.println("Enviei essa mensagem para o server: "+mensagem.getTexto());
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
