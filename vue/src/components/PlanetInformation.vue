<template>
    <div>
      <div class="header-planet">
        <h4>{{ planet.namePlanet }}</h4>
        <h5>
          Управляет:
          <span v-if="planet.user">{{ planet.user.login }} <span v-if="isYour">(вы)</span> </span>
          <span v-else style="color: red">никто</span>
        </h5>
        <hr>
        <b-row>
          <b-col>Система:</b-col>
          <b-col>{{ planet.system.nameSystem }}</b-col>
        </b-row>
        <b-row>
          <b-col>Радиус:</b-col>
          <b-col>{{ planet.rplanet }}</b-col>
        </b-row>
        <b-row>
          <b-col>Условия:</b-col>
          <b-col>{{ planet.typeWeather.name }}</b-col>
        </b-row>
        <hr>
        <h5>Ресурсы:</h5>
        <b-row>
          <b-col>Название:</b-col>
          <b-col>Стоимость:</b-col>
          <b-col>Количество:</b-col>
        </b-row>
        <b-row v-for="resource in planet.resources">
          <b-col>{{ resource.typeResources.name }}</b-col>
          <b-col>{{ resource.typeResources.cost }}</b-col>
          <b-col>{{ resource.count }}</b-col>
        </b-row>
        <hr>
      </div>

      <div v-if="attack && !isYour">
        <b-btn v-if="attack.attacked" variant="danger" class="attack-button" @click="attackPlanet">Захватить</b-btn>
        <b-alert v-else show variant="warning">Недостаточно кораблей ({{ attack.countShips }})</b-alert>
      </div>

    </div>
</template>

<script>
    import { GET_PLANET_ATTACK, ATTACK_PLANET } from "@/vuex/actions/game";

    export default {
        name: "PlanetInformation",
        computed: {
          planet: function () {
            return this.$store.getters.planet
          },

          attack: function () {
            return this.$store.getters.attack
          },

          isYour: function () {
            if (this.planet.user.id === this.$store.getters.user.id) return true; else return false;
          }


        },
      methods: {
          attackPlanet() {
            alert('hello')
            this.$store.dispatch(ATTACK_PLANET, this.planet.id)
          }
      }
    }
</script>

<style scoped>
  .header-planet{
    text-align: center;
  }

  .attack-button{
    width: 100%;
  }

</style>
