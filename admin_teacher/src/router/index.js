import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  /*
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },
  */
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  }

]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: '/studentManagement/index',
    meta: { roles: ['ROLE_ADMIN'] },
    children: [{
      path: 'studentManagement/index',
      name: 'StudentManagement',
      component: () => import ('@/views/studentManagement'),
      meta: { title: '学生管理', icon: 'student', roles: ['ROLE_ADMIN'] }
    }]
  },
  {
    path: '/teacherManagement',
    component: Layout,
    redirect: '/teacherManagement/index',
    meta: { roles: ['ROLE_ADMIN'] },
    children: [{
      path: 'index',
      name: 'TeacherManagement',
      component: () => import ('@/views/teacherManagement'),
      meta: { title: '教师管理', icon: 'teacher', roles: ['ROLE_ADMIN'] }
    }]
  },
  {
    path: '/discussManagement',
    component: Layout,
    redirect: '/discussManagement/index',
    meta: { roles: ['ROLE_ADMIN'] },
    children: [{
      path: 'index',
      name: 'discussManagement',
      component: () => import ('@/views/discussManagement'),
      meta: { title: '讨论管理', icon: 'paper', roles: ['ROLE_ADMIN'] }
    }]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/examinationManagement/index',
    meta: { roles: ['ROLE_TEA'] },
    children: [{
      path: 'examinationManagement/index',
      name: 'ExaminationManagement',
      component: () => import ('@/views/examinationManagement'),
      meta: { title: '考试管理', icon: 'examination', roles: ['ROLE_TEA'] }
    }]
  },
  {
    path: '/questionToPaper',
    component: Layout,
    redirect: '/questionToPaper/index',
    children: [
      {
        path: 'index',
        name: 'QuestionToPaper',
        component: () => import('@/views/questionToPaper'),
        meta: { title: '组卷', icon: 'paper', roles: ['ROLE_TEA'] }
      }
    ]
  },
  {
    path: '/questionManagement',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'QuestionManagemnt',
        component: () => import('@/views/questionManagement'),
        meta: { title: '题库', icon: 'question', roles: ['ROLE_TEA'] }
      }
    ]
  },
  {
    path: '/scoreManagement',
    component: Layout,
    redirect: '/scoreManagement/index',
    children: [{
      path: 'index',
      name: 'ScoreManagement',
      component: () => import('@/views/scoreManagement'),
      meta: { title: '成绩', icon: 'score', roles: ['ROLE_TEA'] }
    }]
  },
  {
    path: '/paperAnalyse',
    component: Layout,
    redirect: '/papeAnalyse/index',
    children: [{
      path: 'index',
      name: 'PaperAnalyse',
      component: () => import('@/views/paperAnalyse'),
      meta: { title: '试卷分析', icon: 'analyze', roles: ['ROLE_TEA'] }
    }]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
