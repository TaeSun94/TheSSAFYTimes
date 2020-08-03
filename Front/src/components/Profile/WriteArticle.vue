<template>
<div data-app>
    <card shadow type="secondary">
        <div slot="header" class="bg-white border-0">
            <div class="row align-items-center">
                <div class="col-8">
                    <h3 class="mb-0">기사 작성</h3>
                </div>
                <div class="col-4 text-right">
                    <a href="#!" class="btn btn-sm btn-primary">등록하기</a>
                </div>
            </div>
        </div>
        <template>
            <form @submit.prevent>
                <h6 class="heading-small text-muted mb-4">기사 작성</h6>
                <div class="pl-lg-4">
                    <div class="row">
                        <div class="col-md-8">
                            <base-input alternative=""
                                label="기사 제목"
                                placeholder="Article Subject"
                                input-classes="form-control-alternative"
                                v-model="model.address"
                            />
                        </div>
                        <div class="col-md-4">
                            <label class="form-control-label">기사 분류</label>
                            <v-select v-model="selected" :options="options" attach chips label="기사 분류" multiple></v-select>
                        </div>
                    </div> 
                </div>
                <br>
                <div class="pl-lg-4">
                    <div class="form-group">
                        <base-input alternative=""
                                label="기사 내용">
                            <textarea rows="4" class="form-control form-control-alternative" placeholder="A few words about you ...">기사 내용이 들어갈 공간입니다.</textarea>
                        </base-input>
                    </div>
                </div> 
            </form>
        </template>
    </card>
</div>
</template>
<script>
export default {
    
}
</script>
