<template>
  <el-dialog
    :visible.sync="dialogData.dialogVisible"
    width="60%"
    top="10vh"
  >
    <el-form
      ref="dataForm"
      :model="dialogData"
      label-position="right"
      label-width="100px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="考试名称" prop="examName">
            <el-input v-model="dialogData.formData.examName" clearable placeholder="考试名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="考试时间" prop="lastTime">
            <el-input v-model="dialogData.formData.lastTime" clearable placeholder="考试时间">
              <span
                slot="suffix"
              >分钟</span>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="所属课程" prop="subjectName">
            <el-select v-model="dialogData.formData.subjectName" clearable filterable placeholder="请选择课程" @change="handleCourseChange">
              <el-option
                v-for="item in dialogData.courseList"
                :key="item.subjectId"
                :label="item.subjectName"
                :value="item.subjectName"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="试卷" prop="paperName">
            <el-select v-model="dialogData.formData.paperName" clearable filterable placeholder="请选择试卷前先选择课程" @change="handlePaperChange">
              <el-option
                v-for="item in dialogData.paperList"
                :key="item.paperId"
                :label="item.paperName"
                :value="item.paperName"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>

        <!--el-col :span="12">
          <el-form-item label="总分" prop="className">
            <el-input v-model="dialogData.formData.totalScore" :readonly="true" />
          </el-form-item>
        </el-col-->
        <el-col :span="12">
          <el-form-item label="结束时间" prop="deadline">
            <el-date-picker
              v-model="dialogData.formData.deadline"
              type="datetime"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择结束时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态">
            <el-radio-group v-model="dialogData.formData.state">
              <el-radio label="发布">发布</el-radio>
              <el-radio label="未发布">未发布</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click.native="dialogData.dialogVisible = false">取消</el-button>
      <el-button type="primary" @click.native="dialogData.handleConfirm">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getPaperByCourse } from '@/api/paper'
export default {
  filters: {

  },
  props: {
    dialogData: {
      type: Object,
      required: true
      /**
       * dialogVisible,
       * courseList,
       * paperList,
       * formData:{
       *    identification,
       *    studentName,
       *    className,
       *    status,
       *    sex
       * },
       * handleConfirm
       *
       */
    }
  },
  data() {
    return {}
  },
  methods: {
    handleCourseChange(subjectName) {
      if (subjectName) {
        const subjectId = this.dialogData.courseList.find(item => item.subjectName === subjectName).subjectId
        this.dialogData.formData.subjectId = subjectId
        this.getPaperList(subjectId)
      }
    },
    handlePaperChange(paperName) {
      if (paperName) {
        const paperId = this.dialogData.paperList.find(item => item.paperName === paperName).paperId
        this.dialogData.formData.paperId = paperId
      }
    },
    //  根据subjectId获取paperList
    getPaperList(subjectId) {
      getPaperByCourse({ subjectId: subjectId }).then(response => {
        this.dialogData.paperList = response.data.map(item => {
          const temp = {
            paperId: item.paperId,
            paperName: item.paperName
          }
          return temp
        })
      }).catch(() => {
        alert('获取paperList失败')
      })
    }
  }
}
</script>
<style >
.el-select{
  width: 100%
}
.el-form{
  width: 90%
}
</style>
