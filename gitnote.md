git ʹ�ó����ܽ�(bash)
1������һ������git�ֿ�
mkdir һ��Ŀ¼
git init
git config --global user.name "trilever"
git config --global user.email"trilever31204@163.com"

Ȼ��Ŀ¼�г���һ��.gitĿ¼�����Ŀ¼���ڶ�����ֿ����ļ����й����������Լ����ļ�����.gitĿ¼֮�⣬.gitĿ¼���ڶ����ǽ��й�����

2����һ���ļ��ύ������git�⣨������git����������ļ�ʵʩ������
�½���t1.txt�ļ�
git add t1.txt//���ļ����뵽git����
ע�⣺�˴�������ڱ��ع���Ŀ¼��ɾ����һ���ļ�����ʱ��Ͳ�Ӧ����ʹ��add����Ӧ����ʹ��git rm t1.txt��Ȼ���commit��һ���ġ�
git commint -m "the first version"

�����ͽ�����ļ��ύ������git���У���git����������������ļ���
ע����������add����ļ���Ȼ��ͳһһ�ν���commit��

3�������ǽ�һ���ļ������޸���֮���Ǹ���ô�����ύ��git����
git status//���ڲ鿴���git����һ�������Ƿ���ʲô�仯������������ʾ���Ƕ�����ļ��������޸ġ�
git diff t1.txt//Ȼ������������ʾ���Ƕ��ļ�����ʲô�����޸ģ�������޸����ݡ�
Ȼ�����add&&commit
git add t1.txt//���ļ����뵽git����
git commint -m "the second version"

4�����ǻ��ĳ���ļ����ж���޸ģ���ô��ô�������ζ���������Щ�޸ģ�
git log t1.txt//��ʾ������ļ��������޸���ʷ���鿴�ύ��ʷ��

5����ô�������˵���ǰ��ĳһ���汾��
a�����˵���һ���汾(git���Ա��汾��ΪHEAD�汾��HEAD^��ʾ���汾����һ���汾)
git reset --hard HEAD^
�����ͽ�����ļ���ԭ�����汾֮ǰ���Ǹ��汾�ˡ�
����100���汾��
git reset --hard HEAD~100

��ʱ�����ʹ��git log���ͻᷢ�����µ��Ǹ��汾�Ѿ������ˣ�Ҳ���ǻ�ԭ֮�������ĵİ汾��ʧ�ˡ���ô�ص������Ǹ��汾��
ֻҪ���������û�йرգ���ô�Ϳ����ҵ������ĵ��Ǹ��汾��commit id��
����ʹ��
git reset --hard "commit id"//�ص�����ָ���İ汾�ţ���Ȼ�汾�ò���дȫ��git���Զ�������������汾��ָ���Ķ����ͻص��Ǹ��汾��

������ص�����������У��Ҳ����Ǹ�commit id��ô�죿
ʹ��
git reflog//��¼ÿһ��git����鿴������ʷ��
�����Ϳ����ҵ���Ҫ�İ汾�š�

6��git�Ĺ��������������Ǵ��������Ŀ¼
7��git�İ汾�⣺����.gitĿ¼���汾������һ��index������һ���ݴ�����add���ǽ��ļ��ӵ�����ݴ������档�汾���ﻹ��һ���Զ����ɵ�master��֧��commit���ǽ��ݴ����������ύ�������֧���档

8��git�����ǹ����ļ��������ڹ����޸ġ�����һ���ļ����κ�һ���޸ģ�����Ҫ��add���ݴ�����Ȼ��commit�����ط�֧���档

9�������������һ�ζԹ������ļ����޸ģ�
git checkout --t1.txt
���ڻָ����������ļ����޸ġ�Ҳ����˵�����޸�û��add��

�ڱ����޸�add֮��
git reset HEAD t1.txt���Գ���add�˵��޸ģ����·Żص���������ʹ���ݴ�����������ա�Ȼ����ʹ��checkout�ָ����������ļ����޸ġ��ͽ��ļ��ָ�����������ӡ���û���޸�֮ǰ��

ʹ��5��������Իָ�commit�˵��޸ġ�

���ǣ�һ���������ļ����е��޸��ύ��Զ�̵��ļ����У��Ͳ��ָܻ��ˡ�


10�������ڰ汾����ɾ��һ���ļ���
ɾ���ļ�Ҳ��һ���޸ġ�
������Ŀ¼��ֱ��ɾ���ļ�֮��git����֪������ʱ����Ҫʹ��
git rm//git����ɾ���ļ�
git commit -m "rm the file"//���޸��ύ���������ļ��ʹӰ汾����ɾ���ˡ�

�����������Ŀ¼��ɾ�����ļ���Ҳ�����ڹ������޸����ļ���ͨ��checkout���Իָ���
ʵ���ϣ�checkout�����þ��ǣ��ð汾���е��ļ��汾�滻Ŀ¼�е��ļ��汾��
���commit֮�󣬿���ͨ��reser���лָ��汾��

11��������git�ֿ����͵�github�ϣ�
a��ssh-keygen -t rsa -C "youremail@example.com"//Ϊ�Լ���github����key
���Լ���.sshĿ¼��.pub�ļ��и���key��ճ����github�˺��С�add key��

b����github��ҳ�д���github�ϵĲֿ⡣����ֿ�ĵ�λ�ͱ��ص�git�ֿ��λ��һ���ġ�

c�������زֿ���github�ϵĲֿ������ӣ�
���뱾�ֿ��bash�У�
git remote add origin git@github.com:trileverwt/java_test.git
d�������زֿ��е��޸�commit֮��add&&commit��
ÿ��push֮ǰ����Ҫ��pull��Զ�ֿ̲�������ݡ�
git pull origin master
Ȼ���٣�
git push origin master��//
�����ͽ����زֿ��е��޸��ύ��Զ�ֿ̲��С�

12�������ӱ��˵���Ŀ��clone����������
���뱾��ĳһ����Ŀ¼�����Ŀ¼�зŵ������е�git��Ŀ�ļ��С�
git clone git@github.com:trileverwt/linux.git

13��git��������
�������$ git push origin master
��ʾ������Ϣ��error:failed to push som refs to ����.
����취���£�
1��������$ git pull origin master //�Ȱ�Զ�̷�����github������ļ�������
2��������$ git push origin master
3��������ֱ��� fatal: Couldn��t find remote ref master����fatal: ��origin�� does not appear to be a git repository�Լ�fatal: Could not read from remote repository.
4������Ҫ��������$ git remote add origingit@github.com:djqiang/gitdemo.git

14��ʹ��git�ڱ��ش���һ����Ŀ�Ĺ���
$ makdir ~/hello-world    //����һ����Ŀhello-world
$ cd ~/hello-world       //�������Ŀ
$ git init             //��ʼ��
$ touch README
$ git add README        //����README�ļ�
$ git commit -m ��first commit��     //�ύ���£���ע����Ϣ��first commit��
$ git remote add origin git@github.com:defnngj/hello-world.git     //����Զ��github��Ŀ
$ git push -u origin master     //��������Ŀ���µ�github��Ŀ��ȥ

git@github.com:trileverwt/triolog.git