<template>
        <v-container class="elevation-5 col-lg-12">
            <v-card-actions>
                <div class="col-lg-3">
                    <connect-here v-model="member"></connect-here>
                </div>
                <div v-if="member.memberImgurl !== null && member.memberImgurl !== ''" class="col-lg-6 text-center" style="position:relative;">
                    <v-avatar :size="150">
                        <img
                            :src="getImg(member.memberImgurl)"
                            alt="John"
                        >  
                    </v-avatar>
                </div>
                <div v-else class="col-lg-6 text-center" style="position:relative;">
                    <v-avatar :size="150">
                        <img
                            src="@/assets/마스크.jpg"
                            alt="비어있음"
                        >  
                    </v-avatar>
                </div>
                <div class="col-lg-3 text-center">
                    <!-- 만약 세션이 나이고 쿼리를 통해 들어온거와 같다면 -->
                    <div v-if="isMember">
                        <div v-if="member.memberFirstName === null || member.memberFirstName === ''">
                            <v-btn tile large depressed dark @click="mvEditProfile" >등록</v-btn>
                        </div>
                        <div v-else>
                            <v-btn tile large depressed dark @click="mvEditProfile" >수정</v-btn>
                        </div>
                    </div>

                    <!-- 아니라면 -->
                    <div v-else>
                        <!-- 만약 친구라면 언팔 친구가 아니면 팔로우 -->
                        <div v-if="followingPeople.includes(member.memberId) !== false">
                            <div>
                                <v-btn tile large depressed dark @click="delFollow" >언팔로우</v-btn>
                            </div>
                        </div>
                        <div v-else>
                            <div>
                                <v-btn tile large depressed dark @click="addFollow" >팔로우</v-btn>
                            </div>
                        </div>
                    </div>
                </div>
            </v-card-actions>
            <div v-if="member.memberFirstName !== null && member.memberFirstName !==''">
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <div class="card-profile-stats d-flex justify-content-center ml-5" style="font-size:13px">
                                <div>
                                    <span class="heading">{{ member.memberArticle }}</span>
                                    <span class="description">내가 쓴 기사 수(Articles)</span>
                                </div>
                                <div>
                                    <span class="heading">{{ member.memberFollower}}</span>
                                    <span class="description">팔로워 수(Followers)</span>
                                </div>
                                <div>
                                    <span class="heading">{{ member.memberFollow }}</span>
                                    <span class="description">팔로잉 수(Followings)</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div >
                        <div class="text-center">
                        <h3 class="mb-4">
                            {{ member.memberFirstName }}, {{ member.memberLastName }}<!-- <span class="font-weight-light">, 나이</span> -->
                        </h3>
                        <div class="h5 font-weight-300">
                            <i class="ni location_pin mr-2"></i>현재 거주지 : {{ member.memberAddress }}
                        </div>
                        <div class="h5 mt-4">
                            <i class="ni business_briefcase-24 mr-2"></i>지역 : <b>{{ member.memberRegion }}</b> / 기수 : <b>{{ member.memberUnit }}</b> / 트랙 : <b>{{ member.memberTrack }}</b>
                        </div>
                        
                        <hr class="my-4" />
                        <v-btn icon @click="show = !show">
                                <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                        </v-btn>
                        </div>
                        <v-slide-y-transition>
                            <v-card-text v-show="show">
                                <p style="font-size:23px"><b>관심 기술 및 언어</b></p>
                                    <div v-if="member.memberInterestedList !== null || member.memberInterestedList === ''">
                                        <tr>
                                            <td v-for="(item, index) in member.memberInterestedList" :key="index +'_memberInterested'">
                                                <i v-if="index !== member.memberInterestedList.length-1">{{item}},&nbsp;&nbsp; </i>
                                                <i v-else>{{item}}</i>
                                            </td>
                                        </tr>
                                    </div>
                                    <div v-else>
                                        <p>등록된 관심 기술 및 언어가 없습니다.</p>
                                    </div>
                                <br>
                                <p style="font-size:23px"><b>사용 가능 기술</b></p>
                                    <div  v-if="member.memberSkillList !== null || member.memberSkillList === ''">
                                        <tr>
                                            <td v-for="(item, index) in member.memberSkillList" :key="index +'_memberSkill'">
                                                <i v-if="index !== member.memberSkillList.length-1">{{item}},&nbsp;&nbsp; </i>
                                                <i v-else>{{item}}</i>
                                            </td>
                                        </tr>
                                    </div>
                                    <div v-else>
                                        <p>등록된 사용 기술 및 언어가 없습니다.</p>
                                    </div>
                                    <br>
                            </v-card-text>
                        </v-slide-y-transition>                                    
                    </div>
                </div>
            </div>
            <div v-else>
                <div style="opacity: 0.3">
                    <div class="row">
                        <div class="col">
                            <div class="card-profile-stats d-flex justify-content-center ml-md-5" style="font-size:13px">
                                <div>
                                    <span class="heading"> 11 </span>
                                    <span class="description">내가 쓴 기사 수(Articles)</span>
                                </div>
                                <div>
                                    <span class="heading"> 22 </span>
                                    <span class="description">팔로워 수(Followers)</span>
                                </div>
                                <div>
                                    <span class="heading"> 33 </span>
                                    <span class="description">팔로잉 수(Followings)</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <h3>
                            이름 성
                        </h3>
                        <div class="h5 font-weight-300">
                            <i class="ni location_pin mr-2"></i>현재 거주 지역
                        </div>
                        <div class="h5 mt-4">
                            <i class="ni business_briefcase-24 mr-2"></i>기수 - 트랙
                        </div>
                        <div>
                            <i class="ni education_hat mr-2"></i>지역 - 반번호
                        </div>
                        <hr class="my-4" />
                        <p>자신이 관심있어하는 기술과 잘하는 기술을 나열</p>
                    </div>
                </div>
            </div>
        </v-container>
</template>

<script>
    import ConnectHere from "./ConnectHere";
    import {mapGetters,mapActions} from 'vuex';
    import http from "@/http-common.js";
    export default {
        name:"ProfileCard",
        data() {
            return {
                selected: null,
                show:false,
                isMember: false,
                followingPeople:[],
                isFollowing:false,
                xx: '',
            }
        },
        created(){
            var id = this.$cookies.get("memberId");
            if(id==this.$route.params.memberid){
                this.isMember = true;
            }
            http.get(`/follow/${id}/ing`).then(({data})=>{
                for(var i = 0; i < data.list.length; i++){
                    this.followingPeople.push(data.list[i]["memberId"]);
                }
                //console.log(this.followingPeople);
            });
            // this.xx = require("/home/ubuntu/ssafytimes/s03p13c208/Ssafience/src/main/resources/static/images/"+id+".jpg");
        },
        components:{
            'connect-here':ConnectHere,
        },
        computed:{
            ...mapGetters({member: 'profile'}),
        },
        methods:{
            mvEditProfile(){
                this.$router.push(`/profileEdit/${this.$store.state.profile.memberId}`)
            },
            ...mapActions(['addFollowing','delFollowing']),
            addFollow(){
                const id = this.$cookies.get("memberId");
                //console.log(this.$store.state.profile.memberId);
                this.$store.dispatch('addFollowing',id);
                // ...mapActions(['addFollowing'],this.$store.state.profile.memberid)
            },
            delFollow(){
                //console.log(this.$store.state.profile.memberId);
                const id = this.$cookies.get("memberId");
                this.$store.dispatch('delFollowing',id);
            },
            getImg(id){
                console.log(id);
                return id;
                // http://adasdsa/images/a.jpg
                // return require("/home/ubuntu/ssafytimes/s03p13c208/Ssafience/src/main/resources/static/images/"+id+".jpg");
            }
        }
    }
</script>

<style scoped>
.container{
    border-radius: 0px;
}

.heading{
    color: darkorange;
}

</style>