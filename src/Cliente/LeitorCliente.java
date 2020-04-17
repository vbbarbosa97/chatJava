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
    private Socket cliente;
    private String nomeCliente;

    public LeitorCliente(Socket cliente,ClienteFrame telaChat, String nomeCliente ){
        
        try {
            this.nomeCliente = nomeCliente;
            this.cliente = cliente;
            this.entrada = new ObjectInputStream(this.cliente.getInputStream());
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
                    conexao(mensagem);
                } else if(action.equals(Mensagem.Action.DESCONEXAO)){
                    
                    if(mensagem.getNome().equals(this.nomeCliente)){
                        this.cliente.close();
                        System.exit(0);
                        return;
                    }
                    else{
                        desconexao(mensagem);
                    }
                    
                } else if(action.equals(Mensagem.Action.MENSAGEM)){
                    mensagem(mensagem);
                }
            }


        } catch (IOException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    private void conexao(Mensagem mensagem){
        this.telaChat.getMensagemRecebida().append(" " + mensagem.getNome() + mensagem.getTexto() + "\n");
    }

    private void desconexao(Mensagem mensagem){   
        this.telaChat.getMensagemRecebida().append(" " + mensagem.getNome() + mensagem.getTexto() + "\n");     
    }

    private void mensagem(Mensagem mensagem){
        this.telaChat.getMensagemRecebida().append(" " + mensagem.getNome() + " diz: " + mensagem.getTexto() + "\n");
    }
}
    
    
