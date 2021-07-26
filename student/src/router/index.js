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
import like from '@/components/like'
import discuss from '@/components/discuss'
//import te from '@/components/te'

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
    },
    {
      path: '/like',
      name: 'like',
      component: like
    },
    {
      path: '/discuss/:id',
      name: 'discuss',
      component: discuss
    }/*
    {
      path: '/te/:id',
      name: 'te',
      component: te
    }*/
  ]
})
