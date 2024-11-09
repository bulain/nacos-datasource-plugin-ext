package com.bulain.nacos.plugin.datasource.impl.mssql;

import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;
import com.bulain.nacos.plugin.datasource.enums.mssql.TrustedMssqlFunctionEnum;

/**
 * The abstract mssql mapper contains CRUD methods.
 *
 * @author bulain
 **/
public abstract class AbstractMapperByMsSql extends AbstractMapper {

    @Override
    public String getFunction(String functionName) {
        return TrustedMssqlFunctionEnum.getFunctionByName(functionName);
    }


    @Override
    public String getDataSource() {
        return DataSourceConstant.MSSQL;
    }

}
