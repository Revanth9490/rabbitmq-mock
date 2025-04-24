package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockChannelTestCase9 {

    @Test
    void reDeclareQueueWithDifferentFlags_shouldThrowOrPassSilently() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "conflicting-queue";

        // Declare queue initially with durable=false
        channel.queueDeclare(queueName, false, false, false, null);

        // Now try to re-declare with different flags (durable=true)
        // Some mocks may throw, others may ignore — we test & document
        assertDoesNotThrow(() -> {
            channel.queueDeclare(queueName, true, false, false, null);
        }, "Re-declaring queue with conflicting durable flag should not throw (mock behavior)");
    }
}
