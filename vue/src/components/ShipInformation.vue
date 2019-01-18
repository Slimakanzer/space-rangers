<template>
  <div>
    <div class="header-ship">

      <div v-if="ship">
        <h4>{{ ship.nameShip }} <span> (ваш) </span></h4>
        <h5>({{ ship.stateShip.nameNormal }})</h5>
        <hr>

        <h5>{{ ship.typeShip.name }}</h5>
        <hr>

        <h5>Здоровье</h5>
        <b-progress :value="ship.hp" :max="ship.typeShip.hp" show-value class="mb-3" variant="primary"></b-progress>
        <h5>Защита</h5>
        <b-progress :value="ship.protection" :max="50" show-value class="mb-3" variant="primary"></b-progress>
        <h5>Скорость</h5>
        <b-progress :value="ship.speed" :max="1000" show-value class="mb-3" variant="primary"></b-progress>
        <hr>

        <div>
          <div class="power-up">
            <b-btn variant="outline-primary" @click="upHealth">Здоровье +100</b-btn>
          </div>
          <div class="power-up">
            <b-btn variant="outline-primary" @click="upSpeed">Скорость +10</b-btn>
          </div>
          <div class="power-up">
            <b-btn variant="outline-primary" @click="upProtection">Защита +2</b-btn>
          </div>
          <b-alert variant="warning" :show="powerupError">Достигнут мак. уровень <b>{{ powerupErrorText }}</b></b-alert>
          <b-alert variant="danger" :show="moneyError">Нет денег</b-alert>

          <b-row>
            <b-col>
              X:
            </b-col>
            <b-col>
              <input type="range" min="-0.1" max="0.1" v-model="shipState.rotationShipX"  step="0.01" @change="rotationShip">
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              Y:
            </b-col>
            <b-col>
              <input type="range" min="-0.1" max="0.1" v-model="shipState.rotationShipY"  step="0.01" @change="rotationShip">
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              Z:
            </b-col>
            <b-col>
              <input type="range" min="-0.1" max="0.1" v-model="shipState.rotationShipZ" step="0.01" @change="rotationShip">
            </b-col>
          </b-row>

          <b-btn @click="moveShip" variant="outline-success" class="move-button" v-if="canMove">MOVE</b-btn>

        </div>

      </div>

      <hr>
      <div v-if="otherShip">

        <div class="power-up" v-if="canAttackShip && canMove">
          <b-btn :variant="variantBattle" @click="damage">Напасть</b-btn>
          <b-alert :show="tooFar" variant="warning">Вы слишком далеко</b-alert>
        </div>


        <h4>{{ otherShip.nameShip }}</h4>
        <h5>{{ otherShip.user.login }}</h5>
        <h5> ({{ otherShip.stateShip.nameNormal }})</h5>
        <hr>

        <h5>{{ otherShip.typeShip.name }}</h5>
        <hr>

        <h5>Здоровье</h5>
        <b-progress :value="otherShip.hp" :max="otherShip.typeShip.hp" show-value class="mb-3" :variant="variantBars"></b-progress>
        <h5>Защита</h5>
        <b-progress :value="otherShip.protection" :max="50" show-value class="mb-3" :variant="variantBars"></b-progress>
        <h5>Скорость</h5>
        <b-progress :value="otherShip.speed" :max="1000" show-value class="mb-3" :variant="variantBars"></b-progress>
        <hr>
        {{ canAttackComp }}
        {{ canAttackShip }}
      </div>
    </div>
  </div>
</template>

<script>
    import { POWERUP_SHIP } from "@/vuex/actions/game";
    import {GET_ITEM_SHIP, GET_USER_SHIPS, UPDATE_USER, UPDATE_SHIP_LOCATION} from "@/vuex/actions/user";
    import { boostShip, addHandler, addErrorHandler, attackShip } from "@/services/websocket";
    import { NOT_ENOUGH_MONEY_POWERUP } from "@/services/errors/shipErrors";
    import { updatePositionShip, updateRotationShip } from "@/webgl/CreateFunc";
    import { canAttack } from "@/services/ships";


    export default {
        data: function(){
          return {
            moneyError: false,
            powerupError: false,
            powerupErrorText: '',
            shipState: {
              rotationShipX: 0,
              rotationShipY: 0,
              rotationShipZ: 0
            },


            tooFar: false,
            canAttackShip: false

          }
        },
        name: "ShipInformation",
        computed: {
          ship() {
            this.moneyError=false;
            this.powerupError=false;
            return this.$store.getters.ship.ship
          },
          otherShip(){
            return this.$store.getters.otherShip
          },
          variantBattle() {
            if(this.isInArea) return 'danger'; else return 'warning';
          },

          canMove() {
            if (this.ship.hp > 0) return true;
            else return false;
          },

          canAttackComp() {
            canAttack(this.otherShip)
              .then(result =>{
                this.canAttackShip = !result
              })
              .catch(() => {
                this.canAttackShip = false
              })
          },

          isInArea() {
            if (this.otherShip && this.ship){

              var length = Math.sqrt(Math.pow(Math.abs(this.ship.locationShipX - this.otherShip.locationShipX) ,2) +
                Math.pow(Math.abs(this.ship.locationShipY - this.otherShip.locationShipY) ,2) +
                Math.pow(Math.abs(this.ship.locationShipZ - this.otherShip.locationShipZ) ,2));

              if (length <= 300) {
                this.tooFar = false;
                return true;
              }
              else {
                this.tooFar=true;
                return false
              }
            }else {
              this.tooFar = false
              return false
            }
          },

          isDestroyed() {
            if (this.otherShip){
              if(this.otherShip.stateShip.name == 'destroyed') return true;
              else return false;
            }else return true;
          },

          isInUsersFraction(){
            const userFraction = this.$store.getters.user.userFraction;
            if (userFraction == null) return false;
            if (this.otherShip) return false
          },

          variantBars() {
            if (this.isDestroyed) return 'secondary';
            else if (this.canAttackShip) return 'danger'
            else return 'success'
          }
        },
      created: function(){
          addErrorHandler(typeError => {
            if(typeError == NOT_ENOUGH_MONEY_POWERUP){
              this.moneyError=true
            }
          })

          addHandler(data => {
            if (data.user.id === this.$store.getters.user.id){

              this.$store.dispatch(UPDATE_USER)
              this.$store.dispatch(GET_ITEM_SHIP, data.id)
                .then(()=>{
                  this.$store.dispatch(GET_USER_SHIPS)
                  this.$root.$emit('clickShip')

                  this.powerupError = false
                  this.moneyError = false
                })
            }
          })
      },
        methods: {
          up(object) {
                boostShip(object)
          },
          upHealth(){
            var object = Object.assign({}, this.ship)
            this.powerupErrorText = 'здоровья'
            object.hp += 100
            object.stateShip = { id: 2, name: 'ready'}
            this.up(object)
          },
          upProtection(){
            var object = Object.assign({}, this.ship)
            this.powerupErrorText = 'защиты'
            object.protection += 2
            this.up(object)
          },
          upSpeed(){
            var object = Object.assign({}, this.ship)
            this.powerupErrorText = 'скорости'
            object.speed += 10
            this.up(object)
          },

          damage(){
            if ( this.isInArea ) {
              var object = Object.assign({}, this.otherShip)
              object.hp -=200;
              attackShip(object)
            }
          },

          moveShip: function () {
            var object = Object.assign({}, this.ship)
            const resultObject = updatePositionShip(object, this.shipState)
            this.$store.dispatch(UPDATE_SHIP_LOCATION, resultObject)
          },

          rotationShip: function () {
            var object = Object.assign({}, this.ship)
            this.shipState.rotationShipX = Number(this.shipState.rotationShipX)
            this.shipState.rotationShipY = Number(this.shipState.rotationShipY)
            this.shipState.rotationShipZ = Number(this.shipState.rotationShipZ)

          }
        }
    }
</script>

<style scoped>

  .header-ship{
    text-align: center;
  }

  .power-up{
    padding-bottom: 3%;
  }

  .power-up > button{
    width: 100%;
  }

  .move-button{
    width: 100%;
  }

</style>
