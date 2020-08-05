<template>
<div class="wrapper" style="margin-top:10%">
    
    <div class="row">
        <v-container class="elevation-5 col-lg-7">
            <div id="app">
              <div class="textfield ml-5 mr-5 mb-5">
                <span class="label ml-3">참여 대회</span>
                <input type="text" class="textfield-input" v-model="freeBoardTitle" value="">
                <hr>
              </div>
              <div class="textfield ml-5 mr-5 mb-5">
                <span class="label ml-3">참여 팀원 수</span>
                <input type="text" class="textfield-input" v-model="freeBoardTitle" value="">
                <hr>
              </div>
              <div class="textfield ml-5 mr-5 mb-5 row">
                <span class="label ml-3">기술 스택</span>
                <input type="text" class="textfield-input" v-model="freeBoardTitle" value="">
                <hr>
              </div>
              <div class="textfield ml-5 row">
                <span class="label ml-3">상세설명</span>
              </div>
              <vue-editor id="editor" class="ml-5 mr-5" useCustomImageHandler @imageAdded="handleImageAdded" v-model="freeBoardContent"> </vue-editor>
            </div>
            <div class="text-right mt-3 mr-5">
              <v-btn @click="checkHandler"> 등록할래요 👌</v-btn>
              
            </div>
        </v-container>

    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
export default {
    name:"TeamWrite",
}
</script>

<style scoped>
.label {
  line-height: 38px;
  font-size: 14px;
  vertical-align: top;
  width: 80%;
  display: inline-block;
}

.textfield-input {
    display: block;
    width: 100%;
    padding: 11px 40px 12px 1.5rem;
    border-radius: 0;
    box-sizing: border-box;
    font: inherit;
    outline: none;
    box-shadow: none;
    font-size: 1.3rem;
}

.theme--light.v-application {
    background: #fff;
    font-family: 'Noto Sans KR', sans-serif
}
</style>