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

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info   */
/******************************************/
CREATE TABLE config_info (
  id bigint NOT NULL identity,
  data_id nvarchar(255) NOT NULL,
  group_id varchar(128) NOT NULL,
  tenant_id varchar(128) default '',
  app_name nvarchar(128),
  content ntext,
  md5 varchar(32),
  gmt_create datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  gmt_modified datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  src_user varchar(128),
  src_ip varchar(50),
  c_desc nvarchar(256),
  c_use varchar(64),
  effect varchar(64),
  type varchar(64),
  c_schema ntext,
  encrypted_data_key ntext,
  constraint pk_configinfo_1 PRIMARY KEY (id),
  constraint uk_configinfo_1 UNIQUE (data_id,group_id,tenant_id)
);

CREATE INDEX idx_configinfo_1 ON config_info(data_id);
CREATE INDEX idx_configinfo_2 ON config_info(group_id);
CREATE INDEX idx_configinfo_3 ON config_info(data_id, group_id);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = his_config_info   */
/******************************************/
CREATE TABLE his_config_info (
  id bigint NOT NULL,
  nid bigint NOT NULL identity,
  data_id nvarchar(255) NOT NULL,
  group_id varchar(128) NOT NULL,
  tenant_id varchar(128) default '',
  app_name nvarchar(128),
  content ntext,
  md5 varchar(32),
  gmt_create datetime NOT NULL DEFAULT '2010-05-05 00:00:00.000',
  gmt_modified datetime NOT NULL DEFAULT '2010-05-05 00:00:00.000',
  src_user varchar(128),
  src_ip varchar(50),
  op_type char(10),
  encrypted_data_key ntext,
  constraint pk_hisconfiginfo_1 PRIMARY KEY (nid)
);

CREATE INDEX idx_hisconfiginfo_1 ON his_config_info(data_id);
CREATE INDEX idx_hisconfiginfo_2 ON his_config_info(gmt_create);
CREATE INDEX idx_hisconfiginfo_3 ON his_config_info(gmt_modified);


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_beta   */
/******************************************/
CREATE TABLE config_info_beta (
  id bigint NOT NULL identity,
  data_id nvarchar(255) NOT NULL,
  group_id varchar(128) NOT NULL,
  tenant_id varchar(128) default '',
  app_name nvarchar(128),
  content ntext,
  beta_ips varchar(1024),
  md5 varchar(32),
  gmt_create datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  gmt_modified datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  src_user varchar(128),
  src_ip varchar(50),
  encrypted_data_key ntext,
  constraint pk_configinfobeta_1 PRIMARY KEY (id),
  constraint uk_configinfobeta_1 UNIQUE (data_id,group_id,tenant_id)
);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_tag   */
/******************************************/
CREATE TABLE config_info_tag (
  id bigint NOT NULL identity,
  data_id nvarchar(255) NOT NULL,
  group_id varchar(128) NOT NULL,
  tenant_id varchar(128) default '',
  tag_id varchar(128) NOT NULL,
  app_name nvarchar(128),
  content ntext,
  md5 varchar(32),
  gmt_create datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  gmt_modified datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  src_user varchar(128),
  src_ip varchar(50),
  constraint pk_configinfotag_1 PRIMARY KEY (id),
  constraint uk_configinfotag_1 UNIQUE (data_id,group_id,tenant_id,tag_id)
);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_aggr   */
/******************************************/
CREATE TABLE config_info_aggr (
  id bigint NOT NULL identity,
  data_id nvarchar(255) NOT NULL,
  group_id varchar(128) NOT NULL,
  tenant_id varchar(128) default '',
  datum_id varchar(255) NOT NULL,
  app_name nvarchar(128),
  content ntext,
  gmt_modified datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  constraint pk_configinfoaggr_1 PRIMARY KEY (id),
  constraint uk_configinfoaggr_1 UNIQUE (data_id,group_id,tenant_id,datum_id)
);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_tags_relation   */
/******************************************/
CREATE TABLE config_tags_relation (
  id bigint NOT NULL,
  tag_name nvarchar(128) NOT NULL,
  tag_type varchar(64),
  data_id nvarchar(255) NOT NULL,
  group_id varchar(128) NOT NULL,
  tenant_id varchar(128) DEFAULT '',
  nid bigint NOT NULL identity,
  constraint pk_configtagrelation_1 PRIMARY KEY (nid),
  constraint uk_configtagrelation_1 UNIQUE (id, tag_name, tag_type)
);

CREATE INDEX idx_configtagsrelation_1 ON config_tags_relation(tenant_id);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = group_capacity   */
/******************************************/
CREATE TABLE group_capacity (
  id bigint NOT NULL identity,
  group_id varchar(128) DEFAULT '',
  quota int DEFAULT 0,
  usage int DEFAULT 0,
  max_size int DEFAULT 0,
  max_aggr_count int DEFAULT 0,
  max_aggr_size int DEFAULT 0,
  max_history_count int DEFAULT 0,
  gmt_create datetime DEFAULT '2010-05-05 00:00:00',
  gmt_modified datetime DEFAULT '2010-05-05 00:00:00',
  constraint pk_groupcapacity_1 PRIMARY KEY (id),
  constraint uk_groupcapacity_1 UNIQUE (group_id)
);

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = tenant_capacity   */
/******************************************/
CREATE TABLE tenant_capacity (
  id bigint NOT NULL identity,
  tenant_id varchar(128) DEFAULT '',
  quota int DEFAULT 0,
  usage int DEFAULT 0,
  max_size int DEFAULT 0,
  max_aggr_count int DEFAULT 0,
  max_aggr_size int DEFAULT 0,
  max_history_count int DEFAULT 0,
  gmt_create datetime DEFAULT '2010-05-05 00:00:00',
  gmt_modified datetime DEFAULT '2010-05-05 00:00:00',
  constraint pk_tenantcapacity_1 PRIMARY KEY (id),
  constraint uk_tenantcapacity_1 UNIQUE (tenant_id)
);

CREATE TABLE tenant_info (
  id bigint NOT NULL identity,
  kp varchar(128) NOT NULL,
  tenant_id varchar(128)  DEFAULT '',
  tenant_name nvarchar(128)  DEFAULT '',
  tenant_desc nvarchar(256) ,
  create_source varchar(32),
  gmt_create bigint NOT NULL,
  gmt_modified bigint NOT NULL,
  constraint pk_tenantinfo_1 PRIMARY KEY (id),
  constraint uk_tenantinfo_1 UNIQUE (kp,tenant_id)
);
CREATE INDEX idx_tenantinfo_1 ON tenant_info(tenant_id);

CREATE TABLE users (
	username varchar(50) NOT NULL,
	password varchar(500) NOT NULL,
	enabled smallint NOT NULL DEFAULT 1,
	constraint pk_users_1 PRIMARY KEY (username)
);

CREATE TABLE roles (
	username varchar(50) NOT NULL,
	role varchar(50) NOT NULL,
	constraint uk_roles_1 UNIQUE (username,role)
);

CREATE TABLE permissions (
    role varchar(50) NOT NULL,
    resource nvarchar(512) NOT NULL,
    action varchar(8) NOT NULL,
    constraint uk_permissions_1 UNIQUE (role,resource,action)
);

INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');
