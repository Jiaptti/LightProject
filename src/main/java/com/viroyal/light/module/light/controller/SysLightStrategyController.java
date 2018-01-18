package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.SysLightStrategy;
import com.viroyal.light.module.light.service.ISysLightStrategyService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Api("SysLightStrategyController相关api")
@Controller
@RequestMapping("/lightStrategy")
public class SysLightStrategyController {

    @Autowired
    ISysLightStrategyService sysLightStrategyService;

    @ApiOperation("移动端添加路灯策略")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/strategySave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:strategy:save")
    public String saveStrategy(SysLightStrategy lightStrategy){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysLightStrategyService.save(lightStrategy);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端更新路灯策略")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/strategyUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:strategy:update")
    public String updateStrategy(SysLightStrategy lightStrategy){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysLightStrategyService.update(lightStrategy);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端删除路灯策略")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/strategyDelete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:strategy:delete")
    public String deleteStrategy(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysLightStrategyService.deleteBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端通过条件查询所有路灯策略(标记required的就是必填)pageId,pageSize为必填项,param参数接口填一个1就行，请求的时候不需要带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="pageId", dataType="Int", value="第几页"),
            @ApiImplicitParam(paramType="query", name="pageSize", dataType="Int", value="多少条"),
            @ApiImplicitParam(paramType="query", name="smoothLevel", dataType="Int", value="车流量少时的亮度"),
            @ApiImplicitParam(paramType="query", name="trafficLevel", dataType="Int", value="车流量多时的亮度"),
            @ApiImplicitParam(paramType="query", name="openTime", dataType="String", value="打开时间(格式为hh:mm:ss)"),
            @ApiImplicitParam(paramType="query", name="closeTime", dataType="String", value="打开时间(格式为hh:mm:ss)"),
            @ApiImplicitParam(paramType="query", name="type", dataType="String", value="季节(冬季，夏季等)"),
            @ApiImplicitParam(paramType="query", name="sort", dataType="String" ,value="排序方式(asc升序，desc降序)")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:strategy:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        if((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))){
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }
        DatePage<SysLightStrategy> datePage = sysLightStrategyService.queryWithCondition(params);
        return JSON.toJSONString(datePage);
    }
}
