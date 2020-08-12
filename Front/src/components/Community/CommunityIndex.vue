<template>
<div class="wrapper" style="margin-top:8%">
    <div class="row">
        <v-container class="elevation-8 col-xl-5 col-lg-5">
            <v-card class="mx-auto">
                <v-card-text>
                <h3>프로그래밍</h3>
                </v-card-text>
                <div>
                    <v-simple-table>
                        <template v-slot:default>
                            <thead>
                                <tr>
                                    <th class="text-left">Subject</th>
                                    <th class="text-left">Author</th>
                                    <th class="text-left">Category</th>
                                    <th class="text-left">Hit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="item in tableData" :key="item.no" @click="rowClicked(item)">
                                    <td>{{item.subject}}</td>
                                    <td>{{item.author}}</td>
                                    <td>{{item.category}}</td>
                                    <td>{{item.hit}}</td>
                                </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                </div>
                <v-card-actions>
                <v-btn text :to="{ path: '/community/programlist'}"> More </v-btn>
                </v-card-actions>
            </v-card>
        </v-container>
        <v-container class="elevation-8 col-xl-5 col-lg-5">
            <v-card class="mx-auto">
                <v-card-text>
                <h3>자유게시판</h3>
                </v-card-text>
                <!-- insert simple table -->
                <v-card-actions>
                <v-btn text :to="{ path: '/community/freelist'}"> More </v-btn>
                </v-card-actions>
            </v-card>
        </v-container>
    </div>
    <div class="row mt-5">
        <v-container class="elevation-8 col-xl-11 col-lg-11">
            <v-card class="mx-auto">
                <v-card-text>
                <h3>팀매칭</h3>
                </v-card-text>
                <!-- insert simple table -->
                <v-card-actions>
                <v-btn text :to="{ path: '/community/teamlist'}"> More </v-btn>
                </v-card-actions>
            </v-card>
        </v-container>

    </div>
    <footer-bar></footer-bar>
</div>
</template>

<script>
export default {
    name: "CommunityIndex",
    data() {
        return {
            tableData: [
                {
                    no:1,
                    subject: '질문있습니당',
                    author: '옴팡이',
                    category: 'Vue',
                    hit: 0
                },
                {
                    no:2,
                    subject: '질문있습니당',
                    author: '옴팡이',
                    category: 'Vue',
                    hit: 0
                },
                {
                    no:3,
                    subject: '질문있습니당',
                    author: '옴팡이',
                    category: 'Vue',
                    hit: 0
                },
                {
                    no:4,
                    subject: '질문있습니당',
                    author: '옴팡이',
                    category: 'Vue',
                    hit: 0
                },
                {
                    no:5,
                    subject: '질문있습니당',
                    author: '옴팡이',
                    category: 'Vue',
                    hit: 0
                },
            ]
        }
    },
    methods: {
        rowClicked(row){
            this.$router.push(`/community/programdetail/${row.no}`);
        }
    }
}
</script>

<style>

</style>