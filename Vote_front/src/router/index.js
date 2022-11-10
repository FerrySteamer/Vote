import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
//导入登录页面组件
import Login from '../views/login.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    //添加登录页面路由
    {
      path:'/login',
      name: 'Login',
      component: Login
    }
  ]
})
