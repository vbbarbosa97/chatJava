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
    private int portaServidor;
    private ArrayList<SalaService> salas = new ArrayList<SalaService>();
    private ArrayList<String> listaNomeSalas = new ArrayList<String>();
    
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
                    
                    if(action.equals(Action.CONEXAO_SERVER)){
                        conexaoServer(mensagem,saida);
                        
                    } else if(action.equals(Action.CONEXAO_SALA)){
                        conexaoSala(mensagem, saida);
                        
                    } else if(action.equals(Action.DESCONEXAO_SERVER)){
                        desconexaoServer(mensagem, saida);
                        return;
                        
                    } else if(action.equals(Action.DESCONEXAO_SALA)){
                         desconexaoSala(mensagem, saida);
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
    
    //Envio para o cliente que se conectou a lista de salas criadas
    public void conexaoServer(Mensagem mensagem, ObjectOutputStream saida){
        System.out.println("\n");
        System.out.println("Recebi uma conexão no servidor");
        mensagem.getSalas().addAll(listaNomeSalas);
        System.out.println("Enviei o nome das salas");
        System.out.println(listaNomeSalas);
        try {
            
            saida.writeObject(mensagem);
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Se existir a sala que ele passou pela mensagem e adiciono o nome e a saida dele na sala
    e envio para todos naquela sala que ele entrou, atualizo a lista de onlines tambem
    Se não existir eu apenas crio a sala colocando ele
    */
    public void conexaoSala(Mensagem mensagem, ObjectOutputStream saida ){
        //Verificando se a sala passada ja existe
        System.out.println("Recebi uma conexao na sala " + mensagem.getNomeSala());
        if(this.listaNomeSalas.contains(mensagem.getNomeSala())){
            //setando esse usuario na sala que escolheu
            System.out.println("Adicionando usuario na sala");
            for(SalaService sala : salas){
                
                if(sala.getNome().equals(mensagem.getNomeSala())){
                    
                    sala.getUsuariosOnline().add(mensagem.getNome());
                    sala.getSaidasOnline().add(saida);
                    mensagem.getUsuariosOnline().addAll(sala.getUsuariosOnline());
                    System.out.println(mensagem.getUsuariosOnline());
                    //enviando para todos dentro daquela sala
                    for(ObjectOutputStream saidaOnline : sala.getSaidasOnline()){
                        try {
                            saidaOnline.writeObject(mensagem);
                            
                        } catch (IOException ex) {
                            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        else{
            System.out.println("\nSala sendo Criada");
            //Criando uma sala e setando no array de salas
            SalaService novaSala = new SalaService();
            novaSala.setNome(mensagem.getNomeSala());
            novaSala.getSaidasOnline().add(saida);
            novaSala.getUsuariosOnline().add(mensagem.getNome());
            this.salas.add(novaSala);
            this.listaNomeSalas.add(mensagem.getNomeSala());
        }
    }
    
    //Apenas quebro o while e envio a mensagem para ele mesmo para fechar o socket
    public void desconexaoServer(Mensagem mensagem, ObjectOutputStream saida){
        try {
            saida.writeObject(mensagem);
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Se a sala só tiver ele deleto a sala com tudo junto
    Se não eu mando a mensagem de saida para todos e retiro ele da sala e 
    quebro o while
    */
    public void desconexaoSala(Mensagem mensagem, ObjectOutputStream saida ){
        
        for(SalaService sala : salas){
            //encontro a sala que ele me passou na mensagem
            if(sala.getNome().equals(mensagem.getNomeSala())){
                
                if(sala.getSaidasOnline().size() == 1){
                    try {
                        saida.writeObject(mensagem);
                        salas.remove(sala);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{

                    sala.getUsuariosOnline().remove(mensagem.getNome());
                    mensagem.getUsuariosOnline().addAll(sala.getUsuariosOnline());
                    
                    //mando mensagem para todos
                    for(ObjectOutputStream saidaOnline : sala.getSaidasOnline()){
                        try {
                            saidaOnline.writeObject(mensagem);
                            
                        } catch (IOException ex) {
                            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    sala.getSaidasOnline().remove(saida);
                }
            }
        }
    }

    //Mando a mensagem pra todos da sala
    public void mensagem(Mensagem mensagem){
        
        for(SalaService sala : salas){
            
            if(sala.getNome().equals(mensagem.getNomeSala())){
                mensagem.getUsuariosOnline().addAll(sala.getUsuariosOnline());
                for(ObjectOutputStream saidaOnline : sala.getSaidasOnline()){
                    try {
                        saidaOnline.writeObject(mensagem);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
}
