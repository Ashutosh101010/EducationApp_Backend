package com.aurd;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.aurd.Student.Repository.LiveSessionRepository;
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

            String json = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"kapaksh\",\n" +
                    "  \"private_key_id\": \"b4dc74bd2483637b469733f52bd045af64415c42\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDDHwgQzd14HAtD\\nKlIdMa670oPi+OxFgfuLgxOKX6zAagpi+A9cEYSwL1t8v0B5cucqy+yE+eIUXqjV\\n4w//tEC53tj8xnYU0LkbxKyAgCb18dvQf52J3mBijNqJP0wlLm8M45oESHxpPHFw\\ndKTMxFOKhqPFPistk686kHFfe9DhnxTjzBU92yFBONljEe/pYz/yEk4nryE3kK28\\nJqAJ3g0YGa5632RNZrv6eL8LW14erSNlb1aT5L4dNWstHCyW5HTpPCUyK5n7jsQ/\\nyEPoNqSnq/2ywQf/yQJ4gauabHfhXA9Qv2FRTL1+rdJw+bqUxvl7/rPvbtC5eLxJ\\nMIsx9bbhAgMBAAECggEAFVuAsQIvozWCoBPyUAK/qwxkZkQz7HnJVGCBE4zcZmVV\\nBLc9SykZuoU8gN+YSZLjrcILfhSyA+PGnD7R4f3TdeToIGcCxs1/7YOuFBSy7wnN\\nszk8v7oJF9BuHUbB6XH3MtR4584dXBGDIzmJTmaQ9GE/bNgctszrCcCEwFxXpVJo\\n1Wld5IkZ4LGvuMM4YurgWSN6Te8xC2bTzyBCX5NhzUz+k+xKpGBaQuy9viFg2kqE\\n1sYvwPGeKj8iN+9L813IXgpUieDNiNHuGLoCtffXg755dve9nRYY5vRFoNuTZUVl\\nBfgduQ154eionDrwWyUe5A4jGAPkXyExeACpUIvrIQKBgQDvrFlFFF8Gwcy6ia3D\\nBBxQAaXlN0O2286giPzpPz9ZI6nm78hsKQ1iwsP1cwtiq/yJhppTxclt71Kh1HEv\\nDplz8PTYFGxa+Y/gaG3Z3BlEJqASTly2Jga/gFMpRvljqkWmVqn1EBurXYfwyxMI\\nVedVyXkJboc6E31tbP6QLydA6QKBgQDQab31eefKtYoMBrKp1J6p/gbB/7q6+Mjz\\nD5SmXRHjBPL4BuRqnpzPRx9zHMKSztaIFoCzHJGlp3p1BAkwpeegEYCKS0DmVtxC\\nQ1+lBUvy8TX8phobHPMinhDnYvvgK9qI51fUT8fQioMYTndr4sjMbXwdTYiqVDPr\\n+4qiIBRLOQKBgHsPcV6o0Qn1JBt7ab7CM49Y6UdCEwCm5/dBnypcETjBuREceTof\\n8gGNo3Q5t9TYHfi3JjJxUbWitjjljZ/tzRjc1brE8cXcceV1eUHtYwO9GV+M4Mmd\\nyMU3LHv7Nz3XRZ/cy7NKrApXId+fRYx4HynLItyW5dQKAdLXrfV2YJ9RAoGAdIi7\\n29e3QQRzVTgljIrD0R+mXbGFkuhrTGWI4+EkkEEDLqaD8A4SdmA5eJ87OOWI1X1n\\npz8xPWRBN87y6I3LzxgaPLnJZ0JoVHDhuR8WEZjIbCXgcQql9NRXdVpGplk/D8/z\\nr1aQzs20cdHmOqALUSti++uPoFYG1kvHf9HaJxkCgYBLkQ/aAyrRVPyVnFZoFe8E\\nr15SoddI5cqlWpCh6e2DAOgTtfMVydm+4TEQx4CeK6fDIatk+ZftV2kY2Ko0R4XL\\npM4XiF8sCm8JB2T12hmFdqyfg4ht86zmRw9XL+OFhJoEE2+BYyl53WJ22MMeaiJN\\nGY6lN/vkD+ht/Gw+Q6FTNg==\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-c4mp6@kapaksh.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"111546179413685310749\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-c4mp6%40kapaksh.iam.gserviceaccount.com\"\n" +
                    "}";

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
    }


}
