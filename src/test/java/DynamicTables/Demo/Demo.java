package DynamicTables.Demo;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {
static WebDriver driver = null;
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa");
		
		String companyName = "Magma Fincorp Ltd.";
				
		int companyColumnNumber = getColumnNumberOfColumnName("Company");
		int currentPriceColumnNumber = getColumnNumberOfColumnName("Current Price (Rs)");
	
		
		if((companyColumnNumber!=-1) && (currentPriceColumnNumber!=-1)) {
			
			int rNum = getRowNumberOfCellValue(companyName,companyColumnNumber);
			
			if(rNum!=-1) {
				
				String currentPrice = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr["+rNum+"]/td["+currentPriceColumnNumber+"]")).getText();
				System.out.println("The current price of Company "+companyName+" is: "+currentPrice);
				
			}else {
				
				System.out.println("The company name "+companyName+" is not available in the table data");
				
			}
			
		
		}else {
			
			System.out.println("Company or Current Price Rs headings may not be avialable in the table headings");
			
		}
		
		driver.quit();
		
	}
	
	public static int getColumnNumberOfColumnName(String columnName) {
		
		List<WebElement> columns = driver.findElements(By.xpath("//table[@class='dataTable']/thead/tr/th"));
		
		for(int cNum=0;cNum<columns.size();cNum++) {
			
			String retrievedColumnName = columns.get(cNum).getText();
			
			if(retrievedColumnName.equals(columnName)) {
				
				return ++cNum;
				
			}
			
		}
		
		return -1;
		
	}
	
	public static int getRowNumberOfCellValue(String cellValue, int cNum) {
		
		List<WebElement> columnValues = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td["+cNum+"]"));
		
		for(int rNum=0;rNum<columnValues.size();rNum++) {
			
			String extractedValue = columnValues.get(rNum).getText();
			
			if(extractedValue.equals(cellValue)) {
				
				return ++rNum;
				
			}
		}
		
		return -1;
		
	}
}
