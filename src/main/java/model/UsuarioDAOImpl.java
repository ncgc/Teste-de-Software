package model;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioDAOImpl implements UsuarioDAO {
    private ArrayList<Usuario> usuarios;
    private String nomeArquivo;

    public UsuarioDAOImpl(String nome_arq_u){
       this.usuarios = new ArrayList<>();
       this.nomeArquivo = nome_arq_u;
       this.inicializaUsuarios(nome_arq_u);
    }

    public void inicializaUsuarios(String nomeArquivo){
        File arquivo = new File(nomeArquivo);
        Scanner scan = null;
        try {
            scan = new Scanner(arquivo);
            while(scan.hasNextLine()){
                String linha = scan.nextLine();
                String[] partes = linha.split(";", 2);
                Usuario u = new Usuario();
                u.setNomeUsuario(partes[0]);
                u.setSenha(partes[1]);
                usuarios.add(u);  
            }
            scan.close();
        } catch (IOException e) {
            try {
                arquivo.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public Usuario inserir (Usuario u){
        usuarios.add(u);
        boolean status = salvarUsuarios();
        if (status) return u;
        return null;
    }

    @Override
    public Usuario buscarPorNomeUsuario(String nomeUsuario) {
        if(usuarios.isEmpty()) return null;
        for(Usuario u: usuarios){
            if(u.getNomeUsuario().equals(nomeUsuario)){
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean removerUsuario(Usuario u){
        usuarios.remove(u);
        boolean status = salvarUsuarios();
        return status;
    }

    private boolean salvarUsuarios(){
        try{
          FileWriter fw = new FileWriter("./src/main/resource/users.txt", true);
          PrintWriter pw = new PrintWriter(fw);
          for(Usuario u: usuarios){
            pw.println(u.tupla());
          } 
          pw.close();
          fw.close();
        } 
        catch (IOException ex) {return false;}
        return true;
    }

    @Override
    public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
        for(Usuario u: usuarios){
            if(u.equals(uAnt)) {
                usuarios.remove(uAnt);
                usuarios.add(uAtual);
                boolean status = salvarUsuarios();
                if (status) return uAtual;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodosUsuarios() { 
        return usuarios;
    }
    
    public void setUsuarios(List<Usuario> usuarios){
        this.usuarios = (ArrayList<Usuario>) usuarios;
    }

    public List<Usuario> getUsuarios(){
        return this.usuarios;
    }
}
