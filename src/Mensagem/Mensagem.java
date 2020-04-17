
package Mensagem;

import java.io.Serializable;
import java.util.ArrayList;

public class Mensagem implements Serializable{
    private String nome;
    private String texto;
    private ArrayList<String> usuariosOnline = new ArrayList<String>();
    private Action action;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
}
