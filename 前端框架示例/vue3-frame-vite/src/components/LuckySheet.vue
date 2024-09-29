<script setup>
import {httpMethod} from "@/utils/request.js";
import Preview from "@/views/preview/preview.vue";
import {onMounted} from "vue";
import {ref} from "vue";
//导出数据对象
const previewData = ref([]);
onMounted(()=>{
  httpMethod.httpPost('/api/cost/register/editExcel',null,{
  responseType: "blob"
  }).then(res => {
    if (res) {
      console.log(res.data)
      previewData.value = res.data
    }
  })
})
</script>

<template>
  <preview :preview-data="previewData"></preview>
</template>
