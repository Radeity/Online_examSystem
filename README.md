# Online_examSystem
ExamSystem为后端源代码
admin为前端 --管理员和教师端源代码
student为前端 --教师端源代码

#部署：
本地环境需要装有mysql、redis、vue
部署项目需要运行exam.sql并确定运行成功，可show tables查看是否成功新建13张表和4个视图
需要在后端源代码中application.properties中改mysql数据库密码为本地数据库密码
需要在dhu.cst.ExamSystem.config.BaiDuAiConfig中更改三个密钥为百度AI平台自己的密钥
分别进入admin和student，npm install安装依赖，速度慢可以装淘宝镜像使用cnpm


#运行：
启动redis、mysql后并启动后端在8080端口
分别进入admin、student目录执行npm run dev启动前端
