package com.fada.project3t5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
class Project3t5ApplicationTests extends CassandraSimpleIntegrationTestBase {

	@Test
	void contextLoads() {
	}

	@Test
	void givenCassandraContainer_whenSpringContextIsBootstrapped_thenContainerIsRunningWithNoExceptions() {
		assertThat(cassandra.isRunning()).isTrue();
	}
}
