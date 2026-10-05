package utilies;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

public class JSONFileManager {
    public LinkedHashMap<String, Object> data;

    public JSONFileManager(String filePath) {
        try{
            Type t = new TypeToken<LinkedHashMap<String, Object>>(){}.getType();
            data = new Gson().fromJson(new FileReader(filePath), t);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object getValue(String key){
        return data.get(key);
    }
}
