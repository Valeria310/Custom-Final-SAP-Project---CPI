import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap
import java.text.DecimalFormat
import groovy.json.*
 
def Message processData(Message message) {
    def code = message.getProperty("CurrencyCode_code");
    code = code.toLowerCase();
    def slurper = new groovy.json.JsonSlurper();
    def body = message.getBody(String);
    def parsedBody = slurper.parseText(body);
    
    productID = message.getProperty("productID");
    productName = message.getProperty("productName");
    quantity = message.getProperty("quantity");
    totalPrice = message.getProperty("totalPrice");
    totalWeight = message.getProperty("totalWeight");
    productImage = message.getProperty("image");
    currency = message.getProperty("CurrencyCode_code");
    status_ID = message.getProperty("status_ID");
    totalLocalPrice = 1/parsedBody.byn[code] * totalPrice;
    def df = new DecimalFormat("#0.00");
    totalPrice = df.format(totalPrice);
    totalLocalPrice = df.format(totalLocalPrice);
    
    def payload = new JsonBuilder();
    payload{
        productID productID
        quantity quantity
        productName productName
        productImage productImage
        totalWeight  totalWeight
        totalPrice totalPrice
        totalLocalPrice totalLocalPrice
        status_ID status_ID
        };
    payload = payload.toPrettyString();
    
    def result = new JsonSlurper().parseText(payload);    
    result.CurrencyCode_code = currency;
    result.LocalCurrencyCode_code = "BYN";
    def newjson = JsonOutput.toJson(result);
    
    message.setBody(newjson);
    message.setHeader("Content-Type", "application/json" + "; charset=utf-8" );
 
return message;
}
