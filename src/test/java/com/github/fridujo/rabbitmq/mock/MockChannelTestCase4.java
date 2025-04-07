package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockChannelTestCase4 {

    @Test
    void basicPublish_shouldHandleEmptyAndLargeMessages() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "boundary-queue";
        channel.queueDeclare(queueName, false, false, false, null);

        // Test 1: Empty message
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, new byte[0]);
        }, "Should handle empty message");

        // Test 2: Large message (~1 MB)
        byte[] largeMessage = new byte[1024 * 1024]; // 1 MB
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, largeMessage);
        }, "Should handle large message (1MB)");

        // Optional Test 3: Just below a "fake" upper limit (e.g., 1.5 MB)
        byte[] almostTooLarge = new byte[1024 * 1024 + 512 * 1024]; // 1.5 MB
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, almostTooLarge);
        }, "Should handle 1.5MB message (boundary)");
    }
}
