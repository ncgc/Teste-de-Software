package model;

import java.util.List;

public interface UsuarioDAO {
    public Usuario inserir(Usuario u);
    public Usuario buscarPorNomeUsuario(String nomeUsuario);
    public boolean removerUsuario(Usuario u);
    public Usuario atualizarUsuario(Usuario uAnt, 
                                    Usuario uAtual);
    public List<Usuario> listarTodosUsuarios();    

}
