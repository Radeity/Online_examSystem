/*create database onlineshop;
use onlineshop;

Create table userTable (
	id  int(11)  primary key auto_increment,
    Username  varchar (20) ,
    Password  varchar (20)
);
Create table GoodTable (
	id  int(11)  primary key auto_increment,
	price int(11),
    goodName  varchar (20)
);
Create table OrderTable (
	orderId  int(11)  primary key auto_increment,
    userId  int(11) references userTable(id),
    goodId int(11) references GoodTable(id),
	price int(11) references GoodTable(price),
	date datetime
);
alter table GoodTable add inventory varchar(20);
alter table GoodTable modify inventory int(11);
insert into GoodTable values(1,649,'SS20 SUPREME 长袖T恤',10);
insert into GoodTable values(2,599,'SS20 SUPREME TEE',10);
insert into OrderTable(userId, goodId, price, date) values(1,1,649,'2020-03-08');
delete from OrderTable;
update GoodTable set inventory=10 where id=1;
update GoodTable set inventory=10 where id=2;
insert into GoodTable values(1,959,'小李子:专柜正品adidas阿迪达斯猎鹰20.1 L AG短钉足球鞋男FW1066',10);
insert into GoodTable values(2,3341,'TheNorthFace北面1990MountainJacket冲锋衣',10);
insert into GoodTable values(3,593,'Roaringwild/咆哮野兽AW19 POLAR FLEECE 破断刺绣摇粒绒外套',10);
insert into GoodTable values(4,99,'【设计师合作款】男装 宽松圆领T恤(短袖) 422995 优衣库UNIQLO',10);
insert into GoodTable values(5,1129,'CDG PLAY x Converse 匡威1970S三星标 川久保玲帆布鞋 150204c',10);
insert into GoodTable values(6,498,'RETROMIND 19SS 美式改良洗水竹节纹工装裤宽松直筒军事风休闲裤',10);


delete from GoodTable where id='1';
delete from GoodTable where id='2';
alter table GoodTable modify goodName varchar(50);
delete from userTable where Password='5';

alter table OrderTable add goodName varchar(20);
alter table OrderTable modify goodName varchar(50);

update GoodTable set inventory=10;*/
create database if not exists examsys;
use examsys;
DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    student_id   int(10) not null AUTO_INCREMENT,
    student_no   varchar(20) DEFAULT NULL COMMENT '学号',
    student_name varchar(20) DEFAULT NULL COMMENT '姓名',
    student_pwd  varchar(50) DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (student_id)
);
insert into student values(1,171310122,'王维饶','666666');
insert into student values(2,171310121,'郭峰','123456');

DROP TABLE IF EXISTS admin;
CREATE TABLE admin
(
    admin_id    int(10) not null AUTO_INCREMENT,
    admin_no   varchar(20) DEFAULT NULL COMMENT '管理员号',
    admin_name varchar(20) DEFAULT NULL COMMENT '姓名',
    admin_pwd  varchar(50) DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (admin_id)
);
insert into admin values(1,4008823,'彭湘玲','123456');


DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher
(
    teacher_id   int(10) not null AUTO_INCREMENT,
    teacher_no   varchar(20) DEFAULT NULL COMMENT '教师号',
    teacher_name varchar(20) DEFAULT NULL COMMENT '姓名',
    teacher_pwd  varchar(50) DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (teacher_id)
);
insert into teacher values(1,101000,'陈德华','111111');
insert into teacher values(2,101001,'丁祥武','222222');

DROP TABLE IF EXISTS classinfo;
CREATE TABLE classinfo
(
    class_id   int(11) not null AUTO_INCREMENT,
    class_name varchar(20) DEFAULT NULL COMMENT '班级名',
    PRIMARY KEY (class_id)
);
insert into classinfo values(1,'数据库1班');
insert into classinfo values(2,'数据库2班');
insert into classinfo values(3,'数据库3班');


DROP TABLE IF EXISTS subject;
CREATE TABLE subject
(
    subject_id   int(10) not null AUTO_INCREMENT,
    subject_name varchar(20) DEFAULT NULL COMMENT '学科名',
    PRIMARY KEY (subject_id)
);
insert into subject values(1,'数据库原理与应用');

DROP TABLE IF EXISTS SC;
CREATE TABLE SC
(
    id         int(10) not null AUTO_INCREMENT,
    student_id int(10),
    class_id   int(10),
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (class_id) REFERENCES classinfo (class_id)
);
insert into SC values(1,1,1);
insert into SC values(2,2,1);

DROP TABLE IF EXISTS TC;
CREATE TABLE TC
(
    id  int(10) not null AUTO_INCREMENT,
    teacher_id int(10),
    subject_id int(10),
    class_id   int(10),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id),
    FOREIGN KEY (class_id) REFERENCES classinfo (class_id)
);
insert into TC values(1,1,1,1);

DROP TABLE IF EXISTS point;
CREATE TABLE point
(
    point_id   int(10) not null AUTO_INCREMENT,
    point_name varchar(50) DEFAULT NULL COMMENT '知识点名',
    subject_id int(10),
    PRIMARY KEY (point_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);
insert into point values(1,'数据模型',1);

DROP TABLE IF EXISTS question;
CREATE TABLE question
(
    question_id     int(10) not null AUTO_INCREMENT,
    question_name   varchar(500) DEFAULT NULL COMMENT '题目名称',
    question_type   int(4)       DEFAULT NULL COMMENT '题目类型：0.单选 1.多选 2.填空题',
    opt             varchar(500) DEFAULT NULL COMMENT '选项内容',
    question_answer varchar(500) DEFAULT NULL COMMENT '题目答案',
    subject_id      int(10),
    point_id        int(10),
    PRIMARY KEY (question_id),
    FOREIGN KEY (point_id) REFERENCES point (point_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);
insert into question values(1,'数据模型的组成要素有?、数据操作、数据的完整性约束条件。',2,'','数据结构',1,1);

DROP TABLE IF EXISTS paper;
CREATE TABLE paper
(
    paper_id   int(10) not null AUTO_INCREMENT,
    paper_name varchar(500) DEFAULT NULL COMMENT '试卷名称',
    subject_id int(10),
    PRIMARY KEY (paper_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);
insert into paper values('1','2019-2020数据库期末考试卷A卷',1);

DROP TABLE IF EXISTS compose;
CREATE TABLE compose
(
    compose_id int(10) not null AUTO_INCREMENT,
    paper_id   int(10),
    teacher_id int(10),
    PRIMARY KEY (compose_id),
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id)
);
insert into compose values(1,1,1);
insert into compose values(2,1,2);

DROP TABLE IF EXISTS exam;
CREATE TABLE exam
(
    exam_id      int(10) not null AUTO_INCREMENT,
    exam_name    varchar(50) DEFAULT NULL COMMENT '考试名称',
    paper_id     int(10),
    subject_id   int(10),
    single_num   int(4)      DEFAULT NULL COMMENT '单选题数量',
    single_score int(4)      DEFAULT NULL COMMENT '单选题总分',
    multi_num    int(4)      DEFAULT NULL COMMENT '多选题数量',
    multi_score  int(4)      DEFAULT NULL COMMENT '多选题总分',
    blank_num    int(4)      DEFAULT NULL COMMENT '填空题数量',
    blank_score  int(4)      DEFAULT NULL COMMENT '填空题总分',
    deadline     datetime COMMENT '截止日期',
    last_time    int(10)     DEFAULT NULL COMMENT '考试时长',
    PRIMARY KEY (exam_id),
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id),
);
insert into exam values(1,'数据库期末考试',1,1,0,0,0,0,1,100,'2017-03-20 23:59:59',120);

DROP TABLE IF EXISTS pdetail;
CREATE TABLE pdetail
(
    detail_id     int(10) not null AUTO_INCREMENT,
    paper_id      int(10),
    question_id   int(10),
    question_type int(4),
    opt           varchar(500),
    question_name varchar(500),
    cor_answer    varchar(500),
    primary key (detail_id),
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
);
insert into pdetail values(1,1,1,2,'','数据模型的组成要素有?、数据操作、数据的完整性约束条件。','数据结构');

DROP TABLE IF EXISTS quema;
CREATE TABLE quema
(
    quema_id   int(10) not null AUTO_INCREMENT,
    subject_id int(10),
    teacher_id int(10),
    primary key (quema_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id)
);
insert into quema values(1,1,1);

DROP TABLE IF EXISTS answer;
CREATE TABLE answer
(
    answer_id   int(10) not null AUTO_INCREMENT,
    student_id  int(10),
    paper_id    int(10),
    question_id int(10),
    stu_answer  varchar(500) DEFAULT NULL COMMENT '学生答案',
    answer_check varchar(1)  DEFAULT NULL COMMENT '正误判断',
    primary key (answer_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
);
insert into answer values(1,1,1,1,'数据结构','F');

DROP TABLE IF EXISTS score;
CREATE TABLE score
(
    score_id   int(10) not null AUTO_INCREMENT,
    score      int(4) DEFAULT NULL COMMENT '分数',
    student_id int(10),
    student_name varchar(500),
    subject_id int(10),
    class_id   int(10),
    paper_id   int(10),
    submit     datetime   DEFAULT NULL COMMENT '提交时间',
    primary key (score_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id),
    FOREIGN KEY (class_id) REFERENCES classinfo (class_id),
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id)
);
insert into score values(1,100,1,1,1,1,'2017-03-20 15:20:16');

DROP TABLE IF EXISTS ec;
CREATE TABLE ec
(
    id  int(10) not null AUTO_INCREMENT,
    exam_id     int(10),
    class_id    int(10),
    primary key (id),
    FOREIGN KEY (exam_id) REFERENCES  exam(exam_id),
    FOREIGN KEY (class_id) REFERENCES classinfo (class_id)
);

set @@global.auto_increment_increment = 1;
set @@auto_increment_increment = 1;