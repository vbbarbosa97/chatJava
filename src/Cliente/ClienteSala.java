
package Cliente;

import Mensagem.Mensagem;
import Mensagem.Mensagem.Action;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ClienteSala extends javax.swing.JFrame {
    private String nomeUsuario;
    private String nomeSala;
    private ClienteService service;
    private Mensagem mensagem;
    static String chaveencriptacao = "0123456789abcdef";
    private ClienteFrame telaChat;
    private ArrayList<String> nomeDaSalas = new ArrayList<String>();

    public ArrayList<String> getNomeDaSalas() {
        return nomeDaSalas;
    }
    
    public ClienteSala(String nome, ClienteFrame telaChat) {
        this.nomeUsuario = nome;
        this.telaChat = telaChat;
        initComponents();
    }

    public JList<String> getListaSalas() {
        return listaSalas;
    }

    public void setService(ClienteService service) {
        this.service = service;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaSalas = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEntrarSala = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnEntrarSala = new javax.swing.JButton();
        txtCriarSala = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCriarSala = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        listaSalas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(listaSalas);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Escolha uma sala ou crie uma sala exclusiva");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("ESCOLHER UMA SALA ");

        txtEntrarSala.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("DIGITE O NOME DA SALA QUE DESEJA ENTRAR ");

        btnEntrarSala.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEntrarSala.setText("ENTRAR");
        btnEntrarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarSalaActionPerformed(evt);
            }
        });

        txtCriarSala.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("CRIAR UMA SALA EXCLUSIVA");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("NOME DA SALA: ");

        btnCriarSala.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCriarSala.setText("CRIAR SALA");
        btnCriarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarSalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEntrarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEntrarSala)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCriarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnCriarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(92, 92, 92))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(132, 132, 132))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCriarSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(btnCriarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEntrarSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntrarSala))
                .addGap(102, 102, 102))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //BOTÃO DE ENTRAR EM UMA SALA JA EXISTENTE
    private void btnEntrarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarSalaActionPerformed
        
        this.nomeSala = this.txtEntrarSala.getText();
        
        System.out.println("Nome da sala que quero entrar: "+ this.nomeSala);
        if(!this.nomeSala.isEmpty()){
            
            if(this.nomeDaSalas.isEmpty()){
                JOptionPane.showMessageDialog(this, "Operação Invalida!\nNão existe salas criadas, crie sua sala!");
            }
            else{
                  
                System.out.println("Entrar nessa sala: "+nomeSala);
                this.mensagem = new Mensagem();
                this.mensagem.setAction(Action.CONEXAO_SALA);
                this.mensagem.setNomeSala(this.nomeSala);
                this.mensagem.setNome(this.nomeUsuario);
                this.mensagem.setTexto(" entrou na sala...",chaveencriptacao);
                this.service.send(this.mensagem);

                this.telaChat.setService(this.service);
                this.telaChat.setNomeSala(this.nomeSala);
                this.telaChat.getNomeDaSala().setText(this.nomeSala);
                this.telaChat.setVisible(true);

                this.nomeSala = null;
                dispose();     
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Operação Invalida!\nDigite uma sala!");
        }
    }//GEN-LAST:event_btnEntrarSalaActionPerformed

    //BOTÃO DE CRIAR UMA SALA
    private void btnCriarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarSalaActionPerformed
        this.nomeSala = this.txtCriarSala.getText();
        
        if(!this.nomeSala.isEmpty()){
            System.out.println("Criar essa sala: "+nomeSala);
            this.mensagem = new Mensagem();
            this.mensagem.setAction(Action.CONEXAO_SALA);
            this.mensagem.setNomeSala(this.nomeSala);
            this.mensagem.setNome(this.nomeUsuario);
            this.mensagem.setTexto(" entrou na sala...",chaveencriptacao);
            this.service.send(this.mensagem);
            
            this.telaChat.setService(this.service);
            this.telaChat.getNomeDaSala().setText(this.nomeSala);
            this.telaChat.setNomeSala(this.nomeSala);
            this.telaChat.setVisible(true);
            
            this.nomeSala = null;
            this.txtCriarSala.setText(null);
            
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Operação Invalida!\nDigite uma sala!");
        }
    }//GEN-LAST:event_btnCriarSalaActionPerformed

    //BOTÃO DE FECHAR O FRAME
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        this.mensagem = new Mensagem();
        this.mensagem.setAction(Action.DESCONEXAO_SERVER);
        this.mensagem.setNome(nomeUsuario);
        this.mensagem.setTexto(" desconectou do server",chaveencriptacao);
        this.service.send(this.mensagem);
        System.out.println("Solcitação enviada!");
        
    }//GEN-LAST:event_formWindowClosing

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriarSala;
    private javax.swing.JButton btnEntrarSala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaSalas;
    private javax.swing.JTextField txtCriarSala;
    private javax.swing.JTextField txtEntrarSala;
    // End of variables declaration//GEN-END:variables
}
