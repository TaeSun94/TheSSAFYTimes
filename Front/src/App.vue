<template>
  <v-app>
    <side-bar app></side-bar>
    <v-main style="background-color:#ebebeb ">
    <nav-bar app></nav-bar>
    <router-view></router-view>
    </v-main>
    
  </v-app>
</template>

<script>

export default {
  name: 'App',
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