package cn.itsource.aisell.controller;

import cn.itsource.aisell.common.JsonResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Supplier;
import cn.itsource.aisell.query.SupplierQuery;
import cn.itsource.aisell.query.SupplierQuery;
import cn.itsource.aisell.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

    @Autowired
    private ISupplierService supplierService;

    @RequestMapping("/index")
    public String index() {
        return "supplier/index";
    }

    /**
     * 加上@ModelAttribute，你每次发送请求都会先执行它对应的方法
     * 此方法用于解决数据丢失问题
     * 场景:用户在修改单行数据时由于没有某字段(如password)不需要修改
     * 因此没有传递,但此时springmvc会将你携带的参数与实体类一
     * 一对应传值,但是你没有传递,所以会赋默认的值(相当于误改了数据)
     * 解决方法:
     * 1.在html代码设置password等字段为hidden,仍然会传值过去
     * 2.在jpa实体类处设置字段为不可修改,那问题来了,我添加的时候是需要修改密码的？
     * 3.使用@ModelAttribute过滤,让过滤后的方法都去数据库查询单行数据,这单行数据
     * 是有password的,如果不修改即使用默认值,因此就解决了问题
     */
    @ModelAttribute("editSupplier")
    public Supplier beforeUpdate(Long id, String _cmd) {
        /*
         * 根据请求参数是否携带id,以及js传过来的url
         * 是否携带_cmd来确定是否应该查询数据库指定单行数据
         */
        if (id != null && "update".equals(_cmd)) {
            // 修改时才去数据库获取
            return supplierService.findOne(id);
        }
        return null;
    }

    /**
     * @param query data-options#field中的字段会与query进行匹配
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage list(SupplierQuery query) {
        return new UIPage(supplierService.queryPage(query));
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Supplier> list() {
        System.out.println(supplierService.findAll());
        return supplierService.findAll();
    }


    /**
     * 新增功能
     *
     * @param supplier
     * @return
     * @link (" / supplier / save ")
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Supplier supplier) {
        return saveOrUpdate(supplier);
    }

    /**
     * 修改功能
     *
     * @param supplier
     * @return
     * @link (" / supplier / update ? _cmd = update ")
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editSupplier") Supplier supplier) {
        return saveOrUpdate(supplier);
    }

    public JsonResult saveOrUpdate(Supplier supplier) {
        try {
            supplierService.save(supplier);
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
            supplierService.delete(id);
            // success默认是true
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

}
