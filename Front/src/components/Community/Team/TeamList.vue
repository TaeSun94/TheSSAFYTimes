<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="col-lg-9  elevation-5">
                <v-card-title>
                    <div class="textfield">
                        <h1 class="m-4 main-title"> 👭 팀 구해요 ! </h1>
                        <small class="ml-3">팀원을 모집해 보세요. </small>
                    </div>
                    <v-spacer></v-spacer>
                </v-card-title>
                <div class="text-right mr-5">
                    <!-- <v-btn v-if="login===true" dark large tile :to="{path:'/community/teamWrite'}">팀원 구하기</v-btn> -->
                    <v-btn dark large tile v-if="login===true" :to="{path:'/community/teamWrite'}">팀원 구하기</v-btn>
                    <v-btn v-if="login===false" disabled large tile depressed :to="{path:'/community/teamWrite'}">팀원 구하기</v-btn>
                </div>
             <div class="text-center">
                <div class="item_card recruit_card col-lg-4" v-for="team in this.teams " :key="team.index" @click="rowClicked(team)">
                    <div class="card_image">
                        <a data-v-74c97ae7=""  class="team_title" style="text-align:center;display: flex;justify-content: center;align-items: center;">
                            {{ team.teamBoardTitle }}
                        </a>
                    
                    </div>
                    <hr style="width:80%;" class="line">
                    <div class="card_contents mr-1">
                        <div class="text-right mb-4">
                            <v-chip label style="font-size:0.95rem;"> {{ team.teamBoardCategory }} </v-chip>
                        </div> 

                        <div class=" mb-3 text-right">
                            <span class=""> 🙋 {{ team.memberId}} </span> 

                        </div> 
                        <div class=" mb-3 text-right">
                            <span>📅 {{ $moment(team.teamBoardEndDatetime).format('YYYY-MM-DD') }} </span>
                        </div> 
                    </div>
                    <div class="stack">
                        <div>🟡 Front-end <b>{{ team.teamBoardFrontRemainCount }}</b> 명 / 🟣 Back-end <b>{{ team.teamBoardBackRemainCount}}</b> 명</div>
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
            login: false,
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
        var id = this.$cookies.get('memberId');
        if(id==null){
            this.login = false;
            this.memberId = '';
        } else  {
            this.login = true;
            this.memberId = id;
        }       
    }
}
</script>

<style scoped>

.line {
    margin-left: auto;
    margin-right: auto;
    border: 2px solid;
    color: #e0e0e0;
}
.main-title{
    font-size: 2rem;
}
.stack{
    margin-bottom: 30px;
    text-align: center;
}
.team_title{
    font-weight: 900px;
    font-size: 1.3rem;
    padding: 20px;
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
    
    border:0.3px solid grey;
}
.bottom_section{
    margin-top:20px;
}
.container{
  background: #fff;
  padding: 20px;
}

.item_card:hover {
  -webkit-transform: scale(1.05);
  transform: scale(1.05);
}
.item_card {
    position: relative;
    width: 320px;
    vertical-align: top;
    margin: 5px;
    margin-top:40px;
    background-color: #ffffff;
    display: inline-block;
    text-align: left;
    box-shadow: 0px 0px 30px #eee;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    -webkit-transition: all 0.45s ease-in-out;
    transition: all 0.45s ease-in-out;
    cursor: pointer;

}

@media screen and (max-width: 768px) { 
    .item_card { 
        margin-top:20px;
        margin-left:0px;
        margin-right:0px;
        width: 500px;
    } 
    
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
.text-center a {
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
.v-btn__content{
    color: white!important;
}
.recruit_card .card_contents {
    padding: 20px 30px;
}
.v-chip__content{
    padding: 10px;
}
</style>