package app.utils.serializers;

import app.io.FileParser;
import app.utils.serializers.api.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("json")
public class JsonSerializer implements Serializer {

    private Gson gson;
    private FileParser fileParser;

    @Autowired
    public JsonSerializer(FileParser fileParser) {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
        this.fileParser = fileParser;
    }

    @Override
    public <T> T deserialize(Class<T> tClass, String fileName) {
        try {
            String file = this.fileParser.readFile(fileName);
            return this.gson.fromJson(file, tClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> void serialize(T object, String fileName) {
        String content = this.gson.toJson(object);
        try {
            this.fileParser.writeFile(fileName, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
