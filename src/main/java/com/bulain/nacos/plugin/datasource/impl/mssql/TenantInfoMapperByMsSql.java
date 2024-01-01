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

package com.bulain.nacos.plugin.datasource.impl.mssql;

import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.TenantInfoMapper;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;

/**
 * The mssql implementation of TenantInfoMapper.
 *
 * @author bulain
 **/

public class TenantInfoMapperByMsSql extends AbstractMapper implements TenantInfoMapper {

    @Override
    public String getDataSource() {
        return DataSourceConstant.MSSQL;
    }
}
