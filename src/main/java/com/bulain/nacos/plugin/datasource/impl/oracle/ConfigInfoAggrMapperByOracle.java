/*
 * Copyright 1999-2024 Bulain.
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

package com.bulain.nacos.plugin.datasource.impl.oracle;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoAggrMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import java.util.List;

/**
 * The oracle implementation of ConfigInfoAggrMapper.
 *
 * @author bulain
 **/
public class ConfigInfoAggrMapperByOracle extends AbstractMapper implements ConfigInfoAggrMapper {
    
    @Override
    public MapperResult findConfigInfoAggrByPageFetchRows(MapperContext context) {
        final Integer startRow = context.getStartRow();
        final Integer pageSize = context.getPageSize();
        final String dataId = (String) context.getWhereParameter(FieldConstant.DATA_ID);
        final String groupId = (String) context.getWhereParameter(FieldConstant.GROUP_ID);
        final String tenantId = (String) context.getWhereParameter(FieldConstant.TENANT_ID);
        
        String sql =
                "SELECT data_id,group_id,tenant_id,datum_id,app_name,content FROM config_info_aggr WHERE data_id=? AND "
                        + "group_id=? AND tenant_id=? ORDER BY datum_id OFFSET " + startRow + " ROWS FETCH NEXT "
                        + pageSize + " ROWS ONLY";
        List<Object> paramList = CollectionUtils.list(dataId, groupId, tenantId);
        return new MapperResult(sql, paramList);
    }
    
    @Override
    public String getDataSource() {
        return DataSourceConstant.ORACLE;
    }
}
