import { createRouter, createWebHistory } from 'vue-router'
import LuckySheet from '../components/LuckySheet.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'luckysheet',
      component: LuckySheet
    }
  ]
})

export default router
