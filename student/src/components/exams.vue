<template>
    <div class="my-content-container">
        <el-row>
            <el-col :span="20" :offset="2">
                <el-table
                    :key="tableKey"
                    :data="examRecodeList"
                    :default-sort="{ prop: 'id', order: 'descending' }"
                    @cell-dblclick="handleDetail"
                    highlight-current-row
                    style="width: 100%;">
                    <el-table-column label="考试名称" align="center">
                        <template slot-scope="scope">
                            <span :title="scope.row.examinationName">{{ scope.row.examinationName }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="考试科目" min-width="90" align="center">
                        <template slot-scope="scope">
                            <span>{{ scope.row.type }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="考试截止时间" sortable prop="start_time" min-width="90" align="center">
                        <template slot-scope="scope">
                            <span>{{ scope.row.endTime }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="状态" min-width="90" align="center">
                        <template slot-scope="scope">
                            <el-tag :type="scope.row.submitStatus  | simpleTagStatusFilter(2)">{{ scope.row.submitStatus | submitStatusFilter }}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="成绩" sortable prop="score" align="center" width="120px">
                        <template slot-scope="scope">
                            <span>{{ scope.row.score }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" align="center">
                        <template slot-scope="scope">
                            <el-button :type="scope.row.submitStatus  | simpleTagStatusFilter(2)" size="mini" :disabled="scope.row.submitStatus==2" @click="handleDetail(scope.row.submitStatus, scope.row.paperid, scope.row.lasttime)">{{ scope.row.submitStatus | opTypeFilter }}</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
    </div>
</template>
<script>
export default {
    data () {
        return {
            examRecodeList: [],
            tableKey: 0,
        }
    },
    created () {
        this.getList()
    },
    methods: {
        // 加载考试记录
        getList () {
            console.log(localStorage.getItem('authorization'))
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/getExam',
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                for(var i=0; i<response.data.examinfo.length; i++){
                    this.examRecodeList.push({
                        examinationName : response.data.examinfo[i].examName,
                        type : response.data.examinfo[i].subjectName,
                        paperid : response.data.examinfo[i].examId,
                        endTime : response.data.examinfo[i].deadline.replace(/-/g, '.').replace('T', ' ').replace('.000+0000', ''),
                        submitStatus : 0,
                        score : response.data.state[i],
                        lasttime: response.data.examinfo[i].lastTime
                    })
                        console.log(this.examRecodeList[i].endTime)
                    if(this.examRecodeList[i].score == -1 || this.examRecodeList[i].score == 'unfinished'){
                        this.examRecodeList[i].score = ''
                        if(new Date(this.examRecodeList[i].endTime).getTime()<new Date().getTime())
                            this.examRecodeList[i].submitStatus = 2
                        else 
                            this.examRecodeList[i].submitStatus = 0
                    }
                    else{
                        this.examRecodeList[i].submitStatus = 1
                    }
                }
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
                //localStorage.clear()
                //this.$router.push('/')
            });
        },
        // 查看成绩详情 || 参加考试
        handleDetail (status, id, time) {
            if(status != 1){
                this.$router.push({
                    path: '/start/'+ id,
                    query:{ 
                        time: time
                    }
                })
            }
            else{
                this.$router.push({
                    path: '/incorrect/'+ id,
                    query:{ 
                        time: time
                    }
                })
            }
        }
    }
}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
.my-content-container {
    margin-top: 2rem;
    margin-left: 20px;
    margin-right: 20px;
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
    position: relative;
    min-height: 240px;
    color: #666666;
    text-align: left;
}
.correct {
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
</style>
