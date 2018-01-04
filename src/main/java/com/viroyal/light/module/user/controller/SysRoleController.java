package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.user.entity.page.CustomPage;
import com.viroyal.light.module.user.entity.page.FrontPage;
import com.viroyal.light.module.user.entity.SysRole;
import com.viroyal.light.module.user.service.ISysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    ISysRoleService sysRoleService;

    //跳转到role管理页面
    @RequestMapping(value = "/rolePage")
    public String role(String edit, Model modle) {
        //edit判断是否编辑成功
        modle.addAttribute("edit", edit);
        return "role/role";
    }

    //获取角色分页对象
    @RequestMapping(value = "/getRoleListWithPager")
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

    @RequestMapping(value = "/list")
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
    @RequestMapping(value = "/editPage/{Id}")
    public String editPage(@PathVariable("Id") String Id, Model model) {
        SysRole role = sysRoleService.selectById(Id);
        model.addAttribute("role", role);
        return "role/edit";
    }


    //刪除
    @RequestMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("sys:role:delete")
    public String delete(@RequestParam(value = "ids[]") String[] ids) {
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
    @RequestMapping(value = "/forward/save")
    @RequiresPermissions("sys:role:save")
    public String save() {
        return "role/edit";
    }

    //增加和修改
    @RequestMapping(value = "/edit")
    @RequiresPermissions("sys:role:update")
    public String editRole(SysRole role) {
        sysRoleService.updateRole(role);
        return "redirect:rolePage?edit=true";
    }

    @RequestMapping(value = "/save")
    public String saveRole(SysRole role) {
        sysRoleService.saveRole(role);
        return "redirect:rolePage?edit=true";
    }
}
