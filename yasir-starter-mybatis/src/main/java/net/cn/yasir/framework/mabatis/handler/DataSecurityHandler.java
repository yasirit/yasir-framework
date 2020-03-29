package net.cn.yasir.framework.mabatis.handler;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义转换器 - 数据加(解)密
 * @author yasir
 * @version 1.0.0
 * @desc
 * @date 2020-03-29
 */
public class DataSecurityHandler extends BaseTypeHandler<Object> {

    private static SymmetricCrypto aes = null;

    static {
        //密钥
        String key = "data-Security";
        //构建
        aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, aes.encryptHex((String) parameter));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return aes.decryptStr(columnValue, CharsetUtil.CHARSET_UTF_8);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return aes.decryptStr(columnValue, CharsetUtil.CHARSET_UTF_8);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return aes.decryptStr(columnValue, CharsetUtil.CHARSET_UTF_8);
    }

}
