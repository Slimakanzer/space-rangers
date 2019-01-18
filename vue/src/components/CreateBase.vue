<template>
    <div>
      <b-btn variant="outline-danger" size="sm" class="create-button" v-b-toggle.collapse-base-create >Создать базу</b-btn>
      <div>
        <b-collapse id="collapse-base-create">
          <b-card>
            <b-form class="form">
              <b-form-input type="text" required placeholder="Название" v-model="createBase.nameBase"></b-form-input>
              <b-btn variant="primary" class="create-button" @click="createBaseFunc">Создать</b-btn>
              <hr>
              <b-row>
                <b-col>Стоимость:</b-col>
                <b-col>1500</b-col>
              </b-row>
            </b-form>
          </b-card>
        </b-collapse>
      </div>
    </div>
</template>

<script>
    import { CREATE_BASE, GET_USER, GET_USER_BASE} from "@/vuex/actions/user";
    import {createBaseWeb} from "@/services/websocket";

    export default {
        name: "CreateBase",
        data: function () {
          return {
            createBase: {
              nameBase: null
            }
          }
        },

        methods: {
          createBaseFunc(){
            this.$store.dispatch(CREATE_BASE, this.createBase)
              .then((base) => {

                createBaseWeb(base);
                this.$store.dispatch(GET_USER)
                this.$store.dispatch(GET_USER_BASE)
              })
          }
        }
    }
</script>

<style scoped>
  .create-button{
    width: 100%;
  }

</style>
