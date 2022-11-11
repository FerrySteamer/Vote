import Vue from 'vue'
import Router from 'vue-router'
//导入登录页面组件
import Login from '@/views/login.vue'
import Home from '@/views/home.vue'
import Register from '@/views/user/register.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      meta: {
        requireAuth: true
      },
      redirect: '/index',
      children:[
        {
          path:'/index',
          name:'Index',
          component:() => import('@/views/home/index'),
          meta:{
            requireAuth:true
          }
        },
        {
          path:'/user',
          name:'User',
          component:()=>import('@/views/user/index'),
          meta:{
            requireAuth:true
          }
        }
      ]
    },
    {
      path:'/login',
      name: 'Login',
      component: Login
    },
    {
      path:'/register',
      name:'Register',
      component: Register
    }
  ]
})
