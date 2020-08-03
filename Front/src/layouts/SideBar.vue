<template>
<div>
    <div class="sidebar-top">
    </div>
</div>
  
</template>

<script>
export default {
  name:'SideBar'
}
</script>

<style>

.sidebar-top {
    background: linear-gradient(-225deg,#ffffff 15px,transparent 0),linear-gradient(45deg,#ffffff 15px,transparent 0);
    background-position: left-bottom;
    background-repeat: repeat-y;
    background-size: 24px 24px;
    content: " ";
    display: block;
    position: absolute;
    left: 0;
    width: 2%;
    height: 100%;
    z-index: 7;
}
</style>