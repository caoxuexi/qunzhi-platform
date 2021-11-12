package com.xidian.qunzhi;

import com.xidian.qunzhi.pojo.dto.UserLoginDTO;
import com.xidian.qunzhi.pojo.dto.UserRegistDTO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author Cao Study
 * @description <h1>TestLogin</h1>
 * @date 2021-11-07 20:47
 */
@Slf4j
@RunWith(SpringRunner.class)
//因为引入了websocket，所以需要指定webEnvironment，不然测试通不过打包会失败
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestLogin {

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void loginTest() throws Exception {
//        String url="http://222.25.188.1:48888/user/login";
//        UserLoginDTO userLoginDTO=new UserLoginDTO();
//        userLoginDTO.setPassword("123456");
//        for (Integer i=1;i<5000;i++){
//            userLoginDTO.setEmail("dragon"+i.toString()+"@xdu.com");
//            restTemplate.postForEntity(url,userLoginDTO, UserLoginVO.class);
//        }
    }
}
