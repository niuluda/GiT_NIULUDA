import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../views/layout/mainLayout.vue'
import Home from "@/views/home/vueLight.vue";
import IframeView from "@/views/home/iFrameView.vue";
import LunBo01 from "@/views/home/luobo01.vue";
/****************************************************
 * path:/最好是定义,因为默认http://12x.32.34.2/也能访问到
 * 如果不定义/那么只能是http://12x.32.34.2/home才能访问到
 * *************************************************/
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: Layout, // 布局组件
      redirect: "/home/01", // 默认重定向到 /home/01
    },
    {
      path: "/home",
      component: Layout, // 布局组件
      name: "主页",
      children: [
        {
          path: "/home/01",
          component: Home,
          name: "主页详情",
          meta: { title: "home", icon: "home", affix: true },
        },
        {
          path: "/home/02",
          component: IframeView,
          name: "iframe",
          meta: { title: "iframe", icon: "iframe", affix: true },
        },{
          path: "/home/03",
          component: LunBo01,
          name: "LunBo01",
          meta: { title: "LunBo01", icon: "LunBo01", affix: true },
        }
      ]
    }
  ]
})
export default router
