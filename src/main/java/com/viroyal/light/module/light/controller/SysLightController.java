package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.SysLight;
import com.viroyal.light.module.light.entity.vo.SysLightVo;
import com.viroyal.light.module.light.service.ISysLightService;
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
 *  前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Api("SysLightController相关api")
@Controller
@RequestMapping("/light")
public class SysLightController {

    @Autowired
    ISysLightService sysLightService;

    @ApiOperation("移动端添加路灯数据")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/lightSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:light:save")
    public String saveLight(SysLightVo sysLight){
       return sysLightService.save(sysLight);
    }


    @ApiOperation("移动端更新路灯数据")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/lightUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:light:update")
    public String updateLight(SysLightVo sysLight){
       return sysLightService.update(sysLight);
    }


    @ApiOperation("移动端删除路灯数据")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/lightDelete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:light:delete")
    public String deleteLight(@RequestParam(value = "ids[]") String[] ids){
       return sysLightService.deleteBatch(ids);
    }

    @ApiOperation("移动端通过条件查询所有路灯数据(标记required的就是必填)pageId,pageSize为必填项,param参数接口填一个1就行，请求的时候不需要带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="pageId", dataType="Int", value="第几页"),
            @ApiImplicitParam(paramType="query", name="pageSize", dataType="Int", value="多少条"),
            @ApiImplicitParam(paramType="query", name="lightId", dataType="Int", value="路灯数据id"),
            @ApiImplicitParam(paramType="query", name="status", dataType="Int", value="是否打开(1/0表示开/关)"),
            @ApiImplicitParam(paramType="query", name="voltageGt", dataType="Int", value="电压(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="voltageLt", dataType="Int", value="电压(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="currentGt", dataType="Int", value="电流(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="currentLt", dataType="Int", value="电流(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="temperatureGt", dataType="Int", value="温度(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="temperatureLt", dataType="Int", value="温度(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="humidityGt", dataType="Int", value="湿度(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="humidityLt", dataType="Int", value="湿度(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="startTime", dataType="Date", value="日期(格式为yyyy-MM-dd)(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="endTime", dataType="Date", value="日期(格式为yyyy-MM-dd)(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="trafficFlow", dataType="Int", value="车流量"),
            @ApiImplicitParam(paramType="query", name="lightnessGt", dataType="Int", value="亮度(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="lightnessLt", dataType="Int", value="亮度(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="infoId", dataType="Int", value="路灯信息id(sys_light_info表的id)"),
            @ApiImplicitParam(paramType="query", name="sort", dataType="String" ,value="排序方式(asc升序，desc降序)")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:light:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        return sysLightService.queryWithCondition(params);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String processUnauthenticatedException(Exception ex) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
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
