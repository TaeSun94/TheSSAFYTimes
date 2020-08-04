<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-5">
            <v-carousel
                cycle
                height="400"
                hide-delimiter-background
                show-arrows-on-hover
            >
                <v-carousel-item
                v-for="(slide, i) in slides"
                :key="i"
                >
                <v-sheet
                    :color="colors[i]"
                    height="100%"
                >
                    <v-row
                    class="fill-height"
                    align="center"
                    justify="center"
                    >
                    <div class="display-3">{{ slide }} Slide</div>
                    </v-row>
                </v-sheet>
                </v-carousel-item>
            </v-carousel>
        </v-container>
    </div>
    <footer-bar></footer-bar>

</div>
</template>

<script>
export default {
  name: "Main",
    data () {
      return {
        colors: [
          'indigo',
          'warning',
          'pink darken-2',
          'red lighten-1',
          'deep-purple accent-4',
        ],
        slides: [
          'First',
          'Second',
          'Third',
          'Fourth',
          'Fifth',
        ],
      }
    },

}

</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
.wrapper {
  font-family: 'Noto Sans KR', sans-serif;
}

</style>