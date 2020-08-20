<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-8 col-lg-7 col-sm-10 title_img" style="">     
            <h2 class="ml-6"> We are</h2>        
            <v-img
                src="@/assets/ssaf.png"
                width="80%"
            ></v-img>
            <v-row 
            no-gutters
            >                
                <v-col v-for="(team, k) in teams" :key="k" class="p-6" cols="12" sm="5" style="margin:3%">
                    <v-hover v-slot:default="{ hover }">
                        <v-card
                        class="mx-auto"
                        color="grey lighten-4"
                        max-width="600"
                        >
                        
                        <v-img
                            :aspect-ratio="16/9"
                            :src="getImg(team.thumbnail)"
                        >
                            <v-expand-transition>
                            <div
                                v-if="hover"
                                class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3"
                                style="height: 100%; backgroundColor:white; color: black; font-weight:900"
                            >
                            {{team.nic}}
                            </div>
                            </v-expand-transition>
                        </v-img>
                        <v-card-text
                            class="pt-6"
                            style="position: relative;"
                        >
                            <v-btn
                            absolute
                            :color="color"
                            class="white--text"
                            fab
                            large
                            right
                            top
                            :href="team.gitLink"
                            target="_blank"
                            >
                            <v-icon x-large>mdi-github</v-icon>
                            </v-btn>
                            <h3 class="display-1 font-weight-light mb-2 text-center"  style="color:#000066">{{team.name}}</h3>
                            <div class="font-weight-light title mb-2 text-center" style="color:#000066">
                            {{team.position}}
                            </div>
                        </v-card-text>
                        </v-card>
                    </v-hover>
                </v-col>         
            </v-row>
    </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
export default {
    data (){
        return {
            color : '#c00000',
            secondColor : '#191970',
            teams : [
                {
                    name : "공선아",
                    nic : "선아",
                    saying : "열심히 하겠습니당.",
                    position :"Back-End",
                    thumbnail : "dv1.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "김지웅",
                    nic : "찌웅이형",
                    saying : "열심히 하겠습니당.",
                    position :"Back-End",
                    thumbnail : "dv2.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "유태선",
                    nic : "태서니",
                    saying : "열심히 하겠습니당.",
                    position :"Front-End",
                    thumbnail : "dv3.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "이승경",
                    nic : "승경",
                    saying : "열심히 하겠습니당.",
                    position :"Front-End",
                    thumbnail : "dv4.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "정성윤",
                    nic : "정옴팡",
                    saying : "열심히 하겠습니당.",
                    position :"Front-End",
                    thumbnail : "dv5.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                }      
            ]
        }
    },
    methods:{
        getImg(img) {
            return require("@/assets/"+img);
        }
    }
}
</script>

<style scoped>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}

h1{
    text-align: center;
    justify-content: center;
    font-size: 60px;
}

.v-image {
    margin-left: auto;
    margin-right:auto;
}
.no-gutters{

    justify-content: center;
}

h2{
    font-style: italic;
    border-bottom: 0px;
}
</style>