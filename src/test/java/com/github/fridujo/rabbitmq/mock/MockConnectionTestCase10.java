package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockConnectionTestCase10 {

    @Test
    void publishingAfterConnectionClose_shouldThrowOrPassSilently() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "connection-close-test";
        channel.queueDeclare(queueName, false, false, false, null);

        // Close the connection
        connection.close();

        // Now try to publish after connection is closed
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, "Message after connection close".getBytes());
        }, "Publishing after closing connection should not throw (mock behavior)");
    }
}

