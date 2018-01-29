package com.viroyal.light.common.utils;

public class BaseConstant {

    //成功返回的Code
    public static final int SUCCESS_CODE = 200;
    //错误返回的Code
    public static final int ERROR_CODE = 500;


    //消息
    public static final String MESSAGE = "message";
    //code
    public static final String CODE = "code";
    //Ajax成功返回的status
    public static final String STATUS = "status";
    //message no result
    public static final String NO_RESULT = "No Result";
    //保存失败
    public static final String SAVE_FAILURE="Save Failure";
    //删除失败
    public static final String DELETE_FAILURE="Delete Failure";
    //更新失败
    public static final String UPDATE_FAILURE="Update Failure";
    //查询失败
    public static final String QUERY_FAILURE="Query Failure";
    //message success
    public static final String SUCCESS_RESULT = "Success";
    //返回user的key
    public static final String USER = "user";
    //返回userList的key
    public static final String VALUE_LIST = "list";
    //踢出成功
    public static final String KICKOUT_SUCCESS = "Kickout Success";
    //踢出失败
    public static final String KICKOUT_FAILURE = "Kickout Failure";
    //登录成功
    public static final String LOGIN_SUCCESS = "登录成功";
    //登录失败
    public static final String LOGIN_FAILURE = "登录失败";
    //验证码错误
    public static final String ERROR_VERIFICATION_CODR = "验证码错误";
    //验证码不能为空
    public static final String NULL_VERIFICATION_CODR = "验证码不能为空";
    //登录的Session Attribute
    public static final String SESSION_ATTRIBUTE = "session_attribute";
    //登出成功
    public static final String LOGOUT_SUCCESS = "登出成功";
    //登出失败
    public static final String LOGOUT_FAILURE = "登出失败";


    //用户名超过指定长度，需在{min} - {max}之间
    public static final String USER_ACCOUNT_LENGTH = "用户名长度不正确，需在{%1$d}~{%2$d}之间";
    //用户姓名超过指定长度，需在{min} - {max}之间
    public static final String USER_NAME_LENGTH = "用户姓名长度不正确，需在{%1$d}~{%2$d}之间";
    //用户密码超过指定长度，需在{min} - {max}之间
    public static final String USER_PASSWORD_LENGTH = "用户密码长度不正确，需在{%1$d}~{%2$d}之间";
    //用户状态超过指定长度，需在{min} - {max}之间
    public static final String USER_STATUS_LENGTH = "用户密码长度不正确，需在{%1$d}~{%2$d}之间";
    //id错误
    public static final String ID_ERROR = "请输入正确的id";
    //角色id为空
    public static final String USER_ROLE_ID_IS_EMPTY = "角色id为空";
    //用户已经存在
    public static final String USER_EXIST = "用户已经存在";
    //用户名为空
    public static final String USER_ACCOUNT_IS_EMPTY = "用户名为空";
    //用户姓名为空
    public static final String USER_NAME_IS_EMPTY= "用户姓名为空";
    //请输入请输入中文姓名
    public static final String USER_NAME_RIGHT_FORMAT = "请输入中文姓名";
    //用户密码为空
    public static final String USER_PASSWORD_IS_EMPTY = "用户密码为空";
    //输入正确格式的手机号码
    public static final String USER_PHONE_RIGHT_FORMAT = "输入正确格式的手机号码";
    //输入正确格式的邮箱
    public static final String USER_EMAIL_RIGHT_FORMAT = "输入正确格式的邮箱";
    //请输入用户状态是否可用
    public static final String USER_NO_STATUS = "请输入用户状态是否可用";
    //请输入用户id
    public static final String NO_USER_ID = "请输入用户id";
    //请输入原始密码
    public static final String NO_ORIGIN_PASSWORD = "请输入原始密码";
    //请输入需要修改的密码
    public static final String NO_NEW_PASSWORD = "请输入需要修改的密码";
    //请确认密码是否正确
    public static final String CHECKOUT_USER_PASSWORD = "请确认密码是否正确";
    //请添加更新对象的id
    public static final String NO_UPDATE_ID = "请添加更新对象的id";
    //没有要分派的id
    public static final String NO_DISPATCH_ID = "没有要分派的id";
    //账号密码不能为空
    public static final String USER_ACCOUNT_NOT_NULL = "用户名密码不能为空";
    //用户不存在错误
    public static final String USER_ACCOUNT = "帐号或密码不正确，请确认用户名密码";
    //权限id错误
    public static final String NO_PERMISSION_ID = "没有更新的权限id";
    //请求错误
    public static final String REQUEST_ERROR = "请求错误";
    //删除id错误
    public static final String NO_DELETE_ID = "请选择要删除的id";
    //街道已经存在
    public static final String REGION_STREET_EXIST = "街道已经存在";

    //角色名为空
    public static final String ROLE_NAME_IS_EMPTY = "角色名为空";
    //角色类型为空
    public static final String ROLE_TYPE_IS_EMPTY = "角色类型为空";
    //没有要更新的数据
    public static final String NO_DATA_TO_UPDATE = "没有要更新的数据";
    //没权限
    public static final String NO_AUTORITY = "没权限";
    //访问异常
    public static final String REQUEST_EXCEPTION = "访问异常";
    //异常类型
    public static final String EXCEPTION_TYPE = "异常类型";
    //异常描述
    public static final String EXCEPTION_MESSAGE = "异常描述";
    //异常原因
    //输入数据格式不正确
    public static final String EXCEPTION_FORMAT = "输入数据格式不正确";
    //请输入正确的用户id
    public static final String INPUT_RIGHT_USER_ID = "请输入正确的用户id";
    //没有数据
    public static final String NO_QUERY_RESULT = "没有数据";


    //权限名超过指定长度，需在{min} - {max}之间
    public static final String PERMISSION_NAME_LENGTH = "权限名超过指定长度，需在{%1$d}~{%2$d}之间";
    //授权超过指定长度，需在{min} - {max}之间
    public static final String PERMISSION_PERMS_LENGTH = "授权超过指定长度，需在{%1$d}~{%2$d}之间";
    //权限名不能为空
    public static final String PERMISSION_NAME_NOT_NULL = "权限名不能为空";
    //授权不能为空
    public static final String PERMISSION_PERMS_NOT_NULL = "授权不能为空";


    //请输入要查询的城市id
    public static final String CITY_ID_NOT_NULL = "请输入要查询的城市id";
    //请输入要查询的地区id
    public static final String AREA_ID_NOT_NULL = "请输入要查询的地区id";
    //地区id不能为空
    public static final String REGION_ID_NOT_NULL = "地区id不能为空";
    //街道id不能为空
    public static final String REGION_STREET_ID_NOT_NULL = "街道id不能为空";
    //地区名不能为空
    public static final String REGION_NAME_NOT_NULL = "地区名不能为空";
    //街道名不能为空
    public static final String REGION_STRETT_NAME_NOT_NULL = "街道名不能为空";
    //地区id超过指定长度，需在{min} - {max}之间
    public static final String REGION_COMMON_ID_LENGTH = "地区id超过指定长度，需在{%1$d}~{%2$d}之间";
    //街道id超过指定长度，需在{min} - {max}之间
    public static final String REGION_STREET_ID_LENGTH = "街道id超过指定长度，需在{%1$d}~{%2$d}之间";
    //地区名超过指定长度，需在{min} - {max}之间
    public static final String REGION_NAME_LENGTH = "地区名超过指定长度，需在{%1$d}~{%2$d}之间";
    //街道名超过指定长度，需在{min} - {max}之间
    public static final String REGION_STREET_NAME_LENGTH = "街道名超过指定长度，需在{%1$d}~{%2$d}之间";
    //地区上级id超过指定长度，需在{min} - {max}之间
    public static final String REGION_UP_REGION_ID_LENGTH = "地区上级id超过指定长度，需在{%1$d}~{%2$d}之间";
    //街道的区id超过指定长度，需在{min} - {max}之间
    public static final String REGION_STREET_UP_REGION_ID_LENGTH = "街道的区id超过指定长度，需在{%1$d}~{%2$d}之间";
    //地区描述不能为空
    public static final String REGION_DESC_NOT_NULL = "地区描述不能为空";
    //地区描述只能是 地级市、直辖市、市辖区、街道等
    public static final String REGION_DESC_RIGHT_FORMAT = "地区描述只能是 地级市、直辖市、市辖区、街道等";
    //请输入正确的邮编
    public static final String REGION_POSTAL_CODE_RIGHT_FORMAT = "请输入正确的邮编";


    //路灯编码不能为空
    public static final String LIGHT_INFO_CODE_NOT_NULL = "路灯编码不能为空";
    //路灯信息不能为空
    public static final String LIGHT_INFO_NOT_NULL = "路灯信息不能为空";
    //路灯信息状态不能为空
    public static final String LIGHT_INFO_STATUS_NOT_NULL = "路灯信息状态不能为空";
    //请选择路灯信息策略id
    public static final String LIGHT_INFO_STREET_ID_NOT_NULL = "请输入路灯所在街道id";
    //请输入路灯的经度
    public static final String LIGHT_INFO_LONGITUDE_NOT_NULL = "请输入路灯的经度";
    //请输入路灯的纬度
    public static final String LIGHT_INFO_LATITUDE_NOT_NULL = "请输入路灯的纬度";
    //指派分组成功
    public static final String LIGHT_INFO_DISPATCH_GROUP_SUCCESS = "指派分组成功";
    //指派分组失败
    public static final String LIGHT_INFO_DISPATCH_GROUP_FAILUER = "指派分组失败";
    //分组指派决策成功
    public static final String LIGHT_INFO_DISPATCH_STRATEGY_SUCCESS = "分组指派决策成功";
    //分组指派决策失败
    public static final String LIGHT_INFO_DISPATCH_STRATEGY_FAILUER = "分组指派决策失败";
    //指派决策成功
    public static final String LIGHT_INFO_DISPATCH_SUCCESS = "指派决策成功";
    //指派决策失败
    public static final String LIGHT_INFO_DISPATCH_FAILUER = "指派决策失败";


    //路灯开启状态不能为空
    public static final String LIGHT_STATUS_NOT_NULL = "路灯开启状态不能为空";
    //请输入路灯当前电压
    public static final String LIGHT_VOLTAGE_NOT_NULL = "请输入路灯当前电压";
    //请输入路灯当前电流
    public static final String LIGHT_CURRENT_NOT_NULL = "请输入路灯当前电流";
    //请输入路灯检测到的车流量
    public static final String LIGHT_TRAFFIC_FLOW_NOT_NULL = "请输入路灯检测到的车流量";
    //请输入路灯检测到的温度
    public static final String LIGHT_TEMPERATURE_NOT_NULL = "请输入路灯检测到的温度";
    //请输入路灯检测到的湿度
    public static final String LIGHT_HUMIDITY_NOT_NULL = "请输入路灯检测到的湿度";
    //请输入路灯当前亮度
    public static final String LIGHT_LIGHTNESS_NOT_NULL = "请输入路灯当前亮度";
    //请输入对应的路灯信息id
    public static final String LIGHT_INFO_ID_NOT_NULL = "请输入对应的路灯信息id";


    //请输入车流量多时候的亮度
    public static final String LIGHT_STRATEGY_TRAFFIC_LEVEL_NOT_NULL = "请输入车流量多时候的亮度";
    //请输入车流量少时候的亮度
    public static final String LIGHT_STRATEGY_SMOOTH_LEVEL_NOT_NULL = "请输入车流量少时候的亮度";
    //打开时间不能为空
    public static final String LIGHT_STRATEGY_OPEN_TIME_NOT_NULL = "打开时间不能为空";
    //关闭时间不能为空
    public static final String LIGHT_STRATEGY_CLOSE_TIME_NOT_NULL = "关闭时间不能为空";
    //策略类型不能为空
    public static final String LIGHT_STRATEGY_TYPE_NOT_NULL = "策略类型不能为空";


    //路灯记录所对应的路灯id不能为空
    public static final String LIGHT_RECORD_INFO_ID_NOT_NULL = "路灯记录所对应的路灯id不能为空";
    //请输入做了何种操作
    public static final String LIGHT_RECORD_OPERATION_NOT_NULL = "请输入做了何种操作";
    //请输入记录状态
    public static final String LIGHT_RECORD_STATUS_NOT_NULL = "请输入记录状态";


    //路灯分组id不能为空
    public static final String LIGHT_GROUP_ID_NOT_NULL = "路灯分组id不能为空";
    //请输入路灯分组名
    public static final String LIGHT_GROUP_NAME_NOT_NULL = "请输入路灯分组名";
    //请输入路灯分组的负责人id
    public static final String LIGHT_GROUP_RESPONSE_ID_NOT_NULL = "请输入路灯分组的负责人id";
}
