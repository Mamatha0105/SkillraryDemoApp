package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class AddCategoryTest extends  BaseClass{

	@Test
	public void addCategoryTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertEquals(category.getPageHeader(),("Category"));
		category.clickNewButton();
		
		
		excel.excelInit(IConstantPath.EXCEL_PATH, "Sheet1");
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
		Map<String, String >map = excel.readFromExcel("Add Category");
		addCategory.setName(map.get("Name"));
		addCategory.clickSave();
		
		soft.assertEquals(category.getSuccessMessage(),"Success!");
		category.deleteCategory(web, map.get("Name"));
		//soft.assertEquals(category.getSuccessMessage(), "Success!");
		if(category.getSuccessMessage().equals("Success!"))
			excel.updateTestStatus("Add Category", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("Add Category", "Fail", IConstantPath.EXCEL_PATH);
		//category.deleteCategory(web, "Name");
		
		soft.assertAll();
	}
}
