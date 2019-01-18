<template>
    <div>
      <div class="create-button">
        <b-btn variant="primary" v-b-toggle.collapse-fraction-create>Создать фракцию</b-btn>
        <div>
          <b-collapse id="collapse-fraction-create">
            <b-card>
              <b-form>
                <b-form-input type="text" required placeholder="Название" v-model="createFraction.nameFraction"></b-form-input>
                <hr>
                <b-row>
                  <b-col>
                    <b-btn variant="outline-success" class="create-button-main" @click="createFractionFunc">Создать</b-btn>
                  </b-col>
                  <b-col>
                    <h5>Стоимость: 10000</h5>
                  </b-col>
                </b-row>
                <b-alert variant="warning" :show="errorCreate">
                  У вас не хватает денег
                </b-alert>
              </b-form>
            </b-card>
          </b-collapse>
        </div>
      </div>
    </div>
</template>

<script>
    import { CREATE_FRACTION, GET_ALL_FRACTIONS} from "@/vuex/actions/game";
    import { GET_USER } from "@/vuex/actions/user";

    export default {
        name: "CreateFraction",
        data: function () {
          return {
            createFraction: {
              nameFraction: null
            },
            errorCreate: false
          }
        },
        methods: {
            createFractionFunc(){
              this.$store.dispatch(CREATE_FRACTION, this.createFraction)
                .then(() => {
                  this.$store.dispatch(GET_ALL_FRACTIONS)
                  this.$store.dispatch(GET_USER)
                  this.errorCreate = false
                })
                .catch(() => {
                  this.errorCreate = true
                })

            }
        }
    }
</script>

<style scoped>
  .create-button > button{
    width: 100%;
  }
  .create-button-main{
    width: 100%;
    height: 100%;
  }

</style>
