package model;

import java.util.ArrayList;
import java.util.List;

public class ServicoContatoImpl implements ServicoContato, Listener{

    private ContatoDAOImpl dao;

    /* O construtor deve receber
    como parâmetro o nome do arquivo de
    dados de contatos (referente ao usuário
    específico carregado)*/
    public ServicoContatoImpl(String nome_arq_dados_c){
       this.dao = new ContatoDAOImpl(nome_arq_dados_c);
    }

    public ServicoContatoImpl(){ }

    @Override
    /* Colocar aqui a lógica do tipo
           só inserir se o contato não existir, 
           ou seja, utilizar o dao para buscar e depois
           para inserir, caso seja aplicável. */
    public Contato inserir(Contato c) {
        ArrayList<Contato> busca = (ArrayList<Contato>) dao.buscarPorParteNome(c.getNome());
        if(busca.isEmpty()){
            return dao.inserir(c);
        }
        for(Contato contato: busca){
            if(contato.equals(c)){
                searchMensagem(Mensagens.CONTATO_EXIST);
                return null;
            }
        }
        return dao.inserir(c);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contato inserir(String nome, String tel, String email, String end) {
        Validacao v = new Validacao();
        if(v.isPadraoNome(nome)){
            if(v.isPadraoTelefone(tel)){
                if(v.isPadraoEmail(email)){
                    if(v.isPadraoEndereco(end)){
                        Contato c = new Contato(nome, tel, email, end);
                        return this.inserir(c);
                    }
                    searchMensagem(Mensagens.END);
                    return null;
                }
                searchMensagem(Mensagens.EMAIL);
                return null;
            }
            searchMensagem(Mensagens.TEL);
            return null;
        }
        searchMensagem(Mensagens.NOME);
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Contato> buscarPorParteNome(String parteNome) {
        if(parteNome.isBlank()){
            searchMensagem(Mensagens.EMPTY_FIELD);
            return null;
        }
        ArrayList<Contato> busca = (ArrayList<Contato>) dao.buscarPorParteNome(parteNome);
        if(busca.isEmpty()){
            searchMensagem(Mensagens.CONTATO_NOT_FOUND);
            return null;
        }
        return busca;
    }

    @Override
    public boolean removerContato(Contato c) {
        if(c != null){
            ArrayList<Contato> busca = (ArrayList<Contato>) dao.buscarPorParteNome(c.getNome());
            if(busca != null){
                for(Contato contato: busca){
                    if(contato.equals(c))
                        return dao.removerContato(c);
                }
            }
            else{
                searchMensagem(Mensagens.CONTATO_NOT_FOUND);
                return false;
            }
        }
        return false;
    }

    @Override
    public Contato atualizarContato(Contato cAnt, Contato cAtual) {
        if(cAnt != null && cAtual != null){
            Validacao v = new Validacao();
            if(v.isPadraoNome(cAtual.getNome()) && v.isPadraoTelefone(cAtual.getTelefone()) && v.isPadraoEmail(cAtual.getEmail()) && v.isPadraoEndereco(cAtual.getEndereco())){
                ArrayList<Contato> buscaAnt = (ArrayList<Contato>) dao.buscarPorParteNome(cAnt.getNome());
                for(Contato contato: buscaAnt){
                    if(contato.equals(cAnt)){
                        ArrayList<Contato> buscaAtual = (ArrayList<Contato>) dao.buscarPorParteNome(cAtual.getNome());
                        for(Contato contato2: buscaAtual){
                            if(contato2.equals(cAtual)){
                                searchMensagem(Mensagens.CONTATO_EXIST);
                                return null;
                            }
                        }
                        return  dao.atualizarContato(cAnt, cAtual);
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public List<Contato> listarTodosContatos() {
       return dao.listarTodosContatos();     
    }

    public void setDAO(ContatoDAOImpl dao){
        this.dao = dao;
    }

    @Override
    public void searchMensagem(Mensagens msg) {

    }
    
}


