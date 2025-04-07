package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockChannelTestCase7 {

    @Test
    void usingChannelAfterClose_shouldNotThrowException() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "closed-channel-queue";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.close();  // Closing the channel

        // Behavior: mock allows use of closed channel
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, "Still working?".getBytes());
        }, "Publishing after channel.close() should not throw (mock behavior)");
    }
}
