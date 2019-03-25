package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {


	@Test
	public void login() throws IOException
	{
		
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(username);
		log4j.info("Entered Username");
		lp.setPassword(password);
		log4j.info("Entered Password");
		lp.loginbtn();
		log4j.info("clicked login button");


		if(driver.getTitle().equals("Guru99999,..... Bank Manager HomePage")) {
			Assert.assertTrue(true);
			log4j.info("Title Matched");
		}
		else
		{
			
			captureScreen(driver,"login");
			Assert.assertTrue(false);
			log4j.info("Title Not Matched");
			
		}

	}




}


