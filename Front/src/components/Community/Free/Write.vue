<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-5 col-lg-7 col-sm-10">
            <div id="app">
              <div class="textfield">
                <input type="text" class="textfield-input" v-model="freeBoardTitle" placeholder="제목을 입력하세요" value="">
                <hr>
              </div>
              <vue-editor id="editor" useCustomImageHandler @imageAdded="handleImageAdded" v-model="freeBoardContent"> </vue-editor>
            </div>
            <div class="text-right mt-3">
              <v-btn @click="checkHandler"> 등록할래요 👌</v-btn>
            </div>
        </v-container>
    </div>
</div>
</template>

<script>

import { VueEditor } from "vue2-editor";
import axios from "axios";
export default {
  name: 'FreeWrite',
  components: {
    VueEditor
  },
  data() {
    return {
      freeBoardTitle: "",
      freeBoardContent: "",
    };
  },
  methods: {
    handleImageAdded: function(file, Editor, cursorLocation, resetUploader) {
      var formData = new FormData();
      formData.append("image", file);
      axios({
        url: "https://fakeapi.yoursite.com/images",
        method: "POST",
        data: formData
      })
        .then(result => {
          let url = result.data.url;
          Editor.insertEmbed(cursorLocation, "image", url);
          resetUploader();
        })
        .catch(err => {
          console.log(err);
        });
    },
    checkHandler() {
      if (this.freeBoardTitle == "") {
        this.$alert("글 제목을 입력하세요");
      } else if (this.freeBoardContent == "") {
        this.$alert("글 내용을 입력하세요");
      } else {
        var freeBoardTitle = this.freeBoardTitle
        var freeBoardContent = this.freeBoardContent
        var memberId = this.$cookies.get("memberId")
        this.$store.dispatch("freeCreate", { freeBoardTitle, freeBoardContent, memberId });
      }
    },
  }
};
</script>
<style scoped>

.textfield-input {
    display: block;
    width: 100%;
    padding: 11px 40px 12px 1.5rem;
    border-radius: 0;
    box-sizing: border-box;
    font: inherit;
    color: black;
    -webkit-transition: all .2s;
    transition: all .2s;
    outline: none;
    box-shadow: none;
    font-size: 1.7rem;
    font-style: bold;
}
hr{
    width: 30%;
    border: 3px solid darkorange;
    margin-bottom: 20px;
    margin-left: 20px;  
}
.v-btn.v-size--default, .v-btn.v-size--large {
    font-size: 1rem;
    font-family: 'Noto Sans KR', sans-serif;
    font-weight: 400;
}
.theme--light.v-application {
    background: #fff;
    font-family: 'Noto Sans KR', sans-serif
}
.ql-editor {
  height: 400px;
}
</style>