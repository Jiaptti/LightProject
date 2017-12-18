-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2017-12-15 17:28:48
-- 服务器版本: 5.5.58-0ubuntu0.14.04.1
-- PHP 版本: 5.5.9-1ubuntu4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `viroyal_light`
--

-- --------------------------------------------------------

--
-- 表的结构 `light_a_street`
--

CREATE TABLE IF NOT EXISTS `light_a_street` (
  `street_name` text NOT NULL,
  `street_id` int(4) NOT NULL,
  `street_light_id` text NOT NULL COMMENT '包含路灯的id',
  `street_a_id` int(4) NOT NULL COMMENT '对应area里的id',
  `street_managerid` int(11) NOT NULL COMMENT 'sys_user里的id',
  PRIMARY KEY (`street_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `light_a_street`
--

INSERT INTO `light_a_street` (`street_name`, `street_id`, `street_light_id`, `street_a_id`, `street_managerid`) VALUES
  ('漕宝路', 3, '1002,1003', 1, 11);

-- --------------------------------------------------------

--
-- 表的结构 `light_carea`
--

CREATE TABLE IF NOT EXISTS `light_carea` (
  `area_name` text NOT NULL,
  `area_id` int(4) NOT NULL,
  `area_c_id` int(4) NOT NULL COMMENT '对应city中的id',
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

--
-- 转存表中的数据 `light_carea`
--

INSERT INTO `light_carea` (`area_name`, `area_id`, `area_c_id`) VALUES
  ('徐汇区', 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `light_city`
--

CREATE TABLE IF NOT EXISTS `light_city` (
  `city_name` text NOT NULL,
  `city_id` int(4) NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市名称';

--
-- 转存表中的数据 `light_city`
--

INSERT INTO `light_city` (`city_name`, `city_id`) VALUES
  ('上海', 1);

-- --------------------------------------------------------

--
-- 表的结构 `light_history`
--

CREATE TABLE IF NOT EXISTS `light_history` (
  `history_date` datetime NOT NULL,
  `history_light_id` int(8) NOT NULL,
  `history_light_status` text NOT NULL,
  `history_light_strategy` text NOT NULL,
  PRIMARY KEY (`history_light_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `light_info`
--

CREATE TABLE IF NOT EXISTS `light_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `light_id` int(11) NOT NULL,
  `light_info` text NOT NULL,
  `light_area` text NOT NULL,
  `light_a_id` int(11) NOT NULL COMMENT 'area里的id',
  `light_register` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `light_id` (`light_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `light_info`
--

INSERT INTO `light_info` (`id`, `light_id`, `light_info`, `light_area`, `light_a_id`, `light_register`) VALUES
  (1, 1002, '3号路灯', '漕宝路401号', 3, 0),
  (2, 1003, '4号路灯', '漕宝路402号', 3, 1),
  (3, 1004, '5号路灯', '漕宝路480号', 3, 1);

-- --------------------------------------------------------

--
-- 表的结构 `light_lightdb`
--

CREATE TABLE IF NOT EXISTS `light_lightdb` (
  `light_id` int(8) NOT NULL COMMENT '路灯id',
  `light_status` text NOT NULL COMMENT '路灯状态开关',
  `light_strategy` text NOT NULL COMMENT '路灯策略',
  `light_level` int(3) NOT NULL COMMENT '路灯亮度',
  `light_temperature` text COMMENT '温度',
  `light_humidity` text COMMENT '湿度',
  PRIMARY KEY (`light_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路灯';

--
-- 转存表中的数据 `light_lightdb`
--

INSERT INTO `light_lightdb` (`light_id`, `light_status`, `light_strategy`, `light_level`, `light_temperature`, `light_humidity`) VALUES
  (1002, 'on', '6点开', 255, '3', '65'),
  (1003, 'off', '18点开', 200, '5', '54');

-- --------------------------------------------------------

--
-- 表的结构 `sys_permission`
--

CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- 转存表中的数据 `sys_permission`
--

INSERT INTO `sys_permission` (`id`, `url`, `name`) VALUES
  (4, '/permission/index.shtml', '权限列表'),
  (6, '/permission/addPermission.shtml', '权限添加'),
  (7, '/permission/deletePermissionById.shtml', '权限删除'),
  (8, '/member/list.shtml', '用户列表'),
  (9, '/member/online.shtml', '在线用户'),
  (10, '/member/changeSessionStatus.shtml', '用户Session踢出'),
  (11, '/member/forbidUserById.shtml', '用户激活&禁止'),
  (12, '/member/deleteUserById.shtml', '用户删除'),
  (16, '/role/deleteRoleById.shtml', '角色列表删除'),
  (17, '/role/addRole.shtml', '角色列表添加'),
  (18, '/role/index.shtml', '角色列表'),
  (19, '/permission/allocation.shtml', '权限分配'),
  (20, '/role/allocation.shtml', '角色分配');

-- --------------------------------------------------------

--
-- 表的结构 `sys_permission_init`
--

CREATE TABLE IF NOT EXISTS `sys_permission_init` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `permission_init` varchar(255) DEFAULT NULL COMMENT '需要具备的权限',
  `sort` int(50) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `sys_permission_init`
--

INSERT INTO `sys_permission_init` (`id`, `url`, `permission_init`, `sort`) VALUES
  (1, '/static/**', 'anon,kickout', 1),
  (2, '/ajaxLogin', 'anon,kickout', 2),
  (3, '/login', 'anon,kickout', 2),
  (4, '/logout', 'logout,kickout', 3),
  (5, '/add', 'perms[权限添加:权限删除],roles[100002],kickout', 4),
  (6, '/**', 'user,kickout', 5),
  (7, '/getGifCode', 'anon,kickout', 2),
  (8, '/kickout', 'anon', 2),
  (10, '/delete', 'perms[权限删除]', 2);

-- --------------------------------------------------------

--
-- 表的结构 `sys_role`
--

CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `sys_role`
--

INSERT INTO `sys_role` (`id`, `name`, `type`) VALUES
  (1, 'admin', '100004'),
  (3, 'user', '100001'),
  (4, 'worker', '100002');

-- --------------------------------------------------------

--
-- 表的结构 `sys_role_permission`
--

CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33 ;

--
-- 转存表中的数据 `sys_role_permission`
--

INSERT INTO `sys_role_permission` (`id`, `rid`, `pid`) VALUES
  (1, 4, 8),
  (2, 4, 9),
  (3, 4, 10),
  (4, 4, 11),
  (5, 4, 12),
  (6, 3, 4),
  (7, 3, 6),
  (8, 3, 7),
  (9, 3, 13),
  (10, 3, 14),
  (11, 3, 15),
  (12, 3, 16),
  (13, 3, 17),
  (14, 3, 18),
  (15, 3, 19),
  (16, 3, 20),
  (17, 1, 4),
  (18, 1, 6),
  (19, 1, 7),
  (20, 1, 8),
  (21, 1, 9),
  (22, 1, 10),
  (23, 1, 11),
  (24, 1, 12),
  (25, 1, 13),
  (26, 1, 14),
  (27, 1, 15),
  (28, 1, 16),
  (29, 1, 17),
  (30, 1, 18),
  (31, 1, 19),
  (32, 1, 20);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user`
--

CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `username` varchar(20) DEFAULT NULL,
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` varchar(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  `create_name_id` varchar(255) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_name_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`id`, `nickname`, `username`, `pswd`, `phone`, `email`, `create_time`, `last_login_time`, `status`, `create_name_id`, `last_update_time`, `last_update_name_id`) VALUES
  (1, 'admin', 'admin', 'l9a0lajfwQ3VRSh4jUUJSQ==', NULL, 'admin@qq.com', '2016-06-16 11:15:33', '2017-12-15 16:24:12', '1', NULL, NULL, NULL),
  (11, 'root', 'root', 'x16YjoF1LNE=', '12332323232', '8446666@qq.com', '2017-12-13 19:30:44', '2017-02-13 15:49:04', '1', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_role`
--

CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `sys_user_role`
--

INSERT INTO `sys_user_role` (`id`, `uid`, `rid`) VALUES
  (2, 11, 3),
  (4, 1, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
