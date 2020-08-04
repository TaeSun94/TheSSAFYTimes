<template>
    <div class="wrapper" style="margin-top:5%">
        <!-- <div>
            <SearchBar />
        </div> -->

        <div class="row">
            
            <v-container class="elevation-5 col-lg-7">
                <div id="app">
                    <div class="textfield">
                        <div v-html="this.free.freeBoard.freeBoardTitle" class="ml-4 textfield-input"></div>
                        <hr>
                        
                    </div>
                                    
                    <div class="text-right mr-5">

                        <small class="description">👀 조회수 {{ this.free.freeBoard.freeBoardHit }} /</small>
                        <small class="description"> SSAFY 3기 {{ this.free.freeBoard.memberId }} / </small>
                        <small class="description"> {{$moment(this.free.freeBoard.freeBoardDatetime).format('YYYY-MM-DD hh:mm:ss')}} </small>

                    </div>
                    <div v-html="this.free.freeBoard.freeBoardContent" class="inner">
                    </div>
                    <div class="delete text-right mr-5">
                        <v-btn rounded @click="deleteHandler" v-if="check"> 삭제 </v-btn>     
                    </div>
                    <div class="likeContent">
                        <h3 class="like ml-3 like-button" @click="likeButton" v-html="likeTrue"> </h3> 
                        <h3 class="like"> 이 글 좋아요</h3>
                        <h3 class="like"> {{ this.free.freeBoard.freeBoardLikeCount }}</h3>       
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
import { mapGetters } from 'vuex'
import http from "@/http-common.js"

export default {

    name: 'FreeDetail',
    data() {
        return {
            replys: [
                {
                    no:1,
                    content:'댓글입니다. 댓글입니다. 댓글입니다.댓다.댓글입니다.댓글입니다.',
                    memberid:'옴팡'
                }
            ],
          content: false,
          commentContent: true,
          likeControll: true,
          likeTrue:'❤️',
          check: false,
          memberId: '',
          commentInput: '',
        }
    },
    computed: {
        ...mapGetters(["free"]),
        ...mapGetters(["free_comments"])
    },
    created() {
        var id = sessionStorage.getItem('memberId');
        this.memberId = id
        this.$store.dispatch("getFree", `/free/board/${this.$route.params.no}`)
        this.$store.dispatch("getFreeComments", `/free/${this.$route.params.no}/comment?freeBoardNo=${this.$route.params.no}`)
        if(this.free.freeBoard.memberId==sessionStorage.getItem('memberId')){
            this.check = true
            console.log(this.check)
        } else  {
            this.check = false
        }     
    },
    methods: {
        commentShow() {
            this.content = !this.content
        },
        commentCreate() {
            http.post("/free/comment", {
                memberId : sessionStorage.getItem("memberId"),
                freeCommentContent: this.commentInput,
                freeBoardNo :  parseInt(`${this.$route.params.no}`)
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
        likeButton(){
            if (this.likeControll == true){
                console.log(this.likeControll)
                this.likeControll = false
                this.likeTrue = '🤍'
                this.free.freeBoard.freeBoardLikeCount--;
            }
            else {
                this.likeControll = true
                console.log(this.likeControll)
                this.likeTrue = '❤️'
                this.free.freeBoard.freeBoardLikeCount++;
            }
        },
        deleteHandler() {
            console.log('삭제')
            this.$store.dispatch("deleteFree", this.$route.params.no)
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