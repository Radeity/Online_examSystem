<template>
    <div class="my-content-container">
        <el-row>
            <el-col :span="20" :offset="2">
                <el-table
                    :key="tableKey"
                    :data="examRecodeList"
                    :default-sort="{ prop: 'id', order: 'descending' }"
                    highlight-current-row
                    style="width: 100%;">
                    <el-table-column fixed label="题库名称" sortable prop="start_time" align="center"  width="200px">
                        <template slot-scope="scope">
                            <span>{{ scope.row.bankName }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="题目名称" align="center">
                        <template slot-scope="scope">
                            <span :title="scope.row.examinationName">{{ scope.row.examinationName }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="题目选项" min-width="90" align="center">
                        <template slot-scope="scope">
                            <span>{{ scope.row.opt }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="题目答案" align="center">
                        <template slot-scope="scope">
                            <span>{{ scope.row.type }}</span>
                        </template>
                    </el-table-column>
                    <!--<el-table-column label="状态" min-width="90" align="center">
                        <template slot-scope="scope">
                            <el-tag :type="scope.row.submitStatus  | simpleTagStatusFilter(2)">{{ scope.row.submitStatus | submitStatusFilter }}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="成绩" sortable prop="score" align="center" width="120px">
                        <template slot-scope="scope">
                            <span>{{ scope.row.score }}</span>
                        </template>
                    </el-table-column>-->
                    <el-table-column label="操作" align="center" width="70px">
                        <template slot-scope="scope">
                            <i v-if="scope.row.favorite" class="el-icon-star-on" @click="toggle(scope.$index)" ></i>
                            <i v-if="!scope.row.favorite" class="el-icon-star-off" @click="toggle(scope.$index)" ></i>
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
            examRecodeList: [
            ],
            tableKey: 0,
            star:'books-6-2',	//样式
        }
    },
    created () {
        this.getList()
    },
    methods: { 
        toggle (index){
            this.examRecodeList[index].favorite = !this.examRecodeList[index].favorite
            this.favorite(index, this.examRecodeList[index].questionId)
        },
        favorite(index, thisid){
            var thisurl = ''
            if(this.examRecodeList[index].favorite==false)
                thisurl = 'http://localhost:8080/api/unfavorite?QuestionId='
            else if(this.examRecodeList[index].favorite==true)
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
        // 加载考试记录
        getList () {
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/getfavorite',
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                console.log(response)
                for(var i=0; i<response.data.favorite.length; i++){
                    this.examRecodeList.push({
                        examinationName : response.data.favorite[i].questionName,
                        bankName : response.data.favorite[i].bankName,
                        questionId : response.data.favorite[i].questionId,
                        opt : response.data.favorite[i].opt,
                        type : response.data.favorite[i].corAnswer,
                        favorite: 1
                    })
                    /*var options = response.data.favorite[i].opt.split(", ")
                    switch(response.data.favorite[i].corAnswer) {
                        case 'A':
                            this.examRecodeList[i].type = options[0]
                            break;
                        case 'B':
                            this.examRecodeList[i].type = options[1]
                            break;
                        case 'C':
                            this.examRecodeList[i].type = options[2]
                            break;
                        case 'D':
                            this.examRecodeList[i].type = options[3]
                            break;
                        default:
                            this.examRecodeList[i].type = response.data.favorite[i].opt
                    }*/
                }
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
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
.el-icon-star-on{
    color: #ffe500;
    font-weight: bolder;
    font-size: 1.2rem;
}
.el-icon-star-off{
    font-weight: bolder;
    font-size: 1.2rem;
}
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
