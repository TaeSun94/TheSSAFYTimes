<template>
    <v-dialog v-model="dialog" max-width="300">
        <template v-slot:activator="{on,attrs}">
            <v-btn
                class="primary"
                dark
                v-bind="attrs"
                v-on="on"
            >
                명함
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
                <v-list-item-title class="headline mb-1">{{profile.memberId}}</v-list-item-title>
                <v-list-item-subtitle>{{profile.memberDesc}}</v-list-item-subtitle>
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
import {mapGetters} from'vuex';

export default {
    name:"",
    computed:{
        ...mapGetters('profile')
    },
    data(){
        return{
            dialog: false,
        }
    },
    created(){
        this.$store.dispatch('getProfile',this.$route.query.memberId);
    },
    methods:{
        moveBlog(){
            this.$route.push({path: `/profile/${profile.memberId}`});
        }
    }
}
</script>

<style>

</style>