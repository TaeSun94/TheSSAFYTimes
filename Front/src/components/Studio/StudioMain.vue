<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <tui-image-editor style="margin: 2% auto" ref="tuiImageEditor" :include-ui="useDefaultUI" :options="options"></tui-image-editor>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import {ImageEditor} from '@toast-ui/vue-image-editor';
import 'tui-image-editor/dist/svg/icon-a.svg';
import 'tui-image-editor/dist/svg/icon-b.svg';
import 'tui-image-editor/dist/svg/icon-c.svg';
import 'tui-image-editor/dist/svg/icon-d.svg';
import 'tui-image-editor/dist/tui-image-editor.css';
export default {
    name:"Studio",
    components: {
        'tui-image-editor': ImageEditor
    },
    data() {
        return {
            useDefaultUI: true, 
            options: {
                includeUI: {
                    uiSize: {
                        width: '1000px',
                        height: '700px'
                    },
                },
                cssMaxWidth: 700,
                cssMaxHeight: 500
            }
        }
    }
}
</script>

<style scoped>
</style>