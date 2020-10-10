// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.junit.jupiter.api.TestInstance.Lifecycle;
// import java.nio.file.StandardCopyOption;
// import java.util.List;

// import model.Usuario;
// import model.UsuarioDAOImpl;


// @TestInstance(Lifecycle.PER_CLASS)
// public class UsuarioDAOTest{
//     UsuarioDAOImpl dao = new UsuarioDAOImpl(".src/main/resource/users.txt");
//     UsuarioDAOImpl dao1;

//     @BeforeAll
//         public static void smokeTest(){
//             File users = new File(".src/main/resource/users.txt");
//             verifyFileExists(users);
//         }

//     private static boolean verifyFileExists(File file) {
//         return file.exists() && !file.isDirectory();
//     }

//     @Test
//     public void should_insert_user(){
//         //given
//         Usuario u = new Usuario("ddddd", "44444", null);
        
//         //when
//         Usuario resp = dao.inserir(u);

//         //then
//         assertEquals(u, resp);
//         assertTrue(dao.getUsuarios().contains(u));  
//     }

//     @BeforeEach
//     public void setup() throws IOException {
//         Path origem = Paths.get("./src/test/resource/UsuarioDAOTest.txt");
//         Path destino = Paths.get("./src/main/resource/utest.txt");
//         Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
//         dao1 = new UsuarioDAOImpl("./src/main/resource/utest.txt");
//     }

//     @Test
//     public void should_list_all_users(){
//         //when
//         List<Usuario> lista = dao1.listarTodosUsuarios();

//         //then
//         assertEquals(7,lista.size());
//     }

//     @Test
//     public void should_search_for_user(){
//         //given
//         Usuario a = new Usuario("amora", "22222", null);
        
//         //when
//         Usuario resp = dao1.buscarPorNomeUsuario("amora");

//         //then
//         assertEquals(a, resp);
//     }
    
//     @Test
//     public void should_remove_user(){
//         //given 
//         int sizeInit = dao1.getUsuarios().size();
//         Usuario s = new Usuario("sonoEterno", "66666", null);

//         //when
//         Boolean status = dao1.removerUsuario(s);

//         //then
//         assertTrue(status);
//         assertNotEquals(sizeInit, dao1.getUsuarios().size());
//     }

//     @AfterEach
//     public void clearTest(){
//         File arquivo = new File("./src/main/resource/utest.txt");
//         arquivo.delete();
//     }

//     @AfterAll
//     public void clear(){
//         File arquivo = new File("./src/main/resource/users.txt");
//         arquivo.delete();
//     }

// }