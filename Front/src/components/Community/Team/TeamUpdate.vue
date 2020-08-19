<template>
<div class="wrapper" style="margin-top:8%">
    
    <div class="row">
        <v-container class="elevation-5 col-lg-7">
            <div id="app">
              <div class="text-center main-title">
                <h1 class="mb-2">팀원 찾기 서비스</h1>
                <small>원하는 팀원을 직접 찾아볼 수 있는 서비스 입니다.</small>
              </div>
              <v-form class="ml-4 mr-4 mt-6">
                <span class="label ml-3">제목</span>
                <v-text-field v-model="boardTitle" class="ml-3 mr-3" placeholder="글 제목을 입력해주세요."           
                ></v-text-field>
                <span class="label ml-3">모집 분야</span>
                
                <v-select
                  v-model="boardCategory"
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
                        v-model="boardFrontRemainCount"
                      ></v-select>
                  </div>
                  <div class="col-3">
                    <span class="label ml-3 d-inline">Back-end</span>
                      <v-select class="col-lg-6" 
                        :items="back"
                        label=""
                        chips
                        persistent-hint
                        v-model="boardBackRemainCount"
                      ></v-select>          
                  </div>       
                </div>  
                <span class="label ml-3 d-inline">마감일</span><br>
                <v-date-picker 
                  v-model="boardEndDatetime" 
                  :landscape="landscape" 
                  :reactive="reactive" 
                  :min="minDate"
                  locale="ko-KR"
                  class="ml-4 mt-4 mb-4"
                  color="green lighten-1"
                  is-dark
                  is-inline
                >
                </v-date-picker>   

              </v-form>
              <div class="textfield ml-5 row">
                <span class="label ml-3">상세설명</span>
              </div>
              <vue-editor id="editor" class="ml-5 mr-5" useCustomImageHandler @imageAdded="handleImageAdded" v-model="boardContent"> </vue-editor>
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
import { mapGetters } from "vuex";
import http from "@/http-common.js"


export default {
    name:"TeamUpdate",
    components: {
      VueEditor
    },
    created() {      
      this.$store.dispatch("getProjects");
      http.get(`/team/board/${this.$route.params.no}`).then(({data})=> {
        var board = data.data;
        this.boardNo = board.teamBoardNo;
        this.boardTitle = board.teamBoardTitle;
        this.boardFrontRemainCount = board.teamBoardFrontRemainCount
        this.boardBackRemainCount = board.teamBoardBackRemainCount
        this.boardContent = board.teamBoardContent
        this.teamBoardWriter = board.memberId;
      }).then(()=> {
          http.get("/member/"+this.teamBoardWriter).then(({data})=> {
          this.member = data.data;
        })
      });      
    },
    computed:{
      ...mapGetters(['projects'])
    },
    data() {
      return {
        boardEndDatetime: null,
        landscape: true,
        reactive: false,
        htmlForEditor: "",
        boardFrontRemainCount: '',
        boardBackRemainCount: '',
        front:[1,2,3,4,'4명 이상'],
        back:[1,2,3,4,'4명 이상'],
        boardTitle: '',
        boardContent: '',
        boardCategory: '',
        minDate: new Date().toISOString().substr(0, 10),
        memberId: '',
        boardNo: '',
        teamBoardWriter: '',
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
        //console.log(this.teamBoardTitle,this.picker, this.teamBoardContent, this.teamBoardFrontRemainCount, this.teamBoardBackRemainCount, this.teamBoardCategory)
      if (this.boardTitle == "") {
        this.$alert("제목을 입력하세요");
      } else if (this.boardContent == "") {
        this.$alert("글 내용을 입력하세요");
      } else if (this.boardFrontRemainCount == "") {
        this.$alert("입력을 확인해주세요")
      } else if (this.boardBackRemainCount == "") {
        this.$alert("입력을 확인해주세요")
      } else if (this.boardCategory == "") {
        this.$alert("카테고리를 선택해주세요")
      } else if (this.boardEndDatetime == null) {
        this.$alert("날짜를 선택해주세요")
      }
      else {
          this.updateHandler()
      }
    },
    updateHandler() {
      http.put("/team/board", {

         boardTitle:  this.boardTitle,
         boardContent : this.boardContent,
         boardFrontRemainCount : this.boardFrontRemainCount,
         boardBackRemainCount: this.boardBackRemainCount,
         boardCategory:this.boardCategory,
         boardEndDatetime : this.boardEndDatetime,
         boardNo : this.boardNo,
         memberId : this.teamBoardWriter

      }).then(({data})=> {
        if(data.result=="success"){
          this.$alert(data.message)
          this.$router.push(`/community/teamdetail/${this.$route.params.no}`)
        } else {
          this.$alert(data.message)
          return
        }
      })
    }
  }
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