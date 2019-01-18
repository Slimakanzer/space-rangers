import axios from 'axios'
import {GET_ALL_SHIPS} from "@/vuex/actions/game";
import { url } from "@/main";

export var ships = []


export function getShips() {
  return new Promise((resolve, reject) => {
    axios.get(url+'/api/game/ships', { withCredentials: true })
      .then(response =>{
        response.data.forEach(ship => {
          addShip(ship)
        })

        resolve(response)
      })
      .catch(error => {
        ships.splice(0, ships.length)
        reject(error)
      })
  })
}

export function canAttack(ship) {
  return new Promise((resolve, reject) => {
    axios.post(url+'/api/user/fraction', ship, { withCredentials: true })
      .then(response =>{
        resolve(response.data)
      })
      .catch(error => {
        reject(false)
      })
  })
}

export function updateShip(ship) {
  axios.put(url+'/api/user/ships', ship, {withCredentials: true})
    .then(response =>{
      return response.data
    })
    .catch(error =>{
      alert(error)
      return null
    })
}


function addShip(ship) {
  ships.push(ship)
}

export function clearAllShips() {
  ships = []
}
