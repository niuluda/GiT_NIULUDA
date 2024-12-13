<template>
    <el-container class="layout">
      <el-header><basic-header/></el-header>
      <el-container>
        <el-aside><basic-aside/></el-aside>
        <el-main>
          <router-view v-slot="{ Component, route }">
            <transition name="fade-transform" mode="out-in" appear>
              <keep-alive>
                <component :is="Component" :key="route.path"/>
              </keep-alive>
            </transition>
          </router-view>
        </el-main>
      </el-container>
    </el-container>
</template>
<style>
 .layout{
   position: fixed;
   width: 100%;
   height: 100%;
   border: solid 1px rgb(43,47,51);
   color: #2c3e50;
 }
 .el-header{
   border-bottom: solid 1px rgb(43,47,51);
   background-color: rgb(43,47,51);
 }
 .el-main{
   border-bottom: solid 1px rgb(43,47,51);
   padding: 5px;
 }
 .el-aside{
   width: 200px;
   border-bottom: solid 1px rgb(43,47,51);
   border-right: solid 1px rgb(43,47,51);
 }

 /*vue3中translation组件失效重写样式*/
 /* fade-transform */
 .fade-transform-leave-active,
 .fade-transform-enter-active {
   transition: all 0.1s;
 }

 /* 可能为enter失效，拆分为 enter-from和enter-to */
 .fade-transform-enter-from {
   opacity: 0;
   transform: translateX(-30px);
 }
 .fade-transform-enter-to {
   opacity: 0;
   transform: translateX(0px);
 }

 .fade-transform-leave-to {
   opacity: 0;
   transform: translateX(30px);
 }
</style>
<script>
import BasicHeader from "@/views/layout/header/basicHeader.vue";
import BasicAside from "@/views/layout/aside/basicAside.vue";
export default {
  components: {BasicAside, BasicHeader}
}
</script>
