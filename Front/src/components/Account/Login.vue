<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-5 col-sm-6"> <!-- 기본틀 푸터까지 -->
        <div class="form sm-m-0">
          <v-form ref="form" v-model="valid" lazy-validation>
              <!--Email-->
            <v-text-field 
            v-model="memberEmail"
            :rules="emailRules"
            label="Email*"
            required
            ></v-text-field>
            <!--Pw-->
            <div>
              <v-text-field 
              v-model="memberPw"
              :rules="PwRules"
              :counter="20"
              label="비밀번호*"
              :type="pw"
              required
              >
                <template v-slot:append-outer>
                  <input id="l" type="checkbox" v-model="checkBox" @click="showPw">
                </template>
              </v-text-field>
            </div>
            <br>
            <footer class="login-foot">
                <v-btn @click="submit" dark large tile width=100%>로그인</v-btn>
                <div class="login-divider" role="separator"></div>

                <p class="login-option mt-5 text-center">SSAFY TIMES 처음이신가요?
                <router-link class="login-option-link" :to="{path:'/join'}">가입하기</router-link>
                </p>
                                 
            </footer>
          </v-form>
        </div>
        </v-container>
    </div>
</div>
</template>

<script>
import http from "@/http-common.js";

export default {
    name: 'Login',
    data: () => ({
      valid: true,
      memberEmail: '',
      IdRules: [
        v => !!v || 'ID를 입력해주세요.',
        v => (v && v.length <= 10) || 'ID must be less than 10 characters'
      ],
      memberPw: '',
      PwRules: [
        v => !!v || '비밀번호를 입력해주세요.',
        v => (v && v.length <= 20) || 'PW must be less than 20 characters'
      ],
      emailRules: [
        v => !!v || '이메일을 입력해주세요.',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 형식이 틀립니다!'
      ],
      checkBox : false,
      pw : "password"
    }),

    methods: {
      submit () {
        if (this.$refs.form.validate()) {
          var memberEmail = this.memberEmail;
          var memberPw = this.memberPw;
          http.post('/account/signin', { memberEmail, memberPw})
          .then(({data})=> {
            if(data.result == 'notavailable') {
              alert(data.message);
              location.reload();
            } else if(data.result == 'notvalid') {
              alert(data.message);
              this.$cookies.remove("memberEmail");
              this.$cookies.set("memberEmail", memberEmail, "30MIN");
              this.$router.push("/EmailCheck");
              location.reload();
            } else if(data.result == 'success') {
              alert(data.message);
              this.$cookies.set("memberId", data.data.memberId, "30MIN");
              this.$cookies.set("memberEmail", memberEmail, "30MIN");
              this.$cookies.set("token", data.authorization, "30MIN");
              this.$router.push("/");
              location.reload();
            }
          })
        }
      },
      showPw (){
        if (this.checkBox) { // password
          this.pw = "password";
        } else {  // text
          this.pw = "text";

        }
      }
    }
    
}
</script>

<style scoped>
.is-fullwidth {
  width: 100%;
  display: block;
  margin: 5px;
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
.form {
    margin-left: 20px;
    margin-right: 20px;
}
.btn{
    margin-top: 20px; 
    margin-right: 10px;
}
.theme--light.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined) {
    background-color: #f5f5f5;
}


.v-text-field {
    padding-top: 30px;
}

#l {
	background-color: rgba(0,0,0,0.7);
	border-radius: 0.75em;
	box-shadow: 0.125em 0.125em 0 0.125em rgba(0,0,0,0.3) inset;
	color: #fdea7b;
	display: inline-flex;
	align-items: center;
	margin: auto;
	padding: 0.15em;
	width: 3em;
	height: 1.5em;
	transition: background-color 0.1s 0.3s ease-out, box-shadow 0.1s 0.3s ease-out;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}
#l:before, #l:after {
	content: "";
	display: block;
}
#l:before {
	background-color: #d7d7d7;
	border-radius: 50%;
	width: 1.2em;
	height: 1.2em;
	transition: background-color 0.1s 0.3s ease-out, transform 0.3s ease-out;
	z-index: 1;
}
#l:after {
	background:
		linear-gradient(transparent 50%, rgba(0,0,0,0.15) 0) 0 50% / 50% 100%,
		repeating-linear-gradient(90deg,#bbb 0,#bbb,#bbb 20%,#999 20%,#999 40%) 0 50% / 50% 100%,
		radial-gradient(circle at 50% 50%,#888 25%, transparent 26%);
	background-repeat: no-repeat;
	border: 0.25em solid transparent;
	border-left: 0.4em solid #d8d8d8;
	border-right: 0 solid transparent;
	transition: border-left-color 0.1s 0.3s ease-out, transform 0.3s ease-out;
	transform: translateX(-22.5%);
	transform-origin: 25% 50%;
	width: 1.2em;
	height: 1em;
}
/* Checked */
#l:checked {
	background-color: rgba(0,0,0,0.45);
	box-shadow: 0.125em 0.125em 0 0.125em rgba(0,0,0,0.1) inset;
}
#l:checked:before {
	background-color: currentColor;
	transform: translateX(125%)
}
#l:checked:after {
	border-left-color: currentColor;
	transform: translateX(-2.5%) rotateY(180deg);
}
/* Other States */
#l:focus {
	/* Usually an anti-A11Y practice but set to remove an annoyance just for this demo */
	outline: 0;
}
</style>