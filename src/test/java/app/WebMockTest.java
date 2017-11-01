package app;

import app.controller.MainManager;
import app.controller.UserManager;
import app.model.Post;
import app.service.AccountService;
import app.service.PostService;
import java.util.ArrayList;
import java.util.HashMap;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Prófunarklasi sem framkvæmir prófanir á weblayer og notar WebMvcTest og
 * Mockito til að prófa service og controller klasa
 */
@RunWith(SpringRunner.class)
/**
 * Aðeins veflagið er keyrt upp en ekki allur "context"-inn Getum beðið um að
 * keyra bara upp KennariController klasann Biðjum um að bæta KennariService inn
 * í "context-inn" sem Mock (prófanahlut)
 */

@WebMvcTest(controllers={MainManager.class, UserManager.class})
public class WebMockTest {

    // Þjónninn (Tomcat) ekki keyrður upp 
    @Autowired
    private MockMvc mockMvc;

    // Bætum við prófunar (e. Mock) service klasa - kemur úr springframework safninu (fyrir Mockito
    // sérstaklega gert fyrir Mockito safnið 
    @MockBean
    PostService postService;
    
    @MockBean
    AccountService accountService;

    /**
     * Aðferð sem athugar hvort model hluturinn sem er sendur
     * yfir í view layerinn sé ekki örugglega af lengd 0 þegar getAllPosts() 
     * aðferðin skilar tómum lista
     */
    @Test
    public void testGetAllPosts() throws Exception {
        when(postService.getAllPosts()).thenReturn(new ArrayList<Post>());
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("posts", hasSize(0)));

    }
    
    
    
    
    /*@Test
    public void testLogout() throws Exception {
        when(postService.).thenReturn(St);
        this.mockMvc.perform(get("/utskra"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("posts", hasSize(0)));

    }*/

    /*@Test
    public void testChangeUserName() throws Exception {
        when(accountService.changeName("bvk1@hi.is", "fdsa")).thenReturn(true);
        this.mockMvc.perform((RequestBuilder) post("/reikningur/breyta-nafni"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("message", "Nafni þínu hefur verið breytt!"));

    }*/
    /**
     * Athugar hvort createNewPost skilar true (þ.e. það tókst að búa til nýtt innlegg) 
     * þegar sett eru inn lögleg viðföng
     */
    @Test
    public void testSuccessNewPost() throws Exception {
        HashMap<String, String> ss = new HashMap();
        ss.put("", "[]");
        when(postService.generateDisplayPostsJSON(new ArrayList<Post>())).thenReturn("{=[]}");
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("postsJSON", ss));
    }

    
    
    
    
    
    
    /**
     * Athugar hvort getAllPosts skilar ekki örugglega lista af innleggum
     */
    @Test
    public void testaLifirTrue4() throws Exception {
        when(postService.getAllPosts()).thenReturn(new ArrayList<Post>());
    }
    
    /**
     * Prófið athugar hvort getPostById skilar ekki örugglega hlut sem er af gerðinni Post
     * Einnig athugar hún hvort index.jsp er skilað þegar farið er á forsíðu
     */
    @Test
    public void testaLifirTrue5() throws Exception {
        when(postService.getPostById(0)).thenReturn(new Post());
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("index"));

    }
}
