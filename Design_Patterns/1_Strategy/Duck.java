

public abstract class Duck
{
	FlyBehavior fb;
	QuackBehavior qb;
	Duck()
	{
		
	}
	
	public void PerformFly()
	{
		fb.fly();
	}
	public void PerformQuack()
	{
		qb.quack();
	}

}
