<template>
  <div class="app-container">
    <div>
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input
            v-model="pagination.listQuery.examName"
            placeholder="输入考试名称查询"
          />
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.subjectName" placeholder="选择课程" clearable @change="handeleSubjectChange">
            <el-option v-for="item in courseList" :key="item.subjectId" :label="item.subjectName" :value="item.subjectName" />
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="pagination.listQuery.teacherName" placeholder="请先选择课程再选授课老师" clearable>
            <el-option v-for="item in teacherList" :key="item.teacherId" :label="item.teacherName" :value="item.teacherName" clearable filterable />
          </el-select>
        </el-col>
        <el-col :span="2">
          <el-button
            icon="el-icon-download"
            type="success"
            @click.native="handleExport"
          >导出</el-button>
        </el-col>
      </el-row>
    </div>
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
    <score-dialog
      :dialog-data="scoreDialogData"
      :pagination="dialogPagination"
      :pie-data="pieData"
    />
  </div>
</template>

<script>
import { getScoreList, getTeacherList, scoreExport } from '@/api/score'
import { getCourseList, getPaperDetail } from '@/api/paper'
import MyTable from './components/MyTable'
import ScoreDialog from './components/ScoreDialog'
import { Message } from 'element-ui'

export default {
  name: 'User',
  components: { MyTable, ScoreDialog },
  filters: {
    //  通过考试名称,课程,授课老师返回成绩列表的filter
    examinationFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }
      const { pageNum, pageSize, examName, subjectName, teacherName } = listQuery
      newData.tableData = newData.tableData.filter(item => (
        item.score !== -1
      ) &&
      (
        !examName ||
          item.examName.toLowerCase().includes(examName.toLowerCase())
      ) && (
        !subjectName ||
          item.subjectName === subjectName
      ) && (
        !teacherName ||
          item.teacherName === teacherName
      )
      )
      pagination.total = newData.tableData.length
      newData.tableData = newData.tableData.filter((item, index) =>
        index < pageSize * pageNum &&
        index >= pageSize * (pageNum - 1))
      return newData
    }
  },
  data() {
    return {
      pagination: {
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          examName: '',
          subjectName: '',
          teacherName: ''
        },
        total: 0
      },
      dialogPagination: {
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          examName: '',
          subjectName: '',
          teacherName: ''
        },
        total: 10
      },
      myTableData: {
        columnList: [
          {
            prop: 'examName',
            label: '考试名称'
          },
          {
            prop: 'subjectName',
            label: '课程'
          },
          {
            prop: 'studentName',
            label: '姓名'
          },
          {
            prop: 'teacherName',
            label: '授课老师'
          },
          {
            prop: 'department',
            label: '学院'
          },
          {
            prop: 'className',
            label: '班级'
          },
          {
            prop: 'score',
            label: '成绩'
          },
          {
            prop: 'submit',
            label: '提交时间'
          }
        ],
        tableData: [
          {
            examName: '',
            subjectName: '',
            studentName: '',
            teacherName: '',
            department: '',
            className: '',
            score: null,
            submit: null
          }
        ],
        handleDetail: this.handleDetail,
        loading: false,
        handleSelectionChange: () => {}
      },
      scoreDialogData: {
        dialogVisible: false,
        examinationInfo: {
          examName: '',
          subjectName: '',
          studentName: '',
          className: ''
        },
        questionList: [{
          questionName: '',
          questionType: '',
          answer: '',
          corAnswer: '',
          score: ''
        }]
      },
      exportDialog: {
        dialogVisible: false,
        subjectName: '',
        examName: '',
        teacherName: ''
      },
      courseList: [],
      teacherList: [],
      pieData: []
    }
  },
  watch: {
    scoreDialogData: {
      handler: function() {
        console.log('change', this.scoreDialogData.dialogVisible)
      }
    }
  },
  created() {
    this.getList()
    this.getcourseList()
  },
  methods: {
    getList() {
      //  调用获取list的api
      const params = {
        pageNum: this.pagination.listQuery.pageNum,
        pageSize: this.pagination.listQuery.pageSize
      }
      getScoreList(params).then(response => {
        // 显示加载动画
        this.myTableData.loading = true
        // 获取table的显示数据 并将sex和status转换成string
        this.myTableData.tableData = response.data
        // Just to simulate the time of the request
        setTimeout(() => {
          this.myTableData.loading = false
        }, 500)
      }).catch(() => {
        this.myTableData.Loading = false
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
    //  初始化弹窗的表单
    handleClean() {
      this.examinationDialogData.formData = {
        examinationName: '',
        examinationType: '',
        course: '',
        totalScore: null,
        status: null,
        startTime: null
      }
    },
    //  查看成绩详情的触发事件
    handleDetail(row) {
      this.scoreDialogData.examinationInfo = row
      const params = {
        ExamId: row.examId,
        StudentId: row.studentId
      }
      this.scoreDialogData.dialogVisible = true
      getPaperDetail(params).then(response => {
        this.scoreDialogData.questionList = response.data.answer
        this.pieData = [{ value: response.data.correct, name: '正确' }, { value: response.data.wrong, name: '错误' }]
        this.scoreDialogData.examinationInfo = row
        this.dialogPagination.total = response.data.answer.length
      }).catch(() => {
        alert('获取成绩详情失败')
      })
    },
    //  获取courseList
    getcourseList() {
      getCourseList().then(response => {
        this.courseList = response.data
        console.log('courseList', this.courseList)
      }).catch(() => {
        alert('获取courseList失败')
      })
    },
    // subject值改变的事件
    handeleSubjectChange(val) {
      if (val) {
        const subjectId = this.courseList.find(item => item.subjectName === val).subjectId
        console.log('subject', subjectId)
        getTeacherList({ SubjectId: subjectId }).then(response => {
          this.teacherList = response.data.map(item => {
            return {
              teacherId: item.teacherId,
              teacherName: item.teacherName
            }
          })
        }).catch(() => {
          alert('获取teacherList失败')
        })
      } else {
        this.pagination.listQuery.teacherName = ''
        this.teacherList = []
      }
    },
    handleExport() {
      const data = this.pagination.listQuery
      if (data.subjectName) {
        scoreExport(data).then(response => {
          this.download(response.data)
        }).catch(() => {
          alert('导出失败')
        })
      } else {
        Message.error('必须选择导出的课程')
      }
    },
    download(data) {
      if (!data) {
        return
      }
      const temp = this.pagination.listQuery
      let filename = temp.subjectName
      if (temp.courseName) {
        filename = filename + '-' + temp.courseName
      }
      if (temp.teacherName) {
        filename = filename + '-' + temp.teacherName
      }
      filename = filename + '.xlsx'
      const url = window.URL.createObjectURL(new Blob([data]))
      const link = document.createElement('a')
      link.style.display = 'none'
      link.href = url
      link.setAttribute('download', filename)

      document.body.appendChild(link)
      link.click()
      window.URL.revokeObjectURL(url)
    }
  }
}
</script>
