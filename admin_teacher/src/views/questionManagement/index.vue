<template>
  <div class="app-container">
    <el-col :span="4">
      <tree
        :data="treeData"
        :node-click="nodeClick"
      />
    </el-col>
    <el-col :span="20">
      <qu-bank-table v-if="course" ref="quBankTable" key="1" :subject-id="subjectId" :handles="{handleCreateBank, handleEditBank}" />
      <question-table v-else ref="questionTable" key="2" :bank-name="bankName" :subject-id="subjectId" :handles="{handleCreate,handleEdit,handleImport}" />
    </el-col>
    <question-dialog ref="questionDialog" :dialog-data="questionDialogData" :subject-id="subjectId" />
    <qu-bank-dialog :dialog-data="quBankDialogData" :subject-id="subjectId" />
    <quimport-dialog ref="quimportDialog" :dialog-data="quimportDialogData" :subject-id="subjectId" :bank-name="bankName"/>
  </div>
</template>

<script>
import Tree from './components/Tree'
import QuestionTable from './components/QuestionTable'
import QuestionDialog from './components/QuestionDialog'
import QuBankTable from './components/QuBankTable'
import QuBankDialog from './components/QuBankDialog'
import QuimportDialog from './components/QuimportDialog'
import { getCourseList } from '@/api/paper'
import { getQuBankList, questionAddEdit, quBankAdd, quBankEdit } from '@/api/quBank'

export default {
  components: { Tree, QuestionTable, QuBankTable, QuestionDialog, QuBankDialog, QuimportDialog },
  data() {
    return {
      course: true,
      treeData: [
        {
          label: 'c语言',
          children: [{
            label: '2017-2018c语言题库'
          }, {
            label: '2018-2019c语言题库'
          }
          ]
        }, {
          label: '面向对象',
          children: [{
            label: '面向对象期中题库'
          }, {
            label: '面向对象期末题库'
          }]
        }, {
          label: 'java程序设计',
          children: [{
            label: 'java程序设计期末题库'
          }, {
            label: 'java程序设计期中题库'
          }]
        }
      ],
      bankName: '',
      subjectId: 0,
      questionDialogData: {
        dialogVisible: false,
        formData: {
          'questionId': 10,
          'questionName': '在关系数据库中存在的完整性规则有?',
          'questionType': 1,
          'opt': 'A、实体完整性规则, B、索引完整性规则, C、引用完整性规则, D、用户定义的完整性规则',
          'questionAnswer': 'AC',
          'subjectId': 1,
          'pointId': 1,
          'bankName': '19-20 数据库',
          'questionTypeName': '多项选择题',
          'pointName': '数据模型'
        },
        handleConfirm: this.handleConfirm
      },
      quBankDialogData: {
        dialogVisible: false,
        formData: {
          quBankName: '',
          oldQuBankName: ''
        },
        handleConfirm: this.handleConfirmBank
      },
      quimportDialogData: {
        dialogVisible: false,        
        handleConfirm: this.handleConfirm
      }
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
        this.bankName = data.id
        /* if (this.$refs.questionTable) {
          console.log(this.course, this.$refs, this.$refs.questionTable)
          this.$refs.questionquestionDialogDataTable.refresh(this.bankName)
        }*/
      } else {
        this.course = true
        this.subjectId = data.id
        /* this.$refs.quBankTable.refresh(this.subjectId)*/
      }
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
          getQuBankList({ subjectId: temp.id }).then(response => {
            temp.children = response.data.map(item => {
              return {
                id: item,
                label: item
              }
            })
          }).catch(() => {
            alert('获取题库名失败')
          })
          return temp
        })
        console.log('treedata', this.treeData)
        this.subjectId = this.treeData[0].id
        // Just to simulate the time of the request
        setTimeout(() => {
        }, 500)
      }).catch(error => {
        console.log(error)
        alert('错误')
      })
    },
    handleClean() {
      const temp = this.questionDialogData.formData
      temp.bankName = this.bankName
      temp.questionId = ''
      temp.questionName = ''
      temp.questionType = ''
      temp.opt = ''
      temp.questionAnswer = ''
      temp.pointName = ''
      temp.pointId = ''
    },
    // 创建试题
    handleCreate() {
      this.handleClean()
      this.questionDialogData.dialogVisible = true
    },
    handleConfirm() {
      const data = {
        question: [this.questionDialogData.formData]
      }
      questionAddEdit(data).then(response => {
        this.questionDialogData.dialogVisible = false
        this.$refs.questionTable.refresh(this.subjectId, this.bankName)
      }).catch(() => {
        alert('添加试题或修改试题失败')
      })
    },
    // 编辑试题
    handleEdit(row) {
      this.questionDialogData.formData = row
      this.questionDialogData.dialogVisible = true
    },
    handleImport(){
      this.quimportDialogData.dialogVisible = true
      //console.log(this.quimportDialogData.dialogVisible)
    },
    handleCleanBank() {
      this.quBankDialogData.formData = {
        quBankName: '',
        oldQuBankName: ''
      }
    },
    // 创建题库
    handleCreateBank() {
      this.handleCleanBank()
      this.quBankDialogData.dialogVisible = true
    },
    // 编辑题库
    handleEditBank(row) {
      this.quBankDialogData.formData = row
      this.quBankDialogData.formData.oldQuBankName = row.quBankName
      this.quBankDialogData.dialogVisible = true
    },
    handleConfirmBank() {
      const data = this.quBankDialogData.formData
      if (data.oldQuBankName !== '') {
        const params = {
          oldbankName: this.quBankDialogData.formData.oldQuBankName,
          newbankName: this.quBankDialogData.formData.quBankName
        }
        quBankEdit(params).then(response => {
          this.quBankDialogData.dialogVisible = false
          this.$refs.quBankTable.refresh(this.subjectId)
        }).catch(() => {
          console.log('编辑题库失败')
        })
      } else {
        const name = this.quBankDialogData.formData.quBankName

        quBankAdd({ bankName: name, subjectId: this.subjectId }).then(response => {
          this.quBankDialogData.dialogVisible = false
          this.$refs.quBankTable.refresh(this.subjectId)
        }).catch(() => {
          console.log('添加题库失败')
        })
      }
    }
  }
}
</script>
