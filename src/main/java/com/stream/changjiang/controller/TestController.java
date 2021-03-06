package com.stream.changjiang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.study.poi.ExcelUtil;
import com.example.study.poi.TestAccount;
import java.util.List;
import java.util.Map;

@Api("人员技能")
@RestController
@RequestMapping("/api/empSkill")
public class TestController {

    public String testMvc(String name){
        System.out.println(name + "========================");
        return name;

    }


   @PostMapping("import")
   public void importExcel(@ApiParam(value = "要上传的文件",required = true) @RequestParam("file") MultipartFile file) throws Exception {
       ExcelUtil excelUtil = new ExcelUtil();
       Workbook workbook = excelUtil.createWorkbook(file);
       List<Sheet> sheets = excelUtil.getSheets(workbook);
       Sheet sheet = sheets.get(1);
       Map<Integer, List<String>> integerListMap = excelUtil.readByColumn(sheet);
       List<TestAccount> testAccounts = excelUtil.buildExcelDTO(integerListMap, TestAccount.class);
       System.out.print(testAccounts);

   }
}
