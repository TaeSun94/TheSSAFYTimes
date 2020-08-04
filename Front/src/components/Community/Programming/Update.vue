<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-10">
            <div class="textfield">
                <input type="text" class="textfield-input" v-model="programTitle" placeholder="제목을 입력하세요" value="">
                <hr>
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
        };
    },
    computed: {
        ...mapGetters(["program"])
    },
    methods: {
        checkHandler() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            if(this.programTitle ==""){
                alert("글 제목을 입력하세요.");
            } else if(content =="") {
                alert("글 내용을 입력하세요.");
            } else  {
                this.updateHandler();
            }
        },
        updateHandler() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            http.put("/board/program", {
                memberId: sessionStorage.getItem("memberId"),
                programBoardTitle: this.programTitle,
                programBoardContent: content,
                programBoardTrack: this.programTrack,
                programBoardNo : this.programNo
            }).
            then(({data}) => {
                if(data.result == "success"){
                    alert(data.message);
                    this.$router.push(`/community/programdetail/${this.$route.params.no}`);
                } else {
                    alert(data.message);
                    return;
                }
            })
        }
    },
    created() {
        http.get(`/board/program/${this.$route.params.no}`).then(({data})=> {
            var board = data.programBoard;
            this.programNo = board.programBoardNo;
            this.programTitle = board.programBoardTitle;
            this.programWriter = board.memberId;
            this.programDatetime = board.programBoardDatetime;
            this.programTrack = board.programBoardTrack;
            this.programContent = board.programBoardContent;
            this.programHit = board.programBoardHit;
        });
    },
    updated() {
        // this.title = this.program.programBoardTitle;
        // this.content = this.program.programBoardContent;
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