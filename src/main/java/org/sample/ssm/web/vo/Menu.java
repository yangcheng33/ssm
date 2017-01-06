package org.sample.ssm.web.vo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class Menu extends BasicVo {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long pid;
    private String code;
    private String name;
    private String url;
    private Short orderNo;
    private java.sql.Timestamp createTime;
    private String createUser;
    private java.sql.Timestamp updateTime;

    private String pname;       // 父菜单名称
    private List<Menu> subMenus;// 子菜单

    private String funcItems;   // 功能项，格式为："功能1的id:功能1的名称;功能2的id:功能2的名称;..."
    private Map<String, String> funcIdNameMap;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Short orderNo) {
        this.orderNo = orderNo;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getFuncItems() {
        return funcItems;
    }

    public void setFuncItems(String funcItems) {
        this.funcItems = funcItems;
    }

    public Map<String, String> getFuncIdNameMap() {
        return funcIdNameMap;
    }

    public void setFuncIdNameMap(Map<String, String> funcIdNameMap) {
        this.funcIdNameMap = funcIdNameMap;
    }
}

