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
        <el-form-item label="密码" prop="password">
          <el-input v-model="dialogData.formData.password" show-password placeholder="请输入新密码" />
        </el-form-item>
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
  props: {
    dialogData: {
      type: Object,
      required: true
      /**
       * dialogVisible
       * formData{
       *    password
       * }
       * handleConfirm
       */
    }}
}
</script>
