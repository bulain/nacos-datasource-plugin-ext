/*
 * Copyright 1999-2022 Bulain.
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

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.bulain.nacos.plugin.datasource.constants.DataSourceConstant;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.GroupCapacityMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

/**
 * The derby implementation of {@link GroupCapacityMapper}.
 *
 * @author bulain
 */
public class GroupCapacityMapperByPgSql extends AbstractMapper implements GroupCapacityMapper {
    
    @Override
    public String getDataSource() {
        return DataSourceConstant.PGSQL;
    }
    
    @Override
    public MapperResult selectGroupInfoBySize(MapperContext context) {
        String sql = "SELECT id, group_id FROM group_capacity WHERE id > ? OFFSET 0 LIMIT ?";
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.ID), context.getPageSize()));
    }
}

