<template>
    <div class="left-block-main">

      <div class="left-block-main">
        <b-btn variant="primary" v-b-toggle.collapse-user-list>
          User: {{ user.login }}
        </b-btn>


        <b-collapse id="collapse-user-list">
          <b-card>
            <h4 class="focus-block" style="text-align: center" @click="modalShow = !modalShow">{{ user.login }}</h4>
            <b-modal v-model="modalShow" size="lg" centered>
              <main-information></main-information>
            </b-modal>

            <h5 style="text-align: center">Уровень: {{ user.level }}</h5>
            <h5 style="text-align: center">Деньги: {{ user.coins }}</h5>
            <hr>
            <div class="create-base">
              <create-base></create-base>
            </div>
            <div>
              <b-btn variant="outline-primary" v-b-toggle.collapse-user-list-base class="collapse-btns">
                Базы: {{ bases.length }}
              </b-btn>

              <div class="collapse-block">
                <b-collapse id="collapse-user-list-base" class="collapse-block">
                  <div v-for="base in bases" @click="clickBase(base.id)" class="focus-block">
                    <b-card>
                      <div>Название: {{ base.nameBase }}</div>
                      <div>Система: {{ base.system.nameSystem }}</div>
                    </b-card>
                  </div>
                </b-collapse>
              </div>
            </div>



            <div>
              <b-btn variant="outline-primary" v-b-toggle.collapse-user-list-ships class="collapse-btns">
                Корабли: {{ ships.length }}
              </b-btn>

              <div class="collapse-block">
                <b-collapse id="collapse-user-list-ships" class="collapse-block">
                  <div v-for="ship in ships" class="focus-block" @click="clickShip(ship.id)">
                    <b-card>
                      <div>Название: {{ ship.nameShip}}</div>
                      <div>Система: {{ ship.system.nameSystem }}</div>
                      <div>hp: {{ ship.hp }} sp: {{ ship.speed }} pr: {{ ship.protection }}</div>
                    </b-card>
                  </div>
                </b-collapse>
              </div>
            </div>
          </b-card>
        </b-collapse>
      </div>
    </div>
</template>

<script>
    import {GET_USER_BASE, GET_USER_SHIPS, GET_ITEM_BASE, GET_ITEM_SHIP, UPDATE_USER, UPDATE_USER_FRACTION} from "@/vuex/actions/user";
    import FractionUserInformation from "@/components/FractionUserInformation";
    import { startWebGL } from "@/webgl/CreateFunc";
    import { addCreateHandler } from "@/services/websocket";
    import {createObjectShip} from "@/webgl/CreateFunc";
    import { GET_PLANET_ATTACK } from "@/vuex/actions/game";
    import CreateBase from "@/components/CreateBase";
    import FractionLeaderInformation from "@/components/FractionLeaderInformation";
    import { clearAll } from "@/webgl/AllParams";
    import MainInformation from "@/components/MainInformation";

    export default {
        name: "UserDisplay",
      components: {MainInformation, FractionLeaderInformation, CreateBase, FractionUserInformation},
      data: function() {
          return {
            modalShow: false,
          }
        },

        methods: {
          clickBase: function(idBase){
            this.$store.dispatch(GET_ITEM_BASE, idBase)
              .then(()=>{
                this.$root.$emit('clickBase')
              })
          },
          clickShip: function (idShip) {
            this.$store.dispatch(GET_ITEM_SHIP, idShip)
              .then(()=>{
                this.$root.$emit('clickShip')
              })
          },
        },

        beforeCreate: function(){
          this.$store.dispatch(GET_USER_BASE)
          this.$store.dispatch(GET_USER_SHIPS)
        },


        computed: {
          user(){
            return this.$store.getters.user
          },
          bases(){
            return this.$store.getters.bases
          },
          ships(){
            return this.$store.getters.ships
          },
        },

        mounted: function () {
          this.$store.dispatch(UPDATE_USER_FRACTION)

          startWebGL()

          addCreateHandler(data => {

            createObjectShip('shuttle', data, false);


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
        }
    }
</script>

<style scoped>

  .collapse-btns{
    width: 100%;
    text-align: left;
  }

  .buttons-info{
    width: 100%;
  }

  .collapse-block{
    margin-bottom: 3%;
  }

  .focus-block{
    cursor: pointer;
  }

  #collapse-user-list{
    max-height: 80%;
    overflow-y: auto;
  }

  .left-block-main{
    height: 100%;
  }

</style>
