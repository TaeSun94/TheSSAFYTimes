<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-8 col-lg-9" style="">            
                <v-row 
                no-gutters
                >                
                    <v-col v-for="team in teams" :key="team" class="p-6" cols="12" sm="5" style="margin:3%">
                        <v-hover v-slot:default="{ hover }">
                            <v-card
                            class="mx-auto"
                            color="grey lighten-4"
                            max-width="600"
                            >
                            
                            <v-img
                                :aspect-ratio="16/9"
                                :src="'../assets/dv2.jpg'"
                            >
                                <v-expand-transition>
                                <div
                                    v-if="hover"
                                    class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text"
                                    style="height: 100%; backgroundColor:#c00000"
                                >
                                {{team.name}}
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
                                >
                                <v-icon>mdi-github</v-icon>
                                </v-btn>
                                <h3 class="display-1 font-weight-light mb-2"  style="color:#000066">{{team.name}}</h3>
                                <div class="font-weight-light title mb-2" style="color:#000066">
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
                    saying : "열심히 하겠습니당.",
                    position :"Back-End",
                    thumbnail : "dv2.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "김지웅",
                    saying : "열심히 하겠습니당.",
                    position :"Back-End",
                    thumbnail : "dv2.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "유태선",
                    saying : "열심히 하겠습니당.",
                    position :"Front-End",
                    thumbnail : "dv2.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "이승경",
                    saying : "열심히 하겠습니당.",
                    position :"Front-End",
                    thumbnail : "dv2.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                },
                {
                    name : "정성윤",
                    saying : "열심히 하겠습니당.",
                    position :"Front-End",
                    thumbnail : "dv2.jpg",
                    gitLink : "https://github.com/Sunaaaa"
                }      
            ]
        }
    }
}
</script>

<style>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}
</style>