package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockChannelTestCase3 {

    @Test
    void queueDeclare_andBasicPublish_shouldSucceed() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "test-queue";
        channel.queueDeclare(queueName, false, false, false, null);

        // Try publishing a message to the queue
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, "Hello".getBytes());
        }, "Should be able to publish to declared queue");
    }
}
