package com.aurd;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.aurd.Student.Repository.LiveSessionRepository;
import com.aurd.Student.WebSocket.ChatSocket;
import com.aurd.Student.WebSocket.WebSocketModel;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.aurd.Student.WebSocket.ChatSocket.liveSessions;

@ApplicationScoped
public class Service {

@Inject
    LiveSessionRepository liveSessionRepository;
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
    }

    public void onStop(@Observes ShutdownEvent event){
    }


}
