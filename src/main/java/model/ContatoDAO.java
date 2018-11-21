package model;

import java.util.List;

public interface ContatoDAO {
    public Contato inserir(Contato c);
    public List<Contato> buscarPorParteNome(String parteNome);
    public boolean removerContato(Contato c);
    public Contato atualizarContato(Contato cAnt, 
                                    Contato cAtual);
    public List<Contato> listarTodosContatos(); 
}
