<template>
  <div class="bg">
    <!-- 背景 -->
    <svg id="bg_svg" viewBox="0 0 1440 480" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid slice">
        <filter id="blur">
            <feGaussianBlur stdDeviation="5"></feGaussianBlur>
        </filter>
        <image xlink:href="static/img/login/login-bg.jpg" width="1440" height="480" filter="url(#blur)"></image>
    </svg>
    <!-- 注册、登录表单 -->
    <div class="login-wrap">
        <el-tabs v-model="activeName">
            <!-- 注册 -->
            <el-tab-pane label="注册" name="/register" class="login-wrap-title">
                <el-form ref="registerForm" :model="register.form" :rules="register.rules" class="register-form" label-position="left" auto-complete="off">
                    <el-form-item prop="identifier">
                        <el-input placeholder="用户名" v-model="register.form.identifier" name="identifier" type="text" auto-complete="off"/>
                    </el-form-item>
                    <el-form-item prop="email">
                        <el-input placeholder="邮箱" v-model="register.form.email" name="email" type="text" auto-complete="off"/>
                    </el-form-item>
                    <el-form-item prop="credential">
                        <el-input placeholder="密码" :type="register.passwordType" v-model="register.form.credential" name="credential" auto-complete="off" @keyup.enter.native="handleRegister"/>
                    </el-form-item>
                    <el-form-item prop="code">
                        <el-row :span="24">
                            <el-col :span="14">
                                <el-input :maxlength="4" v-model="register.form.code" auto-complete="off" placeholder="请输入验证码" @keyup.enter.native="handleRegister" />
                            </el-col>
                            <el-col :span="10">
                                <img src="http://localhost:8080/vercode" class="login-code-img" id="refcode" @click="refreshRegisterCode">
                            </el-col>
                        </el-row>
                    </el-form-item>
                    <el-form-item>
                        <el-button :loading="register.loading" type="primary" @click="openregister">注册</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <!-- 登录 -->
            <el-tab-pane label="登录" name="/login" class="login-wrap-title">
                <el-form ref="loginForm" :model="login.form" :rules="login.rules" class="login-form" auto-complete="on" label-position="left">
                    <el-form-item prop="identifier">
                        <el-input placeholder="用户名或邮箱" v-model="login.form.identifier" name="identifier" type="text" auto-complete="on"/>
                    </el-form-item>
                    <el-form-item prop="credential">
                        <el-input placeholder="密码" :type="login.passwordType" v-model="login.form.credential" name="credential" auto-complete="on" @keyup.enter.native="handleLogin"/>
                        <span class="forgot-suffix">
                            <span class="forgot-link">
                                <router-link to="/reset-password"><span>忘记密码?</span></router-link>
                            </span>
                        </span>
                    </el-form-item>
                    <el-form-item prop="code">
                        <el-row :span="24">
                            <el-col :span="14">
                                <el-input :maxlength="4" v-model="login.form.code" auto-complete="off" placeholder="请输入验证码" @keyup.enter.native="handleLogin" />
                            </el-col>
                            <el-col :span="10">
                                <img src="http://localhost:8080/vercode" class="login-code-img" id="logcode" @click="refreshLoginCode">
                            </el-col>
                        </el-row>
                    </el-form-item>
                    <el-form-item>
                        <el-button :loading="login.loading" type="primary" @click.native.prevent="handleLogin">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </div>
  </div>
</template>

<script>

export default {
    data () {
        return {
            activeName: '/login',
            login: {
                form: {
                    identifier: '',
                    credential: '',
                    code: ''
                },
                rules: {
                    identifier: [{ required: true, trigger: 'blur', message: '请输入用户名' }],
                    credential: [
                        { required: true, trigger: 'blur', message: '请输入密码' },
                        { min: 6, trigger: 'blur', message: '密码长度最少为6位' }],
                    code: [
                        { required: true, message: '请输入验证码', trigger: 'blur' },
                        { min: 4, max: 4, message: '验证码长度为4位', trigger: 'blur' }
                    ]
                },
                loading: false,
                passwordType: 'password'
            },
            register: {
                form: {
                    identifier: '',
                    email: '',
                    credential: '',
                    code: '',
                },
                rules: {
                    identifier: [{ required: true, trigger: 'blur', message: '请输入用户名' }],
                    email: [{ required: true, trigger: 'blur', message: '请输入邮箱地址' }],
                    credential: [
                        { required: true, trigger: 'blur', message: '请输入密码' },
                        { min: 6, trigger: 'blur', message: '密码长度最少为6位' }],
                    code: [
                        { required: true, message: '请输入验证码', trigger: 'blur' },
                        { min: 4, max: 4, message: '验证码长度为4位', trigger: 'blur' }
                    ]
                },
                loading: false,
                passwordType: 'password'
            }
        }
    },
    mounted () {
        this.activeName = this.$route.fullPath
        this.refreshLoginCode()
        this.refreshRegisterCode()
    },
    methods: {
        handleLogin () {
            localStorage.setItem('username', this.login.form.identifier)
            this.$ajax({
                method:'post',
                url:'http://localhost:8080/auth/login',
                headers:{'Content-Type':'application/json'},
                data: JSON.stringify({
                    "username": this.login.form.identifier,
                    "password": this.login.form.credential,
                    "code": this.login.form.code
                })
            }).then(response => {
                localStorage.setItem('authorization', response.headers['authorization'])
                this.$router.push('/')
                location.reload()
            })
            .catch(function(error) {
                if(error.response.data.message == '验证码错误!')
                    alert('验证码错误！')
                else if(error.response.data.message == 'Bad credentials')
                    alert('密码错误！')
                localStorage.clear()
                location.reload()
            });
        },
        refreshLoginCode () {
            var verify = document.getElementById('logcode');
            verify.src = "http://localhost:8080/vercode";
        },
        refreshRegisterCode () {
            var verify = document.getElementById('refcode');
            verify.src = "http://localhost:8080/vercode";
        },
        openregister () {
            this.$notify.info({
                title: '注册方式',
                message: '本平台账号由教师开通发放，请需要账号者联系本班教师',
                offset: 70
            })
        }
    }
}
</script>

<style rel="stylesheet/scss" lang="scss">
#bg_svg {
    position: fixed;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
}
.bg {
    height: 100%;
    display: flex;
    justify-content: center;
}
.login-wrap {
    width: 330px;
    border-radius: 5px;
    padding: 20px;
    margin: 20px;
    background: #ffffff;
    color: #71767a;
    .el-tabs__item {
    font-size: 20px;
    }
    .el-tabs__nav {
    left: 30%;
    }
    .login-code-img {
    margin-left: 10px;
    width: 110px;
    height: 40px;
    }
    /* 注册登录 */
    .login-wrap-title {
    color: #71767a;
    margin: 0 15px;
    cursor: pointer;
    line-height: 24px;
    border-bottom: 2px solid transparent;
    }
    .el-form-item {
    margin-bottom: 20px !important;
    }
    h3 {
    text-align: center;
    color: #ebedef;
    margin-top: 0;
    margin-bottom: 5px;
    span {
        color: #20a0ff;
    }
    }
    form {
    margin-top: 25px;
    .el-form-item {
        margin-bottom: 15px;
    }
    }
    a {
    text-decoration: none;
    color: #1f2d3d;
    }
    button {
    width: 100%;
    font-weight: 600;
    }
}
.forgot-suffix {
    right: 12px;
    position: absolute;
    top: 50%;
    z-index: 2;
    color: #595959;
    line-height: 0;
    -webkit-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
    .forgot-link {
    font-size: 14px;
    border-left: 1px solid #e8e8e8;
    padding-left: 12px;
    padding-right: 5px;
    a {
        color: #8c8c8c;
    }
    }
}
.third-login {
    position: relative;
    margin-top: 20px;
    border-top: 1px solid #e8e8e8;
    padding-top: 20px;
    span.wechat {
    background-position: 0 -41.5px;
    }
}
.third-link {
    display: flex;
}
.third-login span {
    display: inline-block;
    width: 20px;
    height: 21px;
    //background: url("../../static/img/login/third-login.png") no-repeat;
    background-size: 100%;
    vertical-align: middle;
    margin-right: 5px;
}
.sms-code-input {
    width: 55%;
}
.sms-code-send {
    width: 40% !important;
    margin-left: 10px;
}
</style>
