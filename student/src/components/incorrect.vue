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
                            <label class="el-form-item__label">正确题数: </label><el-tag type="success" size="small" effect="dark">{{ correctNumber }}</el-tag>
                        </el-col>
                        <el-col :span="8">
                            <label class="el-form-item__label">错误题数: </label><el-tag type="danger" size="small" effect="dark">{{ incorrectNumber }}</el-tag>
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
                    <div class="subject-content" v-for="option in answers" :key="option.id" v-show="option.answerCheck == 'F' || viewquestion == 0">
                        <div class="subject-content-options">
                            <div class="subject-title">
                                {{ option.questionId }}.
                                    <span class="subject-title-content" v-html="option.questionName"/><br>
                                    <span class="subject-title-content" v-html="option.opt"/>
                                    <span class="subject-title-content">&nbsp;(5)分</span><br><br>
                            </div>
                            <span class="subject-title-number">正误： {{ option.answerCheck }}</span><br><br>
                            <span class="subject-title-number">你的答案： {{ option.stuAnswer }}</span><br>
                            <span class="subject-title-number">参考答案： {{ option.corAnswer }}</span><br>
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
            correctNumber: 0,
            incorrectNumber: 0,
            endtime: '',
            examRecordDetail: {},
            answers: [],
            viewquestionType: 1,
            viewquestion: 1
        }
    },
    created () {
        this.examid = this.$route.params && this.$route.params.id
        this.endtime = this.$route.query.time
        this.getRecordDetail()
    },
    methods: {
        getRecordDetail () {
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/paperdetail?ExamId='+this.examid,
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                this.examRecordDetail = {
                        examinationName : response.data.paper.subjectName,
                        studentName : response.data.score[0].studentName,
                        userName : localStorage.getItem('username'),
                        score : response.data.score[0].score,
                        endTime : this.endtime,
                        duration : response.data.score[0].submit.replace(/-/g, '.').replace('T', ' ').replace('.000+0000', ''),
                        correctNumber : 0,
                        inCorrectNumber : 0
                }
                for(var i=0; i<response.data.answer.length; i++){
                    this.answers.push({
                        questionName : response.data.answer[i].questionName,
                        questionId : i + 1,
                        stuAnswer : response.data.answer[i].stuAnswer,
                        answerCheck : response.data.answer[i].answerCheck,
                        corAnswer : response.data.answer[i].corAnswer,
                        opt : response.data.answer[i].opt
                    })
                    if(response.data.answer[i].answerCheck == 'T')
                        this.correctNumber = this.correctNumber + 1
                    else if(response.data.answer[i].answerCheck == 'F')
                        this.incorrectNumber = this.incorrectNumber + 1
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
