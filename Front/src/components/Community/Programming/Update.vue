<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-5 col-lg-10 col-sm-10">
            <div class="textfield">
                <v-row>
                    <v-col>
                        <input type="text" class="textfield-input" v-model="programTitle" placeholder="제목을 입력하세요" value="">
                        <hr>
                    </v-col>
                    <v-col cols="2">
                        <v-select v-model="programTrack" :items="category" solo label="카테고리"></v-select>
                    </v-col>
                </v-row>
            </div>
            <editor
               v-if="programContent !== ''"
              :options="editorOptions"
              :initialValue="programContent"
              height="500px"
              previewStyle="vertical"
              ref="toastuiEditor"/>
            <div class="text-right mt-3">
                <v-btn @click="checkHandler"> 수정할래요 👌</v-btn>
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
import { mapGetters } from "vuex";

export default {
    name:"ProgramWrite",
    components: {
        editor: Editor
    },
    data() {
        return {
            editorOptions: {
                hideModeSwitch: true
            },
            programNo: 0,
            programTitle: '',
            programWriter: '',
            programDatetime: '',
            programTrack: '',
            programContent: '',
            programHit: 0,
            category: [],
        };
    },
    computed: {
        ...mapGetters(["program"])
    },
    methods: {
        checkHandler() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            if(this.programTitle ==""){
                this.$alert("글 제목을 입력하세요.");
            } else if(content =="") {
                this.$alert("글 내용을 입력하세요.");
            } else if(this.programTrack =="A" || this.programTrack =="B" || this.programTrack == "C" || this.programTrack == "P" || this.programTrack == "기타" || this.programTrack == "") {
                this.$alert("카테고리를 선택하세요");
            } else  {
                this.updateHandler();
            }
        },
        updateHandler() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            http.put("/program/board", {
                memberId: this.programWriter,
                programBoardTitle: this.programTitle,
                programBoardContent: content,
                programBoardTrack: this.programTrack,
                programBoardNo : this.programNo
            }).
            then(({data}) => {
                if(data.result == "success"){
                    this.$alert(data.message);
                    this.$router.push(`/community/programdetail/${this.$route.params.no}`);
                } else {
                    this.$alert(data.message);
                    return;
                }
            })
        }
    },
    created() {
        http.get(`/program/board/${this.$route.params.no}`).then(({data})=> {
            var board = data.data;
            this.programNo = board.programBoardNo;
            this.programTitle = board.programBoardTitle;
            this.programWriter = board.memberId;
            this.programDatetime = board.programBoardDatetime;
            this.programTrack = board.programBoardTrack;
            this.programContent = board.programBoardContent;
            this.programHit = board.programBoardHit;
        });
        http.get("/category/program-track").then(({data})=> {
            this.category = data.list;
        })
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