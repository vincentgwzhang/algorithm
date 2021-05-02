https://segmentfault.com/a/1190000022384751

* 单一责任原则 The Single Responsibility Principle
  ```
  1. 降低类的复杂度，一个类只负责一项职责
  2. 提高可读性，可维护性
  3. 降低变更带来的风险
  ```
* 开放封闭原则 The Open Closed Principle
  ```
  1. 实体应该对扩展是开放的，对修改是封闭的。即，可扩展(extension)，不可修改(modification)。
  2. should be open for extension, but closed for modification
  ```
* 里氏替换原则 Liskov Substitution Principle
    ```
    1. 一个对象在其出现的任何地方，都可以用子类实例做替换，并且不会导致程序的错误。
    2. 子类尽量不要重写父类的方法
    ```
* 接口分离原则 The Interface Segregation Principle
    ```
    具体实现类，在实现 interface 的时候，不需要实现它不应该实现的函数
    ```
* 依赖倒置原则 The Dependency Inversion Principle
    ```
    1. 高层模块不应该依赖于低层模块，二者都应该依赖于抽象
    2. 抽象不应该依赖于细节，细节应该依赖于抽象
    3. Core is Interface-based programming
    ```
  
* 迪米特法则 Demeter Principle
    ```
    1, 最少知道原则
    2, 降低类之间的耦合
    ```
  
* 合成复用原则 Composite Reuse Principle
  ```
      1. 
  ```