# Online_examSystem
- 后端 ExamSystem
- 前端 admin_teacher (管理员和教师端)
- 前端 student（学生端）

# 部署：
本地环境需要装有mysql、redis、vue

部署项目需要运行exam.sql并确定运行成功

需要在后端源代码中application.properties中改mysql数据库密码为本地数据库密码

需要在dhu.cst.ExamSystem.config.BaiDuAiConfig中更改三个密钥为百度AI平台自己的密钥

分别进入admin_teacher和student，npm install安装依赖，速度慢可以装淘宝镜像使用cnpm


# 运行：
启动redis、mysql后并启动后端在8080端口
分别进入admin、student目录执行npm run dev启动前端


# 实现功能
1. 管理员端：用户信息增删查改 用户权限启用、禁用
  
2. 教师端：题库管理 试卷管理 组卷 考试管理 可视化成绩查询 试卷分析
  
3. 学生端：参加考试 成绩查询 题目收藏 题目讨论
