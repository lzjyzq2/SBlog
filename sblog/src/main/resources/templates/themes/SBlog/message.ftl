<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>注册验证</title>
</head>

<style type="text/css">
    .layout {
        max-width: 720px;
        width: 720px;
        margin: 0 auto;
    }
    a{
        color: #000;
        font-weight: bold;
        text-decoration: none;
    }

    .header {
        background: #e9e9e9;
        padding: 16px;
    }

    .content {
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 16px;
    }

    .content span {
        display: inline-block;
        text-align: center;
        margin: 0 auto;
        padding: 5px;
        border-bottom: 2px solid #636363;
        font-size: 30px;
        text-align: center;
    }
    .hint{
        color: #c5c5c5;
        display: block;
        padding-left: 16px;
    }

    .footer {
        background: #e9e9e9;
        padding: 16px;
    }
</style>

<body>
    <div class="layout">
        <div class="header">
            <p>欢迎注册 <a href="${webSiteUrl}">${webSiteName}</a> ，你的验证码如下：</p>
        </div>
        <div class="content">
            <span>${captcha}</span>
            <br>
        </div>
        <p class="hint">验证码有效时间为：15m</p>
        <div class="footer">
            如果您没有进行注册，请忽视本邮件！
        </div>
    </div>
</body>

</html>