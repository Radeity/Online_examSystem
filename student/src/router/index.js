import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
import login from '@/components/login'
import reset_password from '@/components/reset_password'
import password from '@/components/password'
import exams from '@/components/exams'
import startExam from '@/components/startExam'
import incorrect from '@/components/incorrect'
import account from '@/components/account'

Vue.use(Router)

export default new Router({
    mode:'history',
    routes: [
    {
        path: '/',
        name: 'index',
        component: index
    },
    {
        path: '/login',
        name: 'login',
        component: login
    },
    {
        path: '/reset-password',
        name: 'reset-password',
        component: reset_password
    },
    {
        path: '/password',
        name: 'password',
        component: password
    },
    {
        path: '/exams',
        name: 'exams',
        component: exams
    },
    {
      path: '/start/:id',
      name: 'start',
      component: startExam
    },
    {
      path: '/incorrect/:id',
      name: 'incorrect',
      component: incorrect
    },
    {
      path: '/account',
      name: 'account',
      component: account
    }
  ]
})
