### **📌 NPM & 环境变量 笔记**

------

## **1. `npm -g` 安装了什么？**

当运行 `npm install -g 包名` 时，NPM 会：
 ✅ **下载包** 到本地的 `node_modules` 目录
 ✅ **安装到全局目录**（方便所有项目使用）
 ✅ **创建可执行文件**（如 `vue.cmd`，让你能直接运行 `vue` 命令）
 ✅ **更新全局 `package.json`**（如果需要）

📍 **全局安装目录**（可执行文件路径）：

- **Windows**：`C:\Users\你的用户名\AppData\Roaming\npm`
- **macOS/Linux**：`/usr/local/bin`

------

## **2. 为什么 `npm -g` 命令能在任何目录下使用？**

因为 **可执行文件的路径被加入了 `PATH` 环境变量**，所以你可以随时调用它们。

🔍 **查看 NPM 全局路径**：

```sh
npm root -g  # 查看全局 node_modules 目录
npm bin -g   # 查看全局可执行文件目录
```

🔍 **查看 `vue` 命令在哪**：

```sh
where vue  # Windows
which vue  # macOS/Linux
```

------

## **3. Windows 环境变量：用户变量 vs. 系统变量**

| 变量类型     | 作用范围         | 适用场景                       |
| ------------ | ---------------- | ------------------------------ |
| **用户变量** | 只对当前用户有效 | 适合个人自定义                 |
| **系统变量** | 对所有用户有效   | 适合全局配置（如 `JAVA_HOME`） |

🔍 **查看 `PATH` 变量**：

```sh
# Windows（CMD）
echo %PATH%

# Windows（PowerShell）
$env:Path

# macOS / Linux
echo $PATH
```

------

## **4. `vue.cmd` 是什么？**

在 **Windows**，全局安装的 `vue` 命令会生成两个文件：

- **`vue`**（适用于 macOS/Linux）
- **`vue.cmd`**（Windows 批处理文件）

Windows 运行 `vue` 命令时，实际上执行的是 `vue.cmd`。

------

## **5. 解决 `vue` 命令找不到的问题**

如果 `vue` 命令无法使用，可能是 `PATH` 变量没有包含 `npm` 的全局目录。

✅ **手动添加 `npm` 目录到 `PATH`**：

1. 复制 NPM 全局路径

   ：

   ```
   C:\Users\你的用户名\AppData\Roaming\npm
   ```

2. 打开环境变量

   ：

   - 右键“此电脑” → **属性** → **高级系统设置** → **环境变量**
   - 在 **用户变量** 或 **系统变量** 里找到 `Path`
   - **新增** 这个路径
   - **保存并重启 CMD/PowerShell**

3. 测试 `vue` 命令

   ：

   ```sh
   vue --version
   ```

   🎉 如果能正常输出版本号，说明成功了！

------

## **6. 卸载全局包**

如果不想要某个全局安装的包，可以卸载：

```sh
npm uninstall -g 包名
```

比如：

```sh
npm uninstall -g @vue/cli
```

这样 `vue` 命令就不会生效了。

------

### **🚀 总结**

✅ **`npm -g` 安装的命令可以随时使用，因为 `PATH` 变量包含了 `npm` 全局目录**
 ✅ **Windows 运行 `vue` 命令，实际上执行的是 `vue.cmd`**
 ✅ **如果 `vue` 命令找不到，手动把 `npm` 全局目录加到 `PATH` 即可**
 ✅ **`npm uninstall -g` 可以卸载全局包**

💡 **掌握这些，NPM 就用得更顺手啦！😃**