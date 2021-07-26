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
          <el-select v-model="pagination.listQuery.questionType" placeholder="选择题型" clearable filterable>
            <el-option v-for="(item,index) in questionList" :key="item" :label="item" :value="index" />
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.pointId" placeholder="选择知识点" clearable filterable>
            <el-option v-for="item in pointList" :key="item.pointId" :label="item.pointName" :value="item.pointId" />
          </el-select>
        </el-col>
        <el-col :span="10">
          <el-button
            icon="el-icon-check"
            type="primary"
            @click.native="handles.handleCreate"
          >增加</el-button>
          <el-button
            icon="el-icon-delete"
            type="danger"
            @click.native="handleDeletes"
          >删除</el-button>
          <el-button
            icon="el-icon-upload2"
            type="success"
            @click.native="handles.handleImport"
          >导入</el-button>
        </el-col>
      </el-row>
      <my-table :data="myTableData|examinationFilter(pagination)" />
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
import MyTable from '@/components/MyTable'
import { getQuestionOfBank, deleteQuesFromBank } from '@/api/quBank'
import { getPointList } from '@/api/point'
export default {
  components: { MyTable },
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
      /*
      newData.tableData = newData.tableData.filter((item, index) =>
        index < pageSize * pageNum &&
        index >= pageSize * (pageNum - 1))
        */
      // pagination.total = newData.tableData.length
      return newData
    }
  },
  props: {
    bankName: {
      type: String,
      required: true
    },
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
          pointId: null
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
        menuList: [
          {
            icon: 'el-icon-edit',
            word: '编辑',
            command: this.handles.handleEdit
          },
          {
            icon: 'el-icon-delete',
            word: '删除',
            command: this.handleDelete
          }
        ],
        loading: false,
        handleSelectionChange: (val) => {
          this.multipleSelection = val
        }
      },
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
        this.refresh(newval, this.bankName)
      }
    },
    bankName: {
      handler: function(newval, oldval) {
        console.log('rerere', newval)
        this.refresh(this.subjectId, newval)
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
    console.log('qucreated', this.subjectId)
    if (this.subjectId !== 0) { this.refresh(this.subjectId, this.bankName) }
  },
  methods: {
    refresh(subjectId, bankName) {
      this.getQuestionOfBank(subjectId, bankName)
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
          item.pointName = this.pointList.find(it => it.pointId === item.pointId).pointName
          return item
        })
        this.pagination.total = response.data.quesnum
        console.log('quesnum', this.pagination.total)
        this.myTableData.loading = false
      }).catch((error) => {
        console.log(error)
        alert('获取题库的题目失败')
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
    //  分页的pageSize变化触发的事件
    handleSizeChange(pageSize) {
      this.pagination.listQuery.pageSize = pageSize
    },
    //  分页的pageNum变化触发的事件
    handleCurrentChange(pageNum) {
      this.pagination.listQuery.pageNum = pageNum
    },
    handleDelete(row) {
      deleteQuesFromBank({ questionId: row.questionId }).then(response => {
        this.refresh(this.subjectId, this.bankName)
      }).catch(() => {
        alert('删除试题失败')
      })
    },
    handleDeletes() {
      for (const item of this.multipleSelection) {
        this.handleDelete(item)
      }
    }
  }
}
</script>
