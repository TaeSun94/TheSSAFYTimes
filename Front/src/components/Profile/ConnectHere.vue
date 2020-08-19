<template>
<!-- <div data-app> -->
  <v-row justify="center">
    <v-dialog v-model="dialog" max-width="290">
      <template v-slot:activator="{ on, attrs }">
        <v-btn tile large depressed dark v-bind="attrs"
          v-on="on">연락처</v-btn>
      </template>
      <v-card>
        <v-card-title class="headline">{{ value.memberLastName}}{{value.memberFirstName}}'s 연락처</v-card-title>
        <v-card-text>
          Mail : {{ value.memberEmail }}<br>
          Phone : {{ value.memberPhone}}
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
<!-- </div> -->
</template>

<script>
  export default {
    name:"ConnectHere",
    props:{
      value:{},
    },
    data () {
      return {
        dialog: false,
      }
    }
  }
</script>

<style scoped>
#phone{
  font-size: 20px;
}
</style>