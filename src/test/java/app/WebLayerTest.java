package app;

import app.controller.MainManager;
import app.controller.PostsManager;
import app.service.AccountService;
import app.service.PostService;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

// Athugið vel að þessi import séu rétt 
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 *
 * Prófunarklasi sem framkvæmir prófanir án þess að þurfa að kalla á þjóninn
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MainManager.class)  // Spring MockMvc - allt "context"-ið keyrt upp 
public class WebLayerTest {

    // Þjónninn (Tomcat) ekki keyrður upp 
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    PostService postService;
    

    /**
     * Aðferð til að athuga hvort módelhluturinn "posts" hlaðast ekki örugglega
     * á forsíðu
     */
    @Test
    public void postModelIsLoaded() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("posts", notNullValue()));
    }
}
