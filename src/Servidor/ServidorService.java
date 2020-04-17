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
    private int portaServidor;
    
    public ServidorService(int portaServidor){
        try {
            this.portaServidor = portaServidor;
            servidor = new ServerSocket(this.portaServidor);
                    
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
                        desconexao(mensagem,saida);
                        return;
                    } else if(action.equals(Action.MENSAGEM)){
                        mensagem(mensagem);
                    }
                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public void conexao(Mensagem mensagem, ObjectOutputStream saida){
        this.usuariosOnline.add(mensagem.getNome());
        this.saidasOnline.add(saida);
        mensagem.getUsuariosOnline().addAll(usuariosOnline);
        System.out.println("\n");
        System.out.println("Recebi uma conexão de: "+saida);
        System.out.println("Saidas/Usuarios onlines após inclusão: ");
        System.out.println(saidasOnline+"\n"+usuariosOnline+"\n");
        
        try {
            
            for(ObjectOutputStream saidaOnline : saidasOnline){
                                    
                saidaOnline.writeObject(mensagem);                
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconexao(Mensagem mensagem, ObjectOutputStream saida ){
        System.out.println("\n");
        System.out.println("Recebi uma desconexão de: "+saida+"\n");
        this.usuariosOnline.remove(mensagem.getNome());
        mensagem.getUsuariosOnline().addAll(usuariosOnline);
        try {
            for(ObjectOutputStream saidaOnline : saidasOnline){
                
                System.out.println("Enviando para "+saidaOnline);
                saidaOnline.writeObject(mensagem);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.saidasOnline.remove(saida);
        System.out.println("Saidas/Usuarios onlines após remoção: ");
        System.out.println(saidasOnline+"\n"+usuariosOnline);
    }

    public void mensagem(Mensagem mensagem){
        System.out.println("\n");
        System.out.println(mensagem.getNome() + " digitou algo, enviando...");
        mensagem.getUsuariosOnline().addAll(usuariosOnline);
        try {
            for(ObjectOutputStream saidaOnline : saidasOnline){
               
                saidaOnline.writeObject(mensagem);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Mensagem enviada para todos...");
    }
    
}
