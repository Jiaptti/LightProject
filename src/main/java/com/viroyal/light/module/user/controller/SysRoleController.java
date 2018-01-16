package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.CustomPage;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.user.entity.SysRole;
import com.viroyal.light.module.user.service.ISysRoleService;
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
 * 前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Api("SysRoleController相关api")
@Controller
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    ISysRoleService sysRoleService;

    //跳转到role管理页面
    @RequestMapping(value = "/rolePage", method = RequestMethod.GET)
    public String role(String edit, Model modle) {
        //edit判断是否编辑成功
        modle.addAttribute("edit", edit);
        return "role/role";
    }

    //获取角色分页对象
    @RequestMapping(value = "/getRoleListWithPager", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:role:list")
    public String getRoleListWithPager(FrontPage<SysRole> page) {
        page.setPageSize(10);
        Wrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        String keyWords = page.getKeywords();
        if (keyWords != null && keyWords != "") wrapper.like("name", keyWords);
        Page<SysRole> pageList = sysRoleService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysRole> customPage = new CustomPage<SysRole>(pageList);
        return JSON.toJSONString(customPage);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:role:list")
    public String getRoleList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRole> roleList = sysRoleService.selectByMap(new HashMap<String, Object>());
        if(roleList.size() > 0){
            resultMap.put("status", "200");
            resultMap.put("roleList", roleList);
            resultMap.put("message", "success");
        }
        return JSON.toJSONString(resultMap);
    }



    //跳轉到編輯頁面edit
    @RequestMapping(value = "/editPage/{Id}", method = RequestMethod.GET)
    public String editPage(@PathVariable("Id") String Id, Model model) {
        SysRole role = sysRoleService.selectById(Id);
        model.addAttribute("role", role);
        return "role/edit";
    }


    //刪除
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:role:delete")
    public String deleteRole(@RequestParam(value = "ids[]") String[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            sysRoleService.deleteBatchIds(Arrays.asList(ids));
            resultMap.put("flag", true);
            resultMap.put("msg", "刪除成功！");
        } catch (Exception e) {
            resultMap.put("flag", false);
            resultMap.put("msg", e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    //增加和修改
    @RequestMapping(value = "/forward/save", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:save")
    public String save() {
        return "role/edit";
    }

    //增加和修改
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:update")
    public String editRole(SysRole role) {
        sysRoleService.update(role);
        return "redirect:rolePage?edit=true";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveRole(SysRole role) {
        sysRoleService.save(role);
        return "redirect:rolePage?edit=true";
    }

    @ApiOperation("移动端添加角色")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:save")
    public String save(SysRole role){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysRoleService.save(role);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端更新角色")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:update")
    public String update(SysRole role){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysRoleService.update(role);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端删除角色")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:delete")
    public String delete(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysRoleService.deleteBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端通过条件查询角色,param填个1就行，移动端发请求，不用带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="pageId",
                    dataType="Int", required=true,value="第几页"),
            @ApiImplicitParam(paramType="query", name="pageSize",
                    dataType="Int", required=true,value="多少条"),
            @ApiImplicitParam(paramType="query", name="userId",
                    dataType="Int", value="用户id"),
            @ApiImplicitParam(paramType="query", name="userName",
                    dataType="String", value="用户姓名"),
            @ApiImplicitParam(paramType="query", name="userAccount",
                    dataType="String", value="用户账号"),
            @ApiImplicitParam(paramType="query", name="roleName",
                    dataType="String", value="角色名(模糊查询)"),
            @ApiImplicitParam(paramType="query", name="roleType",
                    dataType="String", value="角色类型(模糊查询)"),
            @ApiImplicitParam(paramType="query", name="sort",
                    dataType="String", value="排序方式"),
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        DatePage<SysRole> datePage = sysRoleService.queryWithCondition(params);
        return JSON.toJSONString(datePage);
    }
}
