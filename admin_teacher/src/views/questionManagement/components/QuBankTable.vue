<template>
  <div>
    <el-card>
      <div slot="header">
        <span>题库列表</span>
        <!--el-button style="float: right; padding: 3px 0" type="text">清空所有试题</el-button-->
      </div>
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input
            v-model="pagination.listQuery.quBankName"
            placeholder="输入题库名称查询"
          />
        </el-col>
        <el-col :span="6">
          <el-button
            icon="el-icon-check"
            type="primary"
            @click.native="handles.handleCreateBank"
          >增加</el-button>
          <el-button
            icon="el-icon-delete"
            type="danger"
            @click.native="handleDeletes"
          >删除</el-button>
        </el-col>
      </el-row>
      <my-table :data="myTableData|examinationFilter(pagination)" />
    </el-card>

  </div>
</template>
<script>
import MyTable from '@/components/MyTable'
import { getQuBankList, deleteQuBank } from '@/api/quBank'

export default {
  components: { MyTable },
  filters: {
    //  通过pageination返回考试列表的filter
    examinationFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }
      const { pageNum, pageSize, quBankName } = listQuery
      newData.tableData = newData.tableData.filter(item =>
        (
          !quBankName ||
          item.quBankName.toLowerCase().includes(quBankName.toLowerCase())
        )
      )
      pagination.total = newData.tableData.length
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
          quBankName: ''
        },
        total: 0
      },
      myTableData: {
        columnList: [
          {
            prop: 'quBankName',
            label: '题库名称'
          }
        ],
        tableData: [
          {
            quBankName: '2017c语言题库',
            course: 'c语言',
            seQuNumber: 200,
            quNumber: 300
          }
        ],
        menuList: [
          {
            icon: 'el-icon-edit',
            word: '编辑',
            command: this.handles.handleEditBank
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
      courseList: ['1', '2', '3'],
      teacherList: ['a', 'b', 'c'],
      multipleSelection: []
    }
  },
  watch: {
    subjectId: {
      handler: function(newval, oldval) {
        console.log('refreshQubank', newval)
        this.refresh(newval)
      }
    }
  },
  created() {
    console.log('qubankcreated')
    if (this.subjectId !== 0) {
      this.refresh(this.subjectId)
    }
  },
  methods: {
    refresh(subjectId) {
      this.getQubankLIst(subjectId)
    },
    getQubankLIst(subjectId) {
      getQuBankList({ subjectId: subjectId }).then(response => {
        this.myTableData.tableData = response.data.map(item => {
          return { quBankName: item }
        })
      }).catch(() => {
        console.log('获取题库列表失败')
      })
    },
    handleDelete(row) {
      deleteQuBank({ bankName: row.quBankName }).then(response => {
        this.refresh(this.subjectId)
      }).catch(() => {
        alert('删除题库失败')
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
