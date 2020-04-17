package Cliente;

import Mensagem.Mensagem;
import Mensagem.Mensagem.Action;
import java.net.Socket;

public class ClienteInit extends javax.swing.JFrame {
    private String nome;
    private Mensagem mensagem;
    private Socket cliente;
    private ClienteService service;
    private ClienteFrame telaChat;
    
    public ClienteInit() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeUsuario = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEntrar.setText("ENTRAR");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(nomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(btnEntrar)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntrar))
                .addGap(195, 195, 195))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        nome = nomeUsuario.getText();
        this.telaChat = new ClienteFrame(nome);
        
        if(!nome.isEmpty()){
            this.mensagem = new Mensagem();
            this.mensagem.setAction(Action.CONEXAO);
            this.mensagem.setNome(nome);
            this.mensagem.setTexto(" entrou no chat...");
            
            if(this.cliente == null){
                this.service = new ClienteService();
                this.cliente = service.connect();
                
                //passando para a tela do chat
                this.telaChat.setService(service);
                
                new Thread( new LeitorCliente(this.cliente, this.telaChat,nome )).start();
            }
            
            service.send(mensagem);
            System.out.println("Solcitação enviada!");
        }
            
        
        telaChat.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEntrarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JTextField nomeUsuario;
    // End of variables declaration//GEN-END:variables
}
