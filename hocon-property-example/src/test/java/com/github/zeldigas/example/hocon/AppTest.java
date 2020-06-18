package com.github.zeldigas.example.hocon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.Assert.assertThat;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;


public class AppTest {


    private ConfigurableApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = SpringApplication.run(Application.class);
    }

    @After
    public void tearDown() throws Exception {
        context.close();
    }

    @Test
    public void applicationStartsAndReturnHoconConfig() {
        WebClient client = WebClient.create();

        String result = client.get().uri("http://localhost:8085/config").retrieve()
                .bodyToMono(String.class)
                .block();


        assertThat(result, sameJSONAs("{\"foo\":\"Hello\",\"bar\":42,\"aUri\":\"https://example.org/hello\",\"targetLocale\":\"en-us\",\"configuration\":{\"endpoints\":[\"one\",\"two\",\"three\"],\"connectionSettings\":{\"one\":\"hello\"}}}"));
    }
}
