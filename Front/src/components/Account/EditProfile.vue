<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-5">
                <form>
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                </form>
        </v-container>
        <div class="col-4">
            <div class="card-profile-stats  mt-md-5">
                <div>  
                    <span class="heading"> 누구님의 PROFILE </span>
                </div>
                <div>
                    <button>등록완료!</button>
                </div>
            </div>
        </div>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
export default {
    name:"EditProfile",
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
.form-group input {
    border: 1px solid black;
    border-radius: 5px;
    margin: 10px;
    display: block;
    width:90%;
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