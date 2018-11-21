import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.Validacao;

public class ValidacaoUsuarioTest {
    //given 
    Validacao v = new Validacao();

    @Test 
    public void valid_length_4 (){
        //when comprimento de 4 caracteres
        Boolean foo = v.isPadraoUsuario("anna");
        // then 
        Assertions.assertFalse(foo);
    }

    @Test
    public void valid_length_5(){
        //when comprimento de 5 caracteres
        Boolean foo = v.isPadraoUsuario("78901");
        // then
        Assertions.assertTrue(foo);
    }

    @Test
    public void valid_length_15(){
        //when comprimento de 15 caracteres
        Boolean foo = v.isPadraoUsuario("LmNoPqRsTuVwXyZ");
        // then
        Assertions.assertTrue(foo);
    }

    @Test
    public void invalid_length_16(){
        //when comprimento 16 caracteres
        Boolean foo = v.isPadraoUsuario("abcd456hijklm2301");
        //then 
        Assertions.assertFalse(foo);
    }

    @Test
    public void invalid_empty(){
        //when string vazia
        Boolean foo = v.isPadraoUsuario("");
        //then 
        Assertions.assertFalse(foo);
    }

    @Test
    public void invalid_with_spaces(){
        //when comprimento 16 caracteres
        Boolean foo = v.isPadraoUsuario("          ");
        //then 
        Assertions.assertFalse(foo);
    }
       
    @Test
    public void invalid_special_char(){
        //when caracteres especiais
        Boolean foo = v.isPadraoUsuario("Nat_Gonçalves");
        //then 
        Assertions.assertFalse(foo);
    }
    
}
