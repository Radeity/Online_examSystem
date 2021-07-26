<template>
  <div>
    <el-card>
      <div slot="header">
        <span>试卷列表</span>
      </div>
      <el-row :gutter="20">
        <el-col :span="3">
          <el-input
            v-model="pagination.listQuery.name"
            placeholder="输入试卷名称查询"
          />
        </el-col>
        <el-col :span="6">
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
        </el-col>
      </el-row>
      <my-table :data="paperTableData|examinationFilter(pagination)" />
    </el-card>

  </div>
</template>
<script>
import MyTable from '@/components/MyTable'
import { getPaperByCourse, getCourseList, paperDelete } from '@/api/paper'
export default {
  components: { MyTable },
  filters: {
    //  通过pageination返回考试列表的filter
    examinationFilter(data, pagination) {
      const listQuery = pagination.listQuery
      const newData = { ...data }
      const { pageNum, pageSize, name } = listQuery
      newData.tableData = newData.tableData.filter(item =>
        (
          !name ||
          item.paperName.toLowerCase().includes(name.toLowerCase())
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
      /**
       * handleCreate
       * handleDeletes
       * handleEdit
       * handleDelete
       *
       */
    }

  },
  data() {
    return {
      pagination: {
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          course: '',
          name: '',
          teacherName: ''
        },
        total: 0
      },
      paperTableData: {
        columnList: [
          {
            prop: 'paperName',
            label: '试卷名称'
          },
          {
            prop: 'subjectName',
            label: '所属课程'
          },
          {
            prop: 'singleNum',
            label: '单选择题总数'
          },
          {
            prop: 'singleScore',
            label: '单选题总分'
          },
          {
            prop: 'multiNum',
            label: '多选题总数'
          },
          {
            prop: 'multiScore',
            label: '多选题总分'
          },
          {
            prop: 'blankNum',
            label: '填空题总数'
          },
          {
            prop: 'blankScore',
            label: '填空题总分'
          }
        ],
        tableData: [
          {
            paperId: '',
            paperName: '',
            subjectName: '',
            singleNum: null,
            singleScore: null,
            multiNum: null,
            multiScore: null,
            blankNum: null,
            blankScore: null
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
        handleSelectionChange: this.handleSelectionChange
      },
      courseList: [],
      multipleSelection: []
    }
  },
  watch: {
    subjectId: {
      handler: function(newval, oldval) {
        this.refresh(newval)
      }
    }
  },
  created() {
    if (this.subjectId !== 0) { this.refresh(this.subjectId) }
  },
  methods: {
    // 重新加载
    refresh(subjectId) {
      this.getPaperList(subjectId)
      this.getcourseList()
    },
    //  根据subjectId获取paperList
    getPaperList(subjectId) {
      getPaperByCourse({ subjectId: subjectId }).then(response => {
        this.paperTableData.tableData = response.data
      }).catch(() => {
        alert('根据课程id获取试卷列表失败')
      })
    },
    //  获取courseList
    getcourseList() {
      getCourseList().then(response => {
        this.courseList = response.data.map(item => {
          console.log(item.subjectName)
          return item.subjectName
        })
        console.log('courseList', this.courseList)
      }).catch(() => {
        alert('获取courseList失败')
      })
    },
    // 删除试卷
    handleDelete(row) {
      paperDelete({ paperId: row.paperId }).then(response => {
        this.refresh(this.subjectId)
      }).catch((error) => {
        alert('删除试卷失败')
        console.log(error)
      })
    },
    //  选中项发生变化的事件
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    // 删除选中的试卷
    handleDeletes() {
      for (var item of this.multipleSelection) {
        console.log(item)
        this.handleDelete(item)
      }
    }
  }
}
</script>
