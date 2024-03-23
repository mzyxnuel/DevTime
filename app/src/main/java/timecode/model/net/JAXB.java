package timecode.model.net;

import java.io.StringReader;
import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class JAXB {
   private JAXBContext jaxb;

   public JAXB(Class jclass) throws JAXBException {
      jaxb = JAXBContext.newInstance(jclass);
   }

   public String marshal(Object obj) throws JAXBException {
      StringWriter xml = new StringWriter();
      Marshaller marshaller = jaxb.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.marshal(obj, xml);
      return xml.toString();
   }

   public Object unmarshal(String response) throws JAXBException {
      Unmarshaller unmarshaller = jaxb.createUnmarshaller();
      return unmarshaller.unmarshal(new StringReader(response));
   }
}
