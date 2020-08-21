<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
                <div class="col-8">
                    <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                        <div>
                            <span class="heading">22</span>
                            <span class="description">❤️ 좋아요 한 글</span>
                        </div>
                        <div>
                            <span class="heading">10</span>
                            <span class="description">💛 좋아요 받은 글</span>
                        </div>
                        <div>
                            <span class="heading">89</span>
                            <span class="description">Comments</span>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card-profile-stats  mt-md-5">
                        <div>  
                            <span class="heading"> 누구님의 PROFILE </span>
                        </div>
                        <div>
                        <button> <router-link :to="{ name: 'EditProfile'}" tag="sideitem">🖊️ 추가정보 입력하기 !</router-link></button>
                        </div>
                    </div>
                </div>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
export default {
    name:"Profile",
}
</script>

<style>
.justify-content-center {
    justify-content: center !important;
}
.description{ 
    margin-bottom: 10px;
}

.card-profile-stats > div {
    text-align: center;
    margin-right: 1rem;
    padding: 0.875rem;
}
.card-profile-stats {
    padding: 1rem 0;
}

.card-profile-stats > div .heading {
    font-size: 1.1rem;
    font-weight: bold;
    display: block;
}
.heading-title {
    display: block; 
}
</style>