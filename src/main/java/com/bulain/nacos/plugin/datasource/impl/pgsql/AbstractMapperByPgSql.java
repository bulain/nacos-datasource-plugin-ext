package com.bulain.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.plugin.datasource.enums.mysql.TrustedMysqlFunctionEnum;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;

import java.util.List;

/**
 * The abstract mysql mapper contains CRUD methods.
 *
 * @author bulain
 **/
public abstract class AbstractMapperByPgSql extends AbstractMapper {

    @Override
    public String select(List<String> columns, List<String> where) {
        String sql = super.select(columns, where);
        if (sql.contains("`")) {
            sql = sql.replaceAll("`", "");
        }
        return sql;
    }

    @Override
    public String getFunction(String functionName) {
        return TrustedMysqlFunctionEnum.getFunctionByName(functionName);
    }

}
