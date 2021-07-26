<template>
  <div class="app-container">
    <el-col :span="5">
      <tree :data="treeData" :node-click="nodeClick" />
    </el-col>
    <el-col :span="19">
      <paper-table v-if="course" ref="paperTable" :subject-id="subjectId" :handles="{handleCreate,handleEdit}" />
      <question-table v-else ref="questionTable" :subject-id="subjectId" :paper-id="paperId" :handles="{handleQuCreate}" />
    </el-col>
    <paper-dialog :dialog-data="dialogData" />
    <question-dialog :dialog-data="quDialogData" :subject-id="subjectId" :paper-id="paperId" />
  </div>

</template>

<script>
import Tree from './components/Tree'
import PaperTable from './components/PaperTable'
import QuestionTable from './components/QuestionTable'
import PaperDialog from './components/PaperDialog'
import QuestionDialog from './components/QuestionDialog'
import { getCourseList, getPaperByCourse } from '@/api/paper'
export default {
  components: { Tree, PaperTable, QuestionTable, PaperDialog, QuestionDialog },
  data() {
    return {
      course: true,
      paperList: [],
      treeData: [
        {
          label: 'c语言',
          children: [{
            label: '2017-2018c语言期中考试'
          }, {
            label: '2018-2019c语言期末考试'
          }
          ]
        },
        {
          label: '面向对象',
          children: [{
            label: '面向对象期中考试'
          }, {
            label: '面向对象期末考试'
          }]
        },
        {
          label: 'java程序设计',
          children: [{
            label: 'java程序设计期末考试'
          }, {
            label: 'java程序设计期中考试'
          }]
        }],
      dialogData: {
        dialogVisible: false,
        formData: {
          paperName: '2017-2018c语言期中考试',
          subjectName: 'c语言',
          singleNum: 40,
          singleScore: 20,
          multiNum: 20,
          multiScore: 20,
          blankNum: 20,
          blankScore: 50
        },
        handleConfirm: this.handleConfirm
      },
      quDialogData: {
        dialogVisible: false,
        handleConfirm: this.handleConfirm
      },
      subjectId: 0,
      paperId: 0
    }
  },
  created() {
    this.getCourseList()
  },
  methods: {
    // 判断点击的是试卷还是课程,改变展示QuestionTable还是PapaerTable
    nodeClick(data, node) {
      if (node.parent.parent) {
        this.course = false
        this.paperId = data.id
        console.log('data', data)
        // this.$refs.questionTable.refresh(this.paperId)
      } else {
        this.course = true
        this.subjectId = data.id
        console.log('data2', data)
        // this.$refs.paperTable.refresh(this.subjectId)
      }
    },
    // 刷新界面
    refreshPaperTable() {
      this.$refs.paperTable.refresh(this.subjectId)
    },
    //
    refreshQuestionTable() {
      this.$refs.questionTable.refresh(this.paperId, this.subjectId)
    },
    //  获取教授的所有课程的列表
    getCourseList() {
      //  调用获取list的api
      getCourseList().then(response => {
        // 获取所有的课程转换成treeData
        this.treeData = response.data.map(item => {
          const temp = {
            id: item.subjectId,
            label: item.subjectName,
            children: []
          }
          getPaperByCourse({ subjectId: temp.id }).then(response => {
            temp.children = response.data.map(item => {
              return { label: item.paperName, id: item.paperId }
            })
          }).catch(() => {
            alert('获取试卷名失败')
          })
          return temp
        })
        if (this.subjectId === 0) { this.subjectId = this.treeData[0].id }
        this.refreshPaperTable()
        // Just to simulate the time of the request
        setTimeout(() => {

        }, 500)
      }).catch(() => {
        alert('错误')
      })
    },
    //  初始化dialog的数据
    handleClean() {
      this.dialogData.formData = {
        subjectId: this.subjectId,
        paperName: '',
        subjectName: '',
        singleNum: null,
        singleScore: null,
        multiNum: null,
        multiScore: null,
        blankNum: null,
        blankScore: null
      }
    },
    //  创建新paper的事件
    handleCreate() {
      this.handleClean()
      this.dialogData.dialogVisible = true
    },
    handleEdit(row) {
      this.dialogData.formData = row
      this.dialogData.dialogVisible = true
    },
    handleQuCreate() {
      this.quDialogData.dialogVisible = true
      console.log('quDialogData.dialogVisible')
    }
  }
}
</script>

