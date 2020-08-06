<template>
<div class="wrapper">
    <div class="row">
        <v-container class="elevation-5 col-lg-12">
            <v-card-actions class="ml-3">
                <div class="col-lg-3">
                    <connect-here v-model="member"></connect-here>
                </div>
                <div class="col-lg-6 text-center" style="position:relative;">
                    <v-avatar :size="150">
                        <img
                            src="@/assets/logo.png"
                            alt="John"
                        >  
                    </v-avatar>
                </div>
                <div class="col-lg-3">
                    <!-- 만약 세션이 나이고 쿼리를 통해 들어온거와 같다면 -->
                    <!-- <div v-if="member.memberId === sessionStorage.getItem('memberId')"> -->
                        <div v-if="member.memberFirstName === null || member.memberFirstName === ''">
                            <v-btn class="primary" @click="mvEditProfile">등록</v-btn>
                        </div>
                        <div v-else>
                            <v-btn class="primary" @click="mvEditProfile">수정</v-btn>
                        </div>
                    <!-- </div> -->

                    <!-- 아니라면 -->
                    <!-- <div v-else> -->
                        <!-- 만약 친구라면 언팔 친구가 아니면 팔로우 -->
                        <div>
                            <v-btn class="primary" @click="addFollow">팔로우</v-btn>
                        </div>
                    <!-- </div> -->
                </div>
            </v-card-actions>
            <div v-if="member.memberFirstName !== null && member.memberFirstName !==''">
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <div class="card-profile-stats d-flex justify-content-center ml-5" style="font-size:13px">
                                <div>
                                    <!-- <span class="heading">{{ articles_count }}</span> -->
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
                            <i class="ni business_briefcase-24 mr-2"></i>지역 : {{ member.memberRegion }} - 기수 : {{ member.memberUnit }} - 트랙 : {{ member.memberTrack }}
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
                                    <v-span class="heading"> 11 </v-span>
                                    <v-span class="description">내가 쓴 기사 수(Articles)</v-span>
                                </div>
                                <div>
                                    <v-span class="heading"> 22 </v-span>
                                    <v-span class="description">팔로워 수(Followers)</v-span>
                                </div>
                                <div>
                                    <v-span class="heading"> 33 </v-span>
                                    <v-span class="description">팔로잉 수(Followings)</v-span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <h3>
                            이름 성<v-span class="font-weight-light">, 나이</v-span>
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
    </div>
</div>
</template>

<script>
    import ConnectHere from "./ConnectHere";
    import {mapGetters} from 'vuex';
    export default {
        name:"ProfileCard",
        data() {
            return {
                selected: null,
                show:false
            }
        },
        components:{
            ConnectHere,
        },
        computed:{
            ...mapGetters({member: 'profile'}),
        },
        methods:{
            mvEditProfile(){
                this.$router.push('/profileEdit',this.$store.state.profile.memberId)
            },
            addFollow(){
                
            }
        }
    }
</script>

<style>

</style>