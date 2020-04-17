
package Mensagem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Mensagem implements Serializable{
    private String nome;
    private String textodecriptado;
    private ArrayList<String> usuariosOnline = new ArrayList<String>();
    private Action action;
    static String IV = "AAAAAAAAAAAAAAAA";
    byte[] textoencriptado;
    
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto(String chave) {
        
        try {
            this.textodecriptado = decrypt(this.textoencriptado, chave);
            return textodecriptado;
            
        } catch (Exception ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setTexto(String texto, String chave) {
        try {
            this.textoencriptado = encrypt(texto, chave);
            
        } catch (Exception ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public ArrayList<String> getUsuariosOnline() {
        return usuariosOnline;
    }

    public void setUsuariosOnline(ArrayList<String> usuariosOnline) {
        this.usuariosOnline = usuariosOnline;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
    
    
    public enum Action{
        CONEXAO, DESCONEXAO, MENSAGEM, ATUALIZA_LISTA;
    }
    
    public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(textopuro.getBytes("UTF-8"));
    }
    
    public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(decripta.doFinal(textoencriptado),"UTF-8");
    }
}
