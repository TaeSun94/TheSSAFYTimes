<template>
  <div data-app>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="primary"
            dark
            v-bind="attrs"
            v-on="on"
            rounded
          >
            추가
          </v-btn> 
        </template>
        <v-card>
          <v-card-title class="headline font-weight-regular blue-grey white--text">Follower 등록</v-card-title>
          <v-card-text>
            <v-subheader class="pa-0">Who are you interested in?</v-subheader>
            <v-select
              v-model="selected"
              :items="members"
              label="Follower"
              multiple
              chips
            ></v-select>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
  import http from "@/http-common";
  export default {
    data () {
      return {
        dialog:false,
        isEditing: false,
        selected: [],
        members:[
          {
            value:'지웅',
            text:'지웅'
          },
          {
            value:'태선',
            text:'태선'
          },
          {
            value:'성윤',
            text:'성윤'
          },
          {
            value:'선아',
            text:'선아'
          },
          {
            value:'승경',
            text:'승경'
          },
        ],
      }
    },
    created(){
      http.get(`/`).then(({data})=>{
        this.members = data;
      })
    }
  }
</script>