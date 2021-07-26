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
      style="width: 90%"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="教师姓名" prop="teacherName">
            <el-input v-model="dialogData.formData.teacherName" placeholder="姓名" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工号" prop="teacherNumber">
            <el-input v-model="dialogData.formData.teacherNumber" placeholder="工号" clearable />
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
          <el-form-item label="密码" prop="password">
            <el-input v-model="dialogData.formData.password" placeholder="密码" show-password clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="状态">
            <el-radio-group v-model="dialogData.formData.state">
              <el-radio label="启用">启用</el-radio>
              <el-radio label="禁用">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别">
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
       *    identification,
       *    teacherName,
       *    className,
       *    state,
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
