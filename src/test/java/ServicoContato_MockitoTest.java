import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Contato;
import model.ContatoDAOImpl;
import model.ServicoContatoImpl;


public class ServicoContato_MockitoTest {

        ServicoContatoImpl servCon = new ServicoContatoImpl();
        ContatoDAOImpl contatoDAOmock = Mockito.mock(ContatoDAOImpl.class);
        ArrayList<Contato> fakeContatos = fakeListaContatos();

    @DisplayName("Listar contatos")
    @Test
    public void should_list_all_contacts(){
        when(contatoDAOmock.listarTodosContatos()).thenReturn(fakeContatos);
        servCon.setDAO(contatoDAOmock);

        assertEquals(fakeContatos, servCon.listarTodosContatos());
    }

    @Test
    public void should_return_empty_when_no_contacts(){
        when(contatoDAOmock.listarTodosContatos()).thenReturn(new ArrayList<Contato>());
        servCon.setDAO(contatoDAOmock);

        assertEquals(new ArrayList<Contato>(), servCon.listarTodosContatos());
    }

    @DisplayName("Buscar contato")
    @Test
    public void should_return_contact_when_search_by_contact_name(){
        ArrayList<Contato> busca = fakeBuscaPeter();
        when(contatoDAOmock.buscarPorParteNome(anyString())).thenReturn(busca);
        servCon.setDAO(contatoDAOmock);

        assertEquals(busca, servCon.buscarPorParteNome("Peter"));
    }

    @Test
    public void should_return_null_when_search_by_blank_parteNome(){
        assertNull(servCon.buscarPorParteNome("  "));
    }

    @Test
    public void should_return_null_when_empty_contact(){
        when(contatoDAOmock.buscarPorParteNome(anyString())).thenReturn(new ArrayList<Contato>());
        servCon.setDAO(contatoDAOmock);

        assertNull(servCon.buscarPorParteNome("Galileo"));
    }

    @DisplayName("Remover Contato")
    @Test
    public void should_remove_valid_contact(){
        Contato c = new Contato("Peter Pan", "0000000000", "intruder@disney.com", "Don't belong");
        when(contatoDAOmock.removerContato(isA(Contato.class))).thenReturn(true);
        ArrayList<Contato> busca = fakeBuscaPeter();
        when(contatoDAOmock.buscarPorParteNome(anyString())).thenReturn(busca);
        servCon.setDAO(contatoDAOmock);

        assertTrue(servCon.removerContato(c));
    }

    @Test
    public void should_return_false_if_contact_not_match(){
        Contato c = new Contato("Peter Stark", "777777777", "stark@got.com", "WinterFell");
        ArrayList<Contato> busca = fakeBuscaPeter();
        when(contatoDAOmock.buscarPorParteNome(anyString())).thenReturn(busca);
        servCon.setDAO(contatoDAOmock);

        assertFalse(servCon.removerContato(c));
    }

    @Test
    public void should_return_false_if_not_contact(){
        assertFalse(servCon.removerContato(null));
    }

    @DisplayName("Atualizar Contato")
    @Test
    public void should_update_contact(){
        Contato cAnt = new Contato("Antonhy Stark", "333333333", "iron@man.com", "Jarvis command");
        Contato cAtual = new Contato("Antony Stark", "333333333", "iron@man.com", "Jarvis base");

        when(contatoDAOmock.buscarPorParteNome(isA(String.class))).thenReturn(fakeContatos);
        when(contatoDAOmock.atualizarContato(cAnt, cAtual)).thenReturn(cAtual);
        servCon.setDAO(contatoDAOmock);

        assertEquals(cAtual, servCon.atualizarContato(cAnt, cAtual));
    }

    @Test
    public void should_return_null_if_one_contact_is_null(){
        Contato c1 = new Contato("Stan Lee", "11111111", "stan@marvel.com", "Master Street");
        assertNull(servCon.atualizarContato(null,c1));
        assertNull(servCon.atualizarContato(c1,null));
    }


    @Test
    public void should_return_null_if_cAnt_not_match(){
        Contato cAnt = new Contato("Peter Lee", "222222222", "spider@marvel.com", "White web");
        Contato cAtual = new Contato("John Stark", "333333333", "king@north.com", "Winterfell");
        when(contatoDAOmock.buscarPorParteNome(isA(String.class))).thenReturn(fakeBuscaPeter());
        servCon.setDAO(contatoDAOmock);

        assertNull(servCon.atualizarContato(cAnt, cAtual));
    }

    @Test
    public void should_return_null_if_new_contact_already_exists(){
        Contato cAtual = new Contato("Peter Parker", "222222222", "spider@marvel.com", "White web");
        Contato cAnt = new Contato("Peter Pan", "0000000000", "intruder@disney.com", "Don't belong");
        when(contatoDAOmock.buscarPorParteNome(isA(String.class))).thenReturn(fakeBuscaPeter());
        servCon.setDAO(contatoDAOmock);

        assertNull(servCon.atualizarContato(cAnt, cAtual));
    }

    @DisplayName("Inserir Contato")
    @Test
    public void should_insert_new_Contact(){
        Contato c = new Contato("Fred Mercury", "444444444", "fred@queen.com", "Boehmian Rapsodia");
        when(contatoDAOmock.buscarPorParteNome(isA(String.class))).thenReturn(new ArrayList<Contato>()); 
        when(contatoDAOmock.inserir(isA(Contato.class))).thenReturn(c);
        servCon.setDAO(contatoDAOmock);
        
        assertEquals(c, servCon.inserir("Fred Mercury", "444444444", "fred@queen.com", "Boehmian Rapsodia"));
    }

    @Test
    public void should_insert_new_Contact_with_similar_names(){
        Contato c = new Contato("Peter Mercury", "444444444", "fred@queen.com", "Boehmian Rapsodia");
        when(contatoDAOmock.buscarPorParteNome(isA(String.class))).thenReturn(fakeBuscaPeter()); 
        when(contatoDAOmock.inserir(isA(Contato.class))).thenReturn(c);
        servCon.setDAO(contatoDAOmock);

        assertEquals(c, servCon.inserir("Peter Mercury", "444444444", "peter@queen.com", "Boehmian Rapsodia"));
    }
    
    @Test
    public void should_not_insert_new_contact(){
        when(contatoDAOmock.buscarPorParteNome(isA(String.class))).thenReturn(fakeContatos);
        servCon.setDAO(contatoDAOmock);

        assertNull(servCon.inserir("Stan Lee", "11111111", "stan@marvel.com", "Master Street"));
    }

    private ArrayList<Contato> fakeListaContatos(){
        ArrayList<Contato> contatos = new ArrayList<Contato>();

        Contato c1 = new Contato("Stan Lee", "11111111", "stan@marvel.com", "Master Street");
        Contato c2 = new Contato("Peter Parker", "222222222", "spider@marvel.com", "White web");
        Contato c3 = new Contato("Antonhy Stark", "333333333", "iron@man.com", "Jarvis command");
        Contato c4 = new Contato("Peter Pan", "0000000000", "intruder@disney.com", "Don't belong");

        contatos.add(c3);
        contatos.add(c4);
        contatos.add(c2);
        contatos.add(c1);

        return contatos;
    }

    private ArrayList<Contato> fakeBuscaPeter(){
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        Contato c2 = new Contato("Peter Parker", "222222222", "spider@marvel.com", "White web");
        Contato c4 = new Contato("Peter Pan", "0000000000", "intruder@disney.com", "Don't belong");

        contatos.add(c4);
        contatos.add(c2);

        return contatos;
    }
}
