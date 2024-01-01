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

import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoAggrMapper;

/**
 * The oracle implementation of ConfigInfoAggrMapper.
 *
 * @author bulain
 **/
public class ConfigInfoAggrMapperByOracle extends AbstractMapper implements ConfigInfoAggrMapper {

    @Override
    public String findConfigInfoAggrByPageFetchRows(int startRow, int pageSize) {
        return "SELECT data_id,group_id,tenant_id,datum_id,app_name,content FROM config_info_aggr WHERE data_id=? AND "
                + "group_id=? AND tenant_id=? ORDER BY datum_id OFFSET " + startRow + " ROWS FETCH NEXT " + pageSize
                + " ROWS ONLY";
    }

    @Override
    public String getDataSource() {
        return DataSourceConstant.ORACLE;
    }
}
