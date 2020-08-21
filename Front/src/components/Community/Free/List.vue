<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="col-lg-7 col-sm-10 elevation-3">
                <v-card-title>
                    <div class="textfield">
                        <h1 class="m-5 mb-3"> 🧑‍🤝‍🧑 자유게시판 </h1>
                        <small class="ml-3">익명으로 이뤄진 자유게시판 입니다. 서로 의견을 나눠보세요 </small>
                    </div>
                    <v-spacer></v-spacer>
                    <v-btn v-if="login===false" disabled large tile depressed :to="{  path: '/community/freewrite'}">글쓰기!</v-btn>
                    <v-btn v-if="login===true"  tile large depressed dark :to="{  path: '/community/freewrite'}">글쓰기!</v-btn>
               
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
                    :items="frees"
                    :search="search"
                    :page.sync="page"
                    :items-per-page="perPage"
                    hide-default-footer
                    :per-page="perPage"
                    @click:row="handleClick"
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
                // { text: '글쓴날짜', value: "freeBoardDatetime", dataType: "Date"  },
                { text: 'Up 👍', value: 'freeBoardLike'},
                { text: 'Down 👎', value: 'freeBoardDislike' },
            ],
            login: false,
        }
    },
    computed: {
        ...mapGetters(["frees"]),
        ...mapGetters(["free_comments"]),
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
        var id = this.$cookies.get('memberId');
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
.container{
  background: #fff;
  border-radius: 20px;
}
.table{
    cursor: pointer;
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
.v-data-table-header tr th span{
    font-size: 0.9rem;
}
.writeBtn{
    border-radius: 10px;
    font-size: 0.8em;
}
.v-table tr:hover{
    cursor: pointer;
}
.v-input__icon{
    cursor: pointer;
}
</style>
