package br.ce.nicopizzoni.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		// WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.1.104:8001/tasks"); // ip da maquina - ipconfig
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComErro() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		//Clicar em add Tasks
		driver.findElement(By.id("addTodo")).click();
		
		//Preencher task
		driver.findElement(By.id("task")).sendKeys("Teste Erro");
		
		//Preencher data com valor passado
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		
		//Validar mensagem de erro
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Erro", message);
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher task
			driver.findElement(By.id("task")).sendKeys("Task criada via Selenium");
			
			//Preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2040");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
		} finally {			
			//fechar browser
			driver.quit();
		}
		
		
	}
	
	@Test
	public void napDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2040");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
		} finally {			
			//fechar browser
			driver.quit();
		}
		
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher task
			driver.findElement(By.id("task")).sendKeys("Task criada via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
		} finally {			
			//fechar browser
			driver.quit();
		}
		
		
	}
	
	@Test
	public void napDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
		} finally {			
			//fechar browser
			driver.quit();
		}
		
		
	}
}
