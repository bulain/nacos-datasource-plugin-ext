# 简介

nacos-datasource-plugin-ext 是Nacos适配MSSQL,ORACLE,PGSQL,DMSQL,KBSQL数据源插件。

# 快速使用

### 1.下载nacos-datasource-plugin-ext插件jar:

可以自行打包：

```
git clone https://github.com/bulain/nacos-datasource-plugin-ext.git
cd nacos-datasource-plugin-ext/
mvn clan package
```

### 2下载nacos-server

可在nacos的GitHub仓库下载

也可以自行下载nacos源码打包:

```sh
git clone https://github.com/alibaba/nacos.git
cd nacos/
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U 
echo "打包后的文件位于： ./distribution/target/:"
ls -al ./distribution/target/
```

### 3.插件配置 - pgsql

#### 3.1配置

在nacos安装目录下新建plugins目录，并将nacos-datasource-plugin-ext插件jar放入此目录,目录结构如下:

```
nacos/
├── plugins
│   ├── checker-qual-3.48.3.jar
│   ├── postgresql-42.7.5.jar
│   └── nacos-datasource-plugin-ext-2.5.1.jar
└── target
    └── nacos-server.jar
```

#### 3.2配置数据库链接信息

编辑conf/application.properties配置文件

```yml

#*************** Config Module Related Configurations ***************#
spring.datasource.platform=pgsql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:postgresql://127.0.0.1:5432/nacos?tcpKeepAlive=true&reWriteBatchedInserts=true&ApplicationName=nacos
db.user.0=nacos
db.password.0=******
db.pool.config.driverClassName=org.postgresql.Driver

```

#### 3.3导入pgsql-schema.sql到PostgreSQL数据库

新建数据库（假如数据库名为nacos），执行数据库初始化文件pgsql-schema.sql

#### 3.4启动nacos服务

单机模式启动：

```
cd nacos/
./bin/startup.sh -m standalone
```

### 4.插件配置 - mssql

#### 4.1配置

在nacos安装目录下新建plugins目录，并将nacos-datasource-plugin-ext插件jar放入此目录,目录结构如下:

```
nacos/
├── plugins
│   ├── mssql-jdbc-12.8.1.jre11.jar
│   └── nacos-datasource-plugin-ext-2.5.1.jar
└── target
    └── nacos-server.jar
```

编辑conf/application.properties配置文件

```yml

#*************** Config Module Related Configurations ***************#
spring.datasource.platform=mssql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:sqlserver://127.0.0.1:1433;databaseName=nacos;selectMethod=cursor;integratedSecurity=false;encrypt=false;trustServerCertificate=true
db.user.0=nacos
db.password.0=******
db.pool.config.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver

```

#### 4.3导入mssql-schema.sql到Mssql数据库

新建数据库（假如数据库名为nacos），执行数据库初始化文件mssql-schema.sql

#### 4.4启动nacos服务

单机模式启动：

```
cd nacos/
./bin/startup.sh -m standalone
```

### 5.插件配置 - oracle

#### 5.1配置

在nacos安装目录下新建plugins目录，并将nacos-datasource-plugin-ext插件jar放入此目录,目录结构如下:

```
nacos/
├── plugins
│   ├── ojdbc8-23.5.0.24.07.jar
│   └── nacos-datasource-plugin-ext-2.5.1.jar
└── target
    └── nacos-server.jar
```

编辑conf/application.properties配置文件

```yml

#*************** Config Module Related Configurations ***************#
spring.datasource.platform=oracle

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:oracle:thin:@127.0.0.1:1521/xe
db.user.0=nacos
db.password.0=******
db.pool.config.driverClassName=oracle.jdbc.OracleDriver

```

#### 5.3导入oracle-schema.sql到Oracle数据库

新建数据库（假如数据库名为nacos），执行数据库初始化文件oracle-schema.sql

#### 5.4启动nacos服务

单机模式启动：

```
cd nacos/
./bin/startup.sh -m standalone
```


### 6.插件配置 - dmsql

#### 6.1配置

在nacos安装目录下新建plugins目录，并将nacos-datasource-plugin-ext插件jar放入此目录,目录结构如下:

```
nacos/
├── plugins
│   ├── DmJdbcDriver8-8.1.4.93.jar
│   └── nacos-datasource-plugin-ext-2.5.1.jar
└── target
    └── nacos-server.jar
```

编辑conf/application.properties配置文件

```yml

#*************** Config Module Related Configurations ***************#
spring.datasource.platform=dmsql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:dm://127.0.0.1:5236/DAMENG
db.user.0=nacos
db.password.0=******
db.pool.config.driverClassName=dm.jdbc.driver.DmDriver

```

#### 6.3导入dmsql-schema.sql到Oracle数据库

新建数据库（假如数据库名为nacos），执行数据库初始化文件dmsql-schema.sql

#### 6.4启动nacos服务

单机模式启动：

```
cd nacos/
./bin/startup.sh -m standalone
```


### 7.插件配置 - kbsql

#### 7.1配置

在nacos安装目录下新建plugins目录，并将nacos-datasource-plugin-ext插件jar放入此目录,目录结构如下:

```
nacos/
├── plugins
│   ├── kingbase8-8.6.0.jar
│   └── nacos-datasource-plugin-ext-2.5.1.jar
└── target
    └── nacos-server.jar
```

编辑conf/application.properties配置文件

```yml

#*************** Config Module Related Configurations ***************#
spring.datasource.platform=kbsql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:kingbase8://127.0.0.1:54321/test?currentSchema=public&useUnicode=true&characterEncoding=UTF-8
db.user.0=nacos
db.password.0=******
db.pool.config.driverClassName=com.kingbase8.Driver

```
#### 7.3导入kbsql-schema.sql到Kbsql数据库

新建数据库（假如数据库名为nacos），执行数据库初始化文件kbsql-schema.sql

#### 7.4启动nacos服务

单机模式启动：

```
cd nacos/
./bin/startup.sh -m standalone
```


# 8.许可证
Apache License 