// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App'
import router from './router'
import store from './vuex'

const dev = true;
export var url;
if (dev){
  url = 'http://localhost:8080'
}else {
  url = 'http://localhost:5280'
}

Vue.config.productionTip = false;
Vue.use(BootstrapVue);

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import {AUTH_SUCCESS, AUTH_GOOGLE_REQUEST} from "@/vuex/actions/auth";
import  {getCookie } from "@/vuex/modules/auth";

const token = localStorage.getItem('user-token')
if (token) {
  store.commit(AUTH_SUCCESS, token)
}

if (getCookie('gauth')){
  store.dispatch(AUTH_GOOGLE_REQUEST)
}

/* eslint-disable no-new */
export const component = new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
