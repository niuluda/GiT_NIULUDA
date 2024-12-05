如果你想自己创建一个新分支并切换到这个分支，可以按照以下步骤操作：

------

### 1. **创建并切换到新分支**

使用以下命令可以快速创建一个新分支并切换到它：

```bash
git checkout -b <new-branch-name>
```

或（推荐，Git 2.23+）：

```bash
git switch -c <new-branch-name>
```

#### 示例：

```bash
git switch -c my-new-branch
```

此命令会创建名为 `my-new-branch` 的分支并自动切换到它。

------

### 2. **验证当前分支**

可以用以下命令验证你是否切换到了新分支：

```bash
git branch
```

带有 `*` 的分支是当前分支。

------

### 3. **在新分支上进行操作**

在新分支上，你可以按照正常的 Git 流程操作：

- **添加改动**：

  ```bash
  git add .
  ```

- **提交改动**：

  ```bash
  git commit -m "Your commit message"
  ```

------

### 4. **将新分支推送到远程**

如果你需要将新分支推送到远程仓库，可以使用以下命令：

```bash
git push -u origin <new-branch-name>
```

#### 示例：

```bash
git push -u origin my-new-branch
```

`-u` 参数会将新分支与远程分支关联，之后可以直接使用 `git push` 推送。

------

### 5. **查看分支信息**

你可以使用以下命令查看本地和远程的所有分支：

#### 查看本地分支：

```bash
git branch
```

#### 查看所有分支（本地和远程）：

```bash
git branch -a
```

------

### 6. **注意事项**

- 如果你在新分支上进行开发，请确保从最新的主分支（如 `main` 或 `master`）切换，以避免基于旧代码创建新分支。

  ```bash
  git checkout main
  git pull
  git switch -c my-new-branch
  ```

- 当工作完成后，你可以通过 `git merge` 或 `git rebase` 将新分支的内容合并回主分支。

------

### 总结

1. 创建并切换新分支：

   ```bash
   git switch -c <new-branch-name>
   ```

2. 提交改动并推送到远程：

   ```bash
   git add .
   git commit -m "Commit message"
   git push -u origin <new-branch-name> : <new-branch-name>
   ```