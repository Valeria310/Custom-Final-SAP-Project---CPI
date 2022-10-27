import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap
import java.text.DecimalFormat
import groovy.json.*
 
def Message processData(Message message) {
    def slurper = new groovy.json.JsonSlurper();
    def body = message.getBody(String);
    def parsedBody = slurper.parseText(body);
    
    quantity = message.getProperty("quantity");
    
    def payload = new JsonBuilder();
    payload{
        quantity quantity
        };
    payload = payload.toPrettyString();
    
    def result = new JsonSlurper().parseText(payload);
    def newjson = JsonOutput.toJson(result);
    
    message.setBody(newjson);
    message.setHeader("Content-Type", "application/json" + "; charset=utf-8" );
 
return message;
}