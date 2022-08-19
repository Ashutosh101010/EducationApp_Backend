package com.aurd;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.aurd.Student.Repository.LiveSessionRepository;
import com.google.api.client.util.IOUtils;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.*;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class Service {

    public static FirebaseApp firebaseApp;
    public static AmazonS3 s3;
    public static String bucket="satyadhi";
//    public static String bucket="educationapp";


    public void  onStart(@Observes StartupEvent event){

        final String s3Endpoint = "https://s3.wasabisys.com";
        final String region = "us-east-1";

        AWSCredentialsProvider credentials =
                new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("RO3PDA6CRJSQC4JH65QH", "dtB4aV9tharNtWaW2eaZMK08zCzqHlleBMvlmRof"));

        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Endpoint, region))
                .withCredentials(credentials)
                .build();

        try {
//  -----------old firebase key ------------


//            String json = "{\n" +
//                    "  \"type\": \"service_account\",\n" +
//                    "  \"project_id\": \"kapaksh\",\n" +
//                    "  \"private_key_id\": \"b4dc74bd2483637b469733f52bd045af64415c42\",\n" +
//                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDDHwgQzd14HAtD\\nKlIdMa670oPi+OxFgfuLgxOKX6zAagpi+A9cEYSwL1t8v0B5cucqy+yE+eIUXqjV\\n4w//tEC53tj8xnYU0LkbxKyAgCb18dvQf52J3mBijNqJP0wlLm8M45oESHxpPHFw\\ndKTMxFOKhqPFPistk686kHFfe9DhnxTjzBU92yFBONljEe/pYz/yEk4nryE3kK28\\nJqAJ3g0YGa5632RNZrv6eL8LW14erSNlb1aT5L4dNWstHCyW5HTpPCUyK5n7jsQ/\\nyEPoNqSnq/2ywQf/yQJ4gauabHfhXA9Qv2FRTL1+rdJw+bqUxvl7/rPvbtC5eLxJ\\nMIsx9bbhAgMBAAECggEAFVuAsQIvozWCoBPyUAK/qwxkZkQz7HnJVGCBE4zcZmVV\\nBLc9SykZuoU8gN+YSZLjrcILfhSyA+PGnD7R4f3TdeToIGcCxs1/7YOuFBSy7wnN\\nszk8v7oJF9BuHUbB6XH3MtR4584dXBGDIzmJTmaQ9GE/bNgctszrCcCEwFxXpVJo\\n1Wld5IkZ4LGvuMM4YurgWSN6Te8xC2bTzyBCX5NhzUz+k+xKpGBaQuy9viFg2kqE\\n1sYvwPGeKj8iN+9L813IXgpUieDNiNHuGLoCtffXg755dve9nRYY5vRFoNuTZUVl\\nBfgduQ154eionDrwWyUe5A4jGAPkXyExeACpUIvrIQKBgQDvrFlFFF8Gwcy6ia3D\\nBBxQAaXlN0O2286giPzpPz9ZI6nm78hsKQ1iwsP1cwtiq/yJhppTxclt71Kh1HEv\\nDplz8PTYFGxa+Y/gaG3Z3BlEJqASTly2Jga/gFMpRvljqkWmVqn1EBurXYfwyxMI\\nVedVyXkJboc6E31tbP6QLydA6QKBgQDQab31eefKtYoMBrKp1J6p/gbB/7q6+Mjz\\nD5SmXRHjBPL4BuRqnpzPRx9zHMKSztaIFoCzHJGlp3p1BAkwpeegEYCKS0DmVtxC\\nQ1+lBUvy8TX8phobHPMinhDnYvvgK9qI51fUT8fQioMYTndr4sjMbXwdTYiqVDPr\\n+4qiIBRLOQKBgHsPcV6o0Qn1JBt7ab7CM49Y6UdCEwCm5/dBnypcETjBuREceTof\\n8gGNo3Q5t9TYHfi3JjJxUbWitjjljZ/tzRjc1brE8cXcceV1eUHtYwO9GV+M4Mmd\\nyMU3LHv7Nz3XRZ/cy7NKrApXId+fRYx4HynLItyW5dQKAdLXrfV2YJ9RAoGAdIi7\\n29e3QQRzVTgljIrD0R+mXbGFkuhrTGWI4+EkkEEDLqaD8A4SdmA5eJ87OOWI1X1n\\npz8xPWRBN87y6I3LzxgaPLnJZ0JoVHDhuR8WEZjIbCXgcQql9NRXdVpGplk/D8/z\\nr1aQzs20cdHmOqALUSti++uPoFYG1kvHf9HaJxkCgYBLkQ/aAyrRVPyVnFZoFe8E\\nr15SoddI5cqlWpCh6e2DAOgTtfMVydm+4TEQx4CeK6fDIatk+ZftV2kY2Ko0R4XL\\npM4XiF8sCm8JB2T12hmFdqyfg4ht86zmRw9XL+OFhJoEE2+BYyl53WJ22MMeaiJN\\nGY6lN/vkD+ht/Gw+Q6FTNg==\\n-----END PRIVATE KEY-----\\n\",\n" +
//                    "  \"client_email\": \"firebase-adminsdk-c4mp6@kapaksh.iam.gserviceaccount.com\",\n" +
//                    "  \"client_id\": \"111546179413685310749\",\n" +
//                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
//                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
//                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
//                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-c4mp6%40kapaksh.iam.gserviceaccount.com\"\n" +
//                    "}";
 // ------------------------------------------------------------------------------------------


            //------------------------------------- new firebase key ----------------------------//
//            String json="{\n" +
//                    "  \"type\": \"service_account\",\n" +
//                    "  \"project_id\": \"studentapp-1a45b\",\n" +
//                    "  \"private_key_id\": \"bbf9561fccabf64abf1d38cb66b191e69dc11f3f\",\n" +
//                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjNg1JBdG0mPIp\\nioS2oSpzdboKYTFXime9b17EMJnLHSZdqH2Ad8zorv7fJSMQdlzRakFlw2OiSs9M\\n4nAqA+2K2WYJNcEndrok/Y3Dlibzng8tp3arvM5fQsjl3KNPH4y1G/vw9ergMkw6\\noTE3cGKrSmwaiRjXomiQUM+yt6TR71YJBQ+nvMxpP2ns3rJiPZBdqqALkDXt6jq5\\nmillXZPw0rhm0iEk00tEA2hDA0ClwP8VyJPbDlZymjQ4Vb5uftW/scbQnaiyeVqQ\\nbKhG966sZaEZIWoTDjz9aQpcJrpeHnEU1VkBnka91UxOVccnn0wb5oRsyfEoKp/G\\nf+sc/c2FAgMBAAECggEAAwHScdd9GyY8Q/6s3k6O4Sy4nktXJOVEbw2XweahVsg1\\nNkGN+ml6qYjb4lGlWT7NDAC4rr7QXaOiOSjs5yFGZaoGwsGuSuNZn6faTlG8av2t\\nCurMeXKTnNa2q+GKUsFijs2ysSheyP20QNpKdVqJPt2yqzR+dC6mXY5XnVPb4hBO\\nxy+0oV6ZZkdPS/dO983pY+T/0T5ipxmQIY+8s34EedEIeeK73j7WVHx2fE/nFjc3\\nKfAvCDRks/MqtpGnef7GUe7xFzH4fgtL0VbbJ8vj/nN3xlUDQoCq4bBDXcdP3faz\\nnDWGeuUmeYtE4dnjFVG7LY9JiYnxt0GfYiX/KjRs0QKBgQDd7qqQvXCEE/HqyyGn\\noq5ln2h4b6/cKj/sTNV7Cg15TKKbgZZ8IhmSd08qEqifO4MWP7f3t+LGQ6fizE6D\\n9VijEbVF8OIh8KU4A92v7k1UpT18QB3rwY2R5pEew25xV5yKc1Wn5mRDBE9ty+Sn\\ns6jn40VhoYmtlMSWiS5vqIIfvQKBgQC8Q835H0BaYTygQKtacIFzgMyoPxRrrqF1\\ng5ZNNVErvkNX9PurlliABNL9d9MSmjKXtd834ZHFUx8fjM/HUjFC9c04ca/uo1gj\\nr7cKcNMaCPeL0fTYayvIlsJ+7vgsxRab6rgLwbtC3KojYwHwPMYN30Z7WCJsPSU8\\n6X6S1tX9aQKBgQDN5rcikE+KioKgppNz1u/cYrfnPR+4sQ4bavGJNLtpdZtLkUAP\\nrQdypvSwgR0mlm05QCarUlS4Q17IgWq67O6uZCh9Wk5C1OREgvmzVvEx/hh8ZXQd\\nHbb9zR+IniKiT3lxzi2DF7KfpqkWodtjZ8DmWyosuVVl0SdOzrlhaMJJNQKBgFh3\\nVDaYZNkTkDWNrfeVnn70Kzd0TNg8+0oJR3uIblgr0+FlEU4UHAosOik5QUogg12e\\n7QGJUcJzyNA+jvSOvu9EKec4JP/yadx7GXwigFBfnlNpmbCIy846abLUAHKpOnKR\\n3WJI6wIERz7Rq3uEU1z5allTlPlf0jvJTN0bbvrhAoGAW8XP3VRbMcjhPy8OkrYE\\nCZP6XjPdjSn4802AsA4COcdJTCzOm2QTEr4oQEZcah2HMMLHzingLtawmBw70PEy\\n4pibBXwacj8T/51t6LXo25bofmGoyZyyapbHJALurlH+0YbBSyk9/UNfQYYx6ySr\\n6fPrQ49sp+3czC+K1BUW8XI=\\n-----END PRIVATE KEY-----\\n\",\n" +
//                    "  \"client_email\": \"firebase-adminsdk-n86nb@studentapp-1a45b.iam.gserviceaccount.com\",\n" +
//                    "  \"client_id\": \"106050857983183381066\",\n" +
//                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
//                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
//                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
//                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-n86nb%40studentapp-1a45b.iam.gserviceaccount.com\"\n" +
//                    "}";
//---------------------------------satyadhi firebase key ----------------------------------------

            String json="{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"satyadhi-81f08\",\n" +
                    "  \"private_key_id\": \"b65516fb83f9a87b3b6443f36c38820725760d05\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCr7ikabKqIgJVX\\nz6ZhTFzC79pm4WF1DmVY7+DnpBKiUrWJGlHl1KMU7ul+hQM3nJVaPUWYVIrp7KRV\\nxjkfLHcu5QGKX+BkBRZR1rcfEiKEisAR9RHAGG0/niicTC8vnzZ9bLjNXvoIN4b5\\nv7hAsD4kdQyXkqst4HqEiJPlyg0KFtVk99XQ6/FZ6J2jPlQN0q8aRXBwopa9keCP\\np2iCBPaZd6uVksaJFUCg+tpK0XjJuhsKhOJ9WzKx07UnPxGhqe+7fJGjbkmHOBfO\\nIOSE0vOuZepydb98aMNs+0An44CGS32WjjOkPpS6RB6UWKulCWHnGogyDmfHDwS6\\nxpStEeoRAgMBAAECggEARetI0/I1N+Xy1g8gPplrb+tTClvF4nDETnLfJ9TVryWr\\npeIKRElBORYf5s/5Cvim1FfuoZBgHdmayzcdq8vNfEitY7Sx8HsRJJ8JQZ2CZBx5\\nF9RuL4y/gQu0wDtLRTaeVkuMrBhf3k+WmKKbL9N8sh7XzBxdeP69VICmhOCyhNMw\\nRFUiRd+xafN18CkNx+i44FnsFE0RMMy7gptIL0zwDnnGMrvh10BMXfMvQ+ABIKtx\\nBDfEO5t6PWp5n1UbYwGhKG8HEGrQK1d5KBVVcX+hE1p3/Fh8RbrOwVsDH05J7ovm\\nMpo5pafXng9fVJd9000c4bvYpKR3TjCrnn6sbewQ8QKBgQDvFNvoUXPeyw6r78HS\\no66bXaD2VkspvjMH2vA4tPriL+A4ebGg1M8uqhLRQelYVR9mJ+ec/MFocph/r/JU\\nFl0ojWFXp8dZvVNW3ksCOHQDaBR1NM7GHnQi3ZrmfeWWNq/TVvVm0F+BUIvV2Glw\\ncvf9QgcdHZxSfjySKX61nO26JwKBgQC4GM7AyQbQV7VoHmI08oML7VVm8qQ3HPmG\\n6ebq2T8baSbBBabvCgwsVpRN0VGBnbWfpD/TtHjYanK8k2XXejFYAgkYtzS+Uehm\\nHRJYG9Wsbsevpv+bD25+jkiZ9W/5yWKt6a7jzfqI4vK/ata711PST1AZ0IGk6mIW\\nNgwFhFJ1BwKBgQC2ZQV38VwipmtlBJ6r4i/9W6rHBAKHsBEidwc5W+ji0n+O1liL\\nSI9fHGUKu+wXdalkuCDdBX9P/c0V7NdFC2K641FWHCvb7LI93Yz//q2TQ74HxOfT\\nAby8NLBOE9IpjUMKSpchfuq9rwNfe6DFJxDGZ68hqUtvK9tt7rY6O6SplQKBgAxT\\nfORjkVU5yieigSjSY/vUdDdkX6JhCkQCPTfUO+l17OZXnom6vn3pfhyHu1FWYebC\\ndJOULdMvrWb654K557vgxwMkoR9tFIZhw+yqUHAvKqeUIuEa82b919aXHFw5I6ZJ\\nUzBlhjTMHTy7xozlKfOW4+IWl9eW6eJa1As6hNrZAoGBAL7gTYcwLQ48RHBqFuQR\\nKLHAkf0ri9WvmpmKdXbv4W47UOeJxsnlboEvbvQFZIeIijQz80S8sWGyzEJO3Bbi\\n2+w1ez3mhOQ1O0COfTPcTtXJvncBayW//3Ah4+I6+3txZFI+m9YYol98vV1cAgqu\\n8LhSjd45DdvVGwZM4VEsSpRr\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-6gwsf@satyadhi-81f08.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"103627140667389691613\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-6gwsf%40satyadhi-81f08.iam.gserviceaccount.com\"\n" +
                    "}\n";

            JSONObject jsonObject = new JSONObject(json);
            System.out.println(jsonObject);
            InputStream stream = new ByteArrayInputStream(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            File tempFile = File.createTempFile("temp", ".json");
            tempFile.deleteOnExit();
            FileOutputStream out = new FileOutputStream(tempFile);
            IOUtils.copy(stream, out);
            FileInputStream serviceAccount =
                    new FileInputStream(tempFile);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void onStop(@Observes ShutdownEvent event){
        firebaseApp.delete();
    }


}
