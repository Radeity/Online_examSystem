<template>
  <div>
    <el-card>
      <div slot="header">
        <span>试题列表</span>
        <!--el-button style="float: right; padding: 3px 0" type="text">折叠所有</el-button-->
      </div>
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input
            v-model="pagination.listQuery.questionName"
            placeholder="输入试题标题查询"
          />
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.questionType" placeholder="选择题型" clearable>
            <el-option v-for="item in questionTypeList" :key="item" :label="item" :value="item" clearable filterable />
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.point" placeholder="选择知识点" clearable>
            <el-option v-for="item in pointList" :key="item.pointId" :label="item.pointName" :value="item.pointId" clearable filterable />
          </el-select>
        </el-col>
        <el-col :span="6">
          <el-button
            icon="el-icon-check"
            type="primary"
            @click.native="handles.handleQuCreate"
          >增加</el-button>
          <el-button
            icon="el-icon-delete"
            type="danger"
            @click.native="handleDeletes"
          >删除</el-button>
        </el-col>
      </el-row>
      <my-table :data="myTableData|questionFilter(pagination)" />
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
import { getQuestionList } from '@/api/question'
import { getPointList } from '@/api/point'
import { deleteQuestionFromPaper } from '@/api/paper'

export default {
  components: { MyTable },
  filters: {
    //  通过pageination返回考试列表的filter
    questionFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }
      const { pageNum, pageSize, questionName, questionType, point } = listQuery
      newData.tableData = newData.tableData.filter(item =>
        (
          !questionName ||
          item.questionName.toLowerCase().includes(questionName.toLowerCase())
        ) &&
        (
          (!questionType && questionType !== 0) ||
          item.questionType === questionType
        ) &&
        (
          !point ||
          item.pointId === point
        )
      )
      newData.tableData = newData.tableData.filter((item, index) =>
        index < pageSize * pageNum &&
        index >= pageSize * (pageNum - 1))
      return newData
    }
  },
  props: {
    paperId: {
      type: Number,
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
          questionType: null,
          questionName: '',
          point: ''
        },
        total: 0
      },
      myTableData: {
        columnList: [
          {
            prop: 'questionName',
            label: '试题标题'
          },
          {
            prop: 'questionType',
            label: '题型'
          },
          {
            prop: 'opt',
            label: '选项'
          },
          {
            prop: 'pointName',
            label: '知识点'
          },
          {
            prop: 'corAnswer',
            label: '正确答案'
          }
        ],
        tableData: [
          {
            questionName: '',
            questionType: '',
            pointId: '',
            corAnswer: null
          }
        ],
        menuList: [
          {
            icon: 'el-icon-delete',
            word: '删除',
            command: this.handleQuDelete
          }
        ],
        loading: false,
        handleSelectionChange: this.handleSelectionChange
      },
      questionTypeList: ['单项选择题', '多项选择题', '填空题'],
      pointList: [1, 2, 3],
      multipleSelection: []
    }
  },
  computed: {
    whatchListquery() {
      return JSON.parse(JSON.stringify(this.pagination.listQuery))
    }
  },
  watch: {
    paperId: {
      handler: function(newval, oldval) {
        this.refresh(newval, this.subjectId)
      }
    },
    subjectId: {
      handler: function(newval, oldval) {
        this.refresh(this.paperId, newval)
      }
    },
    whatchListquery: {
      handler: function(newval, oldval) {
        console.log('val', newval, oldval)
        if (newval.pointId !== oldval.pointId || newval.questionType !== oldval.questionType) {
          console.log('siugaipagenum')
          this.pagination.listQuery.pageNum = 1
        }
        this.getQuestionList(this.paperId, this.subjectId)
      },
      deep: true
    }
  },
  created() {
    if (this.paperId !== 0) {
      this.refresh(this.paperId, this.subjectId)
    }
  },
  methods: {
    refresh(paperId, subjectId) {
      this.getQuestionList(paperId, subjectId)
    },
    getQuestionList(paperId, subjectId, listQuery) {
      //  const params = { paperId: paperId, pageSize: this.pagination.listQuery.pageSize, pageNum: this.pagination.listQuery.pageNum }
      let params
      if (listQuery) {
        params = {
          paperId: paperId,
          pointId: listQuery.pointId,
          type: listQuery.questionType,
          pageNum: listQuery.pageNum,
          pageSize: listQuery.pageSize
        }
      } else {
        params = {
          paperId: paperId,
          pointId: this.pagination.listQuery.pointId,
          type: this.pagination.listQuery.questionType,
          pageNum: this.pagination.listQuery.pageNum,
          pageSize: this.pagination.listQuery.pageSize
        }
      }
      this.getPointList(subjectId)
      this.myTableData.loading = true
      getQuestionList(params).then(response => {
        this.myTableData.tableData = response.data.questions.map(item => {
          item.questionType = this.questionTypeList[item.questionType]
          console.log('poinstlist', this.pointList)
          const temp = this.pointList.find(it => it.pointId === item.pointId)
          if (temp) {
            item.pointName = temp.pointName
          }
          return item
        })
        console.log('data', this.myTableData.tableData)
        this.pagination.total = response.data.quesnum
        this.myTableData.loading = false
      }).catch((error) => {
        console.log(error)
        alert('获取题目列表失败')
        this.myTableData.loading = false
      })
    },
    getPointList(subjectId) {
      getPointList({ subjectId: subjectId }).then(response => {
        this.pointList = response.data
      }).catch(() => {
        console.log('subject', subjectId)
        alert('获取pointList失败')
      })
    },
    handleQuDelete(row) {
      const params = {
        questionId: row.questionId,
        paperId: this.paperId
      }
      deleteQuestionFromPaper(params).then(response => {
        this.refresh(this.paperId, this.subjectId)
      }).catch(() => {
        alert('删除试题失败')
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    handleDeletes() {
      for (const item of this.multipleSelection) {
        this.handleQuDelete(item)
      }
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
