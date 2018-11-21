package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {

    private static final String[] listaNegra = {"lugar nenhum", "judas perdeu as botas", "casa da mãe joana", "num sei"};

    public boolean isPadraoUsuario(String nome){
        Pattern regex = Pattern.compile("[a-zA-Z0-9]{5,15}"); 
        Matcher matchRegex = regex.matcher(nome);
         return matchRegex.matches();
    }

    public boolean isPadraoNome(String nome) {
        //Req 8 - não deve aceitar strings vazias
        if (!nome.isEmpty()) {
            //Req 8 - não deve aceitar strings compostas apenas por caracteres em branco
            Pattern padraoVazio = Pattern.compile("\\s+");
            Matcher matchVazio = padraoVazio.matcher(nome);
            if (!matchVazio.matches()) {
                // não deve aceitar strings compostas por caracteres especiais
                Pattern padraoPort = Pattern.compile("[áàéíóúãõçôêâÁÀÉÍÓÚÃÕÇÔÊÂa-zA-Z0-9\\s]+$");
                Matcher matchPort = padraoPort.matcher(nome);
                if (matchPort.matches()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPadraoTelefone(String telefone) {
        //Req 9 - verificação se o o telefone contém apenas números deve estar entre 8 a 20 digitos
        Pattern regex = Pattern.compile("\\d{8,20}");
        Matcher matchRegex = regex.matcher(telefone);
        if (matchRegex.matches()) {
            return true;
        }
        return false;
    }

    public boolean isPadraoEndereco(String endereco) {
        if (endereco.length() > 2 && endereco.length() < 256) {
            for (String proibido : listaNegra) {
                if (endereco.equalsIgnoreCase(proibido) || endereco.toLowerCase().contains(proibido)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isPadraoEmail(String email) {
        //Req 10 - deve conter 1 @
        if (email.contains("@") && email.indexOf("@") == email.lastIndexOf("@")) {
            String local = email.substring(0, email.indexOf("@"));
            String dominio = email.substring(email.indexOf("@") + 1);
            
            //não pode ser vazio nem composto apenas de números	
            local = local.trim();
            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(local);

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            if (!local.isEmpty() && count < local.length()) {

                //domain - deve ter um ponto com um ou mais caractere não numérico.
                if (dominio.length() > 2) {
                    if (dominio.contains(".")) {
                        int arroba = dominio.indexOf("@");
			int first = dominio.indexOf(".");
                        
                        String dominio1;
                        String dominio2;
                        if(first-arroba == 1) {
                        	dominio1 = ".";
                        	dominio2 = dominio.substring(first+1);
                        }
                        else {
                        	dominio1 = dominio.substring(0, first);
                        	dominio2 = dominio.substring(first + 1);
                        }
                        
                        //domain - o primeiro não pode ser vazio nem numero
                        Pattern padraoDominio = Pattern.compile("\\s?\\d?");
                        Matcher matchDominio1 = padraoDominio.matcher(dominio1.substring(0, 1));
                        Matcher matchDominio2 = padraoDominio.matcher(dominio2.substring(0, 1));

                        if (!matchDominio1.matches() && !matchDominio2.matches()) {
                            return true;
                        }
                        
                        return false;
                    }
                    
                    return false;

                }
                
                return false;
            }
            
            return false;
        }
    return false;
    }

}