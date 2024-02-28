package com.example.Demo.Demo;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@EncryptablePropertySource(name = "test", value = "classpath:test.properties", ignoreResourceNotFound = true)
public class TestConfig {
}
