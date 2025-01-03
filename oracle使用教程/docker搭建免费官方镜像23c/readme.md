docker run -d --shm-size=3g -v /etc/localtime:/etc/localtime:ro -v /etc/timezone:/etc/timezone:ro --name oracle23c -p  1528:1521 container-registry.oracle.com/database/free

```
时间漂移错误：

日志中显示了大量的时间漂移错误 (Time drifted forward)，这可能是 Docker 容器与宿主机之间的时钟同步问题导致的。Oracle 数据库对时间漂移非常敏感。

-v /etc/localtime:/etc/localtime:ro -v /etc/timezone:/etc/timezone:ro 

```



```
--shm-size=3g共享内存可能不足：

默认情况下，Docker 容器的共享内存大小为 64MB。如果 Oracle 数据库的内存需求超过这个值，会导致实例启动失败。
```

