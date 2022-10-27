import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap
import groovy.json.*
 
def Message processData(Message message) {
 
    def slurper = new groovy.json.JsonSlurper();
    def body = message.getBody(String);
    def result = slurper.parseText(body);
    message.setProperty("productID", result.productID);
    message.setProperty("productName", result.productName);
    message.setProperty("quantity", result.quantity);
    message.setProperty("totalPrice", result.totalPrice);
    message.setProperty("CurrencyCode_code", result.CurrencyCode_code);
    message.setProperty("totalWeight", result.totalWeight);
    message.setProperty("status_ID", result.status_ID);
    message.setProperty("image", result.image);
   return message;
}
