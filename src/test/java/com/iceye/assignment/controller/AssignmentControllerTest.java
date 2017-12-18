package com.iceye.assignment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceye.assignment.model.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssignmentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void performGetRequestAssignment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/assignment"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonObject[] array = mapper.readValue(mvcResult.getResponse().getContentAsString(), JsonObject[].class);

        Assert.assertNotNull(array);
        Assert.assertFalse(array.length == 0);
        Assert.assertEquals(100, array.length);
    }

    @Test
    public void performGetRequestIngest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/ingest/test"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        Assert.assertNotNull(response);
        Assert.assertEquals("<a href=\"http://localhost:80/download/test\">Download image</a>", response);
    }

}