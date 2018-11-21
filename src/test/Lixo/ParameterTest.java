/*
import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import model.Validacao;

@RunWith(ParameterContext.class)
public class ParameterTest{
    private Validacao servico;

    @Parameter(value=0)
    public String usuario;

    @Parameter(value=1)
    public Boolean esperado;

    @Parameter(value=2)
    public String cenario;

  
    public void setup(){
        servico = new Validacao();
    }

    @Parameters(name="{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][]{
            {"anna", false,"string com 4 caracteres"},
            {"abcd456hijklm2301", false,"string com 16 caracteres"},
            {"", false,"string vazia"},
            {"       ", false,"string com espaços"},
            {"Nat_Gonçalves", false,"string com caracteres especiais"},
        });
    }

    @Test
    public void should_validade_usuario(){
        Boolean resultado = servico.isPadraoUsuario(usuario);
       assertEquals(esperado, resultado);
    }
}
*/
