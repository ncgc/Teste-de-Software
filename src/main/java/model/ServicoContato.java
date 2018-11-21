package model;

import java.util.List;

public interface ServicoContato {
    public Contato inserir(Contato c);
    public Contato inserir(String nome, String tel,
                           String email, String end);
    public List<Contato> buscarPorParteNome(String parteNome);
    public boolean removerContato(Contato c);
    public Contato atualizarContato(Contato cAnt, 
                                    Contato cAtual);
    public List<Contato> listarTodosContatos(); 
}
