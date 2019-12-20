package cn.itsource.aisell.controller;

import cn.itsource.aisell.common.JsonResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Menu;
import cn.itsource.aisell.query.MenuQuery;
import cn.itsource.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/index")
    public String index() {
        return "menu/index";
    }

    /**
     * Page对象 =》 Map结构对象
     *
     * @param query
     * @return
     */
    //分页查询
    @RequestMapping("/page")
    @ResponseBody
    public UIPage page(MenuQuery query) {
        return new UIPage(menuService.queryPage(query));
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Menu> list() {
        return menuService.findAll();
    }


    /**
     * 加上@ModelAttribute，你每次发送请求都会先执行它对应的方法
     */
    @ModelAttribute("editMenu")
    public Menu beforeUpdate(Long id, String _cmd) {
        if (id != null && "update".equals(_cmd)) {
            //修改时才去数据库获取
            return menuService.findOne(id);
        }
        return null;
    }

    //保存功能
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Menu menu) {
        return saveOrUpdate(menu);
    }

    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editMenu") Menu menu) {
        return saveOrUpdate(menu);
    }

    //添加或者修改
    private JsonResult saveOrUpdate(Menu menu) {
        try {
            menuService.save(menu);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }


    //删除方法

    /**
     * 删除后会返回一个json :{success:true/false,msg:xxx}
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            menuService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    @RequestMapping("/findParentMenus")
    @ResponseBody
    public List<Menu> findParentMenus() {
        System.out.println("--------------" + menuService.findParentMenus());
        return menuService.findParentMenus();
    }

}
