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
import com.alibaba.nacos.common.utils.NamespaceUtil;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.plugin.datasource.constants.FieldConstant;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ext implementation of ConfigInfoMapper.
 *
 * @author bulain
 **/

public abstract class ConfigInfoMapperByExt extends AbstractMapperByExt implements ConfigInfoMapper {

    @Override
    public MapperResult findConfigInfoByAppFetchRows(MapperContext context) {
        final String appName = (String) context.getWhereParameter(FieldConstant.APP_NAME);
        final String tenantId = (String) context.getWhereParameter(FieldConstant.TENANT_ID);

        String sql =
                "SELECT id,data_id,group_id,tenant_id,app_name,content FROM config_info WHERE tenant_id LIKE ? AND "
                        + "app_name = ? ORDER BY id " + pageLimit(context);

        return new MapperResult(sql, CollectionUtils.list(tenantId, appName));
    }

    @Override
    public MapperResult getTenantIdList(MapperContext context) {

        return new MapperResult(
                "SELECT tenant_id FROM config_info WHERE tenant_id != '" + NamespaceUtil.getNamespaceDefaultId()
                        + "' GROUP BY tenant_id ORDER BY tenant_id " + pageLimit(context), Collections.emptyList());
    }

    @Override
    public MapperResult getGroupIdList(MapperContext context) {

        return new MapperResult(
                "SELECT group_id FROM config_info WHERE tenant_id ='" + NamespaceUtil.getNamespaceDefaultId()
                        + "' GROUP BY group_id ORDER BY group_id" + pageLimit(context), Collections.emptyList());
    }

    @Override
    public MapperResult findAllConfigKey(MapperContext context) {

        String sql = " SELECT data_id,group_id,app_name "
                + " FROM config_info WHERE tenant_id LIKE ? ORDER BY id " + pageLimit(context);
        return new MapperResult(sql,
                CollectionUtils.list(context.getWhereParameter(FieldConstant.TENANT_ID)));
    }

    @Override
    public MapperResult findAllConfigInfoBaseFetchRows(MapperContext context) {

        return new MapperResult(
                "SELECT t.id,data_id,group_id,content,md5 FROM config_info ORDER BY id " + pageLimit(context), Collections.emptyList());
    }

    @Override
    public MapperResult findAllConfigInfoFragment(MapperContext context) {

        return new MapperResult(
                "SELECT id,data_id,group_id,tenant_id,app_name,content,md5,gmt_modified,type FROM config_info WHERE id > ? "
                        + "ORDER BY id ASC " + pageLimit(context),
                CollectionUtils.list(context.getWhereParameter(FieldConstant.ID)));
    }

    @Override
    public MapperResult findChangeConfigFetchRows(MapperContext context) {
        final String tenant = (String) context.getWhereParameter(FieldConstant.TENANT);
        final String dataId = (String) context.getWhereParameter(FieldConstant.DATA_ID);
        final String group = (String) context.getWhereParameter(FieldConstant.GROUP_ID);
        final String appName = (String) context.getWhereParameter(FieldConstant.APP_NAME);

        final Timestamp startTime = (Timestamp) context.getWhereParameter(FieldConstant.START_TIME);
        final Timestamp endTime = (Timestamp) context.getWhereParameter(FieldConstant.END_TIME);

        List<Object> paramList = new ArrayList<>();

        final String sqlFetchRows = "SELECT id,data_id,group_id,tenant_id,app_name,content,type,md5,gmt_modified FROM"
                + " config_info WHERE ";
        String where = " 1=1 ";

        if (!StringUtils.isBlank(dataId)) {
            where += " AND data_id LIKE ? ";
            paramList.add(dataId);
        }
        if (!StringUtils.isBlank(group)) {
            where += " AND group_id LIKE ? ";
            paramList.add(group);
        }

        if (!StringUtils.isBlank(tenant)) {
            where += " AND tenant_id = ? ";
            paramList.add(tenant);
        }

        if (!StringUtils.isBlank(appName)) {
            where += " AND app_name = ? ";
            paramList.add(appName);
        }
        if (startTime != null) {
            where += " AND gmt_modified >=? ";
            paramList.add(startTime);
        }
        if (endTime != null) {
            where += " AND gmt_modified <=? ";
            paramList.add(endTime);
        }
        return new MapperResult(
                sqlFetchRows + where + " ORDER BY id " + pageLimit(context), paramList);
    }

    @Override
    public MapperResult listGroupKeyMd5ByPageFetchRows(MapperContext context) {

        return new MapperResult(" SELECT t.id,data_id,group_id,tenant_id,app_name,type,md5,gmt_modified "
                + " FROM config_info ORDER BY id " + pageLimit(context), Collections.emptyList());
    }

    @Override
    public MapperResult findConfigInfoBaseLikeFetchRows(MapperContext context) {
        final String tenant = (String) context.getWhereParameter(FieldConstant.TENANT);
        final String dataId = (String) context.getWhereParameter(FieldConstant.DATA_ID);
        final String group = (String) context.getWhereParameter(FieldConstant.GROUP_ID);

        List<Object> paramList = new ArrayList<>();
        final String sqlFetchRows = "SELECT id,data_id,group_id,tenant_id,content FROM config_info WHERE ";
        String where = " 1=1 AND tenant_id='" + NamespaceUtil.getNamespaceDefaultId() + "' ";
        if (!StringUtils.isBlank(dataId)) {
            where += " AND data_id LIKE ? ";
            paramList.add(dataId);
        }
        if (!StringUtils.isBlank(group)) {
            where += " AND group_id LIKE ? ";
            paramList.add(group);
        }
        if (!StringUtils.isBlank(tenant)) {
            where += " AND content LIKE ? ";
            paramList.add(tenant);
        }
        return new MapperResult(
                sqlFetchRows + where + " ORDER BY id " + pageLimit(context), paramList);
    }

    @Override
    public MapperResult findConfigInfo4PageFetchRows(MapperContext context) {
        final String tenantId = (String) context.getWhereParameter(FieldConstant.TENANT_ID);
        final String dataId = (String) context.getWhereParameter(FieldConstant.DATA_ID);
        final String group = (String) context.getWhereParameter(FieldConstant.GROUP_ID);
        final String appName = (String) context.getWhereParameter(FieldConstant.APP_NAME);
        final String content = (String) context.getWhereParameter(FieldConstant.CONTENT);

        List<Object> paramList = new ArrayList<>();

        final String sql = "SELECT id,data_id,group_id,tenant_id,app_name,content,type FROM config_info";
        StringBuilder where = new StringBuilder(" WHERE ");
        where.append(" tenant_id=? ");
        paramList.add(tenantId);
        if (StringUtils.isNotBlank(dataId)) {
            where.append(" AND data_id=? ");
            paramList.add(dataId);
        }
        if (StringUtils.isNotBlank(group)) {
            where.append(" AND group_id=? ");
            paramList.add(group);
        }

        if (StringUtils.isNotBlank(appName)) {
            where.append(" AND app_name=? ");
            paramList.add(appName);
        }
        if (!StringUtils.isBlank(content)) {
            where.append(" AND content LIKE ? ");
            paramList.add(content);
        }
        return new MapperResult(
                sql + where + " ORDER BY id " + pageLimit(context), paramList);
    }

    @Override
    public MapperResult findConfigInfoBaseByGroupFetchRows(MapperContext context) {
        return new MapperResult(
                "SELECT id,data_id,group_id,content FROM config_info WHERE group_id=? AND tenant_id=? ORDER BY id " + pageLimit(context),
                CollectionUtils.list(context.getWhereParameter(FieldConstant.GROUP_ID),
                        context.getWhereParameter(FieldConstant.TENANT_ID)));
    }

    @Override
    public MapperResult findConfigInfoLike4PageFetchRows(MapperContext context) {

        final String tenantId = (String) context.getWhereParameter(FieldConstant.TENANT_ID);
        final String dataId = (String) context.getWhereParameter(FieldConstant.DATA_ID);
        final String group = (String) context.getWhereParameter(FieldConstant.GROUP_ID);
        final String appName = (String) context.getWhereParameter(FieldConstant.APP_NAME);
        final String content = (String) context.getWhereParameter(FieldConstant.CONTENT);

        List<Object> paramList = new ArrayList<>();

        final String sqlFetchRows = "SELECT id,data_id,group_id,tenant_id,app_name,content,encrypted_data_key FROM config_info";
        StringBuilder where = new StringBuilder(" WHERE ");
        where.append(" tenant_id LIKE ? ");
        paramList.add(tenantId);
        if (!StringUtils.isBlank(dataId)) {
            where.append(" AND data_id LIKE ? ");
            paramList.add(dataId);
        }
        if (!StringUtils.isBlank(group)) {
            where.append(" AND group_id LIKE ? ");
            paramList.add(group);
        }
        if (!StringUtils.isBlank(appName)) {
            where.append(" AND app_name = ? ");
            paramList.add(appName);
        }
        if (!StringUtils.isBlank(content)) {
            where.append(" AND content LIKE ? ");
            paramList.add(content);
        }
        String sql =
                sqlFetchRows + where + " ORDER BY id " + pageLimit(context);
        return new MapperResult(sql, paramList);
    }

    @Override
    public MapperResult findAllConfigInfoFetchRows(MapperContext context) {
        return new MapperResult(" SELECT t.id,data_id,group_id,tenant_id,app_name,content,md5 "
                + " FROM config_info  WHERE tenant_id LIKE ? ORDER BY id " + pageLimit(context),
                CollectionUtils.list(context.getWhereParameter(FieldConstant.TENANT_ID)));
    }

    @Override
    public MapperResult findChangeConfig(MapperContext context) {
        String sql =
                "SELECT id, data_id, group_id, tenant_id, app_name, content, gmt_modified, encrypted_data_key " +
                        "FROM config_info " +
                        "WHERE gmt_modified >= ? and id > ? " +
                        "ORDER BY id " +  rowsLimit(context.getWhereParameter(FieldConstant.PAGE_SIZE));
        return new MapperResult(sql, CollectionUtils.list(context.getWhereParameter(FieldConstant.START_TIME),
                context.getWhereParameter(FieldConstant.LAST_MAX_ID)));
    }
}
