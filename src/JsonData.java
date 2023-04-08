import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonData {
	JsonObject json;
	
	public JsonData(String data) {
		this.json = new JsonParser().parse(data).getAsJsonObject();
	}
}
