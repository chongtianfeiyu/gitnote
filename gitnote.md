git 使用场景总结(bash)
1、建立一个本地git仓库
mkdir 一个目录
git init
git config --global user.name "trilever"
git config --global user.email"trilever31204@163.com"

然后目录中出现一个.git目录，这个目录用于对这个仓库中文件进行管理。我们自己的文件放置.git目录之外，.git目录用于对它们进行管理。

2、将一个文件提交到本地git库（就是让git软件对这个文件实施管理）
新建：t1.txt文件
git add t1.txt//将文件加入到git库中
git commint -m "the first version"

这样就将这个文件提交到本地git库中，让git软件帮助管理这个文件。
注：可以连续add多个文件，然后统一一次进行commit。

3、当我们讲一个文件进行修改了之后，那该怎么进行提交到git库中
git status//用于查看这个git库中一切内容是否有什么变化。这个命令会显示我们对这个文件进行了修改。
git diff t1.txt//然后用这个命令，显示我们对文件做了什么样的修改，具体的修改内容。
然后进行add&&commit
git add t1.txt//将文件加入到git库中
git commint -m "the second version"

4、我们会对某个文件进行多次修改，那么怎么看到历次都进行了哪些修改？
git log t1.txt//显示对这个文件的历次修改历史，查看提交历史。

5、那么怎样回退到以前的某一个版本？
a、回退到上一个版本(git中以本版本作为HEAD版本，HEAD^表示本版本的上一个版本)
git reset --hard HEAD^
这样就将这个文件还原到本版本之前的那个版本了。
往上100个版本：
git reset --hard HEAD~100

此时，如果使用git log，就会发现最新的那个版本已经不见了，也就是还原之后，最后更改的版本消失了。怎么回到最后的那个版本？
只要这个命令行没有关闭，那么就可以找到最后更改的那个版本的commit id。
可以使用
git reset --hard "commit id"//回到任意指定的版本号，当然版本好不用写全，git会自动收缩。让这个版本号指向哪儿，就回到那个版本。

如果，关掉了这个命令行，找不到那个commit id怎么办？
使用
git reflog//记录每一次git命令，查看命令历史。
这样就可以找到想要的版本号。

6、git的工作区：就是我们创建的这个目录
7、git的版本库：就是.git目录，版本库里有一个index，这是一个暂存区，add就是将文件加到这个暂存区里面。版本库里还有一个自动生成的master分支，commit就是将暂存区的内容提交到这个分支里面。

8、git并不是管理文件，而是在管理修改。对于一个文件的任何一次修改，都是要先add到暂存区，然后commit到本地分支里面。

9、怎样撤销最近一次对工作区文件的修改？
git checkout --t1.txt
用于恢复工作区中文件的修改。也就是说本次修改没有add。

在本次修改add之后。
git reset HEAD t1.txt可以撤销add了的修改，重新放回到工作区。使得暂存区的内容清空。然后再使用checkout恢复工作区中文件的修改。就将文件恢复到最初的样子……没有修改之前。

使用5中命令可以恢复commit了的修改。

但是，一旦将本地文件库中的修改提交到远程的文件库中，就不能恢复了。


10、怎样在版本库中删除一个文件？
删除文件也是一种修改。
我们在目录中直接删除文件之后，git并不知道。这时候，需要使用
git rm//git库中删除文件
git commit -m "rm the file"//将修改提交，这样，文件就从版本库中删除了。

如果，我们在目录中删除了文件，也就是在工作区修改了文件，通过checkout可以恢复。
实际上，checkout的作用就是，用版本库中的文件版本替换目录中的文件版本。
如果commit之后，可以通过reser进行恢复版本。

11、将本地git仓库推送到github上：
a、ssh-keygen -t rsa -C "youremail@example.com"//为自己的github产生key
在自己的.ssh目录的.pub文件中复制key，粘贴到github账号中。add key。

b、在github网页中创建github上的仓库。这个仓库的地位和本地的git仓库地位是一样的。

c、将本地仓库与github上的仓库相连接：
进入本仓库的bash中：
git remote add origin git@github.com:trileverwt/java_test.git
d、将本地仓库中的修改commit之后，add&&commit。
git push origin master。
这样就将本地仓库中的修改提交到远程仓库中。

12、怎样从别人的项目中pull下来东西。
进入本地某一个大目录，这个目录中放的是所有的git项目文件夹。
git clone git@github.com:trileverwt/linux.git

