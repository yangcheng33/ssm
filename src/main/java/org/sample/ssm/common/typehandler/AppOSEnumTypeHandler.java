package org.sample.ssm.common.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.sample.ssm.common.codetable.AppOSEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis枚举类型转换类.
 *
 * @author Yang Cheng
 * @version v 0.1 2017-07-03 17:08
 */
@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(AppOSEnum.class)
public class AppOSEnumTypeHandler implements TypeHandler<AppOSEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, AppOSEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setByte(i, parameter.getCode());
    }

    @Override
    public AppOSEnum getResult(ResultSet rs, String columnName) throws SQLException {
        return AppOSEnum.getEnumByCode(rs.getByte(columnName));
    }

    @Override
    public AppOSEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return AppOSEnum.getEnumByCode(rs.getByte(columnIndex));
    }

    @Override
    public AppOSEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return AppOSEnum.getEnumByCode(cs.getByte(columnIndex));
    }
}
