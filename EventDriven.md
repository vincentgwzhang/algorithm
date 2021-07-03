以上文的场景为例，在事件驱动的架构中，从储蓄账户转账到理财账户的过程如下：
0. 储蓄服务将用户的储蓄账户中的金额减少10000，并发布“向理财账户转账”事件；
0. 理财服务获取“转账到理财账户”事件， 更新理财账户，将理财账户的金额增加10000，并发布“理财账户转入”事件；
0. 储蓄服务获取“理财账户转入”事件，结束本次转账交易。


保证数据更新与事件发布原子化的方法，有以下几种：
- 使用本地事务发布事件
- 挖掘数据库事务日志
- 使用事件源


1, 使用本地事务发布事件
一个实现原子化的方法是使用本地事务来更新业务实体和事件列表，由一个独立进程来发布事件
具体来说，就是在存储业务实体状态的数据库中，使用一个事件表来充当消息队列。


这种方法的优点是：
- 使用本地事务，保证了数据被更新时事件一定能够被发布
- 实现简单，只需要系统具备本地事务的能力即可实现

这种方法的一个缺点是，数据更新操作与所要发布的事件之间的对应关系，是由应用的开发者实现的，因此有很大可能出错。




2, 由线程或者进程通过挖掘数据库事务或提交日志来发布事件
这种方法的优点是：
- 要发布的事件直接来源于数据库的事务日志，因此不会出错
- 应用无需关注事件的发布，简化了应用开发者的工作

但是这种方法也有一些缺点：
- 事务日志的格式与所使用的数据库相关，因此事件挖掘 的实现会由于数据库的种类或版本的变化而随之需要修改
- 由于是直接从数据库的更新记录生成事件，因此可能会无法逆向推断出业务逻辑，因此并不适合于所有场景（比如前文所述的转账场景）







3 
事件长期保存在事件仓库（Event Store），使用 API 添加和检索实体的事件。
当有新的事件产生时，也同样会自动更新视图

##event
```
Something that happens during your application that requires a response.
```

##event object
```
The concrete representation of an event.
For example, KeyboardEvent and wx.MouseEvent.
```

###event source
```
Any object that creates events. 
Examples are buttons, menu items, list boxes, or any other widget.
```

###event-driven
```
A program structure where the bulk of time is spent waiting for, or respondingto, events.
```

###命令查询响应分离（CQRS ）
```
Command query responsibility segregation (CQRS) ,Query and Command objects to retrieve and modify data, respectively

```

###本质
```text
representing objects as a sequence of events 
```

###一些其他的知识点
```text
1. 读写分离，就是上面的CQRS，写用的叫COMMAND，读用的叫Query
2. 系统可以写一个视图作为最终结果存到数据库
3. 优点：方便重塑对象的最终过程
```