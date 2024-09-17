package com.bulain.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.plugin.datasource.enums.mysql.TrustedMysqlFunctionEnum;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;

/**
 * The abstract mysql mapper contains CRUD methods.
 *
 * @author bulain
 **/
public abstract class AbstractMapperByPgsql extends AbstractMapper {

    @Override
    public String getFunction(String functionName) {
        return TrustedMysqlFunctionEnum.getFunctionByName(functionName);
    }

}
