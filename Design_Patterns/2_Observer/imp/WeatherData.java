package imp;

import java.util.ArrayList;
public class WeatherData implements Subject
{
	private ArrayList<observer> observers;
	private float temperature;
	private float humidity;
	private float pressure; 
	
	public float getTemperature()
	{
		return temperature;
	}


	public float getHumidity()
	{
		return humidity;
	}

	public float getPressure()
	{
		return pressure;
	}

	public WeatherData()
	{
		super();
		observers = new ArrayList<>();
	}
	
	@Override
	public void registerObserver(observer o)
	{
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(observer o)
	{
		int i = observers.indexOf(o);
		if(i>=0)
		{
			observers.remove(o);
		}
	}

	@Override
	public void notifyObservers()
	{
		for (observer observer : observers)
		{
			observer.update(this.temperature,this.humidity,this.pressure);
		}
	}
	
	public void measurementsChanged()
	{
		notifyObservers();
	}
	
	public void setMeasurements(float tem,float humidity,float pressure)
	{
		this.temperature = tem;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
}
