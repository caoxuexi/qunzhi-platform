package pojo.vo;

import lombok.Data;

/**
 * @author Cao Study
 * @description <h1>UserVO</h1>
 * @date 2021-10-16 19:28
 */
@Data
public class UserLoginVO {
    private String email;
    private String nickname;
    private String token;
}
