# Route: `/api` / `api.<domain.name>/`
- 需要Header包含`Refer: site.domain.name` - _可选_

### 空 `/`
- 跳转404 / 默认主页

---
### 搜索 `/unread`
- 用法: `GET /api/unread`
- 返回值: JSON
```
body = {
    count = <未读数量>
}
```
---
### 搜索 `/search`
- 默认: 跳转至搜索页
- 关键词: `keyword`, `filter`, 
    - `keyword`: 搜索关键字
    - `filter`: `time`, `time-reverse`, `relavent`
- 用法: `GET /api/search?keyword=example&filter=time`
- 返回值: JSON
```
body.results = {
    link = <link>
    type = "user" / "article" / 待补充
    title = <title> / <userName> / 其他
    content = <...符合关键词的内容...>
    user = <等同于 api/user/detail?id=<userID>[body][user] 返回值>
}
```
---
### 用户关注时间线 `/timeline`
- 默认: 返回 0 - 20 条 / 400 Bad Request
- 关键词: `from`, `max`, 
    - `from`: 从第几条开始
    - `max`: 一次性最多获取多少 最大50
- 用法: `GET /api/timeline` / `GET /api/timeline?from=21&max=20`
- 返回值: JSON
```
body = {
    list = [
        {
            user = <等同于api/user/detail?id=<userID>返回值>
            link = <link>
            type = "article" / "images" / "text" / 其他
            blocks = [
                {
                    text = <内容>
                    image = <图片链接>
                    linkTo = <点击时跳转>
                    user = <等同于 api/user/detail?id=<userID>[body][user] 返回值>
                },*
            ]
        },*
    ],
    nextUrl = < /api/timeline?from=<this>&max=<用户设定> >
}
```

---
## 用户接口 `/user`
### 用户接口 `/detail`
- 默认: 跳转至400 / 跳转至用户主页
- 关键词: `id`,
    - `id`: 用户id
- 用法: `GET /api/user/detail?id=<userId>`
- 返回值: JSON
```
body.user = {
    link = <到用户主页的链接>
    userName = <用户名>
    userIcon = <头像链接>
    userId = <用户id>
}
```
---
### 用户接口 `/following`
- 默认: 返回 0 - 20 条 / 跳转400
- 关键词: `from`, `max`, 
    - `from`: 从第几条开始
    - `max`: 一次性最多获取多少 最大50
- 用法: `GET /api/user/following` / `GET /api/user/following?from=21&max=20`
- 返回值: JSON
```
body = {
    list = [
        <user.detail>,*
    ],
    nextUrl = < /api/user/following?from=<this>&max=<用户设定> >
}
```
---
### 用户接口 `/following`
- 默认: 返回 0 - 20 条
- 关键词: `from`, `max`, 
    - `from`: 从第几条开始
    - `max`: 一次性最多获取多少 最大50
- 用法: `GET /api/user/following` / `GET /api/user/following?from=21&max=20`
- 返回值: JSON
```
body = {
    list = [
        <user.detail>,*
    ],
    nextUrl = < /api/user/following?from=<this>&max=<用户设定> >
}
```