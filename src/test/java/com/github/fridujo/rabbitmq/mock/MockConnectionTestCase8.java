package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockConnectionTestCase8 {

    @Test
    void multipleChannelsFromOneConnection_shouldOperateIndependently() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();

        // Create two channels from the same connection
        MockChannel channel1 = (MockChannel) connection.createChannel();
        MockChannel channel2 = (MockChannel) connection.createChannel();

        String queue1 = "channel1-queue";
        String queue2 = "channel2-queue";

        // Declare queues on both channels
        channel1.queueDeclare(queue1, false, false, false, null);
        channel2.queueDeclare(queue2, false, false, false, null);

        // Publish messages on both queues via different channels
        assertDoesNotThrow(() -> {
            channel1.basicPublish("", queue1, null, "Message from channel 1".getBytes());
            channel2.basicPublish("", queue2, null, "Message from channel 2".getBytes());
        }, "Both channels should operate independently without exception");
    }
}
