/*
Navicat MySQL Data Transfer

Source Server         : my_db
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : viroyal_light_db

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-01-26 00:18:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_light
-- ----------------------------
DROP TABLE IF EXISTS `sys_light`;
CREATE TABLE `sys_light` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`status`  int(1) NULL DEFAULT 1 COMMENT '1:开灯，0:关灯' ,
`voltage`  int(11) NULL DEFAULT NULL COMMENT '电压' ,
`current`  int(11) NULL DEFAULT NULL COMMENT '电流' ,
`traffic_flow`  int(1) NULL DEFAULT 1 COMMENT '车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息' ,
`temperature`  int(11) NULL DEFAULT NULL COMMENT '温度' ,
`humidity`  int(11) NULL DEFAULT NULL COMMENT '湿度' ,
`datetime`  datetime NULL DEFAULT NULL ,
`info_id`  bigint(10) NULL DEFAULT NULL COMMENT '对应light_info的code' ,
`lightness`  int(11) NULL DEFAULT NULL COMMENT '亮度' ,
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
`last_update_time`  datetime NULL DEFAULT NULL COMMENT '最后更新时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=16

;

-- ----------------------------
-- Records of sys_light
-- ----------------------------
BEGIN;
INSERT INTO `sys_light` VALUES ('1', '1', '110', '800', '1', '6', '65', '2017-12-01 17:40:40', '1', '0', '1', null), ('2', '1', '100', '600', '1', '16', '85', '2017-12-01 12:00:06', '2', '0', '1', null), ('3', '1', '110', '100', '0', '60', '120', '2017-12-31 22:00:36', '3', '8', '1', null), ('4', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '4', '0', '1', null), ('5', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '5', '0', '1', null), ('6', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '6', '0', '1', null), ('7', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '7', '0', '1', null), ('8', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '8', '0', '1', null), ('9', '1', '140', '800', '0', '24', '85', '2017-06-01 23:13:22', '9', '0', '1', null), ('10', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '10', '0', '1', null), ('11', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '11', '0', '1', null), ('12', '1', '140', '800', '1', '24', '85', '2017-06-01 23:13:22', '12', '0', '1', null), ('14', '1', '120', '800', '1', '22', '10', '2018-01-18 10:44:39', '10', '20', '1', null), ('15', '0', '110', '800', '0', '-1', '19', '2018-01-25 18:20:40', '10', '10', '1', '2018-01-25 18:22:49');
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
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
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
`light_info`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路灯信息' ,
`status`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1:正在使用，0:刚注册信息并没有投入使用' ,
`longitude`  float NULL DEFAULT 0 COMMENT '经度' ,
`latitude`  float NULL DEFAULT 0 COMMENT '纬度' ,
`strategy_id`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间' ,
`user_id`  bigint(20) NULL DEFAULT NULL ,
`street_id`  bigint(10) NULL DEFAULT 1 COMMENT '所属街道id' ,
`auto_report`  bigint(10) NULL DEFAULT 60000 COMMENT '自动上报周期' ,
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
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=14

;

-- ----------------------------
-- Records of sys_light_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_info` VALUES ('1', '1001', 'xx1路灯', '0', '0', '1', '4', '2', '5205020', '60000', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('2', '1002', 'xx2路灯', '1', '0', '0', '4', '2', '5205021', '60000', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('3', '1003', 'xx3路灯', '1', '0', '0', '4', '2', '5202010', '60000', '1', '160', '0', '0', '0', '0', '0', '90', '0', '0', '0', '1'), ('4', '1004', 'xx4路灯', '0', '0', '0', '4', '2', '5203030', '60000', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('5', '1005', 'xx5路灯', '0', '0', '0', '4', '3', '5203031', '60000', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('6', '1006', 'xx6路灯', '0', '0', '0', '4', '3', '3101040', '60000', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('7', '1007', 'xx7路灯', '0', '0', '0', '4', '3', '3101040', '60000', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('8', '1008', 'xx8路灯', '1', '0', '0', '4', '3', '3101041', '60000', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('9', '1009', 'xx9路灯', '1', '0', '0', '4', '4', '3101041', '60000', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('10', '1010', 'xx10路灯', '1', '0', '0', '4', '4', '3101120', '60000', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('11', '1011', 'xx11路灯', '1', '0', '0', '4', '4', '3101121', '60000', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('12', '1012', 'xx12路灯', '0', '0', '0', '4', '4', '3101121', '60000', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'), ('13', '1013', 'xx13路灯', '1', '0', '0', '4', '3', '3101121', '60000', '1', '0', '0', '0', '0', '0', null, '0', '0', '0', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_light_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_light_record`;
CREATE TABLE `sys_light_record` (
`id`  bigint(10) NOT NULL AUTO_INCREMENT ,
`record_date`  datetime NULL DEFAULT NULL COMMENT '记录时间' ,
`record_status`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录状态' ,
`record_operation`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录操作' ,
`record_user_id`  bigint(20) NULL DEFAULT NULL COMMENT '谁进行了操作' ,
`light_info_id`  bigint(10) NULL DEFAULT NULL COMMENT '对应路灯id' ,
`last_update_user_id`  bigint(10) NULL DEFAULT NULL COMMENT '最后修改的用户' ,
`last_update_time`  datetime NULL DEFAULT NULL COMMENT '最后修改日期' ,
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=17

;

-- ----------------------------
-- Records of sys_light_record
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_record` VALUES ('1', '2018-01-22 11:01:56', '指派路灯', '指派了维修员张三', '2', '1', null, null, '1'), ('2', '2018-01-16 20:15:14', '维修路灯', '对路灯进行了维修', '3', '2', null, null, '1'), ('3', '2018-01-01 20:16:12', '更新路灯', '调低了路灯亮度', '2', '3', null, null, '1'), ('4', '2018-01-01 20:17:17', '更新路灯', '精确了路灯的具体位置', '1', '4', null, null, '1'), ('5', '2018-01-01 20:17:57', '指派路灯', '指派了维修员张三', '2', '4', null, null, '1'), ('6', '2017-11-22 20:18:03', '维修路灯', '对路灯电流不稳定进行了维修', '3', '5', null, null, '1'), ('7', '2018-01-10 20:18:12', '维修路灯', '对路灯电压不稳定进行了维修', '3', '5', null, null, '1'), ('8', '2018-01-01 20:18:17', '指派路灯', '指派了维修员李四', '2', '6', null, null, '1'), ('9', '2017-12-06 20:18:24', '指派路灯', '指派了维修员李四', '1', '7', null, null, '1'), ('10', '2017-11-15 20:18:33', '更新路灯', '更新了路灯的编号', '2', '8', null, null, '1'), ('11', '2017-11-24 20:18:40', '维修路灯', '对路灯无法检测湿度进行维修', '3', '8', null, null, '1'), ('12', '2017-10-18 20:18:47', '指派路灯', '指派了维修员李四', '2', '9', null, null, '1'), ('13', '2017-09-01 20:18:54', '维修路灯', '维修了路灯无法亮灯的情况', '3', '10', null, null, '1'), ('14', '2018-01-01 20:19:01', '更新路灯', '修改了亮度阈值', '2', '11', null, null, '1'), ('15', '2017-07-12 20:19:07', '更新路灯', '修改了湿度阈值', '2', '12', null, null, '1');
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
`strategy_open_time`  date NULL DEFAULT NULL COMMENT '策略打开日期' ,
`strategy_close_time`  date NULL DEFAULT NULL COMMENT '策略关闭日期' ,
`close_time`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关闭时间' ,
`type`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冬季，夏季' ,
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of sys_light_strategy
-- ----------------------------
BEGIN;
INSERT INTO `sys_light_strategy` VALUES ('1', '200', '255', '18:00:00', '2018-01-01', '2018-03-31', '07:00:00', '春季', '1'), ('2', '180', '255', '19:45:00', '2018-04-01', '2018-06-30', '05:30:00', '夏季', '1'), ('3', '180', '255', '19:00:00', '2018-07-01', '2018-09-30', '06:00:00', '秋季', '1'), ('4', '220', '255', '17:45:00', '2018-10-01', '2018-12-31', '07:25:00', '冬季', '1');
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
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=36

;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '/user/user.jsp', '用户列表', 'sys:user:list,sys:user:info', '1'), ('2', '/user/edit.jsp', '用户添加', 'sys:user:save,sys:role:select', '1'), ('3', '/user/edit.jsp', '用户删除', 'sys:user:delete', '1'), ('4', '/user/edit.jsp', '用户更新', 'sys:user:update,sys:role:select', '1'), ('5', '', '用户Session踢出', 'sys:user:kickout', '1'), ('6', '', '用户激活&禁止', 'sys:user:status', '1'), ('7', '/role/role.jsp', '角色列表', 'sys:role:list,sys:role:info', '1'), ('8', '/role/edit.jsp', '角色删除', 'sys:role:delete', '1'), ('9', '/role/edit.jsp', '角色添加', 'sys:role:save', '1'), ('10', '/role/edit.jsp', '角色更新', 'sys:role:update', '1'), ('11', '/permission/permission.jsp', '权限列表', 'sys:permission:list,sys:permission:info', '1'), ('12', '/permission/edit.jsp', '权限添加', 'sys:permission:save', '1'), ('13', '/permission/edit.jsp', '权限删除', 'sys:permission:delete', '1'), ('14', '/permission/edit.jsp', '权限更新', 'sys:permission:update', '1'), ('15', '/lightinfo/lightinfo.jsp', '路灯信息列表', 'sys:lightInfo:list,sys:lightInfo:info', '1'), ('16', '/lightinfo/edit.jsp', '路灯信息添加', 'sys:lightInfo:save', '1'), ('17', '/lightinfo/edit.jsp', '路灯信息删除', 'sys:lightInfo:delete', '1'), ('18', '/lightinfo/edit.jsp', '路灯信息更新', 'sys:lightInfo:update', '1'), ('19', '/region/region.jsp', '地区列表', 'sys:region:list,sys:region:info', '1'), ('20', '/region/edit.jsp', '地区添加', 'sys:region:save', '1'), ('21', '/region/edit.jsp', '地区删除', 'sys:region:delete', '1'), ('22', '/region/edit.jsp', '地区更新', 'sys:region:update', '1'), ('23', '/light/light.jsp', '路灯列表', 'sys:light:list,sys:light:info', '1'), ('24', '/light/edit.jsp', '路灯添加', 'sys:light:save', '1'), ('25', '/light/edit.jsp', '路灯删除', 'sys:light:delete', '1'), ('26', '/light/edit.jsp', '路灯更新', 'sys:light:update', '1'), ('27', '/strategy/strategy.jsp', '路灯策略列表', 'sys:strategy:list,sys:strategy:info', '1'), ('28', '/strategy/edit.jsp', '路灯策略添加', 'sys:strategy:save', '1'), ('29', '/strategy/edit.jsp', '路灯策略删除', 'sys:strategy:delete', '1'), ('30', '/strategy/edit.jsp', '路灯策略更新', 'sys:strategy:update', '1'), ('32', null, '路灯记录列表', 'sys:lightRecord:list', '1'), ('33', null, '路灯记录添加', 'sys:lightRecord:save', '1'), ('34', null, '路灯记录删除', 'sys:lightRecord:delete', '1'), ('35', null, '路灯记录更新', 'sys:lightRecord:update', '1');
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
INSERT INTO `sys_permission_init` VALUES ('1', '/static/**', 'anon', '1'), ('2', '/ajaxLogin', 'anon', '2'), ('3', '/logout', 'logout', '3'), ('4', '/**', 'kickout,authc', '3'), ('5', '/getGifCode', 'anon', '2'), ('6', '/kickout', 'anon', '2'), ('7', '/userLogin', 'anon', '2'), ('12', '/favicon.ico', 'anon', '2'), ('13', '/login', 'anon', '2'), ('14', '/403', 'anon', '2');
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
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=29

;

-- ----------------------------
-- Records of sys_region
-- ----------------------------
BEGIN;
INSERT INTO `sys_region` VALUES ('1', '520500', '毕节市', '', '地级市', '551700', '1'), ('2', '520300', '遵义市', '', '地级市', '563000', '1'), ('3', '520200', '六盘水市', '', '地级市', '553000', '1'), ('4', '310000', '上海市', '', '直辖市', '200000', '1'), ('5', '520502', '七星关区', '520500', '市辖区', '551700', '1'), ('6', '520201', '钟山区', '520200', '市辖区', '553000', '1'), ('7', '520303', '汇川区', '520300', '市辖区', '563000', '1'), ('8', '310104', '徐汇区', '310000', '市辖区', '200030', '1'), ('9', '310112', '闵行区', '310000', '市辖区', '201100', '1'), ('10', '5205020', '市东街道', '520502', '街道', '551700', '1'), ('11', '5205021', '三板桥街道', '520502', '街道', '551700', '1'), ('12', '5202010', '黄土坡街道', '520201', '街道', '553000', '1'), ('13', '5203030', '洗马路街道', '520303', '街道', '563000', '1'), ('14', '5203031', '大连路街道', '520303', '街道', '563000', '1'), ('15', '3101040', '天平路街道', '310104', '街道', '200030', '1'), ('16', '3101041', '枫林路街道', '310104', '街道', '200030', '1'), ('17', '3101120', '江川路街道', '310112', '街道', '201100', '1'), ('18', '3101121', '古美路街道', '310112', '街道', '201100', '1'), ('19', '310101', '黄浦区', '310000', '市辖区', '210100', '1'), ('28', '3101011', '黄埔大道', '310101', '街道', '210100', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称' ,
`type`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型' ,
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
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
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1'), ('2', '用户', 'user', '1'), ('3', '维修员', 'worker', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`rid`  bigint(20) NULL DEFAULT NULL COMMENT '角色ID' ,
`pid`  bigint(20) NULL DEFAULT NULL COMMENT '权限ID' ,
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=48

;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1', '1'), ('2', '1', '2', '1'), ('3', '1', '3', '1'), ('4', '1', '4', '1'), ('5', '1', '5', '1'), ('6', '1', '6', '1'), ('7', '1', '7', '1'), ('8', '1', '8', '1'), ('9', '1', '9', '1'), ('10', '1', '10', '1'), ('11', '1', '11', '1'), ('12', '1', '12', '1'), ('13', '1', '13', '1'), ('14', '1', '14', '1'), ('15', '2', '2', '1'), ('16', '2', '3', '1'), ('17', '2', '4', '1'), ('18', '2', '5', '1'), ('20', '1', '15', '1'), ('21', '1', '16', '1'), ('22', '1', '17', '1'), ('23', '1', '18', '1'), ('24', '1', '19', '1'), ('25', '1', '20', '1'), ('26', '1', '21', '1'), ('27', '1', '22', '1'), ('28', '3', '15', '1'), ('29', '3', '16', '1'), ('30', '3', '17', '1'), ('31', '3', '18', '1'), ('32', '3', '19', '1'), ('33', '3', '20', '1'), ('34', '3', '21', '1'), ('35', '3', '22', '1'), ('36', '2', '15', '1'), ('37', '2', '16', '1'), ('38', '2', '17', '1'), ('39', '2', '18', '1'), ('40', '2', '19', '1'), ('41', '2', '20', '1'), ('42', '2', '21', '1'), ('43', '2', '22', '1'), ('44', '1', '23', '1'), ('45', '1', '24', '1'), ('46', '1', '25', '1'), ('47', '1', '26', '1');
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
`exist`  int(1) NOT NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', 'l9a0lajfwQ3VRSh4jUUJSQ==', '15565567776', 'admin@admin.com', '2018-01-12 15:34:06', '2018-01-26 00:09:12', '1', '26', null, null, '1'), ('2', '张三', 'zhang', 'eC/MmhASeJBCbfh7W0YU1A==', '13316998986', '123@123.com', '2017-12-24 19:37:40', '2018-01-22 23:01:51', '1', '1', null, null, '1'), ('3', '李四', 'lisi', 'bR28UodwBWRDm+buWIaZbg==', '15575590909', '321@123.com', '2017-12-24 19:38:06', null, '1', '1', null, null, '1'), ('4', '王五', 'wangwu', 'Yb7Dc8/cPeCL8NwOjnYSRA==', '13317979888', '456@123.com', '2017-12-24 19:38:30', null, '1', '1', null, null, '1'), ('6', '权哥', 'quan', '7NjQPajcVm1dzbZMJvVcRA==', '15475878981', '132@123.com', '2017-12-25 10:34:26', '2018-01-15 09:51:15', '1', '1', null, null, '1'), ('7', '老司机', 'laosiji', '7NjQPajcVm1PC6tJMaodpQxtrQrzzUma', '15585858585', '123@123.com', '2017-12-25 10:34:55', '2018-01-11 16:31:05', '1', '1', null, null, '1'), ('8', '赵总', 'zxb', '7NjQPajcVm3rcjWOTacgxQ==', '13816214814', '460837364@qq.com', '2017-12-26 11:11:50', '2018-01-15 09:40:57', '1', '20', null, null, '1'), ('9', '哈哈哈哈哈', 'sunyy', '8AetwF4m3o3Y4JDqkLIeLA==', '13145678961', '2222@11.COM', '2017-12-27 13:59:21', '2018-01-12 14:18:37', '1', '1', '2018-01-03 15:09:37', '1', '1'), ('10', '武因生', '武因生', '7NjQPajcVm05iEcgH3wDZ/3531yNGT4C', '15821361405', 'wys484112@163.com', '2017-12-28 00:00:00', '2017-12-28 19:45:58', '1', null, null, null, '1'), ('11', '黑哥', 'darkface', 'Ysrp0xWBcJDSXzqMlWwNag==', '15545457777', '123@123.com', '2018-01-08 17:53:16', '2018-01-11 16:06:02', '1', '1', null, null, '1'), ('12', 'zzzz', 'zzzz', null, '11111111', 'zzzzzz@viroyal-elec.com', '2018-01-09 09:09:14', null, '0', '29', null, null, '1'), ('13', '罗宝娟', 'luo345', 'g+tTx+DfnBsHMANo9jgcZA==', '13512312312', '123@123.com', '2018-01-09 16:07:53', '2018-01-15 09:41:02', '1', '1', null, null, '1'), ('14', '张三丰', 'tests', 'awTRVGVqQRVw1WvtUEvUSA==', '13319691360', 'test@132.com', '2018-01-24 22:24:44', null, '1', '1', '2018-01-24 22:25:23', '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`uid`  bigint(20) NULL DEFAULT NULL COMMENT '用户ID' ,
`rid`  bigint(20) NULL DEFAULT NULL COMMENT '角色ID' ,
`exist`  int(1) NULL DEFAULT 1 COMMENT '是否可见' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=34

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '1'), ('2', '2', '3', '1'), ('3', '3', '3', '1'), ('4', '4', '3', '1'), ('13', '6', '1', '1'), ('15', '7', '1', '1'), ('22', '8', '1', '1'), ('23', '9', '1', '1'), ('24', '10', '1', '1'), ('25', '11', '1', '1'), ('26', '12', '1', '0'), ('27', '13', '1', '1'), ('28', '14', null, '1'), ('29', '15', null, '1'), ('30', '16', null, '1'), ('31', '17', '1', '1'), ('32', '18', '1', '1'), ('33', '14', '1', '1');
COMMIT;

-- ----------------------------
-- Auto increment value for sys_light
-- ----------------------------
ALTER TABLE `sys_light` AUTO_INCREMENT=16;

-- ----------------------------
-- Auto increment value for sys_light_group
-- ----------------------------
ALTER TABLE `sys_light_group` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for sys_light_info
-- ----------------------------
ALTER TABLE `sys_light_info` AUTO_INCREMENT=14;

-- ----------------------------
-- Auto increment value for sys_light_record
-- ----------------------------
ALTER TABLE `sys_light_record` AUTO_INCREMENT=17;

-- ----------------------------
-- Auto increment value for sys_light_strategy
-- ----------------------------
ALTER TABLE `sys_light_strategy` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for sys_permission
-- ----------------------------
ALTER TABLE `sys_permission` AUTO_INCREMENT=36;

-- ----------------------------
-- Auto increment value for sys_permission_init
-- ----------------------------
ALTER TABLE `sys_permission_init` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for sys_region
-- ----------------------------
ALTER TABLE `sys_region` AUTO_INCREMENT=29;

-- ----------------------------
-- Auto increment value for sys_role
-- ----------------------------
ALTER TABLE `sys_role` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for sys_role_permission
-- ----------------------------
ALTER TABLE `sys_role_permission` AUTO_INCREMENT=48;

-- ----------------------------
-- Auto increment value for sys_user
-- ----------------------------
ALTER TABLE `sys_user` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for sys_user_role
-- ----------------------------
ALTER TABLE `sys_user_role` AUTO_INCREMENT=34;
