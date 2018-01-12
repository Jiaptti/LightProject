package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.CustomPage;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.user.entity.SysPermission;
import com.viroyal.light.module.user.service.ISysPermissionService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Api("SysPermissionController相关api")
@Controller
@RequestMapping(value = "/permission")
public class SysPermissionController {

    @Autowired
    ISysPermissionService sysPermissionService;

    // 跳转到用户管理页面
    @RequestMapping(value = "/permissionPage", method = RequestMethod.GET)
    public String permissionPage(String edit, Model model){
        // edit判断是否编辑成功
        model.addAttribute("edit", edit);
        return "permission/permission";
    }

    @RequestMapping(value = "/getPermissionListWithPager", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:permission:list")
    public String getPermissionListWithPager(FrontPage<SysPermission> page){
        page.setPageSize(10);
        Wrapper<SysPermission> wrapper = new EntityWrapper<SysPermission>();
        String keyWords = page.getKeywords();
        if(keyWords != null &&!keyWords.equals("")){
            wrapper.like("name", keyWords);
        }
        Page<SysPermission> pageList = sysPermissionService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysPermission> customPage = new CustomPage<SysPermission>(pageList);
        return JSON.toJSONString(customPage);
    }

    @ApiOperation("移动端获得权限列表")
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/permissionList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:permission:list")
    public String permissionList(){
        Map<String, Object> resultMap = new HashMap<>();
        List<SysPermission> permissions = sysPermissionService.queryAll();
        if (permissions.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, permissions);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @RequestMapping(value = "/editPage/{Id}", method = RequestMethod.GET)
    public String editPage(@PathVariable("Id") String id, Model model){
        if(!id.equals("add")){
            SysPermission permission = sysPermissionService.selectById(id);
            model.addAttribute("permission", permission);
        }
        return "permission/edit";
    }

    // 增加和修改
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(SysPermission permission, Model model) {
        if (sysPermissionService.insertOrUpdate(permission)) {
            return "forward:permissionPage?edit=true";
        } else {
            return "forward:permissionPage?edit=false";
        }
    }

    // 刪除
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam(value = "ids[]") String[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            sysPermissionService.deleteBatchIds(Arrays.asList(ids));
            resultMap.put("flag", true);
            resultMap.put("msg", "刪除成功！");
        } catch (Exception e) {
            resultMap.put("flag", false);
            resultMap.put("msg", e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端添加权限")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/permissionSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:permission:save")
    public String permissionSave(SysPermission permission){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysPermissionService.savePermission(permission);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端删除权限")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/permissionDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:permission:delete")
    public String permissionDelete(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysPermissionService.deleteBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端更新权限")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/permissionUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:permission:update")
    public String permissionUpdate(SysPermission permission){
        Map<String, Object> resultMap = new HashMap<>();
        if(permission.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NO_PERMISSION_ID);
        } else {
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysPermissionService.update(permission);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE);
            }
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端通过条件查询权限,param填个1就行，移动端发请求，不用带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="pageId",
                    dataType="Int", required=true,value="第几页"),
            @ApiImplicitParam(paramType="query", name="pageSize",
                    dataType="Int", required=true,value="多少条"),
            @ApiImplicitParam(paramType="query", name="userId",
                    dataType="Int", value="用户id"),
            @ApiImplicitParam(paramType="query", name="areaId",
                    dataType="Int", value="区id"),
            @ApiImplicitParam(paramType="query", name="userId",
                    dataType="Int", value="用户id"),
            @ApiImplicitParam(paramType="query", name="sort",
                    dataType="String", value="排序方式"),
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:permission:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        DatePage<SysPermission> datePage = sysPermissionService.queryWithCondition(params);
        return JSON.toJSONString(datePage);
    }

}
