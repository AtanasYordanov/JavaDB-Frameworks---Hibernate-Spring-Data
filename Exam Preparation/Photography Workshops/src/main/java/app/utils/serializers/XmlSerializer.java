package app.utils.serializers;

import app.io.FileParser;
import app.utils.serializers.api.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component("xml")
public class XmlSerializer implements Serializer {

    private final FileParser fileParser;

    @Autowired
    public XmlSerializer(FileParser fileParser) throws JAXBException {
        this.fileParser = fileParser;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(Class<T> tClass, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return (T) unmarshaller.unmarshal(reader);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> void serialize(T object, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            File file = new File(fileName);
            marshaller.marshal(object, file);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}