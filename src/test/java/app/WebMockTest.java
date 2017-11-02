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
}
