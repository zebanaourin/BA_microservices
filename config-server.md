# Sprinb Cloud Config Server

## What is Spring Cloud Config Server?

A Spring Cloud Config Server is a centralized configuration server that manages the external properties for applications across all environments. It's a crucial component in a microservices architecture, where it helps maintain consistency and manage configurations centrally rather than scattered across individual services.

### Key Features of Spring Cloud Config Server

- Centralized Configuration Management: Stores all configuration properties in a central location, such as a Git repository, and serves them to all applications.

- Environment-specific Properties: Supports environment-specific properties for different environments like dev, qa, prod.

- Dynamic Configuration Updates: Allows applications to refresh their configurations without restarting, enabling real-time updates.

- Secure Configuration Management: Supports encryption of sensitive data like passwords, API keys, and certificates.
  Version Control Integration: Integrates with version control systems like Git, making it easy to track changes and roll back if needed.

### How It Works

- Config Server Setup: You set up a Spring Boot application as the Config Server. The server is pointed to a repository (e.g., Git) that contains the configuration files.

- Client Setup: Other Spring Boot applications are set up as Config Clients, which retrieve their configuration properties from the Config Server.

- Accessing Properties: The Config Clients access the server's properties using the application's name and environment (e.g., application-dev.yml for the dev environment).

- Refresh Scope: You can enable dynamic configuration updates by using the @RefreshScope annotation in your beans.

### Example Configuration File

Here's an example configuration file (application-dev.yml) stored in the Git repository:

**Config Server Configuration**

```yaml
server:
  port: 8888

spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-repo/config-repo
          clone-on-start: true
```

```java
// ConfigServerApplication.java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

**Config Client Configuration**

```java
// ConfigClientApplication.java
@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
```

```yaml
spring:
  config:
    import: configserver:http://localhost:8888
  application:
    name: config-client
  cloud:
    config:
      uri: http://localhost:8888
      name: application
      profile: dev
```

** Git repository structure**

```
config-repo
│
├── application-dev.yml
├── application-qa.yml
└── application-prod.yml
```

### Use cases

- **Microservices Configuration Management**: Centralized configuration management for microservices architecture.
- **Environment-specific Properties**: Manage environment-specific properties for different environments.
- **Dynamic Configuration Updates**: Enable real-time configuration updates without restarting applications.

### Conclusion

Spring Cloud Config Server is a powerful tool for managing configurations in a microservices architecture. It provides a centralized configuration management system that simplifies the process of managing configurations across multiple environments. By using Spring Cloud Config Server, you can ensure consistency, security, and real-time updates for your applications' configurations.
