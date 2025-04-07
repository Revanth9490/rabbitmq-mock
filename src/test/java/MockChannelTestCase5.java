package com.github.fridujo.rabbitmq.mock;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class MockChannelTestCase5 {

    @ParameterizedTest
    @CsvSource({
        "true, false, false",
        "false, true, true",
        "true, true, true"
    })
    void queueDeclare_withDifferentFlags_shouldSucceed(boolean durable, boolean exclusive, boolean autoDelete) throws Exception {
        MockConnectionFactory factory = new MockConnectionFactory();
        MockConnection connection = (MockConnection) factory.newConnection();
        MockChannel channel = (MockChannel) connection.createChannel();

        String queueName = "decision-queue-" + durable + "-" + exclusive + "-" + autoDelete;

        assertDoesNotThrow(() -> {
            channel.queueDeclare(queueName, durable, exclusive, autoDelete, null);
        }, "Should successfully declare queue with flags: durable=" + durable + ", exclusive=" + exclusive + ", autoDelete=" + autoDelete);
    }
}
