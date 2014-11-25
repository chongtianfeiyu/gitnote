/**
 * @file Name: ClassTest.java
 * @description:test Class
 * @author : trilever
 * @version : 1.0
 * @mail: trilever31204@gmail.com 
 * @created Time: 2014-5-30 16:00:00
 */	
import java.io.*;
interface eatable
{
	public void eat();
}

class person implements eatable
{	
	public void eat()
	{
		System.out.println("person EAT");
	}
}

class animal implements eatable
{	
	public void eat()
	{
		System.out.println("animal EAT");
	}
}

public class ClassTest
{
	public static void main(String[] args)throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("file.txt")) ));
		Class className = Class.forName(br.readLine());
		
		eatable ea= (eatable)className.newInstance(); 
		ea.eat();

		Class cs = 	animal.class;
		Method[] methods = 	cs.getMethods();
		for(Method m:methods)
		{
			System.out.println(m);
		}
	}
}
