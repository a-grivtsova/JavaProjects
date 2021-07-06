package Client.ClientNetWork;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Transmitter 
{

    private OutputStreamWriter out;
	   
    public Transmitter(OutputStreamWriter out)
    {
    	this.out = out;
    }
	
	public void SendRequest(String message) throws IOException
	{
		out.write(message);
		out.flush();		
	}
    	
}
