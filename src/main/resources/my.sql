-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(64) NOT NULL,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('10', '/member/changeSessionStatus.shtml', '用户Session踢出');
INSERT INTO `sys_permission` VALUES ('11', '/member/forbidUserById.shtml', '用户激活&禁止');
INSERT INTO `sys_permission` VALUES ('12', '/member/deleteUserById.shtml', '用户删除');
INSERT INTO `sys_permission` VALUES ('16', '/role/deleteRoleById.shtml', '角色列表删除');
INSERT INTO `sys_permission` VALUES ('17', '/role/addRole.shtml', '角色列表添加');
INSERT INTO `sys_permission` VALUES ('18', '/role/index.shtml', '角色列表');
INSERT INTO `sys_permission` VALUES ('19', '/permission/allocation.shtml', '权限分配');
INSERT INTO `sys_permission` VALUES ('20', '/role/allocation.shtml', '角色分配');
INSERT INTO `sys_permission` VALUES ('4', '/permission/index.shtml', '权限列表');
INSERT INTO `sys_permission` VALUES ('6', '/permission/addPermission.shtml', '权限添加');
INSERT INTO `sys_permission` VALUES ('7', '/permission/deletePermissionById.shtml', '权限删除');
INSERT INTO `sys_permission` VALUES ('8', '/member/list.shtml', '用户列表');
INSERT INTO `sys_permission` VALUES ('9', '/member/online.shtml', '在线用户');

-- ----------------------------
-- Table structure for sys_permission_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_init`;
CREATE TABLE `sys_permission_init` (
  `id` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `permission_init` varchar(255) DEFAULT NULL COMMENT '需要具备的权限',
  `sort` int(50) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_init
-- ----------------------------
INSERT INTO `sys_permission_init` VALUES ('1', '/static/**', 'anon,kickout', '1');
INSERT INTO `sys_permission_init` VALUES ('2', '/ajaxLogin', 'anon,kickout', '2');
INSERT INTO `sys_permission_init` VALUES ('3', '/logout', 'logout,kickout', '3');
INSERT INTO `sys_permission_init` VALUES ('4', '/add', 'perms[权限添加:权限删除],roles[100002],kickout', '4');
INSERT INTO `sys_permission_init` VALUES ('5', '/**', 'user,kickout', '5');
INSERT INTO `sys_permission_init` VALUES ('6', '/getGifCode', 'anon,kickout', '2');
INSERT INTO `sys_permission_init` VALUES ('7', '/kickout', 'anon', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '100004');
INSERT INTO `sys_role` VALUES ('3', '权限角色', '100001');
INSERT INTO `sys_role` VALUES ('4', '用户中心', '100002');
INSERT INTO `sys_role` VALUES ('ae236732036f43cdbad6864cbeefd14a', '角色管理', '100003');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` varchar(64) NOT NULL,
  `rid` varchar(64) DEFAULT NULL COMMENT '角色ID',
  `pid` varchar(64) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '4', '8');
INSERT INTO `sys_role_permission` VALUES ('10', '3', '14');
INSERT INTO `sys_role_permission` VALUES ('11', '3', '15');
INSERT INTO `sys_role_permission` VALUES ('12', '3', '16');
INSERT INTO `sys_role_permission` VALUES ('13', '3', '17');
INSERT INTO `sys_role_permission` VALUES ('14', '3', '18');
INSERT INTO `sys_role_permission` VALUES ('15', '3', '19');
INSERT INTO `sys_role_permission` VALUES ('16', '3', '20');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '4');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '6');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '7');
INSERT INTO `sys_role_permission` VALUES ('2', '4', '9');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '8');
INSERT INTO `sys_role_permission` VALUES ('21', '1', '9');
INSERT INTO `sys_role_permission` VALUES ('22', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('23', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('24', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('25', '1', '13');
INSERT INTO `sys_role_permission` VALUES ('26', '1', '14');
INSERT INTO `sys_role_permission` VALUES ('27', '1', '15');
INSERT INTO `sys_role_permission` VALUES ('28', '1', '16');
INSERT INTO `sys_role_permission` VALUES ('29', '1', '17');
INSERT INTO `sys_role_permission` VALUES ('3', '4', '10');
INSERT INTO `sys_role_permission` VALUES ('30', '1', '18');
INSERT INTO `sys_role_permission` VALUES ('31', '1', '19');
INSERT INTO `sys_role_permission` VALUES ('32', '1', '20');
INSERT INTO `sys_role_permission` VALUES ('4', '4', '11');
INSERT INTO `sys_role_permission` VALUES ('5', '4', '12');
INSERT INTO `sys_role_permission` VALUES ('6', '3', '4');
INSERT INTO `sys_role_permission` VALUES ('7', '3', '6');
INSERT INTO `sys_role_permission` VALUES ('8', '3', '7');
INSERT INTO `sys_role_permission` VALUES ('9', '3', '13');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` varchar(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  `create_name_id` varchar(255) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_name_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin@qq.com', 'l9a0lajfwQ3VRSh4jUUJSQ==', '2016-06-16 11:15:33', '2017-04-16 17:05:17', '1', null, null, null);
INSERT INTO `sys_user` VALUES ('11', 'root', '8446666@qq.com', 'CBbObwI8VQ2MrKFGmnWL8w==', '2016-05-26 20:50:54', '2017-02-13 15:49:04', '1', null, null, null);
INSERT INTO `sys_user` VALUES ('12', '8446666', '8446666', 'x16YjoF1LNE=', '2016-05-27 22:34:19', '2016-06-15 17:03:16', '1', null, null, null);
INSERT INTO `sys_user` VALUES ('13', '123', '123', 'KL/QrouuaRWRWlkI0mpwbw==', '2016-05-27 22:34:19', '2016-06-15 17:03:16', '0', null, null, null);
INSERT INTO `sys_user` VALUES ('14', 'haiqin', '123123@qq.com', 'zrt4HMxuDq3V8tXGdIBKjA==', '2016-05-27 22:34:19', '2017-03-31 23:04:34', '1', null, null, null);
INSERT INTO `sys_user` VALUES ('2f36d45d11894dc397bbd149e03147f0', 'z77z', '1093615728@qq.com', '123', null, null, '1', null, '2017-04-23 19:15:13', '123');
INSERT INTO `sys_user` VALUES ('41828ae7ffc54108badf47d60e30cb8b', 'z77z', '1093615728@qq.com', '123', null, null, '1', null, '2017-04-23 19:25:28', '123');
INSERT INTO `sys_user` VALUES ('6634923de4a14b6ca3bac5fdf31563a8', 'z77z', '1093615728@qq.com', '123', null, null, '1', null, '2017-04-23 19:35:27', '123');
INSERT INTO `sys_user` VALUES ('7665b21dd0fe4a11bd300fedb72db2ff', 'z77z', '1093615728@qq.com', '123', null, null, '1', null, '2017-04-23 19:22:44', '123');
INSERT INTO `sys_user` VALUES ('88ad33008a724659859f437254acb6b2', 'z77z', '1093615728@qq.com', '123', null, null, '1', null, '2017-04-23 19:23:31', '123');
INSERT INTO `sys_user` VALUES ('f67f2b6b944f4e0db7fc8712a4a2c35e', 'z77z', '1093615728@qq.com', '123', null, null, '1', null, '2017-04-23 19:26:27', '123');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `uid` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `rid` varchar(64) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '12', '4');
INSERT INTO `sys_user_role` VALUES ('2', '11', '3');
INSERT INTO `sys_user_role` VALUES ('3', '11', '4');
INSERT INTO `sys_user_role` VALUES ('4', '1', '1');
