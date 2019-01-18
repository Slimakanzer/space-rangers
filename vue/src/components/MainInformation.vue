<template>
    <div>
      <b-row>
        <b-col>
          <b-btn class="buttons-info" size="sm" variant="outline-primary" @click="clickMainInfo">Основная</b-btn>
        </b-col>
        <b-col >
          <b-btn class="buttons-info" size="sm" variant="outline-primary" @click="clickFractionInfo">Фракции</b-btn>
        </b-col>
        <b-col v-if="isLeader">
          <b-btn class="buttons-info" size="sm" variant="outline-danger" @click="clickLeaderInfo">Управление</b-btn>
        </b-col>
        <b-col>
          <b-btn class="buttons-info" size="sm" variant="outline-primary" @click="clickBattleInfo">Битвы</b-btn>
        </b-col>
      </b-row>

      <b-card>
        <fraction-user-information v-if="showFractionInfo"></fraction-user-information>
        <fraction-leader-information v-if="showLeaderInfo"></fraction-leader-information>
        <user-information v-if="showMainInfo"></user-information>
      </b-card>
    </div>
</template>

<script>
    import FractionLeaderInformation from "@/components/FractionLeaderInformation";
    import FractionUserInformation from "@/components/FractionUserInformation";
    import UserInformation from "@/components/UserInformation";
    export default {
        name: "MainInformation",
      components: {UserInformation, FractionUserInformation, FractionLeaderInformation},
      data: function (){
          return{
            showFractionInfo: false,
            showBattleInfo: false,
            showMainInfo: true,
            showLeaderInfo: false
          }
        },
        methods: {
          clickFractionInfo: function () {
            this.showFractionInfo = true;
            this.showBattleInfo = false;
            this.showMainInfo = false;
            this.showLeaderInfo = false;
          },

          clickBattleInfo: function () {
            this.showFractionInfo = false;
            this.showBattleInfo = true;
            this.showMainInfo = false;
            this.showLeaderInfo = false;
          },
          clickMainInfo: function () {
            this.showFractionInfo = false;
            this.showBattleInfo = false;
            this.showMainInfo = true;
            this.showLeaderInfo = false;
          },
          clickLeaderInfo: function () {
            this.showFractionInfo = false;
            this.showBattleInfo = false;
            this.showMainInfo = false;
            this.showLeaderInfo = true;
          },
        },

        computed: {
          isLeader() {
            if(!this.$store.getters.userFraction) return false;
            if (this.$store.getters.userFraction.stateUserFraction.name === 'leader') return true;
            return false;
          }
        }
    }
</script>

<style scoped>

  .buttons-info{
    width: 100%;
  }

</style>
