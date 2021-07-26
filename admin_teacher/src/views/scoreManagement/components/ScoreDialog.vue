<template>
  <el-dialog :visible.sync="dialogData.dialogVisible">
    <el-row>
      <el-card>
        <div slot="header">
          <span>成绩详情</span>
        </div>
        <el-row :gutter="12" type="flex" align="middle">
          <el-col :span="9">
            <el-row>
              <div style="margin:6px;font-size:17px">
                考试名称：{{ dialogData.examinationInfo.examName }}
              </div>
            </el-row>
            <el-row>
              <div style="margin:6px;font-size:17px">
                授课教师：{{ dialogData.examinationInfo.teacherName }}
              </div>
            </el-row>
            <el-row>
              <div style="margin:6px;font-size:17px">
                考生姓名：{{ dialogData.examinationInfo.studentName }}
              </div>
            </el-row>

            <el-row>
              <div style="margin:6px;font-size:17px">
                学院名称：{{ dialogData.examinationInfo.department }}
              </div>
            </el-row>
            <el-row>
              <div style="margin:6px;font-size:17px">
                班级名称：{{ dialogData.examinationInfo.adminclass }}
              </div>
            </el-row>
          </el-col>
          <el-col :span="3">
            <el-row>
              <div style="margin:4px;font-size:25px">成绩</div>
            </el-row>
            <el-row>
              <div style="margin:4px;font-size:25px">{{ dialogData.examinationInfo.score }}</div>
            </el-row>
          </el-col>
          <el-col :span="9">
            <pie-chart :data="pieData" />
          </el-col>
        </el-row>
      </el-card>
    </el-row>
    <el-row>
      <el-card>
        <el-table
          ref="multipleTable"
          :data="dialogData.questionList"
          :default-sort="{ prop: 'id', order: 'descending' }"
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column label="题目名称" min-width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.questionName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="类型" min-width="90">
            <template slot-scope="scope">
              <el-tag type="info" effect="dark" size="small">{{ scope.row.questionType |typeFilter }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="考生答案" min-width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.stuAnswer }}</span>
            </template>
          </el-table-column>
          <el-table-column label="参考答案" min-width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.corAnswer }}</span>
            </template>
          </el-table-column>
          <el-table-column label="得分" min-width="90">
            <template slot-scope="scope">
              <span>{{ scope.row.answerPoint }}</span>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          v-show="pagination.total>0"
          :current-page="pagination.listQuery.pageNum"
          :page-sizes="[10,20,30, 50]"
          :page-size="pagination.listQuery.pageSize"
          :total="pagination.total"
          background
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </el-card>
    </el-row>
  </el-dialog>
</template>

<script>
import PieChart from './PieChart'
export default {
  components: { PieChart },
  filters: {
    typeFilter(questionType) {
      const typeList = ['单选题', '多选题', '填空题']
      return typeList[questionType]
    }
  },
  props: {
    dialogData: {
      type: Object,
      required: true
    },
    pagination: {
      type: Object,
      required: true
      /**
      listQuery: {
        pageNum: 1,
        pageSize: 10
      },
      total: 0
      */
    },
    pieData: {
      type: Array,
      required: true
    }
  },
  methods: {
    //  分页的pageSize变化触发的事件
    handleSizeChange(pageSize) {
      this.pagination.listQuery.pageSize = pageSize
    },
    //  分页的pageNum变化触发的事件
    handleCurrentChange(pageNum) {
      this.pagination.listQuery.pageNum = pageNum
    }
  }
}
</script>
