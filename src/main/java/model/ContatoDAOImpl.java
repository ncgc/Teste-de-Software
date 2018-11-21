package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ContatoDAOImpl implements ContatoDAO, Listener {
    public ArrayList<Contato> contatos;
    private String nomeArq;

    // criar o construtor da classe
    public ContatoDAOImpl(String nome_arq_dados_c){
        this.contatos = new ArrayList<Contato>();
        this.nomeArq = nome_arq_dados_c;
        this.inicializaContatos();
    }

    private void inicializaContatos() {
        Scanner scan;
        try {
            File arquivo = new File(nomeArq);
            scan = new Scanner(arquivo);
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                String[] partes = linha.split(";", 4);
                Contato umContato = new Contato(partes[0], partes[1], partes[2], partes[3]);
                contatos.add(umContato);
            }
            Collections.sort(contatos);
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            searchMensagem(Mensagens.ARQ_NOT_FOUND);
        }
    }
    
    public boolean salvarContato(){
        Collections.sort(contatos);
        try{
            FileWriter fw = new FileWriter(nomeArq, false);
            PrintWriter pw = new PrintWriter(fw);
            for(Contato c: contatos){
              pw.println(c.tupla());
            } 
            pw.close();
            fw.close();
          } 
          catch (IOException ex) {
            searchMensagem(Mensagens.ARQ_FAIL);
            return false;
          }
          return true;
    }

    @Override
    public Contato inserir(Contato c) {
        contatos.add(c);
        if(salvarContato()) 
            return c;
        contatos.remove(c);
        return null;
    }

    @Override
    public List<Contato> buscarPorParteNome(String parteNome) {
        List<Contato> busca = new ArrayList<Contato>();
        for(Contato c: contatos){
            if(c.getNome().toLowerCase().contains(parteNome.toLowerCase()))
                busca.add(c);
        }
        return busca;
    }

    @Override
    public boolean removerContato(Contato c) {
        contatos.remove(c);
        if(salvarContato())
            return true;
        contatos.add(c);
        return false;
    }

    @Override
    public Contato atualizarContato(Contato cAnt, Contato cAtual) {
        contatos.remove(cAnt);
        contatos.add(cAtual);
        if(salvarContato()) 
            return cAtual;
        contatos.remove(cAtual);
        contatos.add(cAnt);
        return null;
    }

    @Override
    public List<Contato> listarTodosContatos() {
		return this.contatos;
    }

    @Override
    public void searchMensagem(Mensagens msg) {

    }

    public ArrayList<Contato> getContatos(){
        return this.contatos;
    }

    public void setnomeArq(String nome){
        this.nomeArq = nome;
    }
    
}