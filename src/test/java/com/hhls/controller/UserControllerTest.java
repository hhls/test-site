package com.hhls.controller;

import static org.junit.Assert.assertNotNull;

import com.hhls.BaseControllerTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

/**
 * @author xiaodongw_3 Date: 05/01/2020
 * @version $Id$
 */
public class UserControllerTest extends BaseControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private UserController controller;

    @Test
    public void testList() throws Exception {
        MvcResult result = buildPost(mockMvc, "/list", null);
        assertNotNull(result);
        String response = result.getResponse().getContentAsString();
        LOGGER.warn("=======test list result======={}", response);
    }

    @Before
    public void before() throws Exception {
        setup(controller);
    }

    @After
    public void after() throws Exception {

    }
} 
