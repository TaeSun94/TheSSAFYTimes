<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-5 col-lg-9">
            <div>
                <v-hover v-slot:default="{ hover }">
                    <v-card
                    class="mx-auto"
                    color="grey lighten-4"
                    max-width="600"
                    >
                    <v-img
                        :aspect-ratio="16/9"
                        src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
                    >
                        <v-expand-transition>
                        <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                        >
                            "선아"
                        </div>
                        </v-expand-transition>
                    </v-img>
                    <v-card-text
                        class="pt-6"
                        style="position: relative;"
                    >
                        <v-btn
                        absolute
                        color="orange"
                        class="white--text"
                        fab
                        large
                        right
                        top
                        >
                        <v-icon>mdi-cart</v-icon>
                        </v-btn>
                        <h3 class="display-1 font-weight-light orange--text mb-2">공선아</h3>
                        <div class="font-weight-light title mb-2">
                            Back-End
                        </div>
                    </v-card-text>
                    </v-card>
                </v-hover>
                <v-hover v-slot:default="{ hover }">
                    <v-card
                    class="mx-auto"
                    color="grey lighten-4"
                    max-width="600"
                    >
                    <v-img
                        :aspect-ratio="16/9"
                        src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
                    >
                        <v-expand-transition>
                        <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                        >
                            "팽수!"
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
                        <v-icon>mdi-cart</v-icon>
                        </v-btn>
                        <h3 class="display-1 font-weight-light orange--text mb-2">김지웅</h3>
                        <div class="font-weight-light title mb-2">
                            DB
                        </div>
                    </v-card-text>
                    </v-card>
                </v-hover>
                <v-hover v-slot:default="{ hover }">
                    <v-card
                    class="mx-auto"
                    color="grey lighten-4"
                    max-width="600"
                    >
                    <v-img
                        :aspect-ratio="16/9"
                        src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
                    >
                        <v-expand-transition>
                        <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                        >
                            "쇼킹유!"
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
                        <v-icon>mdi-cart</v-icon>
                        </v-btn>
                        <h3 class="display-1 font-weight-light orange--text mb-2">유태선</h3>
                        <div class="font-weight-light title mb-2">
                            Front-End
                        </div>
                    </v-card-text>
                    </v-card>
                </v-hover>
            </div>
            <div>
                <v-hover v-slot:default="{ hover }">
                    <v-card
                    class="mx-auto"
                    color="grey lighten-4"
                    max-width="600"
                    >
                    <v-img
                        :aspect-ratio="16/9"
                        src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
                    >
                        <v-expand-transition>
                        <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                        >
                            "승경!"
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
                        <v-icon>mdi-cart</v-icon>
                        </v-btn>
                        <h3 class="display-1 font-weight-light orange--text mb-2">이승경</h3>
                        <div class="font-weight-light title mb-2">
                            Front-End
                        </div>
                    </v-card-text>
                    </v-card>
                </v-hover>
                <v-hover v-slot:default="{ hover }">
                    <v-card
                    class="mx-auto"
                    color="grey lighten-4"
                    max-width="600"
                    >
                    <v-img
                        :aspect-ratio="16/9"
                        src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
                    >
                        <v-expand-transition>
                        <div
                            v-if="hover"
                            class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                            style="height: 100%;"
                        >
                            "눈뷰신!"
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
                        <v-icon>mdi-cart</v-icon>
                        </v-btn>
                        <h3 class="display-1 font-weight-light orange--text mb-2">정성윤</h3>
                        <div class="font-weight-light title mb-2">
                            Front-End
                        </div>
                    </v-card-text>
                    </v-card>
                </v-hover>
            </div>

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