import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.Criptografia;

public class CriptografiaTest {

     // given
     Criptografia c = new Criptografia();
    
    @Test
    public void cripto_numbers(){
        // when números 
        String foo = c.criptografar("0123456789");
        // then
        assertEquals("3456789012", foo);
    }

    @Test
    public void cripto_alphabet(){
        // when alfabeto
        String foo = c.criptografar("abcdefghijklmnopqrstuvwxyz");
        // then
        assertEquals("defghijklmnopqrstuvwxyzabc", foo);
    }
   
    @Test
    public void descripto_numbers(){
        // when números 
        String foo = c.descriptografar("3456789012");
        // then
        assertEquals("0123456789", foo);
    }

    @Test
    public void descripto_alphabet(){
        // when alfabeto
        String foo = c.descriptografar("defghijklmnopqrstuvwxyzabc");
        // then
        assertEquals("abcdefghijklmnopqrstuvwxyz", foo);
    }
    
}
