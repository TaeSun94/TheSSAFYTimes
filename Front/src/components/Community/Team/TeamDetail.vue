<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-3 col-lg-7">
            <div id="app">
                <div class="text-center main-title">
                    <h1 class="mb-2">{{team.teamBoardTitle}}</h1>
                </div>
                <div class="text-right mr-5">
                    <small @click="to(team.memberId)" class="description" id="follow"> SSAFY 3기 / {{team.memberId}} </small>
                    <small class="description"> </small>
                </div>

                <div>
                    <!-- <v-btn depressed tile dark v-show="canEdit === true" @click="deleteHandler" class="mr-5" style="float: right;">삭제하기!</v-btn>    -->
                </div>
                <div class="tei">
                    <div class="title">
                        <p> 모집분야 / {{team.teamBoardCategory}}</p>
                    </div>
                    <div class="date text-right mr-4">
                        <p> 마감일 📅 {{$moment(team.teamBoardDatetime).format('YYYY-MM-DD')}}</p>
                         
                    </div>                    
                    <hr style="width:95%" >
                    <div v-html="team.teamBoardContent" class="content_inner">
                    </div>
                    <hr style="width:95%" class="">
                    <div class="inner row">
                        <div class="col-6 member">
                            <p>구해요! ({{team.teamBoardBackRemainCount+team.teamBoardFrontRemainCount}}명/{{team.teamBoardTotalCount}}명) </p>
                            <div class="member-content">
                                <div class="mr-5">
                                    <p>🟡FRONT🟡</p>
                                    <small class="img-text">{{team.teamBoardFrontRemainCount}}</small>
                                </div>
                                <div class="">
                                    <p>🟣BACK🟣</p>
                                    <small class="img-text">{{team.teamBoardBackRemainCount}}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr style="width:95%" class="">
                    <div class="inner">                    
                        <div style="text-align:center;">
                            <p>팀매칭 진행율(%) </p>
                            <progress :value="value" max='100'></progress>
                        </div>    
                    </div>          
                    <div class="text-right mr-4"><v-btn tile  v-if="(team.memberId != this.$cookies.get('memberId')) && (this.$cookies.get('memberId') != null) && (team.teamBoardFrontRemainCount!=0 || team.teamBoardBackRemainCount !=0)">관심등록</v-btn></div>
                    <div class="text-right mr-4"><v-btn tile disabled v-if="(team.memberId == this.$cookies.get('memberId')) || (this.$cookies.get('memberId') == null) || (team.teamBoardFrontRemainCount==0 && team.teamBoardBackRemainCount ==0)">관심등록</v-btn></div>
                    <div class="likeContent mt-5 row justify-content-end">
                        <v-container v-if="(team.memberId != this.$cookies.get('memberId'))">
                            <v-textarea
                                solo
                                clearable
                                auto-grow
                                label="작성자에게 전할 말을 써주세요"
                                prepend-icon="mdi-comment"
                                style="margin:3%"
                                v-model="teamApplyContent"
                            ></v-textarea>
                        </v-container>
                        <div class="text-right mr-4"><v-btn  dark large tile  v-if="(team.memberId != this.$cookies.get('memberId')) && (this.$cookies.get('memberId') != null)  && (team.teamBoardFrontRemainCount!=0 || team.teamBoardBackRemainCount !=0)" @click="apply('F')">Front할래요!</v-btn></div>    
                        <div class="text-right mr-4"><v-btn  dark large tile  v-if="(team.memberId != this.$cookies.get('memberId')) && (this.$cookies.get('memberId') != null) && (team.teamBoardFrontRemainCount!=0 || team.teamBoardBackRemainCount !=0)" @click="apply('B')">Back할래요!</v-btn></div>          
                        <div class="text-right mr-4"><v-btn  dark large tile disabled  v-if="(team.memberId == this.$cookies.get('memberId')) || (this.$cookies.get('memberId') == null) || (team.teamBoardFrontRemainCount==0 && team.teamBoardBackRemainCount ==0)">Front할래요!</v-btn></div>  
                        <div class="text-right mr-4"><v-btn  dark large tile disabled  v-if="(team.memberId == this.$cookies.get('memberId')) || (this.$cookies.get('memberId') == null) || (team.teamBoardFrontRemainCount==0 && team.teamBoardBackRemainCount ==0)">Back할래요!</v-btn></div>          
                    </div>

                    <div v-if="team.memberId===this.$cookies.get('memberId')">
                        <div class="comment-content" v-for="item in applys" :key="item.teamApplyNo">
                            <v-simple-table>
                                <template v-slot:default>
                                <tbody>
                                    <tr>
                                        <p class="faq-content">{{ item.teamApplyContent }}<br></p>
                                        <p class="faq-txt text-right" v-if="item.teamApplyPosition=='F'" id="follow" @click="to(item.memberId)">🟡FRONT🟡 🧑 {{ item.memberId }}님</p>
                                        <p class="faq-txt text-right" v-if="item.teamApplyPosition=='B'" id="follow" @click="to(item.memberId)">🟣BACK🟣 🧑 {{ item.memberId }}님</p>
                                        <v-dialog v-model="dialog" persistent max-width="290">
                                            <template v-slot:activator="{ on, attrs }">
                                                <v-btn style="float:right;" tile color="dark" dark  v-if="item.teamApplyPosition=='F' && item.teamApplyStatus==0" v-bind="attrs" v-on="on">FRONT 영입하기 !</v-btn>
                                                <v-btn style="float:right;" tile disabled color="dark" v-if="item.teamApplyPosition=='F' && item.teamApplyStatus==1">FRONT 영입하기 !</v-btn>
                                                <v-btn style="float:right;" tile color="dark" dark  v-if="item.teamApplyPosition=='B' && item.teamApplyStatus==0" v-bind="attrs" v-on="on">BACK 영입하기 !</v-btn>
                                                <v-btn style="float:right;" tile disabled color="dark" v-if="item.teamApplyPosition=='B' && item.teamApplyStatus==1">BACK 영입하기 !</v-btn>
                                            </template>
                                            <v-card>
                                                <v-card-title class="headline">Use Google's location service?</v-card-title>
                                                <v-card-text>정말로 영입하시겠습니까?</v-card-text>
                                                <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn color="info" tile @click="dialog = false">닫기!</v-btn>
                                                <v-btn color="success" tile @click="recruit(item.teamApplyNo)" >영입!</v-btn>
                                                </v-card-actions>
                                            </v-card>
                                        </v-dialog>
                                    </tr>
                                </tbody>
                                </template>
                            </v-simple-table>
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
import { mapGetters } from 'vuex'
import http from "@/http-common.js"

export default {
    name:"TeamDetail",
    data() {
        return {
            dialog: false,
            apply_dialog: false,
            teamApplyContent: '',
            canEdit: false,
        }
    },
    computed: {
        ...mapGetters(["team"]),
        ...mapGetters(["applys"]),
        value() {
            return ((this.team.teamBoardTotalCount-(this.team.teamBoardBackRemainCount + this.team.teamBoardFrontRemainCount))/this.team.teamBoardTotalCount)*100;
        }
    },
    created() {
        this.$store.dispatch("getTeam", `/team/board/${this.$route.params.no}`);
        this.$store.dispatch("getApplys", `/team/board/apply/${this.$route.params.no}/list`);
        if(this.$cookies.get('memberId'== this.team.memberId)){
            this.canEdit = true
        }
    },
    methods: {
        recruit(no) {
            this.dialog = false
            http.put("/team/board/apply", {teamApplyNo:no}).then(({data})=> {
                if(data.result != "success") {
                    this.$alert(data.message);
                } else {
                    location.reload();
                }
            })
        },
        apply(position) {
            http.post("/team/board/apply", {
                memberId:this.$cookies.get('memberId'),
                teamApplyContent: this.teamApplyContent,
                teamApplyPosition: position,
                teamBoardNo: this.$route.params.no,
            }).then(({data})=> {
                if(data.result == "success") {
                    this.$alert(data.message);
                    location.reload();
                } else {
                    this.$alert(data.message);
                    return;
                }
            })
        },
        to(id){
            var url = "/profile/"+id;
            this.$router.push({path: url});
        },
    }

}
</script>

<style scoped>
.member-content, .stack-content{
    display: flex;
    justify-content: center;
}
.tei{
    border: 1px solid ;
    
    padding: 20px;
    margin-top:30px
}
.member{
    /* border-right: 0.3px solid #333; */
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
hr{
    width: 30%;
    margin-bottom: 20px;
    margin-left: 20px;  
}
.comment-content{
    margin: 5px;
    margin-left:20px;
    margin-right:20px;
    border-radius: 10px;
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
#follow:hover {
   color: orange;
}
</style>