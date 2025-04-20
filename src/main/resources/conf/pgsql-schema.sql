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

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info   */
/******************************************/
create table config_info (
  id bigint not null generated always as identity,
  data_id varchar(255) not null,
  group_id varchar(128),
  content text not null,
  md5 varchar(32),
  gmt_create date not null default localtimestamp,
  gmt_modified date not null default localtimestamp,
  src_user text,
  src_ip varchar(50),
  app_name varchar(128),
  tenant_id varchar(128) default '',
  c_desc varchar(256),
  c_use varchar(64),
  effect varchar(64),
  type varchar(64),
  c_schema text,
  encrypted_data_key text not null,
  primary key (id)
) ;
create unique index uk_configinfo_1 on config_info (data_id,group_id,tenant_id);

/******************************************/
/*   数据库全名 = nacos_config               */
/*   表名称 = config_info_gray  since 2.5.0 */
/******************************************/
create table config_info_gray (
    id bigint not null generated always as identity,
    data_id varchar(255) not null,
    group_id varchar(128) not null,
    content text not null,
    md5 varchar(32),
    src_user text,
    src_ip varchar(100),
    gmt_create date not null default localtimestamp,
    gmt_modified date not null default localtimestamp,
    app_name varchar(128),
    tenant_id varchar(128) default '',
    gray_name varchar(128) not null,
    gray_rule text not null,
    encrypted_data_key varchar(256) not null default '',
    primary key (id)
);

create unique index uk_config_info_gray_1 on config_info_gray (data_id,group_id,tenant_id,gray_name);
create index idx_config_info_gray_1 on config_info_gray(ata_id,gmt_modified);
create index idx_config_info_gray_2 on config_info_gray(gmt_modified);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_aggr   */
/******************************************/
create table config_info_aggr (
  id bigint not null generated always as identity,
  data_id varchar(255) not null,
  group_id varchar(128) not null,
  datum_id varchar(255) not null,
  content text not null,
  gmt_modified date not null,
  app_name varchar(128),
  tenant_id varchar(128) default '',
  primary key (id)
);
create unique index uk_configinfoaggr_1 on config_info_aggr (data_id,group_id,tenant_id,datum_id);


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_beta   */
/******************************************/
create table config_info_beta (
  id bigint not null generated always as identity,
  data_id varchar(255) not null,
  group_id varchar(128) not null,
  app_name varchar(128),
  content text not null,
  beta_ips varchar(1024),
  md5 varchar(32),
  gmt_create date not null default localtimestamp,
  gmt_modified date not null default localtimestamp,
  src_user text,
  src_ip varchar(50),
  tenant_id varchar(128) default '',
  encrypted_data_key text not null,
  primary key (id)
);
create unique index uk_configinfobeta_1 on config_info_beta (data_id,group_id,tenant_id);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_tag   */
/******************************************/
create table config_info_tag (
  id bigint not null generated always as identity,
  data_id varchar(255) not null,
  group_id varchar(128) not null,
  tenant_id varchar(128) default '',
  tag_id varchar(128) not null,
  app_name varchar(128),
  content text not null,
  md5 varchar(32),
  gmt_create date not null default localtimestamp,
  gmt_modified date not null default localtimestamp,
  src_user text,
  src_ip varchar(50),
  primary key (id)
);
create unique index uk_configinfotag_1 on config_info_tag (data_id,group_id,tenant_id,tag_id);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_tags_relation   */
/******************************************/
create table config_tags_relation (
  id bigint not null,
  tag_name varchar(128) not null,
  tag_type varchar(64),
  data_id varchar(255) not null,
  group_id varchar(128) not null,
  tenant_id varchar(128) default '',
  nid bigint not null generated always as identity,
  primary key (nid)
);
create unique index uk_configtagrelation_1 on config_tags_relation (id,tag_name,tag_type);
create index idx_tenant_id on config_tags_relation (tenant_id);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = group_capacity   */
/******************************************/
create table group_capacity (
  id bigint not null generated always as identity,
  group_id varchar(128) not null default '',
  quota integer not null default '0',
  usage integer not null default '0',
  max_size integer not null default '0',
  max_aggr_count integer not null default '0',
  max_aggr_size integer not null default '0',
  max_history_count integer not null default '0',
  gmt_create date not null default localtimestamp,
  gmt_modified date not null default localtimestamp,
  primary key (id)
);
create unique index uk_groupcapacity_1 on group_capacity (group_id);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = his_config_info   */
/******************************************/
create table his_config_info (
  id bigint not null,
  nid bigint not null generated always as identity,
  data_id varchar(255) not null,
  group_id varchar(128) not null,
  app_name varchar(128),
  content text not null,
  md5 varchar(32),
  gmt_create date not null default localtimestamp,
  gmt_modified date not null default localtimestamp,
  src_user text,
  src_ip varchar(50),
  op_type char(10),
  tenant_id varchar(128) default '',
  encrypted_data_key text not null,
  publish_type varchar(50) default 'formal',
  gray_name varchar(50),
  ext_info text,
  primary key (nid)
);
create index idx_hisconfiginfo_1 on his_config_info (gmt_create);
create index idx_hisconfiginfo_2 on his_config_info (gmt_modified);
create index idx_hisconfiginfo_3 on his_config_info (data_id);


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = tenant_capacity   */
/******************************************/
create table tenant_capacity (
  id bigint not null generated always as identity,
  tenant_id varchar(128) not null default '',
  quota integer not null default '0',
  usage integer not null default '0',
  max_size integer not null default '0',
  max_aggr_count integer not null default '0',
  max_aggr_size integer not null default '0',
  max_history_count integer not null default '0',
  gmt_create date not null default localtimestamp,
  gmt_modified date not null default localtimestamp,
  primary key (id)
);
create unique index uk_tenantcapacity_1 on tenant_capacity (tenant_id);


create table tenant_info (
  id bigint not null generated always as identity,
  kp varchar(128) not null,
  tenant_id varchar(128) default '',
  tenant_name varchar(128) default '',
  tenant_desc varchar(256),
  create_source varchar(32),
  gmt_create bigint not null,
  gmt_modified bigint not null,
  primary key (id)
);
create unique index uk_tenantinfo_1 on tenant_info (kp,tenant_id);
create index idx_tenantinfo_1 on tenant_info (tenant_id);

create table users (
	username varchar(50) not null,
	password varchar(500) not null,
	enabled smallint not null,
	primary key (username)
);

create table roles (
	username varchar(50) not null,
	role varchar(50) not null
);
create unique index uk_roles_1 on roles (username, role);

create table permissions (
    role varchar(50) not null,
    resource varchar(255) not null,
    action varchar(8) not null
);
create unique index uk_permissions_1 on permissions (role,resource,action);

insert into users (username, password, enabled) values ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

insert into roles (username, role) values ('nacos', 'ROLE_ADMIN');
