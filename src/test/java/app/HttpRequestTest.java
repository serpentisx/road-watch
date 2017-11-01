package app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Prófunarklasi sem sendir URL á test web client
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    // Fyrir random port
    @LocalServerPort
    private int port;

    // Client klasi til að nota í integration test (samþættingarpróf) og notar Http API 
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Aðferð til að athuga hvort virkar að senda HttpRequest á heimaslóðina og
     * fá til baka síðu sem inniheldur strenginn "Vegavaktin"      
     * @throws java.lang.Exception
     */
    @Test
    public void mainPageContainsVegavaktin() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Vegavaktin");
    }
}
