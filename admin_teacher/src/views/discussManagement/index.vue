<template>
    <div class="my-content-container">
        <el-row>
            <el-col :span="20" :offset="2">
                <el-table
                    :key="tableKey"
                    :data="discussList"
                    :default-sort="{ prop: 'id', order: 'descending' }"
                    highlight-current-row
                    style="width: 100%;">
                    <el-table-column label="学生姓名" align="center">
                        <template slot-scope="scope">
                            <span :title="scope.row.stuName">{{ scope.row.stuName }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="留言时间" sortable prop="dtime" min-width="90" align="center">
                        <template slot-scope="scope">
                            <span>{{ scope.row.dtime }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="留言内容" min-width="90" align="center">
                        <template slot-scope="scope">
                            <span>{{ scope.row.content }}</span>
                        </template>
                    </el-table-column>                   
                    <el-table-column label="获赞数" sortable prop="thumb" align="center" width="120px">
                        <template slot-scope="scope">
                            <span>{{ scope.row.thumb }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" align="center">
                        <template slot-scope="scope">
                            <el-button type="danger" icon="el-icon-delete" @click="del(scope.row.discussId)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <div class="block" v-if="discussnum">
            <br>
         <el-pagination
            @current-change="handleCurrentChange"
            @size-change="handleSizeChange"
            :current-page.sync="currentpage"
            :page-sizes="[10, 20, 30]"
            :page-size="10"
            layout="sizes, prev, pager, next"
            :total="discussnum" style="text-align: center">
         </el-pagination>
        </div>
        <br><br>
    </div>
</template>
<script>
import request from '@/utils/request'
export default {
    data () {
        return {
            discussList: [],
            tableKey: 0,
            currentpage: 1,
            orderthumb: 0,
            discussnum: 0,
            pagesize: 10
        }
    },
    created () {
        this.getdiscussnum()
        this.getList()
    },
    methods: {
        handleCurrentChange(val){
          this.currentpage = val
          this.getList()
        },
        handleSizeChange(val){
          this.currentpage = 1
          this.pagesize = val
          this.getList()               
        },
        // 加载讨论数
        getdiscussnum(){
          request({
              method:'get',
              url:'http://localhost:8080/auth/admingetdiscussnum'
              //headers:{'Authorization':localStorage.getItem('authorization')}
          }).then(response => {
            this.discussnum = response.data.discussnum;
          });
        },

        // 加载讨论记录
        getList () {
            this.discussList.splice(0,this.discussList.length)
            request({
                method:'get',
                url:'http://localhost:8080/auth/admingetdiscuss?pageNum='+this.currentpage+'&pageSize='+this.pagesize
            }).then(response => {
                for(var i=0; i<response.data.discuss.length; i++){
                    this.discussList.push({
                        discussId : response.data.discuss[i].discussId,
                        stuName : response.data.discuss[i].stuName,
                        dtime : response.data.discuss[i].dtime,
                        content : response.data.discuss[i].content,
                        thumb : response.data.discuss[i].thumb
                    })
                }
            }).catch(function(error) {
                console.log('暂无更多数据！')
            });
        },
        // 删除讨论
        del (id) {
          request({
              method:'delete',
              url:'http://localhost:8080/auth/deldiscuss?discussId='+id
          }).then(response => {
            if(response.data == true){
              this.getList()
            }
            else{
              console.log('删除失败！')
            }         
          });
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
