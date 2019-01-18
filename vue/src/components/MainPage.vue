<template>
    <div>


      <div class="video_wrap">
        <video class="video_bg" autoplay="autoplay" loop="loop">
          <source src="/static/test.mp4" type="video/mp4"/>
        </video>
      </div>
      <div class="video-background">

        <div>
          <h1>
            Space Rangers
          </h1>
          <h2>
            New revolutionary game
          </h2>
        </div>
      </div>
      <div>
        <news-list :count="4"></news-list>
      </div>

    </div>
</template>

<script>
    import NewsList from "@/components/NewsList";
    export default {
        name: "MainPage",
      components: {NewsList}
    }
</script>

<style scoped>

  .video_wrap {
    position: absolute;
    overflow: hidden;
    z-index: -999;
    min-height: 870px;
    max-height: 870px;
    display: block;
    width: 100%;
    max-width: 2560px;
  }

  .video-background{
    position: relative;
    min-height: 870px;
    max-height: 870px;
    background-color: rgba(0, 0, 0, 0.57);
  }

  .video-background > *{
    position: absolute;
    top: 50%;
    left: 50%;
    height: 30%;
    width: 50%;
    margin: -5% 0 0 -25%;
    text-align: center;
    color: white;
  }

  .video_bg {
    display: block;
    position: relative;
    z-index: 0;
    margin: 0 auto;
    width: 125%;
  }

</style>
