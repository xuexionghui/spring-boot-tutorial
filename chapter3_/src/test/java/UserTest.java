import com.junlaninfo.Application;
import com.junlaninfo.dao.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by 辉 on 2020/3/4.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class UserTest {
    private MockMvc mvc;

    @Autowired
    UserDao  userDao;

    @Autowired
    private WebApplicationContext  webApplicationContext;

    /*
       初始化环境
     */
    @Before
    public   void   setUp(){
        mvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*
     测试添加
     */

    public void testInsert() throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                        .post("/addUser")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"user1\",\"sex\":1,\"birthday\":\"2000-05-21\"}")
        )
//        .andExpect(status().isOk());
//        .andExpect(content().string("hello"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    /*
     测试修改
     */
    @Test
    public void testUpdate() throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                        .post("/updateUser")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"老师胡0\",\"id\":8,\"sex\":0,\"birthday\":\"2020-05-21\"}")
        )
//        .andExpect(status().isOk());
//        .andExpect(content().string("hello"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
