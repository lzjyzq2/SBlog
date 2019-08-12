# SBlog
> 支持单\多用户的博客系统

## 开发指南
---
> 推荐使用`IDEA`进行项目开发，建议安装`alibaba java coding guidelines`插件
- `PostgreSQL数据库`安装
    - 下载[`PostgreSQL`](https://www.postgresql.org/download/)
    - 注意：安装时选择`loacl`为`c`，以避免安装发生错误
- 项目配置
    - 先下载项目到本地
    - 使用`IDEA`打开项目
    - 创建`sblog\src\main\resources\application.properties`
    - 在`sblog\src\main\resources\application.properties`中写入如下配置
    ```
        spring.datasource.url=jdbc:postgresql://[数据库IP]:[数据库端口号]/[数据库名称]
        spring.datasource.username=[用户名]
        spring.datasource.password=[密码]
        spring.datasource.driver-class-name=org.postgresql.Driver

        mybatis.configuration.map-underscore-to-camel-case=true
        mybatis.configuration.default-fetch-size=100
        mybatis.configuration.default-statement-timeout=30
        mybatis.configuration.auto-mapping-unknown-column-behavior=WARNING
    ```
    - 在IDEA中将`File | Settings | Editor | Inspections`中的`Spring\Spring Core\Code\Autowiring for Bean Class`的严重性改为`warning`
- 运行与打包
    - 使用`Gradle\Tasks`中的`applicattion\bootRun`进行运行
    - 使用`Gradle\Tasks`中的`build\jar`进行打包

### 依赖
- `SpringBoot`
- `lombok` [文档](https://projectlombok.org/features/all)
- `MyBatis` [文档](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
- `FreeMarker` [参考手册](http://freemarker.foofun.cn/toc.html)
- `PostgreSQL`