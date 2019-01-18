<template>
    <div class="right-block">
        <b-btn variant="danger" class="right-list" v-b-toggle.collapse-user-item>Открыть панель</b-btn>
      <b-collapse id="collapse-user-item">
          <b-card>
            <base-information v-if="showBase"></base-information>
            <ship-information v-if="showShip"></ship-information>
            <planet-information v-if="showPlanet"></planet-information>
          </b-card>
      </b-collapse>
    </div>

</template>

<script>
    import { GET_ITEM_BASE, GET_ITEM_SHIP } from "@/vuex/actions/user";
    import BaseInformation from "@/components/BaseInformation";
    import ShipInformation from "@/components/ShipInformation";
    import { newPositionCameraForBase } from "@/webgl/CreateFunc";
    import PlanetInformation from "@/components/PlanetInformation";

    export default {
        name: "ItemDisplay",
      components: {PlanetInformation, ShipInformation, BaseInformation},
      data: function(){
          return {
            showBase: false,
            showShip: false,
            showPlanet: false,
          }
        },
        mounted: function(){
          this.$root.$on('clickBase', this.setBase)
          this.$root.$on('clickShip', this.setShip)
          this.$root.$on('clickPlanet', this.setPlanet)
        },
        methods: {
          setBase: function () {
            const base = this.$store.getters.base.base
            newPositionCameraForBase(base.locationBaseX, base.locationBaseY, base.locationBaseZ)


            this.showBase = true
            this.showShip = false
            this.showPlanet = false
          },
          setShip: function () {
            this.showBase = false
            this.showShip = true
            this.showPlanet = false
          },
          setPlanet: function() {

            this.showBase = false
            this.showShip = false
            this.showPlanet = true

          },
          createShip: function () {
            
          }
        },
    }
</script>

<style scoped>

  .right-list{
    display: block;
    margin-left: auto;
  }

  .right-block{
    width: 100%;
    height: 100%;
  }

  #collapse-user-item{
    max-height: 80%;
    overflow-y: auto;
  }

</style>
