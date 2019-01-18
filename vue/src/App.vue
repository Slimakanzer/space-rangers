<template>
    <div style="height: 100%">
      <audio src="/static/space.mp3" autoplay="autoplay" loop="loop"></audio>

      <app-header></app-header>
      <router-view/>
    </div>
</template>

<script>
    import AppHeader from "@/components/AppHeader";
    export default {
        name: "App",
      components: {AppHeader}
    }
</script>

<style scoped>
</style>
