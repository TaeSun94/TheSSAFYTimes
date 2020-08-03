<template>
<div class="wrapper" style="margin-top:5%">
    <div class="row">
        <v-container class="elevation-5 col-lg-11">
            <v-card>
                <v-card-title>
                    팀매칭
                    <v-spacer></v-spacer>
                    <v-btn tile large depressed dark :to="{path: '/community/teamwrite'}">찾아요!</v-btn>
                </v-card-title>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field
                        v-model="search" append-icon="mdi-magnify"
                        label="검색" single-line hide-details
                    ></v-text-field>
                </v-card-title>
                <v-data-table
                    :headers="headers"
                    :items="tableData"
                    :search="search"
                    :page.sync="page"
                    :items-per-page="perPage"
                    hide-default-footer
                    :per-page="perPage"
                    @click:row="rowClicked"
                ></v-data-table>
                <div class="text-center pt-2">
                    <v-pagination v-model="page" :length="pageCount"></v-pagination>
                </div>
            </v-card>
        </v-container>
    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    name:"TeamList",
    data() {
        return {
            page:1,
            perPage: 25,
            pageLength: 0,
            pageCount: this.pageLength/this.perPage
        }
    },
    computed: {
        ...mapGetters(["teams"])
    },
    methods: {
        rowClicked(row) {
            this.$router.push({path: `/community/teamdetail/${row.no}`});
        }
    },
    created() {
        this.$store.dispatch("getTeams", '');
    }
}
</script>

<style>

</style>