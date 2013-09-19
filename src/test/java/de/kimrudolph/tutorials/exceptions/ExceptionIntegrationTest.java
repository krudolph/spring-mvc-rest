package de.kimrudolph.tutorials.exceptions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.kimrudolph.tutorials.configuration.TestApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestApplicationContext.class })
public class ExceptionIntegrationTest {

    private MockMvc service;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {

        service = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetCatNotFoundRequest() throws Exception {

        service
            .perform(get("/cats/wuff").contentType(MediaType.APPLICATION_XML))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.TEXT_PLAIN))
            .andExpect(
                content().string(
                    "Cat 'wuff' has not been found. "
                        + "Maybe you misspelled the name?"));

        service
            .perform(get("/cats/wuff").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.TEXT_PLAIN))
            .andExpect(
                content().string(
                    "Cat 'wuff' has not been found. "
                        + "Maybe you misspelled the name?"));
    }
}
