<template>
    <div>
      <b-row>
        <b-col>Login:</b-col>
        <b-col>Имя:</b-col>
        <b-col>Фамилия:</b-col>
      </b-row>
      <hr>
      <b-row>
        <b-col>{{ user.login }}</b-col>
        <b-col>{{ user.firstName }}</b-col>
        <b-col>{{ user.lastName }}</b-col>
      </b-row>
      <hr>

      <b-btn variant="outline-primary" class="create-button" v-b-toggle.update-user-info-collapse>Изменить данные</b-btn>
      <b-collapse id="update-user-info-collapse">
        <b-card>

          <b-form class="form"
          @submit="updateInfoUser">
            <b-form-input type="text" required placeholder="Login" v-model="userInfo.login"></b-form-input>
            <b-form-input type="text" required placeholder="Имя" v-model="userInfo.firstName"></b-form-input>
            <b-form-input type="text" required placeholder="Фамилия" v-model="userInfo.lastName"></b-form-input>
            <b-btn variant="primary" class="create-button" type="submit">Сохранить</b-btn>
          </b-form>

        </b-card>
      </b-collapse>
    </div>
</template>

<script>
    import { UPDATE_INFO_USER } from "@/vuex/actions/user";

    export default {
        name: "UserInformation",
        computed: {
          user() {
            return this.$store.getters.user
          },

          userFraction() {
            return this.$store.getters.userFraction
          }
        },
        data: function () {
          return {
            userInfo: {
              login: null,
              firstName: null,
              lastName: null
            }
          }
        },

        methods: {
          updateInfoUser() {
            var object = Object.assign({}, this.user)
            object.login = this.userInfo.login;
            object.firstName = this.userInfo.firstName;
            object.lastName = this.userInfo.lastName;
            this.$store.dispatch(UPDATE_INFO_USER, object)
          }
        }
    }
</script>

<style scoped>
  .create-button{
    width: 100%;
  }

</style>
