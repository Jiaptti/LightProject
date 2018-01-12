/*
Navicat MySQL Data Transfer

Source Server         : my_db
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : viroyal_light_db

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-01-12 18:10:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_light
-- ----------------------------
DROP TABLE IF EXISTS `sys_light`;
CREATE TABLE `sys_light` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`status`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1:开灯，0:关灯' ,
`voltage`  bigint(20) NULL DEFAULT NULL COMMENT '电压' ,
`current`  bigint(20) NULL DEFAULT NULL COMMENT '电流' ,
`traffic_flow`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息' ,
`temperature`  bigint(20) NULL DEFAULT NULL COMMENT '温度' ,
`humidity`  bigint(20) NULL DEFAULT NULL COMMENT '湿度' ,
`autoreport`  bigint(16) NULL DEFAULT NULL COMMENT '路灯自动上报周期' ,
`datetime`  datetime NULL DEFAULT NULL ,
`info_id`  bigint(10) NULL DEFAULT NULL COMMENT '对应light_info的code' ,
`lightness`  int(11) NULL DEFAULT NULL COMMENT '亮度' ,
`region_id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=13

;

-- ----------------------------
-- Records of sys_light
-- ----------------------------
BEGIN;
INSERT INTO `sys_light` VALUES ('1', '1', '110', '800', '1', '6', '65', '10000', '2017-12-01 17:40:40', '1', '0', null, '1'), ('2', '1', '100', '600', '1', '16', '85', '20000', '2016-12-01 12:00:06', '2', '0', null, '1'), ('3', '1', '110', '100', '0', '60', '120', '10000', '2017-12-31 22:00:36', '3', '8', null, '1'), ('4', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '4', '0', null, '1'), ('5', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '5', '0', null, '1'), ('6', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '6', '0', null, '1'), ('7', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '7', '0', null, '1'), ('8', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '8', '0', null, '1'), ('9', '1', '140', '800', '0', '24', '85', '20000', '2017-06-01 23:13:22', '9', '0', null, '1'), ('10', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '10', '0', null, '1'), ('11', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '11', '0', null, '1'), ('12', '1', '140', '800', '1', '24', '85', '20000', '2017-06-01 23:13:22', '12', '0', null, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_group`;
CREATE TABLE `sys_light_group` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`group_id`  int(10) NULL DEFAULT NULL COMMENT '组id' ,
`group_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组名' ,
`create_user_id`  bigint(10) NULL DEFAULT NULL COMMENT '由谁创建的组' ,
`responsible_id`  bigint(10) NULL DEFAULT NULL COMMENT '谁负责的组' ,
`create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=13

;

-- ----------------------------
-- Records of sys_light_group
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_group` VALUES ('1', '1', 'xx组路灯1', '1', '2', '2017-12-31 17:20:40', '1'), ('2', '1', 'xx组路灯2', '1', '3', '2017-12-31 13:30:43', '1'), ('3', '1', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('4', '1', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('5', '2', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('6', '2', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('7', '2', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('8', '2', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('9', '3', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('10', '3', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('11', '3', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1'), ('12', '3', 'xx组路灯3', '1', '4', '2017-12-31 07:55:30', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_info`;
CREATE TABLE `sys_light_info` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`code`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路灯编码' ,
`info`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路灯信息' ,
`status`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1:正在使用，0:刚注册信息并没有投入使用' ,
`longitude`  float NULL DEFAULT 0 COMMENT '经度' ,
`latitude`  float NULL DEFAULT 0 COMMENT '纬度' ,
`strategy_id`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间' ,
`user_id`  bigint(20) NULL DEFAULT NULL ,
`street_id`  bigint(10) NULL DEFAULT 1 COMMENT '所属街道id' ,
`group_id`  bigint(10) NULL DEFAULT NULL COMMENT '所属集合id' ,
`voltage_threshold`  int(11) NULL DEFAULT 0 COMMENT '电压报警阀值' ,
`current_threshold`  int(11) NULL DEFAULT 0 COMMENT '电流报警阀值' ,
`temperature_threshold`  int(11) NULL DEFAULT 0 COMMENT '温度报警阀值' ,
`humidity_threshold`  int(11) NULL DEFAULT 0 COMMENT '湿度报警阀值' ,
`lightness_threshold`  int(11) NULL DEFAULT 0 COMMENT '亮度报警阀值' ,
`voltage_overload`  int(11) NULL DEFAULT 0 COMMENT '电压过载阀值' ,
`current_overload`  int(11) NULL DEFAULT 0 COMMENT '电流过载阀值' ,
`temperature_overload`  int(11) NULL DEFAULT 0 COMMENT '温度过载阀值' ,
`humidity_overload`  int(11) NULL DEFAULT 0 COMMENT '师傅过载阀值' ,
`lightness_overload`  int(11) NULL DEFAULT 0 COMMENT '亮度过载阀值' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=13

;

-- ----------------------------
-- Records of sys_light_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_info` VALUES ('1', '1001', 'xx1路灯', '0', '0', '1', '4', '2', '5205020', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('2', '1002', 'xx2路灯', '1', '0', '0', '4', '2', '5205021', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('3', '1003', 'xx3路灯', '1', '0', '0', '4', '2', '5202010', '1', '160', '0', '0', '0', '0', '0', '90', '0', '0', '0', '1'), ('4', '1004', 'xx4路灯', '0', '0', '0', '4', '2', '5203030', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('5', '1005', 'xx5路灯', '0', '0', '0', '4', '3', '5203031', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('6', '1006', 'xx6路灯', '0', '0', '0', '4', '3', '3101040', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('7', '1007', 'xx7路灯', '0', '0', '0', '4', '3', '3101040', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('8', '1008', 'xx8路灯', '1', '0', '0', '4', '3', '3101041', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('9', '1009', 'xx9路灯', '1', '0', '0', '4', '4', '3101041', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('10', '1010', 'xx10路灯', '1', '0', '0', '4', '4', '3101120', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('11', '1011', 'xx11路灯', '1', '0', '0', '4', '4', '3101121', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('12', '1012', 'xx12路灯', '0', '0', '0', '4', '4', '3101121', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_record`;
CREATE TABLE `sys_light_record` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`r_date`  datetime NULL DEFAULT NULL COMMENT '记录时间' ,
`r_status`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录状态' ,
`r_operation`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录操作' ,
`r_userid`  bigint(20) NULL DEFAULT NULL COMMENT '谁进行了操作' ,
`code`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应sys_light_info里的code' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of sys_light_record
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_record` VALUES ('1', '2016-06-16 11:15:33', '更新路灯', '对路灯位置进行了修改', '1', '1008', '1'), ('2', '2017-12-22 11:01:56', '指派路灯', '对路灯指派了维修人员', '2', '1001', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_strategy
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_strategy`;
CREATE TABLE `sys_light_strategy` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`smooth_level`  int(10) NULL DEFAULT NULL COMMENT '车流量少时的亮度' ,
`traffic_level`  int(10) NULL DEFAULT NULL COMMENT '车流量多时的亮度' ,
`open_time`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打开时间' ,
`close_time`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关闭时间' ,
`type`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冬季，夏季' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of sys_light_strategy
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_strategy` VALUES ('1', '200', '255', '18:00:00', '07:00:00', '春季', '1'), ('2', '180', '255', '19:45:00', '05:30:00', '夏季', '1'), ('3', '180', '255', '19:00:00', '06:00:00', '秋季', '1'), ('4', '220', '255', '17:45:00', '07:25:00', '冬季', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址' ,
`name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url描述' ,
`perms`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=30

;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '/user/user.jsp', '用户列表', 'sys:user:list,sys:user:info', '1'), ('2', '/user/edit.jsp', '用户添加', 'sys:user:save,sys:role:select', '1'), ('3', '/user/edit.jsp', '用户删除', 'sys:user:delete', '1'), ('4', '/user/edit.jsp', '用户更新', 'sys:user:update,sys:role:select', '1'), ('5', '', '用户Session踢出', 'sys:user:kickout', '1'), ('6', '', '用户激活&禁止', 'sys:user:status', '1'), ('7', '/role/role.jsp', '角色列表', 'sys:role:list,sys:role:info', '1'), ('8', '/role/edit.jsp', '角色删除', 'sys:role:delete', '1'), ('9', '/role/edit.jsp', '角色添加', 'sys:role:save', '1'), ('10', '/role/edit.jsp', '角色更新', 'sys:role:update', '1'), ('11', '/permission/permission.jsp', '权限列表', 'sys:permission:list,sys:permission:info', '1'), ('12', '/permission/edit.jsp', '权限添加', 'sys:permission:save', '1'), ('13', '/permission/edit.jsp', '权限删除', 'sys:permission:delete', '1'), ('14', '/permission/edit.jsp', '权限更新', 'sys:permission:update', '1'), ('15', '/lightinfo/lightinfo.jsp', '路灯信息列表', 'sys:lightInfo:list,sys:lightInfo:info', '1'), ('16', '/lightinfo/edit.jsp', '路灯信息添加', 'sys:lightInfo:save', '1'), ('17', '/lightinfo/edit.jsp', '路灯信息删除', 'sys:lightInfo:delete', '1'), ('18', '/lightinfo/edit.jsp', '路灯信息更新', 'sys:lightInfo:update', '1'), ('19', '/region/region.jsp', '地区列表', 'sys:region:list,sys:region:info', '1'), ('20', '/region/edit.jsp', '地区添加', 'sys:region.save', '1'), ('21', '/region/edit.jsp', '地区删除', 'sys:region:delete', '1'), ('22', '/region/edit.jsp', '地区更新', 'sys:region:update', '1'), ('23', '/light/light.jsp', '路灯列表', 'sys:light:list,sys:light:info', '1'), ('24', '/light/edit.jsp', '路灯添加', 'sys:light:save', '1'), ('25', '/light/edit.jsp', '路灯删除', 'sys:light:delete', '1'), ('26', '/light/edit.jsp', '路灯更新', 'sys:light:update', '1');
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
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of sys_permission_init
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_init` VALUES ('1', '/static/**', 'anon', '1'), ('2', '/ajaxLogin', 'anon', '2'), ('3', '/logout', 'logout', '3'), ('4', '/**', 'kickout,perms[sys:user:list],authc', '3'), ('5', '/getGifCode', 'anon', '2'), ('6', '/kickout', 'anon', '2'), ('7', '/userLogin', 'anon', '2'), ('12', '/favicon.ico', 'anon', '2'), ('13', '/login', 'anon', '2'), ('14', '/403', 'anon', '2');
COMMIT;

-- ----------------------------
-- Table structure for sys_region
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`common_region_id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区编号' ,
`region_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区名' ,
`up_region_id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '地区上级编号' ,
`region_desc`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区描述' ,
`postalcode`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=22

;

-- ----------------------------
-- Records of sys_region
-- ----------------------------
BEGIN;
INSERT INTO `sys_region` VALUES ('1', '520500', '毕节市', '', '地级市', '551700', '1'), ('2', '520300', '遵义市', '', '地级市', '563000', '1'), ('3', '520200', '六盘水市', '', '地级市', '553000', '1'), ('4', '310000', '上海市', '', '直辖市', '200000', '1'), ('5', '520502', '七星关区', '520500', '市辖区', '551700', '1'), ('6', '520201', '钟山区', '520200', '市辖区', '553000', '1'), ('7', '520303', '汇川区', '520300', '市辖区', '563000', '1'), ('8', '310104', '徐汇区', '310000', '市辖区', '200030', '1'), ('9', '310112', '闵行区', '310000', '市辖区', '201100', '1'), ('10', '5205020', '市东街道', '520502', '街道', '551700', '1'), ('11', '5205021', '三板桥街道', '520502', '街道', '551700', '1'), ('12', '5202010', '黄土坡街道', '520201', '街道', '553000', '1'), ('13', '5203030', '洗马路街道', '520303', '街道', '563000', '1'), ('14', '5203031', '大连路街道', '520303', '街道', '563000', '1'), ('15', '3101040', '天平路街道', '310104', '街道', '200030', '1'), ('16', '3101041', '枫林路街道', '310104', '街道', '200030', '1'), ('17', '3101120', '江川路街道', '310112', '街道', '201100', '1'), ('18', '3101121', '古美路街道', '310112', '街道', '201100', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称' ,
`type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=4

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'admin', '100004', '1'), ('2', 'user', '100001', '1'), ('3', 'worker', '100002', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`rid`  bigint(20) NULL DEFAULT NULL COMMENT '角色ID' ,
`pid`  bigint(20) NULL DEFAULT NULL COMMENT '权限ID' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=51

;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1', '1'), ('2', '1', '2', '1'), ('3', '1', '3', '1'), ('4', '1', '4', '1'), ('5', '1', '5', '1'), ('6', '1', '6', '1'), ('7', '1', '7', '1'), ('8', '1', '8', '1'), ('9', '1', '9', '1'), ('10', '1', '10', '1'), ('11', '1', '11', '1'), ('12', '1', '12', '1'), ('13', '1', '13', '1'), ('14', '1', '14', '1'), ('15', '2', '2', '1'), ('16', '2', '3', '1'), ('17', '2', '4', '1'), ('18', '2', '5', '1'), ('20', '1', '15', '1'), ('21', '1', '16', '1'), ('22', '1', '17', '1'), ('23', '1', '18', '1'), ('24', '1', '19', '1'), ('25', '1', '20', '1'), ('26', '1', '21', '1'), ('27', '1', '22', '1'), ('28', '3', '15', '1'), ('29', '3', '16', '1'), ('30', '3', '17', '1'), ('31', '3', '18', '1'), ('32', '3', '19', '1'), ('33', '3', '20', '1'), ('34', '3', '21', '1'), ('35', '3', '22', '1'), ('36', '2', '15', '1'), ('37', '2', '16', '1'), ('38', '2', '17', '1'), ('39', '2', '18', '1'), ('40', '2', '19', '1'), ('41', '2', '20', '1'), ('42', '2', '21', '1'), ('43', '2', '22', '1'), ('44', '1', '23', '1'), ('45', '1', '24', '1'), ('46', '1', '25', '1'), ('47', '1', '26', '1'), ('50', '1', '29', '1');
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
`flag`  int(1) NOT NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=29

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', 'l9a0lajfwQ3VRSh4jUUJSQ==', '15255257895', 'admin@qq.com', '2016-06-16 11:15:33', '2018-01-12 17:31:37', '1', null, null, null, '1'), ('2', '张三', 'zhang', 'eC/MmhASeJBCbfh7W0YU1A==', '13316998986', '123@123.com', '2017-12-24 19:37:40', null, '1', '1', null, null, '1'), ('3', '李四', 'lisi', 'bR28UodwBWRDm+buWIaZbg==', '15575590909', '321@123.com', '2017-12-24 19:38:06', null, '1', '1', null, null, '1'), ('4', '王五', 'wangwu', 'Yb7Dc8/cPeCL8NwOjnYSRA==', '13317979888', '456@123.com', '2017-12-24 19:38:30', null, '1', '1', null, null, '1'), ('20', '罗总', 'luo', '7NjQPajcVm2j1j8LIfaQew==', '15575889999', '123@123.com', '2017-12-25 10:33:48', '2018-01-03 18:07:51', '1', '1', null, null, '1'), ('22', '老司机', 'laosiji', '7NjQPajcVm1PC6tJMaodpQxtrQrzzUma', '15585858585', '123@123.com', '2017-12-25 10:34:55', '2017-12-25 10:36:52', '1', '1', null, null, '1'), ('28', '123', 'luo', null, null, null, '2018-01-12 18:09:56', null, '0', '1', null, null, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`uid`  bigint(20) NULL DEFAULT NULL COMMENT '用户ID' ,
`rid`  bigint(20) NULL DEFAULT NULL COMMENT '角色ID' ,
`flag`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=22

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '1'), ('2', '2', '3', '1'), ('3', '3', '3', '1'), ('4', '4', '3', '1'), ('13', '20', '1', '1'), ('15', '22', '1', '1'), ('21', '28', null, '1');
COMMIT;

-- ----------------------------
-- Auto increment value for sys_light
-- ----------------------------
ALTER TABLE `sys_light` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for sys_light_group
-- ----------------------------
ALTER TABLE `sys_light_group` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for sys_light_info
-- ----------------------------
ALTER TABLE `sys_light_info` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for sys_light_record
-- ----------------------------
ALTER TABLE `sys_light_record` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for sys_light_strategy
-- ----------------------------
ALTER TABLE `sys_light_strategy` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for sys_permission
-- ----------------------------
ALTER TABLE `sys_permission` AUTO_INCREMENT=30;

-- ----------------------------
-- Auto increment value for sys_permission_init
-- ----------------------------
ALTER TABLE `sys_permission_init` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for sys_region
-- ----------------------------
ALTER TABLE `sys_region` AUTO_INCREMENT=22;

-- ----------------------------
-- Auto increment value for sys_role
-- ----------------------------
ALTER TABLE `sys_role` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for sys_role_permission
-- ----------------------------
ALTER TABLE `sys_role_permission` AUTO_INCREMENT=51;

-- ----------------------------
-- Auto increment value for sys_user
-- ----------------------------
ALTER TABLE `sys_user` AUTO_INCREMENT=29;

-- ----------------------------
-- Auto increment value for sys_user_role
-- ----------------------------
ALTER TABLE `sys_user_role` AUTO_INCREMENT=22;
