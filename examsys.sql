drop database examsys;
create database if not exists examsys;
use examsys;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       mediumint(10) not null AUTO_INCREMENT,
    username varchar(10)  not null COMMENT '号码',
    name     varchar(20)  not null COMMENT '姓名',
    role     varchar(20) not null COMMENT '角色',
    password varchar(100)  not null COMMENT '密码',
    department varchar(20) not null COMMENT '学院',
    gender  varchar(4) not null COMMENT '性别',
    adminclass varchar(10) DEFAULT NULL COMMENT '行政班级',
    state smallint(1) DEFAULT 1 COMMENT '启用状态',
    PRIMARY KEY (id)
);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (2, '4008823', '彭湘玲', 'ADMIN', '$2a$10$U2bmlSVSanFg/RxwQqCnuOAgZH85HbCsFGfHsM0eZ6dz3UehWXYuK', 'cs', '女', null, 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (4, '101000', '丁祥武', 'TEA', '$2a$10$0WvA/TcavQCpqy927qg8mOqzrZgbR7XQ9bxHHRndlT/CLYnTy3z42', '计算机科学与技术', '男', null, 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (5, '101001', '陈德华', 'TEA', '$2a$10$hWRXjrQx1whOR.IYIb9jkej7Vt7ytmHiTH23EX5egh7mb4w34GoyW', '计算机科学与技术', '男', null, 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (8, '2017311551', '刘知恩', 'STU', '$2a$10$ueBAjP7F8bT9HhfYLlMY1uYlVy.A8UlupduZNIeaBdTVP5Q8ILTtK', '计算机科学与技术', '男', '信安1701', 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (9, '2017150008', '许小平', 'STU', '$2a$10$uEWI8.6q9rT0eJZ8w8ssjuyfMqJxQxH3MF1AxGb/Q8F8rvkOdgKWW', '计算机科学与技术', '男', '信安1702', 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (10, '1718020310', '王多鱼', 'STU', '$2a$10$Ecz0Bk8IxyUv2qeyaGt6DeIDw.Nqj4EjYCsIKrvcmFQbAHtwpRUIm', '计算机科学与技术', '男', '信安1702', 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (11, '101002', '李悦', 'TEA', '$2a$10$9A5MoOYbHDxo7WFOWkzete6YjySd8H1izpH8BYRnsUgvYAkjxawDe', '计算机科学与技术', '男', null, 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (12, '101004', '黄秋波', 'TEA', '$2a$10$GkY4o024W2mcswPucG38e.cHZQfryKx4qlbnuPhN9sUgDsu0DriJ.', '计算机科学与技术', '男', null, 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (14, '101005', '王宏建', 'TEA', '$2a$10$x80AWWAS9oj/JOE6v/UnjuyCqjkJAqtz3HgIjbkeqoyuYVhhkZuAy', '计算机科学与技术', '男', null, 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (15, '171717', '张三', 'STU', '$2a$10$52iJpklYsyiyt2T3J5Jo6O66na9BVj9UYypMfG0HAO3339kZ/EN5a', '计算机科学与技术', '女', '计科1701', 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (16, '161616', '李四', 'STU', '$2a$10$xJAzaLybi9UZhMfrBuE79OxAZkTA47JWjC.0mAbkXrDqhxqi98AR2', '计算机科学与技术', '男', '软件1702', 1);
INSERT INTO examsys.user (id, username, name, role, password, department, gender, adminclass, state) VALUES (17, '151515', '王五', 'STU', '$2a$10$2mcNDDTwuG5hLCQEUlvWqOTW5nVBBC5e402ilSo.hd8ggH7QxR7re', '计算机科学与技术', '男', '软工1702', 1);


DROP TABLE IF EXISTS subject;
CREATE TABLE subject
(
    subject_id   mediumint(10) not null AUTO_INCREMENT,
    subject_name varchar(20) not null COMMENT '学科名',
    PRIMARY KEY (subject_id)
);
INSERT INTO examsys.subject (subject_id, subject_name) VALUES (1, '数据库原理与应用');
INSERT INTO examsys.subject (subject_id, subject_name) VALUES (2, '数据结构');
INSERT INTO examsys.subject (subject_id, subject_name) VALUES (3, '密码学');
INSERT INTO examsys.subject (subject_id, subject_name) VALUES (4, 'C语言程序设计');
INSERT INTO examsys.subject (subject_id, subject_name) VALUES (5, '网络安全原理与应用');

DROP TABLE IF EXISTS classinfo;
CREATE TABLE classinfo
(
    class_id   mediumint(11) not null AUTO_INCREMENT,
    class_name varchar(20) not null COMMENT '班级名',
    teacher_id mediumint(10) not null,
    subject_id mediumint(10) not null,
    PRIMARY KEY (class_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id) on delete cascade ON UPDATE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES user (id) on delete cascade ON UPDATE CASCADE
);

INSERT INTO examsys.classinfo (class_id, class_name, teacher_id, subject_id) VALUES (1, '数据库1班', 5, 1);
INSERT INTO examsys.classinfo (class_id, class_name, teacher_id, subject_id) VALUES (2, '数据库2班', 5, 1);
INSERT INTO examsys.classinfo (class_id, class_name, teacher_id, subject_id) VALUES (3, '数据库3班', 5, 1);
INSERT INTO examsys.classinfo (class_id, class_name, teacher_id, subject_id) VALUES (4, '密码学1班', 11, 3);
INSERT INTO examsys.classinfo (class_id, class_name, teacher_id, subject_id) VALUES (5, '网络安全1班', 11, 5);
INSERT INTO examsys.classinfo (class_id, class_name, teacher_id, subject_id) VALUES (6, 'C语言1班', 12, 4);
INSERT INTO examsys.classinfo (class_id, class_name, teacher_id, subject_id) VALUES (7, '数据结构1班', 12, 2);




DROP TABLE IF EXISTS SC;
CREATE TABLE SC
(
    id           mediumint(10) not null AUTO_INCREMENT,
    student_id   mediumint(10) not null,
    class_id     mediumint(10) not null,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classinfo (class_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO examsys.SC (id, student_id, class_id) VALUES (1, 9, 3);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (2, 10, 1);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (3, 8, 2);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (4, 9, 4);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (5, 9, 5);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (6, 9, 6);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (7, 9, 7);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (8, 8, 4);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (9, 8, 5);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (10, 8, 6);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (11, 8, 7);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (12, 10, 5);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (13, 10, 6);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (14, 10, 7);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (15, 10, 4);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (16, 15, 7);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (17, 16, 7);
INSERT INTO examsys.SC (id, student_id, class_id) VALUES (18, 17, 7);

DROP TABLE IF EXISTS point;
CREATE TABLE point
(
    point_id   mediumint(10) not null AUTO_INCREMENT,
    point_name varchar(50) not null COMMENT '知识点名',
    subject_id mediumint(10) not null,
    PRIMARY KEY (point_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (1, '数据模型', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (2, '事务', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (3, '关系代数', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (4, 'SQL语言', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (5, '数据库安全', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (6, '数据库完整性', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (7, '关系数据理论', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (8, '数据库设计', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (9, '数据库编程', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (10, '查询优化', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (11, '并发控制', 1);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (12, '身份认证', 5);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (13, '密码学', 5);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (14, '访问控制', 5);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (15, 'PKI技术', 5);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (16, '防火墙', 5);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (17, 'VPN-IPSec', 5);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (18, '网络协议安全', 5);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (19, '绪论', 2);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (20, '线性表', 2);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (21, '栈和队列', 2);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (22, '树', 2);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (23, '图', 2);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (24, '查找', 2);
INSERT INTO examsys.point (point_id, point_name, subject_id) VALUES (25, '排序', 2);

DROP TABLE IF EXISTS question;
CREATE TABLE question
(
    question_id     mediumint(10) not null AUTO_INCREMENT,
    question_name   varchar(500) DEFAULT NULL COMMENT '题目名称',
    question_type   smallint(1)       DEFAULT NULL COMMENT '题目类型：0.单选 1.多选 2.填空题',
    opt             varchar(500) DEFAULT NULL COMMENT '选项内容',
    question_answer varchar(500) DEFAULT NULL COMMENT '题目答案',
    subject_id      mediumint(10),
    point_id        mediumint(10),
    bank_name       varchar(50)  not null COMMENT '题库名称',
    PRIMARY KEY (question_id),
    FOREIGN KEY (point_id) REFERENCES point (point_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (1, '数据模型的组成要素有?、数据操作、数据的完整性约束条件。', 2, '', '数据结构', 1, 1, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (2, 'SQL语句中的条件以下哪一项来表达？', 0, 'A、THEN, B、WHILE, C、WHERE, D、IF', 'C', 1, 4, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (3, '若要撤销数据库中已经存在的表S，可用?', 0, 'A、DELETE TABLE S, B、DELETE S, C、DROP S, D、DROP TABLE S', 'D', 1, 1, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (4, '查找表结构用以下哪一项?', 0, 'A、FIND, B、SELECT, C、ALTER, D、DESC', 'D', 1, 1, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (5, '在SQL语言中，子查询是?', 0, 'A、选取单表中字段子集的查询语句, B、选取多表中字段子集的查询语句, C、返回单表中数据子集的查询语句, D、嵌入到另一个查询语句之中的查询语句', 'D', 1, 1, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (6, '以下删除记录正确的是?', 0, 'A、Delect from emp where name = ‘dony’, B、delete * from emp where name = ‘dony’, C、drop from emp where name = ‘dony’, D、drop * from emp where name = ‘dony’', 'A', 1, 2, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (9, null, null, null, null, 1, null, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (10, '在关系数据库中存在的完整性规则有?', 1, 'A、实体完整性规则, B、索引完整性规则, C、引用完整性规则, D、用户定义的完整性规则', 'ACD', 1, 1, '19-20 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (12, null, null, null, null, 3, null, '18-19 密码学');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (13, null, null, null, null, 5, null, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (14, '攻击者截获并记录了从A到B的数据，然后又从早些时候所截获的数据中提取出信息
重新发往B称为?', 0, 'A、中间人攻击, B、口令猜测器和字典攻击, C、强力攻击, D、回访攻击            D.  回放攻击', 'D', 5, 12, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (15, 'DES算法加密邮件信息是为了实现哪个安全特性?', 1, 'A、 完整性, B、可用性, C、不可抵赖, D、机密性', 'ABD', 5, 13, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (17, null, null, null, null, 2, null, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (18, '算法的计算量的大小称为计算的()', 0, 'A．效率, B. 复杂性, C. 现实性, D. 难度', 'B', 2, 19, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (19, '36.设F是一个森林，B是由F变换得的二叉树。若F中有n个非终端结点，则B中右指针域为空的结点有（）个。', 0, 'A、n-1, B、n, C、n+1, D、n+2', 'A', 1, 22, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (20, '以下哪种算法为不可逆的数学运算(    )', 0, 'A、MD5, B、RC4, C、IDEA, D、DES', 'C', 5, 12, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (21, '攻击者从网络上窃听他人的通信内容是以下哪种网络攻击类型：', 0, 'A、篡改, B、中断, C、截获, D、伪造', 'D', 5, 12, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (22, '标记安全保护级是TCSEC哪个级别？', 0, 'A、A, B、C, C、B1, D、B2', 'A', 5, 13, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (23, 'Wireshark是用于(    )', 0, 'A、中断网络, B、重放信息, C、VPN, D、监听', 'B', 5, 14, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (24, '以下哪些是DOS攻击(    )', 0, 'A、Ping of Death, B、Jolt2, C、XSS, D、Heart bleeding', 'C', 5, 15, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (25, 'DES 是(    )', 0, 'A、分组密码, B、流密码, C、对称密码, D、Hash', 'D', 5, 16, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (26, '以下哪种情况对破译者最不利（   ）', 0, 'A、仅有密文攻击, B、已知明文攻击, C、选择密文攻击, D、选择明文攻击', 'A', 5, 17, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (27, '关于Feistel结构密码算法的说法正确的是(    )', 0, 'A、Feistel结构是典型的迭代密码, B、Feistel结构需要使用替代, C、Feistel结构的加密和解密完全不一样, D、AES是Feistel结构', 'B', 5, 18, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (28, 'Alice 要用 公钥算法发送加密信息给BoB，保证只用Bob能看，其他人不能解密。要用哪个密钥加密：', 0, 'A、Alice的公钥, B、Bob的公钥, C、Alice的私钥, D、Bob的私钥', 'D', 5, 13, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (29, '以下哪个算法属于公钥算法且基于大整数因子分解问题（   ）', 0, 'A、Deffie-Hellman, B、AES, C、RSA, D、Ei Gamal', 'A', 5, 14, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (30, '身份认证的作用是（   ）', 0, 'A、提供完整性, B、提供保密性, C、对抗假冒攻击, D、确保身份，明确责任', 'B', 5, 15, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (31, '以下哪个认证方法是PAP认证：', 0, 'A、指纹认证, B、口令认证, C、智能卡, D、图片识别码', 'C', 5, 16, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (32, '为了防止哈希过的口令被字典攻击，我们采用了什么方法（   ）', 0, 'A、加时间戳, B、用AES加密, C、加salt, D、加NR', 'D', 5, 17, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (33, '访问控制包括三个要素：', 0, 'A、主体, B、客体, C、认证, D、控制策略', 'A', 5, 18, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (34, '在强制访问控制中，要保证保密性，不可以：', 0, 'A、上读, B、下写, C、上写, D、下读', 'B', 5, 12, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (35, 'PKI如何实现不可否认性：', 0, 'A、加密, B、MAC, C、数字签名, D、数字摘要', 'C', 5, 13, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (36, 'PKI证书标准是：', 0, 'A、X509, B、X508, C、X507, D、X506', 'D', 5, 14, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (37, '关于PKI错误的说法是：', 0, 'A、PKI基于对称加密算法, B、证书废除列表记录了过期或者作废的证书, C、PKI需要CA证书废除列表记录了过期或者作废的证书, D、证书里不包含证书有效期', 'A', 5, 15, '19-20 网络安全');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (38, '算法的计算量的大小称为计算的（    ）。', 0, 'A、效率, B、复杂性, C、现实性, D、难度', 'A', 2, 19, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (39, '一个算法应该是（    ）。', 0, 'A、程序, B、问题求解步骤的描述, C、数据结构+程序 , D、以上都不对', 'B', 2, 19, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (40, '以下属于逻辑结构的是（    ）。', 0, 'A、顺序表, B、哈希表, C、有序表 , D、单链表', 'C', 2, 19, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (41, '以下与数据的存储结构无关的术语是（    ）。', 0, 'A、循环队列, B、链表, C、哈希表, D、栈', 'D', 2, 19, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (42, '线性表（ a1,a2,…,an）以链接方式存储时，访问第i位置元素的时间复杂性为（    ）。', 0, 'A、O（i）, B、O（1）, C、O（n） , D、O（i-1）', 'A', 2, 20, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (43, '对于顺序存储的线性表，访问结点和增加、删除结点的时间复杂度为（    ）。', 0, 'A、O(n)  O(n)  , B、O(n)  O(1)  , C、O(1)  O(n) , D、O(1) O(1)', 'B', 2, 20, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (44, '若某表最常用的操作是在最后一个结点之后插入一个结点或删除最后一个结点。则采用（    ）存储方式最节省运算时间。', 0, 'A、单链表, B、双链表, C、单循环链表, D、带头结点的双循环链表', 'C', 2, 20, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (45, '设一个链表最常用的操作是在末尾插入结点和删除尾结点，则选用(    )最节省时间', 0, 'A、单链表, B、带尾指针的单循环链表, C、单循环链表, D、带头结点的双循环链表', 'D', 2, 20, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (46, '对于栈操作数据的原则是（   ）', 0, 'A、先进先出, B、后进先出, C、后进后出, D、不分顺序', 'A', 2, 21, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (47, '一个栈的输入序列为123…n，若输出序列的第一个元素是n，输出第i（1<=i<=n）个元素是（   ）', 0, 'A、不确定, B、n-i+1 , C、i  , D、n-i', 'B', 2, 21, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (48, '一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈的输出序列的是', 0, 'A、2 3 4 1 5, B、5 4 1 3 2, C、2 3 1 4 5 , D、1 5 4 3 2', 'C', 2, 21, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (49, '递归过程或函数调用时，处理参数及返回地址，要用一种称为（    ）的数据结构', 0, 'A、队列, B、多维数组, C、栈 , D、线性表', 'D', 2, 21, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (50, '设树T的度为4，其中度为1，2，3和4的结点个数分别为4，2，1，1  则T中的叶子数为', 0, 'A、5, B、6, C、7 , D、8', 'A', 2, 22, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (51, '若一棵二叉树具有10个度为2的结点，5个度为1的结点，则度为0的结点个数是（ ）', 0, 'A、9, B、11, C、15 , D、不确定', 'B', 2, 22, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (52, '具有10个叶结点的二叉树中有（  ）个度为2的结点，', 0, 'A、8, B、9, C、10 , D、11', 'C', 2, 22, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (53, '若一棵二叉树有126个节点，在第7层（根节点在第1层）至多有（    ）个节点', 0, 'A、32, B、64, C、63 , D、不存在第7层', 'C', 2, 22, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (54, '图中有关路径的定义是（ ）', 0, 'A、由顶点和相邻顶点序偶构成的边所形成的序列 , B、由不同顶点所形成的序列, C、由不同边所形成的序列  , D、上述定义都不是', 'A', 2, 23, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (55, '一个n个顶点的连通无向图，其边的个数至少为（ ）', 0, 'A、n-1 , B、n, C、n+1  , D、nlogn', 'B', 2, 23, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (56, 'n个结点的完全有向图含有边的数目（ ）', 0, 'A、n*n , B、n(n+1), C、n/2  , D、n*(n-1)', 'C', 2, 23, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (57, '无向图G是 一个连通图，有9条边，则该图至少有（    ）个顶点。', 0, 'A、4 , B、5, C、6 , D、7', 'D', 2, 23, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (58, '下面关于二分查找的叙述正确的是（ ）。', 0, 'A、表必须有序，表可以顺序方式存储，也可以链表方式存储  , B、表必须有序且表中数据必须是整型，实型或字符型, C、表必须有序，而且只能从小到大排列 , D、表必须有序，且表只能以顺序方式存储
', 'A', 2, 24, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (59, '当在一个有序的顺序存储表上查找一个数据时，即可用折半查找，也可用顺序查找，但前者比后者的查找速度（ ）。', 0, 'A、必定快, B、不一定, C、在大部分情况下要快  , D、取决于表递增还是递减', 'B', 2, 24, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (60, '折半查找的时间复杂性为（ ）。', 0, 'A、O（n2）, B、O（n）, C、O（nlogn）  , D、O（logn）', 'C', 2, 24, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (61, '分别以下列序列构造二叉排序树，与用其它三个序列所构造的结果不同的是（ ）', 0, 'A、（100，80， 90， 60， 120，110，130）, B、（100，120，110，130，80， 60， 90）, C、（100，60， 80， 90， 120，110，130）  , D、(100，80， 60， 90， 120，130，110)
', 'D', 2, 24, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (62, '某内排序方法的稳定性是指（ ）。', 0, 'A、该排序算法不允许有相同的关键字记录   , B、该排序算法允许有相同的关键字记录, C、平均时间为0（n log n）的排序方法 , D、以上都不对', 'A', 2, 25, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (63, '下面给出的四种排序法中(    )排序法是不稳定性排序法。', 0, 'A、插入, B、冒泡, C、二路归并 , D、堆排序', 'B', 2, 25, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (64, '若需在O(nlog2n)的时间内完成对数组的排序，且要求排序是稳定的，则可选择的排序方法是（    ）。', 0, 'A、快速排序   , B、堆排序, C、归并排序 , D、直接插入排序', 'C', 2, 25, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (65, '在希尔排序中，最后一趟排序的增量必须为（ ）。', 0, 'A、1, B、3, C、5 , D、7', 'D', 2, 25, '18-19 数据结构');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (67, null, null, null, null, 1, null, '21-22 数据库');
INSERT INTO examsys.question (question_id, question_name, question_type, opt, question_answer, subject_id, point_id, bank_name) VALUES (68, '以下聚合函数求数据总和的是（）', 0, 'A、MAX, B、SUM, C、COUNT, D、AVG', 'B', 1, 4, '21-22 数据库');


DROP TABLE IF EXISTS paper;
CREATE TABLE paper
(
    paper_id   mediumint(10) not null AUTO_INCREMENT,
    paper_name varchar(50) not null COMMENT '试卷名称',
    subject_id mediumint(10) not null,
    subject_name varchar(20) not null,
    single_num   smallint(4)      DEFAULT NULL COMMENT '单选题数量',
    single_score smallint(4)      DEFAULT NULL COMMENT '单选题总分',
    multi_num    smallint(4)      DEFAULT NULL COMMENT '多选题数量',
    multi_score  smallint(4)      DEFAULT NULL COMMENT '多选题总分',
    blank_num    smallint(4)      DEFAULT NULL COMMENT '填空题数量',
    blank_score  smallint(4)      DEFAULT NULL COMMENT '填空题总分',
    PRIMARY KEY (paper_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);

INSERT INTO examsys.paper (paper_id, paper_name, subject_id, subject_name, single_num, single_score, multi_num, multi_score, blank_num, blank_score) VALUES (59, '2019-2020数据库期末考试卷B卷', 1, '数据库原理与应用', 1, 30, 1, 40, 1, 30);
INSERT INTO examsys.paper (paper_id, paper_name, subject_id, subject_name, single_num, single_score, multi_num, multi_score, blank_num, blank_score) VALUES (60, '网络安全原理与应用期中考试', 5, '网络安全原理与应用', 1, 40, 1, 60, 0, 0);
INSERT INTO examsys.paper (paper_id, paper_name, subject_id, subject_name, single_num, single_score, multi_num, multi_score, blank_num, blank_score) VALUES (61, '数据结构期末考试', 2, '数据结构', 10, 100, 0, 0, 0, 0);
INSERT INTO examsys.paper (paper_id, paper_name, subject_id, subject_name, single_num, single_score, multi_num, multi_score, blank_num, blank_score) VALUES (62, '数据库月考', 1, '数据库原理与应用', 3, 90, 0, 0, 1, 10);


DROP TABLE IF EXISTS pdetail;
CREATE TABLE pdetail
(
    detail_id     mediumint(10) not null AUTO_INCREMENT,
    paper_id      mediumint(10) not null,
    question_id   mediumint(10) not null,
    question_type smallint(1) not null,
    opt           varchar(500),
    question_name varchar(500) not null,
    cor_answer    varchar(500) not null,
    point_id      mediumint(10) not null,
    primary key (detail_id),
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question (question_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (101, 59, 1, 2, '', '数据模型的组成要素有?、数据操作、数据的完整性约束条件。', '数据结构', 1);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (116, 59, 10, 1, 'A、实体完整性规则, B、索引完整性规则, C、引用完整性规则, D、用户定义的完整性规则', '在关系数据库中存在的完整性规则有?', 'ACD', 1);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (117, 60, 14, 0, 'A、中间人攻击, B、口令猜测器和字典攻击, C、强力攻击, D、回访攻击            D.  回放攻击', '攻击者截获并记录了从A到B的数据，然后又从早些时候所截获的数据中提取出信息
重新发往B称为?', 'D', 12);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (118, 60, 15, 1, 'A、 完整性, B、可用性, C、不可抵赖, D、机密性', 'DES算法加密邮件信息是为了实现哪个安全特性?', 'ABD', 13);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (121, 59, 2, 0, 'A、THEN, B、WHILE, C、WHERE, D、IF', 'SQL语句中的条件以下哪一项来表达？', 'C', 4);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (123, 61, 19, 0, 'A、n-1, B、n, C、n+1, D、n+2', '36.设F是一个森林，B是由F变换得的二叉树。若F中有n个非终端结点，则B中右指针域为空的结点有（）个。', 'A', 22);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (124, 61, 18, 0, 'A．效率, B. 复杂性, C. 现实性, D. 难度', '算法的计算量的大小称为计算的()', 'B', 19);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (125, 62, 1, 2, '', '数据模型的组成要素有?、数据操作、数据的完整性约束条件。', '数据结构', 1);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (128, 62, 6, 0, 'A、Delect from emp where name = ‘dony’, B、delete * from emp where name = ‘dony’, C、drop from emp where name = ‘dony’, D、drop * from emp where name = ‘dony’', '以下删除记录正确的是?', 'A', 2);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (129, 62, 2, 0, 'A、THEN, B、WHILE, C、WHERE, D、IF', 'SQL语句中的条件以下哪一项来表达？', 'C', 4);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (130, 62, 5, 0, 'A、选取单表中字段子集的查询语句, B、选取多表中字段子集的查询语句, C、返回单表中数据子集的查询语句, D、嵌入到另一个查询语句之中的查询语句', '在SQL语言中，子查询是?', 'D', 1);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (131, 61, 38, 0, 'A、效率, B、复杂性, C、现实性, D、难度', '算法的计算量的大小称为计算的（    ）。', 'A', 19);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (132, 61, 41, 0, 'A、循环队列, B、链表, C、哈希表, D、栈', '以下与数据的存储结构无关的术语是（    ）。', 'D', 19);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (133, 61, 39, 0, 'A、程序, B、问题求解步骤的描述, C、数据结构+程序 , D、以上都不对', '一个算法应该是（    ）。', 'B', 19);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (134, 61, 40, 0, 'A、顺序表, B、哈希表, C、有序表 , D、单链表', '以下属于逻辑结构的是（    ）。', 'C', 19);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (135, 61, 52, 0, 'A、8, B、9, C、10 , D、11', '具有10个叶结点的二叉树中有（  ）个度为2的结点，', 'C', 22);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (136, 61, 63, 0, 'A、插入, B、冒泡, C、二路归并 , D、堆排序', '下面给出的四种排序法中(    )排序法是不稳定性排序法。', 'B', 25);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (137, 61, 58, 0, 'A、表必须有序，表可以顺序方式存储，也可以链表方式存储  , B、表必须有序且表中数据必须是整型，实型或字符型, C、表必须有序，而且只能从小到大排列 , D、表必须有序，且表只能以顺序方式存储
', '下面关于二分查找的叙述正确的是（ ）。', 'A', 24);
INSERT INTO examsys.pdetail (detail_id, paper_id, question_id, question_type, opt, question_name, cor_answer, point_id) VALUES (138, 61, 61, 0, 'A、（100，80， 90， 60， 120，110，130）, B、（100，120，110，130，80， 60， 90）, C、（100，60， 80， 90， 120，110，130）  , D、(100，80， 60， 90， 120，130，110)
', '分别以下列序列构造二叉排序树，与用其它三个序列所构造的结果不同的是（ ）', 'D', 24);

DROP TABLE IF EXISTS exam;
CREATE TABLE exam
(
    exam_id      mediumint(10) not null AUTO_INCREMENT,
    exam_name    varchar(50) DEFAULT NULL COMMENT '考试名称',
    paper_id     mediumint(10) not null,
    subject_id   mediumint(10) not null,
    subject_name varchar(20) not null,
    deadline     datetime  not null COMMENT '截止日期',
    last_time    smallint(10)     not null COMMENT '考试时长',
    paper_name varchar(500) not null,
    state        varchar(15) DEFAULT 'unpublished' comment '试卷状态',
    PRIMARY KEY (exam_id),
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO examsys.exam (exam_id, exam_name, paper_id, subject_id, subject_name, deadline, last_time, paper_name, state) VALUES (108, '数据库期末考试', 59, 1, '数据库原理与应用', '2020-04-30 15:59:59', 120, '2019-2020数据库期末考试卷B卷', 'published');
INSERT INTO examsys.exam (exam_id, exam_name, paper_id, subject_id, subject_name, deadline, last_time, paper_name, state) VALUES (109, '网络安全期中测验', 60, 5, '网络安全原理与应用', '2020-04-16 15:00:00', 90, '网络安全原理与应用期中考试', 'published');
INSERT INTO examsys.exam (exam_id, exam_name, paper_id, subject_id, subject_name, deadline, last_time, paper_name, state) VALUES (110, '数据库期末考试补考', 62, 1, '数据库原理与应用', '2020-04-30 23:59:59', 120, '数据库月考', 'published');
INSERT INTO examsys.exam (exam_id, exam_name, paper_id, subject_id, subject_name, deadline, last_time, paper_name, state) VALUES (111, '数据结构期末考试', 61, 2, '数据结构', '2020-04-30 18:00:00', 120, '数据结构期末考试', 'published');



DROP TABLE IF EXISTS score;
CREATE TABLE score
(
    score_id   mediumint(10) not null AUTO_INCREMENT,
    score      smallint(4) not null COMMENT '分数',
    student_id mediumint(10) not null,
    student_name varchar(20) not null,
    subject_id mediumint(10) not null,
    subject_name varchar(20) not null,
    class_id   mediumint(10) not null,
    paper_id   mediumint(10) not null,
    submit     datetime   DEFAULT NULL COMMENT '提交时间',
    exam_id mediumint(10) not null,
    exam_name varchar(50) not null,
    class_name varchar(20) not null,
    teacher_name varchar(20) not null,
    primary key (score_id),
    FOREIGN KEY (exam_id) REFERENCES exam(exam_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (student_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classinfo (class_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id) ON DELETE CASCADE ON UPDATE CASCADE
);

drop view if exists scoreview;
create view scoreview(score_id,score,student_id,student_no ,student_name,department,
    gender,adminclass,subject_id,subject_name,class_id,class_name,teacher_name,paper_id,submit,exam_id,exam_name)
as select s.score_id, s.score, s.student_id, u.username, s.student_name, u.department, u.gender, u.adminclass,
          s.subject_id, s.subject_name, s.class_id, s.class_name,s.teacher_name, s.paper_id, s.submit, s.exam_id,s.exam_name
from score s,user u where s.student_id = u.id;

INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (174, 70, 10, '王多鱼', 1, '数据库原理与应用', 1, 59, '2020-04-23 22:58:14', 108, '数据库期末考试', '数据库1班', '陈德华');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (175, -1, 8, '刘知恩', 1, '数据库原理与应用', 2, 59, null, 108, '数据库期末考试', '数据库2班', '陈德华');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (176, 30, 9, '许小平', 1, '数据库原理与应用', 3, 59, '2020-04-22 22:40:44', 108, '数据库期末考试', '数据库3班', '陈德华');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (177, -1, 9, '许小平', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (178, -1, 8, '刘知恩', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (179, -1, 10, '王多鱼', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (180, -1, 10, '王多鱼', 1, '数据库原理与应用', 1, 62, null, 110, '数据库期末考试补考', '数据库1班', '陈德华');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (181, -1, 8, '刘知恩', 1, '数据库原理与应用', 2, 62, null, 110, '数据库期末考试补考', '数据库2班', '陈德华');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (182, -1, 9, '许小平', 1, '数据库原理与应用', 3, 62, null, 110, '数据库期末考试补考', '数据库3班', '陈德华');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (183, -1, 9, '许小平', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (184, -1, 8, '刘知恩', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (185, -1, 10, '王多鱼', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (189, -1, 9, '许小平', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (190, -1, 8, '刘知恩', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (191, -1, 10, '王多鱼', 5, '网络安全原理与应用', 5, 60, null, 109, '网络安全期中测验', '网络安全1班', '李悦');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (201, 50, 9, '许小平', 2, '数据结构', 7, 61, '2020-04-24 13:51:01', 111, '数据结构期末考试', '数据结构1班', '黄秋波');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (202, 10, 8, '刘知恩', 2, '数据结构', 7, 61, '2020-04-24 15:27:41', 111, '数据结构期末考试', '数据结构1班', '黄秋波');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (203, 60, 10, '王多鱼', 2, '数据结构', 7, 61, '2020-04-24 13:51:45', 111, '数据结构期末考试', '数据结构1班', '黄秋波');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (204, 60, 15, '张三', 2, '数据结构', 7, 61, '2020-04-24 13:52:22', 111, '数据结构期末考试', '数据结构1班', '黄秋波');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (205, 60, 16, '李四', 2, '数据结构', 7, 61, '2020-04-24 14:01:03', 111, '数据结构期末考试', '数据结构1班', '黄秋波');
INSERT INTO examsys.score (score_id, score, student_id, student_name, subject_id, subject_name, class_id, paper_id, submit, exam_id, exam_name, class_name, teacher_name) VALUES (206, -1, 17, '王五', 2, '数据结构', 7, 61, null, 111, '数据结构期末考试', '数据结构1班', '黄秋波');

DROP TABLE IF EXISTS ec;
CREATE TABLE ec
(
    exam_id     mediumint(10) not null,
    class_id    mediumint(10) not null,
    primary key (exam_id,class_id),
    FOREIGN KEY (exam_id) REFERENCES  exam(exam_id),
    FOREIGN KEY (class_id) REFERENCES classinfo (class_id)
);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (108, 1);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (110, 1);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (108, 2);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (110, 2);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (108, 3);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (110, 3);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (109, 5);
INSERT INTO examsys.ec (exam_id, class_id) VALUES (111, 7);

DROP TABLE IF EXISTS answer;
CREATE TABLE answer
(
    answer_id    mediumint(10) not null AUTO_INCREMENT,
    student_id   mediumint(10) not null,
    paper_id     mediumint(10) not null,
    question_id  mediumint(10) not null,
    stu_answer   varchar(500) DEFAULT NULL COMMENT '学生答案',
    answer_check varchar(1)   not null COMMENT '正误判断',
    answer_point smallint(4) not null COMMENT '本题得分',
    cor_answer  varchar(500) not null COMMENT '正确答案',
    favorite    smallint(1) DEFAULT 0 COMMENT '收藏',
    primary key (answer_id),
    FOREIGN KEY (student_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (paper_id) REFERENCES paper (paper_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question (question_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (26, 9, 59, 2, 'D', 'F', 0, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (27, 9, 59, 10, 'ABC', 'F', 0, 'ACD');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (28, 9, 59, 1, '数据结构', 'T', 30, '数据结构');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (31, 10, 59, 2, 'D', 'F', 0, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (32, 10, 59, 10, 'ACD', 'T', 40, 'ACD');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (33, 10, 59, 1, '数据结构', 'T', 30, '数据结构');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (59, 9, 61, 19, 'A', 'T', 10, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (60, 9, 61, 18, 'B', 'T', 10, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (61, 9, 61, 38, 'C', 'F', 0, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (62, 9, 61, 41, 'D', 'T', 10, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (63, 9, 61, 39, 'A', 'F', 0, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (64, 9, 61, 40, 'B', 'F', 0, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (65, 9, 61, 52, 'C', 'T', 10, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (66, 9, 61, 63, 'D', 'F', 0, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (67, 9, 61, 58, 'A', 'T', 10, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (68, 9, 61, 61, 'B', 'F', 0, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (69, 10, 61, 19, 'A', 'T', 10, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (70, 10, 61, 18, 'C', 'F', 0, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (71, 10, 61, 38, 'B', 'F', 0, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (72, 10, 61, 41, 'D', 'T', 10, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (73, 10, 61, 39, 'A', 'F', 0, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (74, 10, 61, 40, 'C', 'T', 10, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (75, 10, 61, 52, 'C', 'T', 10, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (76, 10, 61, 63, 'B', 'T', 10, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (77, 10, 61, 58, 'A', 'T', 10, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (78, 10, 61, 61, 'B', 'F', 0, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (79, 15, 61, 19, 'A', 'T', 10, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (80, 15, 61, 18, 'B', 'T', 10, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (81, 15, 61, 38, 'D', 'F', 0, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (82, 15, 61, 41, 'C', 'F', 0, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (83, 15, 61, 39, 'A', 'F', 0, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (84, 15, 61, 40, 'B', 'F', 0, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (85, 15, 61, 52, 'C', 'T', 10, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (86, 15, 61, 63, 'B', 'T', 10, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (87, 15, 61, 58, 'A', 'T', 10, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (88, 15, 61, 61, 'D', 'T', 10, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (89, 16, 61, 19, 'B', 'F', 0, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (90, 16, 61, 18, 'B', 'T', 10, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (91, 16, 61, 38, 'B', 'F', 0, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (92, 16, 61, 41, 'D', 'T', 10, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (93, 16, 61, 39, 'B', 'T', 10, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (94, 16, 61, 40, 'C', 'T', 10, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (95, 16, 61, 52, 'C', 'T', 10, 'C');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (96, 16, 61, 63, 'B', 'T', 10, 'B');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (97, 16, 61, 58, 'C', 'F', 0, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (98, 16, 61, 61, 'B', 'F', 0, 'D');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (102, 8, 61, 19, 'B', 'F', 0, 'A');
INSERT INTO examsys.answer (answer_id, student_id, paper_id, question_id, stu_answer, answer_check, answer_point, cor_answer) VALUES (103, 8, 61, 18, 'B', 'T', 10, 'B');

create view paperview(question_id,paper_id,question_type,opt,question_name)
as select question_id,paper_id,question_type,opt,question_name
from pdetail;


drop view if exists answerview;
create view answerview(answer_id,paper_id,student_id,question_id,question_type,opt,question_name,stu_answer,answer_check,cor_answer,answer_point,favorite)
as select a.answer_id,pd.paper_id, a.student_id,pd.question_id, pd.question_type, pd.opt, pd.question_name, a.stu_answer, a.answer_check, a.cor_answer, a.answer_point, a.favorite
from pdetail pd,answer a where pd.question_id = a.question_id and pd.paper_id=a.paper_id;

drop view if exists teacherview;
create view teacherview(teacher_id,teacher_name,subject_id)
as select distinct(usr.id),name,subject_id from user usr,classinfo c where c.teacher_id=usr.id;


set @@global.auto_increment_increment = 1;
set @@auto_increment_increment = 1;


UPDATE examsys.question t SET t.question_name = 'SQL语句中的条件以下哪一项来表达?' WHERE t.question_id = 2;
UPDATE examsys.pdetail t SET t.question_name = 'SQL语句中的条件以下哪一项来表达?' WHERE t.question_id = 2;

CREATE TABLE discuss
(
    discuss_id   mediumint(10) not null AUTO_INCREMENT,
    stu_id       mediumint(10)       not null,
    stu_name     varchar(20)   not null,
    question_id  mediumint(10)       not null,
    reply        smallint(1)   DEFAULT 0 COMMENT '是否为回复',
    replydiscuss mediumint(10) DEFAULT 0 COMMENT '回复的讨论',
    replystuid   mediumint(10)       DEFAULT 0 COMMENT '回复的用户ID',
    replystuname varchar(20)   DEFAULT NULL COMMENT '回复的用户名字',
    content      varchar(400)  DEFAULT NULL COMMENT '讨论内容',
    dtime        datetime      DEFAULT NULL COMMENT '提交时间',
    thumb        mediumint(10) DEFAULT 0 COMMENT '点赞数',
    primary key (discuss_id),
    FOREIGN KEY (stu_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question (question_id) ON DELETE CASCADE ON UPDATE CASCADE
);
