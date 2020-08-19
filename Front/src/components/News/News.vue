<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-5 col-lg-5">
                <div v-if="articles.length">
                    <div class="item_card" v-for="(item, index) in articles" :key="index + '_articles'">
                        <v-container class="elevation-5">
                                <div slot="header" class="bg-white border-0">
                                    <div class="row align-items-center">
                                        <div class="col-8 ml-3">
                                            <h3>제목 : {{item.articleTitle}}</h3>
                                        </div>
                                        <div class="col-3" style="padding-top:20px">
                                            <h5>분야 : {{item.articleType}}</h5>
                                        </div>
                                    </div>
                                </div>
                                <hr class="my-4" />
                                <div>
                                    <p>{{item.articleContent}}</p>
                                </div>
                                <div class="text-right">
                                    <p style="font-size:12px">{{item.memberId}}, {{$moment(item.articleDatetime).format('YYYY-MM-DD hh:mm:ss a')}}</p>
                                </div>
                        </v-container>
                        <br>
                    </div>
                    <!-- infinite loading -->
                    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
                </div>
                <div v-else class="text-center">
                    <h3>등록된 기사가 없습니다.</h3>
                </div>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import InfiniteLoading from 'vue-infinite-loading';
import { mapGetters} from 'vuex';
import http from "@/http-common.js";
export default {
    name:"News",
    components:{
        InfiniteLoading,
    },
    data() {
        return {
            page: 1,
        }
    },
    created(){
        http.get(`/article/list/${this.page}`).then(({ data }) => {
            console.log("우어ㅗ오오어어앙",data);
            this.page += 1;
            this.$store.state.articles = data.list;
        });
    },
    computed:{
        ...mapGetters(['articles'])
    },
    methods:{
        infiniteHandler($state) {
            http.get(`/article/list/${this.page}`).then(({ data }) => {
                setTimeout(()=>{
                    if (data.list.length) {
                        this.page += 1;
                        for(var i = 0; i < data.list.length; i++)
                            this.$store.state.articles.push(data.list[i]);
                        $state.loaded();
                    } else {
                        $state.complete();
                    }
                },1000)
            });
        },
    }
}
</script>

<style>

</style>