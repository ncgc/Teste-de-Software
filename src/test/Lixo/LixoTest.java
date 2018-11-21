/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class LixoTest {
    @Test
    public void imprimir() throws FileNotFoundException{
    File file1 = new File("./src/test/resource/ContatoDAO_test.txt");
    Scanner scan = new Scanner(file1);
    while (scan.hasNextLine()) {
        String linha = scan.nextLine();
        String[] partes = linha.split(";", 4);
        System.out.println(partes);   
    }
    }
}
*/
/*
File arquivo = new File("./src/test/resource/test.txt");
arquivo.createNewFile();
*/

/*
 final File expected = new File("xyz.txt");
    final File output = folder.newFile("xyz.txt");
    TestClass.xyz(output);
    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));

    Test
public void file() {
    File actualFile = new File("actual.txt");
    File expectedFile = new File("expected.txt");
    assertThat(actualFile).hasSameContentAs(expectedFile);
}
    */


/* @Disabled
    @Test(expected = FileNotFoundException.class)
    public void should_return_Exception_when_file_not_found(){
        throws IOException{
            IOException cause = new IOException();
            doThrow (cause).when(dao).inicializaContatos(any(File.class));

            Throwable actual = thrownBy(() -> dao.inicializaContatos());

            assertnotNull(actual);
            assertTrue(actual instanceof IllegalStateException);
            assertSame(cause, actual.getClause());
            assertEquals(mensagem de erro, actual.getMessage());
        }
    }
    */

/*

 @Disabled
    @Test
    public void emailContato() {

        // com mais de um @
        assertFalse(v.isPadraoEmail("email@em@il.com"));
        // com string vazia antes de @
        assertFalse(v.isPadraoEmail("@teste.com"));
        assertFalse(v.isPadraoEmail("rom @roma.com"));

        // com números antes do @
        assertFalse(v.isPadraoEmail("123@poderoso.com"));
        // sucedida por número
        assertFalse(v.isPadraoEmail("adams@123.com"));
        // sucedida por vazio
        assertFalse(v.isPadraoEmail("pack@.com"));
        assertFalse(v.isPadraoEmail("packman@ .com"));

        // sem ponto após @
        assertFalse(v.isPadraoEmail("cinder@disney"));
        // somente com ponto após @
        assertTrue(v.isPadraoEmail("mouse@mick..."));
        // com número após o ponto
        assertFalse(v.isPadraoEmail("ma@ma.10"));
        // vazio após o ponto
        assertFalse(v.isPadraoEmail("vazio@vazio."));
        // email com caracter especial
        assertTrue(v.isPadraoEmail("77777777,!c@#_.com"));
        // email válido
        assertTrue(v.isPadraoEmail("galileo@figaro.com"));
    }

    // remover o teste de ordem alfabética dessa classe e alocar em uma mais 
    // propriadas
    @Disabled
    @Test
    public void ordemAlfabetica() {
        ArrayList<Contato> contatos = new ArrayList<>();
        ArrayList<Contato> contatosOrdem = new ArrayList<>();

        Contato c1 = new Contato();
        c1.setNome("Magali");

        Contato c2 = new Contato();
        c2.setNome("mônica");

        Contato c3 = new Contato();
        c3.setNome("cascão");

        Contato c4 = new Contato();
        c4.setNome("123");

        contatos.add(c1);
        contatos.add(c2);
        contatos.add(c3);
        contatos.add(c4);
        Collections.sort(contatos);

        contatosOrdem.add(c4);
        contatosOrdem.add(c3);
        contatosOrdem.add(c1);
        contatosOrdem.add(c2);

        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c3) > 0);
        assertTrue(c2.compareTo(c2) == 0);

        assertIterableEquals(contatos, contatosOrdem);

    }

      @Disabled
    private void leitura() throws FileNotFoundException{
        File file1 = new File("./src/test/resource/ContatoDAO_test.txt");
        Scanner scan = new Scanner(file1);
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            String[] partes = linha.split(";", 4);
    }
    }

    */