<template>
    <div id="password">
        <el-row class="password-msg"><el-col :span="24" style="color: black;">
            <!--<h1 id="h">修改密码</h1>-->
        </el-col></el-row>
        <el-row><el-col :span="20" :offset="2" style="margin-top:10px;">
            <el-card class="box-card">
                <el-form ref="form" :rules="rules" :label-position="labelPosition" :model="form" label-width="100px" style="width: 90%;">
                    <el-row><el-col :span="12" :offset="6">
                        <el-form-item label="旧密码：" prop="oldPassword">
                        <el-input v-model="form.oldPassword" auto-complete="off" type="password" />
                        </el-form-item>
                    </el-col></el-row>
                    <el-row><el-col :span="12" :offset="6">
                        <el-form-item label="新密码：" prop="newPassword">
                        <el-input v-model="form.newPassword" auto-complete="off" type="password" />
                        </el-form-item>
                    </el-col></el-row>
                    <el-row><el-col :span="12" :offset="6">
                        <el-form-item label="确认新密码" prop="newPassword1">
                        <el-input v-model="form.newPassword1" auto-complete="off" type="password" />
                        </el-form-item>
                    </el-col></el-row>
                    <el-row><el-col :span="12" :offset="8">
                        <el-form-item><el-button type="primary" @click="update">保存</el-button></el-form-item>
                    </el-col></el-row>
                </el-form>
            </el-card>
        </el-col></el-row>
    </div>
</template>
<script>
import OHeader from "./common/header";
import OFooter from "./common/footer";
export default {
    data() {
        const validatePass = (rule, value, callback) => {
            if (this.form.oldPassword !== "") {
                if (value.length <= 0) {
                    callback(new Error("请输入新密码"));
                } else if (value.length < 6) {
                    callback(new Error("密码不能小于6位"));
                } else {
                    callback();
                }
            } else {
                callback();
            }
        };
        const validatePass1 = (rule, value, callback) => {
            if (this.form.oldPassword !== "") {
                if (value.length <= 0) {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.form.newPassword) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
            } else {
                callback();
            }
        };
        return {
            labelPosition: "right",
            form: {
                oldPassword: "",
                newPassword: "",
                newPassword1: ""
            },
            rules: {
                oldPassword: [
                { required: true, message: "请输入旧密码", trigger: "blur" }
                ],
                newPassword: [
                { required: true, validator: validatePass, trigger: "blur" }
                ],
                newPassword1: [
                { required: true, validator: validatePass1, trigger: "blur" }
                ]
            },
            readOnly: false
        };
    },
    methods: {
        update() {
            var an = {
                username: localStorage.getItem('username'),
                oldpassword: this.form.oldPassword,
                newpassword: this.form.newPassword
            }
            this.$ajax({
                method:'post',
                url:'http://localhost:8080/auth/changePass',
                headers:{'Authorization':localStorage.getItem('authorization'),'Content-Type':'application/json'},
                data: JSON.stringify(an)
            }).then(response => {
                this.$router.push('/')
            })
            .catch(function(error) {
                alert('提交错误！')
            });

        }
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" rel="stylesheet/scss" scoped>
#password {
    margin-bottom: 20px;
}
#h {
    font-size: 16px;
}
</style>
