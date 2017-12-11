package com.viroyal.light.module.controller.user;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.entity.page.CustomPage;
import com.viroyal.light.module.entity.page.FrontPage;
import com.viroyal.light.module.entity.user.SysRole;
import com.viroyal.light.module.service.user.ISysRoleService;
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
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    ISysRoleService sysRoleService;

    //跳转到role管理页面
    @RequestMapping(value="/rolePage")
    public String role(String edit,Model modle) {
        //edit判断是否编辑成功
        modle.addAttribute("edit", edit);
        return "role/role";
    }

    //获取角色分页对象
    @RequestMapping(value="/getRoleListWithPager")
    @ResponseBody
    public String getRoleListWithPager(FrontPage<SysRole> page) {
        Wrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        String keyWords = page.getKeywords();
        if(keyWords!=null&&keyWords!="")wrapper.like("name",keyWords);
        Page<SysRole> pageList = sysRoleService.selectPage(page.getPagePlus(),wrapper);
        CustomPage<SysRole> customPage = new CustomPage<SysRole>(pageList);
        return JSON.toJSONString(customPage);
    }

    //跳轉到編輯頁面edit
    @RequestMapping(value="/editPage/{Id}")
    public String editPage(@PathVariable("Id") String Id, Model model) {
        if(Id.equals("add")){
        }else{
            SysRole role = sysRoleService.selectById(Id);
            model.addAttribute("role", role);
        }
        return "role/edit";
    }


    //刪除
    @RequestMapping(value="/delete")
    @ResponseBody
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
    @RequestMapping(value="/edit")
    public String edit(SysRole role,Model model) {
        if(sysRoleService.insertOrUpdate(role)){
            return "redirect:rolePage?edit=true";
        }else{
            return "redirect:rolePage?edit=false";
        }
    }
}
