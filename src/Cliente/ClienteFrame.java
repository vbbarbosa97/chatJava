
package Cliente;

import Mensagem.Mensagem;
import Mensagem.Mensagem.Action;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ClienteFrame extends javax.swing.JFrame {
    private String nomeUsuario;
    private String nomeSala;
    private ClienteService service;
    private Mensagem mensagem;
    static String chaveencriptacao = "0123456789abcdef";
    
    public ClienteFrame(String nome) {
        this.nomeUsuario = nome;
        initComponents();
        this.mensagemEnviada.setText(null);
        this.btnEnviar.setBackground(Color.green);
        this.btnSair.setBackground(Color.red);
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }
    
    public void setService(ClienteService service) {
        this.service = service;
    }

    
    public JList<String> getListaUsuarios() {
        return listaUsuarios;
    }

    public JTextArea getMensagemRecebida() {
        return mensagemRecebida;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mensagemRecebida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        mensagemEnviada = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomeDaSala = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mensagemRecebida.setEditable(false);
        mensagemRecebida.setColumns(20);
        mensagemRecebida.setRows(5);
        jScrollPane1.setViewportView(mensagemRecebida);

        jScrollPane2.setViewportView(listaUsuarios);

        mensagemEnviada.setColumns(20);
        mensagemEnviada.setRows(5);
        jScrollPane3.setViewportView(mensagemEnviada);

        btnEnviar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnEnviar.setText("ENVIAR");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setText("USUÁRIOS ONLINE");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("NOME DA SALA: ");

        nomeDaSala.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        nomeDaSala.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nomeDaSala, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeDaSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //BOTÃO ENVIAR
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        String textoDigitado = this.mensagemEnviada.getText();
        
        if(!textoDigitado.isEmpty()){
            this.mensagem = new Mensagem();
            this.mensagem.setAction(Action.MENSAGEM);
            this.mensagem.setNomeSala(this.nomeSala);
            this.mensagem.setNome(nomeUsuario);
            this.mensagem.setTexto(this.mensagemEnviada.getText(),chaveencriptacao);
            this.service.send(this.mensagem);
            System.out.println("Solcitação enviada!");
            this.mensagemEnviada.setText(null);
        }else{
            JOptionPane.showMessageDialog(this, "Operação Invalida!\nVocê esta tentando enviar uma mensagem sem texto!");
        }
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    public JButton getNomeDaSala() {
        return nomeDaSala;
    }
//BOTÃO SAIR
    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.out.println("Solicitei saida do chat");
        this.mensagem = new Mensagem();
        this.mensagem.setAction(Action.DESCONEXAO_SALA);
        this.mensagem.setNomeSala(this.nomeSala);
        this.mensagem.setNome(this.nomeUsuario);
        this.mensagem.setTexto(" saiu da sala...",chaveencriptacao);
        this.service.send(this.mensagem);
        System.out.println("Solcitação enviada!");
    }//GEN-LAST:event_btnSairActionPerformed

    //BOTÃO FECHAR DO FRAME
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Solicitei saida do chat");
        this.mensagem = new Mensagem();
        this.mensagem.setAction(Action.DESCONEXAO_SALA);
        this.mensagem.setNomeSala(this.nomeSala);
        this.mensagem.setNome(nomeUsuario);
        this.mensagem.setTexto(" saiu da sala...",chaveencriptacao);
        this.service.send(this.mensagem);
        System.out.println("Solcitação enviada!");
    }//GEN-LAST:event_formWindowClosing

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaUsuarios;
    private javax.swing.JTextArea mensagemEnviada;
    private javax.swing.JTextArea mensagemRecebida;
    private javax.swing.JButton nomeDaSala;
    // End of variables declaration//GEN-END:variables
}
