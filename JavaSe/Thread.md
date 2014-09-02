
Java中的进程与线程
--------------
进程：运行中的程序。  
1. 进程是系统中一个独立存在的实体，拥有自己独立的资源、内存区。一个进程的内存空间*一般是不能允许*其他进程访问。  
2. 动态性。程序是静止的，运行起来才叫进程。  
3. 并发性。操作系统中可以同时"并发（concurrent）"。可以运行多个进程。

线程：进程中并发执行流，也叫light-weight Process。没有自己独立的内存、资源。而是和其他的线程共享。

进程的创建成本比线程要高。

并发（`concurrent`）：即使**只有一个CPU**，多个进程或者线程在这个CPU上**快速轮换**地执行。
在任何时刻，只有与CPU个数相同的进程在真正的执行，其他的进程都处于等待状态。但是人是感受不到这个等待。

并行（`parallel`）：必须有**一个以上的CPU**，在同一时刻至少有与`CPU`个数相同的进程在执行。这些**同时**（而不是快速轮流）执行。这才是真正的同时执行。这些进程就是并行执行。
 
多线程的好处：  
1. 功能上，多线程类似于多进程  
2. 创建成本低、效率高。  
3. 线程之间通信方便，无需使用管道流。  
4. `Java`语言的多线程很优秀。

`Java`中创建多线程的方法：  
1. 继承`Thread`类以创建多线程。
   注：线程执行体：就是该线程将要做的事情。`run()`方法里面也就是要执行的代码。
   `Thread`这个类就是线程类。代表着线程。
   `Thread`类最重要的方法是`run()`，它为`Thread`类的方法`start()`所调用，提供我们的线程所要执行的代码。为了指定我们自己的代码，只需要覆盖它！
   `main()`方法本身就是一个主线程。它是默认已启动的线程。我们在`main()`方法之外创建我们自己想要的任意线程，然后在`main()`方法这个主线程里面使用`start()`方法启动这些线程。
a. 继承`Thread`类。重写一个`run()`方法。这个`run()`方法就是线程执行体。里面就是我们要在这个线程里面做的事情。  
   这个`run()`方法不能有返回值，也不能抛出异常。 	
   
b. 在`run()`方法内加入我们要在本线程中执行的代码。例如：通过`Thread`类的`currentThread()`这个静态方法获得当前在运行的线程。 

c. 调用`Thread`对象(`new`一个`Thread`对象即是创建一个线程)的`start()`方法启动线程，而不能调用`run()`方法。每`start()`一次都创建了一个新线程并运行一次`run()`方法。因此，如果想在不同线程中执行不同的代码，就需要在`run()`方法中针对不同的线程名再调用不同的代码即可。
实际上就是创建一个我们的类对象(线程对象)，然后用这个类对象调用它的`start()`方法，因为它已经继承了`Thread`类，所以它有`start()`方法。

`Thread`使用示例：
>
	public class  ThreadTest extends Thread
	{
		public void f(String str)
		{
			System.out.println(str);
		}
		//run method includes the code you want to exec in the current Thread. 
		public void run()
		{
			//for different thread exec the different func
			f(Thread.currentThread().getName());
		}
		//the main thread
		public static void main(String[] args) 
		{
			for(int i=0;i<100;i++)
			{
				//construct a new thread and run it
				new ThreadTest().start();
			}
>		
			System.out.println(Thread.currentThread().getName());
		}
	}

2. 实现`Runnable`接口
a. 实现`Runnable`接口。重写`run()`方法。`run()`里面即是我们想要执行的代码。  
b. 由于`Runnable`接口中并没有`start()`方法,因此只能将`Runnable`对象包装成`Thread`对象(线程对象)才能调用`start()`方法以创建线程并启动。

`Runnable`接口使用示例：
>
	public class  RunnableImplementsTest implements Runnable
	{
		//run method includes the code you want to exec in the current Thread. 
		public void run()
		{
			//for different thread exec the different func
			System.out.println(Thread.currentThread().getName());
		}
		//the main thread
		public static void main(String[] args) 
		{
			for(int i=0;i<100;i++)
			{
				//construct a new thread and run it
				(new Thread(new RunnableImplementsTest())).start();
			}
			System.out.println(Thread.currentThread().getName());
		}
	}

3. 实现`Callable<V>`接口  
   由于前两种方法中，`run()`方法都不能有返回值。所以我们可以实现`Callable<V>`接口，让我们执行的代码具有返回值。这个接口就是`Runnable`接口的增强版。它可以有返回值，还可以抛出异常。
a. 实现`Callable<V>`接口，其中的`V`是我们要执行的代码返回的值类型。重写`call()`方法。该方法具有返回值，还可以抛出异常。  
b. 创建并启动线程的时候，要先将`Callable`对象包装成`Runnable`对象，然后将`Callable`对象包装成`Runnable`对象（由于`Runnable`只是一个接口，需要将`Callable`对象包装成`Runnable`接口的*实现类对象*,实际上就是先将`Callable`对象包装成`FutureTask<V>`对象)，然后将`Runnable`的实现类对象`FutureTask<V>`对象包装成`Thread`对象，然后用`Thread`对象的`start()`方法来创建并启动新线程。  
c. 调用`FutureTask<V>`对象的方法`get()`来获得`call()`方法里的返回值。

`Callable<V>`使用示例：
>
	import java.util.concurrent.*;
	public class  CallableImplementsTest implements Callable<String>
	{
		//run method includes the code you want to exec in the current Thread. 
		public String call() throws Exception 
		{
			//for different thread exec the different func
			return Thread.currentThread().getName();
		}
		//the main thread
		public static void main(String[] args) throws Exception
		{
			for(int i=0;i<100;i++)
			{
				//construct a new thread and run it
				CallableImplementsTest  cit= new CallableImplementsTest();
				FutureTask<String> ft = new FutureTask<String>(cit);
				new Thread(ft).start();
				System.out.println(ft.get());
			}
			System.out.println(Thread.currentThread().getName());
		}
	}

IM经验：当我们要向一个方法中传入一个参数，但是实际上我们有的实参的类型不与之匹配，那么就去看看这个方法中参数的类是否有子类，我们是否可以将我们已有的实参包装成这个方法所要求的参数类型的子类，然后将包装而得的子类对象传给这个方法即可。所以我们编程的时候，对于方法，往往要注意对接口编程，就是将方法中的形参数类型设为接口或者其他的抽象类，以保证使用时可以传入更多的类型实参。
  
实际上，在`Java`中，需要一个接口的对象、需要一个抽象类的对象、需要一个类的对象，都可以视作需要这个接口的实现类（非抽象的）对象、需要这个抽象类的实现类（非抽象的）的对象，需要这个类的子类的对象。

创建线程的方式的对比，创建线程方式分为两类：  
1. 继承`Thread`类。  
2. 实现`Runnable`或者`Callable`接口。  
实现接口的方式更好。原因：  
1. 继承了`Thread`类后就不能继承其他的类了，但是实现了接口之后还可以继承别的类。  
2. 实现接口可以让多个`Thread`对象在创建的时候可以共享同一个`Runnable`对象。更好地实现代码与数据的分离，逻辑更清晰。  
3. 实现接口的缺点：编码略微复杂。


线程的状态：  
当调用`start()`之后，只是启动线程，并没有立即执行线程(没有立即执行`run`方法)。  
新建状态：`new`了一个`Thread`对象(线程对象)后即是新建状态。该线程对象只是一个`Java`对象。
  
就绪状态：调用`start()`后处于就绪状态。
  
运行状态：从就绪到运行状态之间是不可控的，这里依靠系统的线程调度器进行分配，是一个随机的分配。  
如果Thread调用静态方法`sleep(int time)`，就会让当前线程进入阻塞状态`time`长时间，`CPU`会在中止的这段时间内运行其他的线程。  
如果`Thread`调用静态方法`yield()`,就会让当前线程让出`CPU`，处于就绪状态。

阻塞状态(`Blocked`)：如`Thread`执行`sleep`之后，当前线程就会进入阻塞状态。进入阻塞状态之后，当`sleep`时间完成之后，线程离开阻塞状态，进入就绪状态。然后，系统再自己让这个就绪状态的线程进入运行状态。

以上状态转换见*Thread Status状态图*。


控制线程的几个基本方法：  
1. `join()`线程。启动多条线程之后，调用`join()`的线程要先完成以后（调用`join()`的线程死亡之后才行），剩下的线程才能继续往下执行。  
2. 后台线程。也就是`Daemon Thread`。也叫守护线程。因为它的特征是：如果所有的前台线程结束，它会自动死亡。作用是监控前台线程，为前台线程服务。可以将某个线程设为后台线程。那么其他线程都结束之后，它就会死亡。  
3. 线程暂停。`Thread.sleep(100)`:让线程暂停100ms，进入阻塞状态。暂停完成之后，就会进入就绪状态，然后由系统分配`CPU`。  
4. 线程让步。`Thread.yield()`:让出`CPU`，进入就绪状态。在执行了`yield()`之后，就会让系统再次进行`CPU`分配，可能刚刚让出`CPU`的线程再次被分配到了`CPU`，继续执行。  
一般使用`sleep()`而不使用`yield()`，因为前者更稳定。  
5. 改变线程优先级：线程优先级越高，线程就会获得更多被执行的机会。`setPriority(int value)`方法。


`join`使用示例：
>
	public class  ThreadJoinTest extends Thread
	{
		public void run()
		{
			for(int i=0;i<100;i++)
			{
				System.out.println(Thread.currentThread().getName()+"----"+i);
			}
		}
		//the main thread
		public static void main(String[] args) throws Exception
		{
			for(int i=0;i<100;i++)
			{
				System.out.println(Thread.currentThread().getName()+"-----"+i);
				//construct new thread 
				ThreadJoinTest t1 = new ThreadJoinTest();
				ThreadJoinTest t2 = new ThreadJoinTest();
				if(i==20)
				{
					t1.start();
					t2.start();
>				
					t1.join();
					t2.join();
				}
			}
		}
	}



`Daemon Thread`使用示例：
>
	public class  DeamonThreadTest extends Thread
	{
		public void run()
		{
			for(int i=0;i<100;i++)
			{
				System.out.println(Thread.currentThread().getName()+"----"+i);
			}
		}
		//the main thread
		public static void main(String[] args) throws Exception
		{
				//construct new thread 
				DeamonThreadTest t1 = new DeamonThreadTest();
				//set the thread t1 as Deamon Thread after the main thread ends it will also die
				t1.setDaemon(true); 				
				t1.start();
				System.out.println(Thread.currentThread().getName()+"-----"+"ends");
		}
	}


`setPriority`使用示例：

>
	public class  ThreadPriortityTest extends Thread
	{
		public void run()
		{
			for(int i=0;i<100;i++)
			{
				System.out.println(Thread.currentThread().getName()+"----"+i);
			}
		}
		//the main thread
		public static void main(String[] args) throws Exception
		{
				//construct new thread 
				ThreadPriortityTest t1 = new ThreadPriortityTest();
				ThreadPriortityTest t2 = new ThreadPriortityTest();
				//set the priority of the threads
				t1.setPriority(Thread.MAX_PRIORITY); 	
				t2.setPriority(Thread.MAX_PRIORITY-1); 	
				t1.start();
				t2.start();
		}
	}

线程同步：
如：银行账户取钱问题，如果是多线程的话，可能多个人同时取款，会超额取钱。  
竞争资源（共享资源）：如果多个线程需要并发访问、并修改某个对象，该对象就是竞争资源。
因此就需要控制线程安全，线程安全的方法有：  
1. 同步代码块：需要显式指定同步监视器。  
2. 同步方法:不需要显式指定同步监视器。只需要在要作为原子方法的方法前加上`synchronized`标示符即可。其相当于使用方法的调用者作为*同步监视器*。如果方法是示例方法，相当于`this`就是同步监视锁。如果方法是类方法，相当于这个类就是同步监视锁。  

为避免多个线程“自由竞争”修改共享资源导致的不安全问题。于是考虑对资源进行“加锁”。  
以上两种方法的实现机制：当程序要进入某个被“同步监视锁”所监视的代码之前，本线程必须先去获得“同步监视锁” 。  
从语法角度来看，任意对象都可以作为同步监视锁，但是从程序逻辑来看，选择“竞争资源”作为同步监视锁。如下例中的`balanceAccount`就是同步监视器。*所谓的同步监视锁，实际上就是这段代码中要监视的对象。*  

线程同步的关键在于：任意线程进入同步监视器监视的代码之前，都需要对同步监视器加锁。  
什么时候释放对同步监视器的锁？  
1. 监视的代码执行完成。  
2. 在代码中遇到了`break`或者`return`，跳出代码块。  
3. 执行代码过程中遇到了未捕获的异常。  
4. 调用同步监视器的`wait()`方法，使线程进入`wait`状态，也就是阻塞状态。如果想某一个线程在运行完之后不会死亡，而是在下次还可以运行（不用再次`start`），那么就可以对此线程使用`wait()`。使该线程进入阻塞状态，以后在别的线程中使用notify()，就可以唤醒这个阻塞的线程。

什么时候不会释放对同步监视器的锁？  
1. `sleep()`、`yield()`都不会释放。  
2. `suspend()`也不会释放。  
  
同步代码块使用示例：
>
	//The account of a person
	class Account
	{
		//the name of the person
		private String name;
		//the balanceAccount of the person
		private int account;
		Account()
		{
		}
		Account(String aName,int aAccount)
		{
			name=aName;
			account=aAccount;
		}
		public void setName(String aName)
		{
			this.name=aName;
		}
		public void setAccount(int aAccount)
		{
			this.account=aAccount;
		}
		public String getName()
		{
			return this.name;
		}
		public int getAccount()
		{
			return this.account;
		}
	}
	public class  AccoutThreadTest extends Thread
	{
		//the amount of money you want to get 
		private int drawAccount;
		//the amount of money in your account
		private Account balanceAccount;
		AccoutThreadTest(int adrawAccount,Account abalanceAccount)
		{
			this.drawAccount=adrawAccount;
			this.balanceAccount=abalanceAccount;
		}
		public void run() 
		{
			//add the concurrelock on the code block
			synchronized(balanceAccount)
			{
				if(drawAccount<=balanceAccount.getAccount())
				{
					System.out.println(Thread.currentThread().getName()+", You have got the money you want to draw: "+drawAccount);
					balanceAccount.setAccount(balanceAccount.getAccount()-drawAccount);
					System.out.println("The amount of money you left in the account is: "+balanceAccount.getAccount());
				}
				else
				{
					System.out.println(Thread.currentThread().getName()+", The amount of money in your account is not enough!");
				}
			}
		}
		//the main thread
		public static void main(String[] args) throws Exception
		{
			Account ac = new Account("wt",1000);
			new AccoutThreadTest(800,ac).start();
			new AccoutThreadTest(800,ac).start();
		}
	}

同步方法使用示例：
>
	//The account of a person
	class Account
	{
		//the name of the person
		private String name;
		//the balanceAccount of the person
		private int account;
		Account()
		{
		}
		Account(String aName,int aAccount)
		{
			name=aName;
			account=aAccount;
		}
		public void setName(String aName)
		{
			this.name=aName;
		}
		public void setAccount(int aAccount)
		{
			this.account=aAccount;
		}
		public String getName()
		{
			return this.name;
		}
		public int getAccount()
		{
			return this.account;
		}
		//the synchronized method,the this get the synchronized lock
		public synchronized void draw(int drawAccount)
		{
			if(drawAccount<=account)
				{
					System.out.println(Thread.currentThread().getName()+", You have got the money you want to draw: "+drawAccount);
					account=account-drawAccount;
					System.out.println("The amount of money you left in the account is: "+account);
				}
			else
				{
					System.out.println(Thread.currentThread().getName()+", The amount of money in your account is not enough!");
				}
		}
	}
	public class  AccoutThreadFunTest extends Thread
	{
		//the amount of money you want to get 
		private int drawAccount;
		//the amount of money in your account
		private Account balanceAccount;
		AccoutThreadFunTest(int adrawAccount,Account abalanceAccount)
		{
			this.drawAccount=adrawAccount;
			this.balanceAccount=abalanceAccount;
		}
		public void run() 
		{
			//the balanceAccount object call the synchronized method. this has the synchronized lock
			balanceAccount.draw(drawAccount);
		}
		//the main thread
		public static void main(String[] args) throws Exception
		{
			Account ac = new Account("wt",1000);
			//construct 2 different threads
			new AccoutThreadFunTest(800,ac).start();
			new AccoutThreadFunTest(800,ac).start();
		}
	}



线程的通信：同一个进程中的各个线程共享进程的内存空间。  
1. 如果不加控制，多个线程会“自由”地并发执行。  
2. 可以通过同步来解决多个线程并发访问竞争资源的问题。同步会导致效率线程降低，也就是前面所言的线程安全的类会导致效率降低。前面所言的线程安全的类适合于多线程的环境，线程不安全的类适合于单线程环境。  
3. 如果希望各线程之间能更有序地执行。如，生产者-消费者问题，我们希望消费者每次消费的时候都有资源可供消费。这就要求生产者线程与消费者线程之间能够互通消息，才能保证线程之间能够协调有序进行。这就涉及到线程通信的问题。
这里使用到了`Object`类的几个方法：`wait()`--控制线程暂停，会释放对同步监视器的锁，直到收到唤醒通知、`notify()`--发送唤醒通知，唤醒处于`wait`状态的线程、`notifyAll()`--发送唤醒通知，唤醒处于`wait`状态的线程。  
但是并不是所有的对象都可以调用者三个方法。只有**同步监视器**才能调用者三个方法。
如果不使用wait()，就会导致本线程运行完后立刻结束了，线程不会再启动了。也就是说整个线程都结束死亡了，如果不想这个线程在执行这一次之后就死亡，而是想让这个线程此次运行完之后不会死亡，以后还可以不用start就可以运行，那么就要使用wait()，然后在其他线程中使用notify()之后，就会唤醒处于wait状态的线程。这样，就能保证线程在我们可控的顺序下运行。

线程通信使用示例：
>
	//The account of a person
	class Account
	{
		//the name of the person
		private String name;
		//the balanceAccount of the person
		private int account;
		public boolean hasdeposted=false;
		Account()
		{
		}
		Account(String aName,int aAccount)
		{
			name=aName;
			account=aAccount;
		}
		public void setName(String aName)
		{
			this.name=aName;
		}
		public void setDeposit(boolean b)
		{
			this.hasdeposted=b;
		}
		public void setAccount(int aAccount)
		{
			this.account=aAccount;
		}
		public String getName()
		{
			return this.name;
		}
		public int getAccount()
		{
			return this.account;
		}
	}
	class drawThread extends Thread
	{
		//the amount of money you want to get 
		private int drawAccount;
		//the amount of money in your account
		private Account balanceAccount;
		drawThread(int adrawAccount,Account abalanceAccount)
		{
			this.drawAccount=adrawAccount;
			this.balanceAccount=abalanceAccount;
		}
		public void run() 	
		{
			try
			{
				int i;
				//add the concurrelock on the code block
				synchronized(balanceAccount)
				{
					for(i=0;i<100;)
					{
					if(balanceAccount.hasdeposted==true)
					{
						if(drawAccount<=balanceAccount.getAccount())
						{
							System.out.println(Thread.currentThread().getName()+", You have got the money you want to draw: "+drawAccount);
							balanceAccount.setAccount(balanceAccount.getAccount()-drawAccount);
							System.out.println("The amount of money you left in the account is: "+balanceAccount.getAccount());
							System.out.println(i);
							i++;
						}
						else
						{
							System.out.println(Thread.currentThread().getName()+", The amount of money in your account is not enough!");
						}
						//set the flag value
						balanceAccount.setDeposit(false);
						//notify all the other values
						balanceAccount.notifyAll();
					}
					else
					{
						balanceAccount.wait();
					}
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("error");
			}
		}
	}	
	class  depositThread extends Thread
	{
		//the amount of money you want to get 
		private int depositAccount;
		//the amount of money in your account
		private Account balanceAccount;
		depositThread(int adepositAccount,Account abalanceAccount)
		{
			this.depositAccount=adepositAccount;
			this.balanceAccount=abalanceAccount;
		}
		public void run() 
		{
			int j;
			//add the concurrelock on the code block
			try
			{
				synchronized(balanceAccount)
				{
					for(j=0;j<100;)
					{
					if(balanceAccount.hasdeposted==false)
					{
						System.out.println(Thread.currentThread().getName()+", You have deposit the money you want to : "+depositAccount);
						balanceAccount.setAccount(balanceAccount.getAccount()+depositAccount);
						System.out.println("The amount of money you left in the account is: "+balanceAccount.getAccount());
						System.out.println(j);
						//set the flag value
						balanceAccount.setDeposit(true);
						//notifyAll all the other threads
						balanceAccount.notifyAll();
						j++;
					}
					else
					{
						balanceAccount.wait();
					}
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("error");
			}
		}
	}
>	
	public class ThreadCommuniTest
	{
		//the main thread
		public static void main(String[] args) throws Exception
		{
			Account ac = new Account("wt",1000);
			new depositThread(800,ac).start();
			new drawThread(800,ac).start();
		}
	}


线程组与未处理的异常
`Java`中用一个类`ThreadGroup`表示线程组。  
怎样将线程方法放入指定的线程组中？在构造线程对象的时候在构造器中设置器所在的线程组。  
将线程放入线程组之后，就能够对线程组中的线程进行整体的管理。  
线程组里面还可以包含线程组。
使用线程组的时候，需要实现`Runnable`接口或者`Callable`接口，而不要去继承`Thread`类。这样方便与将线程加入线程组。  
线程组对其内的线程的控制：  
1. setDaemon():将这个线程组设置为后台线程组，但并不是将其内的线程设为后台线程。后台线程组的作用在于：当后台线程组里面所有的线程都死亡了，那么这个后台线程组本身也会自动销毁。否则要销毁后台线程组只能调用其destory()方法。  
2. setPriority()：设置线程组的优先级，表示线程组里面的线程的优先级不会超过线程组的优先级。但是这只会限制在设置线程组优先级之后加入的线程的优先级，对于设置线程组优先级之前的线程是没有影响的。  
3. 用于线程异常处理的方法：*在JDK1.5以前*，如果线程出现了异常，系统会自动回调它所在的线程组的uncaughtException()方法来修复异常。也就是说，让线程组帮助线程处理异常。*在JDK1.5以后*，可以由线程自己设置异常处理器(本线程类或者类对象调用给异常处理方法即可），无需线程组帮忙处理了。

线程异常处理的三种方法，由于任何一个线程所要运行的代码都放在该线程的run()方法内部，所以如果要在run()方法外处理异常，就需要run()方法throws Exception，然而，由于run()方法是重写而来，原来的run()方法是没有throws Exception的，所以重写之后也不能throws Exception。因此，我们就需要使用其他的方式而不是依靠run()方法throw来处理异常。所以在Thread类和ThreadGroup类中都有专门的异常处理方法。

线程异常处理方法：  
方法1：使用`ThreadGroup`类的处理方法进行处理
>
	public class ThreadGroupExceptionTest implements Runnable
	{
		public void run()
		{	
			for(int i=0;i<100;i++)
			{
				System.out.println(100/(i-20));
			}
		}
		public static void main(String[] args) 
		{
			ThreadGroup tg = new ThreadGroup("tg1"){
				//set the thread exception dealer func
				//the threadgroup deal the exception for the thread
				public void uncaughtException(Thread t, Throwable e) 
				{
					System.out.println("The Error occure in the thread: "+t.currentThread().getName()+e.getMessage());
				}
			};
>			
			ThreadGroupExceptionTest tgt = new ThreadGroupExceptionTest();
			//construct the thread, add it to the threadgroup
			Thread t = new Thread(tg,tgt);
			t.start();
>			
			//construct the thread, add it to the threadgroup
			Thread t1 = new Thread(tg,tgt);
			t1.start();
>			
			//calculate the num of the thread in the threadgroup
			System.out.println(tg.activeCount());
>			
			//the threadgroup with dealing fun an deal with the exception came out of the threads in the threadgroup but not the thread came out of the threadgroup
			System.out.println(20/0);
		}
	}



方法2:使用`Thread`类的处理方法,各个`Thread`对各自的`Thread Exception`进行处理
>
	public class ThreadExceptionTest implements Runnable
	{
		public void run()
		{	
			for(int i=0;i<100;i++)
			{
				System.out.println(100/(i-20));
			}
		}
		public static void main(String[] args) 
		{
			ThreadExceptionTest tgt = new ThreadExceptionTest();
>			
			Thread t = new Thread(tgt);
			//set the exception dealer func 
			// the thread itself but not the threadgroup deal with its own but not all the exceptions for the thread
			t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
				public void uncaughtException(Thread t, Throwable e) 
				{
					System.out.println("The Error occure in the thread: "+t.currentThread().getName()+e.getMessage());
				}
			}); 
			t.start();
>			
			Thread t1 = new Thread(tgt);
			//set the exception dealer func 
			// the thread itself but not the threadgroup deal with its own but not all the exceptions for the thread
			t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
				public void uncaughtException(Thread t, Throwable e) 
				{
					System.out.println("The Error occure in the thread: "+t.currentThread().getName()+e.getMessage());
				}
			}); 
			t1.start();
>			
			//the thread dealing fun can also deal the exception came out of the main thread
			Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
				public void uncaughtException(Thread t, Throwable e) 
				{
					System.out.println("The Error occure in the thread: "+t.currentThread().getName()+e.getMessage());
				}
			}); 
			System.out.println(20/0);
		}
	}


方法3：使用`Thread`类的`static`处理方法,对所有`Thread`出现的`Thread Exception`进行处理
>
	public class ThreadDefaultExceptionTest implements Runnable
	{
		public void run()
		{	
			for(int i=0;i<100;i++)
			{
				System.out.println(100/(i-20));
			}
		}
		public static void main(String[] args) 
		{
			//set the exception dealer func 
			// the thread itself but not the threadgroup deal with all the exceptions for the threads
			Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){			
				public void uncaughtException(Thread t, Throwable e) 
				{
					System.out.println("The Error occure in the thread: "+t.currentThread().getName()+e.getMessage());
				}
			}); 
>			
			ThreadDefaultExceptionTest tgt = new ThreadDefaultExceptionTest();
			Thread t = new Thread(tgt);
			t.start();
>			
			Thread t1 = new Thread(tgt);
			t1.start();
>			
			//the thread dealing fun can also deal the exception came out of the main thread
			System.out.println(20/0);
		}
	}


线程池（`Pool`）  
池的本质，就是一种“缓存”技术。当某些东西创建的时候需要很大代价的时候，用完它不会立刻扔到，而是留下来，供以后使用。是否要缓存一个东西的决定点在于这个东西的创建成本，也要考虑系统的内存。  
缓存的本质是：以牺牲空间换时间。  
线程的创建成本比较大。虽然创建线程的成本比创建进程的成本小的多，但相比于普通的Java``对象，`Thread`对象的创建成本依然较大。  
为解决此问题，我们需要使用线程池。  
`JDK1.5`之后，提供了良好的线程池支持，依靠`Executors`类以实现线程池。这个类是创建线程池和线程工厂的工具类。 `ExecutorService`就是线程池类。`ScheduledExecutorService`也是线程池，其是`ExecutorService`的子接口，功能比`ExecutorService`强（可以周期性调用某一个线程）。  

线程池使用步骤：  
1. 使用`Executors`静态工厂工具类创建线程池对象(`ExecutorService`对象或者`ScheduledExecutorService`对象)。线程池对象里面放的是空闲的线程资源。  
2. 调用`ExecutorService`对象或者`ScheduledExecutorService`对象的方法来提交我们自己的线程(使用`submit()`方法或者`scheduleAtFixedRate()`方法)。线程提交之后并不会立即被启动，而是取决于线程池中是否有空闲的线程资源。  
3. 线程池没有结束，本程序就不会结束。要调用`shutdown()`方法结束线程池。  

注意这里的`ScheduledExecutorService`线程池可以让某一个线程每隔一定的时间就运行一次，这是一个周期性的运行，这一点可以运用到许多的事儿上。则个线程池的周期性事件的方法是`scheduleAtFixedRate()`方法。
而且使用线程池更简单。
 
工具类：`Arrays`、`Objects`、`Collections`、`Paths`、`Files`、`Executers`。  



怎样开不同的线程，各个线程中做不同的事？  
1. 只写一个线程类，一个线程类中只能有一个`run()`方法，然后用这一个线程类创建多个不同的线程，调用`start()`方法，其中的`run()`方法中进行判断，针对不同的线程执行不同的代码即可。  
2. 做几个不同的事就写几个线程类，每个线程类都有自己的`run()`方法，各个`run()`方法里就是各个线程要做的事。然后对各个线程类建立自己的对象，对各个对象调用`start()`方法。


匿名内部类可用于：  
1. 创建接口变量所引用的对象。  
2. 创建抽象类变量所引用的对象。  
3. 创建任何一个类变量所引用的其子类对象，即使没有写出它的子类的名字，这也就是匿名内部类的特点，父类的子类、接口的实现类、抽象类的实现类等都没有类名，而是直接创建了这些类的类对象。









