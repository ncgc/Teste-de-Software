package model;

import java.io.File;
import java.util.List;

public class ServicoUsuarioImpl implements ServicoUsuario, Listener {
    private UsuarioDAOImpl uDao;
    private String nomeArquivo;

    public ServicoUsuarioImpl(String nome_arq_dados_u) {
        if(nome_arq_dados_u == null || nome_arq_dados_u.isBlank())
            this.uDao =  new UsuarioDAOImpl("users.txt");
        this.uDao = new UsuarioDAOImpl(nome_arq_dados_u);
    }

    public ServicoUsuarioImpl() {
        this.uDao = new UsuarioDAOImpl("users.txt");
    }

    @Override
    public Usuario inserir(Usuario u) {
        Usuario userExist = uDao.buscarPorNomeUsuario(u.getNomeUsuario());
        if (userExist == null) {
            Criptografia c = new Criptografia();
            String nomeC = c.criptografar(u.getNomeUsuario());
            String senhaC = c.criptografar(u.getSenha());
            Usuario uc = new Usuario(nomeC, senhaC, u.getContatos());
            //UsuarioDAOImpl dao = new UsuarioDAOImpl(this.nomeArquivo);
            return uDao.inserir(uc);
        }
        searchMensagem(Mensagens.USUARIO_ALREADY_EXISTS);
        return null;
    }


    @Override
    public Usuario inserir(String nomeUsuario, String senha, String confirmacaoSenha, List<Contato> contatos) {
        Validacao v = new Validacao();
        if (v.isPadraoUsuario(nomeUsuario) && v.isPadraoUsuario(senha)) {
            if (senha.equals(confirmacaoSenha)) {
                return this.inserir(new Usuario(nomeUsuario, senha, contatos));
            }
            return null;
        }
        searchMensagem(Mensagens.CADASTRO_FAIL);
        return null;
    }

    @Override
    public Usuario buscarPorNomeUsuario(String nomeUsuario) {
        if (uDao.getUsuarios().isEmpty())
            return null;
        Validacao v = new Validacao();
        if (v.isPadraoUsuario(nomeUsuario)) {
            Criptografia c = new Criptografia();
            Usuario userCripto = uDao.buscarPorNomeUsuario(c.criptografar(nomeUsuario));
            if (userCripto != null) {
                String nome = c.descriptografar(userCripto.getNomeUsuario());
                String senha = c.descriptografar(userCripto.getSenha());
                List<Contato> newContatos = userCripto.getContatos();
                return new Usuario(nome, senha, newContatos);
            }
        }
        return null;
    }

    @Override
    public boolean removerUsuario(Usuario u) {
        if (u != null && buscarPorNomeUsuario(u.getNomeUsuario()) != null)
            return uDao.removerUsuario(u);
        return false;
    }

    @Override
    public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
        if (uAnt != null && uAtual != null) {
            Usuario isUAnt = buscarPorNomeUsuario(uAnt.getNomeUsuario());
            Usuario isUAtual = null;
            if (buscarPorNomeUsuario(uAtual.getNomeUsuario()) == null)
                isUAtual = uAtual;
            if (isUAnt == null || isUAtual == null)
                return null;
            Criptografia c = new Criptografia();
            isUAtual.setNomeUsuario(c.criptografar(isUAtual.getNomeUsuario()));
            isUAtual.setSenha(c.criptografar(isUAtual.getSenha()));
            return uDao.atualizarUsuario(isUAnt, isUAtual);
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return uDao.listarTodosUsuarios();
    }

    @Override
    public void searchMensagem(Mensagens msg) {

    }

    public void setDAO(UsuarioDAOImpl daoMock) {
        this.uDao = daoMock;
    }

    public void apagarArquivo() {
        // lista todos os arquivos da pasta resource
        File data = new File(nomeArquivo);
        File[] arquivosContatos = data.listFiles();
        for (File f : arquivosContatos) {
            if (f.getName().equals("users.txt"))
                break;
            int indicePonto = f.getName().indexOf(".");
            String arqC = f.getName().substring(6, indicePonto);
            Criptografia c = new Criptografia();
            if (buscarPorNomeUsuario(c.descriptografar(arqC)) == null)
                f.delete();
        }
    }

    public boolean autenticar(String nomeUsuario, String senha){
        Validacao v = new Validacao();
        if (v.isPadraoUsuario(nomeUsuario) && v.isPadraoUsuario(senha)) {
            Usuario userExist = uDao.buscarPorNomeUsuario(nomeUsuario);
            if(userExist != null){
                Criptografia c = new Criptografia();
                if(userExist.getSenha() == c.criptografar(senha))
                    return true;
            }
        }
        searchMensagem(Mensagens.LOGIN_FAIL);
        return false;
    }

    public void setNomeArquivo(Usuario u) {
        if(u != null){
            Criptografia c = new Criptografia();
            String uCripto = c.criptografar(u.getNomeUsuario());
            this.nomeArquivo = "data_u".concat(uCripto);
        }
        throw new Error("Usuário Inválido");
    }
    
    public String getNomeArquivo(Usuario u){
        if(nomeArquivo == null)
            setNomeArquivo(u);
        return this.nomeArquivo;
    }

}
