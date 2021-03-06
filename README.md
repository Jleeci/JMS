#消息中间件的好处：
	解耦、异步、横向扩展、安全可靠、顺序保证kafka

#消息中间件概述
	什么是中间件？
		非底层操作系统软件，非业务应用软件，不是直接给最终用户使用的，
		不能直接给客户带来价值的软件统称为中间件。
	什么是消息中间件？
		关注于数据的发送和接受，利用高效可靠的异步消息传递机制集成分
		布式系统。
	什么是JMS?
		Java消息服务即JMS,是一个Java平台中关于面向消息中间件的API，用于
		里两个应用程序之间，或分布式系统中发送消息，进行异步通信。
	什么是AMQP?
		AMQP是一个提供统一消息服务的应用层标准协议，基于此协议的客户端与
		消息中间件可传递消息，并不受客户端/中间件不同产品，不同开发语言
		等条件的限制。
![JMS和AMQP的差异.PNG](https://i.imgur.com/lXTLd5J.png)
	消息中间件：
	ActiveMQ、RabbitMQ、Kafka

![消息中间件综合评价](https://i.imgur.com/HAgQfDK.png)
#JMS规范： 
	JMS相关概念
		提供者：实现JMS规范的消息中间件服务器
		客户端：发送或接收消息的应用程序。
		生产者/发布者：创建并发送消息的客户端
		消费者/订阅者：接收并处理消息的客户端
		消息：应用程序之间传递的数据内容
		消息模式：在客户端之间传递消息的方式，JMS定义了主题和队列两种模式
	JMS消息模式
		队列模型
			客户端包括生产者和消费者
			队列中的消息只能被一个消费者消费
			消费者可以随时消费队列中的消息
		主题模型
			客户端包括发布者和订阅者
			主题中的消息被所有订阅者消费
			消费者不能消费订阅之前就发送到主题中的消息
![JMS编码接口之间的关系.PNG](https://i.imgur.com/pxvCrvh.png)
##JMS编码接口
1. ConnectionFactory用于创建连接到消息中间件的连接工厂
2. Connection代表了应用程序和消费服务器之间的通信链路
3. Destination指消息发布和接受的地点，包括队列或主题
4. Session 表示一个单线程的上下文，用于发送和接受消息
5. MessageConsumer由会话创建，用于接收发送到目标的消息
6. MessageProducer由会话撞见，用于发送消息到目标
7. Message是在消费者和生产者之间传送的对象，消息头，一组消息属性，一个消息体。
#JMS代码演练

#使用Spring集成JMS连接ActiveMQ
	
1. ConnectionFactory用于管理连接的连接工厂
2. JmsTemplate用于发送和接受消息的模板类
3. MessageListerner消息监听器

###ConnectionFactory
1. 一个Spring为我们提供的连接池
2. JmsTemplate每次发消息都会重新创建连接，会话和productor
3. Spring中提供了SingleConnectionFactory和CachingConnectionFactory
		
###JmsTemplate
1. 是Spring提供的，只需向Spring容器内注册这个类就可以使用JmsTemplate方便的操作jms
2. JmsTemplate类是线程安全的，可以在整个应用范围使用

###MessageListerner


- 实现一个onMessage方法，该方法指接收一个Message参数。

#ActiveMQ集群配置
##为什么要对消息中间件集群
1. 使用高可用，以排除单点故障引起的服务中断。
2. 实现负载均衡，以提升效率为更多客户提供服务。

###集群方式
 
1. 客户端集群：让多个消费者消费同一个队列
2. Broker clusters:多个Broker之间同步消息
3. Master Slav:实现高可用

####客户端配置：
ActiveMQ失效转移（failover）
   允许当其中一台消息服务器宕机时，客户端在传输层上重新连接到其他消息服务器。
语法：failover:(uri,...uriN)?transportOptions
####transportOptons参数说明
- randomize默认为true，表示在URI列表中选择URI连接时是否采用随机策略
-  initalReconnectDelay默认为10，单位毫秒，表示第一次尝试重连之间等待的时间
-  maxReconnectDelay默认30000，单位毫秒，最长重连的时间间隔
####Broker Cluster集群配置
#####原理
NetworkConnector(网络连接器)

- 网络连机器主要用于配置ActiveMQ服务器与服务器之间的网络通讯方式，用于服务器透传消息
- 网络连接器分为静态连接器和动态连接器
静态连接器
		<networkConnectors>
			<networkConnector uri="static:(tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)"/>
		<networkConnectors>
		
