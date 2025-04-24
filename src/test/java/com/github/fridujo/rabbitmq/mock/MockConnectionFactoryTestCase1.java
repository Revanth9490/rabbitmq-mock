package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MockConnectionFactoryTestCase1 {

    @Test
    void newConnection_shouldReturnNonNullConnection() {
        MockConnectionFactory factory = new MockConnectionFactory();
        assertNotNull(factory.newConnection(), "Connection should not be null");
    }
}
