package imp;

public class CurrentConditionDisplay implements observer,DisplayElement
{
	private float temperature;
	private float humidity;
	private float pressure;
	private WeatherData wd;

	public CurrentConditionDisplay(Subject sd)
	{
		super();
		this.wd = (WeatherData) sd;
		wd.registerObserver(this);
	}

	@Override
	public void update(float temp, float humidity, float pressure)
	{
		this.temperature = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		this.display();
	}

	@Override
	public void display()
	{
		// TODO Auto-generated method stub
		System.out.println("currentCondition"+this.temperature+"+"+this.humidity+"+"+this.pressure);
		
	}

}
