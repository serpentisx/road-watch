package test;

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
 *
 * @author Ebba Þóra Hvannberg
 * @date október 2017 
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
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
         * fá til baka síðu sem inniheldur Klukkan 
        
         * @throws java.lang.Exception
         */
	@Test
        public void heimaSkilarKlukkan() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Klukkan");
    }

}
