<template>
  <div class="app-container">
    <!--从这开始到80行左右全是上面的导航栏-->
    <div class="header-area">
      <div class="clever-main-menu">
        <div class="classy-nav-container breakpoint-off">
          <nav id="cleverNav" class="classy-navbar justify-content-between">
            <div class="classy-menu">
              <div class="classynav">
                <!--搜索按钮-->
                <el-popover
                  placement="right"
                  width="400"
                  trigger="click"
                >
                  <!--点搜索按钮出来的东西-->
                  <el-table :data="exam">
                    <el-table-column width="150" property="date" label="考试名称">
                      <template slot-scope="scope">
                        <span>{{ scope.row.examName }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column width="100" property="name" label="试卷名称">
                      <template slot-scope="scope">
                        <span>{{ scope.row.paperName }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column width="300" property="address" label="操作">
                      <template slot-scope="scope">
                        <el-button type="success" size="mini" plain @click="view(scope.row.examId)">查看试卷</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                  <el-button slot="reference" type="success" plain @click="getexam()">选择试卷</el-button>
                </el-popover>
              </div>
            </div>
          </nav>
        </div>
      </div>
    </div>
    <div style="height: 10px" />
    <!--从这开始是界面-->
    <div v-if="examid">
      <el-card class="chart">
        <el-divider>总体分析</el-divider>
        <el-row>
          <el-col :span="7" :offset="4">
            <PieChart :data="pieData" />
          </el-col>
          <el-col :span="7" :offset="4">
            <LineChart :data="pointaccuracy" :x-axis-data="xAxisData" />
          </el-col>
        </el-row>
        <el-divider>试题详情</el-divider>
        <div class="content">
          <el-tabs tab-position="left">
            <el-tab-pane v-for="i in point" :key="i.pointid" :label="i.pointName">
              <el-col>
                <el-card>
                  <div v-for="option in question[i.pointid]" :key="option.id" class="subject-content">
                    <div class="subject-content-options">
                      <div class="subject-title">
                        {{ option.questionId }}.
                        <span class="subject-title-content" v-html="option.questionName" /><br>
                        <span class="subject-title-content" v-html="option.opt" />
                        <span class="subject-title-content">&nbsp;(5)分</span><br><br>
                      </div>
                      <span class="subject-title-number">高频错选答案： {{ point[i.pointid].wrong[option.questionId-1].answer }}</span><br>
                      <span class="subject-title-number">参考答案： {{ option.corAnswer }}</span><br><br><br>
                      正确率<el-progress style=" width:300px;" :percentage="Math.round(correct[i.pointid][option.questionId-1].rate*100)" />
                      高频错选率<el-progress style=" width:300px;" :percentage="Math.round((1-correct[i.pointid][option.questionId-1].rate)*100)" />
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-card>
      <!--        <el-tag-->
      <!--          v-for="(item,index) in xAxisData"-->
      <!--          :key="index"-->
      <!--          type="info"-->
      <!--          @click="goAnchor('#anchor-'+index)"-->
      <!--        >{{ item }}</el-tag>-->
      <!--      <el-card v-for="(i,index) in point" :id="'anchor-'+index" :key="i.pointid">-->
      <!--        <el-row>-->
      <!--          <el-col :span="20" :offset="2">-->
      <!--            <el-divider>知识点{{ (i.pointid+1)+'：'+i.pointName }}</el-divider>-->
      <!--          </el-col>-->
      <!--        </el-row>-->
      <!--        <el-row>-->
      <!--          <el-col :span="20" :offset="2">-->
      <!--            <div v-for="option in question[i.pointid]" :key="option.id" class="subject-content">-->
      <!--              <div class="subject-content-options">-->
      <!--                <div class="subject-title">-->
      <!--                  {{ option.questionId }}.-->
      <!--                  <span class="subject-title-content" v-html="option.questionName" /><br>-->
      <!--                  <span class="subject-title-content" v-html="option.opt" />-->
      <!--                  <span class="subject-title-content">&nbsp;(5)分</span><br><br>-->
      <!--                </div>-->
      <!--                <span class="subject-title-number">高频错选答案： {{ point[i.pointid].wrong[option.questionId-1].answer }}</span><br>-->
      <!--                <span class="subject-title-number">参考答案： {{ option.corAnswer }}</span><br><br><br>-->
      <!--                正确率<el-progress style=" width:300px;" :percentage="Math.round(correct[i.pointid][option.questionId-1].rate*100)" />-->
      <!--                高频错选率<el-progress style=" width:300px;" :percentage="Math.round((1-correct[i.pointid][option.questionId-1].rate)*100)" />-->
      <!--              </div>-->
      <!--            </div>-->
      <!--          </el-col>-->
      <!--        </el-row>-->
      <!--      </el-card>-->
      <!--      <el-backtop target=".page-component__scroll .el-scrollbar__wrap" />-->
    </div>
  </div>
</template>
<script>
import request from '@/utils/request'
import PieChart from './components/PieChart'
import LineChart from './components/LineChart'
export default {
  components: { LineChart, PieChart },
  data() {
    return {
      examid: 0,
      point: [],
      question: [],
      correct: [],
      pointaccuracy: [],
      exam: [],
      username: '',
      activeIndex: false,
      query: '',
      pieData: [],
      // lineData: [],
      xAxisData: [],
      activeBtn: 0
    }
  },
  methods: {
    goAnchor(selector) {
      this.$el.querySelector(selector).scrollIntoView()
    },
    getRecordDetail() {
      request({
        method: 'get',
        url: '/tea/analyzebyexam?examId=' + this.examid
      }).then(response => {
        this.pieData = []
        this.pointaccuracy = []
        this.xAxisData = []
        // console.log(response.data)
        for (var i = 0; i < response.data.pointnum; i++) {
          this.point.push({
            pointName: eval('response.data.point' + (i + 1) + '.pointName'),
            pointid: i,
            wrong: eval('response.data.wrong' + (i + 1))
          })
          this.xAxisData.push(eval('response.data.point' + (i + 1) + '.pointName'))
          this.pieData.push({
            name: eval('response.data.point' + (i + 1) + '.pointName'),
            value: eval('response.data.questions' + (i + 1) + '.length')
          })
          this.question.push(eval('response.data.questions' + (i + 1)))
          for (var a = 0; a < this.question[i].length; a++) {
            this.question[i][a].questionId = a + 1
          }
          this.correct.push(eval('response.data.correct' + (i + 1)))
          this.pointaccuracy.push(eval('response.data.pointaccuracy' + (i + 1)))
          for (var a = 0; a < this.correct[i].length; a++) {
            if (this.correct[i][a].rate === 'NaN') this.correct[i][a].rate = 0
          }
        }
      }).catch(function() {
          console.log('暂无更多数据！')
        })
    },
    getexam() {
      request({
        method: 'get',
        url: '/tea/getpublishedexam'
      }).then(response => {
        console.log(response.data)
        this.exam = response.data
      }).catch(function() {
        console.log('暂无更多数据！')
      })
    },
    logout() {
      localStorage.clear()
      location.reload()
    },
    view(value) {
      this.examid = value
      this.point = []
      this.getRecordDetail()
    }
  }
}
</script>

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
    position: relative;
    min-height: 120px;
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
