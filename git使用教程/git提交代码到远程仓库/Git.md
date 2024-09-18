

## Git工具

1. ## **初始化 Git 仓库**：

   打开命令行终端，进入你的项目目录下(比如:gwtx,需要进入到该目录下)，然后执行以下命令初始化 Git 仓库：

   ```bash
   cd /path/to/your/local/project
   git init
   git remote add origin <GitLab 项目 URL>
   origin为远程仓库别名当然你也可以设置别的
   ```

   这将在项目目录中创建一个名为 `.git` 的隐藏文件夹，用于存储 Git 仓库的相关信息。

2. #### **配置用户身份信息**：

   在进行提交操作之前，需要配置你的用户身份信息，包括电子邮件地址和用户名。执行以下命令设置：

   ```bash
   git config --global user.email "you@example.com"
   git config --global user.name "Your Name"
   ```

   将 `"you@example.com"` 和 `"Your Name"` 替换为你自己的实际信息。

3. **添加文件到暂存区**：

   将你的修改添加到 Git 的暂存区，准备进行提交。执行以下命令：

   ```bash
   git add .
   ```

   这将添加所有修改的文件到暂存区。如果你只想添加特定文件，可以将 `.` 替换为文件名。

4. **提交修改**：

   现在你可以提交你的修改到本地仓库了。执行以下命令：-m "注释"

   ```bash
   git commit -m "Initial commit"
   ```

   将 `"Initial commit"` 替换为你的提交消息，描述本次提交的目的或内容。

5. **提交远程仓库**：

   ```bash
   git push -u origin master
   `git push -u origin master` 是 Git 中用于将本地仓库的内容推送（push）到远程仓库的命令。
   
   具体来说：
   
   - `git push`：这是 Git 的命令，用于将本地仓库的内容推送到远程仓库。
   - `-u`：这是一个选项，也可以写作 `--set-upstream`，它会将本地分支与远程分支关联起来。这意味着以后你只需要执行 `git push` 或 `git pull` 而不需要再指定远程分支和本地分支。
   - `origin`：这是远程仓库的名称或别名，通常是你在本地添加远程仓库时指定的名称，默认情况下是 `origin`。
   - `master`：这是要推送到远程仓库的本地分支的名称。在 Git 中，`master` 通常是默认的主分支名称，但你也可以使用其他分支名称。
   
   因此，`git push -u origin master` 命令的作用是将本地的 `master` 分支的内容推送到远程仓库的 `master` 分支，并将本地的 `master` 分支与远程的 `master` 分支关联起来。
   ```
   
   在 Git 中，`-u` 选项用于将当前分支与远程分支关联起来，使得在以后的推送（`git push`）和拉取（`git pull`）操作中，可以不再指定远程分支和本地分支。
   
   如果你在执行 `git push` 时不加 `-u` 选项，那么每次推送都需要指定远程仓库的别名和分支名称，例如：
   
   ```bash
   git push origin2 master
   ```
   
   而如果你在执行 `git push` 时加上 `-u` 选项，那么 Git 会记住你的推送配置，下次再执行 `git push` 时就不再需要手动指定远程分支和本地分支了。例如，执行以下命令：
   
   ```bash
   git push -u origin2 master
   ```
   
   然后以后再执行 `git push` 时，就可以直接使用以下命令，Git 会自动推送到关联的远程分支：
   
   ```bash
   git push
   ```
   
   总的来说，加上 `-u` 选项可以简化以后的推送操作，使得推送更加方便。

GitLab安装

