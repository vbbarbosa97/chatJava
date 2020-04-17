
package Servidor;

import java.awt.Color;


public class Servidor {
   public static void main(String[] args){
       ServidorFrame telaServer = new ServidorFrame();
       telaServer.setVisible(true);
       while(telaServer.isPortaSetada() == false){
           telaServer.getStatus().setBackground(Color.orange);
           telaServer.getStatus().setText("Aguardando porta...");
       }
       
        telaServer.getStatus().setBackground(Color.green);
        telaServer.getStatus().setText("ONLINE");
       new ServidorService(telaServer.getPortaServidor());
   } 
}
