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
          <b-btn @click="show=true" >Log in</b-btn>

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
        methods: {
          onSubmit: function () {
            if(!(this.form.username || this.form.password)){
              alert("Set your username and password")
            }else
              var params = new URLSearchParams();
              params.append('username', this.form.username);
              params.append('password', this.form.password);

              axios.post('http://localhost:8080/login', params, {
                withCredentials: true
              })
                .then(response =>{
                  this.error=false;
                })
                .catch(error =>{
                  console.log(error)
                  this.error=true
                })


          },
          onClickGoogle: function () {
            window.location='http://localhost:8080/login/google'
          }

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
