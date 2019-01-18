import axios from 'axios'
import {
  GET_USER,
  AUTH_LOGOUT_USER,
  GET_USER_BASE,
  GET_USER_SHIPS,
  GET_ITEM_BASE,
  GET_ITEM_SHIP, GET_ITEM_BASE_SHIPS, CREATE_SHIP, UPDATE_USER,
  GET_USER_FRACTIONS_INFO,
  UPDATE_SHIP_LOCATION,
  GET_SHIP,
  UPDATE_ATTACK_SHIP, CREATE_BASE, OUT_FROM_FRACTION, JOIN_TO_FRACTION, UPDATE_USER_FRACTION, UPDATE_INFO_USER
} from "@/vuex/actions/user"
import { url } from "@/main";

const state = {
  user: null,
  list: {
    bases: null,
    ships: null,
  },
  info: {
    ship: {
      ship: null,
      otherShip: null,
      status: '',
    },
    base: {
      base: null,
      status: '',
      listShips: null
    },
  },
  userFraction: null
}

const mutations = {
  [UPDATE_INFO_USER]: (state, user) => {
    state.user.login = user.login
    state.user.firstName = user.firstName
    state.user.lastName = user.lastName
  },
  [GET_USER]: (state, user) =>{
    state.user = user
  },
  [UPDATE_USER_FRACTION]: (state, userFraction) =>{
    state.userFraction = userFraction
  },
  [UPDATE_USER]: (state, user) => {
    state.user.coins = user.coins
    state.user.level = user.level
  },
  [AUTH_LOGOUT_USER]: (state) =>{
    state.user = null
    state.list.bases = null
    state.list.ships = null
  },
  [GET_USER_BASE]: (state, bases) => {
    state.list.bases = bases
  },
  [GET_USER_SHIPS]: (state, ships) => {
    state.list.ships = ships
  },
  [GET_ITEM_BASE]: (state, info) =>{
    state.info.base.base = info.base
    state.info.base.status = info.status
  },
  [GET_ITEM_SHIP]: (state, info) =>{
    state.info.ship.ship = info.ship
    state.info.ship.status = info.status
  },
  [GET_ITEM_BASE_SHIPS]: (state, listShips) =>{
    state.info.base.listShips = listShips
  },
  [UPDATE_SHIP_LOCATION]: (state, updateShip) => {
    state.info.ship.ship.rotationShipX = updateShip.rotationShipX
    state.info.ship.ship.rotationShipY = updateShip.rotationShipY
    state.info.ship.ship.rotationShipZ = updateShip.rotationShipZ
    state.info.ship.ship.locationShipX = updateShip.locationShipX
    state.info.ship.ship.locationShipY = updateShip.locationShipY
    state.info.ship.ship.locationShipZ = updateShip.locationShipZ
  },

  [UPDATE_ATTACK_SHIP]: (state, ship) => {
    state.info.ship.otherShip.hp = ship.hp;
    state.info.ship.otherShip.stateShip = ship.stateShip;
  },

  [GET_SHIP]: (state, ship) => {
    if (state.user.id == ship.user.id){
      state.info.ship.ship = ship
    } else state.info.ship.otherShip = ship
  },

}

const actions = {
  [UPDATE_INFO_USER]: ({commit, dispatch}, user) => {
    return new Promise((resolve, reject) => {
      axios.post(url+'/api/user', user, { withCredentials: true })
        .then(response => {
          commit(UPDATE_INFO_USER, response.data)
          resolve(response)
        })
        .catch(() => {
          reject()
        })
    })
  },
  [UPDATE_USER_FRACTION]: ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/user/fraction', { withCredentials: true })
        .then(response => {
          commit(UPDATE_USER_FRACTION, response.data)
          resolve(response)
        })
        .catch(() => {
          commit(UPDATE_USER_FRACTION, null)
          reject()
        })
    })
  },
  [JOIN_TO_FRACTION]: ({ commit, dispatch }, id) => {
    return new Promise(((resolve, reject) => {
      axios
        .post(url+'/api/fraction/'+id+'/join', null, {
          withCredentials: true
        })
        .then(response =>{
          resolve(response.data)
        })
        .catch(error =>{
          reject(error)
        })
    }))
  },
  [CREATE_BASE]: ({commit, dispatch}, base) => {
    return new Promise(((resolve, reject) => {
      axios
        .post(url+'/api/user/base', base, {
          withCredentials: true
        })
        .then(response =>{
          resolve(response.data)
        })
        .catch(error =>{
          reject(error)
        })
    }))
  },
  [OUT_FROM_FRACTION]: ({commit, dispatch}) => {
    return new Promise(((resolve, reject) => {
      axios
        .post(url+'/api/fraction/out', null, {
          withCredentials: true
        })
        .then(response =>{
          resolve(response.data)
        })
        .catch(error =>{
          reject(error)
        })
    }))
  },
  [GET_SHIP]: ({commit, dispatch}, id) => {
    return new Promise(((resolve, reject) => {
      axios
        .get(url+'/api/game/ships/'+id, {
          withCredentials: true
        })
        .then(response =>{
          commit(GET_SHIP, response.data)
          resolve(response)
        })
        .catch(error =>{
          commit(GET_SHIP, null)
          reject(error)
        })
    }))
  },

  [UPDATE_ATTACK_SHIP]: ({commit, dispatch}, ship) => {
    commit(UPDATE_ATTACK_SHIP, ship)
  },

  [UPDATE_SHIP_LOCATION]: ({commit, dispatch}, ship) => {
    commit(UPDATE_SHIP_LOCATION, ship)
  },

  [GET_USER]: ({commit, dispatch}) =>{
    return new Promise(((resolve, reject) => {
      commit(GET_USER)

      axios
        .get(url+'/api/user', {
          withCredentials: true
        })
        .then(response =>{
          commit(GET_USER, response.data)
          resolve(response)
        })
        .catch(error =>{
          commit(GET_USER, null)
          reject(error)
        })
    }))
  },

  [UPDATE_USER]: ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      axios
        .get(url+'/api/user', {
          withCredentials: true
        })
        .then(response =>{
          commit(UPDATE_USER, response.data)
          resolve(response)
        })
        .catch(error =>{
          commit(UPDATE_USER, null)
          reject(error)
        })
    })
  },

  [AUTH_LOGOUT_USER]: ({commit, dispatch}) =>{
    return new Promise((resolve, reject) => {
      commit(AUTH_LOGOUT_USER)
      resolve()
    })
  },
  [GET_USER_BASE]: ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      commit(GET_USER_BASE)

      axios.get(url+'/api/user/base', {
        withCredentials: true
      })
        .then(response =>{
          commit(GET_USER_BASE, response.data)
          resolve(response)
        })
        .catch(error =>{
          commit(GET_USER_BASE, null)
          reject(error)
        })
    })
  },
  [GET_USER_SHIPS]: ({commit, dispatch}) =>{
    return new Promise((resolve, reject) => {
      commit(GET_USER_SHIPS)
      axios.get(url+'/api/user/ships', {
        withCredentials: true
      })
        .then(response =>{
          commit(GET_USER_SHIPS, response.data)
          resolve(response)
        })
        .catch(error =>{
          commit(GET_USER_SHIPS, null)
          reject(error)
        })
    })
  },

  [GET_ITEM_BASE]: ({commit, dispatch}, baseId) =>{
    return new Promise((resolve, reject) => {

      axios.get(url+'/api/user/base/'+baseId, {
        withCredentials: true
      })
        .then(response =>{
          const base = response.data
          var status = ''
          if(base.user.id === state.user.id){
            status = 'available'

            axios.get(url+'/api/user/base/'+baseId+'/ships', {
              withCredentials: true
            })
              .then(responseTwo =>{
                commit(GET_ITEM_BASE_SHIPS, responseTwo.data)
              })
              .catch(error =>{
                commit(GET_ITEM_BASE_SHIPS, null)
              })
          } else {
            commit(GET_ITEM_BASE_SHIPS, null)
          }
          commit(GET_ITEM_BASE, { base: base, status: status })
          resolve(response)
        })
        .catch(error =>{
          commit(GET_ITEM_BASE, { base: null, status: '' })
          reject(error)
        })
    })
  },

  [GET_ITEM_SHIP]: ({commit, dispatch}, shipId) =>{
    return new Promise((resolve, reject) => {

      axios.get(url+'/api/user/ships/'+shipId, {
        withCredentials: true
      })
        .then(response =>{
          const ship = response.data
          var status = ''
          if(ship.user.id === state.user.id){
            status = 'available'
          }
          commit(GET_ITEM_SHIP, { ship: ship, status: status })
          resolve(response)
        })
        .catch(error =>{
          commit(GET_ITEM_SHIP, { ship: null, status: '' })
          reject(error)
        })
    })
  },

  [GET_ITEM_BASE_SHIPS]: ({commit, dispatch}, baseId) =>{
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/user/base/'+baseId+'/ships', {
        withCredentials: true
      })
        .then(response =>{
          commit(GET_ITEM_BASE_SHIPS, response.data)
          resolve(response)
        })
        .then(error =>{
          commit(GET_ITEM_BASE_SHIPS, null)
          reject(error)
        })
    })
  },
  [CREATE_SHIP]: ({commit, dispatch}, ship) => {
    return new Promise((resolve, reject) => {
      axios.post(url+'/api/user/ships', ship, {withCredentials: true})
        .then(response =>{
          resolve(response)
        })
        .catch(error =>{
          reject(error)
        })
    })
  }
}

const getters = {
  user: state => state.user,
  bases: state => state.list.bases,
  ships: state => state.list.ships,
  ship: state => state.info.ship,
  base: state => state.info.base,
  otherShip: state => state.info.ship.otherShip,
  userFraction: state => state.userFraction,
}

export default {
  state,
  getters,
  actions,
  mutations,
}
