<template>
<div class="wrapper" style="margin-top:6%;">
    <div class="row">
      <v-container class="col-lg-9 col-sm-10">
          <v-row class="fill-height" align="center" justify="center">
            <h1 class="mr-10" style="margin-top: 4%; margin-bottom:5%">Monthly</h1>
            <div @click="to('ssafytimes')" class="book" style="margin: 6% 20% 0% 20%">
              <div class="cover"></div>
              <h2>The SSAFY <span>Times 8월호</span></h2>
              <span class="writer">Designed By <i>SSAFience</i></span>
            </div>
            <template v-for="(item, i) in items" >
              <v-col :key="i" cols="12" md="4" style="margin-top: 15%">
                <v-hover v-slot:default="{hover}">
                  <v-card :elevation="hover ? 12 : 2"
                    :class="{'on-hover': hover}"
                    @click="to(item.to)">
                    <v-img :src="item.img" height="225px">
                      <v-card-title class="title white--text">
                        <v-row
                          class="fill-height flex-column"
                          justify="space-between">
                          <p class="mt-4 subheading text-center">{{item.title}}</p>

                          <div>
                            <p class="ma-0 body-1 font-weight-bold text-center">{{item.text}}</p>
                            <p class="caption font-weight-medium text-center">{{item.subtext}}</p>
                          </div>
                        </v-row>
                      </v-card-title>
                    </v-img>
                  </v-card>
                </v-hover>
              </v-col>
            </template>
            
          </v-row>
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
        items: [
          {
            title: '프로그래밍 게시판',
            text: "Whatever, Whenever, ask us in Markdown",
            subtext: '지금부터 궁금한 것이 생기면. 더 쉽게. 더 자세하게.',
            img: 'https://cdn.pixabay.com/photo/2015/09/05/23/28/coding-926242_960_720.jpg',
            to: "/community/programlist"
          },
          {
            title: '자유 게시판',
            text: 'Even if it doesn’t mean anything, it’s okay',
            subtext: '어떤 것이든 세상을 바꿀 아이디어의 시작입니다.',
            img: 'https://cdn.pixabay.com/photo/2016/03/27/23/11/post-it-notes-1284667_960_720.jpg',
            to: "/community/freelist"
          },
          {
            title: '팀매칭 게시판',
            text: 'Realize your ideas. With a reliable partner',
            subtext: '동료와 함께라면 못할 것이 없어요.',
            img: 'https://cdn.pixabay.com/photo/2020/07/08/04/12/work-5382501_960_720.jpg',
            to: "/community/teamlist"

          },
        ],
      }
    },
  methods: {
    to(link) {
      if(link==='ssafytimes'){
        alert("The SSAFY Times 최근 호를 불러옵니다.")
      }
      this.$router.push({path: link});
    },
  }
}

</script>

<style scoped>
.v-card {
  transition: opacity .4s ease-in-out;
}

.v-card:not(.on-hover) {
  opacity: 0.6;
}
.v-card p {
  font-size: 27px;
  font-family:Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}
.book {
  position: relative;
  top: 0;
  width: 400px;
  height: 600px;
  background: #fff;
  transform: rotate(-15.5deg) skewX(10deg);
  box-shadow: -35px 35px 50px rgba(0,0,0,1);
  transition: 0.5s;

}
.book:hover {
  transform: rotate(-25.5deg) skewX(10deg) translate(20px, -20px);
  box-shadow: -50px 50px 100px rgba(0,0,0,1);
}
.book:before {
  content: '';
  width: 30px;
  height: 100%;
  background: #6f1a1a;
  position: absolute;
  top: 0;
  left: 0;
  transform: skewY(-45deg) translate(-30px, -15px);
  box-shadow: inset -10px 0 20px rgba(0,0,0,0.2);
}
.book:after {
  content: '';
  width: 100%;
  height: 30px;
  background: #ccc;
  position: absolute;
  bottom: 0;
  left: 0;
  transform: skewX(-45deg) translate(15px, 30px);

}
.book h2 {
  position: absolute;
  bottom: 120px;
  left: 20px;
  font-size: 3.5em;
  line-height: 1em;
  color: #fff;
  font-family: "Times New Roman", Times, serif;


}
.book h2 span
{
  background-color: black;
  /* background: url(https://cdn.pixabay.com/photo/2015/05/01/14/46/new-york-748595_960_720.jpg); */
  background-attachment: fixed;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-family: "Times New Roman", Times, serif;


}
.book .writer {
  position: absolute;
  bottom: 20px;
  right: 20px;
  color: #262626;
  font-size: 0.6em;
  padding-top: 5px;
  font-weight: 500;
  border-top-left-radius: 1px solid #262626;
}
.book .writer i {
  font-weight: 700;
}
.book .cover {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 70%;
  background: url(https://cdn.pixabay.com/photo/2015/05/01/14/46/new-york-748595_960_720.jpg);
  background-size: cover;
}
</style>