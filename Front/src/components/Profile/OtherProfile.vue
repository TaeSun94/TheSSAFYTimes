<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container  class="col-lg-10 mb-5">
            <h1>{{ member.memberIntro }}</h1>
            <p>{{ member.memberDesc }}</p>
        </v-container>
<!--         
        <bussiness-card v-model="member"></bussiness-card> -->
    </div>
    <div class="row">
        <!-- 기사가 들어갈 공간 -->
        <v-container class="col-lg-6">
            <div>
                <div v-if="articles.length">
                    <div v-for="(article, index) in articles" :key="index +'_articles'">
                <!-- 작성된 기사 부분 -->
                        <v-container class="elevation-5">
                            <card class="desc">
                                <div slot="header" class="bg-white border-0">
                                    <div class="row align-items-center">
                                        <div class="col-8 ml-3">
                                            <h3>제목 : {{article.articleTitle}}</h3>
                                        </div>
                                        <div class="col-3" style="padding-top:20px">
                                            <h5>분야 : {{article.articleType}}</h5>
                                        </div>
                                    </div>
                                </div>
                                <hr class="my-4" />
                                <div>
                                    <p>{{article.articleContent}}</p>
                                </div>
                                <div class="text-right">
                                    <p style="font-size:12px">{{article.articleWriter}}, {{article.articleDate}}</p>
                                </div>
                            </card>
                        </v-container>
                        <br>
                    </div>
                    <!-- infinite loading -->
                    <!-- <infinite-loading ></infinite-loading> -->
                </div>
                <div v-else class="text-center">
                    <h3>등록된 기사가 없습니다.</h3>
                </div>
            </div>
        </v-container>
        <!-- 프로필 및 친구 관계가 들어갈 공간 -->
        <div class="col-lg-4 mr-15">
        <!-- <v-container class="elevation-5 col-lg-12"> -->
            <profile-card></profile-card>
        <!-- </v-container> -->
        <br>
        <v-container class="elevation-5 col-lg-11">
            <div class="col mb-4">
                <div class="col-lg-4" style="float:left;">
                    <h4> Followers</h4>
                </div>
                <div class="" style="position:relative; float:right;">
                    <add-following v-model="followings"></add-following>
                </div>
                <hr class="my-4 mt-10" />
            </div>
            
            <div v-if="followings.length" id="following_table">
                <!-- 친구 목록 table -->
                <!-- Id, region, unit만 보이게 -->
                <table class="text-center">
                <tr v-for="(following,index) in followings" :key="index+'_followings'">
                    <td>{{following.memberId}}</td>
                    <td>{{following.memberRegion}}</td>
                    <td>{{following.memberUnit}}</td>
                </tr>
                </table>
            </div>
            <div v-else class="text-center">
                <p>등록된 Followr가 없습니다.</p>
            </div>
        </v-container>
        </div>
        </div>
    <footer-bar></footer-bar>
</div>
</template>
<script>
    // import BussinessCard from './BusinessCard'
    // import InfiniteLoading from 'vue-infinite-loading'
    import AddFollowing from "./AddFollowing"
    import ProfileCard from "./ProfileCard"
    // import WriteArticle from "./Profile/WriteArticle"
    import http from "@/http-common";
    import {mapGetters} from 'vuex';
    export default {
        name: 'user-profile',
        components:{
            AddFollowing,
            ProfileCard,
            // InfiniteLoading,
            // BussinessCard
        },

        data() {
            return {
                selected: null,
                options: [
                    { value: '사회', text: '사회' },
                    { value: '과학', text: '과학' },
                    { value: '기술', text: '기술'},
                    { value: '기타', text: '기타'}
                ],
                followings:[
                    {
                        memberId:'ki0',
                        memberRegion:'광주',
                        memberUnit:'3',
                    },
                    {
                        memberId:'성우',
                        memberRegion:'서울',
                        memberUnit:'3',
                    },
                ],
                articles:[
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                    {
                        articleTitle: '앙기모띠',
                        articleType: '사회',
                        articleWriter: '유태선',
                        articleDate:'2020-07-27',
                        articleContent:"우오오오오오오오오온 이건 바로 나의 글, 내가 쓴 기사는 다음번 잡지에 올라갈 예정이랑께랑! 조금만 더 써보면 이 글이 밖으로 나가는지 밑으로 가는지 알 수 있다구!"
                    },
                ],
                followings_count:'23',
                followers_count:'1234',
                articles_count:'44',
                show: false,
                article_show : false,
            }
        },
        created(){
            this.$store.dispatch('getArticles');
            this.$store.dispatch('getProfile');
            http.get(`/`).then(({data})=>{
                this.options = data;
            });
            http.get(`/`).then(({data})=>{
                this.followings = data;
            });
            http.get(`/`).then(({data})=>{
                this.followers = data;
            });
            http.get(`/`).then(({data})=>{
                this.articles = data;
            });
            http.get(`/`).then(({data})=>{
                this.followings_count = data;
            });
            http.get(`/`).then(({data})=>{
                this.followers_count = data;
            });
            http.get(`/`).then(({data})=>{
                this.articles_count = data;
            });
            // http.get(`/`).then(({data})=>{
            //     this.member = data;
            // });
        },
        computed:{
            ...mapGetters(['articles','profile'])
        },
        methods:{
            registerArticle(){

            },
        }
    };
</script>
<style>
  .checkbox {
    display: none; 
  }
  .title {
    color: purple;
    font-weight: bold;
  }
  .desc {
    max-height: 0px;
    overflow: hidden;
  }
  .checkbox:checked + .title + .desc {
    max-height: 1000px;
  }
  #se{
      padding-top: 3px;
  }
  #reg-button{
      float: right;
  }
</style>