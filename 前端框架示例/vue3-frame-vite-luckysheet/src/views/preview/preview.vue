<template>
  <div id="luckysheet" :class="$style.luckysheet"/>
</template>

<script setup>
//适配luckysheet的js
import {onMounted, ref, watch} from "vue";
import {exportExcel} from "@/utils/excel/export"
import router from "@/router/index"
//标题
const data = ref([])
console.log(router.currentRoute.value.query.u)
//加载文件流
const loadData = () => {

  var options = {
    container: 'luckysheet', // luckysheet为容器id
    title: '公网结算费用明细 - Excel', // 表头名
    lang: 'zh',
    allowEdit: true,
    myFolderUrl: router ? router.currentRoute.value.fullPath : '/', //配置左上角返回
    showinfobar: true, // 是否显示顶部信息栏
    showsheetbar: true, // 是否显示底部sheet按
    loadUrl:'/api/cost/register/cooperate',
    allowUpdate:true,
    updateUrl:'ws://127.0.0.1:7787/notice/'+router.currentRoute.value.query.u+'/'+1,
    hook: {
      cellUpdated: async (r, c, ov, nv, isRefresh) => {
        //非标题规定列进行修改
        // if(r&&r!=0){
        //   const title = data.value[0][c].v;
        //   const row = data.value[r];
        //   //修改列则开启修改方法
        //   if (props.editCols && props.editCols.length > 0 && props.editCols.includes(c)) {
        //     emits('cellUpdated', {r, c, nv, title, row, isRefresh})
        //   }
        // }

      },
      workbookCreateAfter(o) {
        //工作簿创建后调用
        if (o && o.data && o.data[0]) {
          data.value = o.data[0].data
        }
      }
    }
  }
  //创建websocket
  WebSocket
  let socket;
  socket=new WebSocket("ws://127.0.0.1:7787/notice/3");
  socket.OPEN
  luckysheet.create(options);

  //关闭自带loading和返回按钮
  var loading = document.querySelector('.luckysheet-loading-mask ')
  if (loading) {
    loading.style.display = 'none'
  }
  var back = document.querySelector('.luckysheet_info_detail div.luckysheet_info_detail_back')
  if(back){
    back.style.visibility='hidden'
  }
}
//导出excel
const exportExcelData = () => {
  exportExcel(luckysheet.getAllSheets(), '公网通信费用管理平台_结算审核数据单v1')
}
//初始化
onMounted(() => {
  loadData()
})
</script>

<style>
@import "/node_modules/luckysheet/plugins/css/pluginsCss.css";
@import "/node_modules/luckysheet/plugins/plugins.css";
@import "/node_modules/luckysheet/css/luckysheet.css";
@import "/node_modules/luckysheet/assets/iconfont/iconfont.css";
</style>
<style module>
.luckysheet {
  margin: 0px;
  padding: 0px;
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0px;
  top: 0px;

  :global {
    .luckysheet-share-logo {
      height: 32px;
      width: 152px;
      z-index: 1;
      background-image: url("@/assets/logo.png"); /*更换logo*/
      background-repeat: no-repeat;
      background-size: contain;
      background-color: rebeccapurple!important;
    }
  }
}


</style>
<style scoped>
:deep(.luckysheet-share-logo) {
  height: 45px;
  z-index: 1;
  background-image: url("@/assets/1.svg"); /*更换logo*/
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
