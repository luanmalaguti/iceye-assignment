package com.iceye.assignment.service;

import com.iceye.assignment.model.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentServiceTest {

    @Autowired
    private AssignmentService assignmentService;

    @Test
    public void getJsonTest() {
        JsonObject[] jsonObject = assignmentService.getJson();
        assertNotNull(jsonObject);
        assertFalse(jsonObject.length == 0);
    }

    @Test
    public void getBaseUrlTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        String url = assignmentService.getBaseUrl(request);
        assertNotNull(url);
        assertEquals("http://localhost:80", url);
    }
}