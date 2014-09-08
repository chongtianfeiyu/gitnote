#Java中的集合框架(容器类)
---------
集合框架是`Java`的一个子系统。  
`Java`中的容器用于装对象。  
那么这些容器中应该有哪些方法？  
1. 装入对象的方法。  
2. 把对象读取出来的方法。读取之后，这个对象依旧在容器当中。  
3. 把指定对象从容器中删除。  
4. 把整个容器清空。  
5. 遍历，将每一个对象都读取一次。  
6. 将A容器中的对象倒入到B容器。  
7. 从A容器中的所有对象中减去B容器中的所有对象。  
8. 容器之间求交集。

##容器类基本知识

*容器类*的所有方法都是通过实现其他的定义了各种方法名的**接口**(如、`Collection`接口)来达到目的。

定义了各种方法名的**接口**有：(见图：`Collection_Structure.png`)  
>
a. `Collection`接口。里面都是各种方法的声明的集合，没有定义方法，因为接口是极其抽象的，**里面都只有抽象方法**。  
>
b. `List`接口，**其继承自`Collection`接口**。使用条件：存放位置有索引，因此元素可以重复。类似于数组。被线性表实现（*顺序表或者链表*）。
>  
c. `Queue`接口，**也继承自`Collection`接口**。使用条件：先进先出。（*被栈、队列实现*）。
>    
d. `Set`接口，**其继承自`Collection`接口**，内容基本上和`Collection`一样。使用条件：存放位置无索引，但不是随机的，不允许存放元素重复。（*被集合实现*）。

实现了以上那些**接口的类**有：
>  
a. `List`接口被线性表实现，线性表可以基于*顺序表或者链表*。
所以，实现`List`接口的有`ArrayList`类（基于顺序表的实现）、`LinkedList`类（基于链表的实现）、`Vector`类（仅作了解，几乎无用了）。
>
b. `Queue`接口被队列实现，队列是特殊的线性表，也就是在继承线性表类之后加入某些特征（只能先入先出、进行功能上的削弱）获得队列。  
`Queue`派生了一个子接口`Deque`，这个子接口的使用条件：双端队列，两端都可进可出。也就是可以先进先出，也可以后进先出。  
其本质就是：既是栈也是队列。既有栈的方法也有队列的方法。在`Java`中看上去是没有栈这个东西，但实际上是有的，就是`Deque`。  
`Deque`接口的实现类：  
1. 基于顺序表的实现，`ArrayDeque`类。具有顺序表的功能，又有栈、队列的特点。  
2. 基于链表的实现，`Linked
3. `类，这个类既实现了`Deque`又实现了`List`，功能强大。
>
c. `HashSet`类：实现了`Set`接口的类。`HashSet`无序。其有一个子类：`LinkedHashSet`，这是有序的。  
另，对于`Set`接口而言，其还有一个子接口`SortedSet`，这个接口可用于需要排序的集合（也就是说，一旦一个类实现了这个`SortedSet`，那就具有了排序的功能，这个接口里面的功能就都有了）。  
如，`TreeSet`类实现了`SortedSet`这个接口。  
`Vector`类有一个子类`Stack`（仅作了解）。

以上这些实现类中，`ArrayList、LinkedList、ArrayDeque、HashSet、TreeSet`这5个类要重点掌握。

注意：  
1. 我们这里所使用的顺序表(数组)都是动态数组，也就是说可以动态扩充大小。  
2. 我们这里使用的链表都是双向链表，可以双向遍历。

##Java中的容器类的使用  

###容器类的常用操作
>
	Collection<String> c1 = new HashSet<>();

这里使用了接口`Collection`做引用变量，而没有使用类`HashSet`作引用变量。  
同时，这里的`Collection`使用泛型规定了容器中对象类型，那么后面的类`HashSet`就可以不必写出对象类型，这就是所谓的“菱形语法”。

`Removeall()`与`addall()`是针对容器与容器之间的操作。  
`Retainall()`是容器之间求交集的操作。  
遍历`Set`集合迭代器使用示例：
>
	Iterator it = c1.iterator();//创建迭代器
	while(it.hasNext())
	{
		System.out.println(it.next());//使用迭代器
	}

遍历`Set`集合使用foreach循环：
>
	for（String elem：c1）
	{
	}
###List接口的使用

`List`接口就是用于线性结构类的。  
其特征就是：可以依据元素的`index`来存、取元素对象。    
`List`接口中有大量的依据`index`来进行操作的方法。所谓的依据`index`进行操作就是：在操作的时候告诉你的是元素的`index`，就是编号。  
即使是链表，告诉你的也是`index`编号，这就是所谓的依据`index`进行操作。  
链表和顺序表都是告诉你元素编号的。

同样，这里的`List`接口一般都用来创建引用变量，引用它的子类实例，以此体现多态。  
但是要注意的一点就是，如果使用接口创建的引用变量来引用子类实例，那么在要使用子类才有的方法之前，必须要先进行强制类型转换为子类实例变量。  

由于可以依据`index`访问元素，所以其多了一种遍历方式：
>
	List<String> ls = new ArrayList<>();
	for(int i=0;i<ls.size();i++)
	{
		System.out.println(ls.get(i));
	}

####`ArrayList`实现类  
这个类实现了`List`接口，底层是一个数组。  
####`Vector`实现类  
这个类和`ArrayList`相似，底层都是一个数组。这二者的性能非常快，比`hashSet`还快。但只是在查找的时候快，在删除、插入的时候很慢。  
####`LinkedList`实现类  
特征：既是线性表、又是队列、还是栈。基于链表实现。  
其底层是基于链表实现的。**通常**认为性能比不上`ArrayList`。在存取的时候性能很好，在查找的时候很慢。因为它也是根据索引进行操作的，也就是说，在操作的时候告诉`LinkedList`的依旧是`index`。
####`ArrayList`与 `Vector`的区别:  
`Vector`从**JDK1.4**被`ArrayList`代替，所以，能不用`Vector`就不用。`Vector`是线程安全的（但是实现很糟糕），`ArrayList`是线程不安全的。`ArrayList`的性能更好。即使在多线程情况下也不要使用`Vector`，而是通过`Collections`工具类来将`ArrayList`转换包装为线程安全的。    

###Queue接口的使用
`queue`接口唯一的继承接口是`Deque`。  
功能：既是队列(`queue`)又是栈(`stack`)。两端都可出可入。实际上就是功能受限制的线性表。实际上就是继承线性表而来的。

####`Deque`接口作栈使用
使用示例：
>
	import java.util.*;
	public class dequeStackTest
	{
		public static void main(String[] args)
		{
			Deque<String> de = new ArrayDeque<>();
>
			//push the elems into the ArrayDeque
			de.push("hi");
			de.push("hello");
			de.push("world");
			de.push("that");
			de.push("is");
>
			//pop the elems out of the ArrayDeque
			System.out.println(de.pop());
			System.out.println(de.pop());
			System.out.println(de.pop());
			System.out.println(de.pop());
			System.out.println(de.pop());
>	
			//push the elems
			de.push("hi");
			de.push("hello");
			de.push("world");
			de.push("that");
			de.push("is");
>		
			//peek the elems of the ArrayDeque, peek but not remove
			System.out.println(de.peek());
			de.pop();
			System.out.println(de.peek());
			de.pop();
			System.out.println(de.peek());
			de.pop();
			System.out.println(de.peek());
			de.pop();
			System.out.println(de.peek());
			de.pop();
		}
	}

####`Deque`作队列使用  
使用示例：
>
	import java.util.*;
	public class dequeQueueTest
	{
		public static void main(String[] args)
		{
			Deque<String> de = new ArrayDeque<>(); 
>			
			//insert at the end of the arraydeque.
			de.offer("hello");
			de.offer("world");
			de.offer("good");
>
			//poll the elem 
			System.out.println(de.poll());
>
			//peek the elem
			System.out.println(de.peek());
		}
	}

`Deque`具有两个子类：  
1. 基于数组的实现，`ArrayQueue`  
2. 基于链表的实现，`LinkedList`  
这两个子类都具有栈和队列的功能，即使它们是基于不同的实现。  
`Deque`只是一个接口，所有的方法都是依靠实现类：`ArrayQueue`与`LinkedList`实现。

###Set接口的使用
####HashSet实现类
`HashSet`的存储机制：实际上其底层是一个数组。  
`HashSet`的构造器：`HashSet(int initialCapacity, float loadFactor)`。  
这里的参数含义分别是：  
a. `initialCapacity`：数组的长度，但是不一定是实际数组的长度，因为这里的数组的长度永远是2的n次方，这样数组实际的长度是最接近与2的n次方的。  
>
	HashSet<String> ha = new HashSet<>(3);//底层数组长度自动扩展到4。

实际上`HashSet`的工作原理是：当感觉底层数组的空间不足的时候，就会自动创建一个长度扩充一倍的底层数组，将原数组中的东西复制倒入，原数组变为垃圾等待回收。这个过程叫**重Hash**。

b. loadFactor：负载因子。  
那么怎样的时候才叫感觉空间不足？  
就是当已使用的空间占总空间比例达到负载因子的时候。负载因子是一个小数。默认0.75，底层数组长度默认16；

`HashSet`存入元素的机制：  
1. 当有对象元素要存入的时候，会调用这个**对象元素**的`HashCode()`方法，获得一个`hash`值。  
2. 根据返回的`hash`值，计算出这个对象元素在底层数组中的存储位置（也就是数组中的索引）。`Hash`值一样则存储位置一样。  
3. 如果要放入的位置是空的，那么直接放入即可。  
4. 如果该位置上已经有了元素，就会调用该元素的`equals()`方法，判断该两个元素是否相等，如果相等，就会抛弃这个相等的元素。如果`equals()`方法判断不相等，就会在此位置上形成链表，同一个位置上的元素形成链表。
所以，这就是为什么`hashset`中的元素要重写`equals()`方法与`hashcode()`方法。  
`HashSet`取出元素的机制：  
1. 当要从`HashSet`中取出一个对象元素的时候，会调用这个对象元素的`HashCode()`方法，获得一个`hash`值。  
2. 根据返回的`hash`值，得出这个对象元素在底层数组中的存储位置。  
3. 如果该位置恰好是要找的元素，直接取出来即可。  
4. 如果这个位置是一个链表，就需要挨个遍历这个链表，直到找到我们要的元素对象。无链表的时候，效率最高。

最理想的情况下，`HashSet`的性能可以接近于数组。  
这里可以知道，当负载参数过大的时候，会导致`HashSet`被装得过满，这样导致大量数组元素位置相重，进而导致链表的出现。降低`HashSet`的性能。  
注：当我们要将一个对象元素存入到`Hash`表中的时候（`HashSet/HashMap`），需要在这个类的对象中重写`HashCode()`方法，因为存入或者取出这个对象元素的时候，都要使用它的`HashCode()`方法  
`HashSet`类使用示例：
>
    //the use of Hashcode() in the HashSet
    //the override of equals()
    import java.util.*;
    class A
    {
	    private int count;
		private String name;
		public A(){}
    	public A(int acount,String aname)
	    {
		    this.count=acount;
			this.name=aname;
	    }
    	//类的判等equals()重写。所谓的equals()判断的是这两个对象的内部Field是否相等。
	    public boolean equals(Object obj)
	    {
		    if(this==obj)
		    {
			    return true;
		    }
		    if(obj!=null&&obj.getClass()==A.class)
		    {
			    A targetobj=(A)obj;
			    if(this.count==targetobj.count&&this.name.equals(targetobj.name))
			    {
				    return true;
			    }
		    }
		    return false;
    	}
	    public int f()
    	{
    		return this.count;
    	}
		//the hashCode() method returns the same value that all the A object stored int the same position
    	public int hashCode()
    	{
    		return 20;
    	}
    }
    public class hashSetTest2
    {
	    public static void main(String[] args)
	    {
	    	HashSet<A> ha = new HashSet<>();
	    	ha.add(new A(1,"wt"));
	    	ha.add(new A(1,"wt"));
	    	ha.add(new A(1,"wt"));
    		System.out.println(ha);
	    	for(A a:ha)
	    	{
	    		System.out.println(a.f());
    		}
    	}
    }

`HashSet`怎样才认为两个对象能够放在底层数组中相同的位置？ 
> 
1. 两个对象通过`equals()`方法判等返回`true`。因为如果对象本身都不相等，那么就没有放在相同位置的必要了。  
2. 这两个对象的`hashCode()`返回值相等。也就是要求两个对象返回的存储位置是一样的。  

这样就要求我们类的自定义方法`hashCode()`与`equals()`是一致的。  
这里的一致，指的是必须在这两个方法中要考虑相同多的类属性。  
那就是：
>
在`equals()`方法中要保证`name`与`num`属性都相等才行，那么在`hashCode()`方法中也必须使用这两个属性来求出`hash`值，从而保证返回的`hash`值能够代表这个两个属性。俱缺一不可。  

也就是说，对于我们自己写的类对象，如果要存入`HashSet`中，那么就要正确重写`equals()`方法与`hashCode()`方法。  
但是对于已经在**JDK**中有的类，我们就不必自己重写这两个方法。因为**JDK**中的类继c承自`Object`类，都已经实现了这两个方法。  

因此，这两个方法相对于上面的必须改写为：
>
    public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		if(obj!=null&&obj.getClass()==A.class)
		{
			A targetobj=(A)obj;
			if(this.count==targetobj.count&&this.name.equals(targetobj.name))
        	{
    	        return true;
    	    }
    	}
    	return false;
    }
	//the override of hashcode(),use the two all Fields
    public int hashCode()
    {
    	return this.name.hashCode()+this.count;
    }
	public class hashSetTest2
	{
		public static void main(String[] args)
		{
			HashSet<A> ha = new HashSet<>();
			ha.add(new A(1,"lio"));
			ha.add(new A(1,"lio"));
			ha.add(new A(1,"lio"));
>	
			System.out.println(ha);
			for(A a:ha)
			{
				System.out.println(a.f());
			}
		}
	}

`HashSet`的使用示例：
>
	import java.util.*;
	class apple
	{
		String color;
		double weight;
		public apple()
		{
			this.color = "red";
			this.weight = 10.0;
		}
		public apple(String acolor,double aweight)
		{
			this.color = acolor;
			this.weight = aweight;
		}
		//the override of toString()
		@Override
		public String toString()
		{
			return "apple[color:"+this.color+"weight:"+this.weight+"]";
		}
		//the override of equals()
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if(obj!=null&&obj.getClass()==apple.class)
			{
				apple app = (apple)obj;
				if((this.weight==app.weight)&&(this.color.equals(app.color)))
				{
					return true;
				}
			}
			return false;
		}
		//the override of hashcode(),use the two all Fields
		@Override
		public int hashCode()
		{
			return color.hashCode()+(int)weight;
		}
	}
	public class hashSetTest
	{
		public static void main(String[] args)
		{
			//the use of LinkedHashSet
			//class String has it's own equals() and hashcode() method.
			LinkedHashSet<String> hp = new LinkedHashSet<>();
			hp.add("张三");
			hp.add("李四");
			hp.add("王五");
			hp.add("赵六");
			System.out.println(hp);
>	
			//the use of HashSet
			//class apple do not has it's own equals() and hashcode() method.
			HashSet<apple> hp1 = new HashSet<>();
			hp1.add(new apple("green",4.3));
			hp1.add(new apple("blue",6.7));
			hp1.add(new apple("black",5.3));
>	
			System.out.println(hp1);
		}
	}

`HashSet`的子类:`LinkedHashSet`  
其存储机制与`HashSet`类似，但是，`HashSet`的底层机制是一个数组(顺序表），而且数据存放位置是一个无顺序的，也就是与放入时顺序无关的，这样，遍历整个`Hashset`的时候就是无序的。  
而`LinkedHashSet`的底层机制是一个链表，这样，在链表中顺序就和放入对象的顺序是一致的，这样在遍历`linkedHashSet`的时候，就是有序的，与放入顺序一样。

####TreeSet实现类
特征：保证`Set`中的元素对象都是已经排好序的，排序的目的在于便于查找、检索。  
对于字符串，按首字母的`ASCII`码排序。  

`TreeSet`是标准的“红黑树”；  
树——>二叉树——>排序二叉树(左子节点小于`root`节点小于右子节点，也叫二叉搜索树）——>平衡排序二叉树(AVL树：任何节点的树高度差不大于1且是排序二叉树，保证整棵树的权值最小)——>红黑树。  

树：**二叉树**（普通二叉树、排序二叉树、AVL树（平衡排序二叉树））、**红黑树**、**扩展树**、**B树**。  
其中，二叉树、红黑树、扩展树是用于内存中元素的管理。`B`树是用于外存（例如硬盘中数据元素的管理的）。

`TreeSet`的底层用一棵红黑树。红黑树的存入、检索性能很好。  
在没有出现大量的“链表（位置重合、重hash）”的情况下，`HashSet`的性能比`TreeSet`的好。而`TreeSet`不受此影响。

`TreeSet`相对于`HashSet`的优点： 
> 
1. `HashSet`中有一定量的空间是空的（内存的浪费），而`TreeSet`的空间利用率要高。  
2. `TreeSet`可以保证集合元素是有序的。

由于`TreeSet`中元素是有序的，所以要求放入到`TreeSet`中的元素对象是可以比较大小的。

`TreeSet`在比较中认为两个元素对象相等的标准：  
只要这两个对象通过`compareTo()`方法返回值是0，那么`TreeSet`就认为两对象相等。而与`equals()`方法无关（只与`compareTo()`方法有关）。所以我们要重写放入`TreeSet`中的元素对象的所属类中`compareTo()`方法。这个方法是`comparable`接口中的方法，所以，对于我们自己所写的类，必须要实现这个接口中这个唯一的这个方法。如果是**JDK**已有的类，我们就不必去自己实现这个方法了，因为已有的类已经实现了这个方法。 

`TreeSet`容器类中比较元素对象大小的两种方式：  
>
1. 自然排序：  
如果在`TreeSet`容器中存放的是**JDK**中已经存在的类对象，由于**JDK**中存在的类已经实现了`compareTo()`方法，不用我们去重写这个方法。这个就是所谓的**自然排序**。  
也就是：我们自己放入`TreeSet`容器中的类对象自己已经实现`Comparable`接口。也就是已经重写了里面的`compareTo()`这个方法。这个方法被自动用于元素大小比较和排序。无需我们自己再次重写。  
元素实现了这个接口之后，元素自身就是可以进行排序的。  
2. 定制排序：对于我们自己写的类，需要自己去实现这个`compareTo()`方法或者我们对于**JDK**中已有的类希望按照自己想要的方式继续排序，就需要在创建`TreeSet`的时候要传入一个`Comparator`对象，这就是所谓的**定制排序**。  
在创建`TreeSet`的时候，提供一个`Comparator`对象，该对象可负责对元素进行比较大小。实际上，这里集合中元素对象无需实现`comparable`接口（本身无排序功能）而是在使`TreeSet`就需要在构造的时候传入一个`Comparator`对象，由于`Comparator`实际上是一个接口。所以传入的是一个`Comparator`匿名内部类对象。通过这个匿名类产生的对象用于进行比较。

`TreeSet`类使用示例
>
	import java.util.*;
	//no need to implements the interface comparable' func compareTo() but transmit an anonymous object Comparator which override the func compare() into the TreeSet
	class apple 
	{
		String color;
		double weight;
		public apple()
		{
			this.color = "red";
			this.weight = 10.0;
		}
		public apple(String acolor,double aweight)
		{
			this.color = acolor;
			this.weight = aweight;
		}
		//the override of toString()
		@Override
		public String toString()
		{
			return "apple[color:"+this.color+"weight:"+this.weight+"]";
		}
		//the override of equals()
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if(obj!=null&&obj.getClass()==apple.class)
			{
				apple app = (apple)obj;
				if((this.weight==app.weight)&&(this.color.equals(app.color)))
				{
					return true;
				}
			}
			return false;
		}
		//the override of hashcode()
		@Override
		public int hashCode()
		{
			return color.hashCode()+(int)weight;
		}
	}
	public class treeSetTest
	{
		public static void main(String[] args)
		{
			//use TreeSet class
			//class String has implements the interface comparable itself that we do not have to implement it by ourselves.
			TreeSet<String> ts = new TreeSet<>();
			ts.add("张三");
			ts.add("李四");
			ts.add("王五");
			ts.add("赵六");
			System.out.println(ts);
>	
			//use TreeSet class
			//class apple do not has implements the interface comparator itself that we must implement it by ourselves。In this sample，we use anonymous class to override the func of compare() in the interface Compatator.
			TreeSet<apple> ts1 = new TreeSet<>(new Comparator<apple>(){
			//use anonymous class to override the func compare() in the interface Comparator to provide a Comparator object for TreeSet.
			@Override
			public int compare(apple o1,apple o2)
			{
				if(o1.weight>o2.weight)
				{
					return 1;
				}
>		
				else if(o1.weight<o2.weight)
				{
					return -1;
				}
>	
				else
				{
					return 0;
				}
			}
			});
>	
			ts1.add(new apple("green",4.3));
			ts1.add(new apple("blue",6.7));
			ts1.add(new apple("black",5.3));
			System.out.println(ts1);
>	
			//use TreeSet class
			//class String has implements the interface comparator itself but we want it compare as the method we want 
			//that we implement it by ourselves.In this sample，we use anonymous class to override the func of compare() in the interface Compatator.
			TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>(){
			//use anonymous class to override the func compare() in the interface Comparator to provide a Comparator object for TreeSet.
			@Override
			public int compare(String o1,String o2)
			{
				if(o1.length()>o2.length())
				{
					return 1;
				}
>		
				else if(o1.length()<o2.length())
				{
					return -1;
				}
>	
				else
				{
					return 0;
				}
			}
			});
			ts2.add("green");
			ts2.add("blue");
			ts2.add("esddfa");
			System.out.println(ts2);
		}
	}




##操作Collection的工具类Collections
常用工具类：  
`Arrays`类：操作数组。  
`Objects`类：操作对象。  
`Collections`类：操作集合。  
`Integer`类：操作将`String`转换为`Integer`。

当我们在已有的这个类中没有找到所需要的方法的时候，要达到我们的目的，就需要自己去写这个方法，这个时候要注意把这个方法包装放在一个类中，作为`static`方法出现，这样就产生了一个工具类，这样才是良好的面向对象方法。  
例如：
我们有一个`String`数组，需要依据数组的元素去确定这个元素的`index`，这个时候，发现工具类`Arrays`中并没有这种方法。  
所以需要自己去创建这个方法`int find(String[] strArrays,String str)`，要将它作为`static`方法包装在一个类中。
> 
	//find the index of the elem in the array 
	class arraysUtil
	{
		static int find(String[] strArrays,String str)
		{
			int len = strArrays.length;
			int i=0;
			for(;i<len;i++)
			{
				if(str.equals(strArrays[i])==true)
				{
					return i;
				}
			}
			return -1;
		}
	}

这样我们就创建了一个工具类`arraysUtil`类，以后可以直接使用这个类的`static`方法来查找字符串数组中的元素的`index`。
`Collections`工具类中所有方法都是`static`方法。
>
	import java.util.*;
	public class collectiosTest
	{
		public static void main(String[] args)
		{
			List<String> ls = new ArrayList<>();
>			
			ls.add("hi");
			ls.add("hello");
			ls.add("good");
			System.out.println(ls);
>
			//reverse the List
			Collections.reverse(ls);
			System.out.println(ls);
>
			//swap the specific elems in the list
			Collections.swap(ls,1,2);
			System.out.println(ls);
>
			//shuffle the elems in the list
			Collections.shuffle(ls);
			System.out.println(ls);
		}
	}


`synchronizedXXX(Collections<T> cl)`：用于将集合包装成线程安全的集合。

所谓的重构：就是让方法适用于更多的情况。

##Map接口的使用(见图：`Map_Structure.png`)
`Map`里面存放的东西是：很多的`key-value`对。每一项数据都是这样的键值对。也就是说，`Map`就是很多的`key-value`对的集合。
  
`Map`中将`value`当做`key`的附属物。在`key-value`的存储中，只需考虑`key`的存储即可。`key`存储之后，`value`会跟着`key`进行存储。  
如果，只考虑`key`的存储，那么将所有的`key`存储在一起，就是一个`Set`。 
 
实际上，`Map`与`Set`的一一对应的：
`HashSet`的底层是依靠`HashMap`实现的，`HashMap`依靠`hash`算法以确定在其底层数组中的位置。  
`TreeSet`的底层是依靠`TreeMap`实现的，`TreeSet`底层就是一个**红黑树**。
  
常用的实现了`Map`接口的类有3个：`HashMap`与`TreeMap`、`HashTable`。 
使用map.keySet:可以取出Map之中所有的key集合。  
使用map.entrySet:可以去除Map之中的所有键-值对的集合。  

### `HashMap`实现类  
会根据其存、取的元素对象的`key`值的`hashCode()`方法的返回值来确定这个`key`值的存放位置，也随之确定`key-value`对的储存位置。  

`HashMap`也不允许`key`重复。那么怎样才是`key`重复？这里和`HashSet`是一样的。  
- 通过`equals()`返回`true`.    
- 通过`hashCode()`返回值相等.   
那么，在我们用一个**自己写的类**作为`HashMap`的`key`的时候，必须要自己正确地重写这个类的`equals()`方法和`hashCode()`方法；  
但是对于已经在`JDK`中有的类，我们就不必自己重写这两个方法。因为`JDK`中的类继承自`Object`类，都已经实现了这两个方法。  
`HashMap`实际上是无序放置的，所以，为了有序存、取。可以使用`HashMap`的子类`LinkedHashMap`来进行存取。这样就是有序的。

`HashMap`的使用示例:
>
	import java.util.*;
	class apple
	{
		String color;
		double weight;
		public apple()
		{
			this.color = "red";
			this.weight = 10.0;
		}
		public apple(String acolor,double aweight)
		{
			this.color = acolor;
			this.weight = aweight;
		}
		//the override of toString()
		@Override
		public String toString()
		{
			return "apple[color:"+this.color+"weight:"+this.weight+"]";
		}
		//the override of equals()
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
>			
			if(obj!=null&&obj.getClass()==apple.class)
			{
				apple app = (apple)obj;
				if((this.weight==app.weight)&&(this.color.equals(app.color)))
				{
					return true;
				}
			}
			return false;
		}
		//the override of hashcode()
		@Override
		public int hashCode()
		{
			return color.hashCode()+(int)weight;
		}
	}
	public class hashmapTest
	{
		public static void main(String[] args)
		{
			//the use of LinkedHashMap
			//class String has it's own equals() and hashcode() method.
			LinkedHashMap<String,Integer> hp = new LinkedHashMap<>();
			hp.put("张三",90);
			hp.put("李四",80);
			hp.put("王五",70);
			hp.put("赵六",60);
			System.out.println(hp);
>	
			//the use of HashMap
			//class apple do not has it's own equals() and hashcode() method.
			HashMap<apple,Integer> hp1 = new HashMap<>();
			hp1.put(new apple("green",4.3),4);
			hp1.put(new apple("blue",6.7),5);
			hp1.put(new apple("black",5.3),6);
>	
			System.out.println(hp1);
		}
	}  

### `TreeMap`实现类  
其底层的红黑树只对`key`进行排序放置。这就表示`key`必须是可以比较大小的。  
- 自然排序：对于`key`都要实现`Comparable`接口。  
- 定制排序：要求在创建`TreeMap`的时候提供一个`Comparator`以实现比较。

`TreeMap`也不允许`key`重复。那么怎样才是`key`重复？  
- 只要这两个元素对象通过`compare()`方法返回值是0，那么`TreeSet`就认为二对象相等。而与`equals()`方法无关（只与`compare()`方法有关）。  
- 所以我们要重写放入`TreeSet`中的元素对象的所属类中`compareTo()`方法。这个方法是`comparable`接口中的方法，所以，对于我们自己所写的类，必须要实现这个接口中这个唯一的这个方法。

如果是**JDK**已有的类，我们就不必去自己实现这个方法了，因为已有的类以及实现了这个方法。这就是*自然排序*。  
 
对于我们自己写的类需要自己去实现这个`compare()`方法或者我们对于JDK中已有的类希望按照自己的方式去排序，就需要在创建`TreeMap`的使用要传入一个`Comparator`对象，这就是所谓的*定制排序*。  
也就是说在我们用一个**自己写的类**作为`TreeMap`的`key`的时候，必须要自己正确地使这个类实现`Comparator`接口的`compare()`方法；实际上采用的是匿名内部类的形式给`hashMap`提供一个`Comparator`对象。

`TreeMap`的使用示例：
>
	import java.util.*;
	class apple 
	{
		String color;
		double weight;
		public apple()
		{
			this.color = "red";
			this.weight = 10.0;
		}
		public apple(String acolor,double aweight)
		{
			this.color = acolor;
			this.weight = aweight;
		}
>	
		//the override of toString()
		@Override
		public String toString()
		{
			return "apple[color:"+this.color+"weight:"+this.weight+"]";
		}
		//the override of equals()
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
>			
			if(obj!=null&&obj.getClass()==apple.class)
			{
				apple app = (apple)obj;
				if((this.weight==app.weight)&&(this.color.equals(app.color)))
				{
					return true;
				}
			}
			return false;
		}
		//the override of hashcode()
		@Override
		public int hashCode()
		{
			return color.hashCode()+(int)weight;
		}
	}
	public class TreeMapTest
	{
		public static void main(String[] args)
		{
			//use TreeMap class
			//the key class String has implements the interface comparable itself that we do not have to implement it by ourselves.
			TreeMap<String, Integer> ts = new TreeMap<>();
			ts.put("张三",90);
			ts.put("李四",80);
			ts.put("王五",70);
			ts.put("赵六",60);
			System.out.println(ts);
>	
			//use TreeMap class
			//the ke class apple do not has implements the interface comparator itself that we must implement it by ourselves.In this sample，we use anonymous class to override the func of compare() in the interface Compatator.
			TreeMap<apple,Integer> ts1 = new TreeMap<>(new Comparator<apple>(){
			//use anonymous class to override the func compare() in the interface Comparator to provide a Comparator object for TreeMap.
			@Override
			public int compare(apple o1,apple o2)
			{
				if(o1.weight>o2.weight)
				{
					return 1;
				}
>		
				else if(o1.weight<o2.weight)
				{
					return -1;
				}
>	
				else
				{
					return 0;
				}
			}
			});
>	
			ts1.put(new apple("green",4.3),4);
			ts1.put(new apple("blue",6.7),5);
			ts1.put(new apple("black",5.3),6);
			System.out.println(ts1);
>	
			//use TreeMap class
			//class String has implements the interface comparator itself but we want it compare as the method we want 
			//that we implement it by ourselves.In this sample，we use anonymous class to override the func of compare() in the interface Compatator.
			TreeMap<String,Integer> ts2 = new TreeMap<>(new Comparator<String>(){
			@Override
			//use anonymous class to override the func compare() in the interface Comparator to provide a Comparator object for TreeMap.
			public int compare(String o1,String o2)
			{
				if(o1.length()>o2.length())
				{
					return 1;
				}
>		
				else if(o1.length()<o2.length())
				{
					return -1;
				}
>	
				else
				{
					return 0;
				}
			}
			});
			ts2.put("green",4);
			ts2.put("blue",5);
			ts2.put("esddfa",6);
			System.out.println(ts2);
		}
	}

### `HashTable`  
`HashTable`与`HashMap`的区别：  
- `HashTable`自**JDK1.0**开始即有，已落后，能不用就不用。与`Vector`类似。    
- `HashTable`不允许使用`null`作`key`值，但是`HashMap`允许。  
- `HashTable`是线程安全的(但是实现不好),`HashMap`线程不安全，但是性能好。可以通过`Collections`工具类将`HashMap`类包装成线程安全的。所以尽量不用`HashTable`。  
- 除以上几点之外，其他的都是相同的。

总之，无论是`Set`还是`Map`。  
1. 前面带了`Hash`表明的是这个集合存、取元素寻找位置的方式是通过`equals()`方法和`hashCode()`方法的返回值来确定存、取元素在底层数组中的位置的。  
2. 前面带了`Tree`表明的是这个集合存、取元素的是在红黑树中进行的，这个排序过程是通过两种方式进行的，只要元素对象具有排序功能，就能够在红黑树中进行存、取。  
要么是对象本身就拥有比较功能，也就是**JDK**中已经提供了。要么就是我们自己写的类，需要在定义集合的时候向这个集合中通过匿名内部类的形式传入`Comparator`对象。


`Iterator`接口：用于遍历一个集合中的元素。

注：所谓的散列表，实际上就是指通过`hash`值来确定对象在数组中的储存位置的数组，如，`hashSet`、`hashtable`、`hashMap`都是属于散列表。一般散列表不是一个有序的方式，不过查找时间是一个常数。  
注：无论是数据库还是数据结构(集合)，所有对数据的操作不过是：*增、删、改、查*4种而已。




