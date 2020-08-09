<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <div class="col-lg-11" v-if="profile.memberIntro !== null && profile.memberDesc !==null">
        <v-container id="intro">
            <h1>{{ profile.memberIntro }}</h1>
            <p>{{ profile.memberDesc }}</p>
        </v-container>
        </div>
        <!-- <bussiness-card v-model="member"></bussiness-card> -->
    </div>
    <div class="row">
        <!-- 기사가 들어갈 공간 -->
        <v-container class="col-lg-6">
            <div v-if="isMember">
                <v-card-actions>
                    나만의 기사 작성하기!
                    <v-btn
                        icon
                        @click="show = !show"
                    >
                        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                    </v-btn>
                </v-card-actions>
                <v-expand-transition>
                    <div v-show="show">
                        <v-divider></v-divider>
                        <v-container class="elevation-5">
                            <v-form>
                                <div class="item_card">
                                    <div slot="header" class="bg-white border-0">
                                        <div class="row align-items-center">
                                            <div class="col-10">
                                                <h3 class="ml-5 mt-1">기사 작성</h3>
                                            </div>
                                            <div class="col-2 pl-10" id="reg-button">
                                                <v-btn fab dark small color="primary" @click="writeArticle">
                                                    <v-icon dark primary style="font-size:20px;">+</v-icon>
                                                </v-btn>
                                            </div>
                                        </div>
                                    </div>
                                    <hr class="my-4" />
                                    <template>
                                            <!-- <h6 class="heading-small text-muted mb-4">기사 작성</h6> -->
                                            <div class="pl-lg-4">
                                                <div class="row">
                                                    <div class="col-md-8">
                                                        <v-text-field
                                                            v-model="article.articleTitle"
                                                            label="기사 제목"
                                                            :rules="subject_rules"
                                                        ></v-text-field>
                                                    </div>
                                                    <div class="col-md-4" id="se">
                                                        <v-select
                                                            v-model="article.articleType"
                                                            :items="articleTypes"
                                                            label="기사 분류"
                                                            chips
                                                            persistent-hint
                                                            :rules="type_rules"
                                                        ></v-select>
                                                        <!-- <label class="form-control-label">기사 분류</label>
                                                        <v-select bv-model="selected" :options="options" attach chips multiple></v-select> -->
                                                    </div>
                                                </div> 
                                            </div>
                                            <div class="pl-lg-4">
                                                <v-textarea
                                                    autocomplete="article_contents"
                                                    label="기사 내용"
                                                    :rules="content_rules"
                                                    v-model="article.articleContent"
                                                ></v-textarea>
                                            </div>
                                    </template>
                                </div>
                            </v-form>
                        </v-container>
                    </div>
                </v-expand-transition>
            </div>
            <br>
            <div>
                <div v-if="articles.length">
                    <div class="item_card" v-for="(item, index) in articles" :key="index + '_articles'">
                <!-- 작성된 기사 부분 -->
                        <v-container class="elevation-5">
                                <div slot="header" class="bg-white border-0">
                                    <div class="row align-items-center">
                                        <div class="col-8 ml-3">
                                            <h3>제목 : {{item.articleTitle}}</h3>
                                        </div>
                                        <div class="col-3" style="padding-top:20px">
                                            <h5>분야 : {{item.articleType}}</h5>
                                        </div>
                                    </div>
                                </div>
                                <hr class="my-4" />
                                <div>
                                    <p>{{item.articleContent}}</p>
                                </div>
                                <div class="text-right">
                                    <p style="font-size:12px">{{item.memberId}}, {{item.articleDatetime}}</p>
                                </div>
                        </v-container>
                        <br>
                    </div>
                    <!-- infinite loading -->
                    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
                </div>
                <div v-else class="text-center">
                    <h3>등록된 기사가 없습니다.</h3>
                </div>
            </div>
        </v-container>
        <!-- 프로필 및 친구 관계가 들어갈 공간 -->
        <div class="col-lg-4 mr-15">
            <profile-card v-model="profile.memberId"></profile-card>
        <br>
        <v-container class="elevation-5 col-lg-12">
            <div class="col">
                <div class="col-lg-4" style="float:left;">
                    <h4> Followings</h4>
                </div>
                <hr class="my-4 mt-10" />
            </div>
            <div v-if="followings.length" id="following_table">
                <!-- 친구 목록 table -->
                <!-- Id, region, unit만 보이게 -->
                <v-data-table
                    :headers="headers"
                    :items="followings"
                    :page.sync="page"
                    :items-per-page="perPage"
                    hide-default-footer
                    :per-page="perPage"
                    @click:row="rowClicked"
                >
                </v-data-table>
                <!-- <table class="text-center">
                <tr v-for="(following,index) in followings" :key="index+'_followings'">
                    <business-card v-bind="following"></business-card>
                    <td>{{following.memberId}}</td>
                    <td>{{following.memberEmail}}</td>
                    <td>{{following.memberUnit}}</td>
                </tr>
                </table> -->
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
    // import BusinessCard from './BusinessCard'
    import InfiniteLoading from 'vue-infinite-loading'
    import ProfileCard from "@/components/Profile/ProfileCard"
    // import WriteArticle from "./Profile/WriteArticle"
    import {mapState, mapGetters, mapActions} from 'vuex';
    import http from "@/http-common.js";
    export default {
        name: 'UserProfile',
        components:{
            'profile-card':ProfileCard,
            InfiniteLoading,
            // BusinessCard
        },
        data() {
            return {
                page: 1,
                perPage:5,
                articlepage:1,
                list:[],
                selected: null,
                show: false,
                article_show : false,
                subject_rules:[
                    value => !!value || '제목을 입력해 주세요.'
                ],
                type_rules:[
                    value => !!value || '분류를 선택해 주세요.'
                ],
                content_rules:[
                    value => !!value || '내용을 입력해 주세요.'
                ],
                isMember: false,
                headers: [
                { text: '아이디', value: 'memberId', },
                { text: '이메일', value: 'memberEmail' },
            ],
            }
        },
        created(){
            // sessionStorage.setItem('memberId', "admin");
            console.log(this.$route.params.memberid);
            this.$store.dispatch('getProfile',this.$route.params.memberid);
            this.$store.dispatch('getMyArticles',this.$route.params.memberid);
            // this.$store.dispatch('getFollowings',"admin");
            this.$store.dispatch('getArticleTypes');
            this.$store.dispatch('getFollowings',this.$route.params.memberid);
            var id = this.$cookies.get("memberId");
            if(id==this.$route.params.memberid){
                this.isMember = true;
            }
        },
        computed:{
            ...mapState({article: state=> state.article},{member: state=>state.profile}),
            
            ...mapGetters(['profile','articles','article','articleTypes','followings'])
        },
        methods:{
            ...mapActions(['writeArticle']),
            rowClicked(row){
                location.href =`/profile/${row.memberId}`;
            },
            // infiniteHandler($state){
            //     http.get(`/article/${this.$route.params.memberId}`,{
            //         params:{
            //             page: this.articlepage,
            //         },
            //     }).then(({data})=>{
            //     context.commit('setMyArticles',data);
            //     console.log(data);
            // });
            // },
            infiniteHandler($state) {
                http.get(`/article/${this.$route.params.memberId}`
                // , {
                //     params: {
                //         page: this.articlepage,
                //     },
                // }
                ).then(({ data }) => {
                    if (data.list.length) {
                        // this.page += 1;
                        this.$store.state.articles.push(data.list);
                        $state.loaded();
                    } else {
                        $state.complete();
                    }
                });
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
