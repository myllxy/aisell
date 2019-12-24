package cn.itsource.aisell.controller;

import cn.itsource.aisell.common.JsonResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.query.PurchasebillitemQuery;
import cn.itsource.aisell.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/purchasebillitem")
public class PurchasebillitemController extends BaseController {

    @Autowired
    private IPurchasebillitemService purchasebillitemService;

    @RequestMapping("/index")
    public String index() {
        return "purchasebillitem/index";
    }


    /**
     * @param query data-options#field中的字段会与query进行匹配
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage list(PurchasebillitemQuery query) {
        return new UIPage(purchasebillitemService.queryPage(query));
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Purchasebillitem> list() {
        return purchasebillitemService.findAll();
    }


    /**
     * 新增功能
     *
     * @param purchasebillitem
     * @return
     * @link (" / purchasebillitem / save ")
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Purchasebillitem purchasebillitem) {
        return saveOrUpdate(purchasebillitem);
    }

    @ModelAttribute("editPurchasebillitem")
    public Purchasebillitem beforeUpdate(Long id, String _cmd) {
        /*
         * 根据请求参数是否携带id,以及js传过来的url
         * 是否携带_cmd来确定是否应该查询数据库指定单行数据
         */
        if (id != null && "update".equals(_cmd)) {
            // 修改时才去数据库获取
            Purchasebillitem purchasebillitem = purchasebillitemService.findOne(id);
            purchasebillitem.setProduct(null);
            purchasebillitem.setPurchasebill(null);
            return purchasebillitem;
        }
        return null;
    }

    /**
     * 修改功能
     *
     * @param purchasebillitem
     * @return
     * @link (" / purchasebillitem / update ? _cmd = update ")
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPurchasebillitem") Purchasebillitem purchasebillitem) {
        return saveOrUpdate(purchasebillitem);
    }

    public JsonResult saveOrUpdate(Purchasebillitem purchasebillitem) {
        try {
            purchasebillitemService.save(purchasebillitem);
            // success默认是true
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    /**
     * 删除后会返回一个json :{success:true/false,msg:xxx}
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            purchasebillitemService.delete(id);
            // success默认是true
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

}
