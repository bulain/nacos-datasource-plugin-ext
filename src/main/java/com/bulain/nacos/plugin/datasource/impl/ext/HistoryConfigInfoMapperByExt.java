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

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.mapper.HistoryConfigInfoMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

/**
 * The ext implementation of ConfigInfoMapper.
 *
 * @author bulain
 **/

public abstract class HistoryConfigInfoMapperByExt extends AbstractMapperByExt implements HistoryConfigInfoMapper {

    @Override
    public MapperResult removeConfigHistory(MapperContext context) {
        String sql = "DELETE FROM his_config_info WHERE id IN( "
                + "SELECT id FROM his_config_info WHERE gmt_modified < ?)";
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.START_TIME)));
    }

    @Override
    public MapperResult pageFindConfigHistoryFetchRows(MapperContext context) {
        String sql =
                "SELECT nid,data_id,group_id,tenant_id,app_name,src_ip,src_user,op_type,gmt_create,gmt_modified FROM his_config_info "
                        + "WHERE data_id = ? AND group_id = ? AND tenant_id = ? ORDER BY nid DESC " + pageLimit(context);
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.DATA_ID),
                context.getWhereParameter(FieldConstant.GROUP_ID), context.getWhereParameter(FieldConstant.TENANT_ID)));
    }

    @Override
    public MapperResult findDeletedConfig(MapperContext context) {
        return new MapperResult(
                "SELECT data_id, group_id, tenant_id,gmt_modified,nid " +
                        "FROM his_config_info " +
                        "WHERE op_type = 'D' AND gmt_modified >= ? and nid > ? " +
                        "ORDER BY nid " + rowsLimit(context.getWhereParameter(FieldConstant.PAGE_SIZE)),
                CollectionUtils.list(context.getWhereParameter(FieldConstant.START_TIME),
                        context.getWhereParameter(FieldConstant.LAST_MAX_ID)));
    }
}
