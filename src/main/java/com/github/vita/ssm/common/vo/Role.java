package com.github.walker.basewf.auth.vo; 

import com.github.vita.ssm.common.vo.BasicVo;

public class Role extends BasicVo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String remark;
    private Short canViewAll; //是否可查看所有人创建的业务数据
    private String createUser;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;


    public Long getId() {
        return id;
   }

    public void setId(Long id) {
        this.id = id;
   }

    public String getName() {
        return name;
   }

    public void setName(String name) {
        this.name = name;
   }

    public String getRemark() {
        return remark;
   }

    public void setRemark(String remark) {
        this.remark = remark;
   }

    public Short getCanViewAll() {
        return canViewAll;
    }

    public void setCanViewAll(Short canViewAll) {
        this.canViewAll = canViewAll;
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

}

/*List columns as follows:
"id", "name", "remark", "create_user", "create_time", "update_time"
*/