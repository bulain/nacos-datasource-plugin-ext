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

package com.bulain.nacos.plugin.datasource.impl.mssql;

import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoGrayMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import java.util.Collections;

/**
 * The mssql implementation of ConfigInfoGrayMapper.
 *
 * @author bulain
 **/
public class ConfigInfoGrayMapperByMsSql extends AbstractMapperByMsSql implements ConfigInfoGrayMapper {

    @Override
    public MapperResult findAllConfigInfoGrayForDumpAllFetchRows(MapperContext context) {
        int startRow =  context.getStartRow();
        int pageSize =  context.getPageSize();
        String sql = "SELECT id,data_id,group_id,tenant_id,gray_name,gray_rule,app_name,content,md5,gmt_modified "
                + " FROM config_info_gray ORDER BY id OFFSET " + startRow + " ROWS FETCH NEXT "
                + pageSize + " ROWS ONLY";
        return new MapperResult(sql, Collections.emptyList());
    }

}
