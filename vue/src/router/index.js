import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/MainPage'
import NewsList from '@/components/NewsList'
import NewsPage from '@/components/NewsPage'
import UserPage from '@/components/UserPage'

import {MAIN_PAGE, NEWS_LIST, NEWS_PAGE, USER_PAGE} from "./actions/name";
import {MAIN_PAGE_URL, NEWS_LIST_URL, NEWS_PAGE_URL, USER_PAGE_URL} from "./actions/paths";


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: MAIN_PAGE_URL,
      name: MAIN_PAGE,
      component: Main
    },
    {
      path: NEWS_LIST_URL,
      name: NEWS_LIST,
      component: NewsList
    },
    {
      path: NEWS_PAGE_URL,
      name: NEWS_PAGE,
      component: NewsPage
    },
    {
       path : USER_PAGE_URL,
       name: USER_PAGE,
       component: UserPage
    }
  ]
})
