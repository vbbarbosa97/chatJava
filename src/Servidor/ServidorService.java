package Servidor;


import Mensagem.Mensagem;
import Mensagem.Mensagem.Action;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorService {
    private ServerSocket servidor;
    private Socket cliente;
    private ArrayList<String> usuariosOnline = new ArrayList<String>();
    private ArrayList<ObjectOutputStream> saidasOnline = new ArrayList<ObjectOutputStream>();
    
    public ServidorService(){
        try {
            servidor = new ServerSocket(5000);
                    
            while(true){
                cliente = servidor.accept();
                
                new Thread(new LeitorCliente(cliente)).start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class LeitorCliente implements Runnable{

        private ObjectOutputStream saida;
        private ObjectInputStream entrada;
        
        public LeitorCliente(Socket cliente){
            try {
                this.saida = new ObjectOutputStream(cliente.getOutputStream());
                this.entrada = new ObjectInputStream(cliente.getInputStream());
                
            } catch (IOException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run() {
            Mensagem mensagem = null;
            try {   
                
                while( (mensagem = (Mensagem) entrada.readObject()) != null ){
                    
                    Action action = mensagem.getAction();
                    
                    if(action.equals(Action.CONEXAO)){
                        conexao(mensagem,saida);
                    } else if(action.equals(Action.DESCONEXAO)){
                        
                    } else if(action.equals(Action.MENSAGEM)){
                        
                    }
                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void conexao(Mensagem mensagem, ObjectOutputStream saida){
            try {
                saida.writeObject(mensagem);
                
            } catch (IOException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void desconexao(Mensagem mensagem){
            
        }
        
        public void mensagem(Mensagem mensagem){
            
        }
        
    }
}
