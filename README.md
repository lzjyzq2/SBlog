# SBlog
> 支持单\多用户的博客系统

## 开发指南
---
> 推荐使用`IDEA`进行项目开发，建议安装`alibaba java coding guidelines`插件
- `PostgreSQL数据库`安装
    - 下载[`PostgreSQL`](https://www.postgresql.org/download/)
    - 注意：安装时选择`loacl`为`c`，以避免安装发生错误
    - 注意：应自行创建数据库`SBlog`，SBlog项目运行时将自动创建表于`public`模式（架构）下
- `Redis`Windows下安装
    - 下载[`Redis`](https://github.com/MicrosoftArchive/redis/releases)
    - 建议使用[`AnotherRedisDesktopManager`](https://github.com/qishibo/AnotherRedisDesktopManager/releases)进行可视化管理
- 项目配置
    - 先下载项目到本地
    - 使用`IDEA`打开项目
    - 创建`sblog\src\main\resources\application.properties`
    - 在`sblog\src\main\resources\application.properties`中写入如下配置
    ```
        spring.datasource.platform=postgres
        spring.datasource.url=jdbc:postgresql://[数据库IP]:[数据库端口号]/[数据库名称]?useSSL=false
        spring.datasource.username=[用户名]
        spring.datasource.password=[密码]
        spring.datasource.driver-class-name=org.postgresql.Driver
        spring.datasource.driverClassName=org.postgresql.Driver
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
        spring.jpa.properties.hibernate.hbm2ddl.auto=update
        spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
        spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect

        # Redis服务器地址
        spring.redis.host=127.0.0.1
        # Redis服务器连接端口
        spring.redis.port=25001
        # Redis服务器连接密码（默认为空）
        spring.redis.password=
        # 连接池最大连接数（使用负值表示没有限制）
        spring.redis.pool.max-active=100
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        spring.redis.pool.max-wait=-1
        # 连接池中的最大空闲连接
        spring.redis.pool.max-idle=10
        # 连接池中的最小空闲连接
        spring.redis.pool.min-idle=0
    ```
    - 在IDEA中将`File | Settings | Editor | Inspections`中的`Spring\Spring Core\Code\Autowiring for Bean Class`的严重性改为`warning`
    - 在IDEA Settings中安装`lombok` Plugins
        > 应注意使用`lombok`后的IDEA设置
- 运行与打包
    - 使用`Gradle\Tasks`中的`applicattion\bootRun`进行运行
    - 使用`Gradle\Tasks`中的`build\jar`进行打包

### 依赖
- `SpringBoot`
- `lombok` [文档](https://projectlombok.org/features/all)
- `Spring Data JPA` [文档](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- `Redis` [虚假的中文文档](http://www.redis.cn/documentation.html) [官方文档](https://redis.io/documentation) [菜鸟教程](https://www.runoob.com/redis/redis-install.html)
- `FreeMarker` [参考手册](http://freemarker.foofun.cn/toc.html)
- `PostgreSQL`