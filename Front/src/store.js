import Vue from "vue";
import Vuex from "vuex";
import http from "@/http-common";
import router from "./router";
Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        member: {},
        programs: [],
        program: {},
        articles:[],
        article:{},
        articles_count:'',
        profile:{},
        program_comments: [],
        frees: [],
        followings:[],
        following:{},
        article_types:[],
        tracks:[],
        units:[],
        projects:[],
        regions:[],
        skill_languages:[],
        free: {
            data: {},
            message : "",
            result : "",
            status: "",

        },
        free_comments: [],
        notices: [],
        teams: [],
        team: {},
        team_category: [],
        applys: [],
        my_apply_projects:[],
        my_confirm_projects:[],
    },
    getters: {
        member(state) {
            return state.member;
        },
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
        articleTypes(state){
            return state.article_types;
        },
        tracks(state){
            return state.tracks;
        },
        units(state){
            return state.units;
        },
        projects(state){
            
            return state.projects;
        },
        regions(state){
            return state.regions;
        },
        skillLanguages(state){
            return state.skill_languages;
        },
        followings(state){
            return state.followings;
        },
        free_comments(state) {
            return state.free_comments;
        },
        notices(state) {
            return state.notices;
        },
        like_free(state) {
            return state.free;
        },
        teams(state) {
            return state.teams;
        },
        team(state) {
            return state.team;
        },        
        team_category(state) {
            return state.team_category;
        },
        applys(state) {
            return state.applys;
        },
        my_apply_projects(state){
            return state.my_apply_projects;
        },
        my_confirm_projects(state){
            return state.my_confirm_projects;
        },
    },
    mutations: {
        setMember(state, payload) {
            state.member = payload;
            document.$cookies.set("memberId", payload.memberId);
        },
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
            state.profile=payload;
        },
        updateProfile(state, payload){
            if(payload.result === "success"){
                location.href=`/profile/${state.profile.memberId}`;
                state.profile ={};
            }
            else{
                alert("프로필 등록 및 수정중 에러발생");
                location.href=`/profile/${state.profile.memberId}`;
                state.profile ={};
            }
        },
        insertArticle(state, payload){
            if(payload.result ==='success'){
                location.href=`/profile/${state.profile.memberId}`;
                state.profile ={};
            }
            else{
                alert("기사 등록중 오류 발생!");
                location.href=`/profile/${state.profile.memberId}`;
                state.profile ={};
            }
        },
        setMyArticles(state,payload){
            state.articles = payload.list;
        },
        setFrees(state, payload){
            state.frees = payload;
        },
        setFree(state, payload){
            state.free = payload;
        },
        setArticleTypes(state,payload){
            state.article_types = payload;
        },
        setTracks(state,payload){
            state.tracks = payload;
        },
        setUnits(state,payload){
            state.units = payload;
        },
        setProjects(state,payload){
            state.projects = payload;
        },
        setRegions(state,payload){
            state.regions = payload;
        },
        setSkillLanguages(state,payload){
            state.skill_languages = payload;
        },
        setFollowings(state,payload){
            state.followings = payload;
        },
        setFreeComments(state, payload){
            state.free_comments = payload;
        },
        setNotices(state, payload) {
            state.notices = payload;
        },
        setFreeLike(state){
            state.free.data.freeBoardLike = ++state.free.data.freeBoardLike;
        },
        setFreeLikeDown(state){
            state.free.data.freeBoardDislike = ++state.free.data.freeBoardDislike;
        },
        setTeams(state, payload){
            state.teams = payload;
        },
        setTeam(state, payload){
            state.team = payload;
        },
        setTeamCategory(state,payload){
            state.team_category = payload;
        },
        setApplys(state, payload) {
            state.applys = payload;
        },
        setMyApplyProject(state, payload){
            state.my_apply_projects = payload;
        },
        setMyConfirmProject(state, payload){
            state.my_confirm_projects = payload;
        },
    },
    actions: {
        login(context, {memberEmail, memberPw}) {
            http.post('/account/signin',{
                memberEmail,
                memberPw
            })
            .then(({data})=> {
                if(data.result=='fail'){    
                    alert(data.message);
                    this.$router.push("/login");
                } else if(data.result=='notavailable'){
                    alert(data.message);
                    document.$cookies.set("memberEmail", memberEmail, "1D");
                    router.push("/EmailCheck");
                    location.reload();
                } else if(data.result=='success'){
                    alert(data.message);
                    context.commit("setMember", data.data);
                    router.push("/");
                    location.reload();
                }
            });
        },
        getPrograms(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setPrograms", data.list);
            });
        },
        getProgram(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setProgram", data.list);
            });
        },
        getProgramComments(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setProgramComments", data.list);
            });
        },
        getMember(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setMember", data);
            });
        },
        modifyProfile(context,payload){
            const fd = new FormData();
            const path = this.state;
            if(payload.length>0){
                fd.append('file', payload.pop())
                http.post(`/upload/uploadFile`,fd,{
                    headers:{
                        'Accept':'application/json',
                        'Content-Type':"multipart/form-data"
                    }
                }).then(({data})=>{
                    if(data.result === 'success'){
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
                            memberId: path.profile.memberId,
                            interestedList: path.profile.memberInterestedList,
                            skillList: path.profile.memberSkillList,
                            memberImgurl: data.data
                        }).then(({data})=>{
                            context.commit('updateProfile',data);
                        });
                    }
                })
            }
            else{
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
                    memberId: path.profile.memberId,
                    interestedList: path.profile.memberInterestedList,
                    skillList: path.profile.memberSkillList,
                    memberImgurl: path.profile.memberImgurl
                }).then(({data})=>{
                    context.commit('updateProfile',data);
                });
            }
        },
        getProfile(context, payload){
            http.get(`/member/${payload}`).then(({data})=>{
                context.commit('setProfile',data.data);
            });
        },
        writeArticle(context){
            const path = this.state;
            http.post('/article',{
                articleContent: path.article.articleContent,
                articleTitle: path.article.articleTitle,
                articleType: path.article.articleType,
                memberId: path.profile.memberId,
            }).then(({data})=>{
                context.commit('insertArticle',data);
            });
        },
        getMyArticles(context, payload){
            http.get(`/article/${payload}`).then(({data})=>{
                context.commit('setMyArticles',data);
            });
        },
        getMemberX(context, payload){
            http.get(`/member/${payload}`).then(({data})=>{
                context.commit('setPrfile',data);
            });
        },
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
        teamCreate(context, { teamBoardTitle, teamBoardContent, teamBoardFrontRemainCount, teamBoardBackRemainCount, teamBoardCategory, memberId, teamBoardEndDatetime }) {
            http.post('/team/board', {
                    "memberId": memberId,
                    "teamBoardBackRemainCount": teamBoardBackRemainCount,
                    "teamBoardCategory": teamBoardCategory,
                    "teamBoardContent": teamBoardContent,
                    "teamBoardEndDatetime": teamBoardEndDatetime,
                    "teamBoardFrontRemainCount": teamBoardFrontRemainCount,
                    "teamBoardTitle": teamBoardTitle
            })
        },

        teamUpdate(context, { boardTitle, boardContent, boardFrontRemainCount, boardBackRemainCount, boardCategory, boardEndDatetime, boardNo, memberId }) {
            http.post('/team/board', {
                "boardTitle":  boardTitle,
                "boardContent" : boardContent,
                "boardFrontRemainCount" : boardFrontRemainCount,
                "boardBackRemainCount": boardBackRemainCount,
                "boardCategory":boardCategory,
                "boardEndDatetime" : boardEndDatetime,
                "boardNo" : boardNo,
                "memberId" : memberId
            })
        },
        getArticleTypes(context){
            http.get(`/category/article`).then(({data})=>{
                context.commit('setArticleTypes', data.list);
            })
        },
        getTracks(context,payload){
            http.get(`/category/${payload}/track`).then(({data})=>{
                context.commit('setTracks', data.list);
            })
        },
        getUnits(context,payload){
            http.get(`/category/${payload}/unit`).then(({data})=>{
                context.commit('setUnits', data.list);
            })
        },
        getProjects(context){
            http.get(`/category/team`).then(({data})=>{
                context.commit('setProjects', data.list);
            })
        },
        getRegions(context){
            http.get(`/category/region`).then(({data})=>{
                context.commit('setRegions', data.list);
            })
        },
        getSkillLanguages(context){
            http.get(`/category/skill-language`).then(({data})=>{
                context.commit('setSkillLanguages', data.list);
            })
        },
        getFollowings(context,payload){
            http.get(`/follow/${payload}/ing`).then(({data})=>{
                context.commit('setFollowings',data.list);
            })
        },
        deleteFree(context, payload) {
            http.delete(`/free/board/${payload}`).then(({data})=>{
                if (data.result === "success") {
                    location.href='/community/freelist';
                } else {
                    alert("삭제불가")
                }
            })
        },
        getFreeComments(context, payload){
            http.get(payload).then(({data})=>{
                context.commit("setFreeComments", data.list);
            })
        },
        getNotices(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setNotices", data.list);
            })
        },
        addFollowing(context,payload){
            const path = this.state;
            http.post('/follow',{
                "memberIdFrom": payload,
                "memberIdTo": path.profile.memberId
              }).then(({data})=>{
                if(data.result==="success"){
                    location.href=`/profile/${path.profile.memberId}`;
                }
                else{
                    alert("팔로우시 문제 발생");
                    location.href=`/profile/${path.profile.memberId}`;
                }
            })
        },
        freeLike(context, { boardLikeCheck, boardNo, memberId}) {
            http.post('/free/like',{
                boardLikeCheck,
                boardNo,
                memberId,
            })
        },
        getProfileMod(context, payload){
            http.get(`/member/mod/${payload}`).then(({data})=>{
                context.commit('setProfile',data.data);
            });
        },
        getTeams(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setTeams", data.list);
            });
        },
        getTeam(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setTeam", data.data);
            });
        },
        getApplys(context, payload) {
            http.get(payload).then(({data}) => {
                context.commit("setApplys", data.list);
            });
        },
        delFollowing(context, payload){
            const path = this.state;
            const id = path.profile.memberId;
            http.post(`/unfollow`,{
                "memberIdFrom": payload,
                "memberIdTo": id
              }).then(({data})=>{
                if(data.result==="success"){
                    location.href=`/profile/${path.profile.memberId}`;
                }
                else{
                    alert("언팔로우시 문제 발생");
                    location.href=`/profile/${path.profile.memberId}`;
                }
            })
        },
        getMyApplyProject(context, payload){
            http.get(`/team/board/apply/${payload}/date`).then(({data})=>{
                context.commit('setMyApplyProject',data.list);
            });
        },
        getMyConfirmProject(context, payload){
            http.get(`/team/board/apply/${payload}/status`).then(({data})=>{
                context.commit('setMyConfirmProject',data.list);
            });
        },
    }
})
