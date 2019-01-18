<template>

    <div class="main-user">


      <div class="user-list-block">
        <div class="list-block">
          <user-display></user-display>
        </div>
      </div>

      <div class="action-list-block">
        <div class="list-block">
          <item-display></item-display>
        </div>
      </div>



      <div class="canvas-block" id="canvas-block">
        <canvas id="canvas"></canvas>
      </div>

      <!--TODO testing websocket-->

    </div>
</template>

<script>
    import UserDisplay from "@/components/UserDisplay";
    import ItemDisplay from "@/components/ItemDisplay";
    import { addHandler } from "@/services/planets";
    import { startWebGL } from "@/webgl/CreateFunc";
    import { GET_PLANET, GET_PLANET_ATTACK } from "@/vuex/actions/game";

    export default {
        name: "UserPage",
      components: {ItemDisplay, UserDisplay},
      data: function(){
          return {
          }
        },

      mounted: function () {
        addHandler((planet) => {

          // TODO add back call
          this.$store.dispatch(GET_PLANET, planet.id)
            .then(()=> {

              this.$store.dispatch(GET_PLANET_ATTACK, planet.id)

              this.$root.$emit('clickPlanet')
            })
        })



      },
    }
</script>

<style scoped>


  .canvas-block{
    position: fixed;
    overflow: hidden;
    z-index: 1;
  }

  .action-list-block, .user-list-block{
    position: absolute;
    z-index: 10;
    height: 100%;
    width: 20%;
  }

  .main-user{
    /*background-color: black;*/
    height: 100%;
  }

  .list-block{
    margin-top: 5%;
    position: fixed;
    width: 20%;
    height: 100%;
  }

  .user-list-block {
    left: 0;
  }

  .action-list-block{
    right: 0;
  }

</style>
