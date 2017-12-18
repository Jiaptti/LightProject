/*
Navicat MySQL Data Transfer

Source Server         : my_db
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : viroyal_light_db

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-12-18 17:49:57
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
`perms`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=22

;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '/user/user.jsp', '用户列表', 'sys:user:list,sys:user:info'), ('2', '/user/edit.jsp', '用户添加', 'sys:user:save,sys:role:select'), ('3', '/user/edit.jsp', '用户删除', 'sys:user:delete'), ('4', '/user/edit.jsp', '用户更新', 'sys:user:update,sys:role:select'), ('5', '', '用户Session踢出', 'sys:user:kickout'), ('6', '', '用户激活&禁止', 'sys:user:status'), ('7', '/role/role.jsp', '角色列表', 'sys:role:list,sys:role:info'), ('8', '/role/edit.jsp', '角色删除', 'sys:role:delete'), ('9', '/role/edit.jsp', '角色添加', 'sys:role:save,sys:permission:select'), ('10', '/role/edit.jsp', '角色更新', 'sys:role:update,sys:permission:select'), ('11', '/permission/permission.jsp', '权限列表', 'sys:permission:list,sys:permission:info'), ('12', '/permission/edit.jsp', '权限添加', 'sys:permission:save,sys:permission:select'), ('13', '/permission/edit.jsp', '权限删除', 'sys:permission:delete'), ('14', '/permission/edit.jsp', '权限更新', 'sys:permission:update,sys:permission:select');
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
AUTO_INCREMENT=12

;

-- ----------------------------
-- Records of sys_permission_init
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_init` VALUES ('1', '/static/**', 'anon,kickout', '1'), ('2', '/ajaxLogin', 'anon,kickout', '2'), ('3', '/logout', 'logout,kickout', '3'), ('4', '/add', 'perms[权限添加:权限删除],roles[100002],kickout', '4'), ('5', '/**', 'user,kickout', '5'), ('6', '/getGifCode', 'anon,kickout', '2'), ('7', '/kickout', 'anon', '2'), ('10', '/delete', 'perms[权限删除]', '2'), ('11', '/login', 'anon,kickout', '2');
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
INSERT INTO `sys_role` VALUES ('1', 'admin', '100004'), ('2', 'user', '100001'), ('3', 'worker', '100002');
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
AUTO_INCREMENT=36

;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('4', '1', '4'), ('5', '1', '5'), ('6', '1', '6'), ('7', '1', '7'), ('8', '1', '8'), ('9', '1', '9'), ('10', '1', '10'), ('11', '1', '11'), ('12', '1', '12'), ('13', '1', '13'), ('14', '1', '14'), ('15', '2', '2'), ('16', '2', '3'), ('17', '2', '4'), ('18', '2', '5'), ('19', '3', '6');
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
AUTO_INCREMENT=16

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', 'l9a0lajfwQ3VRSh4jUUJSQ==', '15255257895', 'admin@qq.com', '2016-06-16 11:15:33', '2017-12-18 17:10:00', '1', null, null, null), ('11', 'root', 'root', 'x16YjoF1LNE=', '12332323232', '8446666@qq.com', '2017-12-13 19:30:44', '2017-02-13 15:49:04', '1', null, null, null);
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
INSERT INTO `sys_user_role` VALUES ('2', '11', '3'), ('4', '1', '1'), ('8', '15', '4');
COMMIT;

-- ----------------------------
-- Auto increment value for sys_permission
-- ----------------------------
ALTER TABLE `sys_permission` AUTO_INCREMENT=22;

-- ----------------------------
-- Auto increment value for sys_permission_init
-- ----------------------------
ALTER TABLE `sys_permission_init` AUTO_INCREMENT=12;

-- ----------------------------
-- Auto increment value for sys_role
-- ----------------------------
ALTER TABLE `sys_role` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for sys_role_permission
-- ----------------------------
ALTER TABLE `sys_role_permission` AUTO_INCREMENT=36;

-- ----------------------------
-- Auto increment value for sys_user
-- ----------------------------
ALTER TABLE `sys_user` AUTO_INCREMENT=16;

-- ----------------------------
-- Auto increment value for sys_user_role
-- ----------------------------
ALTER TABLE `sys_user_role` AUTO_INCREMENT=9;
