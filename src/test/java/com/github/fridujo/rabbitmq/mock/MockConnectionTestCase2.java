package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockConnectionTestCase2 {

    @Test
    void createChannel_shouldReturnValidMockChannel() {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        assertNotNull(connection, "Connection should not be null");

        MockChannel channel = (MockChannel) connection.createChannel();
        assertNotNull(channel, "Channel should not be null");
        assertTrue(channel instanceof MockChannel, "Should be instance of MockChannel");
    }
}

