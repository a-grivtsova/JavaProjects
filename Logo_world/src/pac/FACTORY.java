package pac;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FACTORY {
	
	HashMap<String, Factory_object > map = new HashMap<>();
	public void run(File_command_work h, Canvas s, Map<String, String> t) 
	{
		if(s.equals(s))
		{
		for(int i = 0; i < h.arr_cmd.size(); i++)
		{
			String hash = h.arr_cmd.get(i);
			
			if(!map.containsKey(hash))//если еще не создан такой объект
			{
				Class<?> c = null;
				
				try {
					c =  Class.forName(t.get(hash));
								
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
						
				Constructor constructor = null;
				try {
					constructor = c.getConstructor();
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
				 
				Factory_object obj = null;
				try {
					obj = (Factory_object)constructor.newInstance();
					map.put(hash, obj);
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
	
				try 
				{
					Method method = c.getDeclaredMethod("execution", Canvas.class, String.class);
					method.invoke(obj, s, h.arr_prm.get(i));
				}
				catch (Exception e) 
				{ 
					if(e.getCause().getClass().equals(IllegalArgumentException.class))
					{
						//e.printStackTrace();
						//System.exit(1);		
						System.out.print("Error command!");
					}
				}
			}
			else // если такой объект уже создан, используем его еще раз 
			{
				map.get(hash).execution(s, h.arr_prm.get(i));
			}
		}
	}
	}
}
