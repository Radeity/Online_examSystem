<template>
    <el-dialog 
      :visible.sync="dialogData.dialogVisible"
       width="60%"
       top="10vh"
     >
     <el-row>
       <el-col :span="24">
         <el-upload
           drag
           :multiple="false"
           :auto-upload="true"
           :show-file-list="true"
           :before-upload="beforeUploadSubjectUpload"
           :on-progress="handleUploadSubjectProgress"
           :on-success="handleUploadSubjectSuccess"
           :action="importUrl"
           :data="params"
           :headers="headers"
           style="text-align: center;">
           <i class="el-icon-upload"></i>
           <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
           <div slot="tip" class="el-upload__tip">只能上传xlsx文件<br><br>（提示表头：题目名称，题目类型，选项，答案，知识点）</div>
         </el-upload>
       </el-col>
     </el-row>
    </el-dialog>
</template>
<script>
import { getToken } from '@/utils/auth'
export default {
    props: {
      dialogData: {
        type: Object,
        required: true
      },
      subjectId: {
        type: Number,
        required: true
      },
      bankName: {
        type: String,
        required: true
      }
    },
    data() {
        return {
            headers: {
              Authorization: 'Bearer ' + getToken()
            },
            importUrl: 'http://localhost:8080/tea/import',
            percentageSubject: '',
            uploadingSubject: false,
            params: {
              subjectId: this.subjectId,
              bankName:  this.bankName 
            }
        }
    },
    watch: {
      subjectId: {
        handler: function(newval, oldval) {
          this.refresh(newval, this.bankName)
        }
      },
      bankName: {
        handler: function(newval, oldval) {
          this.refresh(this.subjectId, newval)
        }
      }
    },    
    methods: {
        refresh(subjectId, bankName) {
          this.params.bankName = bankName
          this.params.subjectId = subjectId
        },
        handleUploadSubjectProgress (event, file, fileList) {
          this.uploadingSubject = true
          this.percentageSubject = parseInt(file.percentage.toFixed(0))
        },
        // 上传成功
        handleUploadSubjectSuccess () {
          this.dialogImportVisible = false
          this.getList()
          notifySuccess(this, '导入成功')
          this.uploadingSubject = false
        },
        // 上传前
        beforeUploadSubjectUpload (file) {
          const isExcel = true//file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
          const isLt10M = file.size / 1024 / 1024 < 10
          if (!isExcel) {
            this.$message.error('上传附件只能是 excel 格式!')
          }
          if (!isLt10M) {
            this.$message.error('上传附件大小不能超过 10MB!')
          }
          return isExcel && isLt10M
        }
    }
}
</script>