
package Servidor;

import java.awt.Color;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ServidorFrame extends javax.swing.JFrame {

    private int portaServidor;
    private boolean portaSetada = false;

    public int getPortaServidor() {
        return portaServidor;
    }

    public boolean isPortaSetada() {
        return portaSetada;
    }

    public JButton getStatus() {
        return status;
    }
    
    
    public ServidorFrame() {
        initComponents();
        this.btnLigar.setBackground(Color.green);
        this.btnDesligar.setBackground(Color.red);
        this.status.setBackground(Color.red);
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        portServer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnDesligar = new javax.swing.JButton();
        btnLigar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        status = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setText("Servidor do DevChat");

        portServer.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Porta do Servidor:");

        btnDesligar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDesligar.setText("DESLIGAR");
        btnDesligar.setEnabled(false);
        btnDesligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesligarActionPerformed(evt);
            }
        });

        btnLigar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLigar.setText("LIGAR");
        btnLigar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLigarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("STATUS DO SERVIDOR:");

        status.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        status.setText("OFFLINE");
        status.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(243, 243, 243)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(202, 202, 202)
                            .addComponent(jLabel2)
                            .addGap(54, 54, 54)
                            .addComponent(portServer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(btnDesligar)
                .addGap(61, 61, 61))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(703, 703, 703)
                    .addComponent(btnLigar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(62, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(168, 168, 168)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(77, 77, 77)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 79, Short.MAX_VALUE)
                        .addComponent(btnDesligar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(399, Short.MAX_VALUE)
                    .addComponent(btnLigar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(79, 79, 79)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //BOTÃO START
    private void btnLigarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLigarActionPerformed
        String porta = this.portServer.getText();
        
        if(porta.isEmpty()){
            JOptionPane.showMessageDialog(this, "Operação Invalida!\nDigite um porta para o servidor!");
            
        } 
        else {
            if(!porta.matches("[0-9]+")){
                JOptionPane.showMessageDialog(this, "Operação Invalida!\nDigite um porta com apenas números para o servidor!");
            }
            else{
                this.btnLigar.setEnabled(false);
                this.btnDesligar.setEnabled(true);
                this.portaServidor = Integer.parseInt(this.portServer.getText());
                this.status.setBackground(Color.green);
                this.status.setText("ONLINE");
                this.portaSetada = true;
            }
        }         
    }//GEN-LAST:event_btnLigarActionPerformed

    //BOTÃO STOP
    private void btnDesligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesligarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnDesligarActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDesligar;
    private javax.swing.JButton btnLigar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField portServer;
    private javax.swing.JButton status;
    // End of variables declaration//GEN-END:variables
}
