<template>
  <v-app>
    <side-bar app></side-bar>
    <v-main style="background-color:#ebebeb ">
    <nav-bar app></nav-bar>
    <vue-scroll-progress-bar height="0.4rem" backgroundColor="orange" style=""/>
    <router-view></router-view>
    </v-main>
    
  </v-app>
</template>

<script>
import { VueScrollProgressBar } from '@guillaumebriday/vue-scroll-progress-bar'

export default {
  name: 'App',
  components: {
      VueScrollProgressBar,
    }
};
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
body {
  font-family: 'Noto Sans KR', sans-serif;
}
/* .v-main__wrap{
  margin-top: 20px;
} */
h2 {
    font-weight: 800;
    font-size: 2.7em;
    border-bottom: 2px solid;
    display: inline;
    
}


</style>