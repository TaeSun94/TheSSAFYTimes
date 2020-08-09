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
                <v-text-field v-model="title" class="ml-3" placeholder="공모전 인원 구합니다악"           
                ></v-text-field>
                <span class="label ml-3">대회명</span>
                <v-text-field v-model="title" class="ml-3" placeholder="예) SSAFY 공모전 or 해커톤"           
                ></v-text-field>
                <span class="label ml-3">한줄 설명</span>
                <v-text-field v-model="content" class="ml-3"  placeholder="예) SSAFY인을 위한 블로그 제작"       
                ></v-text-field>
                <span class="label ml-3">모집인원</span>
                  <v-select class="col-lg-2" 
                      :items="options"
                      label=""
                      chips
                      persistent-hint
                      v-model="count"
                  ></v-select>
                  <span class="label ml-3">기술스택</span>
                  <v-select class="col-lg-8"
                      :items="stack"
                      label=""
                      :multiple=true
                      color="#FCE4EC"
                      :deletable-chips=true
                      chips
                      persistent-hint
                      v-model="stacks"
                  ></v-select>
              </v-form>
              <div class="textfield ml-5 row">
                <span class="label ml-3">상세설명</span>
              </div>
              <vue-editor id="editor" class="ml-5 mr-5" useCustomImageHandler @imageAdded="handleImageAdded"> </vue-editor>
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

export default {
    name:"TeamWrite",
    components: {
      VueEditor
    },
    data() {
      return {
        htmlForEditor: "",
        options:[1,2,3,4,'4명 이상'],
        stack: ['Java','jsp','머신러닝','딥러닝', 'Python', 'Vue.js', 'React', 'Spring', 'Django'],
        
        title: '',
        content: '',
        count: '',
        stacks: '',
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
        console.log(this.title, this.content, this.stacks, this.count)
    }
  }
}
</script>

<style scoped>
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