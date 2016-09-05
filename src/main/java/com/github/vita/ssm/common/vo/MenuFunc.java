package com.github.vita.ssm.common.vo;

import com.github.vita.ssm.common.vo.BasicVo;

public class MenuFunc extends BasicVo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long menuId;
    private String code;
    private String name;
    private java.sql.Timestamp createTime;
    private String createUser;
    private java.sql.Timestamp updateTime;


    public Long getId() {
        return id;
   }

    public void setId(Long id) {
        this.id = id;
   }

    public Long getMenuId() {
        return menuId;
   }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
   }

    public String getCode() {
        return code;
   }

    public void setCode(String code) {
        this.code = code;
   }

    public String getName() {
        return name;
   }

    public void setName(String name) {
        this.name = name;
   }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
   }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
   }

    public String getCreateUser() {
        return createUser;
   }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
   }

    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
   }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
   }

}

/*List columns as follows:
"id", "menu_id", "code", "name", "create_time", "create_user", "update_time"
*/