import FooterBar from "../layouts/FooterBar";
import SideBar from "../layouts/SideBar";
import NavBar from "../layouts/NavBar";
import SearchBar from "../layouts/SearchBar";
export default {
    install(Vue){
        Vue.component(FooterBar.name, FooterBar);
        Vue.component(SideBar.name, SideBar);
        Vue.component(NavBar.name, NavBar);
        Vue.component(SearchBar.name, SearchBar);
    }
};