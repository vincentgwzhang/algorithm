================Docker command============================
docker build -t docker-whale .
docker pull repo.zooplus.de/base/centos7-java8:1.13.1
docker images --> get the image id for below command to use
docker tag 7d9495d03763 maryatdocker/docker-whale:latest --> tag as "maryatdocker/docker-whale"

upload:
docker login
acc / pass
docker push maryatdocker/docker-whale


delete docker image:
docker rmi -f 7d9495d03763

list running containers:
docker ps

list all containers:
docker ps -a

only display container id
docker ps -aq 

stop running container:
1, 首先列出在运行的container:
docker ps
从而得到 CONTAINER ID, 凭着此value, stop container:
docker stop <CONTAINER ID>


运行自家 Oracle 的 image:
docker run -d -p 8080:8080 -p 1521:1521 -v /my/oracle/data:/u01/app/oracle sath89/oracle-12c

####################Delete all images and containers##################
#!/bin/bash
# Delete all containers
docker rm $(docker ps -a -q)
# Delete all images
docker rmi $(docker images -q)

==============================Close lib and do nothing=================
/etc/UPower/UPower.conf


docker rmi $(docker images -a -q)

===============================Docker=============================
通过Docker 下载了Oracle, 运行的时候是这样运行的：

docker pull sath89/oracle-12c
docker run -d -p 8080:8080 -p 1521:1521 sath89/oracle-12c


当运行上面那句话的时候，有个很很很长的字符串会弹了出来，例如：
guangweiz@Vincent:~$ docker run -d -p 8080:8080 -p 1521:1521 sath89/oracle-12c
1d30a6c198bfcd143aa8a66731ba7c0a277a3312820065419bbbb7c5b426b372


这个很长的字符串是用来作为该docker image的句柄的，运用这个句柄，可以：
* 追踪log 文件的运行，命令如下：
docker logs -f 1d30a6c198bfcd143aa8a66731ba7c0a277a3312820065419bbbb7c5b426b372

* 直接进入该镜像，命令如下：
docker exec -it 1d30a6c198bfcd143aa8a66731ba7c0a277a3312820065419bbbb7c5b426b372 /bin/bash

* 记得，输入exit 跳出



docker run -d -p 8080:8080 -p 1521:1521 -v /oradata:/u01/app/oracle sath89/oracle-12c

======================================================================================================

-d 后台运行

docker run --name db --env MYSQL_ROOT_PASSWORD=example -d mariadb
docker run --name MyWordPress --link db:mysql -e WORDPRESS_DB_USER=root -e WORDPRESS_DB_PASSWORD=example -p 8080:80 -d wordpress
[[[
--name 设置别名
--env 设置参数，密码
]]]


什么是docker composer

Docker 提倡理念是一个容器一个进程，假设一个服务需要由多个进程组成，就需要多个容器组成一个系统。相互分工和配合对外提供完整的服务。

允许用户在一个Yaml 文件里面定义一组相关的容器，会根据 --link 等参数对启动的优先级进行排序

1, docker-composer 是直接下载直接使用的。
2, 参数列表

-f 使用指定的 yaml 的位置
ps 显示所有容器信息
restart 重启
logs 查看日志
config -q 验证yaml 信息正确与否
stop / start 停止/启动容器
up -d 启动容器项目（之前并不存在）
pause 暂停
unpause 恢复
rm 删除容器

inspect <container id> 获取所有信息




docker network create somenetwork
docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.2.0
docker run -d --name kibana --net somenetwork -p 5601:5601 kibana:7.2.0

1, Image ID + version is unique
2, Docker 的镜像通过 union filesystem merge together
镜像包括：
A, Bootfs:核心根本，包括 bootloader 和 Kernal
B, roofs:只读模式，而后通过 UFS技术挂载一个可写层

3, 上层镜像优先级高于下层




========================================= 构建docker 镜像 =============================================================
1, 在原本的container 上封装一个新的镜像

docker commit kibana kibana:vincent
第一个kibana 是container 的别名，然后是 Kibana : version

注意: commit 并不利于使用，假设这个容器是建立在前容器基础上，加上安装新的服务。一旦commit成镜像，再运行这个镜像的时候，他并不会自动启动之前新安装的那个服务


2, DockerFile

文件内容
[[[
FROM centos:latest
MAINTAINER vincentzhang@outlook.es

ADD ./apache-tomcat-9.0.22.tar.gz /root
ADD ./jdk-12.0.2_linux-x64_bin.tar.gz /root

ENV JAVA_HOME /root/jdk-12.0.2
ENV PATH ${JAVA_HOME}/bin:$PATH

EXPOSE 8080

ENTRYPOINT /root/apache-tomcat-9.0.22/bin/startup.sh && tailf /root/apache-tomcat-9.0.22/logs/catalina.out
]]]

#注意最后的 . 符号
docker build -t tomcat:v1.0 .

然后运行:
docker run --name tomcat_try -p 23180:8080 -d tomcat:v1.0



==================================构建私有仓库======================================================


服务器：
docker run -d -v /opt/registry:/var/lib/registry -p 5000:5000 --restart=always registry


客户端：
修改:
vim /etc/docker/daemon.json
[[[
		{
			"insecure-registries": ["10.10.10.11:5000"]
		}
]]]


改变要上存的docker(修改成要上存的docker image):
docker tag docker1:v1.0 10.10.10.11:5000/tomcat:v1.0

docker push

检查：
curl -XGET http://10.10.10.11:5000/v2/_catalog

======================================================================================================
Docker 网络通讯


1, 容器与容器之间
docker run --name tomcat --net=none/container/host -d tomcat:v1

--net=none 没有网络
--net=host 直接访问，不需要 -p 参数，因为此时跟主机直接分享网络端口
--net=bridge 桥接方式（就是用端口桥接，子网络那种）
--net=container 使用其他容器的网络栈

查看本身所有container 的连接方式
docker network ls

查看指定了多少个端口
vincent@ubuntu:~$ docker port tomcat_try
8080/tcp -> 0.0.0.0:23180

======================================================================================================
网络的隔离方式

由于默认是子网方式（bridge）,因此本身默认是能够互通的

Step 1:
docker network create -d bridge --subnet=x.x.x.0/24 lamp
docker network create -d bridge --subnet=x.x.x.0/24 lnmp


Step 2:
docker run --name tomcat11 --network=lamp -d tomcat:v1.0 --ip x.x.x.x
docker run --name tomcat12 --network=lanp -d tomcat:v1.0 --ip x.x.x.x

这样就造成 tomcat11和 tomcat12 不在同一个子网里面了例如 172.11.0.1 和 172.12.0.01
======================================================================================================
数据持久化

卷 ，就是独立于 container 外的目录
卷，有分人为卷和绑定卷


第一种:
在dockerfile 里面使用 volume 指令

[[[
FROM centos:latest
MAINTAINER vincentzhang@outlook.es

RUN touch /tmp/1.txt
RUN mkdir /data // RUN命令是创建Docker镜像（image）的步骤，RUN命令对Docker容器（ container）造成的改变是会被反映到创建的Docker镜像上的
VOLUME /data
CMD tail -f /tmp/1.txt//CMD命令是当Docker镜像被启动后Docker容器将会默认执行的命令
]]]

docker rm -f -v test2 删除的时候顺便把卷也删除

第二种：
[[[
FROM centos:latest
MAINTAINER vincentzhang@outlook.es

RUN touch /tmp/1.txt
RUN mkdir /data
#VOLUME /data
CMD tail -f /tmp/1.txt
]]]
但是我强制运行
docker run --name test11 -v /data:/data -d test:v1.0

第三种：
[[[
FROM centos:latest
MAINTAINER vincentzhang@outlook.es

RUN touch /tmp/1.txt
RUN mkdir /data
VOLUME /data
CMD tail -f /tmp/1.txt
]]]
也就是说我声明了VOLUME 的同时，我强制运行
docker run --name test11 -v /data:/data -d test:v1.0

这个时候，命令行更为高优先级

=========================
docker 共享：

第一种:
docker run --name test1 -v /data:/data -d test:v1.0
docker run --name test2 -v /data:/data -d test:v1.0

第二种：直接从test2继承
docker run -name test2 -d test:v2.0
docker run -name test22 --volumes-from test2 -d test:v2.0

===========================================================================================

Docker copy 和 add 的区别

copy 就是本地 copy 文件或者目录
add  可以把网络文件加进去。但是如果是要求认证才能下载的说话就要用 RUN wget 或者 RUN curl
===========================================================================================

CMD 和 RUN 的区别

RUN 在构建的时候执行，并生成一个新的镜像，

CMD 在容器运行的时候执行，在构建时不进行任何操作。
每个Dockerfile只能有一条CMD命令，如果指定了多条命令，只有最后一条会被执行。

===========================================================================================

EXPORT 22 80 443
ENV key value
VOLUME ["/data"]
WORKDIR /path/to/workdir


===========================================================================================


14.ARG
说明：指定一些镜像内使用的参数（例如版本号信息），这些参数在执行docker build命令时才以--build-arg<varname>=<value>格式传入。
格式：
ARG <name> [=<default value>]

===========================================================================================

10.ENTRYPOINT
说明：指定镜像的默认入口命令，该入口命令会在启动容器时作为根命令执行，所以传入值作为该命令的参数。
格式：
ENTRYPOINT ["executable","param1","param2"] exec调用执行
ENTRYPOINT command param1 param2 shell中执行

此时，CMD指令指定值将作为根命令的参数。
每个Dockerfile中只能有一个ENTRYPOINT，当指定多个时，只有最后一个有效。
在运行时，可以被 --entrypoint参数覆盖掉。

===========================================================================================

