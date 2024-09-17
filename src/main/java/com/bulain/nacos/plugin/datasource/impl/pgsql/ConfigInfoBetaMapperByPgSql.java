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
 * OFFSETations under the License.
 */

package com.bulain.nacos.plugin.datasource.impl.pgsql;

import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoBetaMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * The pgsql implementation of ConfigInfoBetaMapper.
 *
 * @author bulain
 **/

public class ConfigInfoBetaMapperByPgSql extends AbstractMapperByPgSql implements ConfigInfoBetaMapper {

    @Override
    public MapperResult findAllConfigInfoBetaForDumpAllFetchRows(MapperContext context) {
        int startRow = context.getStartRow();
        int pageSize = context.getPageSize();
        String sql = " SELECT t.id,data_id,group_id,tenant_id,app_name,content,md5,gmt_modified,beta_ips,encrypted_data_key "
                + " FROM ( SELECT id FROM config_info_beta  ORDER BY id OFFSET " + startRow + " LIMIT " + pageSize + " )"
                + "  g, config_info_beta t WHERE g.id = t.id ";
        List<Object> paramList = new ArrayList<>();
        paramList.add(startRow);
        paramList.add(pageSize);
        
        return new MapperResult(sql, paramList);
    }

    @Override
    public String getDataSource() {
        return DataSourceConstant.PGSQL;
    }
}
