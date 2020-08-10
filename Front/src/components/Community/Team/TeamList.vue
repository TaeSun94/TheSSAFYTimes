<template>
<div class="wrapper" style="margin-top:7%">
    <div class="row">
        <v-container class="col-lg-9">
                <v-card-title>
                    <div class="textfield">
                        <h1 class="m-4 main-title"> 🧑‍🤝‍🧑 팀원 찾아요! </h1>
                    </div>
                    <v-spacer></v-spacer>
                </v-card-title>
                <div class="text-right mr-5">
                  <router-link class="write-link" :to="{path:'/community/teamWrite'}">팀원 구하기</router-link>
                </div>
             <div class="text-center">
                <div class="item_card recruit_card col-lg-6" v-for="team in this.teams " :key="team.index" @click="rowClicked(team)">
                    <div class="card_image">
                        <a data-v-74c97ae7="" style="background-color: rgb(255, 179, 71);text-align: center;display: flex;justify-content: center;align-items: center;">
                            <p  class="team_title" >{{ team.teamBoardTitle }}</p>
                        </a>
                    </div>
                    <div class="card_contents">
                        <div>
                            <a>분야 / {{ team.teamBoardCategory }}</a>
                        </div> 
                        <div class="sub_title  mb-3 text-right">
                            <span class="by_writer">작성자 🙋 <b>{{ team.memberId}}</b> </span> 

                        </div> 
                        <div class="recruit_title text-center">
                            <v-chip
                            class="ma-2"
                            color="green"
                            text-color="white"
                            >
                            📅 {{$moment(team.teamBoardEndDatetime).format('YYYY-MM-DD') }}  
                            
                            <v-icon right></v-icon>
                            </v-chip>
                        </div> 
                        <div class="recruit_desc">
                            <li>프론트엔드 {{ team.teamBoardFrontRemainCount }}명</li>
                            <li> 백엔드 {{ team.teamBoardBackRemainCount}}명 </li>
                            <div class="text-right">
                                <v-btn icon class="">
                                    <v-icon>mdi-heart</v-icon>
                                </v-btn>
                                <v-btn icon class="">
                                    <v-icon>mdi-bookmark</v-icon>
                                </v-btn>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
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
            pageCount: this.pageLength/this.perPage,

        }
    },
    computed: {
        ...mapGetters(["teams"])
    },
    methods: {
        rowClicked(team) {
            this.$router.push({path: `/community/teamdetail/${team.teamBoardNo}`})
        },
    },
    created() {
        this.$store.dispatch("getTeams", '/team/board');
    }
}
</script>

<style scoped>
.main-title{
    font-size: 2rem;
}
.team_title{
    font-weight: 900px;
    font-size: 1.5rem;
}
.text-content{
    height: 250px;
    display: flex;
    align-items: center;
}
.col-3 {
    padding:0px;
}
.col-lg-6 {
    padding:0px;
}
.recruit_card {
    border-radius: 10px;
    border:0.3px solid grey;
}
.bottom_section{
    margin-top:20px;
}
.container{
  background: #fff;
  border-radius: 20px;
  padding: 20px;
}
.item_card:hover {
  -webkit-transform: scale(1.05);
  transform: scale(1.05);
}
.item_card {
    position: relative;
    width: 330px;
    vertical-align: top;
    margin: 40px;
    background-color: #ffffff;
    display: inline-block;
    text-align: left;
    box-shadow: 0px 0px 30px #eee;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    -webkit-transition: all 0.45s ease-in-out;
    transition: all 0.45s ease-in-out;

}
a {
    color: #3c3c3c;
    text-decoration: none;
    font-weight: 900;
}

.recruit_card .card_image > a {
    width: 100%;
    height: 100px;
    display: inline-block;
    background-position: center center;
    transition-duration: 0.2s;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
}
.recruit_card .card_image .card_hover {
    opacity: 0;
    transition-duration: 0.2s;
    background-color: #ffd112;
    width: 100%;
    height: 120px;
    position: absolute;
    top: 0px;
    text-align: center;
    cursor: pointer;
}
.recruit_card .card_contents .sub_title {
    color: #979797;
    font-size: 14px;
}
.item_card .bottom_section {
    position: absolute;
    width: 100%;
    height: 20px;
    left: 0px;
    bottom: 23px;
}
.item_card .bottom_section .writing_time {
    position: absolute;
    top: 3px;
    left: 30px;
}
.item_card .bottom_section .ic_bookmark {
    position: absolute;
    right: 28px;
    top: 6px;
    cursor: pointer;
}
a {
    color: #3c3c3c!important;
    text-decoration: none!important;
}
.recruit_card .card_contents .recruit_desc {
    cursor: pointer;
    font-size: 15px;
    line-height: 25px;
    margin-top: 5px;
    color: #707070;
    word-break: keep-all;
}
.recruit_card .card_contents .title {
    font-size: 12px;
    font-weight: 700;
    cursor: pointer;
    overflow: hidden;
    margin-bottom: 20px;
    text-overflow: ellipsis;
}
.recruit_card .card_contents {
    padding: 40px 15px;
}
.v-chip__content{
    padding: 10px;
}
</style>