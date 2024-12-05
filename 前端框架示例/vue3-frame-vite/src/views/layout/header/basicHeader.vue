<template>
  <el-menu style="--el-menu-active-color: tranparent;" :default-active="activeIndex" class="el-menu" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">
      <vue-light/>
    </el-menu-item>
    <el-menu-item index="1">
      <java-light/>
    </el-menu-item>
    <el-menu-item index="1">
      <spring-light/>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import VueLight from "@/views/home/vueLight.vue";
import JavaLight from "@/views/home/javaLight.vue";
import SpringLight from "@/views/home/springLight.vue";
</script>

<style scoped>
  .el-menu{
    width: 50%;
    left: 50%;
    background-color: transparent;
    display: flex;
  }
  :deep(.logo){
    height: 35px;
  }
  :deep(.logo:hover){
    transform: scale(1.5);
  }
  :deep(.logo::before){
    width: 100px;
    height: 100px;
  }

  :deep(.el-menu-item){
    padding: 0 0;
  }
  .el-menu--horizontal.el-menu{
    border-bottom:none;
  }
  .el-menu-item:hover {
    background-color: transparent !important;
  }
  .el-menu-item.is-active {
    background-color: transparent !important;
  }
</style>
