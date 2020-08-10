<template>
<div class="wrapper" style="margin-top:7%">
    
    <div class="row">
        <v-container class="elevation-3 col-lg-7">
            <div id="app">
              <div class="text-center main-title">
                <h1 class="mb-2">팀원 찾기 서비스</h1>
                <small>원하는 팀원을 직접 찾아볼 수 있는 서비스 입니다.</small>
              </div>
              <v-form class="ml-4 mr-4 mt-6">
                <span class="label ml-3">제목</span>
                <v-text-field v-model="teamBoardTitle" class="ml-3 mr-3" placeholder="글 제목을 입력해주세요."           
                ></v-text-field>
                <!-- <span class="label ml-3">대회명</span>
                <v-text-field v-model="title" class="ml-3" placeholder="예) SSAFY 공모전 or 해커톤"           
                ></v-text-field>
                <span class="label ml-3">한줄 설명</span>
                <v-text-field v-model="content" class="ml-3"  placeholder="예) SSAFY인을 위한 블로그 제작"       
                ></v-text-field> -->
                <span class="label ml-3">모집 분야</span>
                <v-select
                    v-model="teamBoardCategory"
                    :items="projects"
                    chips
                    persistent-hint
                    class="ml-3 mr-3"
                ></v-select>
                <div class="d-flex">
                  <div class="col-3">
                  <span class="label ml-3 d-inline">Front-end</span>
                      <v-select class="col-lg-6" 
                          :items="front"
                          label=""
                          chips
                          persistent-hint
                          v-model="teamBoardFrontRemainCount"
                      ></v-select>
                  </div>
                  <div class="col-3">
                    <span class="label ml-3 d-inline">Back-end</span>
                      <v-select class="col-lg-6" 
                          :items="back"
                          label=""
                          chips
                          persistent-hint
                          v-model="teamBoardBackRemainCount"
                      ></v-select>          
                  </div>       
                </div>  
                  <v-date-picker v-model="picker" :landscape="landscape" :reactive="reactive" ></v-date-picker>   
                  <!-- <span class="label ml-3">기술스택</span>
                  <v-select class="col-lg-8"
                      :items="stack"
                      label=""
                      :multiple=true
                      color="#FCE4EC"
                      :deletable-chips=true
                      chips
                      persistent-hint
                      v-model="stacks"
                  ></v-select> -->
              </v-form>
              <div class="textfield ml-5 row">
                <span class="label ml-3">상세설명</span>
              </div>
              <vue-editor id="editor" class="ml-5 mr-5" useCustomImageHandler @imageAdded="handleImageAdded" v-model="teamBoardContent"> </vue-editor>
            </div>
            <div class="text-right mt-3 mr-5">
              <v-btn @click="checkHandler"> 등록할래요 👌</v-btn>
              
            </div>
        </v-container>

    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { VueEditor } from "vue2-editor";
import axios from "axios";
import {mapGetters} from "vuex";
export default {
    name:"TeamWrite",
    components: {
      VueEditor
    },
    created() {      
      this.$store.dispatch("getProjects");
    },
    computed:{
      ...mapGetters(['projects'])
    },
    data() {
      return {
        picker: null,
        landscape: true,
        reactive: false,
        htmlForEditor: "",
        front:[1,2,3,4,'4명 이상'],
        back:[1,2,3,4,'4명 이상'],
        stack: ['Java','jsp','머신러닝','딥러닝', 'Python', 'Vue.js', 'React', 'Spring', 'Django'],
        teamBoardFrontRemainCount: '',
        teamBoardBackRemainCount: '',
        teamBoardTitle: '',
        teamBoardContent: '',
        teamBoardCategory: '',
        dateTime: '',
        }
      } ,
    
    methods: {
    handleImageAdded: function(file, Editor, cursorLocation, resetUploader) {
      // An example of using FormData
      // NOTE: Your key could be different such as:
      // formData.append('file', file)

      var formData = new FormData();
      formData.append("image", file);

      axios({
        url: "https://fakeapi.yoursite.com/images",
        method: "POST",
        data: formData
      })
        .then(result => {
          let url = result.data.url; // Get url from response
          Editor.insertEmbed(cursorLocation, "image", url);
          resetUploader();
        })
        .catch(err => {
          console.log(err);
        });
    },
    checkHandler() {
        console.log(this.teamBoardTitle,this.picker, this.teamBoardContent, this.teamBoardFrontRemainCount, this.teamBoardBackRemainCount, this.teamBoardCategory)
      if (this.teamBoardTitle == "") {
        alert("제목을 입력하세요");
      } else if (this.teamBoardContent == "") {
        alert("글 내용을 입력하세요");
      } else if (this.teamBoardFrontRemainCount == "") {
        alert("입력을 확인해주세요")
      } else if (this.teamBoardBackRemainCount == "") {
        alert("입력을 확인해주세요")
      } else if (this.teamBoardCategory == "") {
        alert("분야를 입력해주세요")
      } else {
        // 만약, 내용이 다 입력되어 있다면 createHandler 호출
        var teamBoardTitle = this.teamBoardTitle
        var teamBoardContent = this.teamBoardContent
        var teamBoardFrontRemainCount = this.teamBoardFrontRemainCount
        var teamBoardBackRemainCount = this.teamBoardBackRemainCount
        var teamBoardCategory = this.teamBoardCategory
        var memberId = this.$cookies.get("memberId")
        this.$store.dispatch("teamCreate", { teamBoardTitle, teamBoardContent, teamBoardFrontRemainCount, teamBoardBackRemainCount, teamBoardCategory, memberId  });
      }
    },
    
    },
  }

</script>

<style scoped>
.col-3{
  padding: 0px;
}
.label {
  line-height: 38px;
  font-size: 1rem;
  font-weight: 700;
  vertical-align: top;
  width: 80%;
  display: inline-block;
}

.textfield-input {
    display: block;
    width: 100%;
    padding: 11px 40px 12px 1.5rem;
    border-radius: 0;
    box-sizing: border-box;
    font: inherit;
    outline: none;
    box-shadow: none;
    font-size: 1rem;
    font-weight: 100;
}
.main-title h1{
  color: #FFB459;

}
small {
  border-bottom: 0.3px solid green;
}
.theme--light.v-application {
    background: #fff;
    font-family: 'Noto Sans KR', sans-serif
}
.container{
  background: #fff;
  border-radius: 20px;
  padding: 30px;
}

</style>