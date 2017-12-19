package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.user.entity.page.CustomPage;
import com.viroyal.light.module.user.entity.page.FrontPage;
import com.viroyal.light.module.user.entity.SysPermission;
import com.viroyal.light.module.user.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Controller
@RequestMapping(value = "/permission")
public class SysPermissionController {

    @Autowired
    ISysPermissionService sysPermissionService;

    // 跳转到用户管理页面
    @RequestMapping(value = "/permissionPage")
    public String permissionPage(String edit, Model model){
        // edit判断是否编辑成功
        model.addAttribute("edit", edit);
        return "permission/permission";
    }

    @RequestMapping(value = "/getPermissionListWithPager")
    @ResponseBody
    public String getPermissionListWithPager(FrontPage<SysPermission> page){
        Wrapper<SysPermission> wrapper = new EntityWrapper<SysPermission>();
        String keyWords = page.getKeywords();
        if(keyWords != null &&!keyWords.equals("")){
            wrapper.like("name", keyWords);
        }
        Page<SysPermission> pageList = sysPermissionService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysPermission> customPage = new CustomPage<SysPermission>(pageList);
        return JSON.toJSONString(customPage);
    }

    @RequestMapping(value = "/editPage/{Id}")
    public String editPage(@PathVariable("Id") String id, Model model){
        if(!id.equals("add")){
            SysPermission permission = sysPermissionService.selectById(id);
            model.addAttribute("permission", permission);
        }
        return "permission/edit";
    }

    // 增加和修改
    @RequestMapping(value = "/edit")
    public String edit(SysPermission permission, Model model) {
        if (sysPermissionService.insertOrUpdate(permission)) {
            return "forward:permissionPage?edit=true";
        } else {
            return "forward:permissionPage?edit=false";
        }
    }

    // 刪除
    @RequestMapping(value = "/delete")
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
}
