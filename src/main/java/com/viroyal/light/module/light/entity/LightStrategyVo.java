package com.viroyal.light.module.light.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="路灯决策信息")
@Data
@TableName("sys_light_strategy")
public class LightStrategyVo {

    /**
     * 车流量少时的亮度
     */
    @ApiModelProperty("车流量少时的亮度(添加时候必填，更新不用)")
    @TableField("smooth_level")
    private Integer smoothLevel;

    /**
     * 车流量多时的亮度
     */
    @ApiModelProperty("车流量多时的亮度(添加时候必填，更新不用)")
    @TableField("traffic_level")
    private Integer trafficLevel;

    /**
     * 打开时间
     */
    @ApiModelProperty("打开时间(添加时候必填，更新不用)")
    @TableField("open_time")
    private String openTime;

    /**
     * 关闭时间
     */
    @ApiModelProperty("关闭时间(添加时候必填，更新不用)")
    @TableField("close_time")
    private String closeTime;

    /**
     * 冬季，夏季
     */
    @ApiModelProperty("季节(冬季，夏季等)(添加时候必填，更新不用)")
    private String type;

    /**
     * 策略开始时间
     */
    @ApiModelProperty("格式为yyyy-mm-dd(添加时候必填，更新不用)")
    @TableField("strategy_open_time")
    @JSONField(format="yyyy-MM-dd")
    private String strategyOpenTime;

    /**
     * 策略结束时间
     */
    @ApiModelProperty("格式为yyyy-mm-dd(添加时候必填，更新不用)")
    @TableField("strategy_close_time")
    @JSONField(format="yyyy-MM-dd")
    private String strategyCloseTime;

}
