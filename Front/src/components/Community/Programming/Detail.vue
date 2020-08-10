<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-6 col-sm-10">
            <vue-scroll-progress-bar height="0.3rem" backgroundColor="orange"/>
            <div class="textfield">
                <div class="ml-4"><h1>{{programTitle}}</h1></div>
                <hr>
            </div>
            <div class="text-right mr-5">
                <small class="description">👀 조회수 {{programHit}} / </small>
                <small class="description"> SSAFY {{member.memberTrack}} {{programWriter}} / </small>
                <small class="description">{{programDatetime | moment('YYYY-MM-DD HH:MM')}}  </small>
            </div>
            <div></div>
            <Viewer v-if="programContent !== ''" :initialValue="programContent" class="inner" />
            <v-btn depressed dark v-show="canEdit === true" @click="deleteHandler" class="mr-5" style="float: right;">삭제하기!</v-btn>
            <v-btn depressed dark v-show="canEdit === true" @click="toUpdate()" class="mr-1" style="float: right;">수정하기!</v-btn>
            <div class="likeContent">
                <h3 class="like ml-5">❤️ 이 글 좋아요 </h3>
                <h3 class="like">10</h3>
            </div>
            <div>
                <div class="text-right comment" @click="commentShow">
                    댓글 달기
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </div>
                <div v-show="content" class="inner-comment">
                    <v-container>
                        <v-textarea
                            solo
                            clearable
                            auto-grow
                            label="댓글을 달아주세요!"
                            prepend-icon="mdi-comment"
                            @keydown.enter="commentCheck"
                            hint="댓글을 달려면 Enter를 눌러주세요."
                            style="margin:3%"
                            v-model="commentInput"
                        ></v-textarea>
                    </v-container>
                </div>
                <hr style="width:95%" class="mt-5">
                <div v-show="commentContent">
                    <div class="comment-content" v-for="item in program_comments" :key="item.programCommentNo">
                        <v-simple-table>
                            <template v-slot:default>
                            <tbody>
                                <tr>
                                    <p class="faq-content">{{ item.programCommentContent }}<br></p>
                                    <p class="faq-txt text-right">🧑 {{ item.memberId }}님</p>
                                </tr>
                            </tbody>
                            </template>
                        </v-simple-table>
                    </div>
                </div>
            </div>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { mapGetters } from "vuex";
import { VueScrollProgressBar } from '@guillaumebriday/vue-scroll-progress-bar'
import http from "@/http-common.js";
import "codemirror/lib/codemirror.css";
import "@toast-ui/editor/dist/toastui-editor.css";
import { Viewer } from "@toast-ui/vue-editor";

export default {
    name:"ProgramDetail",
    components: {
        VueScrollProgressBar,
        Viewer,
    },
    data() {
        return {
            programNo: 0,
            programTitle: '',
            programWriter: '',
            programDatetime: new Date(),
            programTrack: '',
            programContent: "",
            programHit: 0,

            content: false,
            //comment 
            commentContent: true,
            commentInput: '',
            
            //edit, delete관련
            canEdit: false,
            member: {},
        }
    },
    computed: {
        ...mapGetters(["program"]),
        ...mapGetters(["program_comments"]),
    },
    methods: {
        commentShow() {
            this.content = !this.content
        },
        commentCheck() {
            if(this.commentInput == ""){
                alert("댓글을 입력하세요");
                return;
            } else {
                this.commentCreate();
            }
        },
        commentCreate() {
            http.post("/program/comment", {
                memberId : this.$cookies.get("memberId"),
                commentContent: this.commentInput,
                boardNo :  parseInt(`${this.$route.params.no}`)
            }).
            then(({data}) =>{
                if(data.result == "success") {
                    alert(data.message);
                    location.reload();
                } else {
                    alert(data.message);
                    return;
                }
            })
        },
        deleteHandler() {
            http.delete(`/program/board/${this.$route.params.no}`).then(({data}) => {
                if(data.result == "success"){
                    alert(data.message);
                    this.$router.push("/community/programlist");
                } else {
                    alert(data.message);
                    return;
                }
            });
        },
        toUpdate() {
            this.$router.push(`/community/programupdate/${this.$route.params.no}`);
        }

    },
    created() {
        // this.$store.dispatch("getProgram", `/board/program/${this.$route.params.no}`);
        this.$store.dispatch("getProgramComments", `/program/${this.$route.params.no}/comment`);
        http.get(`/program/board/${this.$route.params.no}`).then(({data})=> {
            var board = data.data;
            this.programNo = board.programBoardNo;
            this.programTitle = board.programBoardTitle;
            this.programWriter = board.memberId;
            this.programDatetime = board.programBoardDatetime;
            this.programTrack = board.programBoardTrack;
            this.programContent = board.programBoardContent;
            this.programHit = board.programBoardHit;
        }).then(()=> {
            http.get("/member/"+this.programWriter).then(({data})=> {
                this.member = data.data;
            })
        });
    },
    mounted() {
        
    },
    updated() {
        var id = this.$cookies.get('memberId');
        var author = this.programWriter;
        if(id != author) { this.canEdit = false }
        else {this.canEdit = true }
    }
}
</script>


<style scoped>
p {
    margin-bottom:0px
}
hr{
    width: 30%;
    border: 3px solid darkorange;
    margin-bottom: 20px;
    margin-left: 20px;  
}
.likeContent {
    margin-left:20px;
    margin-top:30px;
    margin-bottom:30px;
}
.like {
    
    display: inline;
}
.comment {
    cursor: pointer;
}
.faq-content{
    margin-top: 10px;
    margin-left:20px;
    margin-right:20px;
}
.faq-txt {
    margin-left:20px;
    margin-right:20px;
    margin-bottom: 10px;
    color: #666;
    border-radius: 10px;
}
.inner{ 
    margin: 20px;
    padding: 20px;
    padding-bottom: 50px;
    border-radius: 10px;
    border: 0.3px solid #ccc;
    
}
.inner-comment{
    margin: 20px;
    border-radius: 10px;
    border: 0.3px solid #ccc;
}
.comment {
    margin: 5px;
    margin-left:20px;
    margin-right:20px;
    border-radius: 10px;
}
/*  */
.description{
    display: inline;
}

.comment-content{
    margin: 5px;
    margin-left:20px;
    margin-right:20px;
    border-radius: 10px;
}

</style>