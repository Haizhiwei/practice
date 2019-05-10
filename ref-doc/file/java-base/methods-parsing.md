# replace和replaceAll

1)replace的参数是char和CharSequence,即可以支持字符的替换,也支持字符串的替换(CharSequence即字符串序列的意思,说白了也就是字符串); 
2)replaceAll的参数是regex,即基于规则表达式的替换, e.g:可以通过replaceAll("\\d", "*")把一个字符串所有的数字字符都换成星号; 

> 相同点是都是全部替换,即把源字符串中的某一字符或字符串全部换成指定的字符或字符串,如果只想替换第一次出现的,可以使用 replaceFirst(),这个方法也是基于规则表达式的替换,但与replaceAll()不同的是,只替换第一次出现的字符串; 
> ​    另外,如果replaceAll()和replaceFirst()所用的参数据不是基于规则表达式的,则与replace()替换字符串的效果是一样的,即这两者也支持字符串的操作; 
> ​    还有一点注意:**执行了替换操作后,源字符串的内容是没有发生改变的.**

```java
// 将字符串中的'/'替换成'\'的几种方式: 
/*  '\'在java中是一个转义字符，所以需要用两个代表一个。
	例如System.out.println( "\\" ) ;只打印出一个"\"。
	但是'\'也是正则表达式中的转义字符（replaceAll 的参数就是正则表达式），需要用两个代表一个。
	所以：\\\\被java转换成\\,\\又被正则表达式转换成\。 
*/

msgOut= msgIn.replaceAll("/", "\\\\"); 
msgOut= msgIn.replace("/", "\\"); 
msgOut= msgIn.replace('/', '\\');
```