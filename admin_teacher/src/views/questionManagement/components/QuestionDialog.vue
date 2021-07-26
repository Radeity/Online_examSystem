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
          <el-form-item label="试题名称" prop="questionName">
            <el-input v-model="dialogData.formData.questionName" placeholder="试题名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">

          <el-form-item label="试题类型" prop="questionType">
            <el-select v-model="dialogData.formData.questionType" placeholder="请选择试题类型" style="width:100%">
              <el-option
                v-for="(item,index) in questionList"
                :key="item"
                :label="item"
                :value="index"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <div v-if="dialogData.formData.questionType!==2">
        <el-row>
          <el-col :span="12">
            <el-form-item label="A" prop="opt">
              <el-input v-model="option.a" placeholder="选项A" :disabled="optDisable" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="B" prop="opt">
              <el-input v-model="option.b" placeholder="选项B" :disabled="optDisable" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="C" prop="opt">
              <el-input v-model="option.c" placeholder="选项C" :disabled="optDisable" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="D" prop="opt">
              <el-input v-model="option.d" placeholder="选项D" :disabled="optDisable" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <el-row>
        <el-col :span="12">
          <el-form-item label="答案" prop="questionAnswer">
            <el-input v-model="dialogData.formData.questionAnswer" placeholder="答案" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="知识点" prop="pointId">
            <el-select v-model="dialogData.formData.pointId" placeholder="选择知识点" clearable filterable style="width:100%">
              <el-option v-for="item in pointList" :key="item.pointId" :label="item.pointName" :value="item.pointId" />
            </el-select>
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
      optDisable: false,
      option: {
        a: '',
        b: '',
        c: '',
        d: ''
      }
    }
  },
  watch: {
    option: {
      handler: function(newval, oldval) {
        this.dialogData.formData.opt = 'A、' + newval.a + ', ' + 'B、' + newval.b + ', ' + 'C、' + newval.c + ', ' + 'D、' + newval.d
        console.log('data', this.dialogData.formData)
      },
      deep: true
    },
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
