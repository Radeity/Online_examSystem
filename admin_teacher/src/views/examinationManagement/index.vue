<template>
  <div class="app-container">
    <div>
      <el-input
        v-model="pagination.listQuery.name"
        placeholder="输入考试名称查询"
        style="width: 200px"
        class="filter-item"
      />
      <el-button
        class="filter-item"
        icon="el-icon-check"
        type="primary"
        @click.native="handleCreate"
      >增加</el-button>
      <el-button
        class="filter-item"
        icon="el-icon-delete"
        type="danger"
        @click.native="handleDeletes"
      >删除</el-button>
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
    <examination-dialog :dialog-data="examinationDialogData" />
  </div>
</template>

<script>
import { getExaminationList, examinationAddEdit, examinationDelete, examinationPublish, examintionUnpublish } from '@/api/examination'
import { getCourseList } from '@/api/paper'
import MyTable from '@/components/MyTable'
import ExaminationDialog from './components/ExaminationDialog'

export default {
  name: 'User',
  components: { MyTable, ExaminationDialog },
  filters: {
    //  通过pageination返回考试列表的filter
    examinationFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }
      const { pageNum, pageSize, name } = listQuery
      newData.tableData = newData.tableData.filter(item =>
        (
          !name ||
          item.examinationName.toLowerCase().includes(name.toLowerCase())
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
          name: ''
        },
        total: 0
      },
      myTableData: {
        columnList: [
          {
            prop: 'examName',
            label: '考试名称'
          },
          {
            prop: 'subjectName',
            label: '所属课程'
          },
          {
            prop: 'paperName',
            label: '试卷'
          },
          {
            prop: 'lastTime',
            label: '考试时间'
          },
          {
            prop: 'deadline',
            label: '结束时间'
          },
          {
            prop: 'state',
            label: '状态'
          }
        ],
        tableData: [
          {
            examName: '',
            subjectName: '',
            paperName: '',
            lastTime: null,
            deadline: null,
            state: null
          }
        ],
        menuList: [
          {
            icon: 'el-icon-edit',
            word: '编辑',
            command: this.handleEdit
          },
          {
            icon: 'el-icon-delete',
            word: '删除',
            command: this.handleDelete
          },
          {
            icon: 'el-icon-switch-button',
            word: '发布/撤销',
            command: this.handlePublic
          }
        ],
        loading: false,
        handleSelectionChange: this.handelSelectionChange
      },
      examinationDialogData: {
        dialogVisible: false,
        courseList: [],
        paperList: [],
        formData: {
          examName: '',
          subjectName: '',
          subjectId: '',
          paperName: '',
          paperId: '',
          lastTime: null,
          deadline: null,
          state: null
        },
        handleConfirm: this.handleConfirm
      },
      multipleSelection: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    refresh() {
      this.getList()
    },
    getList() {
      //  调用获取list的api
      getCourseList().then(response => {
        this.myTableData.loading = true
        this.courseList = response.data
        this.myTableData.tableData = []
        for (const item of this.courseList) {
          getExaminationList({ SubjectId: item.subjectId }).then(response => {
            // 显示加载动画
            this.myTableData.loading = true
            // 获取table的显示数据
            this.myTableData.tableData = this.myTableData.tableData.concat(
              response.data.map(item => {
                const stateList = { 'published': '发布', 'unpublished': '未发布' }
                item.state = stateList[item.state]
                return item
              })
            )
            // 获取list总条数
            this.pagination.total = this.myTableData.tableData.length
            // Just to simulate the time of the request
            setTimeout(() => {
              this.myTableData.loading = false
            }, 500)
          }).catch(() => {
            this.myTableData.Loading = false
          })
        }
      }).catch(() => {
        alert('获取课程列表失败')
      })
    },
    //  获取courseList
    getcourseList() {
      getCourseList().then(response => {
        this.examinationDialogData.courseList = response.data
        console.log('sourse', this.courseList)
      }).catch(() => {
        alert('获取courseList失败')
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
    //  编辑考试信息
    handleEdit(row) {
      this.examinationDialogData.formData = row
      this.getcourseList()
      this.examinationDialogData.dialogVisible = true
    },
    //  创建考试
    handleCreate() {
      this.handleClean()
      this.getcourseList()
      this.examinationDialogData.dialogVisible = true
    },
    //  确定提交表单
    handleConfirm() {
      const stateList = { '发布': 'published', '未发布': 'unpublished' }
      this.examinationDialogData.formData.state = stateList[this.examinationDialogData.formData.state]
      examinationAddEdit({ 'exam': [this.examinationDialogData.formData] }).then(response => {
        this.examinationDialogData.dialogVisible = false
        this.getList()
      }).catch(() => {
        alert('修改或添加考试失败')
      })
    },
    //  初始化弹窗的表单
    handleClean() {
      this.examinationDialogData.formData = {
        examName: '',
        subjectName: '',
        subjectId: '',
        paperName: '',
        paperId: '',
        lastTime: null,
        deadline: null,
        state: null
      }
    },
    //  删除考试
    handleDelete(row) {
      examinationDelete({ ExamId: row.examId }).then(response => {
        this.getList()
      }).catch(() => {
        alert('删除考试失败')
      })
    },
    handelSelectionChange(val) {
      this.multipleSelection = val
    },
    handleDeletes() {
      for (const item of this.multipleSelection) {
        this.handleDelete(item)
      }
    },
    handlePublic(row) {
      if (row.state === '发布') {
        examintionUnpublish({ ExamId: row.examId }).then(response => {
          row.state = '未发布'
        }).catch(() => {
          alert('撤销考试失败')
        })
      } else {
        examinationPublish({ ExamId: row.examId }).then(response => {
          row.state = '发布'
        }).catch(() => {
          alert('发布考试失败')
        })
      }
    }
  }
}
</script>
