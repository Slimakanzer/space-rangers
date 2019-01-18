<template>
    <div>
      <div style="text-align: center">
        <h3>{{ fraction.nameFraction }}</h3>
      </div>
      <div>
        <b-btn variant="primary" v-b-toggle.fraction-join-collapse class="join-button">Список игроков: </b-btn>
        <div>
          <b-collapse id="fraction-join-collapse">
            <b-card>
              <b-btn variant="outline-danger" class="join-button" @click="update">Обновить</b-btn>

              <b-card v-for="userFraction in userFractions">
                <b-row>
                  <b-col cols="8">
                    <b-row>
                      <b-col>Логин:</b-col>
                      <b-col>Имя:</b-col>
                      <b-col>Фамилия:</b-col>
                      <b-col>Звание:</b-col>
                    </b-row>
                    <hr>
                    <b-row>
                      <b-col>{{ userFraction.user.login }}</b-col>
                      <b-col>{{ userFraction.user.firstName }}</b-col>
                      <b-col>{{ userFraction.user.lastName }}</b-col>
                      <b-col>{{ userFraction.stateUserFraction.nameNormal }}</b-col>
                    </b-row>
                  </b-col>
                  <b-col cols="4">
                    <div v-if="!isLeader(userFraction)">
                      <div v-if="isUserInFraction(userFraction)">
                        <b-btn class="command-button" variant="outline-warning" @click="deleteUser(userFraction)">Удалить</b-btn>
                      </div>
                      <div v-else>
                        <b-row>
                          <b-btn class="join-button" variant="outline-success" @click="acceptUser(userFraction)">Принять</b-btn>
                        </b-row>
                        <b-row>
                          <b-btn class="join-button" variant="outline-danger" @click="deleteUser(userFraction)">Отказать</b-btn>
                        </b-row>
                      </div>
                    </div>
                  </b-col>
                </b-row>


              </b-card>
            </b-card>
          </b-collapse>
        </div>
      </div>
    </div>
</template>

<script>
    import { GET_USERS_IN_FRACTION, ACCEPT_USER, DELETE_USER } from "@/vuex/actions/game";
    import { updateUserFraction } from "@/services/websocket";

    export default {
        name: "FractionLeaderInformation",

        computed: {
          fraction: function () {
            return this.$store.getters.userFraction.fraction
          },
          userFractions: function () {
            return this.$store.getters.userFractions
          },
        },
        mounted: function () {
          this.$store.dispatch(GET_USERS_IN_FRACTION, this.fraction.id)
        },
        methods: {
          isUserInFraction: function (userFraction) {
            if (userFraction.stateUserFraction.name !== 'candidate') return true;
            else return false;
          },
          isLeader: function(userFraction) {
            if (userFraction.stateUserFraction.name === 'leader') return true;
            else return false;
          },

          deleteUser(userFraction){
            this.$store.dispatch(DELETE_USER, userFraction)
              .then(()=> {
                updateUserFraction(userFraction.user.id)
                this.$store.dispatch(GET_USERS_IN_FRACTION, this.fraction.id)
              })
          },


          update(){
            this.$store.dispatch(GET_USERS_IN_FRACTION, this.fraction.id)
          },

          acceptUser(userFraction){
            this.$store.dispatch(ACCEPT_USER, userFraction)
              .then(()=>{
                updateUserFraction(userFraction.user.id)
                this.$store.dispatch(GET_USERS_IN_FRACTION, this.fraction.id)
              })
          }
        }
    }
</script>

<style scoped>
  .join-button{
    width: 100%;
  }
  .command-button{
    width: 100%;
    height: 100%;
  }

</style>
