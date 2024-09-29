<template>
  <div id="luckysheet" :class="$style.luckysheet"/>
</template>

<script setup>
//适配luckysheet的js
import LuckyExcel from 'luckyexcel'
import {onMounted, ref, watch} from "vue";
import {exportExcel} from "@/utils/excel/export"
import router from "@/router/index"
//标题
const dataTitle = ref('我的文档')
const data = ref([])
//预览数据
const props = defineProps({
  //展示数据
  previewData: {
    type: Blob,
    required: true
  },
  //是否为修改列
  editCols: {
    type: Array,
    required: false
  }
})
const emits = defineEmits(['cellUpdated']);
// 监听props属性的变化
watch(props, (val) => {
  //当关闭dialog时或者切换别的数据单时会先销毁上一次的sheet
  luckysheet.destroy()
  if(props.previewData&&props.previewData.length!=0){
    //当第二次导出数据有变化时则重新渲染sheet
    analysisFile()
  }


})
//加载文件流
const loadData = (filesIO) => {
  if(filesIO&&filesIO.length>0){
    filesIO.forEach(sheet=>{
      //获取列宽配置
      const config = sheet.config;
      if(config){
        const columnlens = config.columnlen;
        console.log('cl',columnlens)
        if(columnlens){
          const fromEntries = Object.fromEntries(Object.entries(columnlens).map(([k,v])=>[k,v===-2?0:v]).filter(([k,v])=>v===0));
          if(fromEntries){
            //配置隐藏列
            config.colhidden=fromEntries
          }

        }

      }
    })
  }
  var options = {
    container: 'luckysheet', // luckysheet为容器id
    title: '公网结算费用明细 - Excel', // 表头名
    lang: 'zh',
    allowEdit: true,
    myFolderUrl: router ? router.currentRoute.value.fullPath : '/', //配置左上角返回
    showinfobar: true, // 是否显示顶部信息栏
    showsheetbar: true, // 是否显示底部sheet按
    data: filesIO,
    hook: {
      cellUpdated: async (r, c, ov, nv, isRefresh) => {
        //非标题规定列进行修改
        if(r&&r!=0){
          const title = data.value[0][c].v;
          const row = data.value[r];
          //修改列则开启修改方法
          if (props.editCols && props.editCols.length > 0 && props.editCols.includes(c)) {
            emits('cellUpdated', {r, c, nv, title, row, isRefresh})
          }
        }

      },
      workbookCreateAfter(o) {
        //工作簿创建后调用
        if (o && o.data && o.data[0]) {
          data.value = o.data[0].data
        }
      }
    }
  }

  luckysheet.create(options);
  //sheet加载完成后则dom获取导出按钮设置监听点击导出
  var exportBtn = document.getElementById('luckysheet-exportXlsx-btn-title')
  exportBtn.addEventListener('click', () => {
    exportExcelData()
  })
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
//转换文件流
const analysisFile = async () => {
  const files = new window.File([props.previewData], `${dataTitle.value}`, {type: 'application/vnd.ms-excel;charset=utf-8'})
  LuckyExcel.transformExcelToLucky(files, exportJson => {
    if (!exportJson.sheets || exportJson.sheets.length === 0) {
      ElMessage({
        message: '当前格式不支持,请下载本地查看!',
        type: 'warning'
      })
      return
    }
    loadData(exportJson.sheets)
  })
}
//导出excel
const exportExcelData = () => {
  exportExcel(luckysheet.getAllSheets(), '公网通信费用管理平台_结算审核数据单v1')
}
//初始化
onMounted(() => {
  analysisFile()
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
  height: 32px;
  width: 152px;
  z-index: 1;
  background-image: url("@/assets/logo.png"); /*更换logo*/
  background-repeat: no-repeat;
  background-size: contain;
}
</style>
