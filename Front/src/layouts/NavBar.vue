<template>
    <div class="wrapper">
        <div class="topnav mt-10">
            <v-btn
            icon
            x-large
            @click.stop="drawer = !drawer"
            class="ml-10"
            ><v-icon>mdi-menu</v-icon></v-btn>
        </div>
        <div class="topnav-centered"><button><img @click="to('/')" width="480" src="@/assets/times.png"/></button></div>

        <v-navigation-drawer
            v-model="drawer"
            absolute
            temporary
        >
        <br>
        <v-list-item v-if="login===false">
            <v-list-item-avatar style="margin-right:auto; margin-left:auto" size=70>
                <v-img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQDLFnxu6sy4oQgCw4yuNZeNq1p604iMXTq-Q&usqp=CAU"></v-img>
            </v-list-item-avatar>
        </v-list-item>
        <v-list-item v-if="login===false" style="margin-top:3%">
            <v-list-item-content>
                <v-btn rounded @click="to('/login')">로그인</v-btn>
            </v-list-item-content>
        </v-list-item>
        <v-list-item v-if="login===false">
            <v-list-item-content>
                <v-btn rounded @click="to('/join')">회원가입</v-btn>
            </v-list-item-content>
        </v-list-item>
            
        <v-list-item v-if="login===true">
            <v-list-item-avatar style="margin-right:auto; margin-left:auto" size=70>
                <v-img src="https://img1.daumcdn.net/thumb/S240x240/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99A4654C5C63B09028"></v-img>
            </v-list-item-avatar>
        </v-list-item>
        <v-list-item style="margin-top:3%" v-if="login===true"> 
            <v-list-item-content >
                <v-btn rounded @click="to('/profile')">{{memberId}}</v-btn>
            </v-list-item-content>
        </v-list-item>
        <v-list-item v-if="login===true">
            <v-list-item-content>
                <v-btn rounded @click="invalidate()">로그아웃</v-btn>
            </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>

        <v-list rounded style="text-align:center">
            <v-list-item @click="to('/notice')">
            <v-list-item-content>
                <v-list-item-title>💡  공지사항</v-list-item-title>
            </v-list-item-content>
            </v-list-item>

            <v-list-item @click="to('/community/programlist')">
            <v-list-item-content>
                <v-list-item-title>🙋  프로그래밍</v-list-item-title>
            </v-list-item-content>
            </v-list-item>

            <v-list-item @click="to('/community/freelist')">
            <v-list-item-content>
                <v-list-item-title>🙋  자유게시판</v-list-item-title>
            </v-list-item-content>
            </v-list-item>

            <v-list-item @click="to('/community/teamlist')">
            <v-list-item-content>
                <v-list-item-title>🙋  팀구해요 !</v-list-item-title>
            </v-list-item-content>
            </v-list-item>

            <v-list-item @click="to('/profile')">
            <v-list-item-content>
                <v-list-item-title>🧑  PROFILE</v-list-item-title>
            </v-list-item-content>
            </v-list-item>

            <v-list-item @click="to('/aboutus')">
            <v-list-item-content>
                <v-list-item-title>🎈 AboutUs</v-list-item-title>
            </v-list-item-content>
            </v-list-item>
        </v-list>
        </v-navigation-drawer>
    </div>
</template>

<script>
export default {
    name: "NavBar",
    data () {
      return {
        drawer: null,
        login: false,
        memberId: '',
        memberEmail: '',
      }
    },
    methods: {
        to(url){
            this.$router.push({path: url});
        },
        invalidate() {
            this.$cookies.remove("memberId");
            this.$cookies.remove("memberEmail");
            this.$router.push({path:'/'});
            alert("로그아웃 되었습니다.");
            location.reload();
            this.$router.push('/');
        }
    },
    created() {
        var id = this.$cookies.get("memberId");
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

<style scoped>
.topnav {
  position: relative;
  overflow: hidden;
}

.topnav-centered img {
  float: none;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  top: 100px;
}

.emoji {
    size: 30px;
}
h2 {
    font-size: 4rem;
    margin: 30px;
    margin-top: 30px;
    text-decoration: none;
}
</style>