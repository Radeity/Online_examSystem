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
          <el-form-item label="学生姓名" prop="studentName">
            <el-input v-model="dialogData.formData.studentName" placeholder="姓名" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学号" prop="studentNumber">
            <el-input v-model="dialogData.formData.studentNumber" placeholder="学号" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="学院" prop="college">
            <el-input v-model="dialogData.formData.college" placeholder="学院" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="班级" prop="className">
            <el-input v-model="dialogData.formData.className" placeholder="班级" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="密码" prop="password">
            <el-input v-model="dialogData.formData.password" placeholder="密码" show-password clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="状态" prop="state">
            <el-radio-group v-model="dialogData.formData.state">
              <el-radio label="禁用">禁用</el-radio>
              <el-radio label="启用">启用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-radio-group v-model="dialogData.formData.gender">
              <el-radio label="女">女</el-radio>
              <el-radio label="男">男</el-radio>
            </el-radio-group>
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
       *    studentName,
       *    studentNumber,
       *    college,
       *    className,
       *    status,
       *    gender
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
  }
}
</script>
