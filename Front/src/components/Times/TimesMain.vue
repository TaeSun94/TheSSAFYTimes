<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row" style="">
        <div class="container col-sm-10">
            <input type="checkbox" id="c1">
            <input type="checkbox" id="c2">
            <input type="checkbox" id="c3">
            <input type="checkbox" id="c4">
            <div id="cover">
                <img src="@/assets/slide1.jpg">
            </div>
            <div class="page-container" >
                <div class="page" id="p1">
                    <div class="back">
                        <img src="@/assets/slide3.jpg">
                        <label class="back-btn" for="c1">Back</label>
                    </div>
                    <div class="front">
                        <img src="@/assets/slide2.jpg">
                        <label class="next-btn" for="c1">Next</label>
                    </div>
                </div>
                <div class="page" id="p2">
                    <div class="back">
                        <img src="@/assets/slide5.jpg">
                        <label class="back-btn" for="c2">Back</label>
                    </div>
                    <div class="front">
                        <img src="@/assets/slide4.jpg">
                        <label class="next-btn" for="c2">Next</label>
                    </div>
                </div>
                <div class="page" id="p3">
                    <div class="back">
                        <img src="@/assets/slide7.jpg">
                        <label class="back-btn" for="c3">Back</label>
                    </div>
                    <div class="front">
                        <img src="@/assets/slide6.jpg">
                        <label class="next-btn" for="c3">Next</label>
                    </div>
                </div>
                <div class="page" id="p4">
                    <div class="back">
                        <img src="@/assets/slide9.jpg">
                        <label class="back-btn" for="c4">Back</label>
                    </div>
                    <div class="front">
                        <img src="@/assets/slide8.jpg">
                        <label class="next-btn" for="c4">Next</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
export default {
    name:"",
}
</script>

<style scoped>
body {
    margin: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

input {
    display: none;
}

img {
    width: 100%;
    height: 100%;
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #ebebeb;
}

#cover {
    width: 599px;
    height: 848.2px;
}

.page-container {
    width: 600px;
    height: 848.4px;
    position:relative;
    perspective: 1500px;
}

.page {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    transform-origin: left;
    transform-style: preserve-3d;
    transform: rotateY(0deg);
    transition: .6s;
    color: black;
}

.front, .back {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
}

.front {
    background-color: white;
    box-sizing: border-box;
    padding: 0 0px;
}

.back {
    transform: rotateY(180deg);
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
}

.next-btn {
    position: absolute;
    bottom: 13px;
    right: 13px;
    cursor: pointer;
}

.back-btn {
    position: absolute;
    bottom: 13px;
    left: 13px;
    cursor: pointer;
}

#p1 {
    z-index: 4;
    background-color: white;
}

#p2 {
    z-index: 3;
    background-color: white;
}

#p3 {
    z-index: 2;
    background-color: white;
}

#p4 {
    z-index: 1;
    background-color: white;
}

#c1:checked ~ .page-container #p1 {
    transform: rotateY(-180deg);
    z-index: 1;
}
#c2:checked ~ .page-container #p2 {
    transform: rotateY(-180deg);
    z-index: 2;
}
#c3:checked ~ .page-container #p3 {
    transform: rotateY(-180deg);
    z-index: 3;
}
#c4:checked ~ .page-container #p4 {
    transform: rotateY(-180deg);
    z-index: 4;
}
</style>
