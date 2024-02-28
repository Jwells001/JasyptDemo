package com.example.Demo.Demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SimpleBean {
  private static final Logger LOG = LoggerFactory.getLogger(SimpleBean.class);
  @Value("${secret.property}")
  private String value;
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
}
