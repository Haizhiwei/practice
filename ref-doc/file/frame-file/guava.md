

# Guava

##一.介绍:

Guava工程包含了若干被Google的 Java项目广泛依赖 的核心库，例如：集合 [collections] 、缓存 [caching] 、原生类型支持 [primitives support] 、并发库 [concurrency libraries] 、通用注解 [common annotations] 、字符串处理 [string processing] 、I/O 等等。==guava类似Apache Commons工具集==

##二.基本工具包(Base)##

###1.Optional

guava的Optional类似于Java 8新增的Optional类，都是用来处理null的，不过guava的是抽象类，其实现类为Absent和Present，而java.util的是final类。其中一部分方法名是相同的。

Guava用Optional表示可能为null的T类型引用。一个Optional实例可能包含非null的引用（我们称之为引用存在），也可能什么也不包括（称之为引用缺失）。它从不说包含的是null值，而是用存在或缺失来表示。但Optional从不会包含null值引用。

```java

public class OptionalDemo {
   public static void main(String[] args) {
       Integer value1=null;
       Integer value2=10;
       /*创建指定引用的Optional实例，若引用为null则快速失败返回absent()
         absent()创建引用缺失的Optional实例
        */
       Optional<Integer> a=Optional.fromNullable(value1);
       Optional<Integer> b=Optional.of(value2); //返回包含给定的非空引用Optional实例
       System.out.println(sum(a,b));
   }

   private static Integer sum(Optional<Integer> a,Optional<Integer> b){
       //isPresent():如果Optional包含非null的引用（引用存在），返回true
       System.out.println("First param is present: "+a.isPresent());
       System.out.println("Second param is present: "+b.isPresent());
       Integer value1=a.or(0);  //返回Optional所包含的引用,若引用缺失,返回指定的值
       Integer value2=b.get(); //返回所包含的实例,它必须存在,通常在调用该方法时会调用isPresent()判断是否为null
       return value1+value2;
   }
}

```

### 2.Preconditions###

前置条件Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数。它检查的先决条件。其方法失败抛出IllegalArgumentException。

```java

public class PreconditionsDemo {
   public static void main(String[] args) {
       try {
           getValue(5);
       } catch (IndexOutOfBoundsException e){
           System.out.println(e.getMessage());
       }

       try {
           sum(4,null);
       } catch (NullPointerException e){
           System.out.println(e.getMessage());
       }

       try {
           sqrt(-1);
       } catch (IllegalArgumentException e){
           System.out.println(e.getMessage());
       }

   }

   private static double sqrt(double input){
       Preconditions.checkArgument(input>0.0,
               "Illegal Argument passed: Negative value %s.",input);
       return Math.sqrt(input);
   }

   private static int sum(Integer a,Integer b){
       a=Preconditions.checkNotNull(a,
               "Illegal Argument passed: First parameter is Null.");
       b=Preconditions.checkNotNull(b,
               "Illegal Argument passed: First parameter is Null.");
       return a+b;
   }

   private static int getValue(int input){
       int[] data={1,2,3,4,5};
       int index=Preconditions.checkElementIndex(input,data.length,
               "Illegal Argument passed: Invalid index.");
       return data[index];
   }
}
```

###3.Joiner###

Joiner 提供了各种方法来处理字符串加入操作，对象等。

Joiner的实例不可变的，因此是线程安全的。

> Warning: joiner instances are always immutable; a configuration method such as { useForNull} has no effect on the instance it is invoked on! You must store and use the new joiner instance returned by the method. This makes joiners thread-safe, and safe to store as {@code static final} constants.
>
>
> ```java
> {@code 
> // Bad! Do not do this! 
> Joiner joiner = Joiner.on(‘,’); 
> joiner.skipNulls(); // does nothing!分开写跳过null就不起作用了，因为实例不可改变 
> return joiner.join(“wrong”, null, “wrong”);
> }
> ```

```java

public class JoinerDemo {
   public static void main(String[] args) {
       /*
         on:制定拼接符号，如：test1-test2-test3 中的 “-“ 符号
         skipNulls()：忽略NULL,返回一个新的Joiner实例
         useForNull(“Hello”)：NULL的地方都用字符串”Hello”来代替
        */
       StringBuilder sb=new StringBuilder();
       Joiner.on(",").skipNulls().appendTo(sb,"Hello","guava");
       System.out.println(sb);
       System.out.println(Joiner.on(",").useForNull("none").join(1,null,3));
       System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,4,null,6)));
       Map<String,String>map=new HashMap<>();
       map.put("key1","value1");
       map.put("key2","value2");
       map.put("key3","value3");
       System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
   }
}
```

### 4.Splitter###

Splitter 能够将一个字符串按照指定的分隔符拆分成可迭代遍历的字符串集合，Iterable

```java

public class SplitterDemo {
   public static void main(String[] args) {
       /*
         on():指定分隔符来分割字符串
         limit():当分割的子字符串达到了limit个时则停止分割
         fixedLength():根据长度来拆分字符串
         trimResults():去掉子串中的空格
         omitEmptyStrings():去掉空的子串
         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
        */
       System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d"));//[ a, b, c,d]
       System.out.println(Splitter.fixedLength(3).split("1 2 3"));//[1 2,  3]
       System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
       System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));//[1, 2, 3]
       System.out.println(Splitter.on(" ").trimResults().split("1 2 3")); //[1, 2, 3],默认的连接符是,
       System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}
   }
}

```

###5.Objects###

java7及以后的版本建议使用jdk中的Objects类

###6.EventBus###

Guava为我们提供了事件总线EventBus库，它是事件发布-订阅模式的实现，让我们能在领域驱动设计(DDD)中以事件的弱引用本质对我们的模块和领域边界很好的解耦设计。

Guava为我们提供了同步事件EventBus和异步实现AsyncEventBus两个事件总线，他们都不是单例的。

Guava发布的事件默认不会处理线程安全的，但我们可以标注@AllowConcurrentEvents来保证其线程安全

如果Listener A监听Event A, 而Event A有一个子类Event B, 此时Listener A将同时接收Event A和B消息

事件

```java

//Guava 发布-订阅模式中传递的事件,是一个普通的POJO类
public class OrderEvent {  //事件
   private String message;

   public OrderEvent(String message) {
       this.message = message;
   }

   public String getMessage() {
       return message;
   }
}
```

订阅

```java

public class EventListener { //订阅者

   //@Subscribe保证有且只有一个输入参数,如果你需要订阅某种类型的消息,只需要在指定的方法上加上@Subscribe注解即可
   @Subscribe
   public void listen(OrderEvent event){
       System.out.println("receive message: "+event.getMessage());
   }

   /*
     一个subscriber也可以同时订阅多个事件
     Guava会通过事件类型来和订阅方法的形参来决定到底调用subscriber的哪个订阅方法
    */
   @Subscribe
   public void listen(String message){
       System.out.println("receive message: "+message);
   }
}
```

多个订阅

```java

public class MultiEventListener {
  @Subscribe
   public void listen(OrderEvent event){
       System.out.println("receive msg: "+event.getMessage());
   }

   @Subscribe
   public void listen(String message){
       System.out.println("receive msg: "+message);
   }
}
```

```java

public class EventBusDemo {
   public static void main(String[] args) {
       EventBus eventBus=new EventBus("jack");
       /*
         如果多个subscriber订阅了同一个事件,那么每个subscriber都将收到事件通知
         并且收到事件通知的顺序跟注册的顺序保持一致
        */
       eventBus.register(new EventListener()); //注册订阅者
       eventBus.register(new MultiEventListener());
       eventBus.post(new OrderEvent("hello")); //发布事件
       eventBus.post(new OrderEvent("world"));
       eventBus.post("!");
   }
}
```

**DeadEvent**

如果EventBus发送的消息都不是订阅者关心的称之为Dead Event。

```java

public class DeadEventListener {
   boolean isDelivered=true;

   @Subscribe
   public void listen(DeadEvent event){
       isDelivered=false;
       System.out.println(event.getSource().getClass()+"  "+event.getEvent()); //source通常是EventBus
   }

   public boolean isDelivered() {
       return isDelivered;
   }
}
```

###Collection

不可变集合
==不可变对象有很多优点，包括：==

当对象被不可信的库调用时，不可变形式是安全的；
不可变对象被多个线程调用时，不存在竞态条件问题
不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；

不可变对象因为有固定不变，可以作为常量来安全使用。

JDK也提供了Collections.unmodifiableXXX方法把集合包装为不可变形式，但：

笨重而且累赘：不能舒适地用在所有想做防御性拷贝的场景；

不安全：要保证没人通过原集合的引用进行修改，返回的集合才是事实上不可变的；

低效：包装过的集合仍然保有可变集合的开销，比如并发修改的检查、散列表的额外空间，等等。

==创建不可变集合方法：==

copyOf方法，如ImmutableSet.copyOf(set);

of方法，如ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);

Builder工具

```java
public class ImmutableDemo {
   public static void main(String[] args) {
       ImmutableSet<String> set=ImmutableSet.of("a","b","c","d");
       ImmutableSet<String> set1=ImmutableSet.copyOf(set);
       ImmutableSet<String> set2=ImmutableSet.<String>builder().addAll(set).add("e").build();
       System.out.println(set);
       ImmutableList<String> list=set.asList();
   }
}
```

###新型集合类

#### Multiset

Multiset可统计一个词在文档中出现了多少次

```java
public class MultiSetDemo {
   public static void main(String[] args) {
       Multiset<String> set=LinkedHashMultiset.create();
       set.add("a");
       set.add("a");
       set.add("a");
       set.add("a");
       set.setCount("a",5); //添加或删除指定元素使其在集合中的数量是count
       System.out.println(set.count("a")); //给定元素在Multiset中的计数
       System.out.println(set);
       System.out.println(set.size()); //所有元素计数的总和,包括重复元素
       System.out.println(set.elementSet().size()); //所有元素计数的总和,不包括重复元素
       set.clear(); //清空集合
       System.out.println(set);

   }
}
```

#### Multimap

Multimap可以很容易地把一个键映射到多个值。换句话说，Multimap是把键映射到任意多个值的一般方式。

```java
public class MultiMapDemo {
   public static void main(String[] args) {
       Multimap<String,Integer> map= HashMultimap.create(); //Multimap是把键映射到任意多个值的一般方式
       map.put("a",1); //key相同时不会覆盖原value
       map.put("a",2);
       map.put("a",3);
       System.out.println(map); //{a=[1, 2, 3]}
       System.out.println(map.get("a")); //返回的是集合
       System.out.println(map.size()); //返回所有”键-单个值映射”的个数,而非不同键的个数
       System.out.println(map.keySet().size()); //返回不同key的个数
       Map<String,Collection<Integer>> mapView=map.asMap();
   }
}
```

#### BiMap

BiMap

```java
public class BitMapDemo {
   public static void main(String[] args) {
       BiMap<String,String> biMap= HashBiMap.create();
       biMap.put("sina","sina.com");
       biMap.put("qq","qq.com");
       biMap.put("sina","sina.cn"); //会覆盖原来的value
       /*
         在BiMap中,如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常
         如果对特定值,你想要强制替换它的键，请使用 BiMap.forcePut(key, value)
        */
       biMap.put("tecent","qq.com"); //抛出异常
       biMap.forcePut("tecent","qq.com"); //强制替换key
       System.out.println(biMap);
       System.out.println(biMap.inverse().get("sina.com")); //通过value找key
       System.out.println(biMap.inverse().inverse()==biMap); //true

   }
}
```

#### Table

Table它有两个支持所有类型的键：”行”和”列”。

```java

public class TableDemo {
   public static void main(String[] args) {
       //记录学生在某门课上的成绩
       Table<String,String,Integer> table= HashBasedTable.create();
       table.put("jack","java",100);
       table.put("jack","c",90);
       table.put("mike","java",93);
       table.put("mike","c",100);
       Set<Table.Cell<String,String,Integer>> cells=table.cellSet();
       for (Table.Cell<String,String,Integer> cell : cells) {
           System.out.println(cell.getRowKey()+" "+cell.getColumnKey()+" "+cell.getValue());
       }
       System.out.println(table.row("jack"));
       System.out.println(table);
       System.out.println(table.rowKeySet());
       System.out.println(table.columnKeySet());
       System.out.println(table.values());
   }
}
```

#### Collections

##### filter（）：只保留集合中满足特定要求的元素

```java
public class FilterDemo {
   public static void main(String[] args) {
       List<String> list= Lists.newArrayList("moon","dad","refer","son");
       Collection<String> palindromeList= Collections2.filter(list, input -> {
           return new StringBuilder(input).reverse().toString().equals(input); //找回文串
       });
       System.out.println(palindromeList);
   }
}
```

##### transform（）：类型转换

```java
public class TransformDemo {
   public static void main(String[] args) {
       Set<Long> times= Sets.newHashSet();
       times.add(91299990701L);
       times.add(9320001010L);
       times.add(9920170621L);
       Collection<String> timeStrCol= Collections2.transform(times, new Function<Long, String>() {
           @Nullable
           @Override
           public String apply(@Nullable Long input) {
               return new SimpleDateFormat("yyyy-MM-dd").format(input);
           }
       });
       System.out.println(timeStrCol);
   }
}
```

##### 多个Function组合

```java
public class TransformDemo {
 public static void main(String[] args) {   
	List<String> list= Lists.newArrayList("abcde","good","happiness");
   //确保容器中的字符串长度不超过5
   Function<String,String> f1=new Function<String, String>() {
       @Nullable
       @Override
       public String apply(@Nullable String input) {
           return input.length()>5?input.substring(0,5):input;
       }
   };
   //转成大写
   Function<String,String> f2=new Function<String, String>() {
       @Nullable
       @Override
       public String apply(@Nullable String input) {
           return input.toUpperCase();
       }
   };
   Function<String,String> function=Functions.compose(f1,f2);
   Collection<String> results=Collections2.transform(list,function);
   System.out.println(results);
  }
}
```
#####  集合操作：交集、差集、并集

```java
public class CollectionsDemo {
 public static void main(String[] args) {
   Set<Integer> set1= Sets.newHashSet(1,2,3,4,5);
   Set<Integer> set2=Sets.newHashSet(3,4,5,6);
   Sets.SetView<Integer> inter=Sets.intersection(set1,set2); //交集
   System.out.println(inter);
   Sets.SetView<Integer> diff=Sets.difference(set1,set2); //差集,在A中不在B中
   System.out.println(diff);
   Sets.SetView<Integer> union=Sets.union(set1,set2); //并集
   System.out.println(union);
    }
}
```
####  Cache

缓存在很多场景下都是相当有用的。例如，计算或检索一个值的代价很高，并且对同样的输入需要不止一次获取值的时候，就应当考虑使用缓存。

Guava Cache与ConcurrentMap很相似，但也不完全一样。最基本的区别是ConcurrentMap会一直保存所有添加的元素，直到显式地移除。相对地，Guava Cache为了限制内存占用，通常都设定为自动回收元素。在某些场景下，尽管LoadingCache 不回收元素，它也是很有用的，因为它会自动加载缓存。

Guava Cache是一个全内存的本地缓存实现，它提供了线程安全的实现机制。

通常来说，Guava Cache适用于：

你愿意消耗一些内存空间来提升速度。

你预料到某些键会被查询一次以上。

缓存中存放的数据总量不会超出内存容量。（Guava Cache是单个应用运行时的本地缓存。它不把数据存放到文件或外部服务器。

如果这不符合你的需求，请尝试Memcached这类工具）

Guava Cache有两种创建方式：

cacheLoader
callable callback

LoadingCache是附带CacheLoader构建而成的缓存实现

```java

public class LoadingCacheDemo {
   public static void main(String[] args) {
       LoadingCache<String,String> cache= CacheBuilder.newBuilder()
               .maximumSize(100) //最大缓存数目
               .expireAfterAccess(1, TimeUnit.SECONDS) //缓存1秒后过期
               .build(new CacheLoader<String, String>() {
                   @Override
                   public String load(String key) throws Exception {
                       return key;
                   }
               });
       cache.put("j","java");
       cache.put("c","cpp");
       cache.put("s","scala");
       cache.put("g","go");
       try {
           System.out.println(cache.get("j"));
           TimeUnit.SECONDS.sleep(2);
           System.out.println(cache.get("s")); //输出s
       } catch (ExecutionException | InterruptedException e) {
           e.printStackTrace();
       }
   }
}

```

```java

public class CallbackDemo {
   public static void main(String[] args) {
       Cache<String,String> cache= CacheBuilder.newBuilder()
               .maximumSize(100)
               .expireAfterAccess(1, TimeUnit.SECONDS)
               .build();
       try {
           String result=cache.get("java", () -> "hello java");
           System.out.println(result);
       } catch (ExecutionException e) {
           e.printStackTrace();
       }
   }
}

```

refresh机制： 

- LoadingCache.refresh(K) 在生成新的value的时候，旧的value依然会被使用。 
- CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value 
- CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache

#### 并发

ListenableFuture
传统JDK中的Future通过异步的方式计算返回结果:在多线程运算中可能或者可能在没有结束返回结果，Future是运行中的多线程的一个引用句柄，确保在服务执行返回一个Result。

ListenableFuture可以允许你注册回调方法(callbacks)，在运算（多线程执行）完成的时候进行调用, 或者在运算（多线程执行）完成后立即执行。这样简单的改进，使得可以明显的支持更多的操作，这样的功能在JDK concurrent中的Future是不支持的。

```java
public class ListenableFutureDemo {
 public static void main(String[] args) {
   //将ExecutorService装饰成ListeningExecutorService
   ListeningExecutorService service= MoreExecutors.listeningDecorator(
           Executors.newCachedThreadPool());

   //通过异步的方式计算返回结果
   ListenableFuture<String> future=service.submit(() -> {
       System.out.println("call execute..");
       return "task success!";
   });

   //有两种方法可以执行此Future并执行Future完成之后的回调函数
   future.addListener(() -> {  //该方法会在多线程运算完的时候,指定的Runnable参数传入的对象会被指定的Executor执行
       try {
           System.out.println("result: "+future.get());
       } catch (InterruptedException | ExecutionException e) {
           e.printStackTrace();
       }
   },service);

   Futures.addCallback(future, new FutureCallback<String>() {
       @Override
       public void onSuccess(@Nullable String result) {
           System.out.println("callback result: "+result);
       }

       @Override
       public void onFailure(Throwable t) {
           System.out.println(t.getMessage());
       }
   },service);
      }

}
```
####IO####

```java
public class FileDemo {
  public static void main(String[] args) {
 	File file=new File(System.getProperty("user.dir"));
     }

   //写文件

   private void writeFile(String content,File file) throws IOException {

       if (!file.exists()){
           file.createNewFile();
       }
       Files.write(content.getBytes(Charsets.UTF_8),file);

   }

   //读文件

   private List<String> readFile(File file) throws IOException {

       if (!file.exists()){
           return ImmutableList.of(); //避免返回null
       }
       return Files.readLines(file,Charsets.UTF_8);

   }

   //文件复制

   private void copyFile(File from,File to) throws IOException {

       if (!from.exists()){
           return;
       }
       if (!to.exists()){
           to.createNewFile();
       }
       Files.copy(from,to);

   }

}
```
[guava-importnew](http://www.importnew.com/tag/guava)

