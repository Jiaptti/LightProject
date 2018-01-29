package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.SysLightRecord;
import com.viroyal.light.module.light.entity.vo.SysLightRecordVo;
import com.viroyal.light.module.light.service.ISysLightRecordService;
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
@Api("SysLightInfoController相关api")
@Controller
@RequestMapping(value = "/lightRecord")
public class SysLightRecordController {

    @Autowired
    ISysLightRecordService sysLightRecordService;

    @ApiOperation("移动端添加记录")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/lightRecordSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightRecord:save")
    public String lightRecordSave(SysLightRecordVo lightRecordVo){
        return sysLightRecordService.save(lightRecordVo);
    }

    @ApiOperation("移动端更新记录")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/lightRecordUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightRecord:update")
    public String lightRecordUpdate(SysLightRecordVo lightRecordVo){
        return sysLightRecordService.update(lightRecordVo);
    }

    @ApiOperation("移动端删除路灯记录")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/lightRecordDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightRecord:delete")
    public String lightRecordDelete(@RequestParam(value = "ids[]") String[] ids){
        return sysLightRecordService.deleteBatch(ids);
    }

    @ApiOperation("通过条件查询所有路灯记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "多少条"),
            @ApiImplicitParam(paramType = "query", name = "infoId",
                    dataType = "Int", value = "路灯id(sys_light_info表的id)"),
            @ApiImplicitParam(paramType = "query", name = "userId",
                    dataType = "Int", value = "用户id(sys_user表的id)"),
            @ApiImplicitParam(paramType = "query", name = "startTime",
                    dataType = "String", value = "记录起始时间(格式为yyyy-mm-dd hh:mm:ss)"),
            @ApiImplicitParam(paramType = "query", name = "endTime",
                    dataType = "Int", value = "记录结束时间(格式为yyyy-mm-dd hh:mm:ss)"),
            @ApiImplicitParam(paramType = "query", name = "sort",
                    dataType = "String", value = "排序方式")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightRecord:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params) {
        return sysLightRecordService.queryWithCondition(params);
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
