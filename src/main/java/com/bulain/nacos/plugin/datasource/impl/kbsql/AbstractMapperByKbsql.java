package com.bulain.nacos.plugin.datasource.impl.kbsql;

import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;
import com.bulain.nacos.plugin.datasource.enums.kbsql.TrustedKbsqlFunctionEnum;

/**
 * The abstract kbsql mapper contains CRUD methods.
 *
 * @author bulain
 **/
public abstract class AbstractMapperByKbsql extends AbstractMapper {

    @Override
    public String getFunction(String functionName) {
        return TrustedKbsqlFunctionEnum.getFunctionByName(functionName);
    }

    @Override
    public String getDataSource() {
        return DataSourceConstant.KBSQL;
    }

}
