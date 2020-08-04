<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="col-lg-9">
            <v-card>
                <v-card-title>
                    <div class="textfield">
                        <h1 class="m-4"> 🧑‍🤝‍🧑 팀원 찾아요! </h1>
                    </div>
                    <v-spacer></v-spacer>
                </v-card-title>
                <div  class="text-right mr-5">
                  <router-link class="write-link" :to="{path:'/community/teamWrite'}">팀원 구하기</router-link>
                </div>
  
            </v-card>
            <v-card
                class="col-3"
                color="#26c6da"
                dark
                max-width="400"
            >
                <v-card-title>
                <v-icon
                    large
                    left
                >
                    mdi-twitter
                </v-icon>
                <span class="title font-weight-light">Twitter</span>
                </v-card-title>

                <v-card-text class="headline font-weight-bold">
                "Turns out semicolon-less style is easier and safer in TS because most gotcha edge cases are type invalid as well."
                </v-card-text>

                <v-card-actions>
                <v-list-item class="grow">
                    <v-list-item-avatar color="grey darken-3">
                    <v-img
                        class="elevation-6"
                        src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                    ></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                    <v-list-item-title>Evan You</v-list-item-title>
                    </v-list-item-content>

                    <v-row
                    align="center"
                    justify="end"
                    >
                    <v-icon class="mr-1">mdi-heart</v-icon>
                    <span class="subheading mr-2">256</span>
                    <span class="mr-1">·</span>
                    <v-icon class="mr-1">mdi-share-variant</v-icon>
                    <span class="subheading">45</span>
                    </v-row>
                </v-list-item>
                </v-card-actions>
            </v-card>
         <v-card
                class="col-3"
                color="#26c6da"
                dark
                max-width="400"
            >
                <v-card-title>
                <v-icon
                    large
                    left
                >
                    mdi-twitter
                </v-icon>
                <span class="title font-weight-light">Twitter</span>
                </v-card-title>

                <v-card-text class="headline font-weight-bold">
                "Turns out semicolon-less style is easier and safer in TS because most gotcha edge cases are type invalid as well."
                </v-card-text>

                <v-card-actions>
                <v-list-item class="grow">
                    <v-list-item-avatar color="grey darken-3">
                    <v-img
                        class="elevation-6"
                        src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                    ></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                    <v-list-item-title>Evan You</v-list-item-title>
                    </v-list-item-content>

                    <v-row
                    align="center"
                    justify="end"
                    >
                    <v-icon class="mr-1">mdi-heart</v-icon>
                    <span class="subheading mr-2">256</span>
                    <span class="mr-1">·</span>
                    <v-icon class="mr-1">mdi-share-variant</v-icon>
                    <span class="subheading">45</span>
                    </v-row>
                </v-list-item>
                </v-card-actions>
            </v-card>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    name:"TeamList",
    data() {
        return {
            page:1,
            perPage: 25,
            pageLength: 0,
            pageCount: this.pageLength/this.perPage
        }
    },
    computed: {
        ...mapGetters(["teams"])
    },
    methods: {
        rowClicked(row) {
            this.$router.push({path: `/community/teamdetail/${row.no}`});
        }
    },
    created() {
        this.$store.dispatch("getTeams", '');
    }
}
</script>

<style>

</style>