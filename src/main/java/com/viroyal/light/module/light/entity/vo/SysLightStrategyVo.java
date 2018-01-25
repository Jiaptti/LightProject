package com.viroyal.light.module.light.entity.vo;


import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="路灯决策信息")
@Data
@TableName("sys_light_strategy")
public class SysLightStrategyVo {

    @ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
    private Long id;
    /**
     * 车流量少时的亮度
     */
    @ApiModelProperty("车流量少时的亮度(添加时候必填，更新不用)")
    private Integer smoothLevel;

    /**
     * 车流量多时的亮度
     */
    @ApiModelProperty("车流量多时的亮度(添加时候必填，更新不用)")
    private Integer trafficLevel;

    /**
     * 打开时间
     */
    @ApiModelProperty("打开时间格式为hh:mm:ss(添加时候必填，更新不用)")
    private String openTime;

    /**
     * 关闭时间
     */
    @ApiModelProperty("关闭时间格式为hh:mm:ss(添加时候必填，更新不用)")
    private String closeTime;

    /**
     * 冬季，夏季
     */
    @ApiModelProperty("季节(冬季，夏季等)(添加时候必填，更新不用)")
    private String type;

    /**
     * 策略开始时间
     */
    @ApiModelProperty("开启日期格式为yyyy-mm-dd(添加时候必填，更新不用)")
    private String strategyOpenTime;

    /**
     * 策略结束时间
     */
    @ApiModelProperty("关闭日期格式为yyyy-mm-dd(添加时候必填，更新不用)")
    private String strategyCloseTime;
}
