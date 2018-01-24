package np.com.saathi.client;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
 
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
 

  
public class SpringRestClient {
	
	public static final String REST_SERVICE_URI="http://mysaathi-saathi.a3c1.starter-us-west-1.openshiftapps.com/saathi/app/stock";
  
  private static HttpHeaders getHeaders(){
        //String plainCredentials="user:pass";
       // String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
        
        //System.out.println("base64Credentials:"+base64Credentials);
         
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + "c2hhcmVzYWF0aGk6c2hAcjNzQEB0aDE=");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
  
  
  private static void listAllUsers(){
      System.out.println("\nTesting listAllUsers API-----------");
      RestTemplate restTemplate = new RestTemplate(); 
       
      HttpEntity<String> request = new HttpEntity<String>(getHeaders());
      ResponseEntity response = restTemplate.exchange(REST_SERVICE_URI, HttpMethod.GET, request,String.class);
     
      System.out.println(response);
  }
    
     
 
    public static void main(String args[]){
         
        listAllUsers();
 
       
    }
}