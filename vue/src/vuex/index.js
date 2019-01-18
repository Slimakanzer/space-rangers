import Vue from 'vue'
import Vuex from 'vuex'
import auth from '@/vuex/modules/auth'
import user from '@/vuex/modules/user'
import game from '@/vuex/modules/game'
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    user,
    game,
  },
  strict: true,
})
