package com.github.walker.basewf.auth.vo; 

import com.github.vita.ssm.common.vo.BasicVo;

public class User extends BasicVo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String acctNo;
    private String password;
    private String name;

    private Integer state;
    private String gender;
    private String createUser;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;


    public Long getId() {
        return id;
   }

    public void setId(Long id) {
        this.id = id;
   }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getPassword() {
        return password;
   }

    public void setPassword(String password) {
        this.password = password;
   }

    public String getName() {
        return name;
   }

    public void setName(String name) {
        this.name = name;
   }

    public String getGender() {
        return gender;
   }

    public void setGender(String gender) {
        this.gender = gender;
   }

    public String getCreateUser() {
        return createUser;
   }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
   }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
   }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
   }

    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
   }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
   }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

/*List columns as follows:
"id", "user_id", "password", "name", "gender", "create_user", "create_time", 
"update_time"
*/