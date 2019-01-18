import {
  GET_TYPES_SHIP,
  POWERUP_SHIP,
  GET_ALL_SHIPS,
  ADD_SHIP,
  GET_PLANET,
  GET_PLANET_ATTACK,
  ATTACK_PLANET,
  GET_ALL_FRACTIONS, GET_COUNT_USERS, CREATE_FRACTION, GET_USERS_IN_FRACTION, ACCEPT_USER, DELETE_USER
} from "@/vuex/actions/game";
import axios from "axios";
import { url } from "@/main";

const state = {
  types: null,
  ships: [],
  planet: null,
  attack: null,
  fractions: [],
  userFractions: []
}

const mutations = {
  [GET_USERS_IN_FRACTION]: (state, userFractions) => {
    state.userFractions = userFractions
  },
  [GET_ALL_FRACTIONS]: (state, fractions ) => {
    state.fractions = fractions
  },
  [GET_TYPES_SHIP]: (state, types) => {
    state.types = types
  },
  [GET_ALL_SHIPS]: (state, ships) => {
    state.ships = ships
  },
  [ADD_SHIP]: (state, ship) => {

    let arr = state.ships
    var index = -1
    arr.filter((el, pos) => {
      if(el.id === ship.id)
        delete arr[index = pos]
      return true
    })

    if(index === -1)
      arr.push(ship)
    else
      state.ships.splice(index, 1, ship)

  },
  [GET_PLANET]: (state, planet) => {
    state.planet = planet
  },
  [GET_PLANET_ATTACK]: (state, attack) => {
    state.attack = attack
  }
}

const actions = {
  [ACCEPT_USER]: ({commit, dispatch}, userFraction) => {
    return new Promise((resolve, reject) => {
      axios.post(url+'/api/fraction/'+userFraction.fraction.id+'/accept', userFraction, { withCredentials: true })
        .then(response => {
          resolve(response)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  [DELETE_USER]: ({commit, dispatch}, userFraction) => {
    return new Promise((resolve, reject) => {
      axios.post(url+'/api/fraction/'+userFraction.fraction.id+'/delete', userFraction, { withCredentials: true })
        .then(response => {
          resolve(response)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  [GET_USERS_IN_FRACTION]: ({commit, dispatch}, id) => {
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/fraction/'+id+'/users', { withCredentials: true })
        .then(response => {
          commit(GET_USERS_IN_FRACTION, response.data)
          resolve(response)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  [CREATE_FRACTION]: ({commit, dispatch}, fraction) => {
    return new Promise((resolve, reject) => {
      axios.post(url+'/api/fraction', fraction, { withCredentials: true })
        .then(response => {
          resolve(response)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  [GET_COUNT_USERS]: ({commit, dispatch}, id) => {
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/fraction/'+id+'/users', { withCredentials: true })
        .then(response => {
          resolve(response.data)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
  [GET_ALL_FRACTIONS]: ({commit, dispatch}) => {
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/fraction', { withCredentials: true })
        .then(response => {
          commit(GET_ALL_FRACTIONS, response.data)
          resolve(response)
        })
        .catch(error => {
          commit(GET_ALL_FRACTIONS, null)
          reject(error)
        })
    })
  },
  [ATTACK_PLANET]: ({commit, dispatch}, id) => {
    return new Promise((resolve, reject) => {
      axios.post(url+'/api/game/planets/'+id+'/attack', null, { withCredentials: true })
        .then(response => {
          commit(GET_PLANET, response.data)
          resolve(response)
        })
        .catch(error => {
          reject(error)
        })
    })
  },

  [GET_PLANET_ATTACK]: ({commit, dispatch}, id) => {
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/game/planets/'+id+'/attack', { withCredentials: true })
        .then(response => {
          commit(GET_PLANET_ATTACK, response.data)
          resolve(response)
        })
        .catch(error => {
          commit(GET_PLANET_ATTACK, null)
          reject(error)
        })
    })
  },
  [GET_PLANET]: ({commit, dispatch}, id) => {
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/game/planets/'+id, { withCredentials: true })
        .then(response => {
          commit(GET_PLANET, response.data)
          resolve(response)
        })
        .catch(error => {
          commit(GET_PLANET, null)
          reject(error)
        })
    })
  },
  [GET_TYPES_SHIP]: ({commit, dispatch}) =>{
    return new Promise((resolve, reject) => {
      axios.get(url+'/api/game/ships/types', {withCredentials: true})
        .then(response =>{
          commit(GET_TYPES_SHIP, response.data)
          resolve(response)
        })
        .catch(error =>{
          commit(GET_TYPES_SHIP, null)
          reject(error)
        })
    })
  },

  [POWERUP_SHIP]: ({ commit, dispatch }, ship) =>{
    return new Promise((resolve, reject) => {
      axios.put(url+'/api/user/ships', ship, {withCredentials: true})
        .then(response =>{
          resolve(response)
        })
        .catch(error =>{
          console.log(error)
          reject(error)
        })
    })
  },

  [GET_ALL_SHIPS]: ({ commit, dispatch }) => {
    return new Promise(((resolve, reject) => {
      axios.get(url+'/api/game/ships', { withCredentials: true })
        .then(response =>{
          commit(GET_ALL_SHIPS, response.data)
          resolve(response)
        })
        .catch(error => {
          commit(GET_ALL_SHIPS, null)
          reject(error)
        })
    }))
  },

  [ADD_SHIP]: ({commit, dispatch}, ship) => {
    return new Promise((resolve, reject) => {
      commit(ADD_SHIP, ship)
    })
  }
}

const getters = {
  types: state => state.types,
  gameShips: state => state.ships,
  planet: state => state.planet,
  attack: state => state.attack,
  fractions: state => state.fractions,
  userFractions: state => state.userFractions
}

export default {
  state,
  getters,
  actions,
  mutations,
}
