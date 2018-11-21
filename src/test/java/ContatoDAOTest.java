import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Contato;
import model.ContatoDAOImpl;

public class ContatoDAOTest {
    private ContatoDAOImpl dao;

    @BeforeEach
    public void setup() throws IOException {
        Path origem = Paths.get("./src/test/resource/ContatoDAOTest.txt");
        Path destino = Paths.get("./src/main/resource/data_utest.txt");
        Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
        dao = new ContatoDAOImpl("./src/main/resource/data_utest.txt");
    }

    @Test
    public void file_read_contacts_ordered() {
        File actualFile = new File("./src/test/resource/ContatoDAOTest.txt");
        File expectedFile = new File("./src/main/resource/data_utest.txt");

        assertNotSame(expectedFile, actualFile);
        
        //assertThat(actualFile).hasSameContentAs(expectedFile);
    }

    @Test
    public void should_insert_new_contact(){
        //given
        Contato c = new Contato("Mestre dos Magos", "999999999", "dungeonsMaster@dd.com", "DungeonsDragons");
        assumeTrue(dao.salvarContato());

        //when
        Contato actual = dao.inserir(c);
        
        //then
        File actualFile = new File("./src/main/resource/ContatoDAO_data_utest.txt");

        assertNotNull(actual);
        assertEquals(c, actual);
        assertTrue(dao.contatos.contains(c));
      //  assertThat(actualFile).hasContent(c.tupla());
    }

    @Test
    public void should_list_contacts(){
        //given
        Contato c1 = new Contato("Diana","22222222", "diana@dd.com", "acrobata");
        Contato c2 = new Contato("princesa Diana", "77777777", "diana@royal.uk", "palacio");
        
        //when
        List<Contato> busca = dao.buscarPorParteNome("Diana");

        //then
        assertEquals(2, busca.size());
        assertEquals(c1,busca.get(0));
        assertEquals(c2, busca.get(1));
    }

    @Test
    public void should_remove_contact(){
        //given
        Contato c = new Contato("Sheila", "33333333", "sheila@dd.com", "mão ligeira");
        assumeTrue(dao.salvarContato());
       
        //when
        Boolean actual = dao.removerContato(c);
        File actualFile = new File("./src/main/resource/ContatoDAO_data_utest.txt");
        File originalFile = new File("./src/test/resource/ContatoDAOTest.txt");
        
        //then
        assertTrue(actual);
        assertFalse(dao.contatos.contains(c));
      //  assertThat(actualFile).isNotEqualTo(originalFile);
    }

    @Test
    public void should_update_Contact(){
        //given
        Contato cAnt = new Contato("Eric","11111111", "eric@dd.com", "cavaleiro medroso");
        Contato cAtual = new Contato("Erick","123456789", "erick@dd.com", "cavaleiro medroso");
        assumeNotNull(dao.buscarPorParteNome("Eric"));

        //when 
        Contato resposta = dao.atualizarContato(cAnt, cAtual);
        File actualFile = new File("./src/main/resource/ContatoDAO_data_utest.txt");

        assertEquals(cAtual, resposta);
        assertTrue(dao.contatos.contains(cAtual));
        assertFalse(dao.contatos.contains(cAnt));
      //  assertThat(actualFile).hasContent(cAtual.tupla());
    }

    private void assumeNotNull(List<Contato> buscarPorParteNome) {
    }

    @Test
    public void should_list_all_contacts(){
        //when
        List<Contato> lista = dao.listarTodosContatos();

        //then
        assertNotNull(lista);
        assertEquals(7, lista.size());
        for(int i= 0; i < 7; i++){
            assertTrue(lista.contains(contatoBase().get(i)));
       }
    }

    @AfterEach
    public void clear(){
        File arquivo = new File("./src/main/resource/data_utest.txt");
        arquivo.delete();
    }
 
    private ArrayList<Contato> contatoBase(){
        Contato c1 = new Contato("Bobby", "55555555", "barbaro@dd.com", "deixa a uni");
        Contato c2 = new Contato("Diana","22222222", "diana@dd.com", "acrobata");
        Contato c3 = new Contato("Eric","11111111", "eric@dd.com", "cavaleiro medroso");
        Contato c4 = new Contato("Hank", "00000000", "hank@dd.com", "lider");
        Contato c5 = new Contato("Sheila", "33333333", "sheila@dd.com", "mão ligeira");
        Contato c6 = new Contato("Presto" , "44444444", "presto@dd.com", "mago atrapalhado");
        Contato c7 = new Contato("princesa Diana", "77777777", "diana@royal.uk", "palacio");

        ArrayList<Contato> contatos = new ArrayList<Contato>();
        contatos.addAll(Arrays.asList(c1, c2, c3, c4, c6, c7, c5));

        return contatos;
    }

  
}
