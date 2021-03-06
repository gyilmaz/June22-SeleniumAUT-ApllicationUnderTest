package com.jobappliaction;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PersonalInfoTests {
	WebDriver driver;
	String firstName;
	String lastName;
	int gender;
	String dateOfBirth;
	String email;
	String phoneNumber;
	String city;
	String state;
	String country;
	int annualSalary;
	List<String> technologies;
	int yearsOfExperience;
	String education;
	String github;
	List<String> certifications;
	String additionalSkills;
	Faker data = new Faker();
	Random random = new Random();
	String actualIp;
	String appId; 
	List<String> bodyList= new ArrayList<>(); 	
	List<String> emailList= new ArrayList<>();
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().fullscreen();
	}
	
	@BeforeMethod //runs before each @Test
	public void navigateToHomePage() {
		System.out.println("Navigating to homepage in @BeforeMethod....");
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");	
		firstName = data.name().firstName();
		lastName = data.name().lastName();
		gender = data.number().numberBetween(1,3);
		dateOfBirth = data.date().birthday().toString();
		email = "putyouremail";
		phoneNumber = data.phoneNumber().cellPhone().replace(".", "");
		city=data.address().cityName();
		state=data.address().stateAbbr();
		country=data.address().country();
		annualSalary=data.number().numberBetween(60000, 150000);
		technologies = new ArrayList<>();
		technologies.add("Java-" + data.number().numberBetween(1,4));
		technologies.add("HTML-" + data.number().numberBetween(1,4));
		technologies.add("Selenium WebDriver-" + data.number().numberBetween(1,4));
		technologies.add("TestNG-"+ data.number().numberBetween(1,4));
		technologies.add("Git-"+ data.number().numberBetween(1,4));
		technologies.add("Maven-"+ data.number().numberBetween(1,4));
		technologies.add("JUnit-"+ data.number().numberBetween(1,4));
		technologies.add("Cucumber-"+ data.number().numberBetween(1,4));
		technologies.add("API Automation-"+ data.number().numberBetween(1,4));
		technologies.add("JDBC-"+ data.number().numberBetween(1,4));
		technologies.add("SQL-"+ data.number().numberBetween(1,4));
		
		yearsOfExperience = data.number().numberBetween(1, 11);
		education = data.number().numberBetween(1, 4)+"";
		github = "https://github.com/CybertekSchool/selenium-maven-automation.git";
		certifications = new ArrayList<>();
		certifications.add("Java OCA");
		certifications.add("AWS");
		additionalSkills = data.job().keySkills();
		
	}
	
	@Test (priority=2)
	public void verifyIpAddress(){
		
		driver.get("http://whatismyip.host/");
		String expectedIP = driver.findElement(By.xpath("//div[@id='ipv4']//preceding-sibling::p[@class='ipaddress']"))
				.getText();
		System.out.println(expectedIP);
		Assert.assertEquals(actualIp, expectedIP);
	}
	@Test (priority=3)
	public void verifyEmail(){
		driver.get("http://mail.google.com");
		driver.findElement(By.id("identifierId")).sendKeys("putyouremail");
		driver.findElement(By.xpath("//span[.='Next']")).click();
		driver.findElement(By.name("password")).sendKeys("putyoutpass"+ ""+Keys.ENTER);
		String application="SDET Job Application #" +appId;
		driver.findElement(By.id("gbqfq")).sendKeys(application+""+Keys.ENTER);
		driver.findElement(By.name("training@cybertekschool..")).click();
		 											
		emailList.add(driver.findElement(By.xpath("//td[@valign='top']//parent::span[contains(text(),'"+appId+"')]")).getText());//appId 908"
		String nameEmail=driver.findElement(By.xpath("//td[contains(text(),'Full name')]/following-sibling::td[2]")).getText();
		emailList.add(nameEmail.replace(",","").replace(" ", ","));//Full name "Kaia Kautzer,"
		
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td[2]")).getText());//Gender
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'Date of birth')]/following-sibling::td[2]")).getText());//DOB
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td[2]")).getText());//EMAIL
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'Phone')]/following-sibling::td[2]")).getText());//PHONE
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td[2]")).getText().replace(" ", ""));//ADDRESS
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'Current Annual Salary')]/following-sibling::td[2]")).getText());//SALARY
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'TECHNOLOGIES')]/following-sibling::td[2]")).getText().replace(" ", ""));//TECH
		emailList.add(driver.findElement(By.xpath("//td[contains(text(),'Years of Experience')]/following-sibling::td[2]")).getText());//YearsOfEx
//		System.out.println(bodyList);
//		System.out.println(emailList);
		
		bodyList.removeAll(emailList); 
		Assert.assertTrue(bodyList.isEmpty());
			
		}
		
	
	
	@Test (priority=1)
	public void submitFullApplication() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='Name_First']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='Name_Last']")).sendKeys(lastName);
		setGender(gender);
		setDateOfBirth(dateOfBirth);
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='countrycode']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@name='Address_City']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='Address_Region']")).sendKeys(state);
		Select countryElem = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));
		countryElem.selectByIndex(data.number().numberBetween(1, countryElem.getOptions().size()));
		driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(String.valueOf(annualSalary)+Keys.TAB);
		verifySalaryCalculations(annualSalary);
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
		
		// SECOND PAGE ACTIONS
		setSkillset(technologies);
		if(yearsOfExperience > 0) {
			driver.findElement(By.xpath("//a[@rating_value='"+ yearsOfExperience +"']")).click();
		}
		Select educationList = new Select(driver.findElement(By.xpath("//select[@name='Dropdown']")));
		educationList.selectByIndex(data.number().numberBetween(1, educationList.getOptions().size()));
		//github
		driver.findElement(By.xpath("//input[@type='text' and @name='Website']")).sendKeys(github);
		certification();
		driver.findElement(By.name("MultiLine")).sendKeys("good time management");
		driver.findElement(By.xpath("//em[.='Apply']")).click();
		actualIp = driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'IP')] ")).getText().substring(12);
		appId=driver.findElement(By.xpath("//label[@class='descFld']//div[contains(text(), 'Application ID:')]")).getText().substring(16); 
		//Thread.sleep(4000);
												
				bodyList.add(appId);	
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Dear')]")).getText().substring(5).replace(",","").replace(" ",""));//Full name "Kaia Kautzer,"
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Gender')]")).getText().substring(7));//Gender
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Date')]")).getText().substring(15));//DOB
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Email')]")).getText().substring(7));//EMAIL
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Phone')]")).getText().substring(7));//PHONE
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Address')]")).getText().substring(9).replace(" ", ""));//ADDRESS
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Annual Salary')]")).getText().substring(15));//SALARY
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Technologies')]")).getText().substring(13).replace(" ", ""));//TECH
				bodyList.add(driver.findElement(By.xpath("//label[@class='descFld']//div[contains (text() ,'Years')]")).getText().substring(21));//YearsOfEx
				//System.out.println(Arrays.toString(bodyList.toArray()));
						
	}	
	public void certification(){
		int num=data.number().numberBetween(0, 3); // how many will be selected
		int rn1=0;
		int rn2=0; 
		while(rn1==rn2){
		rn1=data.number().numberBetween(0, 3); 
		rn2=data.number().numberBetween(0, 3); 
		}
		int num1=data.number().numberBetween(0,4);	// which ones will be selected
		WebElement cert1 = driver.findElement(By.id("Checkbox_1"));
        WebElement cert2 = driver.findElement(By.id("Checkbox_2"));
        WebElement cert3 = driver.findElement(By.id("Checkbox_3"));
        List<WebElement> certList= new ArrayList<>();
        				 certList.add(cert1); 
        				 certList.add(cert2); 
        				 certList.add(cert3);
        switch(num1){
        case 0: break; 
        case 1:certList.get(rn1).click();break;
        case 2:certList.get(rn1).click(); certList.get(rn2).click(); break; 
        case 3: certList.get(0).click();certList.get(1).click(); certList.get(2).click(); break;   
        
        }
	 
		
	}
	public void setSkillset(List<String> tech) {
		
		for (String skill : tech) {
			String technology = skill.substring(0, skill.length()-2);
			int rate = Integer.parseInt(skill.substring(skill.length()-1));
			
			String level = "";
			
			switch(rate) {
				case 1:
					level = "Expert";
					break;
				case 2:
					level = "Proficient";
					break;
				case 3:
					level = "Beginner";
					break;
				default:
					fail(rate + " is not a valid level");
			}
			
			String xpath = "//input[@rowvalue='"+ technology +"' and @columnvalue='"+ level +"']";
			driver.findElement(By.xpath(xpath)).click();
			
		}
		
		
	}
	
	public void verifySalaryCalculations(int annual) {
		String monthly = driver.findElement(By.xpath("//input[@name='Formula']")).getAttribute("value");
		String weekly = driver.findElement(By.xpath("//input[@name='Formula1']")).getAttribute("value");
		String hourly =  driver.findElement(By.xpath("//input[@name='Formula2']")).getAttribute("value");
		
		System.out.println(monthly);
		System.out.println(weekly);
		System.out.println(hourly);
		
		DecimalFormat formatter = new DecimalFormat("#.##");
		
		assertEquals(Double.parseDouble(monthly),Double.parseDouble(formatter.format((double)annual /12.0)));
		assertEquals(Double.parseDouble(weekly),Double.parseDouble(formatter.format((double)annual / 52.0)));
		assertEquals(Double.parseDouble(hourly),Double.parseDouble(formatter.format((double)annual / 52.0 / 40.0)));
	}
	
	public void setDateOfBirth(String bday) {
		String[] pieces = bday.split(" ");
		String birthDay = pieces[2] + "-" +  pieces[1] + "-" + pieces[5];
		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(birthDay);
	}
	public void setGender(int n) {
		if(n==1) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		}else {
			driver.findElement(By.xpath("//input[@value='Female']")).click();
		}
	}
	
	@Test
	public void fullNameEmptyTest() {
		//firstly assert that you are on the correct page
		assertEquals(driver.getTitle(), "SDET Job Application");
		
		driver.findElement(By.xpath("//input[@elname='first']")).clear();	
		driver.findElement(By.xpath("//*[@elname='last']")).clear();

		driver.findElement(By.xpath("//em[.=' Next ']")).click();

		String nameError = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
		assertEquals(nameError, "Enter a value for this field.");
	}
}	