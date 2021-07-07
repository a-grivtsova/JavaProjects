import java.util.UUID;

public class car 
{
	body body;
	engine engine;
	accessory accessory;
	String uniqueID_car;
	
	car(body body, engine engine, accessory accessory)
	{
		uniqueID_car = UUID.randomUUID().toString();
		this.body = body;
		this.accessory = accessory;
		this.engine = engine;
	}
}
