<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="col-lg-7 col-sm-10 elevation-5">
                <v-card-title>
                    <div class="textfield">
                        <h1 class="m-5 mb-3"> 🧑‍🤝‍🧑 프로그래밍 </h1>
                        <small class="ml-3">프로그래밍에 대한 질문과 답변을 나누는 공간입니다.</small>
                    </div>
                    <v-spacer></v-spacer>
                    <v-btn v-if="login===false" disabled large tile depressed :to="{ path: '/community/programwrite'}">글쓰기!</v-btn>
                    <v-btn v-if="login===true" tile large depressed dark :to="{ path: '/community/programwrite'}">글쓰기!</v-btn>
                </v-card-title>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field 
                        v-model="search" append-icon="mdi-magnify" 
                        label="검색" single-line hide-details
                    ></v-text-field>
                </v-card-title>
                <v-data-table
                    :headers="headers"
                    :items="programs"
                    
                    :search="search"
                    :page.sync="page"
                    :items-per-page="perPage"
                    hide-default-footer
                    :per-page="perPage"
                    @click:row="rowClicked"
                    
                    class="table"
                >
                </v-data-table>
                <div class="text-center pt-2">
                    <v-pagination v-model="page" :length="pageCount"></v-pagination>
                </div>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    name:"ProgrammList",
    data() {
        return {
            page:1,
            perPage: 25,
            search: "",
            headers: [
                { text: '제목', value: 'programBoardTitle' },
                { text: '글쓴이', value: 'memberId' },
                { text: '조회수', value: 'programBoardHit' },
                { text: '과정', value: 'programBoardTrack' },
                { text: 'Up 👍', value: 'programBoardLike' },
                { text: 'Down 👎', value: 'programBoardDislike' },
                
            ],
            login: false,
        }
    },
    computed: {
        ...mapGetters(["programs"]),
        pageLength() {
            return this.programs.length;
        },
        pageCount() {
            return Math.ceil(this.pageLength / 25);
        }
    },
    methods: {
        rowClicked(row) {
            this.$router.push({path: `/community/programdetail/${row.programBoardNo}`});
        },
    },
    created() {
        this.$store.dispatch("getPrograms", '/program/board');
        var id = this.$cookies.get('memberId');
        if(id==null){
            this.login = false;
            this.memberId = '';
        } else  {
            this.login = true;
            this.memberId = id;
        }
    },
}
</script>

<style>
.writeBtn{
    border-radius: 10px;
    font-size: 0.8em;
}
.table{
    cursor: pointer;
}
</style>