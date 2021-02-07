package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    //dodac logi binarnych wiadomosci
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(9090).addService(new HelloServiceImpl()).build();

        try {
            server.start();
            server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
