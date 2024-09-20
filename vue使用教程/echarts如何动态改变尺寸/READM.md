在使用 ECharts 时，有时需要根据窗口大小或其他因素调整图表的尺寸。这时可以使用 ECharts 提供的 `resize` 方法。以下是如何使用 `resize` 的步骤和注意事项。

### 使用方法

在 Vue 3 组合式 API 中，你可以从 `vue` 包中导入 `nextTick`。

```javascript
<script setup>
	import {nextTick,ref} from "vue";
	import * as echarts from 'echarts'
	const init=()=>{
        //获取div元素
        var dom=document.getElementById('echart');
        //echarts初始化宽高
        var myChart=echarts.init(dom,null,{width:dom.offsetWidth,height:dom.offsetHeight})
    }
    //尺寸改变
    const resize=()=>{
        myChart.resize{width:宽度,height:高度}
    }
    onmounted(()=>{
        //这个时候直接调用init方法可能dom并没有渲染完成所以获取不到宽高
        //使用nextTick确保元素渲染完成然后再初始化图表
        nextTick(()=>{
            init()
        })
        //动态改变尺寸,添加尺寸监听事件
        window.addEventListener('resize',resize);
    })
</script>
<template>
	<div id="echart"/>    
</template>
```





