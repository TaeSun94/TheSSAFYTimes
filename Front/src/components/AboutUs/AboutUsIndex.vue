<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-5 col-lg-9">
            <template class="basil template">
                <v-card color="basil" class="card">
                    <v-card-title class="text-center justify-center py-6">
                        <h1 class="font-weight-bold display-3 basil--text ">SSAFIENCE</h1>
                    </v-card-title>

                    <v-tabs
                    v-model="tab"
                    background-color="transparent"
                    color="basil"
                    grow
                    >
                        <v-tab>공선아</v-tab><v-tab>김지웅</v-tab><v-tab>유태선</v-tab><v-tab>이승경</v-tab><v-tab>정성윤</v-tab>
                        <v-tab-item class="basil">
                            <v-card color="basil" flat>
                                <v-card-text>
                                    <div class="md-layout text-center" >
                                        <div class="md-layout-item md-size-50 mx-auto">
                                            <div class="profile">
                                                <v-avatar size="300" class="profile-img mb-5">
                                                    <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John">
                                                </v-avatar>
                                                <div>
                                                    <h3>정성윤</h3>
                                                    <h6>Developer</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div >
                                        <div class="head text-center" style="font-weight:bold">
                                            <br />
                                            <strong>환영합니다. 슬기로운제주살이(주)입니다.</strong>
                                        </div>
                                        <div class="greetings mt-5 mb-3" style='text-align:left; margin-left:15%;margin-right:15%'>
                                            <p>
                                                안녕하십니까?<br>
                                                슬기로운제주살이(주) 개발자 정성윤입니다.<br>
                                                슬기로운제주살이를 아끼고 사랑해주시는 SSAFY고객님과 운영프로님들, 삼성전자 임직원 여러분께
                                                진심으로 감사를 드립니다.<br><br>
                                                슬기로운제주살이(주)는 2020년 국내 최초의 관광기업으로 출범한 이래 SSAFY고객 여러분의 '제주와 휴식'을 지켜드리기 위해 노력해 왔습니다.<br>
                                                국내 관광산업의 선두주자로서 제주 관광정보와 제주살이 커뮤니티, 인적자원 네트워크를 결합시켜 나아가고 있으며, 고객 여러분께서 365일, 24시간 행복한 제주살이를 누릴 수 있도록 최선을 다하고 있습니다.<br>
                                                나아가 제주의 모든 정보와 부동산정보를 제공하는 '제주 종합 관광 솔루션 기업, 슬기로운제주살이'으로 거듭날 것을 약속드립니다.<br>
                                                <br>
                                                언제 어디서나 고객님과 함께 하겠습니다.<br>
                                                감사합니다.<br>
                                            </p>
                                            <div class="space-right mb-5" style='margin-right:15%'>Developer 정성윤</div>
                                        </div>
                                    </div>
                                </v-card-text>
                            </v-card>
                        </v-tab-item>
                        <!-- 위는 예시 밑에는 포멧 -->
                        <v-tab-item class="basil">
                            <v-card color="basil" flat>
                                <v-card-text>
                                    <div class="md-layout text-center" >
                                        <div class="md-layout-item md-size-50 mx-auto">
                                            <div class="profile">
                                                <v-avatar size="300" class="profile-img mb-5">
                                                    <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John">
                                                </v-avatar>
                                                <div>
                                                    <h3>정성윤</h3>
                                                    <h6>Developer</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div >
                                        <div class="head text-center" style="font-weight:bold">
                                            <br />
                                            <strong>환영합니다. 슬기로운제주살이(주)입니다.</strong>
                                        </div>
                                        <div class="greetings mt-5 mb-3" style='text-align:left; margin-left:15%;margin-right:15%'>
                                            <p>
                                                안녕하십니까?<br>
                                                슬기로운제주살이(주) 개발자 정성윤입니다.<br>
                                                슬기로운제주살이를 아끼고 사랑해주시는 SSAFY고객님과 운영프로님들, 삼성전자 임직원 여러분께
                                                진심으로 감사를 드립니다.<br><br>
                                                슬기로운제주살이(주)는 2020년 국내 최초의 관광기업으로 출범한 이래 SSAFY고객 여러분의 '제주와 휴식'을 지켜드리기 위해 노력해 왔습니다.<br>
                                                국내 관광산업의 선두주자로서 제주 관광정보와 제주살이 커뮤니티, 인적자원 네트워크를 결합시켜 나아가고 있으며, 고객 여러분께서 365일, 24시간 행복한 제주살이를 누릴 수 있도록 최선을 다하고 있습니다.<br>
                                                나아가 제주의 모든 정보와 부동산정보를 제공하는 '제주 종합 관광 솔루션 기업, 슬기로운제주살이'으로 거듭날 것을 약속드립니다.<br>
                                                <br>
                                                언제 어디서나 고객님과 함께 하겠습니다.<br>
                                                감사합니다.<br>
                                            </p>
                                            <div class="space-right mb-5" style='margin-right:15%'>Developer 정성윤</div>
                                        </div>
                                    </div>
                                </v-card-text>
                            </v-card>
                        </v-tab-item>
                    </v-tabs>
                </v-card>
            </template>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
// import { VueScrollProgressBar } from '@guillaumebriday/vue-scroll-progress-bar'
export default {
    name:"AboutUsIndex",
    // components: {
    //   VueScrollProgressBar
    // },
    data() {
        return {
            tab: null,
        }
    }
}
</script>

<style scoped>

.container {
    padding: 15px;
}
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
h1 {
  font-family: 'Noto Sans KR', sans-serif;
  color: white;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
  }
.basil {
  background-color: #CCCCCC !important;
}
.card{
    border: 0.3px solid black;
}
.name .title {
    color: black;
    font-size: 2rem;
    font-weight: 700;
}
.profile-img{
    border: 5px solid white;
}
h6 {
    margin-top:20px;
    font-size: 1rem;
}
p {
    font-size: 1rem;
}
h3 {
    font-size:2rem;
    color: black;
}
.v-tab {
    align-items: center;
    display: flex;
    flex: 0 1 auto;
    font-size: 1.2rem;
    font-weight: 700;
    justify-content: center;
    letter-spacing: .0892857143em;
    line-height: normal;
    min-width: 90px;
    max-width: 360px;
    outline: none;
    padding: 0 16px;
    position: relative;
    text-align: center;
    text-decoration: none;
    text-transform: uppercase;
    transition: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}
.profile-img:hover{
    background-color: black;
    cursor: pointer;
    opacity: 0.5;
}
</style>