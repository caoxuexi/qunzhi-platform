package com.lq.mybatisgeneratordemo.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminLoginParam {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
