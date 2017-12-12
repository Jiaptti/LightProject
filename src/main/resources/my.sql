/*
Navicat MySQL Data Transfer

Source Server         : my_db
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : viroyal_light_db

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-12-12 10:21:50
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id`   VARCHAR(64)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NOT NULL,
  `url`  VARCHAR(256)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT 'url地址',
  `name` VARCHAR(64)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT 'url描述',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission`
VALUES ('10', '/member/changeSessionStatus.shtml', '用户Session踢出'), ('11', '/member/forbidUserById.shtml', '用户激活&禁止'),
  ('12', '/member/deleteUserById.shtml', '用户删除'), ('16', '/role/deleteRoleById.shtml', '角色列表删除'),
  ('17', '/role/addRole.shtml', '角色列表添加'), ('18', '/role/index.shtml', '角色列表'),
  ('19', '/permission/allocation.shtml', '权限分配'), ('20', '/role/allocation.shtml', '角色分配'),
  ('4', '/permission/index.shtml', '权限列表'), ('6', '/permission/addPermission.shtml', '权限添加'),
  ('7', '/permission/deletePermissionById.shtml', '权限删除'), ('8', '/member/list.shtml', '用户列表'),
  ('9', '/member/online.shtml', '在线用户');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_init`;
CREATE TABLE `sys_permission_init` (
  `id`              VARCHAR(255)
                    CHARACTER SET utf8
                    COLLATE utf8_general_ci NOT NULL,
  `url`             VARCHAR(255)
                    CHARACTER SET utf8
                    COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT '链接地址',
  `permission_init` VARCHAR(255)
                    CHARACTER SET utf8
                    COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT '需要具备的权限',
  `sort`            INT(50)                 NULL DEFAULT NULL
  COMMENT '排序',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of sys_permission_init
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_init`
VALUES ('1', '/static/**', 'anon,kickout', '1'), ('2', '/ajaxLogin', 'anon,kickout', '2'),
  ('3', '/logout', 'logout,kickout', '3'), ('4', '/add', 'perms[权限添加:权限删除],roles[100002],kickout', '4'),
  ('5', '/**', 'user,kickout', '5'), ('6', '/getGifCode', 'anon,kickout', '2'), ('7', '/kickout', 'anon', '2');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id`   VARCHAR(64)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` VARCHAR(32)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT '角色名称',
  `type` VARCHAR(10)
         CHARACTER SET utf8
         COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT '角色类型'
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'admin', '100004'), ('3', 'user', '100001'), ('4', 'worker', '100002'),
  ('ae236732036f43cdbad6864cbeefd14a', '角色管理', '100003');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id`  VARCHAR(64)
        CHARACTER SET utf8
        COLLATE utf8_general_ci NOT NULL,
  `rid` VARCHAR(64)
        CHARACTER SET utf8
        COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT '角色ID',
  `pid` VARCHAR(64)
        CHARACTER SET utf8
        COLLATE utf8_general_ci NULL DEFAULT NULL
  COMMENT '权限ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission`
VALUES ('1', '4', '8'), ('10', '3', '14'), ('11', '3', '15'), ('12', '3', '16'), ('13', '3', '17'), ('14', '3', '18'),
  ('15', '3', '19'), ('16', '3', '20'), ('17', '1', '4'), ('18', '1', '6'), ('19', '1', '7'), ('2', '4', '9'),
  ('20', '1', '8'), ('21', '1', '9'), ('22', '1', '10'), ('23', '1', '11'), ('24', '1', '12'), ('25', '1', '13'),
  ('26', '1', '14'), ('27', '1', '15'), ('28', '1', '16'), ('29', '1', '17'), ('3', '4', '10'), ('30', '1', '18'),
  ('31', '1', '19'), ('32', '1', '20'), ('4', '4', '11'), ('5', '4', '12'), ('6', '3', '4'), ('7', '3', '6'),
  ('8', '3', '7'), ('9', '3', '13');
COMMIT;

-- ----------------------------
-- Table structure for sys_street
-- ----------------------------
DROP TABLE IF EXISTS `sys_street`;
CREATE TABLE `sys_street` (
  `sid`         INT(11)                 NOT NULL,
  `street_name` VARCHAR(100)
                CHARACTER SET utf8
                COLLATE utf8_general_ci NULL DEFAULT NULL,
  `street_area` VARCHAR(50)
                CHARACTER SET utf8
                COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of sys_street
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id`                  INT(64)                 NOT NULL AUTO_INCREMENT,
  `nickname`            VARCHAR(20)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '用户昵称',
  `username`            VARCHAR(20)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL,
  `email`               VARCHAR(128)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '邮箱|登录帐号',
  `phone`               VARCHAR(11)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL,
  `pswd`                VARCHAR(32)
                        CHARACTER SET utf8
                        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '密码',
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
  AUTO_INCREMENT = 15;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES
  ('1', 'admin', '管理员', 'admin@qq.com', '15555555555', 'l9a0lajfwQ3VRSh4jUUJSQ==', '2016-06-16 11:15:33',
        '2017-12-12 09:45:55', '1', NULL, NULL, NULL),
  ('11', 'root', '张三', '8446666@qq.com', '15324242458', 'CBbObwI8VQ2MrKFGmnWL8w==', '2016-05-26 20:50:54',
         '2017-12-07 18:05:19', '1', NULL, NULL, NULL),
  ('14', 'haiqin', '李四', '123123@qq.com', '13315898965', 'zrt4HMxuDq3V8tXGdIBKjA==', '2016-05-27 22:34:19',
         '2017-03-31 23:04:34', '1', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id`  INT(11)                 NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(64)
        CHARACTER SET utf8
        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '用户ID',
  `rid` VARCHAR(64)
        CHARACTER SET utf8
        COLLATE utf8_general_ci NULL     DEFAULT NULL
  COMMENT '角色ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  AUTO_INCREMENT = 5;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '12', '4'), ('2', '11', '3'), ('4', '1', '1');
COMMIT;

-- ----------------------------
-- Auto increment value for sys_user
-- ----------------------------
ALTER TABLE `sys_user`
  AUTO_INCREMENT = 15;

-- ----------------------------
-- Auto increment value for sys_user_role
-- ----------------------------
ALTER TABLE `sys_user_role`
  AUTO_INCREMENT = 5;
