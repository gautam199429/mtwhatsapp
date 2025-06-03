# MoneyTree WhatsApp API

A Spring Boot REST API for sending WhatsApp messages and integrating with the MoneyTree platform.

---

## Features

- Send WhatsApp messages (text, document, image, etc.) to one or multiple recipients
- Asynchronous message processing for fast API responses
- Feign client integration for WhatsApp API calls
- JPA/Hibernate with MySQL for configuration and message persistence
- Caching for frequently used configuration values
- Swagger/OpenAPI documentation
- Configuration management via database and properties files

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8+
- WhatsApp Business API credentials

### Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/whatsapp?useSSL=false
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD

whatsapp.api.url=https://your-whatsapp-api-url.com
spring.application.version=1.0.0
```

Set up required configuration keys in your database (e.g., access token, phone number ID, API version).

### Build & Run

```bash
mvn clean install
java -jar target/money-whatsapp-*.jar
```

---

## API Usage

### Send WhatsApp Message

**Endpoint:**  
`POST /api/{version}/messages/send/message`

**Headers:**  
`X-AUTH-TOKEN: <your-auth-token>`

**Body Example:**
```json
{
  "phoneNumbers": ["919999999999", "918888888888"],
  "type": "TEXT",
  "message": "Hello from MoneyTree!"
}
```

**Response:**
```json
{
  "message": "Message processing started in background.",
  "data": { ... }
}
```

---

## Documentation

Swagger UI is available at:  
`http://localhost:8070/swagger-ui.html`  
or  
`http://localhost:8070/swagger-ui/index.html`

---

## Project Structure

- `restcontroller/` - REST API controllers
- `service/` & `serviceimpl/` - Business logic and async processing
- `feignclient/` - Feign client for WhatsApp API
- `entitymodels/` - JPA entities and metadata
- `repository/` - Spring Data JPA repositories
- `configurations/` - Feign, Swagger, and other configurations

---

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## Developed By

Vartulz API Team  
[gautam@vartulz.com](mailto:gautam@vartulz.com)  
[Vartulz Technology Private Limited](https://vartulz.com)

---