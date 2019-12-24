package cn.itsource.aisell.controller;

import cn.itsource.aisell.common.JsonResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.common.UserContent;
import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.query.PurchasebillQuery;
import cn.itsource.aisell.query.PurchasebillQuery;
import cn.itsource.aisell.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/purchasebill")
public class PurchasebillController extends BaseController {

    @Autowired
    private IPurchasebillService purchasebillService;

    @RequestMapping("/index")
    public String index() {
        return "purchasebill/index";
    }


    /**
     * @param query data-options#field中的字段会与query进行匹配
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage list(PurchasebillQuery query) {
        return new UIPage(purchasebillService.queryPage(query));
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Purchasebill> list() {
        return purchasebillService.findAll();
    }

    /**
     * 新增功能
     *
     * @param purchasebill
     * @return
     * @link (" / purchasebill / save ")
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Purchasebill purchasebill) {
        purchasebill.setInputUser(UserContent.getEmp());
        return saveOrUpdate(purchasebill);
    }

    @ModelAttribute("editPurchasebill")
    public Purchasebill beforeUpdate(Long id, String _cmd) {
        /*
         * 根据请求参数是否携带id,以及js传过来的url
         * 是否携带_cmd来确定是否应该查询数据库指定单行数据
         */
        if (id != null && "update".equals(_cmd)) {
            // 修改时才去数据库获取
            Purchasebill purchasebill = purchasebillService.findOne(id);
            return purchasebill;
        }
        return null;
    }

    /**
     * 修改功能
     *
     * @param purchasebill
     * @return
     * @link (" / purchasebill / update ? _cmd = update ")
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPurchasebill") Purchasebill purchasebill) {
        return saveOrUpdate(purchasebill);
    }

    public JsonResult saveOrUpdate(Purchasebill purchasebill) {
        try {
            purchasebillService.save(purchasebill);
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
            purchasebillService.delete(id);
            // success默认是true
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

}
