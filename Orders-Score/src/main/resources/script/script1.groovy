import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap
import groovy.json.*
 
def Message processData(Message message) {
 
    def slurper = new groovy.json.JsonSlurper();
    def body = message.getBody(String);
    def result = slurper.parseText(body);
    message.setProperty("ID", result.productID);
    message.setProperty("quantity", result.quantity);
   return message;
}