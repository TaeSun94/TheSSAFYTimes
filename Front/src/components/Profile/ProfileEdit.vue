<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <!-- Edit Profile 들어갈 공간 -->
        <v-container class="elevation-5 col-lg-6">
            <v-card>
            <p id="edit_header">프로필 등록 및 수정</p>
            <v-form class="ml-4 mr-4 mt-6">
                <v-file-input @change="onChangeImages" show-size counter label="프로필 사진 등록"></v-file-input>
                <div v-if="preview"><img v-bind:src="preview"></div>

                <v-text-field
                    label="Blog 소개 제목"
                    v-model="member.memberIntro"
                ></v-text-field>

                <v-text-field
                    label="간단한 자기 설명"
                    v-model="member.memberDesc"
                ></v-text-field>
                <div class="row">
                    <v-text-field class="col-lg-2"
                    label="성"
                    v-model="member.memberLastName"
                    ></v-text-field>

                    <v-text-field class="col-lg-5"
                        label="이름"
                        v-model="member.memberFirstName"
                    ></v-text-field>
                </div>
                <v-text-field
                    label="현재 거주지역"
                    v-model="member.memberAddress"
                ></v-text-field>
                <v-text-field
                    label="핸드폰 번호(-)포함"
                    v-model="member.memberPhone"
                ></v-text-field>
                <!-- select로 설정 (Not multiple) -->
                <div class="row">
                    <v-select class="col-lg-2"
                        v-model="member.memberRegion"
                        :items="regions"
                        :rules="region_rules"
                        label="지역"
                        chips
                        persistent-hint
                        @change="getUnit(member.memberRegion)"
                    ></v-select>
                    <v-select class="col-lg-2"
                        v-model="member.memberUnit"
                        :items="units"
                        :rules="unit_rules"
                        label="기수"
                        chips
                        persistent-hint
                        @change="getTrack(member.memberUnit)"
                    ></v-select>
                    <v-select class="col-lg-2"
                        v-model="member.memberTrack"
                        :items="tracks"
                        :rules="track_rules"
                        label="트랙"
                        chips
                        persistent-hint
                    ></v-select>
                </div>
                <v-select
                    v-model="member.memberInterestedList"
                    :items="skillLanguages"
                    label="관심 기술 및 언어"
                    multiple
                    chips
                    persistent-hint
                ></v-select>
                <br>
                <v-select
                    v-model="member.memberSkillList"
                    :items="skillLanguages"
                    label="사용 기술 및 언어"
                    multiple
                    chips
                    persistent-hint
                ></v-select>
            </v-form>
            <div class="text-center" id="btn_profile">
                <v-btn @click="modify">프로필 등록 및 수정</v-btn>
            </div>
            </v-card>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>
<script>
    // import http from "@/http-common";
    import {mapGetters, mapState} from 'vuex';
    export default {
        name: 'ProfileEdit',
        data() {
            return {
                region:[],
                memberId:'',
                preview: '',
                img: [],
                region_rules:[
                    value => !!value || '지역을 선택해 주세요.'
                ],
                unit_rules:[
                    value => !!value || '기수를 선택해 주세요.'
                ],
                track_rules:[
                    value => !!value || '트랙을 선택해 주세요.'
                ],
            }
        },
        created(){
            this.$store.dispatch('getRegions');
            this.$store.dispatch('getSkillLanguages');
            var id = this.$cookies.get("memberId");
            this.memberId = id;
            this.$store.dispatch('getProfileMod',this.memberId);
        },
        computed:{
            ...mapGetters(['profile','regions','skillLanguages','units','tracks']),
            ...mapState({member: state=>state.profile}),
        },
        methods:{
            // ...mapActions(['modifyProfile']),
            modify(){
                this.$store.dispatch('modifyProfile',this.img);
            },
            getUnit(value){
                console.log(value);
                this.$store.dispatch('getUnits',value);
            },
            getTrack(value){
                console.log(value);
                this.$store.dispatch('getTracks',value);
            },
            onChangeImages(e) {
                console.log(e)
                this.preview = window.URL.createObjectURL(e);
                if(this.img.length !== 0){
                    this.img.pop();
                    this.img.push(e);
                }
                else{
                    this.img.push(e);
                }
            }
        },
    };
</script>
<style scoped>
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
  #edit_header{
      font-size: 25px;
      padding-left: 2%;
      padding-top: 2%;
  }
</style>