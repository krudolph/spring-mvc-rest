package de.kimrudolph.tutorials.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CatsControllerTest {

    private MockMvc service;

    @Before
    public void setup() {

        service = MockMvcBuilders.standaloneSetup(new CatsController())
            .build();
    }

    @Test
    public void testGetCatJsonRequest() throws Exception {

        service
            .perform(get("/cats/kitty.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string("{\"name\":\"kitty\",\"cuteness\":11}"));
    }

    @Test
    public void testGetAllCatJsonRequest() throws Exception {

        service
            .perform(get("/cats.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().string(
                    "{\"cats\":[{\"name\":\"kitty\",\"cuteness\":11},"
                        + "{\"name\":\"cuddles\",\"cuteness\":5}]}"));
    }

    @Test
    public void testCreateCatJsonRequest() throws Exception {

        service
            .perform(
                post("/cats").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"grumpy\",\"cuteness\":11}"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().string("{\"name\":\"grumpy\",\"cuteness\":11}"));
    }

    @Test
    public void testUpdateCatJsonRequest() throws Exception {

        service.perform(
            put("/cats").contentType(MediaType.APPLICATION_JSON).content(
                "{\"name\":\"kitty\",\"cuteness\":10}")).andExpect(
            status().isOk());
    }

    @Test
    public void testDeleteCatJsonRequest() throws Exception {

        service.perform(
            delete("/cats/kitty").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testGetCatXmlRequest() throws Exception {

        service
            .perform(get("/cats/kitty.xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML))
            .andExpect(
                content().string(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" "
                        + "standalone=\"yes\"?><cat><cuteness>11"
                        + "</cuteness><name>kitty</name></cat>"));
    }

    @Test
    public void testGetAllCatXmlRequest() throws Exception {

        service
            .perform(get("/cats.xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML))
            .andExpect(
                content()
                    .string(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" "
                            + "standalone=\"yes\"?><basket>"
                            + "<cat><cuteness>11</cuteness><name>kitty</name></cat>"
                            + "<cat><cuteness>5</cuteness><name>cuddles</name></cat>"
                            + "</basket>"));
    }

    @Test
    public void testCreateCatXmlRequest() throws Exception {

        service
            .perform(
                post("/cats")
                    .contentType(MediaType.APPLICATION_XML)
                    .accept(MediaType.APPLICATION_XML)
                    .content(
                        "<cat><cuteness>10</cuteness><name>grumpy</name></cat>"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_XML))
            .andExpect(
                content()
                    .string(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                            + "<cat><cuteness>10</cuteness><name>grumpy</name></cat>"));
    }

    @Test
    public void testUpdateCatXmlRequest() throws Exception {

        service.perform(
            put("/cats").contentType(MediaType.APPLICATION_XML).content(
                "<cat><cuteness>10</cuteness><name>kitty</name></cat>"))
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCatXmlRequest() throws Exception {

        service.perform(
            delete("/cats/kitty").contentType(MediaType.APPLICATION_XML))
            .andExpect(status().isOk());
    }
}