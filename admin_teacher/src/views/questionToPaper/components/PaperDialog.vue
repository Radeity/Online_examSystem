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
          <el-form-item label="试卷名称" prop="paperName">
            <el-input v-model="dialogData.formData.paperName" placeholder="试卷名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">

          <el-form-item label="所属课程" prop="subjectName">
            <el-select v-model="dialogData.formData.subjectName" placeholder="请选择课程" style="width:100%">
              <el-option
                v-for="item in courseList"
                :key="item.subjectId"
                :label="item.subjectName"
                :value="item.subjectName"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="单选题总数" prop="singleNum">
            <el-input v-model="dialogData.formData.singleNum" placeholder="单选题总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="单选题总分" prop="singleScore">
            <el-input v-model="dialogData.formData.singleScore" placeholder="单选题总分" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="多选题总数" prop="multiNum">
            <el-input v-model="dialogData.formData.multiNum" placeholder="多选题总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="多选题总分" prop="multiScore">
            <el-input v-model="dialogData.formData.multiScore" placeholder="多选题总分" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="填空总数" prop="blankNum">
            <el-input v-model="dialogData.formData.blankNum" placeholder="填空总数" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="填空题总分" prop="blankScore">
            <el-input v-model="dialogData.formData.blankScore" placeholder="填空题总分" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer">
      <el-button @click.native="dialogData.dialogVisible = false">取消</el-button>
      <el-button type="primary" @click.native="handleConfirm">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { paperAddEdit, getCourseList } from '@/api/paper'
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
       *    paperName: '2017-2018c语言期中考试',
       *    subjectId:
            subjectName: 'c语言',
            singleNum: 40,
            singleScore: 20,
            multiNum: 20,
            multiScore: 20,
            blankNum: 20,
            blankScore: 50
       * },
       * handleConfirm
       *
       */
    }
  },
  data() {
    return {
      courseList: []
    }
  },
  created() {
    this.getcourseList()
  },
  methods: {
    handleConfirm() {
      this.dialogData.formData.subjectId = this.courseList.find(
        val => val.subjectName === this.dialogData.formData.subjectName
      ).subjectId
      const data = {
        paper: [this.dialogData.formData]
      }
      paperAddEdit(data).then(response => {
        this.dialogData.dialogVisible = false
        this.$parent.getCourseList()
      }).catch(() => {
        alert('添加试卷或修改试卷失败')
      })
    },
    //  获取courseList
    getcourseList() {
      getCourseList().then(response => {
        this.courseList = response.data
        console.log('courseList', this.courseList)
      }).catch(() => {
        alert('获取courseList失败')
      })
    }
  }
}
</script>
