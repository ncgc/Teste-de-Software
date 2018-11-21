import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Contato;
import model.Criptografia;
import model.ServicoUsuarioImpl;
import model.Usuario;
import model.UsuarioDAOImpl;

public class ServicoUsuario_MockitoTest {
    private ServicoUsuarioImpl servCon = new ServicoUsuarioImpl();
    private UsuarioDAOImpl daoMock = Mockito.mock(UsuarioDAOImpl.class);
    ArrayList<Usuario> fakeUsuarios = fakeUsuarios();

    @Test
    public void should_insert_new_user(){
        //given 
        when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(null);
        Usuario u = new Usuario("ixodqr", "vhqkd", null);
        when(daoMock.inserir(isA(Usuario.class))).thenReturn(u);
        servCon.setDAO(daoMock);

        //when
        Usuario uActual = servCon.inserir("fulano", "senha", "senha", null);

        //then
        assertEquals(u, uActual);
        verify(daoMock).inserir(any(Usuario.class));
        verify(daoMock, never()).removerUsuario(any(Usuario.class)); 
    }

    @Test
    public void should_not_insert_new_user_out_of_standard(){
        //when
        Usuario uActual_nome = servCon.inserir("ana", "senha", "senha", null);
        Usuario uActual_senha = servCon.inserir("maria", "123", "123", null);
        
        //then
        assertNull(uActual_nome);
        assertNull(uActual_senha);
    }

    @Test
    public void should_not_insert_if_user_already_exists(){
        //given
        Usuario u = new Usuario("ixodqr", "vhqkd", null);
        when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(u);
        servCon.setDAO(daoMock);

        //when
        Usuario uActual = servCon.inserir(u);

        //then
        assertNull(uActual);
        verify(daoMock).buscarPorNomeUsuario(any(String.class));
    }

    @Test
    public void should_not_insert_user_if_dao_fails(){
        //given
        Usuario u = new Usuario("ixodqr", "vhqkd", null);
        when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(null);
        when(daoMock.inserir(isA(Usuario.class))).thenReturn(null);
        servCon.setDAO(daoMock);

        //when
        Usuario uActual = servCon.inserir(u);

        //then
        assertNull(uActual);
        verify(daoMock).inserir(any(Usuario.class));
        verify(daoMock, never()).removerUsuario(any(Usuario.class)); 
    }

    @Test
    public void should_not_insert_user_if_password_differs(){
        //when
        Usuario uActual = servCon.inserir("anaMaria", "loveMe", "senha", null);

        //then
        assertNull(uActual);
    }


    @Test
    public void should_return_user_when_search_by_name(){
        //given
        Usuario uCripto = new Usuario("ddddd", "44444", new ArrayList<Contato>());
        when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(uCripto);
        when(daoMock.getUsuarios()).thenReturn(fakeUsuarios);
        servCon.setDAO(daoMock);
        
        //when
        Usuario uActual = servCon.buscarPorNomeUsuario("aaaaa");

        //then
        assertEquals(fakeUsuarios.get(0), uActual);
        verify(daoMock).buscarPorNomeUsuario(any(String.class));
    }

    @Test
    public void should_not_return_user_when_name_cannot_be_cripto(){
        //given
        when(daoMock.getUsuarios()).thenReturn(fakeUsuarios);
        servCon.setDAO(daoMock);

        //when
        Usuario uActual = servCon.buscarPorNomeUsuario("Gonçalves");

        //then
        assertNull(uActual);
    }

    @Test
    public void should_not_return_user_when_there_is_no_user(){
        //given
        when(daoMock.getUsuarios()).thenReturn(new ArrayList<Usuario>());
        servCon.setDAO(daoMock);

        //when
        Usuario uActual = servCon.buscarPorNomeUsuario("fulano");

        //then
        assertNull(uActual);
    }

    @Test
    public void should_remove_user_if_exists(){
         //given
         Usuario u = fakeUsuarios.get(0);
         Usuario uCripto = new Usuario("ddddd", "44444", new ArrayList<Contato>());
         when(daoMock.removerUsuario(u)).thenReturn(true);
         when(daoMock.getUsuarios()).thenReturn(fakeUsuarios);
         when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(uCripto);
         servCon.setDAO(daoMock);
 
         //when
         Boolean actual = servCon.removerUsuario(u);
         
         //then
         assertTrue(actual);
         verify(daoMock).buscarPorNomeUsuario(any(String.class));
         verify(daoMock).removerUsuario(any(Usuario.class));
     }
    

    @Test
    public void should_not_remove_user_if_doesnt_exists(){
        //given
        Usuario uCripto = new Usuario("ddddd", "44444", new ArrayList<Contato>());
        Usuario u = fakeUsuarios.get(0);
        when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(uCripto);
        when(daoMock.getUsuarios()).thenReturn(fakeUsuarios);
        servCon.setDAO(daoMock);
        
        //when
        Boolean actual = servCon.removerUsuario(u);

        //then
        assertFalse(actual);
        verify(daoMock).buscarPorNomeUsuario(any(String.class));
    }
      
    
    @Test
    public void should_not_remove_user_if_null(){
        //when
        Boolean actual = servCon.removerUsuario(null);

        //then
        assertFalse(actual);
    }

    @Test
    public void should_update_user(){
        //given
        Usuario uAnt = fakeUsuarios.get(0);
        Usuario uAtual = new Usuario("natalia", "12345", null);
        Usuario uCripto = new Usuario("ddddd", "44444", new ArrayList<Contato>());
        Criptografia c = new Criptografia();

        when(daoMock.buscarPorNomeUsuario(c.criptografar(uAnt.getNomeUsuario()))).thenReturn(uCripto);
        when(daoMock.buscarPorNomeUsuario(c.criptografar(uAtual.getNomeUsuario()))).thenReturn(null);
        when(daoMock.getUsuarios()).thenReturn(fakeUsuarios);
        when(daoMock.atualizarUsuario(notNull(), notNull())).thenReturn(uAtual);

        servCon.setDAO(daoMock);

        //when
        Usuario resposta = servCon.atualizarUsuario(uAnt, uAtual);

        //then
        assertEquals(uAtual, resposta);
    }

   
    @Test
    public void should_not_update_user_if_uAnt_doesnt_exists(){
        //given
        Usuario uAnt = fakeUsuarios.get(2);
        Usuario uAtual = new Usuario("natalia", "12345", null);
        when(daoMock.getUsuarios()).thenReturn(fakeUsuarios);
        when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(null);
        servCon.setDAO(daoMock);

        //when
        Usuario resposta = servCon.atualizarUsuario(uAnt, uAtual);

        //then
        assertNull(resposta);
    }

    @Test
    public void should_not_update_user_if_uAtual_already_exists(){
        //given
        Usuario uAnt = fakeUsuarios.get(2);
        Usuario uAtual = fakeUsuarios.get(0);
        Usuario uCripto = new Usuario("ddddd", "44444", new ArrayList<Contato>());

        when(daoMock.getUsuarios()).thenReturn(fakeUsuarios);
        when(daoMock.buscarPorNomeUsuario(isA(String.class))).thenReturn(uCripto);
        servCon.setDAO(daoMock);

        //when
        Usuario resposta = servCon.atualizarUsuario(uAnt, uAtual);

        //then
        assertNull(resposta);
    }

    @Test
    public void should_return_empty_if_no_user(){
        //given
        when(daoMock.getUsuarios()).thenReturn(new ArrayList<Usuario>());

        //when
        List<Usuario> lista = servCon.listarTodosUsuarios();

        //then
        assertTrue(lista.isEmpty());
    }

    @Test
    public void should_return_users(){
        //given
        when(daoMock.listarTodosUsuarios()).thenReturn(fakeUsuarios);
        servCon.setDAO(daoMock);

        //when
        List<Usuario> lista = servCon.listarTodosUsuarios();

        //then
        assertEquals(fakeUsuarios, lista);
        verify(daoMock).listarTodosUsuarios();
    }


    private ArrayList<Usuario> fakeUsuarios(){
        List<Contato> contatos = new ArrayList<Contato>();
        Usuario u1 = new Usuario("aaaaa", "11111", contatos);
        Usuario u2 = new Usuario("ccccc", "22222", contatos);
        Usuario u3 = new Usuario("11111", "aaaaa", contatos);
        Usuario u4 = new Usuario("22222", "ccccc", contatos);

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);
        usuarios.add(u4);

        return usuarios;
    }

    
}