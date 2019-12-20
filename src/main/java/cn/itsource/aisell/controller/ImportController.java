package cn.itsource.aisell.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import cn.itsource.aisell.common.EmployeeExcelVerifyHandler;
import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.repository.DepartmentRepository;
import cn.itsource.aisell.service.IDepartmentService;
import cn.itsource.aisell.service.IEmployeeService;
import com.sun.deploy.net.HttpResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author myllxy
 * @create 2019-12-20 18:26
 */
@Controller
@RequestMapping("import")
public class ImportController {
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    EmployeeExcelVerifyHandler employeeExcelVerifyHandler;

    @RequestMapping("index")
    public String index() {
        return "import";
    }

    @RequestMapping("employeeXlsx")
    public String employeeXlsx(MultipartFile empFile) throws Exception {
        ImportParams params = new ImportParams();
        List<Employee> list = ExcelImportUtil.importExcel(
                empFile.getInputStream(), Employee.class, params);
        list.forEach(e -> {
            e.setPassword("");
            if (e.getDepartment() != null) {
                e.setDepartment(departmentService.findDepartmentByName(e.getUsername()));
                employeeService.save(e);
            }
        });
        return "import";
    }

    @RequestMapping("employeeXlsx_v")
    public String employeeXlsx_v(MultipartFile empFile, HttpServletResponse response) throws Exception {
        ImportParams params = new ImportParams();
        params.setNeedVerfiy(true);
        params.setHeadRows(1);
        params.setVerifyHandler(employeeExcelVerifyHandler);
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(
                empFile.getInputStream(),
                Employee.class, params);
        result.getList().forEach(e -> {
            //根据部门名称拿到它的部门，再放到对应的员工中
            Department dept = departmentService.findDepartmentByName(e.getDepartment().getName());
            e.setDepartment(dept);
            //给一个默认密码
            e.setPassword("");
            System.out.println("正确的：" + e);
            employeeService.save(e);
        });
        result.getFailList().forEach(e -> {
            System.out.println("错误的：" + e);
        });
        if (result.isVerfiyFail()) {
            //如果验证失败，代码到这里面来
            //失败的文件已经准备好了
            Workbook failWorkbook = result.getFailWorkbook();
            //把这个文件导出
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx");
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream ouputStream = response.getOutputStream();
            failWorkbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
        return "import";
    }
}
