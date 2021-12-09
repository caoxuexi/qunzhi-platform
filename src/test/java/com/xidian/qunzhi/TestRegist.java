package com.xidian.qunzhi;

import com.xidian.qunzhi.pojo.dto.UserRegistDTO;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.MD5Utils;
import lombok.extern.slf4j.*;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author beiqixing
 * @Description
 * @Date 2021/11/3 10:21
 */
@Slf4j
@RunWith(SpringRunner.class)
//因为引入了websocket，所以需要指定webEnvironment，不然测试通不过打包会失败
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRegist {

    @Autowired
    UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void registTest() throws Exception {
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("H:m:s");
//        System.out.println(simpleDateFormat.format(new Date()));
//        for(int i=0;i<100000;i++){
//            restTemplate.getForObject("http://222.25.188.1:58081/test/a",String.class);
//        }
//        System.out.println(simpleDateFormat.format(new Date()));


//        UserRegistDTO registDTO = new UserRegistDTO();
//        registDTO.setPassword("123456");
//        String password = MD5Utils.getMD5Str(registDTO.getPassword());
//        for (int i=968601  ;i<1000000;i++) {
//            log.info("正在注册第"+i+"个用户");
//            registDTO.setRealname("Dragon"+i);
//            registDTO.setEmail("dragon"+i+"@xdu.com");
//            registDTO.setNickname(registDTO.getRealname());
//            userService.register(registDTO, password);
//        }
    }
}
