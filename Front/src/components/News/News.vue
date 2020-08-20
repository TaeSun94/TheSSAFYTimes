<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-5 col-lg-7 col-sm-10">
                <v-card-title>
                    <div class="textfield">
                        <h1 class="m-5 mb-3"> 📰 뉴스 피드 </h1>
                        <small class="ml-3">SSAFY의 뉴스를 확인해보세요! </small>
                    </div>
                    <v-spacer></v-spacer>
                </v-card-title>
                <div v-if="articles.length">
                    <div class="item_card elevation-2" v-for="(item, index) in articles" :key="index + '_articles'">
                        <div slot="header" class="header bg-white border-0">
                            <div class="header_content">
                                <h3> {{item.articleTitle}}</h3>
                                <div class="text-right">
                                    <v-chip color="#FFB459">
                                        {{item.articleType}}
                                    </v-chip>
                                </div>
                            </div>
                        </div>
                        <hr/>
                        <div class="news_content">
                            <p>{{item.articleContent}}</p>
                        </div>
                        <div class="text-right news_content">
                            <p style="font-size:12px">{{item.memberId}}, {{$moment(item.articleDatetime).format('YYYY-MM-DD hh:mm:ss a')}}</p>
                        </div>
                        <br>
                    </div>
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

<style scoped>
.item_card{
    margin: 30px;
}
.news_content p {
    padding: 20px;
}
.header {
    padding: 20px;
}
.header_content{
    display: flex;
    justify-content: space-between;
}
.header_content h3{
    font-size: 1.5rem;
    font-weight: 700;
}
</style>