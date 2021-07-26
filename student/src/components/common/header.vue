<template>
    <fixed-header>
        <div class="header-area">
            <div class="clever-main-menu">
                <div class="classy-nav-container breakpoint-off">
                    <nav class="classy-navbar justify-content-between" id="cleverNav">
                        <a class="nav-brand" href="/">OE在线考试系统</a>
                        <div class="classy-menu">
                            <div class="classynav">
                                <el-menu :default-active="activeIndex"
                                        mode="horizontal"
                                        text-color="rgba(0, 0, 0, 0.45)"
                                        active-text-color="#232323"
                                        :unique-opened=true>
                                    <el-menu-item index="/index" @click="open('/')">首页</el-menu-item>
                                    <el-menu-item index="/exams" @click="open('/exams')">考试</el-menu-item>
                                    <el-menu-item index="/like" @click="open('/like')">我的收藏</el-menu-item>
                                    <el-submenu index="/u">
                                        <template slot="title">帮助</template>
                                            <el-menu-item index="u-source" @click="open('https://github.com/Radeity/Online_examSystem')">
                                            源码地址
                                        </el-menu-item>
                                        <el-menu-item index="u-deploy" @click="open('https://github.com/Radeity/Online_examSystem/blob/master/README.md')">
                                            部署文档
                                        </el-menu-item>
                                        <el-menu-item index="c-log" @click="open('https://github.com/Radeity/Online_examSystem/commits/master')">
                                            更新日志
                                        </el-menu-item>
                                        <el-menu-item index="c-overview"  @click="open('https://github.com/Radeity/Online_examSystem/commits/branches')">
                                            规划总览
                                        </el-menu-item>
                                        <el-menu-item index="u-admin" @click="open('http://localhost:8080/admin/index.html#/')">
                                            管理后台
                                        </el-menu-item>
                                    </el-submenu>
                                    <el-submenu v-if="checklogin()" index="/user-info">
                                        <template slot="title">
                                            {{ username }}&nbsp;&nbsp;
                                            <img src="../../../static/img/avatar/5.jpg" style="height: 30px;border-radius: 50%;margin-right: 6px;"/>
                                        </template>
                                        <el-menu-item index="account" @click="open('/account')">个人中心</el-menu-item>
                                        <el-menu-item index="password" @click="open('/password')">修改密码</el-menu-item>
                                        <el-menu-item index="logOut" @click="logout()">退出</el-menu-item>
                                    </el-submenu>
                                </el-menu>
                                <div class="register-login-area" v-if="!checklogin()">
                                <a class="btn" target="_blank" @click="open('/login')">注册 & 登录</a>
                                <!--<a class="btn" target="_blank" @click="open('/login')">登录</a>-->
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </fixed-header>
</template>

<script>
import FixedHeader from 'vue-fixed-header'

export default {
    data(){
        return{
            username: ''
        }
    },
    components: {
        FixedHeader
    },
    created() {
        this.checklogin()
        this.username = localStorage.getItem('username')
    },
    // 检测路由变化
    watch: {
        $route () {
            this.checklogin()
        }
    },
    data () {
        return {
            activeIndex: '/index',
            login: false,
            input: '',
            query: ''
        }
    },
    methods: {
        // 导航栏切换
        open (path) {
            if (path.startsWith('http')) {
                window.open(path)
                return
            }
            else{
                this.$router.push({
                    path: path,
                    query: {}
                })
            }
        },
        checklogin() {
            if(localStorage.getItem('username') == null){
                if(this.$route.path!='/login' && this.$route.path!='/' && this.$route.path!='/reset-password')
                    this.open('/login')
                return 0
            }
            return 1
        },
        logout() {
            localStorage.clear();
            location.reload()
        }
    }
}
</script>
