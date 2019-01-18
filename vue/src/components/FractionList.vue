<template>
    <div>
      <b-card v-for="fraction in fractions">
        <b-row>
          <b-col>
            <b-row>
              <b-col>Название:</b-col>
              <b-col>{{ fraction.nameFraction }}</b-col>
            </b-row>
            <b-row>
              <b-col>Кол-во игроков:</b-col>
              <b-col></b-col>
            </b-row>
          </b-col>
          <b-col v-if="!userInFraction">
            <b-btn class="in-button" variant="outline-success" @click="join(fraction.id)">Подать заявку</b-btn>
          </b-col>
        </b-row>
      </b-card>
    </div>
</template>

<script>
    import { JOIN_TO_FRACTION, GET_USER, UPDATE_USER_FRACTION } from "@/vuex/actions/user";
    import { GET_COUNT_USERS } from "@/vuex/actions/game";

    export default {
        name: "FractionList",
        computed: {
          fractions: function () {
            return this.$store.getters.fractions
          },
          userInFraction: function () {
            if(!this.$store.getters.userFraction) return false;
            if(this.$store.getters.userFraction.stateUserFraction.name == 'candidate') return false;
            else return true;
          },
        },

        methods: {
          join(id){
            this.$store.dispatch(JOIN_TO_FRACTION, id)
              .then(() => {
                this.$store.dispatch(GET_USER)
                this.$store.dispatch(UPDATE_USER_FRACTION)
              })
          },
        }
    }
</script>

<style scoped>
  .in-button{
    width: 100%;
    height: 100%;
  }

</style>
