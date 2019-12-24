package cn.itsource.aisell.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.itsource.aisell.common.JsonResult;
import cn.itsource.aisell.common.UIPage;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.query.EmployeeQuery;
import cn.itsource.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/index")
    public String index() {
        return "employee/index";
    }

    /**
     * @param query data-options#field中的字段会与query进行匹配
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage list(EmployeeQuery query) {
        return new UIPage(employeeService.queryPage(query));
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Employee> list() {
        return employeeService.findAll();
    }


    /**
     * 新增功能
     *
     * @param employee
     * @return
     * @link (" / employee / save ")
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Employee employee) {
        return saveOrUpdate(employee);
    }
    @RequestMapping("/getSuppers")
    @ResponseBody
    public List<Employee> findSupperByName() {
        return employeeService.findSupperByName();
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
    @ModelAttribute("editEmployee")
    public Employee beforeUpdate(Long id, String _cmd) {
        /*
         * 根据请求参数是否携带id,以及js传过来的url
         * 是否携带_cmd来确定是否应该查询数据库指定单行数据
         */
        if (id != null && "update".equals(_cmd)) {
            // 修改时才去数据库获取
            Employee employee = employeeService.findOne(id);
            employee.setDepartment(null);
            return employee;
        }
        return null;
    }

    /**
     * 修改功能
     *
     * @param employee
     * @return
     * @link (" / employee / update ? _cmd = update ")
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editEmployee") Employee employee) {
        return saveOrUpdate(employee);
    }

    public JsonResult saveOrUpdate(Employee employee) {
        try {
            employeeService.save(employee);
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
            employeeService.delete(id);
            // success默认是true
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    @RequestMapping("/export")
    public String export(EmployeeQuery query, ModelMap map, HttpServletRequest request) {
        List<Employee> list = employeeService.findAll();
        //搞定路径问题
        list.forEach(e -> {
            String realPath = request.getServletContext().getRealPath("");
            e.setHeadImage(realPath + e.getHeadImage());
        });
        ExportParams params = new ExportParams("员工数据", "测试", ExcelType.XSSF);
        params.setFreezeCol(2); // 设置冻结列
        map.put(NormalExcelConstants.DATA_LIST, list); // 数据集合
        map.put(NormalExcelConstants.CLASS, Employee.class);//导出实体
        map.put(NormalExcelConstants.PARAMS, params);//参数
        map.put(NormalExcelConstants.FILE_NAME, "员工信息");//文件名称
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
    }

}
