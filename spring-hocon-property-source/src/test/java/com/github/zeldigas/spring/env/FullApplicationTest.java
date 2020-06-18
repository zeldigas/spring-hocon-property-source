package com.github.zeldigas.spring.env;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest("myApp.configuration.endpoints=[]")
public class FullApplicationTest {

    @Autowired
    private Environment environment;

    @Test
    public void hoconPropertySourceAnnotationWorks() {
        assertThat(environment.getProperty("my.hocon.property"), is("foo"));
    }

    @Test
    public void emptyLists() {
        assertThat(environment.getProperty("empty.list"), is(""));
    }

    @HoconPropertySource("classpath:hocon.conf")
    @SpringBootApplication
    static class TestSpringApplication {

    }
}
