<template>
<div class="wrapper" style="margin-top:7%">
    <div class="row">
          <v-container class="elevation-3 col-lg-7">
                <div id="app">
                    <div class="text-center main-title">
                        <h1 class="mb-2">공모전 인원 구함니다</h1>
                    </div>
                    <div class="text-right mr-5">
                        <small class="description">👀 조회수 /</small>
                        <small class="description"> SSAFY 3기 / </small>
                        <small class="description"> </small>
                    </div>
                <div class="tei">
                    <div class="title">
                        <p> 광주광역시 공모전 </p>
                    </div>
                    <hr>

                    <div class="content_inner">
                        <p>ssapy 인을 위한 블로그를 제작하려 합니다</p>
                        <p> 팀원은 총 n 명이 필요합니다. </p>
                        <p> 프론트 n명, 백엔드 n명이 필요한데 java, vue.js를 사용 하실줄 아는 분이면 좋겠슴다.</p>
                    </div>
                    <hr class="">
                    <div class="inner row">
                        <div class="col-6 member">
                            <p>현재팀원 (n명) </p>
                            <div class="member-content">
                                <div class="mr-5">
                                    <img width="30" src="@/assets/member.png"/>
                                    <small class="img-text">홍길동</small>
                                </div>
                                <div class="">
                                    <img width="30" src="@/assets/member.png"/>
                                    <small class="img-text">홍길동</small>
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                            <p>기술스택</p>
                            <div class="stack-content">
                        <div class="recruit_title">
                            <v-chip
                            class="ma-2"
                            color="green"
                            text-color="white"
                            >
                            Vue.js
                            <v-icon right></v-icon>
                            </v-chip>
                            <v-chip
                            class="ma-2"
                            color="green"
                            text-color="white"
                            >
                            JAVA
                            <v-icon right></v-icon>
                            </v-chip>
                                                        <v-chip
                            class="ma-2"
                            color="green"
                            text-color="white"
                            >
                            Spring
                            <v-icon right></v-icon>
                            </v-chip>
                        </div> 
                            </div>
                        </div>
                    </div>
                    <hr class="">
                    <div class="inner">                    
                        <div>
                            <p>팀매칭 진행율(%) </p>
                            <progress value='40' max='100'></progress>
                        </div>    
                    </div>                
                    <div class="delete text-right mr-5">
                        <v-btn rounded @click="deleteHandler" v-if="check"> 삭제 </v-btn>     
                    </div>
                    <div class="likeContent mt-5 row justify-content-end">
                        <div class="text-right mr-4"><v-btn rounded>관심등록</v-btn></div>
                        <div class="text-right mr-4"><v-btn rounded>지원하기</v-btn></div>                        
                    </div>
                </div>
            </div>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name:"TeamDetail",
    computed: {
        ...mapGetters(["team"])
    },
    created() {
        this.$store.dispatch("getTeam", `/community/teamdetail/${this.$route.params.no}`)
    },

}
</script>

<style scoped>
.member-content, .stack-content{
    display: flex;
    justify-content: center;
}
.tei{
    border: 1px solid ;
    border-radius: 20px;
    padding: 20px;
    margin-top:30px
}
.member{
    border-right: 0.3px solid #333;
}
.col-6{
    text-align: center;
}
.container{
  border-radius: 20px;
  
}
progress {
    height: 40px;
}
.img-text{
    display: block;
    text-align: center;
}
#app {
    padding: 30px;
}
.main-title h1{
  color: #FFB459
}
img {
    border-radius: 70%;
    width: 40px;
}
.title{
    font-size: 1.3rem;
    font-weight: 600;
}

#progressbar {
     background-color: black;
     border-radius: 13px; 
     padding: 3px;
}
.content-wrap {
    text-align: center;
}
.team_head {
    padding: 90px 0px 70px;
    background-color: white;
}
.team_title{
    text-align: center;
}
.team_name{
    font-size: 1.4rem;
}
.title, .content, .content_inner{
    color:black;
    margin:30px;
}
.team_content{
    text-align: left;
    height: 300px;
}
.container{
    background-color: #fff;
    padding: 20px;

}
@media  (max-width: 750px) {
    .container{
        padding: 0px;
    }
}

.inner {
    margin: 30px;
}
.row {
    
    justify-content: center;
    margin: 20px;
}
</style>