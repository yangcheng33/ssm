package org.sample.ssm.common.dal;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.sample.ssm.common.po.AppDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDOMapper {
    @Delete({ "delete from central_app", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Long id);

    @Insert({ "insert into central_app (name, version, ", "description, os, ", "git_url, git_branch, ",
              "creator, gmt_create, ", "modifier, gmt_modified)",
              "values (#{name,jdbcType=VARCHAR}, #{version,jdbcType=VARCHAR}, ",
              "#{description,jdbcType=VARCHAR}, #{os,jdbcType=TINYINT}, ",
              "#{gitUrl,jdbcType=VARCHAR}, #{gitBranch,jdbcType=VARCHAR}, ",
              "#{creator,jdbcType=VARCHAR}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
              "#{modifier,jdbcType=VARCHAR}, #{gmtModified,jdbcType=TIMESTAMP})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(AppDO record);

    @InsertProvider(type = AppDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(AppDO record);

    @Select({ "select", "id, name, version, description, os, git_url, git_branch, creator, gmt_create, ",
              "modifier, gmt_modified", "from central_app", "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
               @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
               @Result(column = "version", property = "version", jdbcType = JdbcType.VARCHAR),
               @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
               @Result(column = "git_url", property = "gitUrl", jdbcType = JdbcType.VARCHAR),
               @Result(column = "git_branch", property = "gitBranch", jdbcType = JdbcType.VARCHAR),
               @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
               @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP),
               @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
               @Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP) })
    AppDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = AppDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AppDO record);

    @Update({ "update central_app", "set name = #{name,jdbcType=VARCHAR},", "version = #{version,jdbcType=VARCHAR},",
              "description = #{description,jdbcType=VARCHAR},", "git_url = #{gitUrl,jdbcType=VARCHAR},",
              "git_branch = #{gitBranch,jdbcType=VARCHAR},", "creator = #{creator,jdbcType=VARCHAR},",
              "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},", "modifier = #{modifier,jdbcType=VARCHAR},",
              "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(AppDO record);

    // user-defined
    @Select({ "select * ", "from central_app", "order by os, name" })
    List<AppDO> selectAllApp();
}