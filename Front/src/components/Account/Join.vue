<template>     
  <div class="wrapper" style="margin-top:8%">
    <div class="row">
      <v-container class="col-lg-5 col-sm-6 elevation-5"> 
        <div class="form sm-m-0">
          <v-form ref="form" lazy-validation v-model="valid">
            <v-text-field 
            v-model="memberId"
            :rules="IdRules"
            :counter="10"
            label="닉네임*"
            required
            :color="color"
            @blur="checkIdDup"
            ></v-text-field>
            <v-text-field
            v-model="memberEmail"
            :rules="emailRules"
            label="이메일*"
            required
            :color="color"
            >
              <template v-slot:append-outer>
                <v-btn @click="checkEmail" tile>
                  체크
                </v-btn>
              </template>
            </v-text-field>
            <v-text-field 
            v-model="memberPw"
            :rules="PwRules"
            :counter="20"
            label="비밀번호*"
            type="password"
            required
            :color="color"
            ></v-text-field>
            <v-text-field 
            v-model="memberPw2"
            :rules="PwRules2"
            :counter="20"
            label="비밀번호 확인*"
            type="password"
            required
            :color="color"
            @blur="checkPw"
            >
            </v-text-field>
            <v-checkbox v-model="termck" :color=color>
              <template v-slot:label>
                <div>
                  I agree that
                  <v-dialog v-model="dialog" width="600px">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        color="primary"
                        dark
                        small
                        v-bind="attrs"
                        v-on="on"
                        text
                      >
                        The SSAFY Times
                      </v-btn>
                    </template>
                    <v-card>
                      <v-card-title>
                        <span class="headline">The SSAFY Times Term And Conditions</span>
                      </v-card-title>
                      <v-card-text>
                        약관에 동의하시나여....
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn :color="color" text @click="termDisagree">Disagree</v-btn>
                        <v-btn :color="color" text @click="termAgree">Agree</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                  Term And Condition
                </div>
              </template>
            </v-checkbox>
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
      emailck: false,
      termck:false,
      checkbox:false,
      dialog:false,
      color:"#c00000"
    }),

    methods: {
      termAgree(){
        this.termck = true;
        this.dialog = false;
      },
      termDisagree(){
        this.termck = false;
        this.dialog = false;
      },
      submitJoin () {
          this.$router.push('/EmailCheck')
      },
      submit () {
        if (this.valid != true) {
            alert("주어진 규칙에 맞춰 작성해주세요.");
        } else if (this.idck != true) {
            alert("닉네임을 입력하세요.");
        } else if (this.pwck != true) {
            alert("비밀번호를 확인해주세요.")          
        } else if (this.emailck != true) {
            alert("이메일 확인해주세요.")          
        } else if (this.termck != true) {
            alert("약관에 동의해주세요.")          
        } else {
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