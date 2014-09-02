#回调接口
回调接口：实际上类似于C语言里面的钩子函数，在方法里面传入的方法是可以被覆盖的。使用示例见Dbutil.md文件。  
代码示例如下：  
>接口：
	public interface SubStringable
	{
		Object subString(String str);
	}
>方法：
	public class queryStr
	{
		Object substr(String str,SubStringable stringable)
		{
			String result = null;
			result = (String) stringable.subString(str);
			return result;
		}
	}
>使用回调接口：
>
	public class client
	{
		public static void main(String[] args)
		{
			String s = "hello";
			queryStr q = new queryStr();
			Object res = q.substr(s, new SubStringable()
			{
				@Override
				public Object subString(String str)
				{
					String strs = str.substring(2);
					return strs;
				}
			});
			System.out.println(res);
		}
	}
	
回调接口的作用就是：可以自由定制对数据的处理方法，例如，上面就是定制了一个接口，里面对数据的处理取决于使用方法的用户的具体实现。更加灵活，体现了面向接口编程的思想。  
回调接口在后面的框架中使用很多。  
