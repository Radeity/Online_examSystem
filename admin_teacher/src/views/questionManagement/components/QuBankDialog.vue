<template>
  <el-dialog
    :visible.sync="dialogData.dialogVisible"
    width="60%"
    top="10vh"
  >
    <el-form
      ref="dataForm"
      :model="dialogData.formData"
      label-position="right"
      label-width="100px"
      style="width: 90%"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="题库名称" prop="quBankName">
            <el-input v-model="dialogData.formData.quBankName" placeholder="题库名称" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer">
      <el-button @click.native="dialogData.dialogVisible = false">取消</el-button>
      <el-button type="primary" @click.native="dialogData.handleConfirm">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getPointList } from '@/api/point'
export default {
  filters: {

  },
  props: {
    dialogData: {
      type: Object,
      required: true
      /**
       * dialogVisible,
       * formData:{
       *    questionName: 题目名称,
       *    questionType: 题目类型
            opt: 选项
            questionAnswer: 答案
            pointName: 知识点
       * },
       * handleConfirm
       *
       */
    },
    subjectId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      questionList: ['单项选择题', '多项选择题', '填空题'],
      pointList: [],
      optDisable: false
    }
  },
  watch: {
    subjectId: {
      handler: function(newval, oldval) {
        this.getPointList(newval)
      }
    },
    dialogData: {
      handler: function(newval, oldval) {
        if (newval.formData.questionType === 2) {
          this.optDisable = true
        } else {
          this.optDisable = false
        }
      },
      deep: true
    }
  },
  methods: {
    refresh(subjectId) {
      this.getPointList(subjectId)
    },

    getPointList(subjectId) {
      getPointList({ subjectId: subjectId }).then(response => {
        this.pointList = response.data
      }).catch(() => {
        alert('获取pointList失败')
      })
    }
  }
}
</script>
