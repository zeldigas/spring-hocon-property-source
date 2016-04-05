# Spring Hocon PropertySourceLoader

Project aims to add support for HOCON format in spring boot configuration files.
YAML is great, but some people prefer HOCON more so it's good to have a choice.

There are 2 modules:

1. spring-hocon-property-source - contains property source loader itself
2. hocon-property-example - contains simple spring-boot-starter-web project with demonstration
of hocon configuration

# How to use it in my spring boot code?

1. Add a dependency to your maven project (not in maven central for now, build and deploy yourself)

```
<dependency>
  <dependency>
      <groupId>com.github.zeldigas</groupId>
      <artifactId>spring-hocon-property-source</artifactId>
      <version>0.1-SNAPSHOT</version>
  </dependency>
```

2. In `META-INF/factories` file add the following line

```org.springframework.boot.env.PropertySourceLoader=com.github.zeldigas.spring.env.HoconPropertySourceLoader```

3. Place your HOCON configuration to *.conf files
4. Enjoy!