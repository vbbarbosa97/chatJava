package Cliente;

import Mensagem.Mensagem;
import Mensagem.Mensagem.Action;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;


public class LeitorCliente implements Runnable{
    private ClienteFrame telaChat;
    private ClienteSala telaSala;
    private ObjectInputStream entrada;
    private Socket cliente;
    private String nomeCliente;
    static String chaveencriptacao = "0123456789abcdef";

    public LeitorCliente(Socket cliente,ClienteFrame telaChat, String nomeCliente, ClienteSala telaSala ){
        
        try {
            this.telaSala = telaSala;
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

                if(action.equals(Action.CONEXAO_SERVER)){
                    conexaoServer(mensagem);
                        
                } else if(action.equals(Action.CONEXAO_SALA)){
                    conexaoSala(mensagem);

                } else if(action.equals(Action.DESCONEXAO_SERVER)){
                    this.cliente.close();
                    return;

                } else if(action.equals(Action.DESCONEXAO_SALA)){
                    
                    if(mensagem.getNome().equals(this.nomeCliente)){
                        this.cliente.close();
                        System.exit(0);
                        return;
                    }
                    else{
                        desconexaoSala(mensagem);
                    }   
                    
                } else if(action.equals(Action.MENSAGEM)){
                    mensagem(mensagem);
                }
                
            }


        } catch (IOException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    private void conexaoServer(Mensagem mensagem){
        System.out.println("\nCliente diz: ouvi uma conexão no server");
        System.out.println(mensagem.getSalas()+"\n");
        DefaultListModel listSalas = new DefaultListModel();
        this.telaSala.getNomeDaSalas().addAll(mensagem.getSalas());
        
        for (String sala : mensagem.getSalas()) {
           
            listSalas.addElement(sala);
        }
        
        this.telaSala.getListaSalas().setModel(listSalas);
    }
    
    
    private void conexaoSala(Mensagem mensagem){
        System.out.println("\nCliente diz: ouvi uma conexão na sala");
        if(!mensagem.getNome().equals(this.nomeCliente)){
            this.telaChat.getMensagemRecebida().append(" " + mensagem.getNome() + mensagem.getTexto(chaveencriptacao) + "\n");
        }
        atualizaLista(mensagem);
    }

    private void desconexaoSala(Mensagem mensagem){   
        this.telaChat.getMensagemRecebida().append(" " + mensagem.getNome() + mensagem.getTexto(chaveencriptacao) + "\n");
        atualizaLista(mensagem);
    }

    private void mensagem(Mensagem mensagem){
        this.telaChat.getMensagemRecebida().append(" " + mensagem.getNome() + " diz: " + mensagem.getTexto(chaveencriptacao) + "\n");
        atualizaLista(mensagem);
    }
    
    private void atualizaLista(Mensagem mensagem){
        DefaultListModel listModel = new DefaultListModel();
         
        for (String usuario : mensagem.getUsuariosOnline()) {
           
            if(!usuario.equals(this.nomeCliente)){
               listModel.addElement(usuario);
           } 
        }
        
        this.telaChat.getListaUsuarios().setModel(listModel);
    }
}
    
    
