<template>
<div class="wrapper" style="margin-top:5%">
    
    <div class="row">
        <v-container class="elevation-5 col-lg-7">
            <div id="app">
              <div class="textfield">
                <input type="text" class="textfield-input" v-model="title" placeholder="제목을 입력하세요" value="">
                <hr>
              </div>
              <vue-editor id="editor" useCustomImageHandler @imageAdded="handleImageAdded" v-model="htmlForEditor"> </vue-editor>
            </div>
            <div class="text-right mt-3">
              <v-btn @click="handleSavingContent"> 등록할래요 👌</v-btn>
            </div>
            
            <editor 
              :options="editorOptions"
              height="500px"
              previewStyle="vertical"
              ref="toastuiEditor"/>
        </v-container>

    </div>
    <footer-bar></footer-bar>
</div>
</template>


<script>

import { VueEditor } from "vue2-editor";
import axios from "axios";
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/vue-editor';

export default {
  name: 'Editor',
  components: {
    VueEditor,
    editor: Editor
  },
  data() {
    return {
      htmlForEditor: "",
      title: "",
      editorOptions: {
        hideModeSwitch: true
      }
    };
  },
  

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
    handleBackground() {
      console.log("얍")
    },
    handleSavingContent: function() {
      // You have the content to save
      console.log(this.htmlForEditor);
      console.log(this.title);
      var content = this.$refs.toastuiEditor.invoke("getHtml");
      // var content = this.$refs.toastuiEditor.invoke("getMarkdown");
      console.log(content);
      this.$router.push('/detail')
      
    }
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
/* .textfield-input:focus{
    background: white;
} */
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
/* .ql-editor:focus{
  background-color: white;
} */
</style>