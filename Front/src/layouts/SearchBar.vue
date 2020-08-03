<template>
    <div class="searchBar">
        <form class="navbar-user-search-form ">
            <div class="innerbox">

                <div class="textfield">
                    <input class="textfield-input" type="search" id="help-search" placeholder="검색 키워드를 입력 해주세요!" name="search">
                </div>
                <button class="navbar-user-search-form-close ">
                    <v-icon>fas fa-search</v-icon>
                </button>
            </div>
        </form>
    </div>

</template>

<script>
export default {
  name:"SearchBar",
}
</script>

<style scoped>

.searchBar{
    margin: 30px;
}
.navbar-user-search-form {
    width: 100%;
    padding-top: 1rem;
    box-sizing: border-box;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    z-index: 8;
}
.textfield{
position: relative;
}
.search{
    display: inline;
}
.navbar-user-search-form input {
    padding-left: 56px;
    height: 52px;
    font-size: 1.125rem;
}

.navbar-user-search-form-close {
    width: 52px;
    height: 52px;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    left: 35rem;
    top: 0;
    z-index: 12121;
}
.navbar-user-search-form input {
    padding-left: 56px;
    height: 52px;
    font-size: 1.125rem;
}

.navbar-user-search-form .innerbox {
    max-width: 640px;
    margin: 0 auto;
    padding: 0 1rem;
    position: relative;
}
.textfield-input {
    display: block;
    width: 100%;
    padding: 11px 40px 12px 1.5rem;
    background: #f3f3f3;
    border: 0.5px solid #252525;
    border-radius: 20px;
    box-sizing: border-box;
    font: inherit;
    color: #252525;
    -webkit-transition: all .2s;
    transition: all .2s;
    outline: none;
    box-shadow: none;
}
</style>