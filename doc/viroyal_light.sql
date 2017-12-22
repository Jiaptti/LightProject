-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2017-12-22 14:47:06
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
('上海', 1),
('贵州', 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `light_info`
--

INSERT INTO `light_info` (`id`, `light_id`, `light_info`, `light_area`, `light_a_id`, `light_register`) VALUES
(1, 1002, '3号路灯', '漕宝路401号', 3, 1),
(2, 1003, '4号路灯', '漕宝路402号', 3, 1),
(3, 1004, '5号路灯', '漕宝路480号', 3, 1),
(4, 1005, '5号路灯', '漕宝路800号', 3, 1);

-- --------------------------------------------------------

--
-- 表的结构 `light_lightdb`
--

CREATE TABLE IF NOT EXISTS `light_lightdb` (
  `light_id` int(8) NOT NULL COMMENT '路灯id',
  `light_status` text NOT NULL COMMENT '路灯状态开关',
  `light_voltage` int(1) NOT NULL,
  `light_current` int(1) NOT NULL,
  `light_strategy` text NOT NULL COMMENT '路灯策略',
  `light_level` int(3) NOT NULL COMMENT '路灯亮度',
  `light_temperature` text COMMENT '温度',
  `light_humidity` text COMMENT '湿度',
  `light_autoreport` int(16) NOT NULL COMMENT '路灯自动上报周期',
  PRIMARY KEY (`light_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路灯';

--
-- 转存表中的数据 `light_lightdb`
--

INSERT INTO `light_lightdb` (`light_id`, `light_status`, `light_voltage`, `light_current`, `light_strategy`, `light_level`, `light_temperature`, `light_humidity`, `light_autoreport`) VALUES
(1002, 'on', 110, 800, '6点开', 255, '6', '65', 10000),
(1003, 'off', 110, 750, '18点开', 200, '5', '54', 10000);

-- --------------------------------------------------------

--
-- 表的结构 `sys_permission`
--

CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  `perms` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- 转存表中的数据 `sys_permission`
--

INSERT INTO `sys_permission` (`id`, `url`, `name`, `perms`) VALUES
(1, '/user/user.jsp', '用户列表', 'sys:user:list,sys:user:info'),
(2, '/user/edit.jsp', '用户添加', 'sys:user:save,sys:role:select'),
(3, '/user/edit.jsp', '用户删除', 'sys:user:delete'),
(4, '/user/edit.jsp', '用户更新', 'sys:user:update,sys:role:select'),
(5, '', '用户Session踢出', 'sys:user:kickout'),
(6, '', '用户激活&禁止', 'sys:user:status'),
(7, '/role/role.jsp', '角色列表', 'sys:role:list,sys:role:info'),
(8, '/role/edit.jsp', '角色删除', 'sys:role:delete'),
(9, '/role/edit.jsp', '角色添加', 'sys:role:save,sys:permission:select'),
(10, '/role/edit.jsp', '角色更新', 'sys:role:update,sys:permission:select'),
(11, '/permission/permission.jsp', '权限列表', 'sys:permission:list,sys:permission:info'),
(12, '/permission/edit.jsp', '权限添加', 'sys:permission:save,sys:permission:select'),
(13, '/permission/edit.jsp', '权限删除', 'sys:permission:delete'),
(14, '/permission/edit.jsp', '权限更新', 'sys:permission:update,sys:permission:select');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- 转存表中的数据 `sys_permission_init`
--

INSERT INTO `sys_permission_init` (`id`, `url`, `permission_init`, `sort`) VALUES
(1, '/static/**', 'anon,kickout', 1),
(2, '/ajaxLogin', 'anon,kickout', 2),
(3, '/logout', 'logout,kickout', 3),
(4, '/add', 'perms[权限添加:权限删除],roles[100002],kickout', 4),
(5, '/**', 'user,kickout', 5),
(6, '/getGifCode', 'anon,kickout', 2),
(7, '/kickout', 'anon', 2),
(10, '/delete', 'perms[权限删除]', 2),
(11, '/login', 'anon,kickout', 2);

-- --------------------------------------------------------

--
-- 表的结构 `sys_role`
--

CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `sys_role`
--

INSERT INTO `sys_role` (`id`, `name`, `type`) VALUES
(1, 'admin', '100004'),
(2, 'user', '100001'),
(3, 'worker', '100002');

-- --------------------------------------------------------

--
-- 表的结构 `sys_role_permission`
--

CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- 转存表中的数据 `sys_role_permission`
--

INSERT INTO `sys_role_permission` (`id`, `rid`, `pid`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 1, 11),
(12, 1, 12),
(13, 1, 13),
(14, 1, 14),
(15, 2, 2),
(16, 2, 3),
(17, 2, 4),
(18, 2, 5),
(19, 3, 6);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=30 ;

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`id`, `nickname`, `username`, `pswd`, `phone`, `email`, `create_time`, `last_login_time`, `status`, `create_name_id`, `last_update_time`, `last_update_name_id`) VALUES
(1, 'admin', 'admin', 'l9a0lajfwQ3VRSh4jUUJSQ==', '15255257895', 'admin@qq.com', '2016-06-16 11:15:33', '2017-12-22 13:51:47', '1', NULL, NULL, NULL),
(2, 'root', 'root', 'x16YjoF1LNE=', '12332323232', '8446666@qq.com', '2017-12-13 19:30:44', '2017-02-13 15:49:04', '1', NULL, NULL, NULL),
(27, 'zhao', 'zhao', 'eC/MmhASeJDApVs80EUFtg==', '15548548788', '123@123.com', '2017-12-20 16:02:26', '2017-12-22 11:22:55', '1', NULL, NULL, NULL),
(28, 'luo', 'luo', '2VAatbK+bS2oD5LrkRnNpw==', '18616981234', 'luo@163.com', '2017-12-20 16:25:31', '2017-12-21 11:28:05', '1', NULL, NULL, NULL),
(29, 'mars', 'mars', 'n6PE2kNFqCMnRlhJ0pZlpg==', '13816214814', '460837364@qq.com', '2017-12-22 11:29:20', '2017-12-22 11:29:36', '1', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_role`
--

CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- 转存表中的数据 `sys_user_role`
--

INSERT INTO `sys_user_role` (`id`, `uid`, `rid`) VALUES
(1, 1, 1),
(2, 2, 2),
(19, 27, 2),
(20, 28, 3),
(21, 29, 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
