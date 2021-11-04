Docker

### 一. 什么是Docker

​	在docker的官方之[什么是docker](https://www.docker.com/why-docker)中提到了一句话：“当今各大组织或者团体的创新都源于软件(例如OA、ERP等)，其实很多公司都是软件公司"。用户量的激增导致了并发、指数级增加的数据、应用的可靠性等问题，单体应用已经应对不了这些问题，于是诞生了分布式、集群、微服务、边缘计算等各种名词、架构风格和满足这种架构风格的各种框架，那我们接下来跟大家谈谈这些技术名词。

​     分布式：将一个复杂的应用按照模块进行拆分，每个拆分的模块做成一个应用，分开部署，分开运行，各个模块之间通过webservice、http rest、rpc的方式进行调用。但是分布式系统中面临着很多棘手的问题：1. 如果某一个应用crash掉了，会导致调用该模块的其他模块也无法正常工作；2. 因为网络抖动或者硬件的问题导致数据的一致性问题(即分布式事务问题)；3. 运维和硬件成本的急剧上升。

​     集群：集群是指将某一个应用或者某个模块部署在多台机器上(这些机器上跑的代码是相同的)，然后通过负载均衡的方式让每个应用都能处理请求，即使某一个应用宕掉了，其他的应用一样可以处理请求，集群是为了解决我们上面提到的分布式应用中的第一个问题，但是集群也面临着诸多的问题：1. 运维和硬件成本的急剧增加；2. 实现集群势必会引入第三方的插件，那么第三方插件如何去保障其稳定运行；

​          微服务：微服务只是一种架构风格，最早是由Martin Fowler(博客点击[这里](https://www.martinfowler.com/))提出，他对微服务的解释是：In short, the microservice architectural style is an approach to developing a single application as a **suite of small services**, each **running in its own process** and communicating with lightweight mechanisms, often an HTTP resource API. These services are **built around business capabilities** and **independently deployable** by fully automated deployment machinery. There is a **bare minimum of centralized management** of these services, which may be written in different programming languages and use different data storage technologies（简而言之，微服务是一种架构的风格，将每一个单独的应用来作为一个服务套件，每一个服务套件运行在其独立的进程当中，使用轻量级的方式相互调用，通常采用HTTP的方式。这些个服务是要建立在业务能力和自动化独立部署的基础上的。这些服务间应该以一种去中心化的方式运行，而且这些服务可以使用不同的语言、不同的存储机制来实现)。我用自己的话来表达一下，所谓的微服务就是将一个可以独立部署、业务能力独立的应用，应用之间耦合度尽量降低(只能尽量降低，不可能实现绝对的解耦)，尽可能的去中心化。微服务也同样的面临着诸多的问题：1. 分布式事务问题；2. 运维和硬件成本急剧上升。

​        边缘计算：首先跟大家说一下，为什么要在这里提到边缘计算呢？因为本文讲的是docker, docker中提到了边缘计算。所谓的边缘计算是指在接近数据源的地方进行数据的处理，而不是将数据集中到一起进行处理，边缘计算可以实现数据的实时分析，将有价值的数据过滤后丢给云端。下面给一张图方便大家的理解：

![](images/1.gif)

​	我们在回到本节标题“什么是docker”，我们在介绍完上面这些名词后，会发现无论当今所流行的不论是分布式、集群还是微服务都面临着一个问题：运维和硬件成本的急剧上升。那么docker的出现就是为了解决这个问题：解决运维和硬件成本的问题。

　　提到这里结合自己的工作经历跟大家讲解一下我以前在某家公司是如何解决这个问题的，我们公司购买一台服务器，然后在服务器上虚拟出多个计算机，然后在虚拟的机器上部署我们的应用，所谓虚拟机是借助于一些软件虚拟出一台和我们的物理机一样的机器，也有CPU、内存、硬盘、光驱等。虽然我们可以在一台真实的物理机上虚拟出多台机器，但是每个机器上其实都是有一套完整的操作系统，那么多台虚拟机上就有多套操作系统，这些操作系统也是要消耗物理机的资源的，那么如何解决这个问题呢？这同样回到我们该节的主题“什么是docker”。

<img src="images\2.png" style="height:400px;width:390px;"><img src="images\3.png" style="height:400px;width:410px;">

### 二. Docker的优点

其实这个问题，我们在第一节“什么是docker”这个章节已经给出了答案。在本节我们会给出系统的总结：

#### **2.1 资源的复用**

​       上节笔者说到我们公司在解决运维和机器成本问题的时候说到，通过传统的虚拟机的方式每一台虚拟机都有一套完整的操作系统，那么我们能不能就使用一个操作系统，每个隔离的进程只运行我们的应用和所依赖的第三方软件，docker恰恰可以解决这个问题。

#### **2.2 一致的环境**

​       我相信做过开发的朋友都有这样的经历，我们在本地开发一个应用，尤其是分布式应用，我们需要在本地安装多台虚拟机，在本地测试各种功能完好。接着修改各种参数后辛辛苦苦部署到测试机上后，测试的同事经过紧张、严谨的测试，一切都那么的prefect。当我们高高兴兴的修改完各种参数后部署到生产环境，我嘞个擦，各种问题都出现。开发人员经常挂在嘴边的几句话“昨天我跑着还是好好的呀”，“测试的时候还是好好的呀”，导致开发人员说这些话的原因是因为开发、测试、生产环境的不一致所导致的。docker也可以解决这个问题，docker的镜像提供了除内核以外完整运行环境，确保的环境的一致性。

#### **2.3 启动速度更快**

​      以往的虚拟机的方式启动的时候需要的时间会很长，因为要启动操作系统，可能需要几分钟甚至更长。但是docker启动只需要几秒、几毫秒。

#### **2.4 应用的迁移**

​      给朋友举个例子，以前你的应用部署在阿里云上，那么那天你的领导需要将应用签署到腾讯云上，使用docker的话，会变得非常的简单。

### 三. Docker的安装

​	Docker安装步骤的官网地址：<https://docs.docker.com/install/linux/docker-ce/ubuntu/>

#### 3.1配置仓库

```
yum install -y yum-utils \
  device-mapper-persistent-data \
  lvm2
```

#### 3.2 开始安装

**配置docker的下载源**

```
yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

**安装docker**

```
yum -y install docker-ce docker-ce-cli containerd.io
```

#### 3.3  验证docker是否安装成功

```
docker -v       #查看docker的版本信息
docker info     #查看docker的基本信息，例如多少镜像，多少个容器等
docker help    #查看docker命令
```

### 四. **docker镜像与容器**

​	docker的镜像与容器是docker中两个至关重要的概念，首先给各位读者解释一下这两个概念。镜像，我们从字面意思上看，镜子里成像，我们人站在镜子面前，在镜子中会呈现一个完整的我们(包括我们的着装、表情、发型等等)。那么在软件领域的镜像是指对数据按照一定的格式的一个完整的拷贝；容器是镜像运行时的实体，比如说镜像是个类，当没有被加载的时候，它是存放在我们的硬盘上的，而容器是对象，对象只有在程序运行的时候才会被创建，并且一个类可以创建N多个对象，对应到我们的镜像与容器，一个镜像可以用于创建多个容器，每个容器运行在各自的namespace中间。

​	镜像是对文件的副本，能被特定的软件或者硬件锁识别。

​	在本节我们粗略的说一下镜像，重点在于讲解容器，因为容器一定要用到镜像，所有我们又不得不提，会有专门的篇章来介绍镜像。

#### 4.1 **镜像仓库的配置**

​	所谓的镜像仓库就是镜像集中存放的位置，docker默认的镜像仓库地址是 [https://hub.docker.com](https://hub.docker.com/)，由于该地址服务器不在内地，访问的速度可能会有一定的影响。所以我们会经常将docker默认的镜像仓库换成阿里巴巴或者163的镜像仓库地址，以便加快我们的访问速度，本小节我们以阿里巴巴的镜像仓库为例为大家讲解。

​	A. 注册阿里云账户，地址为：<https://account.aliyun.com/login/login.htm>

![](images\4.png)

​	B.进入到阿里云控制台，选择“容器镜像服务”

![](images\5.png)

​	C.进入到“镜像加速器”，在右侧会出现“加速器地址”以及如何配置加速器地址

![](images\6.png)

​	D. 在 /etc/docker/目录下是没有 daemon.json文件的，所有你要进入到 /etc/docker/ 目录下执行 **touch  daemon.json**  命令创建 daemon.json文件，如下图所示：

![](images\8.png)

​	E.在新创建的daemon.json文件中加入如下内容

```
{
  "registry-mirrors": ["https://zbhswmwr.mirror.aliyuncs.com"]
}
```

​	F.执行如下命令

```
sudo systemctl daemon-reload                  #重新加载守护进程
sudo systemctl restart docker                 #重启docker
```

4.2 **docker镜像的基本命令**

​	A.查看docker本地有多少镜像：**docker images** 

![](images\9.png)

 	B.拉取镜像仓库的某个镜像：**docker pull 镜像名:tag**，例如：**docker pull hello-world:linux**	

![](images\10.png)

​	C.只查看镜像的ID: **docker images -aq**

![](images\11.png)

​	D.删除一个镜像： **docker rmi 镜像名[:tag]**  或者  **docker rmi 镜像ID**    

![](images\12.png)

### 五. **容器的基本命令**

 A. 容器的启动：**docker run 镜像名:tag**  

![](images\14.png)

B.查看正在运行的容器：**docker container ls** 或者 **docker ps** 或者 **docker ps -n 2**

![](images\15.png)

注：docker ps -n 2表示查看最近运行或者运行过的两个容器。

C.查看所有的容器，包括已经停止了的容器：**docker container ls -a** 或者 **docker ps -a**

![](images\15_1.png)

D.以交互的方式启动容器，例如我们启动一个centos容器：**docker run -i -t centos**

![](images\16.png)

   	注意：-t的是宿主机分配一个终端，并将该终端绑定到标准的输入上；-i让容器的标准输入保持打开状态。二者都是联合在一起使用的。

 	如果以上述的方式启动centos容器，那么我们如何回到我们的宿主机呢？有两种方式：

　　          1). 执行 exit 命令，该命令会关闭容器，然后退出。

​                  2).按住ctrl + p + q, 容器不关闭，只是退出。

 E.以后台进程的方式启动容器：**docker run -d centos** 

![](images\18.png)

​	细心的你一定会发现，我们以守护进程的方式启动容器后，容器却已经退出了(status为Exited)。原因是因为docker容器启动后必须要有一个前台进程，说白了就是一直要有事情干，那么怎么能让他有事干了，我们可以开启一个一直挂起的命令，例如：top tail，否则就会自动退出。那么有什么解决方法呢？其实这个问题解决的方法笔者搜集到三种(都是在网上查找的)：

​	1).以交互的方式启动容器，然后通过ctrl + p + q 不关闭容器退出容器，这种方式在上面已经给大家提到过：

```
docker run -it centos 
```

　　2).通过一个死循环间歇性的不停的输出一个字符串 ：

```
docker run -d centos /bin/bash -c "while true;do echo hello world;sleep 2;done"
```

​        3).通过我们上面提到过的top命令的方式开启一个前台进程，让容器有事情干：

```
docker run -d centos /usr/bin/top -b
```

F.容器的停止，容器的停止有两种方式：1.**docker stop 容器ID**；2.**docker kill 容器ID**。方式一是一种平滑、优雅的方式关闭容器；方式二是暴力的方式关闭容器。

![](images\19.png)

G.删除容器：**docker rm [-f] 容器ID**。-f表示强制删除，正在运行的容器用该命令。

![](images\20.png)

H.查看容器的日志：d**ocker logs -t -f 容器ID**。-t是显示日志的时间，-f是监视日志的增长。

![](images\21.png)

I.重新进入已经退出的容器：**docker attach 容器ID** 

![](images\22.png)

 J.不进入到容器，直接通过命令操作容器：**docker exec 容器ID ls -l /**   

![](images\23.png)



**启动mysql的命令：**

```
docker run --name myMysql -e MYSQL_ROOT_PASSWORD=7890 -p 3307:3306 -d mysql:5.7.27
```

备注：启动mysql容器，指定容器名为myMysql，指定root的密码是7890，将宿主机的3307端口映射到mysql容器中的3306端口。

