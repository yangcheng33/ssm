package com.github.walker.basewf.auth.vo; 

import com.github.vita.ssm.common.vo.BasicVo;

import java.sql.Timestamp;

public class UserRole extends BasicVo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private Long roleId;
    private String createUser;
    private java.sql.Timestamp createTime;

    public Long getId() {
        return id;
   }

    public void setId(Long id) {
        this.id = id;
   }

    public Long getUserId() {
        return userId;
   }

    public void setUserId(Long userId) {
        this.userId = userId;
   }

    public Long getRoleId() {
        return roleId;
   }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
   }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}

/*List columns as follows:
"id", "user_id", "role_id"
*/