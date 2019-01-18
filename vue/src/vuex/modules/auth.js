import axios from 'axios'
import {AUTH_REQUEST, AUTH_ERROR, AUTH_SUCCESS, AUTH_LOGOUT, AUTH_GOOGLE_REQUEST} from '../actions/auth'
import { connect } from "@/services/websocket";
import { GET_USER } from "@/vuex/actions/user";
import { url } from "@/main";

export function getCookie(name) {
  var matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
  ));
  return matches ? decodeURIComponent(matches[1]) : undefined;
}

function deleteCookie(cookie_name) {
  var cookie_date = new Date ( );  // Текущая дата и время
  cookie_date.setTime ( cookie_date.getTime() - 1 );
  document.cookie = cookie_name += "=; expires=" + cookie_date.toGMTString();
}

const state = {
  token: localStorage.getItem('user-token') || '',
  status: '',
}

const actions = {
  [AUTH_GOOGLE_REQUEST]: ({commit, dispatch}) => {
      return new Promise((resolve, reject) => {
        dispatch(GET_USER)
          .then(() => {
            const token = getCookie('auth')
            localStorage.setItem('user-token', token)
            commit(AUTH_SUCCESS, token)
            resolve()
          })
          .catch(() => {
            commit(AUTH_ERROR)
            localStorage.removeItem('user-token')
            reject(error)
          })
      })
  },
  [AUTH_REQUEST]: ({commit, dispatch}, user) =>{

    return new Promise((resolve, reject) => {
      commit(AUTH_REQUEST)
      axios.post(url+'/login', user, {
        withCredentials: true
      })
        .then(response =>{
          const token = getCookie('auth')

          localStorage.setItem('user-token', token)
          commit(AUTH_SUCCESS, token)

          resolve(response)
        })
        .catch(error =>{
          commit(AUTH_ERROR)
          localStorage.removeItem('user-token')
          reject(error)
        })
    })
  },

  [AUTH_LOGOUT]: ({commit, dispatch}) =>{
    return new Promise(((resolve, reject) => {
      commit(AUTH_LOGOUT)
      localStorage.removeItem('user-token')

      deleteCookie('user-token')
      resolve()
    }))
  }
}

const mutations = {
    [AUTH_REQUEST]: (state) => {
    state.status = 'loading'
  },
    [AUTH_SUCCESS]: (state, token) => {

      // TODO websocket connect
      connect()
    state.status = 'success'
    state.token = token
  },
    [AUTH_ERROR]: (state) => {
    state.status = 'error'
      state.token = ''
  },
    [AUTH_LOGOUT]: (state) =>{
      state.status = 'logout'
      state.token = ''
    }
}

const getters = {
  isAuthenticated: state => !!state.token,
  authStatus: state => state.status
}

export default {
  state,
  getters,
  actions,
  mutations,
}
