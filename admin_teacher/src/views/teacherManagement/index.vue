<template>
  <div class="app-container">
    <div>
      <el-input
        v-model="pagination.listQuery.teacherName"
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
    <teacher-table :data="myTableData|teacherNameFilter(pagination)" />
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
    <teacher-dialog :dialog-data="teacherDialogData" />
    <change-password-dialog :dialog-data="pwdDialogData" />
  </div>
</template>

<script>

import { getTeacherList, teacherAddEdit, teacherDelete } from '@/api/teacher'
import { statePermit, stateForbid } from '@/api/student'
import TeacherTable from './components/TeacherTable'
import TeacherDialog from './components/TeacherDialog'
import ChangePasswordDialog from './components/ChangePasswordDialog'

export default {
  name: 'User',
  components: { TeacherTable, TeacherDialog, ChangePasswordDialog },
  filters: {
    //  通过教师名称返回考试列表的filter
    teacherNameFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }
      const { pageNum, pageSize, teacherName } = listQuery
      newData.tableData = newData.tableData.filter(item =>
        (
          !teacherName ||
          item.teacherName.toLowerCase().includes(teacherName.toLowerCase())
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
          teacherName: ''
        },
        total: 0
      },
      myTableData: {
        tableData: [
          {
            id: null,
            teacherName: '',
            teacherNumber: null,
            college: '',
            gender: '',
            state: ''
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
      teacherDialogData: {
        dialogVisible: false,
        formData: {
          id: null,
          teacherName: '',
          teacherNumber: null,
          college: '',
          state: '',
          gender: ''
        },
        handleConfirm: this.handleTeacherConfirm
      },
      pwdDialogData: {
        dialogVisible: false,
        formData: {
          id: '',
          teacherName: '',
          teacherNumber: null,
          college: '',
          state: '',
          gender: ''
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
      getTeacherList().then(response => {
        // 显示加载动画
        this.myTableData.loading = true
        // 获取table的显示数据 并将status转换成string
        this.myTableData.tableData = response.data.map(item => {
          const stateList = ['禁用', '启用']
          const temp = {
            id: item.id,
            teacherNumber: item.username,
            teacherName: item.name,
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
    handleSizeChange(pageSize) {
      this.pagination.listQuery.pageSize = pageSize
    },
    handleCurrentChange(pageNum) {
      this.pagination.listQuery.pageNum = pageNum
    },
    //  编辑老师帐号
    handleEdit(row) {
      this.teacherDialogData.formData = row
      this.teacherDialogData.dialogVisible = true
    },
    //  创建新教师帐号
    handleCreate() {
      this.handleClean()
      this.teacherDialogData.dialogVisible = true
    },
    //  创建或修改教师信息确认事件
    handleTeacherConfirm() {
      this.teacherDialogData.dialogVisible = false
      const temp = this.teacherDialogData.formData
      const stateList = { '禁用': 0, '启用': 1 }
      const data = {
        id: temp.id,
        username: temp.teacherNumber,
        name: temp.teacherName,
        gender: temp.gender,
        state: stateList[temp.state],
        department: temp.college,
        password: temp.password,
        role: 'TEA'
      }
      teacherAddEdit(data).then(response => {
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
    //  初始化弹窗的表单
    handleClean() {
      this.teacherDialogData.formData = {
        identification: null,
        teacherName: '',
        college: '',
        password: '',
        state: null,
        sex: null
      }
    },
    //  删除教师帐号
    handleDelete(row) {
      const params = { username: row.teacherName }
      teacherDelete(params).then(response => {
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
        alert('删除教师失败')
      })
    },
    // table选中行发生变化的事件
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    //  删除选中的教师帐号
    handleDeletes() {
      for (var item of this.multipleSelection) {
        this.handleDelete(item)
      }
    },
    //  修改密码
    handleChangePassword(row) {
      this.pwdDialogData.formData = row
      this.pwdDialogData.dialogVisible = true
    },
    //  dialog修改密码的事件
    handlePasswdConfirm() {
      this.pwdDialogData.dialogVisible = false
      const temp = this.pwdDialogData.formData
      const stateList = { '禁用': 0, '启用': 1 }
      const data = {
        id: temp.id,
        username: temp.teacherNumber,
        name: temp.teacherName,
        gender: temp.gender,
        state: stateList[temp.state],
        department: temp.college,
        password: temp.password,
        role: 'TEA'
      }
      teacherAddEdit(data).then(response => {
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
    //  修改帐号状态
    handleChangeStatus(row) {
      console.log(row)
      const { state, id } = row
      if (state === '启用') {
        statePermit({ id: id }).then(response => {
          console.log(id)
          row.state = '禁用'
        })
      } else if (state === '禁用') {
        stateForbid({ id: id }).then(response => {
          console.log(id)
          row.state = '启用'
        })
      }
    }
  }
}
</script>
