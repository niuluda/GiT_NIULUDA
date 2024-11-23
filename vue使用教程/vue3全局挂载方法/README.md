在 Vue 3 中，可以通过 `app.config.globalProperties` 来全局挂载方法，这样你就可以在任何组件中轻松调用这些方法。下面是如何实现的步骤。

### 1. **挂载全局方法**

在你的 `main.js` 文件中，可以通过 `app.config.globalProperties` 将方法挂载到 Vue 应用中。

```javascript
import { createApp } from 'vue';
import App from './App.vue';

const app = createApp(App);

// 挂载全局方法
app.config.globalProperties.$myGlobalMethod = function () {
  console.log('这是一个全局方法');
};

app.mount('#app');
```

### 2. **在组件中使用全局方法**

在组合式 API 中，可以通过 `getCurrentInstance()` 获取当前实例，然后使用 `proxy` 来访问全局方法。

```javascript
<script setup>
    import { getCurrentInstance } from 'vue';
	//如果写在方法中则为undefined,所以一定在这里定义
    const { proxy } = getCurrentInstance();
	const callGlobalMethod = () => {
      proxy.$myGlobalMethod(); // 调用全局方法
    };
</script>

```

### 3. **完整示例**

结合上述步骤，下面是一个完整的示例：

#### `main.js`

```javascript
import { createApp } from 'vue';
import App from './App.vue';

const app = createApp(App);

// 挂载全局方法 $myGlobalMethod自定义名称,可以不带$
app.config.globalProperties.$myGlobalMethod = function () {
  console.log('这是一个全局方法');
};

app.mount('#app');
```



### 总结

通过这种方式，你可以在 Vue 3 中全局挂载方法，并在任何组件中轻松调用。这在需要共享功能或逻辑的场景中非常有用。