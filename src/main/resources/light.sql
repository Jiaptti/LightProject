--
-- 数据库: `viroyal_light`
--

-- --------------------------------------------------------

--
-- 表的结构 `sys_area`
--

CREATE TABLE IF NOT EXISTS `sys_area` (
  `id`       BIGINT(10) NOT NULL AUTO_INCREMENT,
  `area_name` VARCHAR(80)         DEFAULT NULL
  COMMENT '区域名称',
  `area_id`   INT(11)             DEFAULT NULL
  COMMENT '区域所属id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 7;

--
-- 转存表中的数据 `sys_area`
--

INSERT INTO `sys_area` (`id`, `area_name`, `area_id`) VALUES
  (1, '黔西区', 1001),
  (2, '六枝特区', 1002),
  (3, '水城县', 2001),
  (4, '红花岗区', 2002),
  (5, '闵行区', 3005),
  (6, '徐汇区', 4008);

-- --------------------------------------------------------

--
-- 表的结构 `sys_city_area`
--

CREATE TABLE IF NOT EXISTS `sys_city_area` (
  `id`      BIGINT(10) NOT NULL AUTO_INCREMENT,
  `city_id` BIGINT(10)          DEFAULT NULL
  COMMENT '对应sys_city中的city_id',
  `area_id` BIGINT(10)          DEFAULT NULL
  COMMENT '对应sys_area中的area_id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 7;

--
-- 转存表中的数据 `sys_city_area`
--

INSERT INTO `sys_city_area` (`id`, `city_id`, `area_id`) VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 2, 3),
  (4, 3, 4),
  (5, 4, 5),
  (6, 4, 6);
-- --------------------------------------------------------

--
-- 表的结构 `sys_city`
--

CREATE TABLE IF NOT EXISTS `sys_city` (
  `id`        BIGINT(10) NOT NULL AUTO_INCREMENT,
  `city_name` VARCHAR(50)         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 5;

--
-- 转存表中的数据 `sys_city`
--

INSERT INTO `sys_city` (`id`, `city_name`) VALUES
  (1, '毕节市'),
  (2, '六盘水市'),
  (3, '遵义市'),
  (4, '上海市');

-- --------------------------------------------------------

--
-- 表的结构 `sys_light`
--

CREATE TABLE IF NOT EXISTS `sys_light` (
  `id`           BIGINT(10) NOT NULL AUTO_INCREMENT,
  `status`       VARCHAR(1)          DEFAULT '1'
  COMMENT '1:开灯，0:关灯',
  `voltage`      BIGINT(20)          DEFAULT NULL
  COMMENT '电压',
  `current`      BIGINT(20)          DEFAULT NULL
  COMMENT '电流',
  `traffic_flow` VARCHAR(1)          DEFAULT '1'
  COMMENT '车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息',
  `temperature`  BIGINT(20)          DEFAULT NULL
  COMMENT '温度',
  `humidity`     BIGINT(20)          DEFAULT NULL
  COMMENT '湿度',
  `autoreport`   BIGINT(16)          DEFAULT NULL
  COMMENT '路灯自动上报周期',
  `strategy`     VARCHAR(1)          DEFAULT '1'
  COMMENT '引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间',
  `datetime`     DATETIME            DEFAULT NULL,
  `code`         VARCHAR(100)        DEFAULT NULL
  COMMENT '对应light_info的code',
  `lightness`    INT(11)             DEFAULT NULL
  COMMENT '亮度',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 13;

--
-- 转存表中的数据 `sys_light`
--

INSERT INTO `sys_light` (`id`, `status`, `voltage`, `current`, `traffic_flow`, `temperature`, `humidity`, `autoreport`, `strategy`, `datetime`, `code`, `lightness`)
VALUES
  (1, '1', 110, 800, '1', 6, 65, 10000, '4', '2017-12-01 17:40:40', '1001', 0),
  (2, '1', 100, 600, '1', 16, 85, 20000, '4', '2016-12-01 12:00:06', '1002', 0),
  (3, '1', 110, 100, '0', 60, 120, 10000, '4', '2017-12-31 22:00:36', '1003', 8),
  (4, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1004', 0),
  (5, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1005', 0),
  (6, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1006', 0),
  (7, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1007', 0),
  (8, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1008', 0),
  (9, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1009', 0),
  (10, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1010', 0),
  (11, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1011', 0),
  (12, '1', 140, 800, '1', 24, 85, 20000, '4', '2017-6-01 23:13:22', '1012', 0);

-- --------------------------------------------------------

--
-- 表的结构 `sys_light_group`
--

CREATE TABLE IF NOT EXISTS `sys_light_group` (
  `id`             BIGINT(10) NOT NULL AUTO_INCREMENT,
  `group_id`       INT(10)  DEFAULT NULL
  COMMENT '组id',
  `light_id`       BIGINT(20)          DEFAULT NULL
  COMMENT '此组内的灯id',
  `group_name`     VARCHAR(100)        DEFAULT NULL
  COMMENT '组名',
  `create_user_id` BIGINT(10)          DEFAULT NULL
  COMMENT '由谁创建的组',
  `responsible_id` BIGINT(10)          DEFAULT NULL
  COMMENT '谁负责的组',
  `create_time`    DATETIME            DEFAULT NULL
  COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 4;

--
-- 转存表中的数据 `sys_light_group`
--
INSERT INTO `sys_light_group` (`id`,group_id, `light_id`, `group_name`, `create_user_id`, `responsible_id`, `create_time`) VALUES
  (1, 1, 1, 'xx组路灯1', 1, 2, '2017-12-31 17:20:40'),
  (2, 1, 2, 'xx组路灯2', 1, 3, '2017-12-31 13:30:43'),
  (3, 1, 3, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (4, 1, 4, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (5, 2, 5, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (6, 2, 6, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (7, 2, 7, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (8, 2, 8, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (9, 3, 9, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (10, 3, 10, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (11, 3, 11, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30'),
  (12, 3, 12, 'xx组路灯3', 1, 4, '2017-12-31 7:55:30');

-- --------------------------------------------------------

--
-- 表的结构 `sys_light_info`
--

CREATE TABLE IF NOT EXISTS `sys_light_info` (
  `id`                    BIGINT(10) NOT NULL AUTO_INCREMENT,
  `code`                  VARCHAR(100)        DEFAULT NULL
  COMMENT '路灯编码',
  `info`                  VARCHAR(100)        DEFAULT NULL
  COMMENT '路灯信息',
  `status`                VARCHAR(1)          DEFAULT '1'
  COMMENT '1:正在使用，0:刚注册信息并没有投入使用',
  `longitude`             FLOAT      NOT NULL
  COMMENT '经度',
  `latitude`              FLOAT      NOT NULL
  COMMENT '纬度',
  `street_id`             BIGINT(10) NOT NULL
  COMMENT '所属街道id',
  `voltage_threshold`     INT(11)             DEFAULT NULL
  COMMENT '电压报警阀值',
  `current_threshold`     INT(11)             DEFAULT NULL
  COMMENT '电流报警阀值',
  `temperature_threshold` INT(11)             DEFAULT NULL
  COMMENT '温度报警阀值',
  `humidity_threshold`    INT(11)             DEFAULT NULL
  COMMENT '湿度报警阀值',
  `lightness_threshold`   INT(11)             DEFAULT NULL
  COMMENT '亮度报警阀值',
  `voltage_overload`      INT(11)             DEFAULT NULL
  COMMENT '电压过载阀值',
  `current_overload`      INT(11)             DEFAULT NULL
  COMMENT '电流过载阀值',
  `temperature_overload`  INT(11)             DEFAULT NULL
  COMMENT '温度过载阀值',
  `humidity_overload`     INT(11)             DEFAULT NULL
  COMMENT '师傅过载阀值',
  `lightness_overload`    INT(11)             DEFAULT NULL
  COMMENT '亮度过载阀值',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 13;

--
-- 转存表中的数据 `sys_light_info`
--

INSERT INTO `sys_light_info` (`id`, `code`, `info`, `status`, `longitude`, `latitude`, `street_id`, `voltage_threshold`, `current_threshold`, `temperature_threshold`, `humidity_threshold`, `lightness_threshold`, `voltage_overload`, `current_overload`, `temperature_overload`, `humidity_overload`, `lightness_overload`)
VALUES
  (1, '1001', 'xx1路灯', '0', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (2, '1002', 'xx2路灯', '1', 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (3, '1003', 'xx3路灯', '1', 0, 0, 2, 160, 0, 0, 0, 0, 0, 90, 0, 0, 0),
  (4, '1004', 'xx4路灯', '0', 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (5, '1005', 'xx5路灯', '0', 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (6, '1006', 'xx6路灯', '0', 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (7, '1007', 'xx7路灯', '0', 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (8, '1008', 'xx8路灯', '0', 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (9, '1009', 'xx9路灯', '0', 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (10, '1010', 'xx10路灯', '0', 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (11, '1011', 'xx11路灯', '0', 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
  (12, '1012', 'xx12路灯', '0', 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------
--
-- 表的结构 `sys_street`
--

CREATE TABLE IF NOT EXISTS `sys_street` (
  `id`          BIGINT(10) NOT NULL AUTO_INCREMENT,
  `street_name` VARCHAR(100)        DEFAULT NULL
  COMMENT '街道名称',
  `street_id`   INT(11)             DEFAULT NULL
  COMMENT '街道所属id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 7;

--
-- 转存表中的数据 `sys_street`
--

INSERT INTO `sys_street` (`id`, `street_name`, `street_id`) VALUES
  (1, '莲城街道', 10011),
  (2, '九龙街道', 10021),
  (3, '尖山街道', 20011),
  (4, '老城街道', 20021),
  (5, '莲花路', 60021),
  (6, '桂平路', 60023);

-- --------------------------------------------------------
--
-- 表的结构 `sys_area_street`
--
CREATE TABLE IF NOT EXISTS `sys_area_street` (
  `id`        BIGINT(10) NOT NULL AUTO_INCREMENT,
  `area_id`   BIGINT(10)          DEFAULT NULL
  COMMENT '区域id',
  `street_id` BIGINT(10)          DEFAULT NULL
  COMMENT '街道id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 7;

--
-- 转存表中的数据 `sys_street`
--

INSERT INTO `sys_area_street` (`id`, `area_id`, `street_id`) VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 3, 3),
  (4, 4, 4),
  (5, 5, 5),
  (6, 6, 6);

-- --------------------------------------------------------
--
-- 表的结构 `sys_light_record`
--

CREATE TABLE IF NOT EXISTS `sys_light_record` (
  `id`          BIGINT(10)   NOT NULL AUTO_INCREMENT,
  `r_date`      DATETIME              DEFAULT NULL
  COMMENT '记录时间',
  `r_status`    VARCHAR(255)          DEFAULT NULL
  COMMENT '记录状态',
  `r_operation` VARCHAR(1000)         DEFAULT NULL
  COMMENT '记录操作',
  `r_userid`    BIGINT(20)            DEFAULT NULL
  COMMENT '谁进行了操作',
  `code`        VARCHAR(100) NOT NULL
  COMMENT '对应sys_light_info里的code',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 3;

--
-- 转存表中的数据 `sys_light_record`
--

INSERT INTO `sys_light_record` (`id`, `r_date`, `r_status`, `r_operation`, `r_userid`, `code`) VALUES
  (1, '2016-06-16 11:15:33', '更新路灯', '对路灯位置进行了修改', 1, '1008'),
  (2, '2017-12-22 11:01:56', '指派路灯', '对路灯指派了维修人员', 2, '1001');

-- --------------------------------------------------------

--
-- 表的结构 `sys_light_strategy`
--

CREATE TABLE IF NOT EXISTS `sys_light_strategy` (
  `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
  `smooth_level`  INT(10)             DEFAULT NULL
  COMMENT '车流量少时的亮度',
  `traffic_level` VARCHAR(100)        DEFAULT NULL
  COMMENT '车流量多时的亮度',
  `open_time`     VARCHAR(100)        DEFAULT NULL
  COMMENT '打开时间',
  `close_time`    VARCHAR(100)        DEFAULT NULL
  COMMENT '关闭时间',
  `type`          VARCHAR(100)        DEFAULT NULL
  COMMENT '冬季，夏季',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 5;

--
-- 转存表中的数据 `sys_light_strategy`
--

INSERT INTO `sys_light_strategy` (`id`, `smooth_level`, `traffic_level`, `open_time`, `close_time`, `type`) VALUES
  (1, 200, '255', '18:00:00', '07:00:00', '春季'),
  (2, 180, '255', '19:45:00', '05:30:00', '夏季'),
  (3, 180, '255', '19:00:00', '06:00:00', '秋季'),
  (4, 220, '255', '17:45:00', '07:25:00', '冬季');

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_light`
--

CREATE TABLE IF NOT EXISTS `sys_user_light` (
  `id`       BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id`  BIGINT(20)          DEFAULT NULL
  COMMENT '用户ID',
  `light_id` BIGINT(20)          DEFAULT NULL
  COMMENT '路灯ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 13;

--
-- 转存表中的数据 `sys_user_light`
--

INSERT INTO `sys_user_light` (`id`, `user_id`, `light_id`) VALUES
  (1, 2, 1),
  (2, 2, 2),
  (3, 2, 3),
  (4, 2, 4),
  (5, 2, 5),
  (6, 3, 6),
  (7, 3, 7),
  (8, 3, 8),
  (9, 3, 9),
  (10, 4, 10),
  (11, 4, 11),
  (12, 4, 12);

-- --------------------------------------------------------

--
-- 表的结构 `sys_permission`
--

CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id`    BIGINT(20) NOT NULL AUTO_INCREMENT,
  `url`   VARCHAR(256)        DEFAULT NULL
  COMMENT 'url地址',
  `name`  VARCHAR(64)         DEFAULT NULL
  COMMENT 'url描述',
  `perms` VARCHAR(500)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 15;

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
  `id`              BIGINT(20) NOT NULL AUTO_INCREMENT,
  `url`             VARCHAR(255)        DEFAULT NULL
  COMMENT '链接地址',
  `permission_init` VARCHAR(255)        DEFAULT NULL
  COMMENT '需要具备的权限',
  `sort`            INT(50)             DEFAULT NULL
  COMMENT '排序',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 15;

--
-- 转存表中的数据 `sys_permission_init`
--

INSERT INTO `sys_permission_init` (`id`, `url`, `permission_init`, `sort`) VALUES
  (1, '/static/**', 'anon', 1),
  (2, '/ajaxLogin', 'anon', 2),
  (3, '/logout', 'logout', 3),
  (4, '/**', 'kickout,perms[sys:user:list],authc', 3),
  (5, '/getGifCode', 'anon', 2),
  (6, '/kickout', 'anon', 2),
  (7, '/userLogin', 'anon', 2),
  (12, '/favicon.ico', 'anon', 2),
  (13, '/login', 'anon', 2),
  (14, '/403', 'anon', 2);

-- --------------------------------------------------------

--
-- 表的结构 `sys_role`
--

CREATE TABLE IF NOT EXISTS `sys_role` (
  `id`   BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32)         DEFAULT NULL
  COMMENT '角色名称',
  `type` VARCHAR(10)         DEFAULT NULL
  COMMENT '角色类型',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 4;

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
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `rid` BIGINT(20)          DEFAULT NULL
  COMMENT '角色ID',
  `pid` BIGINT(20)          DEFAULT NULL
  COMMENT '权限ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 20;

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
  `id`                  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nickname`            VARCHAR(20)         DEFAULT NULL
  COMMENT '用户昵称',
  `username`            VARCHAR(20)         DEFAULT NULL,
  `pswd`                VARCHAR(32)         DEFAULT NULL
  COMMENT '密码',
  `phone`               VARCHAR(11)         DEFAULT NULL,
  `email`               VARCHAR(128)        DEFAULT NULL
  COMMENT '邮箱|登录帐号',
  `create_time`         DATETIME            DEFAULT NULL
  COMMENT '创建时间',
  `last_login_time`     DATETIME            DEFAULT NULL
  COMMENT '最后登录时间',
  `status`              VARCHAR(1)          DEFAULT '1'
  COMMENT '1:有效，0:禁止登录',
  `create_name_id`      VARCHAR(255)        DEFAULT NULL,
  `last_update_time`    DATETIME            DEFAULT NULL,
  `last_update_name_id` VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 28;

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`id`, `nickname`, `username`, `pswd`, `phone`, `email`, `create_time`, `last_login_time`, `status`, `create_name_id`, `last_update_time`, `last_update_name_id`)
VALUES
  (1, 'admin', 'admin', 'l9a0lajfwQ3VRSh4jUUJSQ==', '15255257895', 'admin@qq.com', '2016-06-16 11:15:33',
      '2018-01-03 17:19:08', '1', NULL, NULL, NULL),
  (2, '张三', 'zhang', 'eC/MmhASeJBCbfh7W0YU1A==', '13316998986', '123@123.com', '2017-12-24 19:37:40', NULL, '1', '1',
      NULL, NULL),
  (3, '李四', 'lisi', 'bR28UodwBWRDm+buWIaZbg==', '15575590909', '321@123.com', '2017-12-24 19:38:06', NULL, '1', '1',
      NULL, NULL),
  (4, '王五', 'wangwu', 'Yb7Dc8/cPeCL8NwOjnYSRA==', '13317979888', '456@123.com', '2017-12-24 19:38:30', NULL, '1', '1',
      NULL, NULL),
  (20, '罗总', 'luo', '7NjQPajcVm2j1j8LIfaQew==', '15575889999', '123@123.com', '2017-12-25 10:33:48',
       '2018-01-03 18:07:51', '1', '1', NULL, NULL),
  (21, '权哥', 'quan', '7NjQPajcVm1dzbZMJvVcRA==', '15475878981', '132@123.com', '2017-12-25 10:34:26', NULL, '1', '1',
       NULL, NULL),
  (22, '老司机', 'laosiji', '7NjQPajcVm1PC6tJMaodpQxtrQrzzUma', '15585858585', '123@123.com', '2017-12-25 10:34:55',
       '2017-12-25 10:36:52', '1', '1', NULL, NULL),
  (25, '赵总', 'zxb', '7NjQPajcVm3rcjWOTacgxQ==', '13816214814', '460837364@qq.com', '2017-12-26 11:11:50',
       '2018-01-03 10:43:37', '1', '20', NULL, NULL),
  (26, '哈哈哈哈哈', 'sunyy', '8AetwF4m3o3Y4JDqkLIeLA==', '13145678961', '2222@11.COM', '2017-12-27 13:59:21',
       '2018-01-03 16:17:03', '1', '1', '2018-01-03 15:09:37', '1'),
  (27, '武因生', '武因生', '7NjQPajcVm05iEcgH3wDZ/3531yNGT4C', '15821361405', 'wys484112@163.com', '2017-12-28 00:00:00',
       '2017-12-28 19:45:58', '1', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_role`
--

CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `uid` BIGINT(20)          DEFAULT NULL
  COMMENT '用户ID',
  `rid` BIGINT(20)          DEFAULT NULL
  COMMENT '角色ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 21;

--
-- 转存表中的数据 `sys_user_role`
--

INSERT INTO `sys_user_role` (`id`, `uid`, `rid`) VALUES
  (1, 1, 1),
  (2, 2, 3),
  (3, 3, 3),
  (4, 4, 3),
  (13, 20, 1),
  (14, 21, 1),
  (15, 22, 1),
  (18, 25, 1),
  (19, 26, 1),
  (20, 27, 3);
