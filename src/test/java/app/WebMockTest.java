package app;




import app.controller.UserManager;
import app.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

// Athugið vel að þessi import séu rétt 
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

/**
 *
 * @author Ebba Þóra Hvannberg
 * @date október 2017 
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 * 
 * Prófunarklasi sem framkvæmir prófanir á weblayer og notar WebMvcTest og
 * Mockito til að prófa service klasa 
*/

@RunWith(SpringRunner.class)
/**
 *  Aðeins veflagið er keyrt upp en ekki allur "context"-inn
 *  Getum beðið um að keyra bara upp KennariController klasann 
 *  Biðjum um að bæta KennariService inn í "context-inn" sem Mock (prófanahlut)
 */
@WebMvcTest(UserManager.class)     
                                            
public class WebMockTest {

    // Þjónninn (Tomcat) ekki keyrður upp 
    @Autowired
    private MockMvc mockMvc;

    // Bætum við prófunar (e. Mock) service klasa - kemur úr springframework safninu (fyrir Mockito
    // sérstaklega gert fyrir Mockito safnið 
    @MockBean
    AccountService accountService;
     
    /**
     * Aðferð sem prófar /lifir á KennariController en með
     * erALifi() false. Ættum að fá til baka nyrKennari.html síðuna
     */
    @Test
    public void testaLifirTrue() throws Exception {
        // Látum erNafnRett() skila true 
        // Notum Mockito í prófanirnar - Mockito er Framework fyrir unit testing í Java 
        // http://site.mockito.org/   
        
        // Prófið ætti að takast - prófum sönnu leiðina í if-setningunni
        when(daginnService.erALifi()).thenReturn(true);
        this.mockMvc.perform(get("/lifir"))
                .andDo(print())
                .andExpect(status()
                .isOk())
                .andExpect(content()
                .string(containsString("Nýr kennari")));  
      
    }
    /**
     * Aðferð sem prófar /lifir á KennariController en með
     * erALifi() false. Ættum að fá til baka listiKennara.html síðuna
     */
    @Test
    public void testaLifirFalse() throws Exception {
        
        // Prófið ætti að takast - prófum ósönnu leiðina í if-setningunni 
        when(daginnService.erALifi()).thenReturn(false);
        this.mockMvc.perform(get("/lifir")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Listi yfir kennara")));
    }
    
    /**
     * Prófið ætti að mistakast - prófum ósönnu leiðina erALifi() en berum
     * saman við rangan streng 
     * @throws Exception 
     */
    @Test
    public void testaLifirFalseMedRongumStreng() throws Exception {
        
        // Prófið ætti að ekki takast - prófum ósönnu leiðina í if-setningunni 
        when(daginnService.erALifi()).thenReturn(false);
        
      
        this.mockMvc.perform(get("/lifir")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nýr kennari"))); 
        }
       
    }
