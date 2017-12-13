/*
Navicat MySQL Data Transfer

Source Server         : my_db
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : viroyal_light_db

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2017-12-14 01:45:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址' ,
`name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url描述' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=21

;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('4', '/permission/index.shtml', '权限列表'), ('6', '/permission/addPermission.shtml', '权限添加'), ('7', '/permission/deletePermissionById.shtml', '权限删除'), ('8', '/member/list.shtml', '用户列表'), ('9', '/member/online.shtml', '在线用户'), ('10', '/member/changeSessionStatus.shtml', '用户Session踢出'), ('11', '/member/forbidUserById.shtml', '用户激活&禁止'), ('12', '/member/deleteUserById.shtml', '用户删除'), ('16', '/role/deleteRoleById.shtml', '角色列表删除'), ('17', '/role/addRole.shtml', '角色列表添加'), ('18', '/role/index.shtml', '角色列表'), ('19', '/permission/allocation.shtml', '权限分配'), ('20', '/role/allocation.shtml', '角色分配');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_init`;
CREATE TABLE `sys_permission_init` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址' ,
`permission_init`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需要具备的权限' ,
`sort`  int(50) NULL DEFAULT NULL COMMENT '排序' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=11

;

-- ----------------------------
-- Records of sys_permission_init
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_init` VALUES ('1', '/static/**', 'anon,kickout', '1'), ('2', '/ajaxLogin', 'anon,kickout', '2'), ('3', '/logout', 'logout,kickout', '3'), ('4', '/add', 'perms[权限添加:权限删除],roles[100002],kickout', '4'), ('5', '/**', 'user,kickout', '5'), ('6', '/getGifCode', 'anon,kickout', '2'), ('7', '/kickout', 'anon', '2'), ('10', '/delete', 'perms[权限删除]', '2');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称' ,
`type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'admin', '100004'), ('3', 'user', '100001'), ('4', 'worker', '100002');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`rid`  bigint(20) NULL DEFAULT NULL COMMENT '角色ID' ,
`pid`  bigint(20) NULL DEFAULT NULL COMMENT '权限ID' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=33

;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '4', '8'), ('2', '4', '9'), ('3', '4', '10'), ('4', '4', '11'), ('5', '4', '12'), ('6', '3', '4'), ('7', '3', '6'), ('8', '3', '7'), ('9', '3', '13'), ('10', '3', '14'), ('11', '3', '15'), ('12', '3', '16'), ('13', '3', '17'), ('14', '3', '18'), ('15', '3', '19'), ('16', '3', '20'), ('17', '1', '4'), ('18', '1', '6'), ('19', '1', '7'), ('20', '1', '8'), ('21', '1', '9'), ('22', '1', '10'), ('23', '1', '11'), ('24', '1', '12'), ('25', '1', '13'), ('26', '1', '14'), ('27', '1', '15'), ('28', '1', '16'), ('29', '1', '17'), ('30', '1', '18'), ('31', '1', '19'), ('32', '1', '20');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称' ,
`username`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pswd`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码' ,
`phone`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`email`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱|登录帐号' ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`last_login_time`  datetime NULL DEFAULT NULL COMMENT '最后登录时间' ,
`status`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1:有效，0:禁止登录' ,
`create_name_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`last_update_time`  datetime NULL DEFAULT NULL ,
`last_update_name_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=19

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', null, 'l9a0lajfwQ3VRSh4jUUJSQ==', null, 'admin@qq.com', '2016-06-16 11:15:33', '2017-12-14 00:35:01', '1', null, null, null), ('11', 'root', 'root', 'x16YjoF1LNE=', '12332323232', '8446666@qq.com', '2017-12-13 19:30:44', '2017-02-13 15:49:04', '1', null, null, null), ('18', 'test', 'test', 'Zv0g77FaTgw=', '12332323232', '123@123.com', '2017-12-14 01:44:00', null, '1', null, null, null);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`uid`  bigint(20) NULL DEFAULT NULL COMMENT '用户ID' ,
`rid`  bigint(20) NULL DEFAULT NULL COMMENT '角色ID' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=9

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('2', '11', '3'), ('4', '1', '1'), ('7', null, '3'), ('8', null, '3');
COMMIT;

-- ----------------------------
-- Auto increment value for sys_permission
-- ----------------------------
ALTER TABLE `sys_permission` AUTO_INCREMENT=21;

-- ----------------------------
-- Auto increment value for sys_permission_init
-- ----------------------------
ALTER TABLE `sys_permission_init` AUTO_INCREMENT=11;

-- ----------------------------
-- Auto increment value for sys_role
-- ----------------------------
ALTER TABLE `sys_role` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for sys_role_permission
-- ----------------------------
ALTER TABLE `sys_role_permission` AUTO_INCREMENT=33;

-- ----------------------------
-- Auto increment value for sys_user
-- ----------------------------
ALTER TABLE `sys_user` AUTO_INCREMENT=19;

-- ----------------------------
-- Auto increment value for sys_user_role
-- ----------------------------
ALTER TABLE `sys_user_role` AUTO_INCREMENT=9;
