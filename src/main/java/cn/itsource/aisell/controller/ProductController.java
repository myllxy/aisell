package cn.itsource.aisell.controller;

import cn.itsource.aisell.common.JsonResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.query.ProductQuery;
import cn.itsource.aisell.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/index")
    public String index() {
        return "product/index";
    }


    /**
     * @param query data-options#field中的字段会与query进行匹配
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage list(ProductQuery query) {
        return new UIPage(productService.queryPage(query));
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Product> list() {
        return productService.findAll();
    }


    /**
     * 新增功能
     *
     * @param product
     * @return
     * @link (" / product / save ")
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Product product) {
        return saveOrUpdate(product);
    }

    @ModelAttribute("editProduct")
    public Product beforeUpdate(Long id, String _cmd) {
        /*
         * 根据请求参数是否携带id,以及js传过来的url
         * 是否携带_cmd来确定是否应该查询数据库指定单行数据
         */
        if (id != null && "update".equals(_cmd)) {
            // 修改时才去数据库获取
            Product product = productService.findOne(id);
            product.setProducttype(null);
            product.setSdd1(null);
            product.setSdd2(null);
            return product;
        }
        return null;
    }

    /**
     * 修改功能
     *
     * @param product
     * @return
     * @link (" / product / update ? _cmd = update ")
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editProduct") Product product) {
        return saveOrUpdate(product);
    }

    public JsonResult saveOrUpdate(Product product) {
        try {
            productService.save(product);
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
            productService.delete(id);
            // success默认是true
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

}
