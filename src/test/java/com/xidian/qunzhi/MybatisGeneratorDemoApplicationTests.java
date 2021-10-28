package com.xidian.qunzhi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//因为引入了websocket，所以需要指定webEnvironment，不然测试通不过打包会失败、
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MybatisGeneratorDemoApplicationTests {

    @Test
    void contextLoads() {
    }

}
