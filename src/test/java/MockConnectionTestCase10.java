package com.github.fridujo.rabbitmq.mock;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MockConnectionTestCase10 {

    @Test
    void publishingAfterConnectionClose_shouldNotThrowOrPassSilently() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "connection-close-test";
        channel.queueDeclare(queueName, false, false, false, null);

        // Close the connection
        connection.close();

        // Try to publish after connection is closed
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, "Message".getBytes());
        }, "Publishing after closing connection should not throw an exception");
    }
}
