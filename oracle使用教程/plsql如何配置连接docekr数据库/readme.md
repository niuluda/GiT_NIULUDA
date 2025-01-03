#### 1.下载instant-client()

```
https://www.oracle.com/database/technologies/instant-client/winx64-64-downloads.html
```

#### 2.解压到指定目录(instantclient_12_2)

```
D:\PLSQL Developer 12\instantclient_12_2
```

#### 3.新建network\ADMIN目录

```
instantclient_12_2\network\ADMIN
```

#### 4.进入容器查看数据库名称

```
docker exec -it oracle11g /bin/bash
bash-4.4$: sqlplus / AS SYSDBA(不能切换root 切换了会让你输入用户名和密码)
SQL>show parameter service_name
输出可知数据库用户名:FREE

NAME				     TYPE	 VALUE
------------------------------------ ----------- ------------------------------
service_names			     string	 FREE


```

#### 5.编辑之前建立好的 instantclient_12_2\network\ADMIN\tnsnames.ora 文件。内容如下：

```
test_docker_oracle=
(DESCRIPTION =
  (ADDRESS = (PROTOCOL = TCP)(HOST= 172.18.20.129)(PORT = 1521))
  (CONNECT_DATA =
    (SERVER = DEDICATED)
    (SERVICE_NAME = xe)
  )
)
HOST 为 Docker 在宿主机上的 IP 地址， SERVICE_NAME 是之前在 Docker 中 ORACLE 中查看得到的服务名
```

6.配置oci.dll

```
plsql-首选项-连接
```

![](images/微信截图_20250103163103.png)