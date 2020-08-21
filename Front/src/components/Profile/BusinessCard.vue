<template>
    <v-dialog v-model="dialog" max-width="300">
        <template v-slot:activator="{on,attrs}">
            <v-btn icon v-bind="attrs"
             v-on="on">
              <span>{{value.memberId}}</span>
            </v-btn>
        </template>
        <v-card
            class="mx-auto"
            max-width="344"
            outlined
        >
            <v-list-item three-line>
            <v-list-item-content>
                <div class="overline mb-4">The SSAFY Times</div>
                <v-list-item-title class="headline mb-1">{{value.memberId}}</v-list-item-title>
                <v-list-item-subtitle>{{value.memberDesc}}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-avatar
                tile
                size="80"
                color="grey"
            ></v-list-item-avatar>
            </v-list-item>
            <v-card-actions>
            <v-btn text @click="moveBlog">Blog로 이동하기</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
export default {
    name:"BusinessCard",
    props:{
      value:{},
    },
    data(){
        return{
            dialog: false,
        }
    },
    methods:{
        moveBlog(){
            this.$route.push({path: `/profile/${this.value.memberId}`});
        }
    }
}
</script>

<style>

</style>