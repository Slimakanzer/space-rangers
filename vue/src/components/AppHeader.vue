<template>
    <div>
      <b-navbar toggleable="md" type="light">
        <b-navbar-brand>SpaceR</b-navbar-brand>
      </b-navbar>
      <b-navbar toggleable="md" type="light" fixed="top" class="nav-bar-background">

        <b-navbar-brand :to="{ name: 'MainPage' }">SpaceR</b-navbar-brand>

        <b-navbar-nav>
          <b-nav-item :to="{ name: 'NewsList' }">Новости</b-nav-item>
        </b-navbar-nav>


        <b-navbar-nav class="ml-auto">


          <b-btn @click="show=true" v-if="!user">Log in</b-btn>
          <div v-else>
            Добро пожаловать, <b>{{ user.firstName }} {{ user.lastName }}</b>
            <b-btn :to="{ name: 'UserPage'}" variant="danger">На карту</b-btn>
            <b-btn @click="onLogout" variant="danger">Logout</b-btn>
          </div>
          <!-- Modal Component -->
          <b-modal
            v-model="show"
            :lazy="true"
            ok-title="Login">
            <b-form
            @submit="onSubmit">
              <b-form-group label="Логин">
                <b-form-input type="text" placeholder="Username" v-model="form.username" required/>
              </b-form-group>

              <b-form-group label="Пароль">
                <b-form-input type="password" placeholder="Password" v-model="form.password" required/>
              </b-form-group>

              <b-form-group>
                <b-button variant="primary" class="login-button" type="submit">Вход</b-button>
              </b-form-group>

              <b-form-group>
                <b-button class="google-button" variant="outline-primary" @click="onClickGoogle">
                  <b-img rounded="circle" width="30" height="30"src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/1024px-Google_%22G%22_Logo.svg.png"></b-img>
                  Войти через Google
                </b-button>
              </b-form-group>
            </b-form>

            <b-alert :show="error" variant="danger" class="error-alert">
            Ошибка авторизации
          </b-alert>

            <div slot="modal-footer" class="w-100">
              <b-btn class="float-right" variant="primary" @click="show=false">
                Закрыть
              </b-btn>
            </div>

          </b-modal>
        </b-navbar-nav>

      </b-navbar>

    </div>
</template>

<script>
    import axios from 'axios'
    import {AUTH_LOGOUT, AUTH_REQUEST} from "@/vuex/actions/auth";
    import {MAIN_PAGE_URL, USER_PAGE_URL} from "@/router/actions/paths";
    import {GET_USER, AUTH_LOGOUT_USER} from "@/vuex/actions/user";
    import { url } from "@/main";

    export default {
        name: "AppHeader",
        data: function(){
          return{
            show: false,
            form:{
              username:'',
              password:''
            },
            error: false
          }
        },

        computed:{
          isAuthenticated(){
            return this.$store.getters.isAuthenticated
          },
          user(){
            return this.$store.getters.user
          },
        },

        mounted: function(){
            if(this.isAuthenticated){
              this.$store.dispatch(GET_USER)
            }
        },


        methods: {
          onSubmit: function () {
            if(!(this.form.username || this.form.password)){
              alert("Set your username and password")
            }else
              var params = new URLSearchParams();
              params.append('username', this.form.username);
              params.append('password', this.form.password);

            this.$store.dispatch(AUTH_REQUEST, params)
              .then(response =>{
                this.show = false
                this.error = false
                this.$store.dispatch(GET_USER)
                this.$router.push(USER_PAGE_URL)
              })
              .catch(error =>{
                this.$router.push(MAIN_PAGE_URL)
                this.error = true
              })

          },
          onClickGoogle: function () {
            window.location.replace(url+'/login/google')
          },

          onLogout: function(){

            this.$store.dispatch(AUTH_LOGOUT)
              this.$store.dispatch(AUTH_LOGOUT_USER)
              .then(() =>{
                this.$router.push(MAIN_PAGE_URL)
                this.user = null
              })
          },
        }
    }
</script>

<style scoped>

  .nav-bar-background{
    background: #fff;
  }

  .login-button, .google-button{
    width: 100%;
  }

  .error-alert{
    text-align: center;
  }

</style>
