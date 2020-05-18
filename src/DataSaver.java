import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataSaver {


    public DataSaver() {
    }
    public static void serializeToXML (TrazAqui settings) throws IOException
    {
        FileOutputStream fos = new FileOutputStream("./src/estado.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(e -> System.out.println("Exception! :"+e.toString()));
        encoder.writeObject(settings);
        encoder.close();
        fos.close();
    }

    public static TrazAqui deserializeFromXML() throws IOException {
        FileInputStream fis = new FileInputStream("./src/estado.xml");
        XMLDecoder decoder = new XMLDecoder(fis);
        TrazAqui decodedState = (TrazAqui) decoder.readObject();
        decoder.close();
        fis.close();
        return decodedState;
    }
}