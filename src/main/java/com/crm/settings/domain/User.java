package com.crm.settings.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    @TableId("id")
    private String id;
    private String loginAct;
    private String name;
    @JsonIgnore
    private String loginPwd;
    private String email;
    private String expireTime;//char(19)
    private String lockState;//lock:'0' | unlock:'1'
    private String deptno;
    private String allowIps;
    private String createTime;//char(19)
    private String createBy;
    private String editTime;//char(19)
    private String editBy;
}

