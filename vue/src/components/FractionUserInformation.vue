<template>
    <div v-if="user">


      <b-card v-if="fraction"
              class="card-fraction"
              overlay
              :img-src="url(fraction.stateUserFraction.name)"
              text-variant="white"
      >
        <h4 class="fraction-name"><b>{{ fraction.fraction.nameFraction }}</b></h4>
        <hr>
        <b-row>
          <b-col><h5>Дата:</h5></b-col>
          <b-col><h5>{{ date(fraction.date) }}</h5></b-col>
        </b-row>
        <b-row>
          <b-col><h5>Статус:</h5></b-col>
          <b-col><h5>{{ fraction.stateUserFraction.nameNormal }}</h5></b-col>
        </b-row>

        <div v-if="userInFraction">
          <b-btn class="leave-button" variant="outline-warning" @click="outFromFraction">Выйти из фракции</b-btn>
        </div>
      </b-card>


      <div v-if="!userInFraction" class="text-fraction">
        <create-fraction></create-fraction>
        <b-alert variant="danger" show>
          Вы не состоите во фракции
        </b-alert>
      </div>
      <hr>
      <div class="all-fractions">
        <fraction-list></fraction-list>
      </div>
    </div>
</template>

<script>
    import FractionList from "@/components/FractionList";
    import { GET_ALL_FRACTIONS } from "@/vuex/actions/game";
    import CreateFraction from "@/components/CreateFraction";
    import { OUT_FROM_FRACTION, GET_USER, UPDATE_USER_FRACTION } from "@/vuex/actions/user";

    export default {
        name: "FractionUserInformation",
      components: {CreateFraction, FractionList},
      computed: {
          fraction: function () {
            return this.$store.getters.userFraction
          },

          userInFraction: function () {
            if(!this.$store.getters.userFraction) return false;
            if(this.$store.getters.userFraction.stateUserFraction.name === 'candidate') return false;
            else return true;
          },
          user: function () {
            return this.$store.getters.user;
          }
        },

        mounted: function() {
          this.$store.dispatch(UPDATE_USER_FRACTION)
          this.$store.dispatch(GET_ALL_FRACTIONS)
        },

        methods: {
          date: function (date) {
            return new Date(date).toLocaleDateString('ru', {
              year: 'numeric',
              month: 'long',
              day: 'numeric'
            })
          },

          outFromFraction() {
            this.$store.dispatch(OUT_FROM_FRACTION)
              .then(() => {
                this.$store.dispatch(GET_USER)
                this.$store.dispatch(UPDATE_USER_FRACTION)
              })
          },

          url: function (name) {
            if (name == 'player') return "https://picsum.photos/900/250/?image=654"
            if (name == 'left') return "https://picsum.photos/900/250/?image=120"
            return "https://picsum.photos/900/250/?image=1042"
          }
        }
    }
</script>

<style scoped>
  .fraction-name{
    text-align: center;
  }

  hr{
    background: white;
  }
  .card-fraction{
    margin-bottom: 5%;
  }
  .text-fraction{
    text-align: center;
  }
  .create-button > button{
    width: 100%;
  }
  .leave-button{
    width: 100%;
  }
</style>
