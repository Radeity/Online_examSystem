<template>
  <el-dialog
    :visible.sync="dialogData.dialogVisible"
    width="60%"
    top="10vh"
  >
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
          <el-select v-model="bankName" placeholder="选择题库" filterable>
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
            @click.native="handleAdd"
          >增加</el-button>
        </el-col>
      </el-row>
      <div>
        <el-table v-loading="myTableData.loading" :data="myTableData.tableData|questionFilter(pagination)" @selection-change="myTableData.handleSelectionChange">
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
  </el-dialog>
</template>

<script>
import { getQuestionOfBank, getQuBankList } from '@/api/quBank'
import { getPointList } from '@/api/point'
import { addQuestionToPaper } from '@/api/paper'
import { Message } from 'element-ui'
import { countColumn } from 'codemirror'
export default {
  filters: {
    //  通过pageination返回考试列表的filter
    questionFilter(data, pagination) {
      const listQuery = pagination.listQuery
      let newData = [...data]
      const { pageNum, pageSize, questionName, questionType, pointId } = listQuery
      newData = newData.filter(item =>
        (
          !questionName ||
          item.questionName.toLowerCase().includes(questionName.toLowerCase())
        )
        /* &&
        (
          (!questionType && questionType !== 0) ||
          item.questionType === questionType
        ) &&
        (
          !pointId ||
          item.pointId === pointId
        )*/
      )
      /* newData.tableData = newData.filter((item, index) =>
        index < pageSize * pageNum &&
        index >= pageSize * (pageNum - 1))*/
      return newData
    }
  },
  props: {
    dialogData: {
      type: Object,
      required: true
    },
    subjectId: {
      type: Number,
      required: true
    },
    paperId: {
      type: Number,
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
          pointId: null
        },
        total: 10
      },
      msg : '',
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
      bankName: '',
      questionList: ['单项选择题', '多项选择题', '填空题'],
      pointList: [],
      multipleSelection: []
    }
  },
  computed: {
    whatchListquery() {
      return JSON.parse(JSON.stringify(this.pagination.listQuery))
    }
  },
  watch: {
    subjectId: {
      handler: function(newval, oldval) {
        console.log('refreshqu', newval)
        this.refresh(newval, this.subjectId)
      }
    },
    bankName: {
      handler: function(newval, oldval) {
        this.getQuestionOfBank(this.subjectId, newval)
      }
    },
    whatchListquery: {
      handler: function(newval, oldval) {
        console.log('val', newval, oldval)
        if (newval.pointId !== oldval.pointId || newval.questionType !== oldval.questionType) {
          console.log('siugaipagenum')
          this.pagination.listQuery.pageNum = 1
        }
        this.getQuestionOfBank(this.subjectId, this.bankName, newval)
      },
      deep: true
    }
  },
  created() {
    if (this.subjectId !== 0) { this.refresh(this.subjectId) }
  },
  methods: {
    async refresh(subjectId) {
      await this.getBankList(subjectId)
      this.bankName = this.bankList[0]
      console.log('bankName', this.bankName)
      this.getQuestionOfBank(subjectId, this.bankName)
    },
    getQuestionOfBank(subjectId, bankName, listQuery) {
      let params
      if (listQuery) {
        params = {
          bankName: bankName,
          pointId: listQuery.pointId,
          type: listQuery.questionType,
          pageNum: listQuery.pageNum,
          pageSize: listQuery.pageSize
        }
      } else {
        params = {
          bankName: bankName,
          pointId: this.pagination.listQuery.pointId,
          type: this.pagination.listQuery.questionType,
          pageNum: this.pagination.listQuery.pageNum,
          pageSize: this.pagination.listQuery.pageSize
        }
      }
      this.getPointList(subjectId)
      this.myTableData.loading = true
      getQuestionOfBank(params).then(response => {
        this.myTableData.tableData = response.data.questions.map(item => {
          item.questionTypeName = this.questionList[item.questionType]
          const temp = this.pointList.find(it => it.pointId && it.pointId === item.pointId)
          if (temp) { item.pointName = temp.pointName }
          return item
        })
        this.pagination.total = response.data.quesnum
        console.log('total', this.pagination.total)
        this.myTableData.loading = false
      }).catch((error) => {
        alert('获取题库的题目失败2')
        console.log(error)
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
      return getQuBankList({ subjectId: subjectId }).then(response => {
        this.bankList = response.data
        console.log('bandk', this.bankList)
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
    },
    handleAdd() {
      for (const item of this.multipleSelection) {
        const params = {
          questionId: item.questionId,
          paperId: this.paperId
        }
        addQuestionToPaper(params).then(response => {
          this.dialogData.dialogVisible = false
          this.$parent.refreshQuestionTable()
          //Message.info(response.data.msg)
        }).catch(error => {
          this.dialogData.dialogVisible = false
          console.log(error)
        })
      }

    }
  }
}
</script>
