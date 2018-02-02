package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.SysBasicAlarm;
import com.viroyal.light.module.light.entity.vo.SysBasicAlarmVo;
import com.viroyal.light.module.light.service.ISysBasicAlarmService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 报警基准表 前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Api("SysBasicAlarmController相关api")
@Controller
@RequestMapping(value = "/alarm")
public class SysBasicAlarmController {
	@Autowired
    ISysBasicAlarmService sysBasicAlarmService;

    @ApiOperation("移动端添加闹钟警报")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/alarmSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:alarm:save")
    public String alarmSave(SysBasicAlarmVo basicAlarmVo){
        return sysBasicAlarmService.save(basicAlarmVo);
    }

    @ApiOperation("移动端更新闹钟警报")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/alarmUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:alarm:update")
    public String alarmUpdate(SysBasicAlarmVo basicAlarmVo){
        return sysBasicAlarmService.update(basicAlarmVo);
    }

    @ApiOperation("移动端删除闹钟警报")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/alarmDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:alarm:delete")
    public String lightGroupDelete(@RequestParam(value = "ids[]") String[] ids){
        return sysBasicAlarmService.deleteBatch(ids);
    }


    @ApiOperation("通过条件查询路灯警报结果集")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "多少条"),
            @ApiImplicitParam(paramType = "query", name = "alarmReportTime",
                    dataType = "Long", value = "警阈值上报时间间隔"),
            @ApiImplicitParam(paramType = "query", name = "alarmVoltage",
                    dataType = "Int", value = "电压报警阈值(大于最大电压阈值，或者小于最小电压阈值)"),
            @ApiImplicitParam(paramType = "query", name = "alarmTemperature",
                    dataType = "Int", value = "温度报警阈值(大于最大温度阈值，或者小于最小温度阈值)"),
            @ApiImplicitParam(paramType = "query", name = "alarmCurrent",
                    dataType = "Int", value = "电流报警阈值(大于最大电流阈值，或者小于最小电流阈值)"),
            @ApiImplicitParam(paramType = "query", name = "alarmHumidity",
                    dataType = "Int", value = "湿度报警阈值(大于最大湿度阈值，或者小于最小湿度阈值)"),
            @ApiImplicitParam(paramType = "query", name = "alarmBrightness",
                    dataType = "String", value = "亮度报警阈值(大于最大亮度阈值，或者小于最小亮度阈值)"),
            @ApiImplicitParam(paramType = "query", name = "alarmTraffic",
                    dataType = "Int", value = "车流量报警阈值"),
            @ApiImplicitParam(paramType = "query", name = "alarmName",
                    dataType = "String", value = "报警阈值名称"),
            @ApiImplicitParam(paramType = "query", name = "sort",
                    dataType = "String", value = "排序方式(asc/desc)")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightGroup:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params) {
        return sysBasicAlarmService.queryWithCondition(params);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String processUnauthenticatedException(Exception ex) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ex.printStackTrace();
        if(ex instanceof UnauthorizedException){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NO_AUTORITY);
        } else if(ex instanceof BindException){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_EXCEPTION + " : " + BaseConstant.EXCEPTION_FORMAT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_EXCEPTION + " : " +
                    BaseConstant.EXCEPTION_TYPE + " = " +ex.getClass().getSimpleName() + ", " +
                    BaseConstant.EXCEPTION_MESSAGE + " = " + ex.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
