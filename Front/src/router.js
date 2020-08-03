import Vue from "vue";
import VueRouter from "vue-router";

import Notice from "@/components/Notice.vue";
import MainPage from "@/components/Main/MainPage.vue";
import NoticeList from "@/components/Notice/NoticeList.vue";
import Join from "@/components/Account/Join.vue";
import EmailCheck from "@/components/Account/EmailCheck.vue";
import Login from "@/components/Account/Login.vue";
import Community from "@/components/Community.vue";
import CommunityIndex from "@/components/Community/CommunityIndex.vue";
import NoticeDetail from "@/components/Notice/NoticeDetail.vue";
import Editor from "@/components/Editor/EditorPage.vue";
import NewsPage from "@/components/Community/NewsPage.vue";
import EditProfile from "@/components/Account/EditProfile";
import UserProfile from "@/components/Profile/UserProfile";
import ProfileEdit from "@/components/Profile/ProfileEdit";
import ProgramList from "@/components/Community/Programming/List";
import ProgramDetail from "@/components/Community/Programming/Detail";
import ProgramWrite from "@/components/Community/Programming/Write";
import TeamList from "@/components/Community/Team/TeamList";
import TeamWrite from "@/components/Community/Team/TeamWrite";
import TeamDetail from "@/components/Community/Team/TeamDetail";
import DetailPage from "@/components/Editor/DetailPage.vue";
import OtherProfile from "@/components/Profile/OtherProfile";
// import ProfileCard from "@/components/Profile/ProfileCard";
import AboutUs from "@/components/AboutUs.vue";
import AboutUsIndex from "@/components/AboutUs/AboutUsIndex.vue";
import FreeList from "@/components/Community/Free/List";
import FreeWrite from "@/components/Community/Free/Write";
import FreeDetail from "@/components/Community/Free/Detail";

Vue.use(VueRouter);

export default new VueRouter({
    mode: "history",
    routes: [
        {
            path:"/notice",
            name:"Notice",
            component:Notice,
            redirect: "/notice/list",
            children: [
                {
                    path: "list",
                    name: "NoticeList",
                    component: NoticeList
                },
                {
                    path: "detail/:no",
                    name: "NoticeDetail",
                    component: NoticeDetail
                }
            ]
        },
        {
            path:"/",
            name:"Main",
            component: MainPage,
        },
        {
            path:"/join",
            name:"Join",
            component:Join,
        },
        {
            path:"/login",
            name:"Login",
            component:Login,
        }
        ,
        {
            path:"/EmailCheck",
            name:"EmailCheck",
            component: EmailCheck
        },
        {
            path:"/community",
            name:"Community",
            component:Community,
            redirect: "/community/index",
            children: [
                {
                    path:"index",
                    name:"CommunityIndex",
                    component: CommunityIndex
                },
                {
                    path: "/community/NewsPage",
                    name: "NewsPage",
                    component: NewsPage
                },
                {
                    path:"programlist",
                    name:"ProgramList",
                    component: ProgramList,
                },
                {
                    path:"programdetail/:no",
                    name:"ProgramDetail",
                    component: ProgramDetail,
                },
                {
                    path:"programwrite",
                    name:"ProgramWrite",
                    component: ProgramWrite,
                },
                {
                    path:"teamlist",
                    name:"TeamList",
                    component: TeamList,
                },
                {
                    path:"teamwrite",
                    name:"TeamWrite",
                    component: TeamWrite
                },
                {
                    path:"teamdetail/:no",
                    name:"TeamDetail",
                    component: TeamDetail
                },
                {
                    path:"freelist",
                    name:"FreeList",
                    component: FreeList,
                },
                {
                    path:"freewrite",
                    name:"FreeWrite",
                    component: FreeWrite,
                },
                {
                    path:"freedetail/:no",
                    name:"FreeDetail",
                    component: FreeDetail,
                },


            ]
        },
        {
            path:"/editor",
            name:"Editor",
            component:Editor,
        },
    

        {
            path: "/edit",
            name: "EditProfile",
            component: EditProfile,
        },
        {
            path:'/profile',
            name:"Profile",
            component: UserProfile,
            // children:[
            //     {
            //         path:"ProfileCard/:memberId",
            //         name:"ProfileCard",
            //         component: ProfileCard
            //     }
            // ]
        },
        {
            path:'/profileEdit',
            name:'ProfileEdit',
            component: ProfileEdit,
        },
        {
            path:'/otherprofile',
            name:'OtherProfile',
            component: OtherProfile,
        },
        {
            path: "/detail",
            name: "Detail",
            component: DetailPage,
        },
        {
            path:"/aboutus",
            name:"AboutUs",
            component: AboutUs,
            redirect: "/aboutus/aboutusindex",
            children: [
                {
                    path:"aboutusindex",
                    name:"AboutUsIndex",
                    component:AboutUsIndex
                }
            ]
        }

    ]
});