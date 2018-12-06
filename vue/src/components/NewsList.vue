<template>
    <div class="news-list">
      <b-container>
        <b-row class="title-name">
            <h1>
              Новости
            </h1>
        </b-row>
        <b-row v-for="n in news">
          <b-card
            :title="n.name"
            :sub-title="new Date(n.date).toLocaleDateString('ru', {
              year: 'numeric',
              month: 'long',
              day: 'numeric'
            })"
            class="news">
            <hr>
            <p>
              {{ n.fullName.substring(0, 200) }} ...
            </p>

            <div>
              <b-button :to='{ name: "NewsPage", params: { id: n.id } }' variant="outline-primary" size="sm">Подробнее</b-button>
            </div>

          </b-card>

        </b-row>
      </b-container>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "NewsList",
        props:{
          count: Number
        },
        data: function () {
          return {
            news: null
          }
        },
        created: function () {
          var addedQuery;
          if(this.count === undefined) addedQuery = "";
          else addedQuery = "?count="+this.count;

          axios
            .get('http://localhost:8080/api/news'+addedQuery)
            .then(response => {
              this.news = response.data;
            })
            .catch(error => alert(error));
        }
    }
</script>

<style scoped>
  .news{
    margin: 10px;
    width: 100%;
  }

  .title-name{
    padding-top: 10px;
    color: #fff;
  }
  .title-name > h1{
    width: 100%;
    text-align: center;
  }

  .short-text{
    margin-top: 10px;
    margin-bottom: 10px;
    width: 100%;
  }

  .news-list {
    background: linear-gradient(180.60379733790592deg, rgba(156, 222, 255, 1) 1.9009584664536745%, rgba(155, 221, 254, 1) 1.9009584664536745%, rgba(0, 112, 181, 1) 92.84877529286476%);
  }


</style>
