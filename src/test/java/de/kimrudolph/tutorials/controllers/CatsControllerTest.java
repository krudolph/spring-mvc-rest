package de.kimrudolph.tutorials.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.http.MediaType;

public class CatsControllerTest {

    @Test
    public void testGetCatJsonRequest() throws Exception {
        standaloneSetup(new CatsController())
            .build()
            .perform(get("/cats/kitty.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string("{\"name\":\"kitty\",\"cuteness\":11}"));
    }

    @Test
    public void testGetCatNotFoundJsonRequest() throws Exception {
        standaloneSetup(new CatsController()).build()
            .perform(get("/cats/wuff.json")).andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCatJsonRequest() throws Exception {
        standaloneSetup(new CatsController())
            .build()
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
        standaloneSetup(new CatsController())
            .build()
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
        standaloneSetup(new CatsController())
            .build()
            .perform(
                put("/cats").contentType(MediaType.APPLICATION_JSON).content(
                    "{\"name\":\"kitty\",\"cuteness\":10}"))
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCatJsonRequest() throws Exception {
        standaloneSetup(new CatsController())
            .build()
            .perform(
                delete("/cats/kitty").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testGetCatXmlRequest() throws Exception {
        standaloneSetup(new CatsController())
            .build()
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
        standaloneSetup(new CatsController())
            .build()
            .perform(get("/cats.xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML))
            .andExpect(
                content()
                    .string(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" "
                            + "standalone=\"yes\"?><basket>"
                            + "<cats><cuteness>11</cuteness><name>kitty</name></cats>"
                            + "<cats><cuteness>5</cuteness><name>cuddles</name></cats>"
                            + "</basket>"));
    }

    @Test
    public void testCreateCatXmlRequest() throws Exception {
        standaloneSetup(new CatsController())
            .build()
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
        standaloneSetup(new CatsController())
            .build()
            .perform(
                put("/cats").contentType(MediaType.APPLICATION_XML).content(
                    "<cat><cuteness>10</cuteness><name>kitty</name></cat>"))
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCatXmlRequest() throws Exception {
        standaloneSetup(new CatsController())
            .build()
            .perform(
                delete("/cats/kitty").contentType(MediaType.APPLICATION_XML))
            .andExpect(status().isOk());
    }
}