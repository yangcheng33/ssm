package com.github.walker.basewf.auth.vo; 

import com.github.vita.ssm.common.vo.BasicVo;

public class RoleProd extends BasicVo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long roleId;
    private String prodCode;
    private String prodName;
    private String createUser;
    private java.sql.Timestamp createTime;


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

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
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


}

/*List columns as follows:
"id", "role_id", "prod_code", "prod_name", "create_user", "create_time"
*/