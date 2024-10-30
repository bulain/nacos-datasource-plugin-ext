package com.bulain.nacos.plugin.datasource.impl.mssql;

import com.alibaba.nacos.plugin.datasource.enums.mysql.TrustedMysqlFunctionEnum;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;

import java.util.List;

/**
 * The abstract mssql mapper contains CRUD methods.
 *
 * @author bulain
 **/
public abstract class AbstractMapperByMsSql extends AbstractMapper {

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


    @Override
    public String getDataSource() {
        return DataSourceConstant.MSSQL;
    }

}
