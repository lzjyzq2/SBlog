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
    - 在项目中创建`sblog`或`sblog-dev`文件夹
    - 将`sblog\src\main\resources\application.properties`拷贝到文件夹中修改配置
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
        spring.redis.port=[端口]
        # Redis服务器连接密码（默认为空）
        spring.redis.password=[密码]
        # 连接池最大连接数（使用负值表示没有限制）
        spring.redis.pool.max-active=100
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        spring.redis.pool.max-wait=-1
        # 连接池中的最大空闲连接
        spring.redis.pool.max-idle=10
        # 连接池中的最小空闲连接
        spring.redis.pool.min-idle=0

        # 国际化文件
        spring.messages.basename=i18n/messages
        # 国际化文件编码
        spring.messages.encoding=UTF-8

        # JavaMailSender 邮件发送的配置
        spring.mail.default-encoding=UTF-8
        spring.mail.host=[邮件服务地址]
        spring.mail.port=[邮件服务端口号]
        spring.mail.username=[邮箱账户]

        # 邮箱开启的授权码
        spring.mail.password=[授权码]
        spring.mail.properties.smtp.auth=true
        spring.mail.properties.smtp.starttls.enable=true
        spring.mail.properties.smtp.starttls.required=true
        spring.mail.properties.mail.smtp.ssl.enable=true


        # 以下内容不建议修改
        # 数据表permission的初始化内容
        sblog.data.permission = write+,write,commit,view,delete,admin,admin+
        # 数据表role的初始化内容
        sblog.data.role = {"user":["write+","commit","view","admin"],"admin":["write+","commit","view","delete","admin","admin+"]}
        ```
    - ~~在IDEA中将`File | Settings | Editor | Inspections`中的`Spring\Spring Core\Code\Autowiring for Bean Class`的严重性改为`warning`~~
    - 在IDEA Settings中安装`lombok` Plugins
        > 应注意安装`lombok`插件后的IDEA设置
- ### 接口文档
    - 规划中的接口文档，可按如下方式查看：
        ```
        [rootDir]/sblog/RESTful.md
        ```
    - 已实现的接口文档，可按如下方式查看：
        1. 启动项目
        2. 访问 `localhost:[port]/swagger-ui.html`
- ### 注意事项
    #### Swagger2：
    - 在`Controller`下应增加`@RequestMapping`注解
    - 在使用`Model`作为接口值时应使用`@RequestBody`注解
    #### 初始化内容：
    - 不建议修改`sblog.data.permission`内容会使得系统无法正常工作
    - `sblog.data.role`内容为JSON字符串，其格式与含义如下：
        ```
        {
            <roleName>:[permission1,permission2]
        }
        ```
    #### 配置文件优先级列表(由上到下，优先级越低，上层覆盖下层)
    - `S'Blog` 配置文件优先级：
        - file:./sblog-dev/
        - file:./sblog/
    - `SpringBoot` 配置文件优先级:
        - file:./config/
        - file:./
        - classpath:/config/
        - classpath:/
    #### 多环境配置文件使用：
    - IDEA：在`Environment variables`中输入：
        ```
        spring.profiles.active=dev
        ```
    - JAR：启动时使用：
        ```
        `java -jar xxx.jar --spring.profiles.actvie=dev `
        ```
- 运行与打包
    - 使用`Gradle\Tasks`中的`applicattion\bootRun`进行运行
    - 使用`Gradle\Tasks`中的`build\jar`进行打包
- 建议使用的Commit规范
    ```
    <type>(<scope>): <subject>
    <BLANK LINE>
    <body>
    <BLANK LINE>
    <footer>
    ```
    Type的分类：
    ```
    feat：新功能（feature）
    fix：修补bug
    docs：文档（documentation）
    style： 格式（不影响代码运行的变动）
    refactor：重构（即不是新增功能，也不是修改bug的代码变动）
    test：增加测试
    chore：构建过程或辅助工具的变动
    ```
    Scope的推荐类型：
    ```
    controller
    service
    entity
    dao
    bean
    util
    ……
    ```
    Subject：
    ```
    subject是 commit 目的的简短描述
    ```
    - 详细参考：[点击查看](http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html)

### 依赖
- `SpringBoot`
- `lombok` [文档](https://projectlombok.org/features/all)
- `Spring Data JPA` [文档](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- `Redis` [虚假的中文文档](http://www.redis.cn/documentation.html) [官方文档](https://redis.io/documentation) [菜鸟教程](https://www.runoob.com/redis/redis-install.html)
- `FreeMarker` [参考手册](http://freemarker.foofun.cn/toc.html)
- `PostgreSQL`
- `Swagger2`
- `JWT`
- `Shrio`
- `JavaMailSender`