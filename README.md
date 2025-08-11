# Orders Microservice

A modern Spring Boot 3.2.0 microservice that modernizes the original C program for order management.

## Features

- **RESTful API**: Complete CRUD operations for orders
- **Database Integration**: H2 in-memory database with JPA
- **Validation**: Input validation with proper error handling
- **Logging**: Comprehensive logging with SLF4J
- **Exception Handling**: Global exception handler with standardized error responses
- **Health Check**: Built-in health monitoring endpoints
- **Sample Data**: Pre-loaded with sample orders for testing

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Maven**

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd orders-microservice
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Orders Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/orders` | Create a new order |
| GET | `/api/v1/orders` | Get all orders |
| GET | `/api/v1/orders/{id}` | Get order by ID |
| PUT | `/api/v1/orders/{id}` | Update order by ID |
| DELETE | `/api/v1/orders/{id}` | Delete order by ID |

### Search Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/orders/search/customer?customerName={name}` | Search orders by customer name |
| GET | `/api/v1/orders/search/amount?minAmount={min}&maxAmount={max}` | Search orders by amount range |

### Utility Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/orders/health` | Health check |
| GET | `/h2-console` | H2 Database Console |

## API Usage Examples

### Create an Order
```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "John Doe",
    "amount": 150.50
  }'
```

### Get All Orders
```bash
curl http://localhost:8080/api/v1/orders
```

### Get Order by ID
```bash
curl http://localhost:8080/api/v1/orders/1
```

### Update an Order
```bash
curl -X PUT http://localhost:8080/api/v1/orders/1 \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "John Doe Updated",
    "amount": 200.00
  }'
```

### Delete an Order
```bash
curl -X DELETE http://localhost:8080/api/v1/orders/1
```

### Search by Customer Name
```bash
curl "http://localhost:8080/api/v1/orders/search/customer?customerName=John"
```

### Search by Amount Range
```bash
curl "http://localhost:8080/api/v1/orders/search/amount?minAmount=100&maxAmount=300"
```

## Database Schema

The `orders` table contains:
- `id`: Primary key (auto-generated)
- `customer_name`: Customer name (required, max 100 chars)
- `amount`: Order amount (required, positive decimal)
- `created_at`: Timestamp when order was created
- `updated_at`: Timestamp when order was last updated

## Configuration

The application uses `application.yml` for configuration:
- Server port: 8080
- H2 in-memory database
- JPA with Hibernate
- Comprehensive logging
- Health check endpoints

## Development

### Project Structure
```
src/main/java/com/example/orders/
├── OrdersApplication.java          # Main application class
├── controller/
│   └── OrderController.java       # REST API endpoints
├── service/
│   └── OrderService.java          # Business logic
├── repository/
│   └── OrderRepository.java       # Data access layer
├── entity/
│   └── Order.java                 # JPA entity
├── dto/
│   ├── OrderRequest.java          # Request DTO
│   └── OrderResponse.java         # Response DTO
├── exception/
│   ├── GlobalExceptionHandler.java # Exception handling
│   └── ErrorResponse.java         # Error response model
└── config/
    └── DataLoader.java            # Initial data loader
```

### Adding New Features
1. Create entity classes in the `entity` package
2. Add repository interfaces in the `repository` package
3. Implement business logic in the `service` package
4. Create REST endpoints in the `controller` package
5. Add DTOs in the `dto` package if needed

## Testing

### Run Tests
```bash
mvn test
```

### Manual Testing
1. Start the application
2. Use the provided curl commands or Postman
3. Access H2 console at `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:ordersdb`
   - Username: `sa`
   - Password: `password`

## Monitoring

- Health check: `http://localhost:8080/api/v1/orders/health`
- H2 Console: `http://localhost:8080/h2-console`
- Application logs are displayed in the console

## Comparison with Original C Code

| Feature | C Program | Spring Boot Microservice |
|---------|-----------|--------------------------|
| **Data Storage** | Text file | H2 Database with JPA |
| **Input Method** | Console input | REST API |
| **Data Validation** | Basic | Comprehensive validation |
| **Error Handling** | Basic | Global exception handling |
| **Scalability** | Single user | Multi-user, scalable |
| **Data Persistence** | File-based | Database with transactions |
| **API** | None | RESTful API |
| **Search** | None | Advanced search capabilities |
| **Monitoring** | None | Health checks, logging |

## Future Enhancements

- Add authentication and authorization
- Implement caching with Redis
- Add message queuing for async processing
- Implement API versioning
- Add comprehensive unit and integration tests
- Docker containerization
- Kubernetes deployment manifests
- Metrics and monitoring with Micrometer
- API documentation with OpenAPI/Swagger

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.
