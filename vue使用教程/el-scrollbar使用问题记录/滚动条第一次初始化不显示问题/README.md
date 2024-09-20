el-scrollbar 在使用过程中发现点击这条数据el-dialog第一次打开得时候并没有展示出来滑动条,第二次才正常显示?

```
<script>
	//声明元素
	const bar=ref({});
	//获取数据
	const async getData=()=>{
		await api.getData().then(res=>{
			//因为数据是异步获取的导致数据还没有获取到el-scrollbar没有被撑开
			//所以使用了await等待函数执行完成又重新调用el-scrollbar得update方法进行更新
		})
		//更新滑动条则能正常展示
		bar.value.update();
	}
</script>
<template>
	<el-dialog>
		<el-scrollbar ref="bar"></el-scrollbar>
	</el-dialog>
	
</template>
```

