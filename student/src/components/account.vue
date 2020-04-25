<template>
    <div class="content-container">
        <el-row>
            <el-col :span="20" :offset="2" style="margin-top:10px;">
                <el-form ref="form" :rules="rules" :label-position="labelPosition" :model="Info" label-width="100px" style="width: 90%;">
                    <el-row>
                        <el-col :span="12">
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="账号：" prop="identifier">
                                    <el-input :disabled="disabled" v-model="username"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="身份：" prop="identifier">
                                    <el-input :disabled="disabled" value="学生"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="姓名：" prop="name">
                                    <el-input v-model="Info.name" placeholder="请输入姓名"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="班级" prop="class">
                                    <el-input v-model="Info.class" placeholder="请输入班级"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="学院" prop="class">
                                    <el-input v-model="Info.fac" placeholder="请输入学院"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="性别" prop="sex">
                                    <el-radio-group v-model="Info.sex">
                                    <el-radio :label="0">男</el-radio>
                                    <el-radio :label="1">女</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="出生日期" prop="born">
                                    <el-date-picker v-model="Info.born" format="yyyy 年 MM 月 dd 日" value-format="timestamp" placeholder="出生日期"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="电话号码" prop="phone">
                                    <el-input v-model="Info.phone" placeholder="电话号码"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                <el-form-item label="邮箱" prop="email">
                                    <el-input v-model="Info.email" placeholder="邮箱"/>
                                </el-form-item>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="10" :offset="2">
                            <el-row>
                                <el-col :span="12" :offset="6" style="text-align: center">
                                    <img src="static/img/avatar/2.jpg" class="avatar">
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12" :offset="6" style="text-align: center">
                                <h4>头像</h4>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8" :offset="8">
                            <el-form-item>
                                <el-button type="primary" @click="update">保存</el-button>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import OHeader from './common/header'
import OFooter from './common/footer'
export default {
    data () {
        return {
        labelPosition: 'right',
        disabled: true,
        rules: {
            identifier: [{ required: true, message: '请输入账号', trigger: 'change' }]
        },
        username : localStorage.getItem('username'),
        Info: {
            name: '',
            class: '',
            fac: '',
            sex: 0,
            phone: '',
            email: '',
        }
        }
    },
    components: {
        OHeader,
        OFooter
    },
    created () {
        this.getuser()
    },
    methods: {
        getuser () {
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/auth/getuser',
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                if(response.data.gender == '男')
                    this.Info.sex = 0
                else
                    this.Info.sex = 1
                this.Info.name = response.data.name
                this.Info.class = response.data.adminclass
                this.Info.fac = response.data.department
                
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
            });
        },
        update() {
            this.$notify.info({
                title: '更改信息',
                message: '本平台账号信息由教师录入，如有错误请联系本班教师',
                offset: 70
            })
        }
    }
}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
.message{
    margin-bottom: 20px;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
</style>
