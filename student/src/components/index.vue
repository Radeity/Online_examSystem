<template>
    <div>
        <el-row class="hero-area" type="flex" justify="center" align="middle">
            <el-col :span="24">
                <div class="hero-content">
                <h2>Online-Exam 在线考试系统</h2>
                <h4>基于SpringBoot+Vue搭建的新一代在线教学管理平台</h4>
                <h4>提供多用户、权限管理、考试、练习等功能</h4>
                <router-link to="/exams" class="btn clever-btn">开始使用</router-link>
                </div>
            </el-col>
        </el-row>
        <div class="cool-facts-area padding-100-0">
            <el-row type="flex" justify="center" :gutter="50">
                <el-col :span="4">
                    <transition name="fade-transform" mode="out-in">
                        <div class="single-cool-facts-area mb-100" v-show="showFacts">
                            <div class="icon">
                                <img src="static/img/core-img/star.png" alt="">
                            </div>
                            <h2><count-to :start-val="0" :end-val="200" :duration="2600" class="counter"/></h2>
                            <h5>用户数</h5>
                        </div>
                    </transition>
                </el-col>
                <el-col :span="4">
                    <transition name="fade-transform" mode="out-in">
                        <div class="single-cool-facts-area mb-100" v-show="showFacts">
                            <div class="icon">
                                <img src="static/img/core-img/star.png" alt="">
                            </div>
                            <h2><count-to :start-val="0" :end-val="500" :duration="2600" class="counter"/></h2>
                            <h5>考试数</h5>
                        </div>
                    </transition>
                </el-col>
                <el-col :span="4">
                    <transition name="fade-transform" mode="out-in">
                        <div class="single-cool-facts-area mb-100" v-show="showFacts">
                            <div class="icon">
                                <img src="static/img/core-img/star.png" alt="">
                            </div>
                            <h2><count-to :start-val="0" :end-val="233" :duration="2600" class="counter"/></h2>
                            <h5>题目数</h5>
                        </div>
                    </transition>
                </el-col>
                <el-col :span="4">
                    <transition name="fade-transform" mode="out-in">
                        <div class="single-cool-facts-area mb-100" v-show="showFacts">
                            <div class="icon">
                                <img src="static/img/core-img/star.png" alt="">
                            </div>
                            <h2><count-to :start-val="0" :end-val="300" :duration="2600" class="counter"/></h2>
                            <h5>课程数</h5>
                        </div>
                    </transition>
                </el-col>
            </el-row>
        </div>
        <div v-if="isActive" class="go-top-box" @click="goTop(step)">
            <i class="top-icon el-icon-caret-top"></i>
        </div>
        <o-footer></o-footer>
    </div>
</template>

<script>
import OFooter from './common/footer'
import CountTo from 'vue-count-to'
export default {
    props: {
        step: {
            type: Number,
            default: 50
        }
    },
    data () {
        return {
            isActive: false,
            showFacts: false,
            showCourses: false,
            showBlog: false
        }
    },
    components: {
        OFooter,
        CountTo
    },
    methods: {
        // 返回顶部
        goTop: function (i) {
            document.documentElement.scrollTop -= i
            if (document.documentElement.scrollTop > 0) {
                setTimeout(() => this.goTop(i), 16)
            }
        }
    },
    created () {
        let vm = this
        window.onscroll = function () {
            vm.isActive = document.documentElement.scrollTop > 60
            if (document.documentElement.scrollTop > 300) {
                setTimeout(() => {
                    vm.showCourses = true
                }, 350)
            }
            if (document.documentElement.scrollTop > 800) {
                setTimeout(() => {
                    vm.showBlog = true
                }, 350)
            }
        }
        setTimeout(() => {
            vm.showFacts = true
        }, 350)
    }
}
</script>
