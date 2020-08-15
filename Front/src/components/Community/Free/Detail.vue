<template>
    <div class="wrapper" style="margin-top:8%">
        <!-- <div>
            <SearchBar />
        </div> -->

        <div class="row">
            
            <v-container class="elevation-5 col-lg-7">
                <div id="app">
                    <div class="textfield">
                        <div v-html="freeBoardTitle" class="ml-4 textfield-input"></div>
                        <hr>
                    
                    </div>
                                    
                    <div class="text-right mr-5">
                        
                        <small class="description">👀 조회수 {{ freeBoardHit }} /</small>
                        <small class="description"> SSAFY 3기 / </small>
                        <small class="description"> {{$moment(freeBoardDatetime).format('YYYY-MM-DD hh:mm:ss a')}} </small>

                    </div>
                   
                    <div v-html="freeBoardContent" class="inner">
                    </div>
                    <v-btn depressed dark v-show="canEdit === true" @click="deleteHandler" class="mr-5" style="float: right;">삭제하기!</v-btn>
                    <v-btn depressed dark v-show="canEdit === true" @click="toUpdate()" class="mr-1" style="float: right;">수정하기!</v-btn>
                
                    <div class="u_likeit">
                        <ul class="u_likeit_layer _faceLayer" role="menu">
                            <li class="u_likeit_list good" role="menuitem">
                                <a class="u_likeit_list_button _button nclicks(abt_presslink) off" data-type="like" data-log="RTC.like|RTC.unlike" href="#" role="button" aria-selected="false" aria-pressed="false">
                                    <span class="u_likeit_list_name _label" @click="upButton"> Up 👍</span>
                                    <span class="u_likeit_list_count _count">{{  freeBoardLike }}</span>
                                </a>
                            </li>
                            <li class="u_likeit_list warm" role="menuitem">
                                <a class="u_likeit_list_button _button off" data-type="warm" data-log="RTC.warm|RTC.unwarm" href="#" role="button" aria-selected="false" aria-pressed="false">
                                    <span class="u_likeit_list_name _label" @click="downButton">Down 👎</span>
                                    <span class="u_likeit_list_count _count">{{  freeBoardDislike }}</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    
                  <!--댓글 쓰기 폼-->
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
                    </div>
                    <hr style="width:95%" class="mt-5">
                    
                    <!--댓글 목록-->
                    <div v-show="commentContent">
                        <div class="comment-content" v-for="item in free_comments" :key="item.freeCommentNo">
                            <v-simple-table>
                                <template v-slot:default>
                                <tbody >
                                    <tr>
                                        <p class="faq-content">{{ item.freeCommentContent }}<br></p>
                                        <p class="faq-txt text-right">😷 **** 님</p>
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
import { mapGetters } from 'vuex'
import http from "@/http-common.js"

export default {

    name: 'FreeDetail',
    data() {
        return {
            content: false,
            commentContent: true,
            likeControll: true,
            check: false,
            memberId: '',
            commentInput: '',
            upCount: '',
            freeBoardNo: 0,
            freeBoardWriter: '',
            freeBoardTitle: '',
            freeBoardLike: '',
            freeBoardDatetime: '',
            freeBoardContent: '',
            freeBoardHit: 0,
            freeBoardDislike : '',

            //edit, delete관련
            canEdit: false,
            member: {},
        }
    },
    computed: {
        ...mapGetters(["free"]),
        ...mapGetters(["free_comments"]),

    },
    created() {
        
        this.count = this.free.data.freeBoardLikeCount 
        this.$store.dispatch("getFreeComments", `/free/${this.$route.params.no}/comment`)
        http.get(`/free/board/${this.$route.params.no}`).then(({data})=> {
            var board = data.data;
            this.freeBoardNo = board.freeBoardNo;
            this.freeBoardTitle = board.freeBoardTitle;
            this.freeBoardLike = board.freeBoardLike;
            this.freeBoardHit = board.freeBoardHit;
            this.freeBoardDislike = board.freeBoardDislike;
            this.freeBoardDatetime = board.freeBoardDatetime;
            this.freeBoardContent = board.freeBoardContent;
            this.freeBoardWriter = board.memberId;
        }).then(()=> {
                http.get("/member/"+this.freeBoardWriter).then(({data})=> {
                this.member = data.data;
            })
        });
    },
    methods: {
        commentShow() {
            this.content = !this.content
        },
        commentCreate() {
            http.post("/free/comment", {
                memberId : this.$cookies.get('memberId'),
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
        commentCheck() {
            if(this.commentInput == ""){
                alert("댓글을 입력하세요");
                return;
            } else {
                this.commentCreate();
            }
        },
        deleteHandler() {
            http.delete(`/free/board/${this.$route.params.no}`).then(({data}) => {
                if(data.result == "success"){
                    alert(data.message);
                    this.$router.push("/community/freelist");
                } else {
                    alert(data.message);
                    return;
                }
            });
        },
        upButton() {
            var boardLikeCheck = 1
            var boardNo = this.$route.params.no
            var memberId = this.$cookies.get("memberId");
            
            http.post('/free/like',{
                boardLikeCheck,
                boardNo,
                memberId,
            })
            .then(({data})=> {
                if(data.result != "success") {
                    alert(data.message)
                }else{
                    this.$store.commit("setFreeLike", data);
                }
            })
        },

        downButton() {
            var boardLikeCheck = 1
            var boardNo = this.$route.params.no
            var memberId = this.$cookies.get('memberId');
            
            http.post('/free/like',{
                boardLikeCheck,
                boardNo,
                memberId,
            })
            .then(({data})=> {
                if(data.result != "success") {
                    alert(data.message)
                }else{
                    this.$store.commit("setFreeLikeDown", data);
                }
            })
        },
    },
    updated() {
        var id = this.$cookies.get('memberId');
        var author = this.freeBoardWriter;
        if(id != author) { this.canEdit = false }
        else {this.canEdit = true }
    }

}
</script>

<style scoped>
.u_likeit{
    display: flex;
    justify-content: center;
    border-top:0.3px solid  #ccc;
    border-bottom: 0.3px solid #ccc;
    width: 80%;
    text-align: center;
    margin: auto;
    margin-top:100px;
    margin-bottom: 100px;
}
.u_likeit_list_name {
    margin: 0 -5px 6px;
    font-size: 12px;
    color: #999;
    line-height: 14px;
}
.u_likeit_list_count {
    font-size: 15px;
    color: #000;
    line-height: 14px;
    font-weight: normal;
}
.u_likeit_list_count {
    display: block;
    text-align: center;
    padding: 10px;
}
.u_likeit_list_name {
    display: block;
    font-size: 1.2rem;
}
.u_likeit > .u_likeit_layer .u_likeit_list {
    display: table-cell;
    padding: 50px;
}
.u_likeit > .u_likeit_layer .u_likeit_list_button {
    width: auto;
}
.u_likeit a, .u_likeit a:hover, .u_likeit a:visited {
    white-space: nowrap;
    text-decoration: none;
}
.faq-content{
    margin: 10px;
}
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
p {
    margin-bottom:0px
}
.like-button{
    cursor: pointer;
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
    
}

.inner-comment, .inner-comment .container {
    background-color: #ebebeb;
}

tbody tr {
    background-color: #ebebeb;
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