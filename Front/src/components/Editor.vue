<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-5">
            <editor
            :value="editorText"
            :options="editorOptions"
            :html="editorHtml"
            :visible="editorVisible"
            previewStyle="vertical"
            height="500px"
            mode="wysiwyg"
            />
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { Editor } from '@toast-ui/vue-editor'

export default {
    components: {
        'editor': Editor
    },
    data() {
        return {
            editorText: 'This is initialValue.',
            editorOptions: {
                hideModeSwitch: true
            },
            editorHtml: '',
            editorVisible: true
        };
    },
};
</script>