1. Linux内核层 
Android系统是基于 Linux 2.6 内核的，这一层为 Android设备的各种硬件提供了底层的驱动，如显示驱动、音频驱动、照相机驱动、蓝牙驱动、Wi-Fi驱动、电源管理等。  
2. 系统运行库层 
这一层通过一些C/C++ 库来为Android系统提供了主要的特性支持。如SQLite 库提供了数据库的支持，OpenGL|ES 库提供了 3D绘图的支持，Webk it 库提供了浏览器内核的支持等。同样在这一层还有Android运行时库，它主要提供了一些核心库，能够允许开发者使用Java 语言来编写 Android应用。另外 Android运行时库中还包含了Dalvik 虚拟机，它使得每一个Android应用都能运行在独立的进程当中，并且拥有一个自己的Dalvik 虚拟机实例。相较于Java 虚拟机，Dalvik 是专门为移动设备定制的，它针对手机内存、CPU 性能有限等情况做了优化处理。  
3. 应用框架层 
这一层主要提供了构建应用程序时可能用到的各种API，Android自带的一些核心应用就是使用这些API完成的，开发者也可以通过使用这些API来构建自己的应用程序。  
4. 应用层 
所有安装在手机上的应用程序都是属于这一层的，比如系统自带的联系人、短信等程序，或者是你从Google Play 上下载的小游戏，当然还包括你自己开发的程序。  


Android系统到底提供了哪些东西，供我们可以开发出优秀的应用程序。  
1. 四大组件 
Android系统四大组件分别是活动（Activity）、服务（Service）、广播接收器（Broadcast Receiver）和内容提供器（Content Provider）。其中活动是所有 Android应用程序的门面，凡是在应用中你看得到的东西，都是放在活动中的。而服务就比较低调了，你无法看到它，但它会一直在后台默默地运行，即使用户退出了应用，服务仍然是可以继续运行的。广播接收器可以允许你的应用接收来自各处的广播消息，比如电话、短信等，当然你的应用同样也可以向外发出广播消息。内容提供器则为应用程序之间共享数据提供了可能，比如你想要读取系统电话簿中的联系人，就需要通过内容提供器来实现。  
2. 丰富的系统控件 
Android系统为开发者提供了丰富的系统控件，使得我们可以很轻松地编写出漂亮的界面。当然如果你品味比较高，不满足于系统自带的控件效果，也完全可以定制属于自己的控件。  
3. SQLite 数据库 
Android系统还自带了这种轻量级、运算速度极快的嵌入式关系型数据库。它不仅支持标准的SQL 语法，还可以通过 Android封装好的 API 进行操作，让存储和读取数据变得非常方便。  
4. 地理位置定位 
移动设备和PC相比起来，地理位置定位功能应该可以算是很大的一个亮点。现在的Android手机都内置有 GPS ，走到哪儿都可以定位到自己的位置，发挥你的想象就可以做出创意十足的应用，如果再结合上功能强大的地图功能，LBS 这一领域潜力无限。  
5. 强大的多媒体 
Android系统还提供了丰富的多媒体服务，如音乐、视频、录音、拍照、闹铃等等，这一切你都可以在程序中通过代码进行控制，让你的应用变得更加丰富多彩。  
6. 传感器 
Android手机中都会内置多种传感器，如加速度传感器、方向传感器等，这也算是移动设备的一大特点。通过灵活地使用这些传感器，你可以做出很多在PC上根本无法实现的应用。


catlog日志工具实际的作用：  
就是如同我们在程序中添加的System.out.println()一样。当我们使用log日志的时候，在程序中添加log.d()语句，那么当执行到这个语句的时候，就会显示出我们预先设定显示的内容，从告诉我们程序执行到这里了。这个作用和System.out.println()的作用是一样的，更加简便快捷，而且有过滤器可以使用，方便。  