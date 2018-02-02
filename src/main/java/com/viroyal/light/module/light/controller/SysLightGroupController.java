package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.vo.SysLightGroupVo;
import com.viroyal.light.module.light.service.ISysLightGroupService;
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
@Api("SysLightGroupController相关api")
@Controller
@RequestMapping(value = "/lightGroup")
public class SysLightGroupController {
    @Autowired
    ISysLightGroupService sysLightGroupService;

    @ApiOperation("移动端添加路灯分组")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/lightGroupSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightGroup:save")
    public String lightGroupSave(SysLightGroupVo lightGroupVo){
        return sysLightGroupService.save(lightGroupVo);
    }

    @ApiOperation("移动端更新路灯分组")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/lightGroupUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightGroup:update")
    public String lightGroupUpdate(SysLightGroupVo lightGroupVo){
        return sysLightGroupService.update(lightGroupVo);
    }

    @ApiOperation("移动端删除路灯记录")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/lightGroupDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightGroup:delete")
    public String lightGroupDelete(@RequestParam(value = "ids[]") String[] ids){
        return sysLightGroupService.deleteBatch(ids);
    }

    @ApiOperation("通过条件查询所有路灯记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "多少条"),
            @ApiImplicitParam(paramType = "query", name = "groupId",
                    dataType = "Int", value = "路灯分组id(对应的不是主键，是group_id字段)"),
            @ApiImplicitParam(paramType = "query", name = "groupName",
                    dataType = "String", value = "路灯分组名"),
            @ApiImplicitParam(paramType = "query", name = "groupStrategyId",
                    dataType = "Int", value = "路灯分组决策id"),
            @ApiImplicitParam(paramType = "query", name = "createUserId",
                    dataType = "Int", value = "创建路灯分组的用户id"),
            @ApiImplicitParam(paramType = "query", name = "responsibleId",
                    dataType = "Int", value = "路灯分组负责人的用户id"),
            @ApiImplicitParam(paramType = "query", name = "createStart",
                    dataType = "String", value = "创建分组的时间的起始范围(格式为yyyy-mm-dd hh:mm:ss)"),
            @ApiImplicitParam(paramType = "query", name = "createEnd",
                    dataType = "Int", value = "创建分组的时间的结束范围(格式为yyyy-mm-dd hh:mm:ss)"),
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
        return sysLightGroupService.queryWithCondition(params);
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

    @ApiOperation("移动端给路灯分组添加策略")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "groupId", required = true,
                    dataType = "Int", value = "路灯分组id(多个id用逗号隔开)"),
            @ApiImplicitParam(paramType = "query", name = "strategyId", required = true,
                    dataType = "Int", value = "要分配的策略id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="指派成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="指派失败")
    })
    @RequestMapping(value = "/dispatchStrategy", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightGroup:update")
    public String dispatchStrategy (@RequestParam(value = "groupId") String groupId, @RequestParam(value = "strategyId") String strategyId){
        return sysLightGroupService.dispatchStrategy(groupId, strategyId);
    }
}
