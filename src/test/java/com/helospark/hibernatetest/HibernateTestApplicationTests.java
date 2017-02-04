package com.helospark.hibernatetest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration test to reproduce HV-1088.
 * Run as JUnit test.
 * @author helospark
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HibernateTestApplicationTests {
	private static final int NUMBER_OF_THREADS = 100;
	private AtomicInteger failedCalls = new AtomicInteger(0);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void contextLoads() {
		failedCalls.set(0);

		// Create threads
		List<Thread> threads = IntStream.range(0, NUMBER_OF_THREADS)
				.boxed()
				.map(i -> new Thread(() -> callTestEndpoint()))
				.collect(Collectors.toList());

		// Start threads
		threads.stream()
				.forEach(thread -> thread.start());

		// Wait for them to finish
		threads.stream()
				.forEach(thread -> {
					try {
						thread.join();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

		// Assert on result
		int failedCallAsInt = failedCalls.intValue();
		Assert.assertEquals(failedCallAsInt + " calls have failed out of " + NUMBER_OF_THREADS, 0, failedCallAsInt);
	}

	private void callTestEndpoint() {
		try {
			ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/test", String.class);
			if (entity.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
				System.out.println(entity.getBody());
				failedCalls.incrementAndGet();
			}
			//	System.out.println(entity.getStatusCodeValue());
		} catch (Exception e) {
			e.printStackTrace();
			failedCalls.incrementAndGet();
		}
	}
}
