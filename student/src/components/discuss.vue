<template>
    <div class="my-content-container">
    <el-row>
        <el-col :span="20" :offset="2">
            <div class="subject-content">
                <div class="subject-content-star">
                    <div v-if="this.option.favorite">
                        <i class="el-icon-star-on" @click="favorite()" ></i>
                        <span style="color: #F9C93C;">取消收藏</span>
                    </div>
                    <div v-if="!this.option.favorite">
                        <i class="el-icon-star-off" @click="favorite()" ></i>
                        <span>收藏</span>                                
                    </div>
                </div>
                <div class="subject-content-options">
                    <div class="subject-title">
                        <span class="subject-title-content" v-html="this.option.questionName"/><br>
                        <br><span class="subject-title-content" v-html="this.option.opt"/><br><br><br>
                    </div>
                </div>
                <div class="subject-content-options">                                                                                                                                                                                                                                                       
                    <span class="subject-title-number">正误： {{ this.option.answerCheck }}</span><br><br>
                    <span class="subject-title-number">你的答案： {{ this.option.stuAnswer }}</span><br>
                    <span class="subject-title-number">参考答案： {{ this.option.corAnswer }}</span><br>
                </div>
            </div>
        </el-col>
    </el-row>         
    <el-row>
      <el-col :span="20" :offset="2">
         <el-divider></el-divider>
         <span style="margin-left:2%; font-weight:300; font-size:20px">题目讨论</span><br>
         <el-row>
             <img src="../../static/img/avatar/5.jpg" class="image"/>
             <el-input class="discussion" type="textarea" placeholder="我要提问/表达观点......(限400字)" 
             v-model="newque" maxlength="400" style="align-text:center"
             :autosize="{ minRows: 4, maxRows: 10}" show-word-limit></el-input>
         </el-row>
         <el-button type="success" @click="submitques" style="margin-top:10px; float:right; margin-right:10%">提交</el-button>    
      </el-col>
    </el-row>
    <br><br>
    <el-col v-if="discussionList.length!=0" :span="20" :offset="2">    
     <el-tabs @tab-click="handleClick">
         <el-tab-pane label="" name="blanks1" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks2" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks3" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks4" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks5" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks6" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks7" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks8" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks9" :disabled="true" ></el-tab-pane>
         <el-tab-pane label="" name="blanks0" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane label="" name="blanks" :disabled="true"></el-tab-pane>
         <el-tab-pane id="tab1" label="按时间排序" name="first"></el-tab-pane>
         <el-tab-pane id="tab2" label="按点赞数排序" name="second"></el-tab-pane>
     </el-tabs>
     </el-col>
     <el-col :span="20" :offset="2" style="margin-bottom:100px;">
        <div class="discuss-content" v-for="(value, index)  in discussionList" :key="index">
            <br><br>
            <img src="../../static/img/avatar/5.jpg" class="image1"/>
            <div class="discussion1">
                <span class="span" style="font-size:16px; font-weight:350; ">{{ discussionList[index].stuName }}</span><br>
                <!--span是行内元素要加margin-tio必须转为块级"display:block"-->
                <span style="font-size:15px; font-weight:300; white-space:pre-wrap;word-break:break-all;display:block; margin-top:7px">{{ discussionList[index].content }}</span><br><br>
                <i class="el-icon-top-left" @click="reply(index,discussionList[index].reply)" style="font-size:15px; font-weight:100">&nbsp;回复&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>
                <i v-if="discussionList[index].thumbflag" class="iconfont icon-thumbs1" style="color:red" @click="thumbup(index,discussionList[index].reply)"></i>
                <span v-if="discussionList[index].thumbflag" style="font-size:15px; font-weight:100; ">&nbsp;已赞&nbsp;{{discussionList[index].thumb}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>                
                <i v-if="!discussionList[index].thumbflag" class="iconfont icon-thumbs1" style="color:black" @click="thumbup(index,discussionList[index].reply)"></i>
                <span v-if="!discussionList[index].thumbflag" style="font-size:15px; font-weight:100;">&nbsp;赞&nbsp;{{discussionList[index].thumb}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>                
                
                <span @click="replyshow(index,discussionList[index].reply)" style="font-size:15px; font-weight:100; cursor:pointer; "
                onMouseOver="this.style.color='#6593BE';this.style.textDecoration='none'" 
                onMouseOut="this.style.color='#000000';this.style.textDecoration='none'">查看回复({{discussionList[index].replynum}})</span>
                <span style="position:relative; float:right; font-weight:100; font-size:15px">{{discussionList[index].dtime}}</span>
                
                <div v-if="discussionList[index].replyflag" style="width:111%">
                    <br><img src="../../static/img/avatar/5.jpg" class="image1"/>
                    <el-input class="discussion1" type="textarea" placeholder="分享你的思路......(限400字)" 
                    v-model="newreply" maxlength="400" style="align-text:center"
                    :autosize="{ minRows: 4, maxRows: 10}" show-word-limit></el-input>
                    <el-button type="success" @click="submitreply(index)" size="small" style="margin-top:3.5px; float:right; margin-right:10%;">提交</el-button> 
                </div>  

                <!--show reply-->
                <div v-if="discussionList[index].showreply" style="width:111%">            
                    <div class="discuss-content" v-for="(value, i)  in replyList" :key="i">
                        <br><br>
                        <img src="../../static/img/avatar/5.jpg" class="image1"/>
                        <div class="discussion1">
                            <span class="span" style="font-size:16px; font-weight:350; ">{{ replyList[i].stuName }}&nbsp;</span>
                            <span class="span" style="font-size:14px; font-weight:200; ">回复</span>
                            <span class="span" style="font-size:16px; font-weight:350; ">&nbsp;{{replyList[i].replystuname}}</span><br>
                            <span style="font-size:15px; font-weight:300; white-space:pre-wrap;word-break:break-all;display:block; margin-top:7px">{{ replyList[i].content }}</span><br><br>
                            <i class="el-icon-top-left" @click="reply(i,replyList[i].reply)" style="font-size:15px; font-weight:100">&nbsp;回复&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>
                            
                            <i v-if="replyList[i].thumbflag" class="iconfont icon-thumbs1" style="color:red" @click="thumbup(i,replyList[i].reply)"></i>
                            <span v-if="replyList[i].thumbflag" style="font-size:15px; font-weight:100; ">&nbsp;已赞&nbsp;{{replyList[i].thumb}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>                
                            <i v-if="!replyList[i].thumbflag" class="iconfont icon-thumbs1" style="color:black" @click="thumbup(i,replyList[i].reply)"></i>
                            <span v-if="!replyList[i].thumbflag" style="font-size:15px; font-weight:100;">&nbsp;赞&nbsp;{{replyList[i].thumb}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>                
                    
                            <span style="position:relative; float:right; font-weight:100; font-size:15px">{{replyList[i].dtime}}</span>
                            <div v-if="replyList[i].replyflag" style="width:111%">
                                <br><img src="../../static/img/avatar/5.jpg" class="image1"/>
                                <el-input class="discussion1" type="textarea" placeholder="分享你的思路......(限400字)" 
                                v-model="newreplyreply" maxlength="400" style="align-text:center"
                                :autosize="{ minRows: 4, maxRows: 10}" show-word-limit></el-input>
                                <el-button type="success" @click="submitreplyreply(i,index)" size="small" style="margin-top:3.5px; float:right; margin-right:10%;">提交</el-button>    
                            </div>
                        </div><br><br>
                    </div>
                </div>
                <!--end show-->              

            </div><br><br>
            <div class="tableTitle"></div>
        </div>
        <div class="block" v-if="discussnum">
            <br>
         <el-pagination
            @current-change="handleCurrentChange"
            :current-page.sync="currentpage"
            :page-size="pagesize"
            layout="prev, pager, next, jumper"
            :total="discussnum" style="text-align: center">
         </el-pagination>
        </div>
     </el-col>  
     
     </div>

</template>
<script>
export default {
    data () {
        return {
            examRecodeList: [],
            tableKey: 0,
            questionId: 0,
            option: {},
            newque: "",
            newreply: "",
            newreplyreply:"",
            discussionList: [],
            replyList: [],
            currentpage: 1,
            orderthumb: 0,
            discussnum: 0,
            pagesize: 5,
            timer: null
        }
    },
    created () {
        this.questionId = this.$route.params && this.$route.params.id
        this.getQuestion()
        this.getDiscussion(1,5)
    },
    methods: {
        // 加载题目信息
        getQuestion () {
            console.log(localStorage.getItem('authorization'))
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/getquestion?questionId='+this.questionId,
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                this.option = {
                    questionName : response.data.questionName.replace(/\?/g, '______'),
                    stuAnswer : response.data.stuAnswer,
                    answerCheck : response.data.answerCheck,
                    corAnswer : response.data.corAnswer,
                    opt : response.data.opt,
                    id : response.data.questionId,
                    favorite: response.data.favorite,
                    questionType: response.data.questionType,
                }
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
                //localStorage.clear()
                //this.$router.push('/')
            });
        },
        //加载讨论信息
        getDiscussion (page, size) {
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/getdiscussbytime?questionId='+this.questionId+'&pageNum=0',
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {            
                localStorage.setItem('studentid', response.data.studentId)
                localStorage.setItem('studentname', response.data.studentName)
                this.discussnum = response.data.discussnum
                for(var i=0; i<response.data.discuss.length; i++){
                    this.discussionList.push({
                        discussId : response.data.discuss[i].discussId,
                        stuId : response.data.discuss[i].stuId,
                        stuName : response.data.discuss[i].stuName,
                        reply : response.data.discuss[i].reply,
                        replystuid : response.data.discuss[i].replystuid,
                        replystuname : response.data.discuss[i].replystuname,
                        content : response.data.discuss[i].content.replace(/\\n/g, '<br>'),
                        dtime : response.data.discuss[i].dtime,
                        thumb : response.data.discuss[i].thumb,
                        replynum : response.data.replynum[i],
                        replyflag : 0,
                        thumbflag : 0,
                        showreply : 0
                    })
                    console.log(this.discussionList[i].content)         
                }
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
            });
        },
        handleCurrentChange(val){
            if(this.orderthumb==1){
                this.getorderbythumb(val)
            }
            else{
                this.getorderbytime(val)
            }
        },
        getorderbytime(page){
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/getdiscussbytime?questionId='+this.questionId+'&pageNum='+(page-1),
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                this.discussionList.splice(0,this.discussionList.length)
                for(var i=0; i<response.data.discuss.length; i++){
                    this.discussionList.push({
                        discussId : response.data.discuss[i].discussId,
                        stuId : response.data.discuss[i].stuId,
                        stuName : response.data.discuss[i].stuName,
                        reply : response.data.discuss[i].reply,
                        replystuid : response.data.discuss[i].replystuid,
                        replystuname : response.data.discuss[i].replystuname,
                        content : response.data.discuss[i].content.replace(/\\n/g, '<br>'),
                        dtime : response.data.discuss[i].dtime,
                        thumb : response.data.discuss[i].thumb,
                        replynum : response.data.replynum[i],
                        replyflag : 0,
                        thumbflag : 0,
                        showreply : 0
                    })
                    console.log(this.discussionList[i].content)         
                }                              
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
            });            
        },
        getorderbythumb(page){
            this.$ajax({
                method:'get',
                url:'http://localhost:8080/api/getdiscussbythumb?questionId='+this.questionId+'&pageNum='+(page-1),
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                this.discussionList.splice(0,this.discussionList.length)
                for(var i=0; i<response.data.discuss.length; i++){
                    this.discussionList.push({
                        discussId : response.data.discuss[i].discussId,
                        stuId : response.data.discuss[i].stuId,
                        stuName : response.data.discuss[i].stuName,
                        reply : response.data.discuss[i].reply,
                        replystuid : response.data.discuss[i].replystuid,
                        replystuname : response.data.discuss[i].replystuname,
                        content : response.data.discuss[i].content.replace(/\\n/g, '<br>'),
                        dtime : response.data.discuss[i].dtime,
                        thumb : response.data.discuss[i].thumb,
                        replynum : response.data.replynum[i],
                        replyflag : 0,
                        thumbflag : 0,
                        showreply : 0
                    })
                    console.log(this.discussionList[i].content)         
                }                                                                                                                                   
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
            });            
        },
        handleClick: function (tab, event) {
            if(event.target.getAttribute('id')=="tab-first"){
                this.orderthumb = 0
                this.currentpage = 0
                this.getorderbytime(1)
            }
            else{
                this.orderthumb = 1
                this.currentpage = 0
                this.getorderbythumb(1)
            }
        },
        //根据property属性排序(deprecated)
        compare(property){
            return function(a,b){
                var value1 = a[property];
                var value2 = b[property];
                return value2 - value1;
            }
        },
        //根据日期排序(deprecated)
        sortDownDate(a, b) {
            return Date.parse(b.dtime) - Date.parse(a.dtime);
        },
        favorite(){
            this.option.favorite = !this.option.favorite
            var thisurl = ''
            if(this.option.favorite==false)
                thisurl = 'http://localhost:8080/api/unfavorite?QuestionId='
            else if(this.option.favorite==true)
                thisurl = 'http://localhost:8080/api/favorite?QuestionId='
            this.$ajax({
                method:'post',
                url: thisurl + this.option.id,
                headers:{'Authorization':localStorage.getItem('authorization')}
            }).then(response => {
                console.log(response)
            })
            .catch(function(error) {
                console.log('暂无更多数据！')
            });
        },
        //输入回复
        reply(index,flag){
            
            if(flag==0){
                this.discussionList[index].showreply = false
                for(var k=0;k<this.discussionList.length;k++){
                    if(this.discussionList[k].replyflag&&k!=index){
                        this.discussionList[k].replyflag=false
                    }
                }
                this.discussionList[index].replyflag = !this.discussionList[index].replyflag
                this.replyList.splice(0,this.discussionList.length)
            }
            else{
                this.replyList[index].showreply = false
                for(var k=0;k<this.replyList.length;k++){
                    if(this.replyList[k].replyflag&&k!=index){
                        this.replyList[k].replyflag = false;
                    }
                }
                this.replyList[index].replyflag = !this.replyList[index].replyflag
            }
        },
        //展示回复
        replyshow(index,flag){
            this.discussionList[index].replyflag = false
            if(this.discussionList[index].showreply){
                            
                this.discussionList[index].showreply = !this.discussionList[index].showreply
                this.replyList.splice(0,this.replyList.length)
            }
            else{
                for(var j = 0; j < this.discussionList.length; j++){
                    this.discussionList[j].showreply= 0
                }
                this.replyList.splice(0,this.discussionList.length)
                if(flag==0){
                    var thisurl = 'http://localhost:8080/api/getreplybydiscuss/?discussId='+this.discussionList[index].discussId
                }
                else{
                    var thisurl = 'http://localhost:8080/api/getreplybydiscuss/?discussId='+this.replyList[index].discussId
                }
                this.$ajax({
                    method:'get',
                    url: thisurl,
                    headers:{'Authorization':localStorage.getItem('authorization')}
                }).then(response => {
                    if(response.data.discuss.length!=0){
                        this.replyList.splice(0,this.replyList.length)
                        for(j=0;j<this.discussionList.length;j++){
                            this.discussionList[j].showreply = 0
                        }
                        this.discussionList[index].showreply = !this.discussionList[index].showreply
                    }
                    for(var i=0; i<response.data.discuss.length; i++){
                        this.replyList.push({
                            discussId : response.data.discuss[i].discussId,
                            stuId : response.data.discuss[i].stuId,
                            stuName : response.data.discuss[i].stuName,
                            questionId : response.data.discuss[i].questionId,
                            reply : response.data.discuss[i].reply,
                            replydiscuss : response.data.discuss[i].replydiscuss,
                            replystuid : response.data.discuss[i].replystuid,
                            replystuname : response.data.discuss[i].replystuname,
                            content : response.data.discuss[i].content.replace(/\\r\\n/g, '<br>'),      //error
                            dtime : response.data.discuss[i].dtime,
                            thumb : response.data.discuss[i].thumb,
                            replyflag : 0,
                            thumbflag : 0
                        })                      
                    }                  
                })
                .catch(function(error) {
                    console.log('暂无更多数据！')
                });
            }
        },
        thumbup(index, flag){
            var thisurl = ''
            if(flag==1){
                if(this.replyList[index].thumbflag==1){
                    this.replyList[index].thumb = this.replyList[index].thumb-1
                    this.replyList[index].thumbflag = 0
                    thisurl = 'http://localhost:8080/api/unthumb?discussId='
                }
                else{
                    this.replyList[index].thumb = this.replyList[index].thumb+1
                    this.replyList[index].thumbflag = 1
                    thisurl = 'http://localhost:8080/api/thumbup?discussId='
                }
                this.$ajax({
                    method:'post',
                    url: thisurl + this.replyList[index].discussId,
                    headers:{'Authorization':localStorage.getItem('authorization')}
                }).then(response => {
                    console.log(response)
                })
                .catch(function(error) {
                    console.log('暂无更多数据！')
                });
            }
            else{
                if(this.discussionList[index].thumbflag==1){
                    this.discussionList[index].thumb = this.discussionList[index].thumb-1
                    this.discussionList[index].thumbflag = 0
                    thisurl = 'http://localhost:8080/api/unthumb?discussId='
                }
                else{
                    this.discussionList[index].thumb = this.discussionList[index].thumb+1
                    this.discussionList[index].thumbflag = 1
                    console.log('"iconthumb"+index')
                    thisurl = 'http://localhost:8080/api/thumbup?discussId='
                }
                this.$ajax({
                    method:'post',
                    url: thisurl + this.discussionList[index].discussId,
                    headers:{'Authorization':localStorage.getItem('authorization')}
                }).then(response => {
                    console.log(response)
                })
                .catch(function(error) {
                    console.log('暂无更多数据！')
                });
            }
        },        
        // 查看成绩详情 || 参加考试
        handleDetail (status, id, time) {
            if(status != 1){
                this.$router.push({
                    path: '/start/'+ id,
                    query:{ 
                        time: time
                    }
                })
            }
            else{
                this.$router.push({
                    path: '/incorrect/'+ id,
                    query:{ 
                        time: time
                    }
                })
            }
        },
        submitques(){
            let qu = []
            qu.push({
                "questionId" : this.questionId,
                "stuId" : localStorage.getItem('studentid'),
                "stuName" : localStorage.getItem('studentname'),
                "reply" :0,
                "content" : this.newque
            })
            this.$ajax({
                method:'post',
                url:'http://localhost:8080/api/savediscuss',
                data: JSON.stringify({"discuss": qu}),
                headers:{'Authorization':localStorage.getItem('authorization'),'Content-Type':'application/json'}
            }).then(response => {
                alert(response.data.msg)
                location.reload();
                //this.$message(response.data.msg)
            })
        },
        submitreply(index){
            let qu = []
            qu.push({
                "stuId" : parseInt(localStorage.getItem('studentid')),
                "stuName" : localStorage.getItem('studentname'),
                "questionId" : parseInt(this.questionId), 
                "reply" :1,
                "replydiscuss" :this.discussionList[index].discussId,
                "replystuid" :this.discussionList[index].stuId,
                "replystuname" :this.discussionList[index].stuName,
                "content" : this.newreply
            })            
            this.$ajax({
                method:'post',
                url:'http://localhost:8080/api/savediscuss',
                data: JSON.stringify({"discuss": qu}),
                headers:{'Authorization':localStorage.getItem('authorization'),'Content-Type':'application/json'}
            }).then(response => {
                //location.reload();
                this.$message(response.data.msg)
            })
        },
        submitreplyreply(i,index){
            let qu = []
            qu.push({
                "stuId" : parseInt(localStorage.getItem('studentid')),
                "stuName" : localStorage.getItem('studentname'),
                "questionId" : parseInt(this.questionId), 
                "reply" :1,
                "replydiscuss" :this.discussionList[index].discussId,
                "replystuid" :this.replyList[i].stuId,
                "replystuname" :this.replyList[i].stuName,
                "content" : this.newreplyreply
            })            
            this.$ajax({
                method:'post',
                url:'http://localhost:8080/api/savediscuss',
                data: JSON.stringify({"discuss": qu}),
                headers:{'Authorization':localStorage.getItem('authorization'),'Content-Type':'application/json'}
            }).then(response => {
                //location.reload();
                this.$message(response.data.msg)
            })
        }
    }
}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
.my-content-container {
    margin-top: 2rem;
    margin-left: 20px;
    margin-right: 20px;
}
.incorrect-answer-gray-box {
    margin-top: 50px;
    margin-bottom: 50px;
    min-height: 200px;
}
.incorrect-answer-gray-box-title {
    text-align: center;
}
/* 题目 */
.subject-title {
    color: #333333;
    font-size: 16px;
    line-height: 22px;
    margin-bottom: 10px;
    padding-left: 20px;
    position: relative;
    .subject-title-number {
    position: absolute;
    left: -25px;
    top: 0;
    display: inline-block;
    width: 40px;
    line-height: 22px;
    text-align: right;
    }
}
/* 题目选项 */
.subject-option {
    padding-bottom: 10px;
    padding-left: 10px;
}
.score {
    margin: 20px;
}
.correct {
    color: #F56C6C;
}
.right {
    color: #67C23A;
}

.score-gray-box {
    margin-top: 50px;
}
.score-gray-box-title {
    text-align: center;
}
.image {
    width: 60px;
    margin-top:20px;
    margin-left:3%;
    position: absolute;
}

.image1 {
    width: 50px;
    margin-left:3%;
    position: absolute
}

.discussion {
    width:80%;
    margin-top: 20px; 
    margin-left:10%;
    position : relative;
}
.discussion1 {
    width:80%;
    margin-left:10%;
    position: relative
}
.discussion-detail {
    width:80%;
    margin-top: 20px;
}
.subject-content {
    background: #F6F7FA;
    border-radius: 4px;
    margin-bottom: 21px;
    padding: 12px 0 12px 22px;
    //position: relative;
    min-height: 240px;
    color: #666666;
    text-align: left;
}
.subject-content-star {
    position: relative;
    margin-left: 90%;
}
.subject-content-options{
    position: relative;
    margin-top: -20px;
    padding-top: 0px;
    width: 90%;
}
/* 题目 */
.subject-title {
    color: #333333;
    font-size: 16px;
    line-height: 22px;
    margin-bottom: 10px;
    padding-left: 20px;
    position: relative;
    .subject-title-number {
    position: absolute;
    left: -25px;
    top: 0;
    display: inline-block;
    width: 40px;
    line-height: 22px;
    text-align: right;
    }
}
.el-icon-star-on{
    color: #ffe500;
    font-weight: bolder;
    font-size: 1.3rem;
}
.el-icon-star-off{
    font-weight: bolder;
    font-size: 1.2rem;
}
.discuss-content:hover{
    background:#F6F7FA;
}
.tableTitle {
    position: relative;
    margin: 0 auto;
    width: 100%;
    height: 0.5px;
    background-color: #d4d4d4;
    text-align: center;
    color: rgba(101, 101, 101, 1);
  }
  
</style>
