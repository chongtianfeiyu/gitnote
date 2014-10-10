1，子类会继承父类的所有属性与方法，但是不会继承父类的构造函数。只是，在调用子类的构造函数时，子类构造函数内会自己调用父类的构造函数，父类的构造函数会对父类的属性进行初始化，又由于子类从父类继承了所有的属性，所以，相当于调用了父类的构造函数对子类从父类中继承的那些属性进行了初始化。
2、多态只是方法的多态，而不是类属性的多态，也就是说，通过父类引用变量，只能通过多态调用子类重写的方法，而不能调用子类重写的属性。当然，也不能通过多态调用父类中没有的方法，只能调用父类中有在子类中进行了重写的方法。
3、继承与组合是实现类的复用的重要手段，但是，继承带了的坏处就是破坏封装(因为在继承中，子类可以直接使用父类的属性与方法，这样导致继承破坏了父类的封装性，导致子类与父类的耦合性提高，可以被子类重写。为了不被子类重写，可以将某些方法设为final)，而组合方式(将一个类作为另一个类的属性)实现类的复用则能提供更好的封装性(可将一个类作为另一个类的组合成分，允许外部类直接复用该类的public方法)。
具体该使用继承还是组合，决定于现实情况到底是哪一种，例如，person就可以使用arm作为一个属性，以使用组合的方式，来利用arm这个类的方法进行复用。。而animal与wolf就应该是继承关系来实现复用。