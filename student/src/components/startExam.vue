<template>
    <div class="subject-box">
        <el-row :gutter="30">
            <el-col :span="5">
                <div class="tool-bar">
                    <div class="time-remain">
                        剩余时间:
                        <div class="time">
                            <count-down v-on:start_callback="countDownS_cb(1)" v-on:end_callback="countDownE_cb(1)" :current-time="currentTime" :start-time="startTime" :end-time="endTime" :tip-text="'距离考试开始'" :tip-text-end="'距离考试结束'" :end-text="'考试结束'" :hourTxt="':'" :minutesTxt="':'" :secondsTxt="''"></count-down>
                        </div>  
                    </div>
                    <div class="current-progress">
                        当前进度: {{index}}/{{exam.question.length}}
                    </div>
                    <div class="answer-card">
                        <el-button type="text" icon="el-icon-date" @click="answerCard">答题卡</el-button>
                    </div>
                    <el-button type="success" icon="el-icon-date" @click="submitExam">提交</el-button>
                </div>
            </el-col>
            <el-col :span="18">
                <div class="subject-box-card" v-if="options">
                <div class="subject-exam-title">{{exam.examinationName}}（共{{exam.question.length}}题，合计{{exam.totalScore}}分）</div>
                
                <!--单选题-->
                <div  v-if="textnow.questionType === 0">
                    <div class="subject-title">
                        (单选) {{ index }}.
                            <span class="subject-title-content" v-html="textnow.questionName"/>
                            <span class="subject-title-content">&nbsp;({{score.single}})分</span>
                    </div>
                    <ul class="subject-options" v-for="option in options" :key="option.id">
                        <li class="subject-option">
                        <input class="toggle" type="checkbox" :checked="answernow === option[0]" @change="toggleOption(option[0])">
                        <label>
                            <span class="subject-option-prefix">{{ option }}&nbsp;</span>
                        </label>
                        </li>
                    </ul>
                </div>
                <!--多选题-->
                <div v-if="textnow.questionType === 1">
                    <div class="subject-title">
                        (多选) {{ index }}.
                            <span class="subject-title-content" v-html="textnow.questionName"/>
                            <span class="subject-title-content">&nbsp;({{score.mult}})分</span>
                    </div>
                    <ul class="subject-options" v-for="option1 in options" :key="option1.id">
                        <li class="subject-option">
                        <input class="toggle" type="checkbox" :checked="isChecked(option1[0])" @change="multtoggleOption(option1[0])">
                        <label>
                            <span class="subject-option-prefix">{{ option1 }}&nbsp;</span>
                        </label> 
                        </li>
                    </ul>
                </div>
                <!--填空题-->
                <div v-if="textnow.questionType === 2" class="subject-title">
                    {{ index }}.
                        <span class="subject-title-content" v-html="textnow.questionName"/>
                        <span class="subject-title-content">&nbsp;({{score.blank}})分</span>
                </div>
                <div v-if="textnow.questionType === 2" style="width:300px;margin-top: 20px;">
                    <el-input style="" v-model="answernow" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容"></el-input>
                </div>
                <div class="subject-buttons">
                    <el-button plain @click="toSubject(-2)">上一题</el-button>
                    <el-button plain @click="toSubject(-1)">下一题</el-button>
                    <el-button type="success" @click="submitExam">提交</el-button>
                </div>
                </div>
            </el-col>
        </el-row>
        <el-dialog title="答题卡" :visible.sync="dialogVisible" width="50%" top="10vh" center>
            <div class="answer-card-title" >{{exam.examinationName}}（共{{exam.question.length}}题，合计{{exam.totalScore}}分）</div>
            <el-row class="answer-card-content">
                <el-button plain style="width:100%; margin:0px" v-for="(value, index) in exam.question" :key="index" @click="toSubject(index)" >&nbsp;{{index + 1}}&nbsp;{{questate[index]}}</el-button>
            </el-row>
        </el-dialog>
    </div>
</template>
<script>
import CountDown from 'vue2-countdown'
    export default {
    components: {
        CountDown
    },
    data () {
        return {
            selected: '',
            exam: {
                examinationName: '',
                totalScore: 100,
                question: []
            },
            disableSubmit: true,
            dialogVisible: false,
            subjectIndex: 1,
            textnow: [],
            options: [],
            index: 1,
            answer: [],
            answernow: "",
            multanswernow: [],
            currentTime: 0,
            startTime: 0,
            endTime: 0,
            lastTime: '120',
            examid: '',
            score: {
                single: 0,
                mult: 0,
                blank: 0
            },
            questate: []
        }
    },
    created () {
        //获取当前url的param: examid
        this.examid = this.$route.params && this.$route.params.id
        this.getPaper()
        //答题卡时间倒计时参数设置
        this.startTime = (new Date()).getTime()
        this.currentTime = (new Date()).getTime()
        this.endTime = this.startTime + this.$route.query.time * 60 * 1000
    },
    methods: {
        submitExam() {
            this.toSubject(this.index - 1)
            let answer = []
            let an = []
            for(var i=0; i<this.exam.question.length; i++){
                if(this.answer[i] == undefined){
                    answer[i] = ''
                }
                else if(typeof this.answer[i] != "string"){
                    answer[i] = ''
                    for(var a=0; a<this.answer[i].length; a++){
                        answer[i] += this.answer[i][a]
                    }
                }
                else{
                    answer[i] = this.answer[i]
                }
                an.push({
                    "studentId": localStorage.getItem('studentid'),
                    "paperId": this.exam.question[i].paperId,
                    "questionId": this.exam.question[i].questionId,
                    "stuAnswer": answer[i],
                    "answerCheck": ''
                })
            }
            this.$ajax({
                method:'post',
                url:'http://localhost:8080/api/submit',
                data: JSON.stringify({"answer": an}),
                headers:{'Authorization':localStorage.getItem('authorization'),'Content-Type':'application/json'}
            }).then(response => {
                alert('你的考试得分是：'+response.data.score+'分！')
                this.$router.push('/exams')
            })
            .catch(function(error) {
                console.log('提交错误！')
                this.$router.push('/exams')
            });
        },
        getPaper(){
            this.$ajax({
                method:'get',
                    url:'http://localhost:8080/api/getPaper?ExamId='+this.examid,
                    headers:{'Authorization':localStorage.getItem('authorization')}
                }).then(response => {
                    var seq = []
                    for(var i=0;i<response.data.question.length; i++){
                        seq[i] = i
                    }
                    for(var i=0; i<response.data.question.length; i++){
　　　　　　                var j = Math.floor(Math.random()*10)
　　　　　　                var newa = seq[i]
　　　　　　                seq[i] = seq[j]
　　　　　　                seq[j] = newa
                    }
                    for(var i=0; i<response.data.question.length; i++){
                        this.exam.question.push({
                            questionName : response.data.question[seq[i]].questionName,
                            questionId : response.data.question[seq[i]].questionId,
                            questionType : response.data.question[seq[i]].questionType,
                            opt : response.data.question[seq[i]].opt,
                            paperId : response.data.question[seq[i]].paperId
                        })
                        console.log(this.exam.question[i].questionName)

                        this.questate[i] = "Not Seen"

                        if(this.exam.question[i].questionType == 2)
                            this.exam.question[i].questionName = this.exam.question[i].questionName.replace(/\?/g, '______')

                    }
                    this.exam.examinationName = response.data.paperId.paperName
                    localStorage.setItem('studentid', response.data.studentId)
                    //当前界面题目和选项设置
                    if(this.exam.question[0].questionType != 2)
                        this.options = this.exam.question[0].opt.split(", ")
                    this.textnow = this.exam.question[0]
                    this.score.single = response.data.paperId.singleScore / response.data.paperId.singleNum
                    this.score.mult = response.data.paperId.multiScore / response.data.paperId.multiNum
                    this.score.blank = response.data.paperId.blankScore / response.data.paperId.blankNum
                    this.exam.totalScore = response.data.paperId.blankScore + response.data.paperId.multiScore +response.data.paperId.singleScore
                })
                .catch(function(error) {
                    this.$message('请重新加载页面')
                    localStorage.clear()
                    this.$router.push('/exams')
                });
        },
        // 答题卡
        answerCard () {
            this.dialogVisible = true
        },
        // 跳转题目
        toSubject (index) {
            if(this.questate[this.index - 1] == "Not Seen")
                this.questate[this.index - 1] = "Not Answered"
            if(this.exam.question[this.index - 1].questionType == 2){
                this.answer[this.index - 1] = this.answernow
                if(this.answernow != ''){
                    this.questate[this.index - 1] = "Answered"
                }
            }
            if(index == -2){
                if(this.index == 1){
                    this.$message('已经是第一题了')
                }
                else{
                    this.index = this.index - 1
                    this.newQues()
                }
            }
            else if(index==-1){
                if(this.index == this.exam.question.length){
                    this.$message('已经是最后一题了')
                }
                else{
                    this.index = this.index + 1
                    this.newQues()
                }
            }
            else{
                this.index = index + 1
                this.newQues()
                this.dialogVisible = false
            }
        },
        //加载新的题目、选项和用户的选择
        newQues() {
            this.textnow = this.exam.question[this.index - 1]
            if(this.exam.question[this.index-1].questionType != 2)
                this.options = this.exam.question[this.index - 1].opt.split(", ")

            if(this.answer[this.index - 1] != undefined){
                if(this.exam.question[this.index-1].questionType == 1)
                    this.multanswernow = this.answer[this.index - 1]
                else
                    this.answernow = this.answer[this.index - 1]
            }
            else{
                if(this.exam.question[this.index-1].questionType == 1)
                    this.multanswernow = []
                else
                    this.answernow = ''
            }
        },
        // 选中选项
        toggleOption (answer) {
            if(answer != this.answernow){
                this.answernow = answer
                this.answer[this.index - 1] = answer
                this.questate[this.index - 1] = "Answered"
            }
            else{
                this.answernow = ''
                this.questate[this.index - 1] = "Not Answered"
                this.answer[this.index - 1] = ''
            }
        },
        // 多选选中选项
        multtoggleOption(answer) {
            if(this.multanswernow.indexOf(answer) == -1){
                this.multanswernow.push(answer[0])
                this.answer[this.index - 1] = this.multanswernow
                this.questate[this.index - 1] = "Answered"
            }
            else{
                this.multanswernow.splice(this.multanswernow.indexOf(answer),1)
                this.answer[this.index - 1] = this.multanswernow
            }
            if(this.multanswernow.length==0){
                this.questate[this.index - 1] = "Not Answered"
            }
        },
        isChecked (optionName) {
            return this.multanswernow.includes(optionName)
        },
        countDownS_cb: function (x) {
            this.$message('考试开始')
        },
        countDownE_cb: function (x) {
            this.$message('考试结束')
            this.disableSubmit = true
            this.submitExam()
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../assets/css/subject.scss";
.subject-box {
    margin-top: 50px;
    margin-left: 20px;
}
.subject-box-card {
    margin-bottom: 30px;
    min-height: 400px;
}
.subject-buttons {
    text-align: center;
}
.tool-bar {
    margin-left: 20px;
}
.time-remain .time {
    display: inline-block;
    font-size: 18px;
    line-height: 22px;
    color: #FF0000;
    font-weight: 400;
}

/* 答题卡 */
.answer-card-title {
    font-size: 13px;
    color: #3A3E51;
    line-height: 17px;
    padding: 10px 0;
}
.answer-card-split {
    width: 100%;
    border-bottom: 1px solid #E6E6E6;
}
.answer-card-content {
    padding-bottom: 10px;
    font-size: 0;
    margin-right: -15px;
    > button {
    margin-top: 5px;
    }
}
</style>
