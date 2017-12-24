/*
Navicat MySQL Data Transfer

Source Server         : my_db
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : viroyal_light_db

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2017-12-24 19:51:58
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_light
-- ----------------------------
DROP TABLE IF EXISTS `sys_light`;
CREATE TABLE `sys_light` (
  `id`           BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `status`       VARCHAR(1)
                 CHARACTER SET utf8
                 COLLATE utf8_general_ci NULL     DEFAULT '1'
  COMMENT '1:开灯，0:关灯',
  `voltage`      BIGINT(20)              NULL     DEFAULT NULL
  COMMENT '电压',
  `current`      BIGINT(20)              NULL     DEFAULT NULL,
  `traffic_flow` VARCHAR(1)
                 CHARACTER SET utf8
                 COLLATE utf8_general_ci NULL     DEFAULT '1'
  COMMENT '车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息',
  `province`     VARCHAR(10)
                 CHARACTER SET utf8
                 COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '省',
  `town`         VARCHAR(10)
                 CHARACTER SET utf8
                 COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '市',
  `area`         VARCHAR(10)
                 CHARACTER SET utf8
                 COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '区',
  `street`       VARCHAR(50)
                 CHARACTER SET utf8
                 COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '街道',
  `temperature`  BIGINT(20)              NULL     DEFAULT NULL
  COMMENT '温度',
  `humidity`     BIGINT(20)              NULL     DEFAULT NULL
  COMMENT '湿度',
  `autoreport`   BIGINT(16)              NULL     DEFAULT NULL
  COMMENT '路灯自动上报周期',
  `strategy`     VARCHAR(1)
                 CHARACTER SET utf8
                 COLLATE utf8_general_ci NULL     DEFAULT '1'
  COMMENT '引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Records of sys_light
-- ----------------------------
BEGIN;
INSERT INTO `sys_light` VALUES ('1', '1', '110', '800', '1', '贵州省', '毕节市', '黔西区', '莲城街道', '6', '65', '10000', '4'),
  ('2', '1', '100', '600', '1', '贵州省', '六盘水市', '六枝特区', '九龙街道', '16', '85', '20000', '4'),
  ('3', '0', '90', '700', '0', '贵州省', '六盘水市', '水城县', '尖山街道', '1', '85', '10000', '4'),
  ('4', '1', '140', '800', '1', '贵州省', '遵义市', '红花岗区', '老城街道', '24', '85', '20000', '4');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_info`;
CREATE TABLE `sys_light_info` (
  `id`     BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `code`   VARCHAR(100)
           CHARACTER SET utf8
           COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '路灯编码',
  `info`   VARCHAR(100)
           CHARACTER SET utf8
           COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '路灯信息',
  `status` VARCHAR(1)
           CHARACTER SET utf8
           COLLATE utf8_general_ci NULL     DEFAULT '1'
  COMMENT '1:正在使用，0:刚注册信息并没有投入使用',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Records of sys_light_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_info`
VALUES ('1', '10001', 'xx1路灯', '0'), ('2', '1002', 'xx2路灯', '1'), ('3', '1003', 'xx3路灯', '1'),
  ('4', '1004', 'xx4路灯', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_record`;
CREATE TABLE `sys_light_record` (
  `id`          BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `r_date`      DATETIME                NULL     DEFAULT NULL
  COMMENT '记录时间',
  `r_status`    VARCHAR(255)
                CHARACTER SET utf8
                COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '记录状态',
  `r_operation` VARCHAR(1000)
                CHARACTER SET utf8
                COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '记录操作',
  `r_userid`    BIGINT(20)              NULL     DEFAULT NULL
  COMMENT '谁进行了操作',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 3;

-- ----------------------------
-- Records of sys_light_record
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_record`
VALUES ('1', '2016-06-16 11:15:33', '更新路灯', '对路灯位置进行了修改', '1'), ('2', '2017-12-22 11:01:56', '指派路灯', '对路灯指派了维修人员', '2');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id`    BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `url`   VARCHAR(256)
          CHARACTER SET utf8
          COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT 'url地址',
  `name`  VARCHAR(64)
          CHARACTER SET utf8
          COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT 'url描述',
  `perms` VARCHAR(500)
          CHARACTER SET utf8
          COLLATE utf8_general_ci NULL     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 22;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '/user/user.jsp', '用户列表', 'sys:user:list,sys:user:info'),
  ('2', '/user/edit.jsp', '用户添加', 'sys:user:save,sys:role:select'), ('3', '/user/edit.jsp', '用户删除', 'sys:user:delete'),
  ('4', '/user/edit.jsp', '用户更新', 'sys:user:update,sys:role:select'), ('5', '', '用户Session踢出', 'sys:user:kickout'),
  ('6', '', '用户激活&禁止', 'sys:user:status'), ('7', '/role/role.jsp', '角色列表', 'sys:role:list,sys:role:info'),
  ('8', '/role/edit.jsp', '角色删除', 'sys:role:delete'),
  ('9', '/role/edit.jsp', '角色添加', 'sys:role:save,sys:permission:select'),
  ('10', '/role/edit.jsp', '角色更新', 'sys:role:update,sys:permission:select'),
  ('11', '/permission/permission.jsp', '权限列表', 'sys:permission:list,sys:permission:info'),
  ('12', '/permission/edit.jsp', '权限添加', 'sys:permission:save,sys:permission:select'),
  ('13', '/permission/edit.jsp', '权限删除', 'sys:permission:delete'),
  ('14', '/permission/edit.jsp', '权限更新', 'sys:permission:update,sys:permission:select');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_init`;
CREATE TABLE `sys_permission_init` (
  `id`              BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `url`             VARCHAR(255)
                    CHARACTER SET utf8
                    COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '链接地址',
  `permission_init` VARCHAR(255)
                    CHARACTER SET utf8
                    COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '需要具备的权限',
  `sort`            INT(50)                 NULL     DEFAULT NULL
  COMMENT '排序',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 12;

-- ----------------------------
-- Records of sys_permission_init
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_init`
VALUES ('1', '/static/**', 'anon,kickout', '1'), ('2', '/ajaxLogin', 'anon,kickout', '2'),
  ('3', '/logout', 'logout,kickout', '3'), ('4', '/add', 'perms[权限添加:权限删除],roles[100002],kickout', '4'),
  ('5', '/**', 'user,kickout', '5'), ('6', '/getGifCode', 'anon,kickout', '2'), ('7', '/kickout', 'anon', '2'),
  ('10', '/delete', 'perms[权限删除]', '2'), ('11', '/login', 'anon,kickout', '2');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id`   BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '角色名称',
  `type` VARCHAR(10)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '角色类型',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'admin', '100004'), ('2', 'user', '100001'), ('3', 'worker', '100002');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `rid` BIGINT(20) NULL     DEFAULT NULL
  COMMENT '角色ID',
  `pid` BIGINT(20) NULL     DEFAULT NULL
  COMMENT '权限ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 36;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission`
VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('4', '1', '4'), ('5', '1', '5'), ('6', '1', '6'),
  ('7', '1', '7'), ('8', '1', '8'), ('9', '1', '9'), ('10', '1', '10'), ('11', '1', '11'), ('12', '1', '12'),
  ('13', '1', '13'), ('14', '1', '14'), ('15', '2', '2'), ('16', '2', '3'), ('17', '2', '4'), ('18', '2', '5'),
  ('19', '3', '6');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_strategy
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_strategy`;
CREATE TABLE `sys_light_strategy` (
  `id`            BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `smooth_level`  BIGINT(20)              NULL     DEFAULT NULL
  COMMENT '车流量少时的亮度',
  `traffic_level` VARCHAR(100)
                  CHARACTER SET utf8
                  COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '车流量多时的亮度',
  `open_time`     VARCHAR(100)
                  CHARACTER SET utf8
                  COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '打开时间',
  `close_time`    VARCHAR(100)
                  CHARACTER SET utf8
                  COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '关闭时间',
  `type`          VARCHAR(100)
                  CHARACTER SET utf8
                  COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '冬季，夏季',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Records of sys_light_strategy
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_strategy` VALUES ('1', '200', '255', '18:00:00', '07:00:00', '春季'),
  ('2', '180', '255', '19:45:00', '05:30:00', '夏季'),
  ('3', '180', '255', '19:00:00', '06:00:00', '秋季'),
  ('4', '220', '255', '17:45:00', '07:25:00', '冬季');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id`                  BIGINT(20)              NOT NULL AUTO_INCREMENT,
  `nickname`            VARCHAR(20)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '用户昵称',
  `username`            VARCHAR(20)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL,
  `pswd`                VARCHAR(32)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '密码',
  `phone`               VARCHAR(11)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL,
  `email`               VARCHAR(128)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '邮箱|登录帐号',
  `create_time`         DATETIME                NULL     DEFAULT NULL
  COMMENT '创建时间',
  `last_login_time`     DATETIME                NULL     DEFAULT NULL
  COMMENT '最后登录时间',
  `status`              VARCHAR(1)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT '1'
  COMMENT '1:有效，0:禁止登录',
  `create_name_id`      VARCHAR(255)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL,
  `last_update_time`    DATETIME                NULL     DEFAULT NULL,
  `last_update_name_id` VARCHAR(255)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 19;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES
  ('1', 'admin', 'admin', 'l9a0lajfwQ3VRSh4jUUJSQ==', '15255257895', 'admin@qq.com', '2016-06-16 11:15:33',
        '2017-12-24 19:32:10', '1', NULL, NULL, NULL),
  ('2', '张三', 'zhang', 'eC/MmhASeJBCbfh7W0YU1A==', '13316998986', '123@123.com', '2017-12-24 19:37:40', NULL, '1', '1',
        NULL, NULL),
  ('3', '李四', 'lisi', 'bR28UodwBWRDm+buWIaZbg==', '15575590909', '321@123.com', '2017-12-24 19:38:06', NULL, '1', '1',
        NULL, NULL),
  ('4', '王五', 'wangwu', 'Yb7Dc8/cPeCL8NwOjnYSRA==', '13317979888', '456@123.com', '2017-12-24 19:38:30', NULL, '1', '1',
        NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_light
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_light`;
CREATE TABLE `sys_user_light` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `uid` BIGINT(20) NULL     DEFAULT NULL
  COMMENT '用户ID',
  `lid` BIGINT(20) NULL     DEFAULT NULL
  COMMENT '路灯ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 9;

-- ----------------------------
-- Records of sys_user_light
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_light` VALUES ('1', '2', '1'), ('2', '3', '3'), ('3', '4', '4');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `uid` BIGINT(20) NULL     DEFAULT NULL
  COMMENT '用户ID',
  `rid` BIGINT(20) NULL     DEFAULT NULL
  COMMENT '角色ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 12;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1', '1'), ('2', '2', '3'), ('3', '3', '3'), ('4', '4', '3');
COMMIT;

-- ----------------------------
-- Auto increment value for sys_light
-- ----------------------------
ALTER TABLE `sys_light`
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Auto increment value for sys_light_info
-- ----------------------------
ALTER TABLE `sys_light_info`
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Auto increment value for sys_light_record
-- ----------------------------
ALTER TABLE `sys_light_record`
  AUTO_INCREMENT = 3;

-- ----------------------------
-- Auto increment value for sys_permission
-- ----------------------------
ALTER TABLE `sys_permission`
  AUTO_INCREMENT = 22;

-- ----------------------------
-- Auto increment value for sys_permission_init
-- ----------------------------
ALTER TABLE `sys_permission_init`
  AUTO_INCREMENT = 12;

-- ----------------------------
-- Auto increment value for sys_role
-- ----------------------------
ALTER TABLE `sys_role`
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Auto increment value for sys_role_permission
-- ----------------------------
ALTER TABLE `sys_role_permission`
  AUTO_INCREMENT = 36;

-- ----------------------------
-- Auto increment value for sys_light_strategy
-- ----------------------------
ALTER TABLE `sys_light_strategy`
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Auto increment value for sys_user
-- ----------------------------
ALTER TABLE `sys_user`
  AUTO_INCREMENT = 19;

-- ----------------------------
-- Auto increment value for sys_user_light
-- ----------------------------
ALTER TABLE `sys_user_light`
  AUTO_INCREMENT = 9;

-- ----------------------------
-- Auto increment value for sys_user_role
-- ----------------------------
ALTER TABLE `sys_user_role`
  AUTO_INCREMENT = 12;
