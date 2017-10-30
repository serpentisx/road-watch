package test;

import app.controller.UserManager;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 *
 * @author Bjarki Viðar
 * @date október 2017
 * 
 * Prófunarklasi sem athugar hvort UserManager keyrir 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
    
        @Autowired 
        UserManager userManager;

        /**
         * Aðferð til að athuga hvort UserManager hlutur hefur verið búinn til 
         */
        
	@Test
	public void contextLoads() {
        
                   assertThat(userManager).isNotNull();
	}

}
