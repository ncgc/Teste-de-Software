/* Interface que modela usuários da Agenda. */
package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nomeUsuario;
    private String senha;
    private ArrayList<Contato> contatos;
    
    public Usuario(){}
    
    public Usuario(String nomeUsuario, String senha, List<Contato> contatos){
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        if(contatos == null)
            this.contatos = new ArrayList<Contato>();
        else
            this.contatos = (ArrayList<Contato>) contatos;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = (ArrayList<Contato>) contatos;
    }
    
    /* Implementar a versão correta.*/
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        Usuario other = (Usuario) o;
        if(nomeUsuario == null){
            if(other.nomeUsuario != null) return false;
        }
        else if(other.nomeUsuario == null) return false;
        else if (!nomeUsuario.equals(other.nomeUsuario)) return false;
        return true;
    }
    

    public String tupla(){
        return this.getNomeUsuario().concat(";").concat(this.getSenha());
    }
}
