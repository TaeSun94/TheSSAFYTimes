<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-7"> <!-- 기본틀 푸터까지 -->
            <vue-scroll-progress-bar height="0.3rem" backgroundColor="orange"/>
            <!-- <v-data-table
              :headers="headers"
              :items="desserts"
              :page.sync="page"
              :items-per-page="perPage"
              hide-default-footer
              align="center"
              justify="center"
              :per-page="5"
              @click:row="rowClicked"
              popout
            >
            </v-data-table> -->
            <v-card>
              <v-card-title>
                The SSAFY Time Notice
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
                    <v-row no-gutters>
                      <v-col cols="4">{{item.noticeTitle}}</v-col>
                      <v-col cols="8" class="text--secondary">
                        <v-fade-transition leave-absolute>
                          <span v-if="open">
                            {{item.noticeDatetime}}
                          </span>
                        </v-fade-transition>
                      </v-col>
                    </v-row>
                  </v-expansion-panel-header>
                  <v-expansion-panel-content>
                    {{item.noticeContent}}
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <!-- <v-btn v-if="isAdmin == true" text color="info"> 수정하기 </v-btn> -->
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
        // page:1,
        // perPage:1,
        isAdmin : false,
        dialog: false,
        title: '',
        content: '',
      }
    },
    computed: {
      ...mapGetters(["notices"]),
      // pageLength() {
      //   return this.notices.length;
      // },
      // pageCount() {
      //   return Math.ceil(this.pageLength / 1);
      // }
    },
    methods: {
      // rowClicked(row) {
      //   this.$router.push({path: `/notice/detail/${row.no}`});
      // }
      deleteHandler(no) {
        http.delete(`/notice/${no}`).then(({data})=> {
          if(data.result=="success") {
            alert(data.message);
            location.reload();
          } else {
            alert(data.message);
            return;
          }
        })
      },
      createHandler() {
        http.post("/notice", {
          member_id: sessionStorage.getItem("memberId"),
          noticeContent: this.content,
          noticeTitle: this.title
        }).then(({data})=> {
          if(data.result == "success") {
            location.reload();
          } else {
            alert(data.message);
            return;
          }
        })
      }
    },
    created() {
      this.$store.dispatch("getNotices", '/notice');
      var id = sessionStorage.getItem('memberId');
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

<style>
</style>