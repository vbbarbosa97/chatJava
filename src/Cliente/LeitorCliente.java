package Cliente;

import Mensagem.Mensagem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LeitorCliente implements Runnable{
    private ClienteFrame telaChat;
    private ObjectInputStream entrada;

    public LeitorCliente(Socket cliente,ClienteFrame telaChat ){
        
        try {
            this.entrada = new ObjectInputStream(cliente.getInputStream());
            this.telaChat = telaChat;
            
        } catch (IOException ex) {
            Logger.getLogger(LeitorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @Override
    public void run() {
        Mensagem mensagem = null;
        try {

            while( (mensagem = (Mensagem) entrada.readObject()) != null ){
                Mensagem.Action action = mensagem.getAction();

                if(action.equals(Mensagem.Action.CONEXAO)){

                } else if(action.equals(Mensagem.Action.DESCONEXAO)){

                } else if(action.equals(Mensagem.Action.MENSAGEM)){

                }
            }


        } catch (IOException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    private void conexao(Mensagem mensagem){

    }

    private void desconexao(Mensagem mensagem){

    }

    private void mensagem(Mensagem mensagem){

    }
}
    
    
