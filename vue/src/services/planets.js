import { url} from "@/main";

export var planets = []
export var bases = []

const handlers = []

import axios from 'axios'

export function getPlanets(idSystem) {

  return new Promise((resolve, reject) => {
    axios.get(url+'/api/game/systems/'+ idSystem +'/planets', { withCredentials: true })
      .then(response => {
        planets.splice(0, planets.length)
        response.data.forEach( planet => {
          addPlanet(planet)
        })

        console.log(planets)

        resolve(response)
      })
      .catch(error => {
        reject(error)
      })
  })
}

export function getBases() {

  return new Promise((resolve, reject) => {
    axios.get(url+'/api/game/bases', { withCredentials: true })
      .then(response => {
        response.data.forEach( base => {
          addBase(base)
        })

        resolve(response)
      })
      .catch(error => {
        reject(error)
      })
  })
}


export function onPlanet(planet) {
  handlers.forEach(handler => {
    handler(planet)
  })
}

export function addHandler(handler) {
  handlers.push(handler)
}

function addPlanet(planet) {
  planets.push(planet)
}

function addBase(base) {
  bases.push(base)
}


export function clearAllPlanet() {
  planets = []
  bases = []
}
