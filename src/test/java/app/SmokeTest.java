package app;

import app.controller.UserManager;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Prófunarklasi sem athugar hvort UserManager (controller klasi) keyrir
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    UserManager userManager;

    /**
     * Aðferð til að athuga hvort UserManager controller hefur verið búinn til
     */
    @Test
    public void contextLoads() {
        assertThat(userManager).isNotNull();
    }
}
