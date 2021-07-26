<template>
    <div class="footer">
        <div class="container">
            <div class="site-info">
                <el-row>
                    <el-col :span="3" :offset="2" class="footer-col">
                        <h4>链接</h4>
                        <a target="_blank" href="http://localhost:8080/admin/index.html#/">教师后台</a>
                        <a target="_blank" href="https://github.com/Radeity/Online_examSystem/">GITHUB源码</a>
                        <a target="_blank" href="https://github.com/Radeity">About Us</a>
                    </el-col>
                    <el-col :span="3" class="footer-col">
                        <h4>工具</h4>
                        <a target="_blank" href="http://element-cn.eleme.io">Element Ui</a>
                        <a target="_blank" href="https://cn.vuejs.org/">Vue</a>
                    </el-col>
                    <el-col :span="3" class="footer-col">
                        <h4>关于</h4>
                        <a target="_blank" href="https://github.com/Radeity/Online_examSystem/issues">联系我们</a>
                    </el-col>
                    <el-col :span="7" class="footer-col">
                        <div class="we-chat">

                        </div>
                        <div class="we-chat-new">
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div class="line"></div>                    
        </div>
    </div>
</template>
<script>
export default {
    data () {
        return {
        }
    },
    methods: {},
    components: {}
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
.footer {
    background-position: 50%;
    background-color: #252525;
    width: 100%;
    box-sizing: border-box;
    height: 400px;
}
.container {
    width: 1150px;
    margin: 0 auto;
    position: relative;
    z-index: 1;
}
.c5 {
    color: #646464;
    right: -70px;
    position: relative;
}
.copyright {
    color: #434d55;
    font-size: 12px;
    padding: 20px 0 0;
    display: flex;
}
.privacy {
    float: left;
    margin: 0 0 0 12px;
}
.content-c0 {
    color: #5079d9;
    cursor: pointer;
    text-decoration: none;
    &:hover {
    color: #3A5FCD;
    }
}
.content-c1 {
    float: left;
    line-height: 12px;
    padding: 1px 10px 0;
    border-left: 1px solid #ccc;
    list-style: none;
}
.content-c2 {
    float: left;
    height: 15px;
    line-height: 15px;
    color: #ffffffa6;
    margin: 0 0 0 12px;
}
.cop {
    text-align: center;
    height: 15px;
}
.content-c3 {
    margin-right: 20px;
    color: #ffffffa6;
    font-size: 12px;
    height: 12px;
    line-height: 1;
}
.footer-col {
    width: 200px;
    float: left;
    margin-top: 30px;
    h4 {
        color: #fff;
        font-size: 30px;
        font-weight: 400;
    }
    a {
        display: block;
        margin: 0;
        line-height: 2.5;
        font-size: 20px;
        font-weight: 400;
        color: #ffffffa6;
    }
    .we-chat {
        position: absolute;
        right: 0;
        width: 216px;
        z-index: 2;
        padding-top: 260px;
        //background-image: url("../../../static/img/core-img/qq.png");
        background-repeat: no-repeat;
        background-position: top;
        text-align: center;
        line-height: 22px;
    }
    .we-chat-new {
        position: absolute;
        width: 216px;
        z-index: 2;
        padding-top: 260px;
        color: #ffffff;
        //background-image: url("../../../static/img/core-img/qq_new.png");
        background-repeat: no-repeat;
        background-position: top;
        text-align: center;
        line-height: 22px;
    }
}
</style>
