<template>
  <v-footer padless style="margin-top:5%; background-color:#ebebeb; border-top:1px solid #252525">
    <v-col class="text-center" cols="12">
      <strong>SSAFience</strong>
      <router-link :to="{path:'/aboutus'}">선아</router-link>
      <router-link :to="{path:'/aboutus'}">지웅</router-link>
      <router-link :to="{path:'/aboutus'}">태선</router-link>
      <router-link :to="{path:'/aboutus'}">승경</router-link>
      <router-link :to="{path:'/aboutus'}">성윤</router-link>
    </v-col>
  </v-footer>
</template>

<script>
export default {
  name:"FooterBar",
  
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: black!important;
  margin: 5px;
}

</style>