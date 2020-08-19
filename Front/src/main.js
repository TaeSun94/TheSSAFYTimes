import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from "./router";
import axios from "axios";
import store from "./store";
import globalcomponents from "./plugins/globalcomponents";
import 'v-markdown-editor/dist/v-markdown-editor.css';
import Editor from 'v-markdown-editor'
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import Vue2Editor from "vue2-editor";
import vueMoment from 'vue-moment';
import VueCookies from 'vue-cookies'
import VueQuill from 'vue-quill'
import VueSimpleAlert from "vue-simple-alert";
import VCalendar from 'v-calendar';
import VuetifyConfirm from 'vuetify-confirm'

Vue.use(VuetifyConfirm, { vuetify })
Vue.use(VueSimpleAlert);
Vue.use(VCalendar);
VueMarkdownEditor.use(vuepressTheme);
Vue.use(vueMoment)
Vue.use(Vue2Editor);
Vue.use(VueMarkdownEditor);
 // global register
Vue.use(Editor);
Vue.config.productionTip = false;
Vue.prototype.$Axios = axios;
Vue.use(globalcomponents);
Vue.use(VueCookies);
// use

 
Vue.use(VueQuill)

new Vue({
  vuetify,
  store,
  router,
  render: h => h(App)
}).$mount('#app')
