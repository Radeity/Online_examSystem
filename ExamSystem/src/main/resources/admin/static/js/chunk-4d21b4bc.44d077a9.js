(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4d21b4bc"],{"5b8e":function(e,t,a){"use strict";var n=a("7054"),i=a.n(n);i.a},"6c49":function(e,t,a){"use strict";a.d(t,"c",(function(){return i})),a.d(t,"d",(function(){return l})),a.d(t,"f",(function(){return o})),a.d(t,"g",(function(){return r})),a.d(t,"e",(function(){return s})),a.d(t,"a",(function(){return u})),a.d(t,"b",(function(){return c}));var n=a("b775");function i(){return Object(n["a"])({url:"/tea/getsubject",method:"get"})}function l(e){return Object(n["a"])({url:"/tea/getpaper",method:"get",params:e})}function o(e){return Object(n["a"])({url:"/tea/saveorupdatepaper",method:"post",data:e})}function r(e){return Object(n["a"])({url:"/tea/delpaper",method:"DELETE",params:e})}function s(e){return Object(n["a"])({url:"/tea/paperdetailfortea",method:"get",params:e})}function u(e){return Object(n["a"])({url:"/tea/addquetopaper",method:"post",params:e})}function c(e){return Object(n["a"])({url:"/tea/delequefrompaper",method:"delete",params:e})}},7054:function(e,t,a){},"859a":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-col",{attrs:{span:5}},[a("tree",{attrs:{data:e.treeData,"node-click":e.nodeClick}})],1),e._v(" "),a("el-col",{attrs:{span:19}},[e.course?a("paper-table",{ref:"paperTable",attrs:{"subject-id":e.subjectId,handles:{handleCreate:e.handleCreate,handleEdit:e.handleEdit}}}):a("question-table",{ref:"questionTable",attrs:{"subject-id":e.subjectId,"paper-id":e.paperId,handles:{handleQuCreate:e.handleQuCreate}}})],1),e._v(" "),a("paper-dialog",{attrs:{"dialog-data":e.dialogData}}),e._v(" "),a("question-dialog",{attrs:{"dialog-data":e.quDialogData,"subject-id":e.subjectId,"paper-id":e.paperId}})],1)},i=[],l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-card",[a("div",{attrs:{slot:"header"},slot:"header"},[a("span",[e._v("试卷分类")])]),e._v(" "),a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.filterText,callback:function(t){e.filterText=t},expression:"filterText"}}),e._v(" "),a("div",{staticClass:"block"},[a("el-tree",{ref:"tree",attrs:{data:e.data,"node-key":"id","filter-node-method":e.filterNode},on:{"node-click":e.nodeClick}})],1)],1)},o=[],r={props:{data:{type:Array,required:!0},nodeClick:{type:Function,required:!0}},data:function(){return{filterText:""}},watch:{filterText:function(e){this.$refs.tree.filter(e)}},methods:{filterNode:function(e,t){return!e||-1!==t.label.indexOf(e)}}},s=r,u=(a("bea6"),a("2877")),c=Object(u["a"])(s,l,o,!1,null,null,null),p=c.exports,d=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",[a("div",{attrs:{slot:"header"},slot:"header"},[a("span",[e._v("试卷列表")])]),e._v(" "),a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:3}},[a("el-input",{attrs:{placeholder:"输入试卷名称查询"},model:{value:e.pagination.listQuery.name,callback:function(t){e.$set(e.pagination.listQuery,"name",t)},expression:"pagination.listQuery.name"}})],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-button",{attrs:{icon:"el-icon-check",type:"primary"},nativeOn:{click:function(t){return e.handles.handleCreate(t)}}},[e._v("增加")]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-delete",type:"danger"},nativeOn:{click:function(t){return e.handleDeletes(t)}}},[e._v("删除")])],1)],1),e._v(" "),a("my-table",{attrs:{data:e._f("examinationFilter")(e.paperTableData,e.pagination)}})],1)],1)},m=[],b=a("2d63"),h=(a("c5f6"),a("6762"),a("2fdb"),a("7f7f"),a("db72")),f=a("b5b3"),g=a("6c49"),v={components:{MyTable:f["a"]},filters:{examinationFilter:function(e,t){var a=t.listQuery,n=Object(h["a"])({},e),i=a.pageNum,l=a.pageSize,o=a.name;return n.tableData=n.tableData.filter((function(e){return!o||e.paperName.toLowerCase().includes(o.toLowerCase())})),t.total=n.tableData.length,n.tableData=n.tableData.filter((function(e,t){return t<l*i&&t>=l*(i-1)})),n}},props:{subjectId:{type:Number,required:!0},handles:{type:Object,required:!0}},data:function(){return{pagination:{listQuery:{pageNum:1,pageSize:10,course:"",name:"",teacherName:""},total:0},paperTableData:{columnList:[{prop:"paperName",label:"试卷名称"},{prop:"subjectName",label:"所属课程"},{prop:"singleNum",label:"单选择题总数"},{prop:"singleScore",label:"单选题总分"},{prop:"multiNum",label:"多选题总数"},{prop:"multiScore",label:"多选题总分"},{prop:"blankNum",label:"填空题总数"},{prop:"blankScore",label:"填空题总分"}],tableData:[{paperId:"",paperName:"",subjectName:"",singleNum:null,singleScore:null,multiNum:null,multiScore:null,blankNum:null,blankScore:null}],menuList:[{icon:"el-icon-edit",word:"编辑",command:this.handles.handleEdit},{icon:"el-icon-delete",word:"删除",command:this.handleDelete}],loading:!1,handleSelectionChange:this.handleSelectionChange},courseList:[],multipleSelection:[]}},watch:{subjectId:{handler:function(e,t){this.refresh(e)}}},created:function(){0!==this.subjectId&&this.refresh(this.subjectId)},methods:{refresh:function(e){this.getPaperList(e),this.getcourseList()},getPaperList:function(e){var t=this;Object(g["d"])({subjectId:e}).then((function(e){t.paperTableData.tableData=e.data})).catch((function(){alert("根据课程id获取试卷列表失败")}))},getcourseList:function(){var e=this;Object(g["c"])().then((function(t){e.courseList=t.data.map((function(e){return console.log(e.subjectName),e.subjectName})),console.log("courseList",e.courseList)})).catch((function(){alert("获取courseList失败")}))},handleDelete:function(e){var t=this;Object(g["g"])({paperId:e.paperId}).then((function(e){t.refresh(t.subjectId)})).catch((function(e){alert("删除试卷失败"),console.log(e)}))},handleSelectionChange:function(e){this.multipleSelection=e},handleDeletes:function(){var e,t=Object(b["a"])(this.multipleSelection);try{for(t.s();!(e=t.n()).done;){var a=e.value;console.log(a),this.handleDelete(a)}}catch(n){t.e(n)}finally{t.f()}}}},y=v,D=Object(u["a"])(y,d,m,!1,null,null,null),N=D.exports,j=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",[a("div",{attrs:{slot:"header"},slot:"header"},[a("span",[e._v("试题列表")])]),e._v(" "),a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:3}},[a("el-input",{attrs:{placeholder:"输入试题标题查询"},model:{value:e.pagination.listQuery.questionName,callback:function(t){e.$set(e.pagination.listQuery,"questionName",t)},expression:"pagination.listQuery.questionName"}})],1),e._v(" "),a("el-col",{attrs:{span:3}},[a("el-select",{attrs:{placeholder:"选择题型",clearable:""},model:{value:e.pagination.listQuery.questionType,callback:function(t){e.$set(e.pagination.listQuery,"questionType",t)},expression:"pagination.listQuery.questionType"}},e._l(e.questionTypeList,(function(e){return a("el-option",{key:e,attrs:{label:e,value:e,clearable:"",filterable:""}})})),1)],1),e._v(" "),a("el-col",{attrs:{span:3}},[a("el-select",{attrs:{placeholder:"选择知识点",clearable:""},model:{value:e.pagination.listQuery.point,callback:function(t){e.$set(e.pagination.listQuery,"point",t)},expression:"pagination.listQuery.point"}},e._l(e.pointList,(function(e){return a("el-option",{key:e.pointId,attrs:{label:e.pointName,value:e.pointId,clearable:"",filterable:""}})})),1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-button",{attrs:{icon:"el-icon-check",type:"primary"},nativeOn:{click:function(t){return e.handles.handleQuCreate(t)}}},[e._v("增加")]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-delete",type:"danger"},nativeOn:{click:function(t){return e.handleDeletes(t)}}},[e._v("删除")])],1)],1),e._v(" "),a("my-table",{attrs:{data:e._f("questionFilter")(e.myTableData,e.pagination)}}),e._v(" "),a("div",[a("el-pagination",{directives:[{name:"show",rawName:"v-show",value:e.pagination.total>0,expression:"pagination.total>0"}],attrs:{"current-page":e.pagination.listQuery.pageNum,"page-sizes":[10,20,30,50],"page-size":e.pagination.listQuery.pageSize,total:e.pagination.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)],1)},k=[],q=(a("7514"),a("b775"));function I(e){return Object(q["a"])({url:"/tea/getquefrompaper",method:"get",params:e})}var _=a("8bd7"),L={components:{MyTable:f["a"]},filters:{questionFilter:function(e,t){var a=t.listQuery,n=Object(h["a"])({},e),i=a.pageNum,l=a.pageSize,o=a.questionName,r=a.questionType,s=a.point;return n.tableData=n.tableData.filter((function(e){return(!o||e.questionName.toLowerCase().includes(o.toLowerCase()))&&(!r&&0!==r||e.questionType===r)&&(!s||e.pointId===s)})),n.tableData=n.tableData.filter((function(e,t){return t<l*i&&t>=l*(i-1)})),n}},props:{paperId:{type:Number,required:!0},subjectId:{type:Number,required:!0},handles:{type:Object,required:!0}},data:function(){return{pagination:{listQuery:{pageNum:1,pageSize:10,questionType:null,questionName:"",point:""},total:0},myTableData:{columnList:[{prop:"questionName",label:"试题标题"},{prop:"questionType",label:"题型"},{prop:"opt",label:"选项"},{prop:"pointName",label:"知识点"},{prop:"corAnswer",label:"正确答案"}],tableData:[{questionName:"",questionType:"",pointId:"",corAnswer:null}],menuList:[{icon:"el-icon-delete",word:"删除",command:this.handleQuDelete}],loading:!1,handleSelectionChange:this.handleSelectionChange},questionTypeList:["单项选择题","多项选择题","填空题"],pointList:[1,2,3],multipleSelection:[]}},computed:{whatchListquery:function(){return JSON.parse(JSON.stringify(this.pagination.listQuery))}},watch:{paperId:{handler:function(e,t){this.refresh(e,this.subjectId)}},subjectId:{handler:function(e,t){this.refresh(this.paperId,e)}},whatchListquery:{handler:function(e,t){console.log("val",e,t),e.pointId===t.pointId&&e.questionType===t.questionType||(console.log("siugaipagenum"),this.pagination.listQuery.pageNum=1),this.getQuestionList(this.paperId,this.subjectId)},deep:!0}},created:function(){0!==this.paperId&&this.refresh(this.paperId,this.subjectId)},methods:{refresh:function(e,t){this.getQuestionList(e,t)},getQuestionList:function(e,t,a){var n,i=this;n=a?{paperId:e,pointId:a.pointId,type:a.questionType,pageNum:a.pageNum,pageSize:a.pageSize}:{paperId:e,pointId:this.pagination.listQuery.pointId,type:this.pagination.listQuery.questionType,pageNum:this.pagination.listQuery.pageNum,pageSize:this.pagination.listQuery.pageSize},this.getPointList(t),this.myTableData.loading=!0,I(n).then((function(e){i.myTableData.tableData=e.data.questions.map((function(e){e.questionType=i.questionTypeList[e.questionType],console.log("poinstlist",i.pointList);var t=i.pointList.find((function(t){return t.pointId===e.pointId}));return t&&(e.pointName=t.pointName),e})),console.log("data",i.myTableData.tableData),i.pagination.total=e.data.quesnum,i.myTableData.loading=!1})).catch((function(e){console.log(e),alert("获取题目列表失败"),i.myTableData.loading=!1}))},getPointList:function(e){var t=this;Object(_["a"])({subjectId:e}).then((function(e){t.pointList=e.data})).catch((function(){console.log("subject",e),alert("获取pointList失败")}))},handleQuDelete:function(e){var t=this,a={questionId:e.questionId,paperId:this.paperId};Object(g["b"])(a).then((function(e){t.refresh(t.paperId,t.subjectId)})).catch((function(){alert("删除试题失败")}))},handleSelectionChange:function(e){this.multipleSelection=e},handleDeletes:function(){var e,t=Object(b["a"])(this.multipleSelection);try{for(t.s();!(e=t.n()).done;){var a=e.value;this.handleQuDelete(a)}}catch(n){t.e(n)}finally{t.f()}},handleSizeChange:function(e){this.pagination.listQuery.pageSize=e},handleCurrentChange:function(e){this.pagination.listQuery.pageNum=e}}},S=L,T=Object(u["a"])(S,j,k,!1,null,null,null),w=T.exports,Q=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{visible:e.dialogData.dialogVisible,width:"60%",top:"10vh"},on:{"update:visible":function(t){return e.$set(e.dialogData,"dialogVisible",t)}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"90%"},attrs:{model:e.dialogData.formData,"label-position":"right","label-width":"100px"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"试卷名称",prop:"paperName"}},[a("el-input",{attrs:{placeholder:"试卷名称"},model:{value:e.dialogData.formData.paperName,callback:function(t){e.$set(e.dialogData.formData,"paperName",t)},expression:"dialogData.formData.paperName"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"所属课程",prop:"subjectName"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择课程"},model:{value:e.dialogData.formData.subjectName,callback:function(t){e.$set(e.dialogData.formData,"subjectName",t)},expression:"dialogData.formData.subjectName"}},e._l(e.courseList,(function(e){return a("el-option",{key:e.subjectId,attrs:{label:e.subjectName,value:e.subjectName}})})),1)],1)],1)],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"单选题总数",prop:"singleNum"}},[a("el-input",{attrs:{placeholder:"单选题总数"},model:{value:e.dialogData.formData.singleNum,callback:function(t){e.$set(e.dialogData.formData,"singleNum",t)},expression:"dialogData.formData.singleNum"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"单选题总分",prop:"singleScore"}},[a("el-input",{attrs:{placeholder:"单选题总分"},model:{value:e.dialogData.formData.singleScore,callback:function(t){e.$set(e.dialogData.formData,"singleScore",t)},expression:"dialogData.formData.singleScore"}})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"多选题总数",prop:"multiNum"}},[a("el-input",{attrs:{placeholder:"多选题总数"},model:{value:e.dialogData.formData.multiNum,callback:function(t){e.$set(e.dialogData.formData,"multiNum",t)},expression:"dialogData.formData.multiNum"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"多选题总分",prop:"multiScore"}},[a("el-input",{attrs:{placeholder:"多选题总分"},model:{value:e.dialogData.formData.multiScore,callback:function(t){e.$set(e.dialogData.formData,"multiScore",t)},expression:"dialogData.formData.multiScore"}})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"填空总数",prop:"blankNum"}},[a("el-input",{attrs:{placeholder:"填空总数"},model:{value:e.dialogData.formData.blankNum,callback:function(t){e.$set(e.dialogData.formData,"blankNum",t)},expression:"dialogData.formData.blankNum"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"填空题总分",prop:"blankScore"}},[a("el-input",{attrs:{placeholder:"填空题总分"},model:{value:e.dialogData.formData.blankScore,callback:function(t){e.$set(e.dialogData.formData,"blankScore",t)},expression:"dialogData.formData.blankScore"}})],1)],1)],1)],1),e._v(" "),a("div",{attrs:{slot:"footer"},slot:"footer"},[a("el-button",{nativeOn:{click:function(t){e.dialogData.dialogVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},nativeOn:{click:function(t){return e.handleConfirm(t)}}},[e._v("确定")])],1)],1)},O=[],C={filters:{},props:{dialogData:{type:Object,required:!0}},data:function(){return{courseList:[]}},created:function(){this.getcourseList()},methods:{handleConfirm:function(){var e=this;this.dialogData.formData.subjectId=this.courseList.find((function(t){return t.subjectName===e.dialogData.formData.subjectName})).subjectId;var t={paper:[this.dialogData.formData]};Object(g["f"])(t).then((function(t){e.dialogData.dialogVisible=!1,e.$parent.getCourseList()})).catch((function(){alert("添加试卷或修改试卷失败")}))},getcourseList:function(){var e=this;Object(g["c"])().then((function(t){e.courseList=t.data,console.log("courseList",e.courseList)})).catch((function(){alert("获取courseList失败")}))}}},x=C,z=Object(u["a"])(x,Q,O,!1,null,null,null),$=z.exports,E=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{visible:e.dialogData.dialogVisible,width:"60%",top:"10vh"},on:{"update:visible":function(t){return e.$set(e.dialogData,"dialogVisible",t)}}},[a("el-card",[a("div",{attrs:{slot:"header"},slot:"header"},[a("span",[e._v(e._s(e.bankName)+"题库")])]),e._v(" "),a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:3}},[a("el-input",{attrs:{placeholder:"输入试题标题查询",clearable:""},model:{value:e.pagination.listQuery.questionName,callback:function(t){e.$set(e.pagination.listQuery,"questionName",t)},expression:"pagination.listQuery.questionName"}})],1),e._v(" "),a("el-col",{attrs:{span:3}},[a("el-select",{attrs:{placeholder:"选择题库",filterable:""},model:{value:e.bankName,callback:function(t){e.bankName=t},expression:"bankName"}},e._l(e.bankList,(function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),1)],1),e._v(" "),a("el-col",{attrs:{span:3}},[a("el-select",{attrs:{placeholder:"选择题型",clearable:"",filterable:""},model:{value:e.pagination.listQuery.questionType,callback:function(t){e.$set(e.pagination.listQuery,"questionType",t)},expression:"pagination.listQuery.questionType"}},e._l(e.questionList,(function(e,t){return a("el-option",{key:e,attrs:{label:e,value:t}})})),1)],1),e._v(" "),a("el-col",{attrs:{span:3}},[a("el-select",{attrs:{placeholder:"选择知识点",clearable:"",filterable:""},model:{value:e.pagination.listQuery.pointId,callback:function(t){e.$set(e.pagination.listQuery,"pointId",t)},expression:"pagination.listQuery.pointId"}},e._l(e.pointList,(function(e){return a("el-option",{key:e.pointId,attrs:{label:e.pointName,value:e.pointId}})})),1)],1),e._v(" "),a("el-col",{attrs:{span:4}},[a("el-button",{attrs:{icon:"el-icon-check",type:"primary"},nativeOn:{click:function(t){return e.handleAdd(t)}}},[e._v("增加")])],1)],1),e._v(" "),a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.myTableData.loading,expression:"myTableData.loading"}],attrs:{data:e._f("questionFilter")(e.myTableData.tableData,e.pagination)},on:{"selection-change":e.myTableData.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection"}}),e._v(" "),e._l(e.myTableData.columnList,(function(e,t){return a("el-table-column",{key:t,attrs:{prop:e.prop,label:e.label,"show-overflow-tooltip":"",sortable:""}})}))],2)],1),e._v(" "),a("div",[a("el-pagination",{directives:[{name:"show",rawName:"v-show",value:e.pagination.total>0,expression:"pagination.total>0"}],attrs:{"current-page":e.pagination.listQuery.pageNum,"page-sizes":[10,20,30,50],"page-size":e.pagination.listQuery.pageSize,total:e.pagination.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)],1)},V=[],P=(a("96cf"),a("3b8d")),F=a("75fc"),A=a("bc79"),B=a("5c96"),J=(a("56b3"),{filters:{questionFilter:function(e,t){var a=t.listQuery,n=Object(F["a"])(e),i=(a.pageNum,a.pageSize,a.questionName);a.questionType,a.pointId;return n=n.filter((function(e){return!i||e.questionName.toLowerCase().includes(i.toLowerCase())})),n}},props:{dialogData:{type:Object,required:!0},subjectId:{type:Number,required:!0},paperId:{type:Number,required:!0}},data:function(){var e=this;return{pagination:{listQuery:{pageNum:1,pageSize:10,questionName:null,questionType:null,pointId:null},total:10},myTableData:{columnList:[{prop:"questionName",label:"试题标题"},{prop:"questionTypeName",label:"题型"},{prop:"opt",label:"选项"},{prop:"questionAnswer",label:"答案"},{prop:"pointName",label:"知识点"}],tableData:[{questionName:"",questionType:"",knowledge:"",score:""}],loading:!1,handleSelectionChange:function(t){e.multipleSelection=t}},bankList:[],bankName:"",questionList:["单项选择题","多项选择题","填空题"],pointList:[],multipleSelection:[]}},computed:{whatchListquery:function(){return JSON.parse(JSON.stringify(this.pagination.listQuery))}},watch:{subjectId:{handler:function(e,t){console.log("refreshqu",e),this.refresh(e,this.subjectId)}},bankName:{handler:function(e,t){this.getQuestionOfBank(this.subjectId,e)}},whatchListquery:{handler:function(e,t){console.log("val",e,t),e.pointId===t.pointId&&e.questionType===t.questionType||(console.log("siugaipagenum"),this.pagination.listQuery.pageNum=1),this.getQuestionOfBank(this.subjectId,this.bankName,e)},deep:!0}},created:function(){0!==this.subjectId&&this.refresh(this.subjectId)},methods:{refresh:function(){var e=Object(P["a"])(regeneratorRuntime.mark((function e(t){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,this.getBankList(t);case 2:this.bankName=this.bankList[0],console.log("bankName",this.bankName),this.getQuestionOfBank(t,this.bankName);case 5:case"end":return e.stop()}}),e,this)})));function t(t){return e.apply(this,arguments)}return t}(),getQuestionOfBank:function(e,t,a){var n,i=this;n=a?{bankName:t,pointId:a.pointId,type:a.questionType,pageNum:a.pageNum,pageSize:a.pageSize}:{bankName:t,pointId:this.pagination.listQuery.pointId,type:this.pagination.listQuery.questionType,pageNum:this.pagination.listQuery.pageNum,pageSize:this.pagination.listQuery.pageSize},this.getPointList(e),this.myTableData.loading=!0,Object(A["d"])(n).then((function(e){i.myTableData.tableData=e.data.questions.map((function(e){e.questionTypeName=i.questionList[e.questionType];var t=i.pointList.find((function(t){return t.pointId&&t.pointId===e.pointId}));return t&&(e.pointName=t.pointName),e})),i.pagination.total=e.data.quesnum,console.log("total",i.pagination.total),i.myTableData.loading=!1})).catch((function(e){alert("获取题库的题目失败2"),console.log(e),i.myTableData.loading=!1}))},getPointList:function(e){var t=this;Object(_["a"])({subjectId:e}).then((function(e){t.pointList=e.data})).catch((function(){alert("获取pointList失败")}))},getBankList:function(e){var t=this;return Object(A["c"])({subjectId:e}).then((function(e){t.bankList=e.data,console.log("bandk",t.bankList)})).catch((function(){alert("获取题库名失败")}))},handleSizeChange:function(e){this.pagination.listQuery.pageSize=e},handleCurrentChange:function(e){this.pagination.listQuery.pageNum=e},handleAdd:function(){var e,t=this,a=Object(b["a"])(this.multipleSelection);try{for(a.s();!(e=a.n()).done;){var n=e.value,i={questionId:n.questionId,paperId:this.paperId};Object(g["a"])(i).then((function(e){t.dialogData.dialogVisible=!1,t.$parent.refreshQuestionTable(),B["Message"].info(e.data.msg)})).catch((function(e){t.dialogData.dialogVisible=!1,console.log(e)}))}}catch(l){a.e(l)}finally{a.f()}}}}),M=J,R=Object(u["a"])(M,E,V,!1,null,null,null),G=R.exports,H={components:{Tree:p,PaperTable:N,QuestionTable:w,PaperDialog:$,QuestionDialog:G},data:function(){return{course:!0,paperList:[],treeData:[{label:"c语言",children:[{label:"2017-2018c语言期中考试"},{label:"2018-2019c语言期末考试"}]},{label:"面向对象",children:[{label:"面向对象期中考试"},{label:"面向对象期末考试"}]},{label:"java程序设计",children:[{label:"java程序设计期末考试"},{label:"java程序设计期中考试"}]}],dialogData:{dialogVisible:!1,formData:{paperName:"2017-2018c语言期中考试",subjectName:"c语言",singleNum:40,singleScore:20,multiNum:20,multiScore:20,blankNum:20,blankScore:50},handleConfirm:this.handleConfirm},quDialogData:{dialogVisible:!1,handleConfirm:this.handleConfirm},subjectId:0,paperId:0}},created:function(){this.getCourseList()},methods:{nodeClick:function(e,t){t.parent.parent?(this.course=!1,this.paperId=e.id,console.log("data",e)):(this.course=!0,this.subjectId=e.id,console.log("data2",e))},refreshPaperTable:function(){this.$refs.paperTable.refresh(this.subjectId)},refreshQuestionTable:function(){this.$refs.questionTable.refresh(this.paperId,this.subjectId)},getCourseList:function(){var e=this;Object(g["c"])().then((function(t){e.treeData=t.data.map((function(e){var t={id:e.subjectId,label:e.subjectName,children:[]};return Object(g["d"])({subjectId:t.id}).then((function(e){t.children=e.data.map((function(e){return{label:e.paperName,id:e.paperId}}))})).catch((function(){alert("获取试卷名失败")})),t})),0===e.subjectId&&(e.subjectId=e.treeData[0].id),e.refreshPaperTable(),setTimeout((function(){}),500)})).catch((function(){alert("错误")}))},handleClean:function(){this.dialogData.formData={subjectId:this.subjectId,paperName:"",subjectName:"",singleNum:null,singleScore:null,multiNum:null,multiScore:null,blankNum:null,blankScore:null}},handleCreate:function(){this.handleClean(),this.dialogData.dialogVisible=!0},handleEdit:function(e){this.dialogData.formData=e,this.dialogData.dialogVisible=!0},handleQuCreate:function(){this.quDialogData.dialogVisible=!0,console.log("quDialogData.dialogVisible")}}},K=H,U=Object(u["a"])(K,n,i,!1,null,null,null);t["default"]=U.exports},"8bd7":function(e,t,a){"use strict";a.d(t,"a",(function(){return i}));var n=a("b775");function i(e){return Object(n["a"])({url:"/tea/getpointbysubjectid",method:"get",params:e})}},"9e76":function(e,t,a){},b5b3:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.data.loading,expression:"data.loading"}],attrs:{data:e.data.tableData},on:{"selection-change":e.data.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection"}}),e._v(" "),e._l(e.data.columnList,(function(e,t){return a("el-table-column",{key:t,attrs:{prop:e.prop,label:e.label,"show-overflow-tooltip":"",sortable:""}})})),e._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-dropdown",{on:{command:e.handleCommand}},[a("span",{staticClass:"el-dropdown-link"},[e._v("\n            下拉菜单\n            "),a("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},e._l(e.data.menuList,(function(n,i){return a("el-dropdown-item",{key:i,attrs:{icon:n.icon,command:{command:n.command,row:t.row}}},[e._v(e._s(n.word))])})),1)],1)]}}])})],2)],1)},i=[],l={props:{data:{type:Object,required:!0}},data:function(){return{}},methods:{handleCommand:function(e){e.command(e.row)}}},o=l,r=(a("5b8e"),a("2877")),s=Object(r["a"])(o,n,i,!1,null,null,null);t["a"]=s.exports},bc79:function(e,t,a){"use strict";a.d(t,"c",(function(){return i})),a.d(t,"d",(function(){return l})),a.d(t,"b",(function(){return o})),a.d(t,"g",(function(){return r})),a.d(t,"e",(function(){return s})),a.d(t,"f",(function(){return u})),a.d(t,"a",(function(){return c}));var n=a("b775");function i(e){return Object(n["a"])({url:"/tea/getbank",method:"get",params:e})}function l(e){return Object(n["a"])({url:"/tea/questionintegratedquery",method:"get",params:e})}function o(e){return Object(n["a"])({url:"/tea/delequestion",method:"delete",params:e})}function r(e){return Object(n["a"])({url:"/tea/saveorupdatequestion",method:"post",data:e})}function s(e){return Object(n["a"])({url:"/tea/addbank",method:"post",params:e})}function u(e){return Object(n["a"])({url:"/tea/updatebank",method:"post",params:e})}function c(e){return Object(n["a"])({url:"/tea/delebank",method:"delete",params:e})}},bea6:function(e,t,a){"use strict";var n=a("9e76"),i=a.n(n);i.a}}]);