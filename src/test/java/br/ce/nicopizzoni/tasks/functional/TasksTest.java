package br.ce.nicopizzoni.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComErro() {
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
	public void deveSalvarTarefaComSucesso() {
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
	public void napDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void napDeveSalvarTarefaComDataPassada() {
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
