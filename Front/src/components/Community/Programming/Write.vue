<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-5 col-lg-10 col-sm-10">
            <div class="textfield">
                <v-row>
                    <v-col>
                        <input type="text" class="textfield-input" v-model="title" placeholder="제목을 입력하세요" value="">
                        <hr>
                    </v-col>
                    <v-col cols="2">
                        <v-select v-model="track" :items="category" solo label="카테고리"></v-select>
                    </v-col>
                </v-row>
            </div>
            <editor 
              :options="editorOptions"
              height="500px"
              previewStyle="vertical"
              ref="toastuiEditor"/>
            <div class="text-right mt-3">
                <v-btn @click="checkHandler" tile> 등록할래요 👌</v-btn>
            </div>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import http from "@/http-common";
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/vue-editor';

export default {
    name:"ProgramWrite",
    components: {
        editor: Editor
    },
    data() {
        return {
            title: "",
            editorOptions: {
                hideModeSwitch: true
            },
            member : {},
            category: [],
            track: '',
        };
    },
    created() {
        var id = this.$cookies.get('memberId');
        http.get("/member/"+id).then(({data})=> {
            this.member = data.data;
        });
        http.get("/category/program-track").then(({data})=>{
            this.category = data.list;
        })
    },
    methods: {
        checkHandler() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            if(this.title ==""){
                alert("글 제목을 입력하세요.");
            } else if(content =="") {
                alert("글 내용을 입력하세요.");
            }  else if(this.track =="") {
                alert("카테고리를 선택하세요");
            } else  {
                this.createHandler();
            }
        },
        createHandler() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            http.post("/program/board", {
                memberId: this.$cookies.get("memberId"),
                programBoardTitle: this.title,
                programBoardContent: content,
                programBoardTrack: this.track,
            }).
            then(({data}) => {
                if(data.result == "success"){
                    alert(data.message);
                    this.$router.push("/community/programlist");
                } else {
                    alert(data.message);
                    return;
                }
            })
        }
    }
}
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