<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-5"> <!-- 기본틀 푸터까지 -->
        <div class="form sm-m-0">
          <v-form ref="form" v-model="valid" lazy-validation>
              <!--Id-->
            <v-text-field 
            v-model="memberId"
            :rules="IdRules"
            :counter="10"
            label="ID*"
            required
            ></v-text-field>
            <!--Pw-->
            <v-text-field 
            v-model="memberPw"
            :rules="PwRules"
            :counter="20"
            label="비밀번호*"
            type="password"
            required
            ></v-text-field>
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
    <footer-bar></footer-bar>
</div>
</template>

<script>
export default {
    name: 'Login',
    data: () => ({
      valid: true,
      memberId: '',
      IdRules: [
        v => !!v || 'ID를 입력해주세요.',
        v => (v && v.length <= 10) || 'ID must be less than 10 characters'
      ],
      memberPw: '',
      PwRules: [
        v => !!v || '비밀번호를 입력해주세요.',
        v => (v && v.length <= 20) || 'PW must be less than 20 characters'
      ],
    }),

    methods: {
      submit () {
        if (this.$refs.form.validate()) {
          var memberId = this.memberId;
          var memberPw = this.memberPw;
          this.$store.dispatch("login", {memberId, memberPw});
        }
      },
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
</style>