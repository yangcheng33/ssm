package org.sample.ssm.common.dal;

import org.apache.ibatis.jdbc.SQL;
import org.sample.ssm.common.po.AppDO;

public class AppDOSqlProvider {

    public String insertSelective(AppDO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("central_app");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getVersion() != null) {
            sql.VALUES("version", "#{version,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.VALUES("os", "#{os}");
        }
        
        if (record.getGitUrl() != null) {
            sql.VALUES("git_url", "#{gitUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getGitBranch() != null) {
            sql.VALUES("git_branch", "#{gitBranch,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.VALUES("creator", "#{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getGmtCreate() != null) {
            sql.VALUES("gmt_create", "#{gmtCreate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifier() != null) {
            sql.VALUES("modifier", "#{modifier,jdbcType=VARCHAR}");
        }
        
        if (record.getGmtModified() != null) {
            sql.VALUES("gmt_modified", "#{gmtModified,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AppDO record) {
        SQL sql = new SQL();
        sql.UPDATE("central_app");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getOs() != null) {
            sql.SET("os = #{os}");
        }
        
        if (record.getGitUrl() != null) {
            sql.SET("git_url = #{gitUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getGitBranch() != null) {
            sql.SET("git_branch = #{gitBranch,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getGmtCreate() != null) {
            sql.SET("gmt_create = #{gmtCreate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifier() != null) {
            sql.SET("modifier = #{modifier,jdbcType=VARCHAR}");
        }
        
        if (record.getGmtModified() != null) {
            sql.SET("gmt_modified = #{gmtModified,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}