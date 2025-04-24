package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class mockChannelTestCase6 {

    @Test
    void basicPublish_toUndeclaredQueue_shouldNotCrash() throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "non-existent-queue";

        // Assert that publishing to undeclared queue does not throw
        assertDoesNotThrow(() -> {
            channel.basicPublish("", queueName, null, "Silent publish".getBytes());
        }, "Publishing to undeclared queue should not crash (mock behavior)");
    }
}
