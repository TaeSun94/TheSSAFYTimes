<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-8 col-lg-5"> <!-- 기본틀 푸터까지 -->
        <form class="login" name="login">
            <v-text-field
            v-model="memberEmail"
            :rules="emailRules"
            label="이메일*"
            disabled
            required
            ></v-text-field>

            <footer class="login-foot">
                <button class="primary-button mt-3 is-fullwidth" type="submit" v-on:click="emailValid()">이메일 인증하기</button>
                <div class="login-divider" role="separator"></div>

                <p class="login-option">이메일 인증이 필요해요!
                </p>
                <p class="login-option">인증 완료 됐나요?
                    <a class="login-option-link" href="/login">로그인하기</a>
                </p> 
                                 
            </footer>
        </form>
        
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import http from "@/http-common.js";
export default {
    name: 'EmailCheck',
    data: () => ({
        memberEmail: '',
        emailRules: [
        v => !!v || '이메일을 입력해주세요.',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 형식이 틀립니다!'
      ],
    }),
    created() {
        this.memberEmail = this.$cookies.get('memberEmail');
    },
    methods: {
        emailValid() {
            http.post('/valid/sendEmail', {
                memberEmail: this.memberEmail
            })
            .then(({data})=> {
                console.log(data);
                if(data.result=="success"){
                    alert(data.message);
                } else if(data.result=="fail"){
                    alert(data.message);
                }
            });
        }
    }

}
</script>

<style scoped>

.is-fullwidth {
    display: block;
    width: 100%;
}
.primary-button {
    display: inline-block;
    min-width: 104px;
    background: #fb7800;
    padding: 11px 1.5rem 12px;
    border-radius: 0;
    text-align: center;
    cursor: pointer;
    position: relative;
    background: #161616;
    border: 1px solid #161616;
    color: #fff;
}
.login-option-link {
    margin: 0 .5rem;
    color: #252525;
    text-decoration: underline;
}
p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
.textfield-input {
    display: block;
    width: 100%;
    padding: 11px 40px 12px 1.5rem;
    background: #f3f3f3;
    border: 1px solid #252525;
    border-radius: 0;
    box-sizing: border-box;
    font: inherit;
    color: #252525;
    -webkit-transition: all .2s;
    transition: all .2s;
    outline: none;
    box-shadow: none;
}
.login {
    max-width: 400px;
    margin: 0 auto;
    padding: 7.5% 5% 4rem;
    position: relative;
}
.textfield {
    margin: 1rem 0;
    position: relative;
}
</style>