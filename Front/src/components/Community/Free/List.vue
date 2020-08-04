<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-7">
            <v-card>
                <v-card-title>
                    <div class="textfield">
                        <h1 class="m-4"> 🧑‍🤝‍🧑 자유게시판 </h1>
                    </div>
                    <v-spacer></v-spacer>
                    <v-btn v-if="login===false"  disabled large tile depressed :to="{ path: '/community/freewrite'}" class="writeBtn" >글쓰기!</v-btn>
                    <v-btn v-if="login===true" tile large depressed dark  :to="{ path: '/community/freewrite'}" class="writeBtn" >글쓰기!</v-btn>
                </v-card-title>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field 
                        append-icon="mdi-magnify" 
                        label="검색" single-line hide-details
                    ></v-text-field>
                </v-card-title>
                <v-data-table
                    :headers="headers"
                    :items="frees"
                    :search="search"
                    :page.sync="page"
                    :items-per-page="perPage"
                    hide-default-footer
                    :per-page="perPage"
                    @click:row="handleClick"
                    class="table"
                >
                <template v-slot:item.freeBoardDatetime="{ item }">
                    <span>{{new Date(item.freeBoardDatetime).toLocaleString()}}</span>
                </template>
                </v-data-table>
                <div class="text-center pt-2">
                    <v-pagination v-model="page" :length="pageCount"></v-pagination>
                </div>
            </v-card>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    name:"FreeList",
    data() {
        return {
            page:1,
            perPage: 25,
            search: "",
            white: false,
            headers: [
                {
                    text: '글번호',
                    align: 'center',
                    sortable: false,
                    value: 'freeBoardNo',
                },
                { text: '조회수', value: 'freeBoardHit' },
                { text: '글제목', value: 'freeBoardTitle' },
                { text: '글쓴이', value: 'memberId' },
                { text: '글쓴날짜', value: 'freeBoardDatetime' },
                { text: '좋아요', value: 'freeBoardLikeCount' },
            ],
            login: false,
        }
    },
    computed: {
        ...mapGetters(["frees"]),
        pageLength() {
            return this.frees.length;
        },
        pageCount() {
            return Math.ceil(this.pageLength / 25);
            
        }
    },
    methods: {

        handleClick(value) {
            this.$router.push({path: `/community/freedetail/${value.freeBoardNo}`});
        },


    },
    created() {
       this.$store.dispatch("getFrees", '/free/board');
        var id = sessionStorage.getItem('memberId');
        if(id==null){
            this.login = false;
            this.memberId = '';
        } else  {
            this.login = true;
            this.memberId = id;
        }       

        }

}
</script>

<style>

.textfield {
    display: block;
    font-size: 0.8em;
    margin-block-start: 0.67em;
    margin-block-end: 0.67em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
}
.writeBtn{
    border-radius: 10px;
    font-size: 0.8em;
}
.v-input__icon{
    cursor: pointer;
}
</style>