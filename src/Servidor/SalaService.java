
package Servidor;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SalaService {
    private ArrayList<String> usuariosOnline = new ArrayList<String>();
    private ArrayList<ObjectOutputStream> saidasOnline = new ArrayList<ObjectOutputStream>();
    private String nome;

    public ArrayList<String> getUsuariosOnline() {
        return usuariosOnline;
    }

    public void setUsuariosOnline(ArrayList<String> usuariosOnline) {
        this.usuariosOnline = usuariosOnline;
    }

    public ArrayList<ObjectOutputStream> getSaidasOnline() {
        return saidasOnline;
    }

    public void setSaidasOnline(ArrayList<ObjectOutputStream> saidasOnline) {
        this.saidasOnline = saidasOnline;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
