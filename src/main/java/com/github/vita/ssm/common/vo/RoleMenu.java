package com.github.walker.basewf.auth.vo;

import com.github.vita.ssm.common.vo.BasicVo;

import java.sql.Timestamp;

public class RoleMenu extends BasicVo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long roleId;
    private Long menuId;
    private String funcIds;

    private String createUser;
    private java.sql.Timestamp createTime;

    private String menuCode;
    private String menuName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }



    public String getFuncIds() {
        return funcIds;
    }

    public void setFuncIds(String funcIds) {
        this.funcIds = funcIds;
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


    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


}

/*List columns as follows:
"id", "role_id", "menu_id", "func_items", "create_user", "create_time", "update_time"
*/