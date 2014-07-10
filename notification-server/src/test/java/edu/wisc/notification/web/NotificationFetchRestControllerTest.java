package edu.wisc.notification.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.wisc.notification.NotificationServerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotificationServerApplication.class)
@WebAppConfiguration
public class NotificationFetchRestControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void testEmptyFetch() throws Exception {
        MvcResult result = this.mvc.perform(get("/fetch").param("username", "fakie").param("groups", "blank")).andExpect(status().isOk()).andReturn();
        
        String respondingJson = result.getResponse().getContentAsString();
        String expected = "{ \"categories\" : [ { \"title\" : \"Default Category\" } ] }";
        
        JSONAssert.assertEquals(expected, respondingJson, false);
    }
}
