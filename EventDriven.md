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