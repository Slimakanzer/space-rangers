<template>
    <div class="news-page">
      <b-container class="news-container">
        <b-row class="news-inner">
          <b-col cols="12">
            <h2>{{ news.name }}</h2>
            <h4>{{ new Date(news.date).toLocaleDateString('ru', {
              year: 'numeric',
              month: 'long',
              day: 'numeric'
              }) }}</h4>
          </b-col>
        </b-row>
        <hr>
        <b-row class="news-inner">
          <b-col cols="12">{{ news.fullName }}</b-col>
        </b-row>
      </b-container>
    </div>
</template>

<script>
    import axios from 'axios'
    import {url } from "@/main";

    export default {
        name: "NewsPage",
        data:function () {
          return{
            id: null,
            news: null
          }
        },
        mounted: function () {
          this.id = this.$route.params.id;
          axios
            .get(url+'/api/news/'+this.id)
            .then(response =>{
              this.news = response.data;
            })
        }
    }
</script>

<style>
  .news-inner{
    padding: 30px;
  }

  .news-container{
    border: 1px solid rgba(0, 0, 0, 0.125);
  }





</style>
