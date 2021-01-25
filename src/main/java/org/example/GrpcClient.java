package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.HelloRequest;
import org.example.grpc.HelloResponse;
import org.example.grpc.HelloServiceGrpc;

import java.util.Scanner;

public class GrpcClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        while (true) {
            System.out.println("Please provide first name (type 'exit' to stop):");
            String firstName = scanner.nextLine();
            if ("exit".equalsIgnoreCase(firstName)) {
                break;
            }
            System.out.println("Please provide last name (type 'exit' to stop):");
            String lastName = scanner.nextLine();
            if ("exit".equalsIgnoreCase(lastName)) {
                break;
            }
            HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .build());
            System.out.println(helloResponse.getGreeting());
        }
        channel.shutdown();
    }
}
