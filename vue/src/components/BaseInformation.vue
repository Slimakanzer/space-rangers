<template>
    <div>
      <div class="header-base">
        <h4>{{ base.nameBase }}</h4>
        <h5>{{ base.user.login }} ({{ base.user.level }} уровень)</h5>
        <hr>
      </div>
      <div>
        Система: {{ base.system.nameSystem }}
      </div>

      <div>
        Координаты X: {{ base.locationBaseX }}
      </div>

      <div>
        Координаты Y: {{ base.locationBaseY }}
      </div>

      <div>
        Координаты Z: {{ base.locationBaseZ }}
      </div>

      <hr>

      <div v-if="isModified">
        <b-btn variant="danger" class="create-button" v-b-toggle.collapse-ship-create >Создать корабль</b-btn>

        <div>
          <b-collapse id="collapse-ship-create">
            <b-card>
              <b-form class="form">


                <b-form-input type="text" required placeholder="Название" v-model="createShip.nameShip"></b-form-input>
                <b-form-select v-model="createShip.typeShip">
                  <template slot="first">
                    <option :value="null" disabled>Тип корабля</option>
                  </template>
                  <option v-for="type in types" :value="type" >{{ type.name }}</option>
                </b-form-select>

                <div v-if="createShip.typeShip">
                  <b-card>
                    <b-container class="bv-example-row">
                      <b-row>
                        <b-col>Здоровье:</b-col>
                        <b-col>{{ createShip.typeShip.hp }}</b-col>
                      </b-row>
                      <b-row>
                        <b-col>Скорость:</b-col>
                        <b-col>{{ createShip.typeShip.speed }}</b-col>
                      </b-row>
                      <b-row>
                        <b-col>Защита:</b-col>
                        <b-col>{{ createShip.typeShip.protection }}</b-col>
                      </b-row>

                      <b-row>
                        <b-col>Стоимость:</b-col>
                        <b-col>{{ createShip.typeShip.cost }}</b-col>
                      </b-row>
                    </b-container>
                  </b-card>
                  <b-btn variant="primary" class="create-button" @click="createShipClick">Создать</b-btn>
                </div>
              </b-form>

              <b-alert variant="danger" :show="moneyError" style="text-align: center">У вас не достаточно денег</b-alert>
            </b-card>
          </b-collapse>
        </div>

        <hr>

        <div v-for="ship in listShips">
          <b-card>
            <div><b style="font-size: large">{{ ship.nameShip }}:</b> hp: {{ ship.hp }} sp: {{ ship.speed }} pr: {{ ship.protection }}</div>
          </b-card>
        </div>
      </div>
    </div>
</template>

<script>
    import { GET_TYPES_SHIP } from "@/vuex/actions/game";
    import { GET_USER_BASE, GET_USER_SHIPS, CREATE_SHIP, GET_ITEM_BASE} from "@/vuex/actions/user";
    import { createShip } from "@/services/websocket";
    import { addErrorHandler } from "@/services/websocket";
    import { NOT_ENOUGH_MONEY_CREATE_SHIP } from "@/services/errors/shipErrors";
    import { createShipForPosition } from "@/webgl/CreateSpaceships";

    export default {
        name: "BaseInformation",
        data: function(){
          return {
            createShip: {
              base: null,
              nameShip: null,
              typeShip: null,
            },
            moneyError: false,
          }
        },
        computed: {
          base(){
            this.createShip.base = this.$store.getters.base.base
            this.createShip.system = this.$store.getters.base.base.system
            return this.$store.getters.base.base
          },
          listShips(){
            return this.$store.getters.base.listShips
          },
          isModified(){
            return !!this.$store.getters.base.status
          },
          types(){
            return this.$store.getters.types
          },
        },
        mounted: function () {
          this.$store.dispatch(GET_TYPES_SHIP)
        },

        created: function() {
          addErrorHandler(typeError => {

            if(typeError == NOT_ENOUGH_MONEY_CREATE_SHIP){
              this.moneyError=true;
            }
          })
        },
        methods: {
          createShipClick: function () {
            createShip(this.createShip)

            this.$store.dispatch(GET_USER_SHIPS)
          },
        },
    }
</script>

<style scoped>

  .header-base{
    text-align: center;
  }
  .create-button{
    width: 100%;
  }

  .collapse-block{
    margin-bottom: 3%;
  }

</style>
