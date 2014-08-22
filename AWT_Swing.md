Java中的界面编程
------------
`Java`在客户端上的表现并不突出，客户端程序主要集中在`Windows`平台上，`Java`主要集中在大型软件的服务器端编程。例如：电信、银行这些部门的软件，主要是B/S结构的，前端并不需要太复杂的实现。  

`Java`中界面组件进化历史：  
1. `AWT`(发布于JDK1.0)：一个`GUI`抽象窗口工具集，既然是抽象的，就并未为这个窗口工具集提供实现，而是调用了操作系统上的界面实现，以为了迎合操作系统的界面风格。因此AWT只能使用各个操作系统间界面组件的交集（例如：`Windows`下有按钮与图标，`linux`下有按钮与文本框，那么`AWT`中只能有按钮）所以`AWT`难以实现较好的界面。  
2. `Swing`发布，为绝大部分界面组件都提供了实现，所有的这些组件都是在黄将诶绘制在空白区域上。它自己实现了这些界面之间，所以其无需使用底层的系统组件，也就无需适配各个系统的交集。使得Swing的界面风格在各个系统中更加统一。


AWT:  
在`java.awt`包中，提供了基本的`Java`程序`GUI`设计工具：  
界面编程的本质步骤：  
1. 在创建添加界面组件之前，先创建一个容器。  
2. `new`一个界面组件。  
3. 把组件安在容器的某一个位置。

`AWT`界面组件  
界面组件设计的类结构图：如图所示。  
一切界面组件都是`Component`。  
一切的菜单组件都是`MenuComponent`。 

`Container`:容器，其本身既可以作为组件，也可以作为放置其他组件、容器的容器。
容器类对象可以使用方法`add()`来添加组件。
  
`Container`容器类的层次结构图：如图所示。  
继承Container类的有：`Window`（可独立存在）、`Panel`（不可独立存在，只能放在其他的`Container`中）、`ScrollPane`。  

继承`Window`的有`Frame`、`Dialog`。一般不直接使用`Window`而是使用`Frame`或者`Dialog`。  
`Frame`类：有标题，可通过拖拉改变大小。实际上就是代表一个窗口。

`Panel`容器：  
为放置组件提供空间。允许使用自己的布局管理器。不能单独存在，必须放置在其他的容器中。  

`AWT`中的布局管理器：  
布局管理器是一个非常有用的工具。如果不使用布局管理器，而是通过组件构造时的位置、大小来进行设置，那么会导致：  
1. 窗口大小改变的时候，窗口会变得难看。  
2. 平台改变的时候，窗口变得难以适应。

所谓的布局，就是组件位置与大小。使用布局管理器之后，更为简单，程序员无需显式控制组件的位置与大小，而是选择合适的布局管理器即可管理容器里面**各组件的位置布局**。  
布局管理器：  
1. `FlowLayout`：让组件在容器中横向排列，到顶就会自动折返到下一行。  
使用时被容器一次性调用`FlowLayout`，用于全局。  
2. `BorderLayout`：将容器分为五个区域，每个区域中都能放组件。各个区域会自动调整大小。如果某个区域没有组件，就会自动被别的区域占满。    
使用时容器一次一次为每一个组件调用`BorderLayout`的`Field`。每调用一次都只对这个组件有效果。  
但是并不表明每个区域只能放一个组件，可以使用`Panel`容器里放多个组件，然后将这个容器放在某一个区域即可。  
注意：任何一个布局管理器对象可以被任何一个容器使用，如，一个布局管理器既可以被一个`Frame`容器使用，也可以被一个`Panel`容器使用，然后可以使用这个布局管理器将这个`Panel`加到这个`Frame`容器中。  
3. `GridLayout`：将整个容器界面自定义划分成若干个块，然后对容器调用该布局管理器。即对全局的组件使用该布局管理器。   
4. `CardLayer`：将组件一个一个地叠加在一起。看到的只是最上面的那个组件。  
5. `GridBagLayout`：  
6. `BoxLayout`：是`Swing`里面的一个布局管理器。用法简单，功能强大。代替了`GridBagLayout`。要么是垂直方向排组件，要么横向排组件。   
7. 还可以不使用布局管理器：  
在`Frame`里设置布局管理器为`null`；然后对每一个组件都各自设置大小及位置。

注：内部类里访问局部变量，局部变量必须加上`Final`修饰。

`AWT`使用示例：
>	
	import java.awt.*;
	public class AWTFlowLayoutTest
	{
		public static void main(String[] args) throws Exception
		{
			FlowLayout fl = new FlowLayout(FlowLayout.CENTER,60,80);
>
			//construct a container
			Frame MainFrame = new Frame("hello");
			//set the size of the frame(container) and locate it on the postion you want
			MainFrame.setBounds(100,200,300,400);
>		
			//add the button with the default layout
			MainFrame.add(new Button("Yes"));
>
			//use the layout FlowLayout f1 for all the components
			MainFrame.setLayout(fl);
>		
			MainFrame.setVisible(true);
		}
	}

  
>
	import java.awt.*;
	public class AWTBorderLayoutTest
	{
		public static void main(String[] args) throws Exception
		{
			BorderLayout bl = new BorderLayout(10,10);
>		
			//construct a container
			Frame MainFrame = new Frame("hello");
			//set the size of the frame(container) and locate it on the postion you want
			MainFrame.setBounds(100,200,300,400);
>		
			//add new buttons with the layout b1
			MainFrame.add(new Button("East"),bl.EAST);
			MainFrame.add(new Button("West"),bl.WEST);
			MainFrame.add(new Button("North"),bl.NORTH);
			MainFrame.add(new Button("South"),bl.SOUTH); 
>		
			//create a panel with layout bl to contain two buttons
			Panel pl = new Panel(bl);
			pl.add(new Button("East"),bl.EAST);
			pl.add(new Button("West"),bl.WEST);
>
			//add the panel with layout bl
			MainFrame.add(pl,bl.CENTER);
>		
			MainFrame.setVisible(true);
		}
	}


>
	import java.awt.*;
	public class AWTGridLayoutTest
	{
		public static void main(String[] args) throws Exception
		{
			GridLayout gl = new GridLayout(2,2,5,5);
>		
			//construct a container
			Frame MainFrame = new Frame("hello");
			//set the size of the frame(container) and locate it on the postion you want
			MainFrame.setBounds(100,200,300,400);
>
			//set the layout gl
			MainFrame.setLayout(gl);
>
			//add buttons
			MainFrame.add(new Button("East"));
			MainFrame.add(new Button("West"));
			MainFrame.add(new Button("North"));
			MainFrame.add(new Button("South"));
			MainFrame.pack();
			MainFrame.setVisible(true);
		}
	}	



>
	import java.awt.*;
	public class AWTCardLayoutTest
	{
		public static void main(String[] args) throws Exception
		{
			CardLayout cl = new CardLayout(4,4);
>
			//construct a container
			Frame MainFrame = new Frame("hello");
			//set the size of the frame(container) and locate it on the postion you want
			MainFrame.setBounds(100,200,300,400);
>
			Panel pl = new Panel();
			//use the layout FlowLayout f1 for all the components
			pl.setLayout(cl);
>		
			//add the button with the default layout
			pl.add(new Button("East"));
			pl.add(new Button("West"));
			pl.add(new Button("North"));
			pl.add(new Button("South"));
>
			//cl.next(pl);
			MainFrame.add(pl,BorderLayout.NORTH);
>		
			Panel pl2 = new Panel();
			pl2.add(new Button("First"));
			pl2.add(new Button("Previous"));
			pl2.add(new Button("Next"));
			pl2.add(new Button("Last"));
>		
			MainFrame.add(pl2,BorderLayout.SOUTH);
			MainFrame.setVisible(true);
		}
	}



>
	import java.awt.*;
	import javax.swing.*;
	public class AWTBoxLayoutTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			Frame MainFrame = new Frame("hello");
			//set the size of the frame(container) and locate it on the postion you want
			MainFrame.setBounds(100,200,300,400);
>		
			//BoxLayout for MainFrame
			BoxLayout bl1 = new BoxLayout(MainFrame,BoxLayout.Y_AXIS);
			MainFrame.setLayout(bl1);
			MainFrame.add(new Button("1"));
			MainFrame.add(new Button("2"));
			MainFrame.add(new Button("3"));
>		
			Panel pl = new Panel();
			//BoxLayout for pl
			BoxLayout bl2 = new BoxLayout(pl,BoxLayout.X_AXIS);
			pl.setLayout(bl2);
			Panel pl1 = new Panel();
			//BoxLayout for pl1
			BoxLayout bl3 = new BoxLayout(pl1,BoxLayout.Y_AXIS);
			pl1.setLayout(bl3);
			pl1.add(new Button("4"));
			pl1.add(new Button("5"));
			pl.add(pl1);
			pl.add(new Button("6"));
>		
			MainFrame.add(pl);
>		
			MainFrame.setVisible(true);
		}
	}


Java中的Swing
-----------
由于`AWT`中的界面*组件*较少，所以导致界面不够美观。此时就需要使用`Swing`界面组件。  
`Java`中还有一套界面组件库，来自`IBM`的`SWT/JFaces`。
`Swing`功能更强大，使用更方便。  
`JComponent`：一切的`Swing`组件及`Swing`容器都属于（继承自）`JComponent`。`JFrame`、`JDialo`除外。  
由于`AWT`组件要调用底层系统的实现，所以被称为“重量级”组件。  
`Swing`组件是依靠自己实现，无需调用底层系统的实现，所以被称为“轻量级”组件。  

`JOptionPane`:是一个工具类，专用于弹出各种对话框。`static`方法有`showMessageDialog()`等。

`Swing`使用示例：
>
	import java.awt.*;
	import javax.swing.*;
	public class SwingTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>
			//JCheckBox
			MainFrame.add(new JCheckBox("good!"),bl.NORTH);
>		
			//JColorChooser
			JColorChooser.showDialog(MainFrame, "hi", Color.black); 
>		
			//JList
			String[] str = new String[]{"1","2","3"};
			MainFrame.add(new JList<String>(str),bl.SOUTH);
>		
			//JOptionPane
			System.out.println(JOptionPane.showInputDialog(null,"please input:"));
			MainFrame.setVisible(true);
		}
	}  

`JPanel`:类似与`Panel`。  
`JPasswordField`：密码输入框。
`JProgressBar`：进度条。  
`JRadioButton`：单选框。  
`JScrollBar`：滚动条。  
`JSplitPane`：分割面板。  
`JTabledPane`：窗口里面的`Tab`页面。  
`JTable`：表格。`JTable`必须放在`JScrollPane`中显示。  
`JToggleButton`：开关按钮。
`JToolBar`：做工具条。
`JToolBar.Separator`:工具条的分割线。  
`JToolTip`：工具条上的提示。

表格使用示例：
>
	import java.awt.*;
	import javax.swing.*;
	public class SwingTableTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>
			String[] str1 = new String[]{"Name","Age","Gender"};
			String[][] str2 = new String[][]{new String[]{"Jim","13","male"},new String[]{"Mary","15","female"},new String[]{"Jack","11","male"}};
>		
			JTable jt = new JTable(str2,str1);
			JScrollPane jsp = new JScrollPane(jt);
			MainFrame.add(jsp);
			MainFrame.setVisible(true);
		}
	}


工具条使用示例：
>
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	public class SwingToolBarTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>
			JToolBar jtb = new JToolBar("good", JToolBar.HORIZONTAL);
			 Action ac1 = new AbstractAction("yes") 
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("yes");
				}
			};
			 Action ac2 = new AbstractAction("no") 
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("no");
				}
			};
			 Action ac3 = new AbstractAction("cancel") 
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("cancel");
				}
			};
			jtb.add(ac1);  
			jtb.add(ac2);  
			jtb.addSeparator(); 
			jtb.add(ac3);  
>		
		 	String[] str1 = new String[]{"Name","Age","Gender"};
			String[][] str2 = new String[][]{new String[]{"Jim","13","male"},new String[]{"Mary","15","female"},new String[]{"Jack","11","male"}};
>		
			JTable jt = new JTable(str2,str1);
			JScrollPane jsp = new JScrollPane(jt); 
			MainFrame.add(jsp);
			MainFrame.add(jtb,BorderLayout.NORTH);
			MainFrame.setVisible(true);
		}
	}


`Window`容器有两个子类：`Frame`、`Dialog`。
`Swing`中容器：`JFrame`、`JDialog`。  
`JDialog`：本质上对话框也是一个窗口，区别在于对话框必须属于某一个窗口。对话框是一个容器，里面可以放各种组件。  
创建对话框需要指定的参数：  
1.`owner`  
2. `title`  
3. 是否`modal`：如果是模式对话框，那么该对话框出现的时候，其`ower`不能得到焦点。否则就是非模式对话框。

对话框使用示例：
>
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	public class SwingDialogTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>
			JButton jbu1 = new JButton("yes");
			JButton jbu2 = new JButton("no");
			JButton jbu3 = new JButton("cancel");
			MainFrame.setLayout(new FlowLayout());
			MainFrame.add(jbu1);
			MainFrame.add(jbu2);
			MainFrame.add(jbu3);
>		
			final JDialog jd1 = new JDialog(MainFrame,"yes",true);
			final JDialog jd2 = new JDialog(MainFrame,"no",false);
			final JDialog jd3 = new JDialog(MainFrame,"yes",true);
>		
			//add button action react for button1 with anonymous class
			jbu1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("yes");
					//show the dialog
					jd1.setVisible(true);
				}
			});
>		
			//add button action react listener for button2 with anonymous class
			jbu2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("no");
					//show the dialog
					jd2.setVisible(true);
				}
			});
>		
			//add button action react for button3 with anonymous class
			jbu3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("cancel");
					//show the dialog
					jd3.setVisible(true);
				}
			});
>		
			MainFrame.setVisible(true);
		}
	}


还有两个特殊的对话框：
`JColorChooser`、`JFileChooser`:颜色选择对话框与文件选择对话框。

文件选择对话框使用示例：
>  
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	import java.nio.file.*;
	import java.io.*;
	public class SwingFileDialogTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			final JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>
			JButton jbu3 = new JButton("Choose");
>	
			MainFrame.add(jbu3,BorderLayout.SOUTH);
>		
			//add button action react for button3
			jbu3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 			{
					try
					{
						SwingDialogTest.readFile(MainFrame);
					}
					catch(Exception m)
					{
						System.out.println("Error");
					}
				}
			});
			MainFrame.setVisible(true);
		}
>	
		static void readFile(Component c) throws Exception
		{
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(c);
			File f = jfc.getSelectedFile();
>		
		 	if(f!=null)
			{ 
				//read the file with notepad
				Runtime rt = Runtime.getRuntime();
				rt.exec("notepad "+f.getPath());
>		
			 	//read the line in the CMD line
				InputStreamReader isr = new InputStreamReader(new FileInputStream(f)); 
				BufferedReader br = new BufferedReader(isr);
				String tempstr = null;
				while((tempstr=br.readLine())!=null)
				{
					System.out.println(tempstr);
				}  
>		
				//read the line in the textarea
				JTextArea jtp = new JTextArea();
				JFrame jf = (JFrame)c;
				jf.add(new JScrollPane(jtp));
>	
				InputStreamReader isr1 = new InputStreamReader(new FileInputStream(f)); 
				BufferedReader br1 = new BufferedReader(isr1);
				String tempstr1 = null;
				while((tempstr1=br1.readLine())!=null)
				{
					jtp.append(tempstr1+"\n"); 
				}  
			}
		}
	}


注：怎样为一个抽象类创建对象？  
使用匿名内部类即可，使用内部类创建一个该抽象类的子类的匿名对象。然后让这个抽象类引用变量去引用这个子类的匿名对象。  

Java中的事件编程
-----------
针对的就是程序与人的交互---通过鼠标、键盘触发事件。
Java的事件处理模式：委托式的事件处理。
委托式事件处理模型：  
1. 在这种处理模型之下，事件源（如按钮）发生事件（点击）的时候，事件源不处理事件。  
2. 事件源会发出事件（`Event`）给事件监听器（`Listener`）：也就是发送了事件发生点等关于该事件的详细信息。  
3. 事件监听器（`Listener`）获得事件信息（`Event`对象），只能依据事件信息对象（`Event`）对事件进行反应处理。  
4. 事件监听器都要实现一个特定的方法。


事件源：一切组件都可能是事件源。
  
事件：无需程序员理会，事件发送过程也无需理会。
  
监听器：需要程序员实现，通过匿名内部类获得一个监听器的对象。需要实现相应的接口。  
**对于不同的事件，有不同的监听器。**需要为事件源上发生的不同的事件注册与之对应的事件监听器，也就是说一个事件源可以被多个事件监听器监听，一个事件监听器也可以监听多个事件源。  
事件编程的重点就是实现事件处理的方法。  
可以简化为匿名内部类。经常使用的是匿名内部类。
单击事件：`ActionEvent`。

下例中使用了*内部类*，还可以使用*匿名内部类*。
单击监听器： `ActionListener`
单击事件处理示例：
>
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	public class SwingActionlistenerTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>		
			//create your own actionlintener for click and implements the reaction func
			class MyEventListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e) 
				{
					System.out.println(e.getActionCommand());				
					System.out.println(e.getWhen());
				}
			}
>		
			JButton bu1 = new JButton("Yes");
			JButton bu2 = new JButton("No");
			//add the actionlistener for your buttons
			bu1.addActionListener(new MyEventListener());
			bu2.addActionListener(new MyEventListener());
>		
			MainFrame.add(bu1,BorderLayout.NORTH);
			MainFrame.add(bu2,BorderLayout.SOUTH);
			MainFrame.setVisible(true);
		}
	}

注：`Event`类是属于`AWT`的。
IM：无论是我们通过`implements`来实现监听器接口获得监听器对象还是通过匿名内部类来获得监听器对象，都需要实现监听器接口中的**所有抽象方法**，即使有些只是空实现。
这样就导致，当监听器接口里的方法过多的时候，需要程序员实现这个接口里面的所有方法，导致监听器过于臃肿。  
于是就出现了适配器，适配器是监听器的实现类。它为监听器接口提供了通实现。也即是说，采用适配器之后，我们不需要的接口里的方法可以不用自己去实现，只用实现自己想要实现的方法。  
对于方法很多的事件监听器接口，都会为它提供对应的事件适配器。 

窗口监听器：
对任何一个窗口（`Frame`或者`Dialog`都适用)监听。

窗口监听器使用示例：
>
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	public class SwingWindowActionListenerTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			final JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>
			//add windowlistener
			MainFrame.addWindowListener(new WindowListener()
			{
				//implements the window funcs all	
				public void windowClosing(WindowEvent e) 			
				{
					try
					{
						System.out.println("hi");
						int result = JOptionPane.showConfirmDialog(MainFrame, 	"yes?"); 
						if(result==0)
						{	 
							System.exit(0);
						}
					}
					catch(Exception d)
					{
						System.out.println("error");
					}
				}
				public void windowActivated(WindowEvent e) 
				{}
				public void windowClosed(WindowEvent e) 
				{}
>			
				public void windowDeactivated(WindowEvent e) 
				{}
				public void windowDeiconified(WindowEvent e) 
				{}
				public void windowIconified(WindowEvent e) 
				{}
				public void windowOpened(WindowEvent e) 
				{}
			});
			MainFrame.setVisible(true);
		}
	}

窗口适配器使用示例：
>
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	public class SwingWindowActionAdapterTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			final JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			//MainFrame.setLayout(new FlowLayout(10,10,10));
			BorderLayout bl = new BorderLayout(40,40);
>	
			//add windowlistener
			MainFrame.addWindowListener(new WindowAdapter()
			{
				//implements the window close raction only the funcs you want
				public void windowClosing(WindowEvent e) 			
					{
					try
					{
						System.out.println("hi");
						int result = JOptionPane.showConfirmDialog(MainFrame, "yes?"); 
						if(result==0)
						{	 
							System.exit(0);
						}
					}
					catch(Exception d)
					{
						System.out.println("error");
					}
				}
			});
			MainFrame.setVisible(true);
		}
	}


各种事件与接口对应：  
1. `Action`：`ActionListener`：单击事件  
2. `Item`：`ItemListener`：  
3. `Mouse Motion`：`MouseMotionListener`：鼠标动作事件  
4. `Mouse`：`MouseListener`：鼠标按键事件  
5. `Key`：`KeyListener`：按键事件  
6. `Focus`：`FocusListener`：焦点事件  
7. `Text`:`TextListener`:文本框、文本域事件


Java中的菜单
-----------
`AWT`菜单：`MenuComponent`,一切菜单组件都是`MenuComponent`。见类结构图。  
`MenuBar`：就是一整个工具条。包含多个`Menu`，`Menu`包含多个`MenuItem`。

`Swing`菜单：比`AWT`菜单功能更强大。比如，Swing菜单支持图标菜单。  
`JMenuBar`:工具条。包含多个JMenu，JMenu包含多个`JMenuItem`。  
由于`JMenu`继承自`JMenuItem`，所以，`JMenu`可以当`JMenuItem`使用。  
`JFrame`使用`setJMenuBar(JMenuBar menubar)`来安装菜单工具条。
`JMenu`可以`add(JMenuItem j)`也可以`add(JMenu j)`,这样就可以产生二级菜单,也就是说，`JMenu`可以当成`JMenuItem`使用.  

同样可以为菜单项（MenuItem）添加监控器。  

菜单使用示例：

>
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	public class SwingJMenuTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			BorderLayout bl = new BorderLayout(40,40);
>	
			//construct the menubar
			JMenuBar jmb = new JMenuBar();
>		
			//construct the menus for the menubar	
			JMenu jm1 = new JMenu("Files",true);
			JMenu jm2 = new JMenu("Edit",true);
			JMenu jm3 = new JMenu("Search",true);
			JMenu jm4 = new JMenu("Runs",true);
			JMenu jm5 = new JMenu("Language",true);
			jmb.add(jm1);
			jmb.add(jm2);
			jmb.add(jm3);
			jmb.add(jm4);
			jmb.add(jm5);
>			
			//construct the menuitems for the menu
			JMenuItem jmi1 = new JMenuItem("1");
			JMenuItem jmi2 = new JMenuItem("2");
			JMenu jmi3 = new JMenu("3");
			JMenuItem jmi4 = new JMenuItem("4");
			jmi1.setAccelerator(KeyStroke.getKeyStroke('V',InputEvent.CTRL_DOWN_MASK)); 
			jm1.add(jmi1); 
			jm1.add(jmi2);
			//add separator in the menu
			jm1.addSeparator(); 
			jm1.add(jmi3); 
			jmi3.add(jmi4); 
>		
			//add ActionListener for the menuitem
			jmi1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("hi");
				}
			});
			//add the menubar onto the frame
			MainFrame.setJMenuBar(jmb);
			MainFrame.setVisible(true);
		}
	}

为文本域添加监控器：文本域监控器来自`javax.swing.event.*`包。  
使用`DocumentListener`来监听文本框内容的改变。

文本域监控使用示例：

>
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	import javax.swing.event.*;
	public class SwingJMenuTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a container
			JFrame MainFrame = new JFrame("hello");
			MainFrame.setBounds(100,200,300,400);
			BorderLayout bl = new BorderLayout(40,40);
>	
			//construct the menubar
			JMenuBar jmb = new JMenuBar();
>		
			//construct the menus for the menubar	
			JMenu jm1 = new JMenu("New",true);
			JMenu jm2 = new JMenu("Save",true);
			JMenu jm3 = new JMenu("Search",true);
			JMenu jm4 = new JMenu("Runs",true);
			JMenu jm5 = new JMenu("Language",true);
			jmb.add(jm1);
			jmb.add(jm2);
			jmb.add(jm3);
			jmb.add(jm4);
			jmb.add(jm5);
>		
			//construct the menuitems for the menu
			JMenuItem jmi1 = new JMenuItem("1");
			JMenuItem jmi2 = new JMenuItem("2");
			JMenu jmi3 = new JMenu("3");
			JMenuItem jmi4 = new JMenuItem("4");
			jmi1.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK)); 
			jmi2.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK)); 
			jm1.add(jmi1); 
			jm1.add(jmi2);
			//add separator in the menu
			jm1.addSeparator(); 
			jm1.add(jmi3); 
			jmi3.add(jmi4); 
>		
			final JTextArea jta = new JTextArea(60,80);
			//add ActionListener for the menuitem
			jmi1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("hi");
				}
			});
			   boolean isModified = false;
			jta.getDocument().addDocumentListener(new DocumentListener(){
>			
				public void changedUpdate(DocumentEvent e) 
				{
				}
				public void insertUpdate(DocumentEvent e) 
				{
					System.out.println("insert");
				}
				public void removeUpdate(DocumentEvent e) 
				{
					System.out.println("remove");
				}
			});
>		
			MainFrame.add(jta,BorderLayout.CENTER);
			//add the menubar onto the frame
			MainFrame.setJMenuBar(jmb);
			MainFrame.setVisible(true);
		}
	}	


上、下文菜单：也就是右键菜单。  
`JPopupMenu`:是整个上、下文菜单的容器。

画图：  
前面的组件是`Swing`帮我们完成的。实际上那些组件都是JDK的开发者帮我们画上去的。  
如果想要界面组件更多，就需要自己来绘制。
  
`AWT`绘图：  
需要创建`Canvas`或者`Panel`，然后重写它的`onPaint(Graphic g)`方法。
`Graphic`相当于画笔，所有的内容都是通过它绘制出来的。  
`Canvas`与`Panel`都是空白的“矩形区域”容器。往里面画什么就有什么。二者几乎是一样的，

`Swing`绘图：   
使用的`JPanel`也是一个空白区域，但是多了一个“双缓冲机制”。  
双缓冲：当我们要在某一个“组件”上绘制图形的时候，先在内存中绘制一个图形，然后将图形整体绘制在组件上。这样就避免在“组件”上一笔一笔地画（每一笔都会导致组件的一次刷新，导致组件闪烁）图形，提高了性能。


注：由于`main`方法是`static`方法，导致在`main`方法中不能调用类中的非`static`方法与`static`域，因此这个时候要将这些方法、域设置为`static`的。但往往并不能如此将他们都设置为`static`的。  
这个时候，有一种处理方式：  
在`main`方法外创建一个非`static`方法，然后在这个非`static`方法中调用想要的非`static`方法与域。最后在`main`方法中创建一个类对象，通过这个类对象调用前面创建的那个非`static`方法。

上例是使用`AWT`绘图使用示例：  
>
	import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	public class AWTDrawingTest
	{
		private Frame mainFrame = new Frame();
		private String RECT_shape = "rect";
		private String OVAL_shape = "oval";
		private String shapeToDraw = "";
		private MyCanvas drawArea = new MyCanvas();
		class MyCanvas extends Canvas
		{
			public void paint(Graphics g) 
			{
				Random rand = new Random();
				if(shapeToDraw.equals(RECT_shape)==true)
				{
					g.drawRect(rand.nextInt(200),rand.nextInt(200),50,50); 	
			}
>
				if(shapeToDraw.equals(OVAL_shape)==true)
				{
					g.drawOval(rand.nextInt(200),rand.nextInt(200),50,50); 	
				}
			}
		}
>
		public void init()
		{
			Button ButtRect = new Button("rect");
			Button ButtOval = new Button("oval");
			Panel MyPanel = new Panel();
			ButtRect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) 
				{
					shapeToDraw = RECT_shape;
					drawArea.repaint();	
				}	
			});
>
			ButtOval.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) 
				{
					shapeToDraw = OVAL_shape;
					drawArea.repaint();	
				}	
			});
>
			MyPanel.add(ButtRect);
			MyPanel.add(ButtOval);
			drawArea.setPreferredSize(new Dimension(300,300));
			mainFrame.add(MyPanel);
			mainFrame.add(drawArea,BorderLayout.SOUTH);
			mainFrame.pack();
			mainFrame.setVisible(true);
		}
		public static void main(String[] args)
		{
			new AWTDrawingTest().init();	
		}
	}  


注：用**抽象类或者接口**创建对象时，都是	使用**内部类或者匿名内部类。**

注：在类内部，不能在方法之外的区域调用方法。只能在方法之内调用其他方法，也就是说，不能在放`Field`的区域内出现方法的调用语句。


所谓的真彩色，就是32位颜色。分别为`RGBA`(红、绿、蓝、透明度)。每个基本色有256(2的8次方)种，共有2的32次方种。`BMP`是没有压缩的格式。  

`BufferedImage`使用：
`BufferedImage`对象`Image`相当于在内存中绘制一张图片。然后通过该对象的`getGraphics()`方法获得`Graphics`对象`ps`，也就是内存中这张图片`Image`的画笔。  
在后面的方法中，都是使用这个`ps`对象的各种方法将所有图形绘制在内存中的`BufferedImage`对象`Image`上（这里使用的画笔是内存中的那张图片的画笔）。  
然后在`paint()`方法的重写中，直接使用这个`Graphics`对象`g`来进行调用`drawImage()`方法绘制（就是将内存中的`Image`对象绘制在我们的显示组件`Canvas`上），这里使用的是显示组件的画笔`g`是`Canvas`的画笔而不是使用原来内存中的那张图片`Image`的画笔`ps`进行绘制。

`BufferedImage`使用示例：

>
	import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	import java.awt.image.*;
	public class AWTBufferedImageDrawingTest
	{
		private Frame mainFrame = new Frame();
		private String RECT_shape = "rect";
		private String OVAL_shape = "oval";
		private String shapeToDraw = "";
		private MyCanvas drawArea = new MyCanvas();
		BufferedImage Image = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
		private Graphics ps = Image.getGraphics();
		class MyCanvas extends Canvas
		{
			public void paint(Graphics g) 
			{
				g.drawImage(Image,0,0,null);
			}
		}
>
		public void init()
		{
			Button ButtRect = new Button("rect");
			Button ButtOval = new Button("oval");
			Panel MyPanel = new Panel();
			ButtRect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) 
				{
					Random rand = new Random();
					ps.drawRect(rand.nextInt(200),rand.nextInt(200),50,50); 	
					drawArea.repaint();	
				}	
			});
>
			ButtOval.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) 
				{
					Random rand = new Random();
					ps.drawOval(rand.nextInt(200),rand.nextInt(200),50,50); 	
					drawArea.repaint();	
				}	
			});
			mainFrame.setBounds(0,0,300,300);
			MyPanel.add(ButtRect);
			MyPanel.add(ButtOval);
			MyPanel.setBackground(new Color(255,0,0)); 
			drawArea.setPreferredSize(new Dimension(300,300));
			mainFrame.add(MyPanel);
			mainFrame.add(drawArea,BorderLayout.SOUTH);
			mainFrame.pack();
			mainFrame.setVisible(true);
		}
		public static void main(String[] args)
		{
			new AWTBufferedImageDrawingTest().init();	
		}
	}  



可以使用`ImageIO`类将图片读、取在磁盘上的专用类。前面的`BufferedImage`类是将图片在内存中放置。这个`ImageIO`类是将图片在磁盘上输入、输出。

`Graphics`类可以对图形进行处理，如、旋转等。它是`Graphics`类的子类。可用于验证码的产生。

`ImageIO`及`Graphics`类使用示例：

>
	import java.awt.*;
	import javax.swing.*;
	import java.util.concurrent.*;
	import java.awt.image.*;
	import javax.imageio.*;
	import java.io.*;
	import java.util.*;
	public class AWTImageIOTest
	{
		public static void main(String[] args) throws Exception
		{
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			BufferedImage image = new BufferedImage(200,150,BufferedImage.TYPE_4BYTE_ABGR );
			Graphics ps = image.getGraphics();
			//use Graphics2D to ratota the image
			Graphics2D pd = (Graphics2D)ps;
			pd.setColor(new Color(255,0,0)); 
			pd.setBackground(new Color(255,0,0));
			int x=40;
			for(int i=1;i<=6;i++) 
			{
			 	//get the char by random
				char ch = (char)rand.nextInt(65,65+26); 
				double ang = rand.nextDouble(Math.PI/12,Math.PI/6);
				//rotate the pd
				pd.rotate(ang,x,80); 
				//draw the char on the image
				pd.drawString(""+ch, x, 60); 
				//recover the pd
				pd.rotate(-ang,x,80); 
				x=x+20;
			}
>		
			pd.setColor(new Color(0,0,0)); 
			for(int j=0;j<100;j++)
			{
				int xStart = rand.nextInt(20,180);
				int yStart = rand.nextInt(10,130);
				int xEnd = rand.nextInt(60,180);
				int yEnd = rand.nextInt(30,140);
				pd.drawLine(xStart,yStart,xEnd,yEnd);
			}
			ImageIO.write(image,"png",new File(UUID.randomUUID	()+"1.png"));
		}
	}
