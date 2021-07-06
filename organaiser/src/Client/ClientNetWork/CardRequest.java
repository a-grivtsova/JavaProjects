package Client.ClientNetWork;

public class CardRequest {
	
	public String NewCardtRequest(String card_name, String id_tasklist)
	{		
		return
				"<command name='newcard'>"
				+"<card_name>"+card_name+"</card_name>"
				+"<id_tasklist>"+id_tasklist+"</id_tasklist>"
				+"</command>\n";	
	}

	public String CardListRequest(String id_tasklist) {
		// TODO Auto-generated method stub
		return
				"<command name='listcards'>"
				+"<id_tasklist>"+id_tasklist+"</id_tasklist>"
				+"</command>\n";
	}

}
