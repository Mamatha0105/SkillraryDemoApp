package testScripts;

//This TestScript got Failed because the page is not working

import java.util.Map;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class AddUserTest extends BaseClass {
	@Test
	public void addUserTest() {
		SoftAssert soft = new SoftAssert();
		home.clickUserTab();
		soft.assertTrue(users.getPageHeader().contains("Users"));
		users.clickNewButton();
		
		excel.excelInit(IConstantPath.EXCEL_PATH, "Sheet1");
		soft.assertEquals(addUser.getPageHeader(), "Add New User");
		Map<String , String >map = excel.readFromExcel("Add User");
	//	soft.assertEquals(addUser.getPageHeader(), "Add New User");
		
		
		addUser.setEmail(map.get("Email"));
		addUser.setPassword(map.get("Password"));
		addUser.setFirstname(map.get("Firstname"));
		addUser.setLastname(map.get("Lastname"));
		addUser.setAddress(map.get("Address"));
		addUser.setContactInfo(map.get("Contact Info"));
		addUser.uploadPhoto(map.get("Photo"));
		addUser.clickSave();
		try {
		soft.assertEquals(users.getSuccessMessage(), "Success!");
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		soft.assertAll();
	}
	


}


