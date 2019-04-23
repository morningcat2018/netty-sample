
[原文链接](https://netty.io/wiki/new-and-noteworthy-in-4.0.html)

[译文链接](http://ifeve.com/netty-4-0-new/)

此文档涵盖了此次Netty大版本中值得关注的变更点及新特性，以帮助你将自己的应用顺利移植到新版本。

## 项目结构变更

基于netty已经不再是[JBoss.org](http://netty.io/news/2011/11/04/new-web-site.html)的一部分，我们将包名从 org.jboss.netty 变更为 io.netty。

二进制jar包也被分割成了多个子模块，以便用户可以排除非必要的特性。当前结构如下：

Artifact ID | 描述 
 --- | --- 
netty-parent |	Maven parent POM
netty-common |	工具类及日志接口
netty-buffer |	ByteBuf API，用来替换java.nio.ByteBuffer
netty-transport |	Channel API 及核心 transports
netty-transport-rxtx |	Rxtx transport
netty-transport-sctp |	SCTP transport
netty-transport-udt |	UDT transport
netty-handler |	ChannelHandler 的相关实现
netty-codec |	编解码框架，用于编写encoder及decoder
netty-codec-http |	HTTP, Web Sockets, SPDY, and RTSP相关的编解码器
netty-codec-socks |	SOCKS协议相关的编解码器
netty-all |	包含以上所有artifacts的All-in-one的JAR
netty-tarball |	Tarball distribution
netty-example |	样例
netty-testsuite-* |	整合的测试集
netty-microbench |	微基准测试（Microbenchmarks）

所有的artifacts（除了netty-all.jar）都已经是OSGi bundles了，可以直接在你的OSGi容器中使用。

## 通用API变更

- 为使代码更加简洁，Netty在大多数场景都已经支持链式方法。

- 非可配置变量的getter不再有get-前缀。（如：Channel.getRemoteAddress() → Channel.remoteAddress() ）
  - Boolean类型的属性仍然以 is- 开头以避免混淆（如： ‘empty’既是一个形容词也是一个动词，那么 empty() 就会包含两种含义）
  
- 4.0 CR4与4.0 CR5之间的API变更，请参照 [Netty 4.0.0.CR5 released with new-new API](http://netty.io/news/2013/06/18/4-0-0-CR5.html)

## Buffer API变更

#### ChannelBuffer → ByteBuf

在对netty包结构进行如上调整之后，buffer API也可以作为独立包使用了。所以，即使你不把Netty用来作为网络框架，你仍然可以使用buffer API。
因此，`ChannelBuffer` 这个名字也变得不合时宜，我们便将之重命名为 `ByteBuf`。

用于创建buffer的工具类 `ChannelBuffers` 现在被拆分为 `Unpooled` 及 `ByteBufUtil` 两个工具类。
一如其名，4.0引入了`池化的 ByteBuf`，可以使用 `ByteBufAllocator` 的实现类进行分配。

#### ByteBuf 是抽象类而非接口

根据我们的内部性能测试，将接口 ByteBuf 变更为抽象类，能带来约5%的吞吐量提升。

#### 大部分buffer的最大容量都是动态的

3.x版本，buffer有定长（fixed）和动态（dynamic）两种。定长的buffer一旦创建，它的容量就不会再变化。
而动态的buffer在每次操作 write*(…) 时都会根据需要动态调整容量。

4.0开始，所有的buffer都是动态的。并且比旧的动态buffer更加优秀。你可以更加容易和安全的增减buffer的容量。
容易是因为提供了新方法 ByteBuf.capacity(int newCapacity)。而安全则是因为你可以设定buffer的最大容量从而防止其无限扩增。

```java
// 不再使用 dynamicBuffer() - 换为 buffer().
ByteBuf buf = Unpooled.buffer();

// 增加buffer容量
buf.capacity(1024);
...

// 减少buffer容量(最后512字节会被删除)
buf.capacity(512);
```

通过 wrappedBuffer() 创建的包装了单个buffer或者单个byte数组的buffer是唯一的例外。
如果增大其容量就会破坏它包装已存在buffer的意义——节省内存（saving memory copies）。
如果你包装了一个buffer以后还想改变它的容量，那么你需要新建一个拥有足够容量的buffer，并且copy你需要包装的部分过去。


#### 

#### 

#### 

#### 

#### 






