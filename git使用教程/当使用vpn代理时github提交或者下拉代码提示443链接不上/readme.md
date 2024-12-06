###### 问题定位

```
首先，确认你是否在使用VPN。VPN的使用可能会改变本机的系统端口号，从而影响到Git的正常连接。
```

######  操作指南

```
 a. VPN使用环境下的解决方案
**查看系统端口号**: 打开“设置 -> 网络和Internet -> 代理”，记录下当前的端口号。
```

设置代理端口

```
git config --global http.proxy 127.0.0.1:<你的端口号>
git config --global https.proxy 127.0.0.1:<你的端口号>
```

验证:会显示 Git 全局配置的所有设置

```
git config --global -l
```

执行后会输出

```
user.name=John Doe
user.email=johndoe@example.com
core.editor=vim
core.autocrlf=input
merge.tool=vimdiff
color.ui=true

```

