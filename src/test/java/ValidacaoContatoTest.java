// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

// import model.Validacao;

// public class ValidacaoContatoTest {
//     //given
//     Validacao v = new Validacao();

//     @DisplayName("Nome de contato")

//     @Test
//     public void valid_Name_with_numbers() {
//         // when números
//         Boolean foo = v.isPadraoNome("1234567890");
//         // then
//         Assertions.assertTrue(foo);
//     }

//     @Test
//     public void valid_Name_with_letters() {
//         // when letras do alfabeto brasileiro (incluindo acentos)
//         Boolean foo = v.isPadraoNome("MAURICIO DE SOUZA");
//         Boolean foo2 = v.isPadraoNome("âêôáééóúàãõç");
//         // then
//         Assertions.assertTrue(foo);
//         Assertions.assertTrue(foo2);
//     }

//     @Test
//     public void invalid_empty_Name() {
//         // when em branco
//         Boolean foo = v.isPadraoNome("");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_spaces_as_Name() {
//         // when nome com espaços
//         Boolean foo = v.isPadraoNome("      ");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_special_char_as_Name() {
//         // when caracteres especiais
//         Boolean foo = v.isPadraoNome("m@r%a_mon$ne!");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @DisplayName("Telefone de contato")

//     @Test
//     public void invalid_special_char_as_Phone() {
//         // when com caracteres especiais
//         Boolean foo = v.isPadraoTelefone("(12)19283-7465");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_phone_whit_size_7() {
//         // when comprimento de 7 algarismos
//         Boolean foo = v.isPadraoTelefone("1234567");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void valid_phone_whit_size_8() {
//         // when comprimento de 8 algarismos
//         Boolean foo = v.isPadraoTelefone("12345678");
//         // then
//         Assertions.assertTrue(foo);
//     }

//     @Test
//     public void valid_phone_whit_size_20() {
//         // when comprimento 20 algarismos
//         Boolean foo = v.isPadraoTelefone("12345678901234567890");
//         // then
//         Assertions.assertTrue(foo);
//     }

//     @Test
//     public void invalid_phone_whit_size_21() {
//         // when comprimento de 21 algarismos
//         Boolean foo = v.isPadraoTelefone("123456789012345678901");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_phone_whit_letters() {
//         // when caracteres não numéricos
//         Boolean foo = v.isPadraoTelefone("tel 123456");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @DisplayName("Endereco")

//     @Test
//     public void invalid_address_with_size_2() {
//         // when string de comprimento 2 caracteres
//         Boolean foo = v.isPadraoEndereco("ab");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void valid_address_with_size_3() {
//         // when string de comprimento 3 caracteres
//         Boolean foo = v.isPadraoEndereco("123");
//         // then
//         Assertions.assertTrue(foo);
//     }

//     @Test
//     public void valid_address_with_size_255() {
//         // when string de comprimento 255 caracteres
//         Boolean foo = v.isPadraoEndereco(
//                 "123456789qwertyuiopasdfghjkl;'zxcvbnm,./1234567890-=!@#$%^&*()_+^QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjkl;'zxcvbnm,./1234567890-=!@#$%^&*()_+^QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjkl;'zxcvbnm,./1234567890-=!@#$%^&*()_+^QWERTYUIOPASDFGHJKLZXCVBNM");
//         // then
//         Assertions.assertTrue(foo);
//     }

//     @Test
//     public void invalid_address_with_size_256() {
//         // when string de comprimento 256 caracteres
//         Boolean foo = v.isPadraoEndereco(
//                 "0123456789qwertyuiopasdfghjkl;'zxcvbnm,./1234567890-=!@#$%^&*()_+^QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjkl;'zxcvbnm,./1234567890-=!@#$%^&*()_+^QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjkl;'zxcvbnm,./1234567890-=!@#$%^&*()_+^QWERTYUIOPASDFGHJKLZXCVBNM");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_address_with_lugar_nenhum() {
//         // when string com lugar nenhum
//         Boolean foo = v.isPadraoEndereco("banido para lugar nenhum");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_address_with_judas_perdeu_as_botas() {
//         // when string com lugar nenhum
//         Boolean foo = v.isPadraoEndereco("Onde Judas Perdeu As Botas");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_address_with_casa_mae_joana() {
//         // when string com lugar nenhum
//         Boolean foo = v.isPadraoEndereco("casa da mãe JOANA");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_address_with_num_sei() {
//         // when string com lugar nenhum
//         Boolean foo = v.isPadraoEndereco("NUM sei, só sei que foi assim");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @DisplayName("Email")

//     @Test
//     public void invalid_with_more_than_one_at() {
//         // when string contém mais de um @
//         Boolean foo = v.isPadraoEmail("email@em@il.com");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_empty_before_at() {
//         // when string vazia antes de @
//         Boolean foo = v.isPadraoEmail("@teste.com");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_space_before_at() {
//         // when string com espaço antes de @
//         Boolean foo = v.isPadraoEmail("rom @teste.com");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_only_numbers_before_at() {
//         // when string composta somente por números antes do @
//         Boolean foo = v.isPadraoEmail("123@poderoso.com");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_number_after_at() {
//         // when @ sucedido por números 
//         Boolean foo = v.isPadraoEmail("adams@123.com");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_empty_after_at(){
//         // when @ sucedido por string vazia 
//         Boolean foo = v.isPadraoEmail("pack@.com");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_space_after_at(){
//         // when @ sucedido por espaço
//         Boolean foo = v.isPadraoEmail("packman@ .com");
//         // then
//         Assertions.assertFalse(foo);
//     }


//     @Test
//     public void invalid_without_dot(){
//         // when sem separação de ponto
//         Boolean foo = v.isPadraoEmail("cinder@disney");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void invalid_number_after_dot(){
//         // when números após a separação por ponto
//         Boolean foo = v.isPadraoEmail("ma@ma.10");
//         // then
//         Assertions.assertFalse(foo);
//     }


//     @Test
//     public void invalid_empty_after_dot(){
//         // when sem sucessão de caracteres não numéricos e não vazios
//         Boolean foo = v.isPadraoEmail("vazio@vazio.");
//         // then
//         Assertions.assertFalse(foo);
//     }

//     @Test
//     public void valid_Email(){
//         // when 
//         Boolean foo = v.isPadraoEmail("galileo@figaro.com");
//         Boolean foo2 = v.isPadraoEmail("77777777,!c@#_.com");
//         Boolean foo3 = v.isPadraoEmail("mouse@mick...");
//         // then
//         Assertions.assertTrue(foo);
//         Assertions.assertTrue(foo2);
//         Assertions.assertTrue(foo3);
//     }

// }
