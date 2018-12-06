import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/MainPage'
import NewsList from '@/components/NewsList'
import NewsPage from '@/components/NewsPage'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainPage',
      component: Main
    },
    {
      path: '/news',
      name: 'NewsList',
      component: NewsList
    },
    {
      path: '/news/:id',
      name: 'NewsPage',
      component: NewsPage
    }
  ]
})
