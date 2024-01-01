# 简介

nacos-datasource-plugin-ext 是Nacos适配MSSQL,ORACLE,PGSQL数据源插件。

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

### 3.插件配置

#### 3.1配置

在nacos安装目录下新建plugins目录，并将nacos-datasource-plugin-ext插件jar放入此目录,目录结构如下:

```
nacos/
├── bin
├── conf
├── data
├── derby.log
├── LICENSE
├── logs
├── NOTICE
├── plugins
│   └── nacos-datasource-plugin-ext-2.3.0.jar
├── target
│   └── nacos-server.jar
└── work
```

#### 3.2配置数据库链接信息

编辑conf/application.properties配置文件

```yml

#*************** Config Module Related Configurations ***************#
### If use mssql/oracle/pgsql as datasource:
spring.datasource.platform=pgsql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:postgresql://127.0.0.1:5432/nacos?tcpKeepAlive=true&reWriteBatchedInserts=true&ApplicationName=nacos
db.user.0=pgsql
db.password.0=pgsql
db.pool.config.driverClassName=org.postgresql.Driver

```

#### 3.3导入pgsql-schema.sql到PostgreSQL数据库

新建数据库（假如数据库名为nacos）：

```sql
create database nacos;
```

执行导入命令

```sql
psql -U postgres -d nacos -f ./pgsql-schema.sql
```

#### 3.4启动nacos服务

单机模式启动：

```
cd nacos/
./bin/startup.sh -m standalone
```

# 4.许可证
Apache License 