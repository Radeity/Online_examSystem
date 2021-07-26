<template>
  <div>
    <el-card>
      <div slot="header">
        <span>{{ bankName }}题库</span>
        <!--el-button style="float: right; padding: 3px 0" type="text">清空所有试题</el-button-->
      </div>
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input
            v-model="pagination.listQuery.questionName"
            placeholder="输入试题标题查询"
            clearable
          />
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.bankName" placeholder="选择题库" clearable filterable>
            <el-option v-for="item in bankList" :key="item" :label="item" :value="item" />
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.questionType" placeholder="选择题型" clearable filterable>
            <el-option v-for="(item,index) in questionList" :key="item" :label="item" :value="index" />
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.pointId" placeholder="选择知识点" clearable filterable>
            <el-option v-for="item in pointList" :key="item.pointId" :label="item.pointName" :value="item.pointId" />
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-button
            icon="el-icon-check"
            type="primary"
            @click.native="handles.handleCreate"
          >增加</el-button>
        </el-col>
      </el-row>
      <div>
        <el-table v-loading="myTableData.loading" :data="myTableData.tableData" @selection-change="myTableData.handleSelectionChange">
          <el-table-column type="selection" />
          <el-table-column
            v-for="(item,index) in myTableData.columnList"
            :key="index"
            :prop="item.prop"
            :label="item.label"
            show-overflow-tooltip
            sortable
          />
        </el-table>
      </div>
      <div>
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
      </div>
    </el-card>

  </div>
</template>
<script>
import { getQuestionOfBank, getQuBankList } from '@/api/quBank'
import { getPointList } from '@/api/point'
export default {
  filters: {
    //  通过pageination返回考试列表的filter
    examinationFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }
      const { pageNum, pageSize, questionName, questionType, pointId } = listQuery
      newData.tableData = newData.tableData.filter(item =>
        (
          !questionName ||
          item.questionName.toLowerCase().includes(questionName.toLowerCase())
        ) &&
        (
          !questionType ||
          item.questionType === questionType
        ) &&
        (
          !pointId ||
          item.pointId === pointId
        )
      )
      console.log('totaaallllllllllll', pagination.total)
      newData.tableData = newData.tableData.filter((item, index) =>
        index < pageSize * pageNum &&
        index >= pageSize * (pageNum - 1))
      return newData
    }
  },
  props: {
    subjectId: {
      type: Number,
      required: true
    },
    handles: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      pagination: {
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          questionName: null,
          questionType: null,
          pointId: null,
          bankName: ''
        },
        total: 10
      },
      myTableData: {
        columnList: [
          {
            prop: 'questionName',
            label: '试题标题'
          },
          {
            prop: 'questionTypeName',
            label: '题型'
          },
          {
            prop: 'opt',
            label: '选项'
          },
          {
            prop: 'questionAnswer',
            label: '答案'
          },
          {
            prop: 'pointName',
            label: '知识点'
          }
        ],
        tableData: [
          {
            questionName: '',
            questionType: '',
            knowledge: '',
            score: ''
          }
        ],
        loading: false,
        handleSelectionChange: (val) => {
          this.multipleSelection = val
        }
      },
      bankList: [],
      questionList: ['单项选择题', '多项选择题', '填空题'],
      pointList: [],
      multipleSelection: []
    }
  },
  watch: {
    subjectId: {
      handler: function(newval, oldval) {
        console.log('refreshqu', newval)
        this.refresh(newval)
      }
    }
  },
  created() {
    console.log('qucreated', this.subjectId)
    if (this.subjectId !== 0) { this.refresh(this.subjectId, this.bankName) }
  },
  methods: {
    refresh(subjectId) {
      this.getBankList(subjectId)
      this.pagination.listQuery.bankName = this.bankList[0]
      this.getQuestionOfBank(subjectId, this.pagination.listQuery.bankName)
    },
    getQuestionOfBank(subjectId) {
      const params = {
        bankName: this.bankName,
        pageNum: this.pagination.listQuery.pageNum,
        pageSize: this.pagination.listQuery.pageSize
      }
      this.getPointList(subjectId)
      this.myTableData.loading = true
      getQuestionOfBank(params).then(response => {
        this.myTableData.tableData = response.data.questions.map(item => {
          item.questionTypeName = this.questionList[item.questionType]
          item.pointName = this.pointList.find(it => it.pointId === item.pointId).pointName
          return item
        })
        this.pagination.total = response.quesnum
        this.myTableData.loading = false
      }).catch(() => {
        alert('获取题库的题目失败1')
        this.myTableData.loading = false
      })
    },
    getPointList(subjectId) {
      getPointList({ subjectId: subjectId }).then(response => {
        this.pointList = response.data
      }).catch(() => {
        alert('获取pointList失败')
      })
    },
    getBankList(subjectId) {
      getQuBankList({ subjectId: subjectId }).then(response => {
        this.bankList = response.data
      }).catch(() => {
        alert('获取题库名失败')
      })
    },
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
