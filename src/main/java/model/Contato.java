package model;

public class Contato implements Comparable<Contato>{
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    
    public Contato(){}
    
    public Contato(String nome, String telefone, String endereco, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        Contato other = (Contato) o;
        if(nome == null || telefone == null || email == null || endereco == null) return false;
        else if(other.nome == null || other.telefone == null || other.email == null || other.endereco == null) return false;
        else if (!nome.equals(other.nome)|| !telefone.equals(other.telefone)|| !email.equals(other.email) || !endereco.equals(other.endereco)) return false;
        return true;
    }

    @Override
    public int compareTo(Contato c) {
        return this.getNome().compareToIgnoreCase(c.getNome());
    }

    public String tupla(){
        return this.getNome() + ";" + this.getTelefone() + ";" + this.getEmail() + ";" + this.getEndereco();
    }

}
