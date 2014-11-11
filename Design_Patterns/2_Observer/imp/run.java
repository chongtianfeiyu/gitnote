package imp;

public class run
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		WeatherData sb = new WeatherData();
		CurrentConditionDisplay ob = new CurrentConditionDisplay(sb);
		statisticDisplay sd = new statisticDisplay(sb);
		sb.setMeasurements(100, 100, 100);
		sb.setMeasurements(200, 200, 200);
		sb.setMeasurements(10, 10, 10);
	}

}
