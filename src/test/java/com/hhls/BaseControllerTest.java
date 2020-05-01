package com.hhls;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.HandlerMapping;


/**
 * @author xiaodongw_3 Date: 2020-05-01 Time: 下午3:36
 * @version $Id$
 */
public class BaseControllerTest extends CommonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerTest.class);
    protected MockMvc mockMvc;

    protected void setup(Object controller) {
        this.mockMvc = standaloneSetup(controller)
            .alwaysExpect(status().isOk()).build();
    }

    protected MvcResult buildGet(MockMvc mockMvc, String url) throws Exception {
        MvcResult mvcResult = null;
        LOGGER.info("url---{}", url);
        //RbacUserContext.initLoginInfo(rbacLoginInfo());
        mvcResult = mockMvc.perform(
            get(url)
                //.header(HttpHeaders.AUTHORIZATION, getBaseAuthrizationString())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .requestAttr(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE,
                             Sets.newHashSet(MediaType.APPLICATION_JSON))
        )
            .andExpect(status().is(HttpStatus.OK.value()))
            .andReturn();
        mvcResult.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
        return mvcResult;
    }

    protected MvcResult buildPost(MockMvc mockMvc, String url, String reqJson, String contentType, boolean needLogin)
        throws Exception {
        reqJson = StringUtils.defaultString(reqJson, "{}");
        LOGGER.info("url---{}\nreqParam:{}", url, reqJson);
        long start = System.currentTimeMillis();
        MvcResult mvcResult = null;
        MockHttpServletRequestBuilder builder = post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf8")
            .header("Content-Type", contentType)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            //.requestAttr(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, Sets.newHashSet(MediaType.APPLICATION_JSON))
            .content(reqJson);
        if (needLogin) {
            //RbacUserContext.initLoginInfo(rbacLoginInfo());
            //builder.header(HttpHeaders.AUTHORIZATION, getBaseAuthrizationString());
        }
        mvcResult = mockMvc.perform(builder)
            .andExpect(status().is(HttpStatus.OK.value()))
            .andReturn();
        mvcResult.getResponse().addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        LOGGER.error("{}接口请求耗时{}ms", url, (System.currentTimeMillis() - start));
        return mvcResult;
    }

    protected MvcResult buildPost(MockMvc mockMvc, String url, String reqJson) throws Exception {
        return buildPost(mockMvc, url, reqJson, MediaType.APPLICATION_JSON_VALUE, true);
    }

    protected MvcResult buildPost(MockMvc mockMvc, String url, Object param) throws Exception {
        String reqJson;
        reqJson = null != param ? new ObjectMapper().writeValueAsString(param) : null;
        return buildPost(mockMvc, url, reqJson, MediaType.APPLICATION_JSON_VALUE, true);
    }

    protected MvcResult buildPost(MockMvc mockMvc, String url) throws Exception {
        return buildPost(mockMvc, url, null, MediaType.APPLICATION_JSON_VALUE, true);
    }

    protected MvcResult buildPostNotLogin(MockMvc mockMvc, String url, Object param) throws Exception {
        String reqJson = null != param ? JSON.toJSONString(param) : null;
        return buildPost(mockMvc, url, reqJson, MediaType.APPLICATION_JSON_VALUE, false);
    }

    protected MvcResult buildPostNotLogin(MockMvc mockMvc, String url) throws Exception {
        return buildPostNotLogin(mockMvc, url, null);
    }

}

