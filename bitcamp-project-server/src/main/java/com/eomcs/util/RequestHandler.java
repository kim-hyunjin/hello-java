package com.eomcs.util;

import java.lang.reflect.Method;

public class RequestHandler {
  Object bean;
  String name;
  Method method;

  public RequestHandler() {

  }

  public RequestHandler(Method method, String beanName, Object bean) {
    this.method = method;
    this.name = beanName;
    this.bean = bean;
  }

  public Object getBean() {
    return bean;
  }

  public void setBean(Object bean) {
    this.bean = bean;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

}
