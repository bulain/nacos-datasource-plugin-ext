/*
 * Copyright 1999-2025 Bulain.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bulain.nacos.plugin.datasource.impl.ext;

import com.alibaba.nacos.plugin.datasource.enums.mysql.TrustedMysqlFunctionEnum;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;
import com.bulain.nacos.plugin.datasource.enums.dmsql.TrustedDmsqlFunctionEnum;
import com.bulain.nacos.plugin.datasource.enums.kbsql.TrustedKbsqlFunctionEnum;
import com.bulain.nacos.plugin.datasource.enums.oracle.TrustedOracleFunctionEnum;
import com.bulain.nacos.plugin.datasource.enums.pgsql.TrustedPgsqlFunctionEnum;

/**
 * The abstract ext mapper contains CRUD methods.
 *
 * @author bulain
 **/
public abstract class AbstractMapperByExt extends AbstractMapper {

    @Override
    public String getFunction(String functionName) {
        String dataSource = getDataSource();
        if (DataSourceConstant.ORACLE.equals(dataSource)) {
            return TrustedOracleFunctionEnum.getFunctionByName(functionName);
        } else if (DataSourceConstant.MSSQL.equals(dataSource)) {
            return TrustedDmsqlFunctionEnum.getFunctionByName(functionName);
        } else if (DataSourceConstant.DMSQL.equals(dataSource)) {
            return TrustedDmsqlFunctionEnum.getFunctionByName(functionName);
        } else if (DataSourceConstant.PGSQL.equals(dataSource)) {
            return TrustedPgsqlFunctionEnum.getFunctionByName(functionName);
        } else if (DataSourceConstant.KBSQL.equals(dataSource)) {
            TrustedKbsqlFunctionEnum.getFunctionByName(functionName);
        }
        return TrustedMysqlFunctionEnum.getFunctionByName(functionName);
    }

    protected String pageLimit(MapperContext context) {
        String dataSource = getDataSource();
        int startRow = context.getStartRow();
        int pageSize = context.getPageSize();
        if (DataSourceConstant.ORACLE.equals(dataSource)
                || DataSourceConstant.MSSQL.equals(dataSource)
                || DataSourceConstant.DMSQL.equals(dataSource)) {
            return " OFFSET " + startRow + " ROWS FETCH NEXT " + pageSize + " ROWS ONLY";
        } else if (DataSourceConstant.PGSQL.equals(dataSource)
                || DataSourceConstant.KBSQL.equals(dataSource)) {
            return " LIMIT " + pageSize + " OFFSET " + startRow;
        }
        return " LIMIT " + startRow + "," + pageSize;
    }

    protected String rowsLimit(Object pageSize) {
        String dataSource = getDataSource();
        if (DataSourceConstant.ORACLE.equals(dataSource)
                || DataSourceConstant.MSSQL.equals(dataSource)
                || DataSourceConstant.DMSQL.equals(dataSource)) {
            return " OFFSET 0 ROWS FETCH NEXT " + pageSize + " ROWS ONLY";
        } else if (DataSourceConstant.PGSQL.equals(dataSource)
                || DataSourceConstant.KBSQL.equals(dataSource)) {
            return " LIMIT " + pageSize;
        }
        return " LIMIT " + pageSize;
    }

}
