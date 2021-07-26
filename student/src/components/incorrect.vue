<template>
    <div class="app-container">
        <el-card>
            <el-row>
                <el-col :span="20" :offset="2">
                    <el-divider>成绩详情</el-divider>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="20" :offset="2">
                    <el-row>
                        <el-col :span="8">
                            <label class="el-form-item__label">考试名称: {{ examRecordDetail.examinationName }}</label>
                        </el-col>
                        <el-col :span="8">
                            <label class="el-form-item__label">考生ID: {{ examRecordDetail.userName }}</label>
                        </el-col>
                        <el-col :span="8">
                            <label class="el-form-item__label">考生姓名: {{ examRecordDetail.studentName }}</label>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <label class="el-form-item__label">总得分: {{ examRecordDetail.score }}</label>
                        </el-col>
                        <el-col :span="8">
                            <label class="el-form-item__label">考试结束时间: {{ examRecordDetail.endTime }}</label>
                        </el-col>
                        <el-col :span="8">
                            <label class="el-form-item__label">提交时间: {{ examRecordDetail.duration }}</label>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <label class="el-form-item__label">评卷人: 系统自动评分</label>
                        </el-col>
                        <el-col :span="8">
                            <label class="el-form-item__label">正确题数: </label><el-tag type="success" size="small" effect="dark">{{ examRecordDetail.correctNumber }}</el-tag>
                        </el-col>
                        <el-col :span="8">
                            <label class="el-form-item__label">错误题数: </label><el-tag type="danger" size="small" effect="dark">{{ examRecordDetail.incorrectNumber }}</el-tag>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="20" :offset="2">
                    <el-divider>错题列表</el-divider>
                    <el-table-column label="操作" align="center">
                        <el-button :type="viewquestionType | viewquestionTypeFilter(1)"  effect="dark" size="mini" @click="changeview()">{{ viewquestion | viewquestionFilter }}</el-button>
                    </el-table-column>
                </el-col><br><br><br>
            </el-row>
            <el-row>
                <el-col :span="20" :offset="2">
                    <div class="subject-content" v-for="option in answers" :key="option.questionId" v-show="option.answerCheck == 'F' || viewquestion == 0">
                        <div class="subject-content-star">
                            <div v-if="option.favorite">
                                <i class="el-icon-star-on" @click="toggle(option.questionId)" ></i>
                                <span style="color: #F9C93C;">取消收藏</span>
                            </div>
                            <div v-if="!option.favorite">
                                <i class="el-icon-star-off" @click="toggle(option.questionId)" ></i>
                                <span>收藏</span>                                
                            </div>
                        </div>
                        <div class="subject-content-options">
                            <div class="subject-title">
                                <div  v-if="option.questionType === 0">
                                    {{ option.questionId }}.
                                    <span class="subject-title-content" v-html="option.questionName"/>
                                    <span class="subject-title-content">&nbsp;({{score.single}})分</span><br>
                                    <br><span class="subject-title-content" v-html="option.opt"/><br><br>
                                </div>
                                <div  v-if="option.questionType === 1">
                                    {{ option.questionId }}.
                                    <span class="subject-title-content" v-html="option.questionName"/>
                                    <span class="subject-title-content">&nbsp;({{score.mult}})分</span><br>
                                    <br><span class="subject-title-content" v-html="option.opt"/><br><br>
                                </div>
                                <div  v-if="option.questionType === 2">
                                    {{ option.questionId }}.
                                    <span class="subject-title-content" v-html="option.questionName"/>
                                    <span class="subject-title-content">&nbsp;({{score.blank}})分</span><br>
                                    <br><span class="subject-title-content" v-html="option.opt"/><br><br>
                                </div>            
                            </div>
                            <span class="subject-title-number">正误： {{ option.answerCheck }}</span><br><br>
                            <span class="subject-title-number">你的答案： {{ option.stuAnswer }}</span><br>
                            <span class="subject-title-number">参考答案： {{ option.corAnswer }}</span><br><br>
                            <span>对题目还存在疑问？<a :href='[/discuss/+option.id]' style='color:#70A0F7;'>参与题目讨论</a></span>
                        </div>
                    </div>
                </el-col>
            </el-row>                                   
        </el-card>
    </div>
</template>
<script>
export default {
    data () {
        return {
            examid: 0,
            endtime: '',
            examRecordDetail: {},
            answers: [],
            newque: "",
            viewquestionType: 1,
            viewquestion: 1,
            score: {
                single: 0,
                mult: 0,
                blank: 0
            }
        }
    },
    created () {
        this.examid = this.$route.params && this.$route.params.id
        this.endtime = this.$route.query.time
        this.getRecordDetail()
    },
    methods: {
        toggle (index){
            this.answers[index-1].favorite = !this.answers[index-1].favorite
            console.log(this.answers[index-1].favorite)
            this.favorite(index - 1, this.answers[index-1].id)
        },
        favorite(index, thisid){
            var thisurl = ''
            if(this.answers[index].favorite==false)
                thisurl = 'http://localhost:8080/api/unfavorite?QuestionId='
            else if(this.answers[index].favorite==true)
                thisurl = 'http://localhost:8080/api/favorite?QuestionId='

            this.$ajax({
                method:'post',
                url: thisurl + thisid,
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                console.log(response)
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
            });
        },
        getRecordDetail () {
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/paperdetail?ExamId='+this.examid,
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                console.log(response)
                this.examRecordDetail = {
                        examinationName : response.data.paper.subjectName,
                        studentName : response.data.score[0].studentName,
                        userName : localStorage.getItem('username'),
                        score : response.data.score[0].score,
                        endTime : this.endtime,
                        duration : response.data.score[0].submit.replace(/-/g, '.').replace('T', ' ').replace('.000+0000', ''),
                        correctNumber : response.data.correct,
                        incorrectNumber : response.data.wrong
                }
                this.score.single = response.data.paper.singleScore / response.data.paper.singleNum
                this.score.mult = response.data.paper.multiScore / response.data.paper.multiNum
                this.score.blank = response.data.paper.blankScore / response.data.paper.blankNum
                for(var i=0; i<response.data.answer.length; i++){
                    this.answers.push({
                        questionName : response.data.answer[i].questionName.replace(/\?/g, '______'),
                        questionId : i + 1,
                        stuAnswer : response.data.answer[i].stuAnswer,
                        answerCheck : response.data.answer[i].answerCheck,
                        corAnswer : response.data.answer[i].corAnswer,
                        opt : response.data.answer[i].opt,
                        id : response.data.answer[i].questionId,
                        favorite: response.data.answer[i].favorite,
                        questionType: response.data.answer[i].questionType
                    })
                    if(this.answers[i].questionType == 2){
                        this.answers[i].questionName = this.answer[i].questionName.replace(/\?/g, '______')
                    }
                        
                }
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
                //localStorage.clear()
                //this.$router.push('/')
            });
        },
        changeview() {
            this.viewquestionType = 1 - this.viewquestionType
            this.viewquestion = 1 - this.viewquestion
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" rel="stylesheet/scss" scoped>
.el-icon-star-on{
    color: #ffe500;
    font-weight: bolder;
    font-size: 1.3rem;
}
.el-icon-star-off{
    font-weight: bolder;
    font-size: 1.2rem;
}
.incorrect-answer-gray-box {
    margin-top: 50px;
    margin-bottom: 50px;
    min-height: 200px;
}
.incorrect-answer-gray-box-title {
    text-align: center;
}
/* 题目 */
.subject-title {
    color: #333333;
    font-size: 16px;
    line-height: 22px;
    margin-bottom: 10px;
    padding-left: 20px;
    position: relative;
    .subject-title-number {
    position: absolute;
    left: -25px;
    top: 0;
    display: inline-block;
    width: 40px;
    line-height: 22px;
    text-align: right;
    }
}
/* 题目选项 */
.subject-option {
    padding-bottom: 10px;
    padding-left: 10px;
}
.score {
    margin: 20px;
}
.subject-content {
    background: #F6F7FA;
    border-radius: 4px;
    margin-bottom: 21px;
    padding: 12px 0 12px 22px;
    //position: relative;
    min-height: 240px;
    color: #666666;
    text-align: left;
}
.subject-content-star {
    position: relative;
    margin-left: 90%;
}
.subject-content-options{
    position: relative;
    margin-top: -20px;
    padding-top: 0px;
    width: 90%;
}

.incorrect {
    color: #F56C6C;
}
.right {
    color: #67C23A;
}

.score-gray-box {
    margin-top: 50px;
}
.score-gray-box-title {
    text-align: center;
}
.subject-options {
    margin: 0;
    padding: 0;
    list-style: none;
    > li {
    position: relative;
    font-size: 24px;
    label {
        word-break: break-all;
        display: block;
        line-height: 1.0;
        transition: color 0.4s;
        font-weight: normal;
    }
    /* 选项名称 */
    .subject-option-prefix {
        font-size: 16px;
        display: inline-block
    }
    }
}

</style>
