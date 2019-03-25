package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.PageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);

		lp.setUserName(user);
		log4j.info("provided user name");
		lp.setPassword(pwd);
		log4j.info("provided password");
		lp.loginbtn();
		log4j.info("Clicked on sumbit");

		Thread.sleep(3000);

		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			Thread.sleep(3000);
			log4j.info("Login test failed");
		}
		else
		{
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept(); // closes the logout alert
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			log4j.info("Login test passed");

		}
	}


	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}

	}



	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);


		String logindata[][]=new String[rownum][colcount];


		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j); //1,0
			}
		}

		return logindata;

	}
}
