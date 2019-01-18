import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'
import { updatePositionShipMove, deleteShip } from "@/webgl/CreateFunc";
import {GET_USER, UPDATE_ATTACK_SHIP, UPDATE_USER, UPDATE_USER_FRACTION} from "@/vuex/actions/user";
import state from '../vuex'
import { createBase } from "@/webgl/CreateFunc";
import { url } from "@/main";

var stompClient = null;
var stompErrorClient = null;
const handlers = [];
const errorHandlers = [];
const createHandlers = [];

 export function connect() {
  var socket = new SockJS(url+'/space-rangers');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/game', function (message) {
        handlers.forEach(handler => handler(JSON.parse(message.body)))
    });

    stompClient.subscribe('/topic/create', function (message) {
      alert(message.body)
      createHandlers.forEach(handler => handler(JSON.parse(message.body)))
    });

    stompClient.subscribe('/topic/move', function (message) {
      updatePositionShipMove(JSON.parse(message.body))
    });


    stompClient.subscribe('/topic/attack', function (message) {
      state.dispatch(UPDATE_ATTACK_SHIP, JSON.parse(message.body))
    });


    stompClient.subscribe('/user/topic/error', function (error) {
      errorHandlers.forEach(handler => handler(JSON.parse(error.body).typeError))
    })


    stompClient.subscribe('/topic/user', function (message) {
      state.dispatch(UPDATE_USER)
    })

    stompClient.subscribe('/topic/fraction', function (message) {
      const id = message.body
      if (state.getters.user.id == id) {
        state.dispatch(UPDATE_USER_FRACTION)
      }
    })


    stompClient.subscribe('/topic/base', function (message) {
      const base = JSON.parse(message.body);
      createBase(base.locationBaseX, base.locationBaseY, base.locationBaseZ)
    })

  });

}

export function addHandler(handler) {
  handlers.push(handler)
}

export function addErrorHandler(handler) {
  errorHandlers.push(handler)
}
export function addCreateHandler(handler) {
  createHandlers.push(handler)
}

export function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}

export function moveShip(ship) {
  // alert(JSON.stringify(ship))
  stompClient.send("/app/ship/move", {}, JSON.stringify(ship));
}

export function boostShip(ship) {
  // alert(JSON.stringify(ship))
  stompClient.send("/app/ship/boost", {}, JSON.stringify(ship));
}

export function createShip(ship) {
  // alert(JSON.stringify(ship))
  stompClient.send("/app/ship/create", {}, JSON.stringify(ship));
}

export function attackShip(ship) {
  // alert(JSON.stringify(ship))
  stompClient.send("/app/ship/attack", {}, JSON.stringify(ship));
}

export function createBaseWeb(base) {
  // alert(JSON.stringify(ship))
  stompClient.send("/app/base/create", {}, JSON.stringify(base));
}


export function updateUserFraction(id) {
  // alert(JSON.stringify(ship))
  stompClient.send("/app/fraction/update", {}, id);
}
