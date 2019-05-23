#restful风格

统一代码风格，便于团队开发，特别是前后端分离，面向接口开发

URI：  /资源名称/资源标识       HTTP请求方式区分对资源CRUD操作

get获取资源、post新建资源、put更新资源、delete删除资源

      	普通CRUD（uri来区分操作）       	RestfulCRUD      
  查询  	getEmp                 						emp---GET        
  添加  	addEmp?xxx             					emp---POST       
  修改  	updateEmp?id=xxx&xxx=xx			emp/{id}---PUT   
  删除  	deleteEmp?id=1         					emp/{id}---DELETE

> 看Url就知道要什么
>
> 看http method就知道干什么
> 看http status code就知道结果如何

首先要明确一点：REST 实际上只是一种设计风格，它并不是标准。（所以你可以看到网上一大堆的各种最佳实践，设计指南，但是没有人说设计标准）。[aisuhua/restful-api-design-references · GitHub](https://link.zhihu.com/?target=https%3A//github.com/aisuhua/restful-api-design-references)

说说几个重要的概念：

**1、REST 是面向资源的，这个概念非常重要，而资源是通过 URI 进行暴露。**
URI 的设计只要负责把资源通过合理方式暴露出来就可以了。对资源的操作与它无关，操作是通过 HTTP动词来体现，所以REST 通过 URI 暴露资源时，会强调不要在 URI 中出现动词。

```
GET /rest/api/getDogs --> GET /rest/api/dogs 获取所有小狗狗 
GET /rest/api/addDogs --> POST /rest/api/dogs 添加一个小狗狗 
GET /rest/api/editDogs/:dog_id --> PUT /rest/api/dogs/:dog_id 修改一个小狗狗 
GET /rest/api/deleteDogs/:dog_id --> DELETE /rest/api/dogs/:dog_id 删除一个小狗狗 
```

左边的这种设计，很明显不符合REST风格，上面已经说了，URI 只负责准确无误的暴露资源，而 getDogs/addDogs...已经包含了对资源的操作，这是不对的。相反右边却满足了，它的操作是使用标准的HTTP动词来体现。

**2、REST很好地利用了HTTP本身就有的一些特征，如HTTP动词、HTTP状态码、HTTP报头等等**
REST API 是基于 HTTP的，所以你的API应该去使用 HTTP的一些标准。这样所有的HTTP客户端（如浏览器）才能够直接理解你的API（当然还有其他好处，如利于缓存等等）。REST 实际上也非常强调应该利用好 HTTP本来就有的特征，而不是只把 HTTP当成一个传输层这么简单了。

HTTP动词

```
GET     获取一个资源 
POST    添加一个资源 
PUT     修改一个资源 
DELETE  删除一个资源 
```

实际上，这四个动词实际上就对应着增删改查四个操作，这就利用了HTTP动词来表示对资源的操作。

HTTP状态码

```
200 OK 
400 Bad Request 
500 Internal Server Error
```

- 所有事情都按预期正确执行完毕 - 成功
- APP 发生了一些错误 – 客户端错误
- API 发生了一些错误 – 服务器端错误

这三种状态与上面的状态码是一一对应的。

HTTP报头

```
Authorization 认证报头 
Cache-Control 缓存报头 
Cnotent-Type  消息体类型报头 
......
```

报头还有很多，不一一列举。HTTP报头是描述HTTP请求或响应的元数据，它的作用是客户端 与 服务器端进行相互通信时，告诉对方应该如何处理本次请求。

**3、超媒体**
老实说，这个词汇我到目前还有没搞得全懂。那也说说自己的理解吧，不一定准确哦，有错误希望指出。
”超媒体“ 你没听说过没关系，”超链接“ 你一定不会陌生。简单来说，”超链接“ 是实现超媒体中的一种方式。”超媒体“希望达到一种就是说在 REST API 中把所有资源给链接起来。它就犹如你打开一个网站的首页，你难道看到的只有首页吗？NO !, 不是的，你可以通过首页查看商品、查看文章、查看论坛。”超媒体“ 就是做这个事情，它利用 API 把所有资源的关系给链接起来了，你看到不会只是一个独立的资源，而是关系网中的一个资源。
”超媒体“ 有点高大上了，老实说，就算你够牛X，写出了一个非常棒的符合”超媒体“的REST API，你的用户即开发者，也不一定能够接受你这种高大上的设计。当然，我相信未来也许可以普及了。



# git命令

## 安装git##

linux

通过一条`sudo apt-get install git`就可以直接完成Git的安装，非常简单。

windows 略

安装完成后，还需要最后一步设置，在命令行输入：

```
$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
```

通过`git init`命令把这个目录变成Git可以管理的仓库：

##git基本操作##

初始化一个Git仓库，使用`git init`命令。

添加文件到Git仓库，分两步：

1. 使用命令`git add <file>`，注意，可反复多次使用，添加多个文件；
2. 使用命令`git commit -m <message>`，完成。



- 要随时掌握工作区的状态，使用`git status`命令。
- 如果`git status`告诉你有文件被修改过，用`git diff`可以查看修改内容。



- `HEAD`指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使用命令

  `git reset --hard commit_id`。(`git reset --hard HEAD^`)

  > （Git必须知道当前版本是哪个版本，在Git中，用`HEAD`表示当前版本，也就是最新的提交`1094adb...`（注意我的提交ID和你的肯定不一样），上一个版本就是`HEAD^`，上上一个版本就是`HEAD^^`，当然往上100个版本写100个`^`比较容易数不过来，所以写成`HEAD~100`。）

- 穿梭前，用`git log`可以查看提交历史，以便确定要回退到哪个版本。

- 要重返未来，用`git reflog`查看命令历史，以便确定要回到未来的哪个版本。



场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令`git checkout -- file`。

场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令`git reset HEAD <file>`，就回到了场景1，第二步按场景1操作。

场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，参考[版本回退](https://www.liaoxuefeng.com/wiki/896043488029600/897013573512192)一节，不过前提是没有推送到远程库。

##git关联远程库##

要关联一个远程库，使用命令`git remote add origin git@server-name:path/repo-name.git`；

```
$ git remote add origin git@github.com:haizhiwei/learngit.git
```

关联后，使用命令`git push -u origin master`第一次推送master分支的所有内容；

> echo "# learngit" >> README.md
> git init
> git add README.md
> git commit -m "first commit"
> git remote add origin https://github.com/Haizhiwei/learngit.git
> git push -u origin master
>
> 
>
> git remote add origin https://github.com/Haizhiwei/learngit.git
> git push -u origin master

此后，每次本地提交后，只要有必要，就可以使用命令`git push origin master`推送最新修改；

分布式版本系统的最大好处之一是在本地工作完全不需要考虑远程库的存在，也就是有没有联网都可以正常工作，而SVN在没有联网的时候是拒绝干活的！当有网络的时候，再把本地提交推送一下就完成了同步，真是太方便了！



要克隆一个仓库，首先必须知道仓库的地址，然后使用`git clone`命令克隆。

 `git clone git@github.com:haizhiwei/gitskills.git`

Git支持多种协议，包括`https`，但通过`ssh`支持的原生`git`协议速度最快。

## git分支

Git鼓励大量使用分支：

查看分支：`git branch`

创建分支：`git branch <name>`

切换分支：`git checkout <name>`

创建+切换分支：`git checkout -b <name>`

合并某分支到当前分支：`git merge <name>`

删除分支：`git branch -d <name>`

用带参数的`git log`也可以看到分支的合并情况：

```
$ git log --graph --pretty=oneline --abbrev-commit
```

当Git无法自动合并分支时，就必须首先解决冲突。解决冲突后，再提交，合并完成。

解决冲突就是把Git合并失败的文件手动编辑为我们希望的内容，再提交。

用`git log --graph`命令可以看到分支合并图。

>  Git分支十分强大，在团队开发中应该充分应用。
>
>  合并分支时，加上`--no-ff`参数就可以用普通模式合并，合并后的历史有分支，能看出来曾经做过合并，而`fast forward`合并就看不出来曾经做过合并。

修复bug时，我们会通过创建新的bug分支进行修复，然后合并，最后删除；

当手头工作没有完成时，先把工作现场`git stash`一下，然后去修复bug，修复后，再`git stash pop`，回到工作现场。

> 一是用`git stash apply`恢复，但是恢复后，stash内容并不删除，你需要用`git stash drop`来删除；
>
> 另一种方式是用`git stash pop`，恢复的同时把stash内容也删了：

你可以多次stash，恢复的时候，先用`git stash list`查看，然后恢复指定的stash，用命令：

```
$ git stash apply stash@{0}
```

因此，多人协作的工作模式通常是这样：

1. 首先，可以试图用`git push origin <branch-name>`推送自己的修改；
2. 如果推送失败，则因为远程分支比你的本地更新，需要先用`git pull`试图合并；
3. 如果合并有冲突，则解决冲突，并在本地提交；
4. 没有冲突或者解决掉冲突后，再用`git push origin <branch-name>`推送就能成功！

如果`git pull`提示`no tracking information`，则说明本地分支和远程分支的链接关系没有创建，用命令`git branch --set-upstream-to <branch-name> origin/<branch-name>`。

这就是多人协作的工作模式，一旦熟悉了，就非常简单。

### 小结

- 查看远程库信息，使用`git remote -v`；

- 本地新建的分支如果不推送到远程，对其他人就是不可见的；

- 从本地推送分支，使用`git push origin branch-name`，如果推送失败，先用`git pull`抓取远程的新提交；

- 在本地创建和远程分支对应的分支，使用`git checkout -b branch-name origin/branch-name`，本地和远程分支的名称最好一致；

  ```
  查看远程分支 $ git branch -r
  $ git checkout -b dev origin/dev
  ```

- 建立本地分支和远程分支的关联，使用`git branch --set-upstream branch-name origin/branch-name`；

- 从远程抓取分支，使用`git pull`，如果有冲突，要先处理冲突。



- rebase操作可以把本地未push的分叉提交历史整理成直线；

- rebase的目的是使得我们在查看历史提交的变化时更容易，因为分叉的提交需要三方对比。

  ?

## git标签##

- 命令`git tag <tagname>`用于新建一个标签，默认为`HEAD`，也可以指定一个commit id；
- 命令`git tag -a <tagname> -m "blablabla..."`可以指定标签信息；
- 命令`git tag`可以查看所有标签。


- 命令`git push origin <tagname>`可以推送一个本地标签；
- 命令`git push origin --tags`可以推送全部未推送过的本地标签；
- 命令`git tag -d <tagname>`可以删除一个本地标签；
- 命令`git push origin :refs/tags/<tagname>`可以删除一个远程标签。





- 忽略某些文件时，需要编写`.gitignore`；

- `.gitignore`文件本身要放到版本库里，并且可以对`.gitignore`做版本管理！

  ?

#javaSE和javaEE #

JavaEE：Java Enterprise Edition，Java企业版，多用于企业级开发，包括web开发等等。企业版本帮助开发和部署可移植、健壮、可伸缩切安全的服务端Java应用。Java EE是在JavaSE的基础上构建的他提供Web 服务、组建模型、管理和通信API.可以用来实现企业级的面向服务体系结构(service-oriented architecture,SOA)和web2.0应用程序。

JavaSE：通常是指Java Standard Edition，Java标准版，就是一般Java程序的开发就可以(如桌面程序)，可以看作是JavaEE的子集。它允许开发和部署在桌面、服务器、嵌入式环境和实施环境中使用的Java应用程序。JavaSE 包括支持Java　Ｗeb服务开发的类，并为Java Platform,Enterprise Edition(Java EE)提供基础。



#Other#

1、在配置文件中指定  spring.profiles.active=dev

?	2、命令行：

?		java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev；

?		可以直接在测试的时候，配置传入命令行参数

?	3、虚拟机参数；

?		-Dspring.profiles.active=dev

