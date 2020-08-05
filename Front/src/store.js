import Vue from "vue";
import Vuex from "vuex";
import http from "@/http-common";
import router from "./router";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        // login
        member: {},
        // program
        programs: [],
        program: {},
        articles:[],
        article:{},
        followings_count:'',
        followers_count:'',
        articles_count:'',
        profile:{},
        program_comments: [],

        // free
        frees: [],
        free: {
            data: {},
            message : "",
            result : "",
            status: "",

        },
        // comment
        free_comments: [],
    },
    getters: {
        // login
        member(state) {
            return state.member;
        },
        // program
        programs(state) {
            return state.programs;
        },
        program(state) {
            return state.program;
        },
        program_comments(state) {
            return state.program_comments;
        },
        articles(state){
            return state.articles;
        },
        article(state){
            return state.article;
        },
        profile(state){
            return state.profile;
        },
        frees(state) {
            return state.frees;
        },
        free(state) {
            return state.free;
        },
        free_comments(state) {
            return state.free_comments;
        },

    },
    mutations: {
        // login
        setMember(state, payload) {
            state.member = payload;
            sessionStorage.setItem('memberId', payload.member.memberId);
            sessionStorage.setItem('memberEmail', payload.member.memberEmail);
        },
        // program
        setPrograms(state, payload) {
            state.programs = payload;
        },
        setProgram(state, payload) {
            state.program = payload;
        },
        setProgramComments(state, payload) {
            state.program_comments = payload;
        },
        setProfile(state, payload){
            state.profile=payload.member;
        },

        //profile 등록 및 수정
        updateProfile(state, payload){
            console.log(payload);
            state.profile ={};
            if(payload.result === "success"){
                alert("프로필 등록 및 수정 완료");
                // alert(payload.message);
            }
            else{
                alert("프로필 등록 및 수정중 에러발생");
            }
            location.href='/profile';
        },
        //기사 관련
        insertArticle(state, payload){
            console.log(payload);
            state.article ={};
            if(payload.result ==='success'){
                alert("기사 등록 완료!");
                location.href='/profile';
            }
            else{
                alert("기사 등록중 오류 발생!");
                location.href='/profile';
            }
            
        },
        setMyArticles(state,payload){
            state.articles = payload.articleList;
        },
        // Free
        setFrees(state, payload){
            state.frees = payload;
            
        },
        setFree(state, payload){
            state.free = payload;
        },
        setFreeComments(state, payload){
            state.free_comments = payload;
        },

    },
    actions: {
        //login
        login(context, {memberEmail, memberPw}) {
            http.post('/account/signin',{
                memberEmail,
                memberPw
            })
            .then(({data})=> {
                console.log(data);
                if(data.result=='fail'){    
                    alert(data.message);
                    this.$router.push("/login");
                } else if(data.result=='notavailable'){
                    alert(data.message);
                    context.commit("setMember", data);
                    //이메일 인증페이지로 가면 됌
                    router.push("/EmailCheck");
                    location.reload();
                } else if(data.result=='success'){
                    alert(data.message);
                    context.commit("setMember", data);
                    router.push("/");
                    location.reload();
                }
            });
        },
        // program
        getPrograms(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setPrograms", data.list);
            });
        },
        getProgram(context, payload) {
            http.get(payload).then(({data}) => {
                console.log(data)
                context.commit("setProgram", data);
            });
        },
        getProgramComments(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setProgramComments", data.programCommentList);
            });
        },
        // 멤버확인할때
        getMember(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setMember", data);
            });
        },
        //profile
        modifyProfile(context){
            const path = this.state;
            console.log(path.profile.memberId);
            http.put(`/member`,{
                memberAddress: path.profile.memberAddress,
                memberClass: path.profile.memberClass,
                memberDesc: path.profile.memberDesc,
                memberFirstName: path.profile.memberFirstName,
                memberIntro: path.profile.memberIntro,
                memberLastName: path.profile.memberLastName,
                memberPhone: path.profile.memberPhone,
                memberRegion: path.profile.memberRegion,
                memberTrack: path.profile.memberTrack,
                memberUnit: path.profile.memberUnit,
                memberId: path.profile.memberId
            }).then(({data})=>{
                console.log(data);
                context.commit('updateProfile',data);
            });
        },
        getProfile(context, payload){
            http.get(`/member/${payload}`).then(({data})=>{
                console.log(data);
                context.commit('setProfile',data);
            });
        },
        writeArticle(context){
            const path = this.state;
            console.log(path.article.articleContent);
            console.log(path.article.articleTitle);
            console.log(path.article.articleType);
            http.post('/article',{
                articleContent: path.article.articleContent,
                articleTitle: path.article.articleTitle,
                articleType: 1,
                memberId: "tyzlddy"
            }).then(({data})=>{
                console.log(data);
                context.commit('insertArticle',data);
            });
        },
        getMyArticles(context, payload){
            http.get(`/article/${payload}`).then(({data})=>{
                context.commit('setMyArticles',data);
                console.log(data);
            });
        },
        getMemberX(context, payload){
            http.get(`/member/${payload}`).then(({data})=>{
                context.commit('setPrfile',data);
            });
        },
        //Free
        getFrees(context,payload) {
            http.get(payload).then(({data}) => {
                context.commit("setFrees", data.list)

            });
        },
        getFree(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setFree", data);
            })
        },
        freeCreate(context, {freeBoardTitle, freeBoardContent, memberId}) {
            http.post('/free/board',{
                freeBoardTitle,
                freeBoardContent,
                memberId,
            })
            .then(({data})=> {
                if(data.result=='success'){
                    context.commit("setFree", data);
                    location.href='/community/freelist';
                } else {
                    alert(data.message);
                    return;
                }
            })
        },
        deleteFree(context, payload) {
            http.delete(`/free/board/${payload}`).then(({data})=>{
                console.log(data)
                if (data.result === "success") {
                    location.href='/community/freelist';
                } else {
                    alert("삭제불가")
                }
            })
        },
        getFreeComments(context, payload){
            http.get(payload).then((({data})=>{
                context.commit("setFreeComments", data.list);
            }))
        }
    }
    
})
