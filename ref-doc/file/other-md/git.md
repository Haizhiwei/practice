#restful���

ͳһ�����񣬱����Ŷӿ������ر���ǰ��˷��룬����ӿڿ���

URI��  /��Դ����/��Դ��ʶ       HTTP����ʽ���ֶ���ԴCRUD����

get��ȡ��Դ��post�½���Դ��put������Դ��deleteɾ����Դ

      	��ͨCRUD��uri�����ֲ�����       	RestfulCRUD      
  ��ѯ  	getEmp                 						emp---GET        
  ���  	addEmp?xxx             					emp---POST       
  �޸�  	updateEmp?id=xxx&xxx=xx			emp/{id}---PUT   
  ɾ��  	deleteEmp?id=1         					emp/{id}---DELETE

> ��Url��֪��Ҫʲô
>
> ��http method��֪����ʲô
> ��http status code��֪��������

����Ҫ��ȷһ�㣺REST ʵ����ֻ��һ����Ʒ���������Ǳ�׼������������Կ�������һ��ѵĸ������ʵ�������ָ�ϣ�����û����˵��Ʊ�׼����[aisuhua/restful-api-design-references �� GitHub](https://link.zhihu.com/?target=https%3A//github.com/aisuhua/restful-api-design-references)

˵˵������Ҫ�ĸ��

**1��REST ��������Դ�ģ��������ǳ���Ҫ������Դ��ͨ�� URI ���б�¶��**
URI �����ֻҪ�������Դͨ������ʽ��¶�����Ϳ����ˡ�����Դ�Ĳ��������޹أ�������ͨ�� HTTP���������֣�����REST ͨ�� URI ��¶��Դʱ����ǿ����Ҫ�� URI �г��ֶ��ʡ�

```
GET /rest/api/getDogs --> GET /rest/api/dogs ��ȡ����С���� 
GET /rest/api/addDogs --> POST /rest/api/dogs ���һ��С���� 
GET /rest/api/editDogs/:dog_id --> PUT /rest/api/dogs/:dog_id �޸�һ��С���� 
GET /rest/api/deleteDogs/:dog_id --> DELETE /rest/api/dogs/:dog_id ɾ��һ��С���� 
```

��ߵ�������ƣ������Բ�����REST��������Ѿ�˵�ˣ�URI ֻ����׼ȷ����ı�¶��Դ���� getDogs/addDogs...�Ѿ������˶���Դ�Ĳ��������ǲ��Եġ��෴�ұ�ȴ�����ˣ����Ĳ�����ʹ�ñ�׼��HTTP���������֡�

**2��REST�ܺõ�������HTTP������е�һЩ��������HTTP���ʡ�HTTP״̬�롢HTTP��ͷ�ȵ�**
REST API �ǻ��� HTTP�ģ��������APIӦ��ȥʹ�� HTTP��һЩ��׼���������е�HTTP�ͻ��ˣ�������������ܹ�ֱ��������API����Ȼ���������ô��������ڻ���ȵȣ���REST ʵ����Ҳ�ǳ�ǿ��Ӧ�����ú� HTTP�������е�������������ֻ�� HTTP����һ���������ô���ˡ�

HTTP����

```
GET     ��ȡһ����Դ 
POST    ���һ����Դ 
PUT     �޸�һ����Դ 
DELETE  ɾ��һ����Դ 
```

ʵ���ϣ����ĸ�����ʵ���ϾͶ�Ӧ����ɾ�Ĳ��ĸ����������������HTTP��������ʾ����Դ�Ĳ�����

HTTP״̬��

```
200 OK 
400 Bad Request 
500 Internal Server Error
```

- �������鶼��Ԥ����ȷִ����� - �ɹ�
- APP ������һЩ���� �C �ͻ��˴���
- API ������һЩ���� �C �������˴���

������״̬�������״̬����һһ��Ӧ�ġ�

HTTP��ͷ

```
Authorization ��֤��ͷ 
Cache-Control ���汨ͷ 
Cnotent-Type  ��Ϣ�����ͱ�ͷ 
......
```

��ͷ���кܶ࣬��һһ�о١�HTTP��ͷ������HTTP�������Ӧ��Ԫ���ݣ����������ǿͻ��� �� �������˽����໥ͨ��ʱ�����߶Է�Ӧ����δ���������

**3����ý��**
��ʵ˵������ʻ��ҵ�Ŀǰ����û���ȫ������Ҳ˵˵�Լ������ɣ���һ��׼ȷŶ���д���ϣ��ָ����
����ý�塰 ��û��˵��û��ϵ���������ӡ� ��һ������İ��������˵���������ӡ� ��ʵ�ֳ�ý���е�һ�ַ�ʽ������ý�塰ϣ���ﵽһ�־���˵�� REST API �а�������Դ�����������������������һ����վ����ҳ�����ѵ�������ֻ����ҳ��NO !, ���ǵģ������ͨ����ҳ�鿴��Ʒ���鿴���¡��鿴��̳������ý�塰 ������������飬������ API ��������Դ�Ĺ�ϵ�����������ˣ��㿴������ֻ��һ����������Դ�����ǹ�ϵ���е�һ����Դ��
����ý�塰 �е�ߴ����ˣ���ʵ˵�������㹻ţX��д����һ���ǳ����ķ��ϡ���ý�塰��REST API������û��������ߣ�Ҳ��һ���ܹ����������ָߴ��ϵ���ơ���Ȼ��������δ��Ҳ������ռ��ˡ�



# git����

## ��װgit##

linux

ͨ��һ��`sudo apt-get install git`�Ϳ���ֱ�����Git�İ�װ���ǳ��򵥡�

windows ��

��װ��ɺ󣬻���Ҫ���һ�����ã������������룺

```
$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
```

ͨ��`git init`��������Ŀ¼���Git���Թ���Ĳֿ⣺

##git��������##

��ʼ��һ��Git�ֿ⣬ʹ��`git init`���

����ļ���Git�ֿ⣬��������

1. ʹ������`git add <file>`��ע�⣬�ɷ������ʹ�ã���Ӷ���ļ���
2. ʹ������`git commit -m <message>`����ɡ�



- Ҫ��ʱ���չ�������״̬��ʹ��`git status`���
- ���`git status`���������ļ����޸Ĺ�����`git diff`���Բ鿴�޸����ݡ�



- `HEAD`ָ��İ汾���ǵ�ǰ�汾����ˣ�Git���������ڰ汾����ʷ֮�䴩��ʹ������

  `git reset --hard commit_id`��(`git reset --hard HEAD^`)

  > ��Git����֪����ǰ�汾���ĸ��汾����Git�У���`HEAD`��ʾ��ǰ�汾��Ҳ�������µ��ύ`1094adb...`��ע���ҵ��ύID����Ŀ϶���һ��������һ���汾����`HEAD^`������һ���汾����`HEAD^^`����Ȼ����100���汾д100��`^`�Ƚ�������������������д��`HEAD~100`����

- ����ǰ����`git log`���Բ鿴�ύ��ʷ���Ա�ȷ��Ҫ���˵��ĸ��汾��

- Ҫ�ط�δ������`git reflog`�鿴������ʷ���Ա�ȷ��Ҫ�ص�δ�����ĸ��汾��



����1����������˹�����ĳ���ļ������ݣ���ֱ�Ӷ������������޸�ʱ��������`git checkout -- file`��

����2�����㲻�������˹�����ĳ���ļ������ݣ�����ӵ����ݴ���ʱ���붪���޸ģ�����������һ��������`git reset HEAD <file>`���ͻص��˳���1���ڶ���������1������

����3���Ѿ��ύ�˲����ʵ��޸ĵ��汾��ʱ����Ҫ���������ύ���ο�[�汾����](https://www.liaoxuefeng.com/wiki/896043488029600/897013573512192)һ�ڣ�����ǰ����û�����͵�Զ�̿⡣

##git����Զ�̿�##

Ҫ����һ��Զ�̿⣬ʹ������`git remote add origin git@server-name:path/repo-name.git`��

```
$ git remote add origin git@github.com:haizhiwei/learngit.git
```

������ʹ������`git push -u origin master`��һ������master��֧���������ݣ�

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

�˺�ÿ�α����ύ��ֻҪ�б�Ҫ���Ϳ���ʹ������`git push origin master`���������޸ģ�

�ֲ�ʽ�汾ϵͳ�����ô�֮һ���ڱ��ع�����ȫ����Ҫ����Զ�̿�Ĵ��ڣ�Ҳ������û������������������������SVN��û��������ʱ���Ǿܾ��ɻ�ģ����������ʱ���ٰѱ����ύ����һ�¾������ͬ��������̫�����ˣ�



Ҫ��¡һ���ֿ⣬���ȱ���֪���ֿ�ĵ�ַ��Ȼ��ʹ��`git clone`�����¡��

 `git clone git@github.com:haizhiwei/gitskills.git`

Git֧�ֶ���Э�飬����`https`����ͨ��`ssh`֧�ֵ�ԭ��`git`Э���ٶ���졣

## git��֧

Git��������ʹ�÷�֧��

�鿴��֧��`git branch`

������֧��`git branch <name>`

�л���֧��`git checkout <name>`

����+�л���֧��`git checkout -b <name>`

�ϲ�ĳ��֧����ǰ��֧��`git merge <name>`

ɾ����֧��`git branch -d <name>`

�ô�������`git log`Ҳ���Կ�����֧�ĺϲ������

```
$ git log --graph --pretty=oneline --abbrev-commit
```

��Git�޷��Զ��ϲ���֧ʱ���ͱ������Ƚ����ͻ�������ͻ�����ύ���ϲ���ɡ�

�����ͻ���ǰ�Git�ϲ�ʧ�ܵ��ļ��ֶ��༭Ϊ����ϣ�������ݣ����ύ��

��`git log --graph`������Կ�����֧�ϲ�ͼ��

>  Git��֧ʮ��ǿ�����Ŷӿ�����Ӧ�ó��Ӧ�á�
>
>  �ϲ���֧ʱ������`--no-ff`�����Ϳ�������ͨģʽ�ϲ����ϲ������ʷ�з�֧���ܿ��������������ϲ�����`fast forward`�ϲ��Ϳ����������������ϲ���

�޸�bugʱ�����ǻ�ͨ�������µ�bug��֧�����޸���Ȼ��ϲ������ɾ����

����ͷ����û�����ʱ���Ȱѹ����ֳ�`git stash`һ�£�Ȼ��ȥ�޸�bug���޸�����`git stash pop`���ص������ֳ���

> һ����`git stash apply`�ָ������ǻָ���stash���ݲ���ɾ��������Ҫ��`git stash drop`��ɾ����
>
> ��һ�ַ�ʽ����`git stash pop`���ָ���ͬʱ��stash����Ҳɾ�ˣ�

����Զ��stash���ָ���ʱ������`git stash list`�鿴��Ȼ��ָ�ָ����stash�������

```
$ git stash apply stash@{0}
```

��ˣ�����Э���Ĺ���ģʽͨ����������

1. ���ȣ�������ͼ��`git push origin <branch-name>`�����Լ����޸ģ�
2. �������ʧ�ܣ�����ΪԶ�̷�֧����ı��ظ��£���Ҫ����`git pull`��ͼ�ϲ���
3. ����ϲ��г�ͻ��������ͻ�����ڱ����ύ��
4. û�г�ͻ���߽������ͻ������`git push origin <branch-name>`���;��ܳɹ���

���`git pull`��ʾ`no tracking information`����˵�����ط�֧��Զ�̷�֧�����ӹ�ϵû�д�����������`git branch --set-upstream-to <branch-name> origin/<branch-name>`��

����Ƕ���Э���Ĺ���ģʽ��һ����Ϥ�ˣ��ͷǳ��򵥡�

### С��

- �鿴Զ�̿���Ϣ��ʹ��`git remote -v`��

- �����½��ķ�֧��������͵�Զ�̣��������˾��ǲ��ɼ��ģ�

- �ӱ������ͷ�֧��ʹ��`git push origin branch-name`���������ʧ�ܣ�����`git pull`ץȡԶ�̵����ύ��

- �ڱ��ش�����Զ�̷�֧��Ӧ�ķ�֧��ʹ��`git checkout -b branch-name origin/branch-name`�����غ�Զ�̷�֧���������һ�£�

  ```
  �鿴Զ�̷�֧ $ git branch -r
  $ git checkout -b dev origin/dev
  ```

- �������ط�֧��Զ�̷�֧�Ĺ�����ʹ��`git branch --set-upstream branch-name origin/branch-name`��

- ��Զ��ץȡ��֧��ʹ��`git pull`������г�ͻ��Ҫ�ȴ����ͻ��



- rebase�������԰ѱ���δpush�ķֲ��ύ��ʷ�����ֱ�ߣ�

- rebase��Ŀ����ʹ�������ڲ鿴��ʷ�ύ�ı仯ʱ�����ף���Ϊ�ֲ���ύ��Ҫ�����Աȡ�

  ?

## git��ǩ##

- ����`git tag <tagname>`�����½�һ����ǩ��Ĭ��Ϊ`HEAD`��Ҳ����ָ��һ��commit id��
- ����`git tag -a <tagname> -m "blablabla..."`����ָ����ǩ��Ϣ��
- ����`git tag`���Բ鿴���б�ǩ��


- ����`git push origin <tagname>`��������һ�����ر�ǩ��
- ����`git push origin --tags`��������ȫ��δ���͹��ı��ر�ǩ��
- ����`git tag -d <tagname>`����ɾ��һ�����ر�ǩ��
- ����`git push origin :refs/tags/<tagname>`����ɾ��һ��Զ�̱�ǩ��





- ����ĳЩ�ļ�ʱ����Ҫ��д`.gitignore`��

- `.gitignore`�ļ�����Ҫ�ŵ��汾������ҿ��Զ�`.gitignore`���汾����

  ?

#javaSE��javaEE #

JavaEE��Java Enterprise Edition��Java��ҵ�棬��������ҵ������������web�����ȵȡ���ҵ�汾���������Ͳ������ֲ����׳���������а�ȫ�ķ����JavaӦ�á�Java EE����JavaSE�Ļ����Ϲ��������ṩWeb �����齨ģ�͡������ͨ��API.��������ʵ����ҵ�������������ϵ�ṹ(service-oriented architecture,SOA)��web2.0Ӧ�ó���

JavaSE��ͨ����ָJava Standard Edition��Java��׼�棬����һ��Java����Ŀ����Ϳ���(���������)�����Կ�����JavaEE���Ӽ������������Ͳ��������桢��������Ƕ��ʽ������ʵʩ������ʹ�õ�JavaӦ�ó���JavaSE ����֧��Java����eb���񿪷����࣬��ΪJava Platform,Enterprise Edition(Java EE)�ṩ������



#Other#

1���������ļ���ָ��  spring.profiles.active=dev

?	2�������У�

?		java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev��

?		����ֱ���ڲ��Ե�ʱ�����ô��������в���

?	3�������������

?		-Dspring.profiles.active=dev

