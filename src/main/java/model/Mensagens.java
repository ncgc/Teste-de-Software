package model;

public enum Mensagens{
    //Usuario
    LOGIN_FAIL ("Nome e/ou senha inválidos"), 
    CADASTRO_FAIL("Usuário e/ou senha não atendem os requisitos"),
    USUARIO_ALREADY_EXISTS("Usuário já cadastrado. Tente novamente"),
    CONFSENHA("Confirmação de senha diferente da senha"), 

    //Contato
    NOME("Nome não atende aos requisitos"), 
    TEL("Telefone não atende aos requisitos"), 
    END("Endereço não atende aos requisitos"), 
    EMAIL("E-mail não atende aos requisitos"),  
    CONTATO_EXIST("Esse contato já existe"),
    CONTATO_NOT_FOUND("Nenhum contato foi encontrado"),
    CONTATO_CHANGE("Alteração salva com sucesso"),
    NEW_CONTATO("Contato cadastrado"),

    //Comuns 
    EMPTY_FIELD("Existem campos sem preenchimento"),
    ARQ_FAIL("Erro na leitura e/ou gravação do arquivo"),
    ARQ_NOT_FOUND("Arquivo não encontrado");

    private String msg;      

    Mensagens(String mensage){
        this.msg = mensage;
    }
    
    public String getMensagem(){
        return msg;
    }
}