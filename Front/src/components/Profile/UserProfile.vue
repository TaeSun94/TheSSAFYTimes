<template>
<div class="wrapper" style="margin-top:8%">
    <v-container class="col-lg-10">
        <div class="row">
            <div class="col-lg-12" v-if="profile.memberIntro !== null && profile.memberDesc !==null">
            <div class="ml-5">
                <h1 class="mb-3 profile_title">{{ profile.memberIntro }}</h1>
                <p>{{ profile.memberDesc }}</p>
            </div>
            </div>
            <!-- <bussiness-card v-model="member"></bussiness-card> -->
        </div>
    <div class="row">
        <!-- 기사가 들어갈 공간 -->
        <!-- <v-container class="col-lg-6"> -->
        <div class="col" >
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
                        <div class="elevation-5 mt-5">
                            <v-form>
                                <div class="item_card">
                                    <div slot="header" class="bg-white border-0">
                                        <div class="row align-items-center">
                                            <div class="col-10">
                                                <h3 class="ml-5 mt-1">기사 작성</h3>
                                            </div>
                                            <div >
                                                <v-btn tile large depressed dark @click="writeArticle" >기사쓰기!</v-btn>
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
                        </div>
                    </div>
                </v-expand-transition>
            </div>
            <br>
            <div>
                <div v-if="articles.length">
                    <div class="item_card elevation-2" v-for="(item, index) in articles" :key="index + '_articles'">
                <!-- 작성된 기사 부분 -->
                    <div slot="header" class="header bg-white border-0">
                        <div class="header_content">
                            <h3> {{item.articleTitle}}</h3>
                            <div class="text-right">
                                <v-chip color="#FFB459">
                                    {{item.articleType}}
                                </v-chip>
                            </div>
                        </div>
                        <br>
                    </div>
                        <hr/>
                        <div class="news_content">
                            <p>{{item.articleContent}}</p>
                        </div>
                        <div class="text-right news_content">
                            <p style="font-size:12px">{{item.memberId}}, {{$moment(item.articleDatetime).format('YYYY-MM-DD hh:mm:ss a')}}</p>
                        </div>
                    </div>
                    <!-- infinite loading -->
                    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
                </div>
                <div v-else class="text-center" style="padding-top:8%" >
                    <img src="@/assets/옴팡이.jpg"/>
                    <!-- <v-container class="elevation-5"> -->
                    <h1>등록된 기사가 없습니당...ㅠ</h1>
                    <!-- </v-container> -->
                </div>
            </div>
        </div>
        <!-- </v-container> -->
        <!-- 프로필 및 친구 관계가 들어갈 공간 -->
        <div class="col-lg-4 profile_container">
            <profile-card v-model="profile.memberId"></profile-card>
            <br>
            <div class="elevation-5 col-lg-12">
                    <v-card-title>
                        <div class="textfield">
                            <h3 class="m-4">Followings</h3>
                        </div>
                    </v-card-title>
                    <v-card>
                    <div v-if="followings.length" id="following_table">
                        <!-- 친구 목록 table -->
                        <!-- Id, region, unit만 보이게 -->
                        <v-data-table
                            :headers="followingHeaders"
                            :items="followings"
                            :page.sync="fpage"
                            :items-per-page="fperPage"
                            hide-default-footer
                            :per-page="fperPage"
                            @click:row="followingClicked"
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
                        <p>등록된 Follower가 없습니다.</p>
                    </div>
                    </v-card>
            </div>
            <br>
            <div class="elevation-5 col-lg-12">
                    <v-card-title>
                        <div class="textfield">
                            <h3 class="m-4"> 🟠 지원 확정 프로젝트 현황</h3>
                        </div>
                    </v-card-title>
                    <v-card>
                        <div v-if="my_confirm_projects.length">
                            <v-data-table
                            :headers="projectHeaders"
                            :items="my_confirm_projects"
                            :page.sync="ppage"
                            :items-per-page="pperPage"
                            hide-default-footer
                            :per-page="pperPage"
                            @click:row="projectClicked"
                            >
                            </v-data-table>
                        </div>
                        <div v-else class="text-center">
                            <p>확정된 프로젝트가 없습니다.</p>
                        </div>
                    </v-card>
                    <br>
                    <hr class="project_hr">
                    <v-card-title>
                        <div class="textfield">
                            <h3 class="m-4">🟢 지원 중인 프로젝트 현황</h3>
                        </div>
                    </v-card-title>
                    <v-card>
                        <div v-if="my_apply_projects.length">
                            <v-data-table
                                :headers="projectHeaders"
                                :items="my_apply_projects"
                                :page.sync="ppage"
                                :items-per-page="pperPage"
                                hide-default-footer
                                :per-page="pperPage"
                                @click:row="projectClicked"
                            >
                            </v-data-table>
                        </div>
                        <div v-else class="text-center">
                            <p>지원중인 프로젝트가 없습니다.</p>
                        </div>
                    </v-card>
            </div>
        </div>
    </div>
    </v-container>
    <footer-bar></footer-bar>
</div>
</template>
<script>
    import InfiniteLoading from 'vue-infinite-loading'
    import ProfileCard from "@/components/Profile/ProfileCard"
    import {mapState, mapGetters, mapActions} from 'vuex';
    import http from "@/http-common.js";
    export default {
        name: 'UserProfile',
        components:{
            'profile-card':ProfileCard,
            InfiniteLoading,
        },
        data() {
            return {
                page: 1,
                fpage: 1,
                fperPage:5,
                ppage:1,
                pperPage:5,
                articlepage:1,
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
                followingHeaders: [
                    { text: '아이디', value: 'memberId', },
                    { text: '이메일', value: 'memberEmail' },
                ],
                projectHeaders:[
                    {text:'프로젝트 번호', value:'boardNo'},
                    {text: '분류', value: 'boardType'},
                    {text: '프로젝트 이름', value: 'boardTitle'},
                ],
                profileArticle:[],
                memid:'',
            }
        },
        created(){
            this.memid = this.$route.params.memberid;
            this.$store.dispatch('getProfile',this.$route.params.memberid);
            this.$store.dispatch('getArticleTypes');
            this.$store.dispatch('getFollowings',this.$route.params.memberid);
            var id = this.$cookies.get("memberId");
            if(id==this.$route.params.memberid){
                this.isMember = true;
            }
            this.$store.dispatch('getMyApplyProject',this.$route.params.memberid);
            this.$store.dispatch('getMyConfirmProject',this.$route.params.memberid);
            http.get(`/article/${this.memid}/${this.articlepage}`).then(({ data }) => {
                this.articlepage += 1;
                this.$store.state.articles = data.list;
            });
        },
        computed:{
            ...mapState({article: state=> state.article},{member: state=>state.profile}),
            
            ...mapGetters(['profile','articles','article','articleTypes','followings','my_apply_projects','my_confirm_projects'])
        },
        methods:{
            ...mapActions(['writeArticle']),
            followingClicked(row){
                location.href =`/profile/${row.memberId}`;
            },
            projectClicked(row){
                location.href =`/community/teamdetail/${row.boardNo}`;
            },
            infiniteHandler($state) {
                http.get(`/article/${this.memid}/${this.articlepage}`).then(({ data }) => {
                    setTimeout(()=>{
                        if (data.list.length) {
                            this.articlepage += 1;
                            for(var i = 0; i < data.list.length; i++)
                                this.$store.state.articles.push(data.list[i]);
                            $state.loaded();
                        } else {
                            $state.complete();
                        }
                    },1000)
                });
            },
        }
    };
</script>
<style scoped>
    .container{
        padding: 10px;
    }
    .checkbox {
    display: none; 
    }

    .profile_title {
    color: darkorange;
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
    .item_card{
        padding: 20px;
    }
    .project_content{
        padding: 10px;
    }
    .v-application .mr-15 {
    margin-right: 0px !important; 
    }
    .news_content p {
        padding: 20px;
    }
    .header_content{
        display: flex;
        justify-content: space-between;
    }

    .project_hr {
        border: 0.5px dashed gray;
    }

    .header_content h3{
        font-size: 1.5rem;
        font-weight: 700;
    }
    .v-sheet.v-card:not(.v-sheet--outlined) {
    box-shadow: none
}
</style>
