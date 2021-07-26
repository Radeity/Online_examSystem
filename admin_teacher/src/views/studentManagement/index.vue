<template>
  <div class="app-container">
    <div>
      <el-input
        v-model="pagination.listQuery.studentName"
        placeholder="输入姓名查询"
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
    <student-table ref="studentTable" :data="myTableData|studentNameFilter(pagination)" />
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
    <student-dialog :dialog-data="studentDialogData" />
    <change-password-dialog :dialog-data="pwdDialogData" />
  </div>
</template>

<script>
import { getStudentList, studentAddEdit, studentDelete, statePermit, stateForbid } from '@/api/student'
import StudentTable from './components/StudentTable'
import StudentDialog from './components/StudentDialog'
import ChangePasswordDialog from './components/ChangePasswordDialog'
export default {
  name: 'User',
  components: { StudentTable, StudentDialog, ChangePasswordDialog },
  filters: {
    //  通过学生名称返回考试列表的filter
    studentNameFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }

      const { pageNum, pageSize, studentName } = listQuery

      newData.tableData = newData.tableData.filter(item =>
        (
          !studentName ||
          item.studentName.toLowerCase().includes(studentName.toLowerCase())
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
          studentName: ''
        },
        total: 0
      },
      myTableData: {
        columnList: [
          {
            prop: 'id',
            label: 'id'
          },
          {
            prop: 'studentName',
            label: '学生姓名'
          },
          {
            prop: 'studentNumber',
            label: '学号'
          },
          {
            prop: 'college',
            label: '学院'
          },
          {
            prop: 'className',
            label: '班级'
          },
          {
            prop: 'gender',
            label: '性别'
          }
        ],
        tableData: [
          {
            studentName: '王小虎',
            studentNumber: 12,
            className: '信息1701',
            gender: '男',
            state: '启用',
            college: '计算机科学与技术',
            passsword: '123456'
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
            word: '启用/禁用',
            command: this.handleChangeStatus
          },
          {
            icon: 'el-icon-key',
            word: '修改密码',
            command: this.handleChangePassword
          }
        ],
        loading: false,
        handleSelectionChange: this.handleSelectionChange
      },
      studentDialogData: {
        dialogVisible: false,
        formData: {
          id: '',
          studentName: '',
          studentNumber: '',
          college: '',
          className: '',
          state: '',
          gender: '',
          password: ''
        },
        handleConfirm: this.handleStudentConfirm
      },
      pwdDialogData: {
        dialogVisible: false,
        formData: {
          id: '',
          studentName: '',
          studentNumber: '',
          college: '',
          className: '',
          gender: '',
          password: '',
          state: ''
        },
        handleConfirm: this.handlePasswdConfirm
      },
      multipleSelection: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      //  调用获取list的api
      getStudentList().then(response => {
        // 显示加载动画
        this.myTableData.loading = true
        // 获取table的显示数据 并将status转换成string
        this.myTableData.tableData = response.data.map(item => {
          const stateList = ['禁用', '启用']
          const temp = {
            id: item.id,
            studentNumber: item.username,
            studentName: item.name,
            className: item.adminclass,
            gender: item.gender,
            state: stateList[item.state],
            college: item.department,
            password: item.password
          }
          return temp
        })
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
      this.studentDialogData.formData = {
        studentName: '',
        studentNumber: null,
        college: '',
        className: '',
        state: '',
        gender: '',
        password: ''
      }
      console.log(this.studentDialogData.formData)
    },
    //  创建新学生帐号
    handleCreate() {
      this.handleClean()
      this.studentDialogData.dialogVisible = true
    },
    //  编辑学生帐号
    handleEdit(row) {
      this.studentDialogData.formData = row
      this.studentDialogData.dialogVisible = true
    },
    // dialog确定修改或添加学生的事件
    handleStudentConfirm() {
      this.studentDialogData.dialogVisible = false
      const temp = this.studentDialogData.formData
      const stateList = { '禁用': 0, '启用': 1 }
      const data = {
        id: temp.id,
        username: temp.studentNumber,
        name: temp.studentName,
        adminclass: temp.className,
        gender: temp.gender,
        state: stateList[temp.state],
        department: temp.college,
        password: temp.password,
        role: 'STU'
      }
      studentAddEdit(data).then(response => {
        // 显示加载动画
        this.myTableData.loading = true
        // 重新加载列表
        this.getList()
        // Just to simulate the time of the request
        setTimeout(() => {
          this.myTableData.loading = false
        }, 500)
      }).catch(() => {
        this.myTableData.Loading = false
        alert('请求失败')
      })
    },
    //  删除学生帐号
    handleDelete(row) {
      const params = { username: row.studentName }
      studentDelete(params).then(response => {
        // 显示加载动画
        this.myTableData.loading = true
        // 重新加载列表
        this.getList()
        // Just to simulate the time of the request
        setTimeout(() => {
          this.myTableData.loading = false
        }, 500)
      }).catch(() => {
        this.myTableData.Loading = false
        alert('删除学生失败')
      })
    },
    //  dialog修改密码的事件
    handlePasswdConfirm() {
      this.pwdDialogData.dialogVisible = false
      console.log('form', this.pwdDialogData.formData)
      const temp = this.pwdDialogData.formData
      const stateList = { '禁用': 0, '启用': 1 }
      const data = {
        id: temp.id,
        username: temp.studentNumber,
        name: temp.studentName,
        adminclass: temp.className,
        gender: temp.gender,
        state: stateList[temp.state],
        department: temp.college,
        password: temp.password,
        role: 'STU'
      }
      studentAddEdit(data).then(response => {
        // 显示加载动画
        this.myTableData.loading = true
        // 重新加载列表
        this.getList()
        // Just to simulate the time of the request
        setTimeout(() => {
          this.myTableData.loading = false
        }, 500)
      }).catch(() => {
        this.myTableData.Loading = false
        alert('请求失败')
      })
    },
    // table选中行发生变化的事件
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    //  删除选中的学生帐号
    handleDeletes() {
      for (var item of this.multipleSelection) {
        this.handleDelete(item)
      }
    },
    //  修改帐号状态
    handleChangeStatus(row) {
      console.log(row)
      const data = {
        id: row.id
      }
      console.log(data)
      if (row.state === '启用') {
        statePermit(data).then(response => {
          row.state = '禁用'
        })
      } else if (row.state === '禁用') {
        stateForbid(data).then(response => {
          row.state = '启用'
        })
      }
    },
    //  修改密码
    handleChangePassword(row) {
      this.pwdDialogData.formData = row
      this.pwdDialogData.dialogVisible = true
    }
  }
}
</script>
