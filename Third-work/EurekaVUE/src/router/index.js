import { createRouter, createWebHistory } from 'vue-router'
const routes = [
  {
    path: '/',
    name:'Login',
    component: ()=>import('../views/Login.vue')
  },
  {
    path: '/MyOrder',
    name:'MyOrder',
    component: ()=>import('../views/MyOrder.vue')
  },
  {
    path: '/SelectOrder',
    name:'SelectOrder',
    component: ()=>import('../views/SelectOrder.vue')
  },
  {
    path: '/Register',
    name:'Register',
    component: ()=>import('../views/Register.vue')
  },
  {
    path: '/UpdateOrder/',
    name:'UpdateOrder',
    component: ()=>import('../views/UpdateOrder.vue')
  },
  {
    path: '/CreateOrder/',
    name:'CreateOrder',
    component: ()=>import('../views/CreateOrder.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
