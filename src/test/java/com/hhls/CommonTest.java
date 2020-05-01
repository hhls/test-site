package com.hhls;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
    ({"classpath*:spring-*.xml"}) //加载配置文件
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上/
//控制事务，参见下一个实例
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
//@Transactional
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时
//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//------------
public class CommonTest {

    protected MockMvc mvc;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;

    public static String mock(MockMvc mvc, String uri, String json)
        throws Exception {
        return mvc.perform(post(uri, "json").characterEncoding("UTF-8")
                               .contentType(MediaType.APPLICATION_JSON)
                               .content(json.getBytes())).andReturn()
            .getResponse().getContentAsString();
    }
}
