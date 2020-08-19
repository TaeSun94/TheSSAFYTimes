<template>     
  <div class="wrapper" style="margin-top:8%">
    <div class="row">
      <v-container class="col-lg-5 col-sm-6 elevation-5"> 
        <div class="form sm-m-0">
          <v-form ref="form" lazy-validation v-model="valid">
              <!--Id-->
            <v-text-field 
            v-model="memberId"
            :rules="IdRules"
            :counter="10"
            label="닉네임*"
            required
            @blur="checkIdDup"
            ></v-text-field>
            <!--Email-->
            <v-text-field
            v-model="memberEmail"
            :rules="emailRules"
            label="이메일*"
            required
            >
              <template v-slot:append-outer>
                <v-btn @click="checkEmail" tile>
                  체크
                </v-btn>
              </template>
            </v-text-field>
            
            <!--Pw-->
            <v-text-field 
            v-model="memberPw"
            :rules="PwRules"
            :counter="20"
            label="비밀번호*"
            type="password"
            required
            ></v-text-field>
            <!--Pw 확인-->
            <v-text-field 
            v-model="memberPw2"
            :rules="PwRules2"
            :counter="20"
            label="비밀번호 확인*"
            type="password"
            required
            @blur="checkPw"
            >
            </v-text-field>
            <footer class="login-foot mt-3">
              <v-btn @click="submit"
              @keyup.enter="submit"
              dark
              large
              tile
              width=100%>
                가입하기
              </v-btn>
              <p class="login-option mt-5 text-center">이미 계정이 있으신가요?
              <router-link class="login-option-link" :to="{path:'/login'}">로그인</router-link>
              </p>                                    
            </footer>
          </v-form>
        </div>
     </v-container>
    </div>
  <footer-bar></footer-bar>
</div>
</template>

<script>
import http from '@/http-common'
  export default {
    name: 'Join', 
    data: () => ({
      valid: false,
      memberId: '',
      IdRules: [
        v => !!v || '닉네임을 입력해주세요.',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters'
      ],
      memberPw: '',
      PwRules: [
        v => !!v || '비밀번호를 입력해주세요.',
        v => (v && v.length <= 20) || 'Name must be less than 20 characters',
        value => !!value || 'Required.',
        v => v.length >= 8 || 'Min 8 characters',
      ],
      memberPw2: '',
      PwRules2: [
        v => !!v || '비밀번호를 입력해주세요.',
        v => (v && v.length <= 20) || 'Name must be less than 20 characters',
        value => !!value || 'Required.',
        v => v.length >= 8 || 'Min 8 characters',
      ],
      memberEmail: '',
      emailRules: [
        v => !!v || '이메일을 입력해주세요.',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 형식이 틀립니다!'
      ],
      idck: false,
      pwck: false,
      emailck: false
    }),

    methods: {
      submitJoin () {
          this.$router.push('/EmailCheck')
      },
      submit () {
        if(this.valid == true &&this.idck==true && this.pwck==true && this.emailck==true){
          http.post('/account/signup', {
            memberId: this.memberId,
            memberEmail: this.memberEmail,
            memberPw: this.memberPw,
          })
          .then(({data})=> {
            if(data.result=="success"){
              alert(data.message);
              this.$router.push({path: '/login'});
            } else if(data.result=="fail"){
              alert(data.message);
            }
          });
        } else {
          alert("입력값을 확인해주세요 !");
        }
      },
      clear () {
        this.$refs.form.reset()
      },
      checkIdDup() {
        if(this.memberId=='') {
          alert("닉네임을 입력하세요.");
          return;
        }
        http.post('/valid/id', {
          memberId : this.memberId
        })
        .then(({data})=> {
          if(data.result == "notavailable") {
            this.idck=false;
            this.$alert(data.message);
            return;
          } else if(data.result == "success") {
            this.idck=true;
            this.$alert(data.message);
            return;
          }
        })
      },
      checkPw() {
        if(this.memberPw == '' || this.memberPw2 == ''){
          alert("비밀번호를 입력해주세요.");
          return;
        }
        if(this.memberPw != this.memberPw2){
          this.pwck = false;
          alert("비밀번호가 일치하지 않습니다.");
        } else {
          this.pwck = true;
        }
      },
      checkEmail() {
        if(this.memberEmail==''){
          alert("이메일을 입력하세요.");
          return;
        }
        http.post('/valid/email', {
          memberEmail : this.memberEmail
        })
        .then(({data})=> {
          if(data.result == "success") {
            this.emailck = true;
            this.$alert(data.message);
          } else if(data.result == "notavailable"){
            this.emailck = false;
            this.$alert(data.message);
          }
        })
      }
    },
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
</style>