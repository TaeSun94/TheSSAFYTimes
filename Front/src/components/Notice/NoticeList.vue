<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="col-lg-7 col-sm-10 elevation-5">
            <vue-scroll-progress-bar height="0.3rem" backgroundColor="orange"/>
            <v-card>
              <v-card-title>
                  <div class="textfield">
                      <h1 class="m-5 mb-3"> 💡 공지사항 </h1>
                      <small class="ml-3">공지사항을 확인해보세요! </small>
                  </div>
                <v-spacer></v-spacer>
                <v-dialog v-if="isAdmin===true" v-model="dialog" persistent max-width="45%">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn tile large depressed dark v-bind="attrs" v-on="on">
                      글쓰기!
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="headline">공지사항 작성</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12" sm="6" md="6">
                            <v-text-field label="제목*" required v-model="title"></v-text-field>
                          </v-col>
                          <v-col cols="12">
                            <v-textarea solo clearable auto-grow label="내용*" v-model="content"/>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="orange darken-1" dark tile @click="createHandler()">쓰기</v-btn>
                      <v-btn color="grey darken-1" dark tile @click="dialog = false">닫기</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-card-title>
              <v-expansion-panels inset>
                <v-expansion-panel
                  v-for="item in this.notices"
                  :key="item.noticeNo"
                >
                  <v-expansion-panel-header v-slot="{ open }">
                    <v-row no-gutters class="noticeTitle">
                      <v-col cols="9" class="noticeTitle" >{{ item.noticeTitle }}</v-col>
                      <v-col cols="3" >
                        <v-fade-transition leave-absolute>
                          <span v-if="open">
                            {{$moment(item.noticeDatetime).format('YYYY-MM-DD hh:mm:ss')}}
                          </span>
                        </v-fade-transition>
                      </v-col>
                    </v-row>
                  </v-expansion-panel-header>
                  <v-expansion-panel-content>
                    {{item.noticeContent}}
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn v-if="isAdmin == true" text color="red" @click="deleteHandler(item.noticeNo)"> 삭제하기 </v-btn>
                    </v-card-actions>
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </v-expansion-panels>
            </v-card>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>
<script>
import { VueScrollProgressBar } from '@guillaumebriday/vue-scroll-progress-bar';
import { mapGetters } from "vuex";
import http from "@/http-common.js";

export default {
    name: "NoticeList",
    components: {
      VueScrollProgressBar
    },
    data () {
      return {
        isAdmin : false,
        dialog: false,
        title: '',
        content: '',
      }
    },
    computed: {
      ...mapGetters(["notices"]),
    },
    methods: {
      deleteHandler(no) {
        http.delete(`/notice/${no}`, {
          headers: {
            "Authorization" : this.$cookies.get("token")
          }
        }).then(({data})=> {
          if(data.result=="success") {
            this.$alert(data.message);
            location.reload();
          } else {
            this.$alert(data.message);
            return;
          }
        })
      },
      createHandler() {
        if(this.$cookies.isKey("token") != true) {
          this.$alert("로그인이 만료되었습니다. 다시 로그인 해주세요.");
          this.$cookies.remove("memberId");
          this.$cookies.remove("memberEmail");
          this.$router.push({path:'/'});
          location.reload();
          return;
        }
        http.post("/notice", {
          memberId: "",
          noticeContent: this.content,
          noticeTitle: this.title
        },
        {
          headers: {
            "Authorization" : this.$cookies.get("token")
          }
        }).then(({data})=> {
          if(data.result == "success") {
            location.reload();
          } else {
            this.$alert(data.message);
            return;
          }
        })
      }
    },
    created() {
      this.$store.dispatch("getNotices", '/notice');
      var id = this.$cookies.get('memberId');
      if(id != 'admin') {
        this.isAdmin = false;
        this.memberId = '';
      } else {
        this.isAdmin = true;
        this.memberId = id;
      }
    }
}
</script>

<style scoped>
.v-expansion-panel-header:hover {
  background-color: rgb(255, 179, 71);
}
.v-expansion-panel-content__wrap{
  padding-top:16px
}
.textfield {
    display: block;
    font-size: 0.8em;
    margin-block-start: 0.67em;
    margin-block-end: 0.67em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
    
}
.noticeTitle{
  font-size: 1.1rem;
  font-weight: 100;
}
.v-card__title{
  margin-bottom: 0px;
}

.container{
  background: #fff;
}
.v-sheet.v-card:not(.v-sheet--outlined) {
    box-shadow: none
}
</style>