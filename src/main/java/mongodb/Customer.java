package mongodb;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class Customer {
    @Id
    public String id;

    public String firstName;
    public String lastName;
    public Map<String,String> references;
    public Map<String, Object> additionalData;

    public Customer() {}

    public Customer(String firstName, String lastName, Map<String, String> references, Map<String, Object> additionalData) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.references = references;
        this.additionalData = additionalData;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', references='%s', additionalData='%s']",
                id, firstName, lastName, new JSONObject(references), new JSONObject(additionalData));
    }
}
