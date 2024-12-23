# mds-technical-task

This project is a Spring Boot-based REST API connected to a PostgreSQL database used for analysing stock data, and it can be run using Docker containers with Docker Compose. The application includes a database migration using Flyway to manage schema changes.

## Prerequisites

Before running the application, ensure you have the following installed on your machine:

- [Docker](https://www.docker.com/get-started) (with Docker Compose)
- [Maven](https://maven.apache.org/download.cgi) (for building the Spring Boot application)

## Project Structure

- **`src/`**: Contains the source code for the Spring Boot application.
- **`Dockerfile`**: The Docker configuration file for the Spring Boot application.
- **`docker-compose.yaml`**: The Docker Compose file to set up PostgreSQL and the Spring Boot application containers.
- **`application.properties`**: Configuration for Spring Boot (database, Flyway, etc.).

## Setting Up the Project

### 1. Build the Spring Boot Application

Before running the containers, you need to build the Spring Boot application and package it into a JAR file.

Run the following Maven command in the project root directory:

```bash
mvn clean package
```

This will generate the `mds-technical-task-0.0.1.jar` file in the `target/` directory.

### 2. Configure Docker Compose

Ensure your `docker-compose.yaml` file is correctly configured. It includes:

- **PostgreSQL**: The PostgreSQL container will be set up with the database `trading`, user `admin`, and password `aj241218`.
- **Spring Boot Application**: The application container is built using the `Dockerfile` in the repository, and it connects to the PostgreSQL database.

The `docker-compose.yaml` file should look like this:

```yaml
version: '3.8'
services:
  postgres:
    image: 'postgres:latest'
    environment:
      - 'POSTGRES_DB=trading'
      - 'POSTGRES_PASSWORD=aj241218'
      - 'POSTGRES_USER=admin'
    ports:
      - '5432:5432'
    networks:
      - mds-network

  app:
    build:
      context: .
      dockerfile: Dockerfile
    depends_on:
      - postgres
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/trading
      - SPRING_DATASOURCE_USERNAME=admin
      - SPRING_DATASOURCE_PASSWORD=aj241218
      - SPRING_FLYWAY_URL=jdbc:postgresql://postgres:5432/trading
      - SPRING_FLYWAY_USER=admin
      - SPRING_FLYWAY_PASSWORD=aj241218
    ports:
      - '8080:8080'
    networks:
      - mds-network

networks:
  mds-network:
    driver: bridge
```

### 3. Build and Run the Application with Docker Compose

To build and start the application and PostgreSQL containers, run the following command from the root of your project directory:

```bash
docker-compose up --build
```

This command will:

- Build the Docker image for your Spring Boot application.
- Start both the PostgreSQL and Spring Boot application containers.
- The Spring Boot application will be accessible on `http://localhost:8080`.

### 4. Accessing the Application

Once the containers are running, you can access the REST API at:

```
http://localhost:8080
```

You can test the REST endpoints using tools like [Postman](https://www.postman.com/) or `curl`.

### 5. Stopping the Containers

To stop the running containers, use the following command:

```bash
docker-compose down
```

This will stop and remove the containers, but it will not remove the Docker network or the images, allowing for faster restarts.

### 6. Logs

If you want to view the logs of your Spring Boot application, you can use:

```bash
docker-compose logs app
```

### 7. Database Migration with Flyway

Flyway is enabled in the Spring Boot application, and it will automatically manage database migrations when the application starts. If there are migration scripts in the `src/main/resources/db/migration` folder, Flyway will run them on startup.

## Usage

Once the application is up and running, you can use the following REST API endpoints to interact with the system.

Example:

- **GET /api/stocks** – View all stocks
- **POST /api/stocks** – Create new stock.
- **PUT /api/stocks/{id}** – Update stock with given id.
- **DELETE /api/stocks/{id}** - Delete stock with given id.
- **GET /api/stockhistory** – View all stocks history
- **POST /api/stockhistory** – Create new stock history.
- **PUT /api/stockhistory/{id}** – Update stock history with given id.
- **DELETE /api/stockhistory/{id}** - Delete stock history with given id.
- **POST /api/stocks/search** - Get stock trading information.

---

### API Endpoint Documentation

#### **GET** `/api/stocks`

This endpoint allows you to retrieve detailed information about all stocks. Information provided is id, name, code and establishedOn.

##### Request

- **URL**: `/api/stocks`
- **Method**: `GET`
- **Authentication**: None
- **Content-Type**: None

##### Response

The response returns details of the stocks.

###### **Success Response (HTTP 200)**

The response will contain all stock information.

```json
[
    {
        "id": 1,
        "code": "AAPL",
        "name": "Apple",
        "establishedOn": "1976-04-01T00:00:00.000+00:00"
    },
    {
        "id": 2,
        "code": "AMZN",
        "name": "Amazon",
        "establishedOn": "1994-07-05T00:00:00.000+00:00"
    },
    {
        "id": 3,
        "code": "META",
        "name": "Facebook",
        "establishedOn": "2004-02-04T00:00:00.000+00:00"
    },
    {
        "id": 4,
        "code": "GOOGLE",
        "name": "Google",
        "establishedOn": "1998-09-04T00:00:00.000+00:00"
    },
    {
        "id": 5,
        "code": "NFLX",
        "name": "Netflix",
        "establishedOn": "1997-08-29T00:00:00.000+00:00"
    }
]
```

#### **POST** `/api/stocks`

This endpoint allows the user to create new stock.

##### Request

- **URL**: `/api/stocks`
- **Method**: `POST`
- **Authentication**: None
- **Content-Type**: `application/json`

##### Request Body

The request body should contain the following JSON data:

```json
{
    "code" : "GOOGLE",
    "name" : "Google",
    "establishedOn" : "1998-09-04"
}
```

##### Response

The response returns the details of the created stock.

###### **Success Response (HTTP 200)**

```json
{
    "id" : 6,
    "code" : "GOOGLE",
    "name" : "Google",
    "establishedOn" : "1998-09-04"
}
```

###### **Error Response (HTTP 400 - Bad Request)**

If any validation fails (e.g., name empty, duplicate code..), the server will return an error message.

```json
{
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for argument [0] in public dev.mds.interview.mdstechnicaltask.model.Stock dev.mds.interview.mdstechnicaltask.controller.StockController.create(dev.mds.interview.mdstechnicaltask.model.Stock): [Field error in object 'stock' on field 'code': rejected value []; codes [NotBlank.stock.code,NotBlank.code,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [stock.code,code]; arguments []; default message [code]]; default message [Code is mandatory]] ",
    "path": "/api/stocks",
    "timestamp": "2024-12-23T03:56:08.441+00:00"
}
```

#### **PUT** `/api/stocks/{id}`

This endpoint allows the user to update the stock with given id.

##### Request

- **URL**: `/api/stocks`
- **Method**: `PUT`
- **Authentication**: None
- **Content-Type**: `application/json`
- 
##### URL Parameters

| Parameter   | Type   | Description                                | Example     |
|-------------|--------|--------------------------------------------|-------------|
| `id`    | Long | The stock id. This is a required path parameter. | `6`    |

##### Request Body

The request body should contain the following JSON data:

```json
{
    "id" : 6,
    "code" : "GOOGLE",
    "name" : "Google Inc",
    "establishedOn" : "1998-09-04"
}
```

##### Response

The response returns the details of the created stock.

###### **Success Response (HTTP 200)**

```json
{
    "id" : 6,
    "code" : "GOOGLE",
    "name" : "Google Inc",
    "establishedOn" : "1998-09-04"
}
```

#### **DELETE** `/api/stocks/{id}`

This endpoint allows a user to **remove a specific stock**. This operation deletes the stock.

##### Request

- **URL**: `/api/stocks/{id}`
- **Method**: `DELETE`
- **Authentication**: None
- **Content-Type**: `application/json`

##### URL Parameters

| Parameter    | Type   | Description                                    | Example     |
|--------------|--------|------------------------------------------------|-------------|
| `id` | Long | The stock id. | `6`    |

##### Response

The response confirms the successful deletion of the stock.

#### **POST** `/api/stocks/search`

This endpoint allows the user to get stock information for given period and it's predecending and following periods.

##### Request

- **URL**: `/api/stocks/search`
- **Method**: `POST`
- **Authentication**: None
- **Content-Type**: `application/json`

##### Request Body

The request body should contain the following JSON data:

```json
{
    "stockCode" : "AAPL",
    "dateFrom" : "2005-05-01",
    "dateTo" : "2005-05-20"
}
```

##### Response

The response returns the details about stock trading profits in the chosen period and it's predecending and following periods.

###### **Success Response (HTTP 200)**

```json
{
    "periods": [
        {
            "bestBuyDate": "2005-05-12",
            "bestBuyPrice": 1.218929,
            "bestSellDate": "2005-05-19",
            "bestSellPrice": 1.341071,
            "profit": 0.12214199999999997,
            "maxProfit": 0.31142799999999937,
            "moreProfitableStocks": [
                {
                    "id": 5,
                    "code": "NFLX",
                    "name": "Netflix",
                    "establishedOn": "1997-08-28T22:00:00.000+00:00"
                },
                {
                    "id": 4,
                    "code": "GOOGLE",
                    "name": "Google",
                    "establishedOn": "1998-09-03T22:00:00.000+00:00"
                },
                {
                    "id": 2,
                    "code": "AMZN",
                    "name": "Amazon",
                    "establishedOn": "1994-07-04T22:00:00.000+00:00"
                }
            ]
        },
        {
            "bestBuyDate": "2005-04-15",
            "bestBuyPrice": 1.2625,
            "bestSellDate": "2005-04-21",
            "bestSellPrice": 1.327857,
            "profit": 0.06535700000000011,
            "maxProfit": 0.2399990000000003,
            "moreProfitableStocks": [
                {
                    "id": 5,
                    "code": "NFLX",
                    "name": "Netflix",
                    "establishedOn": "1997-08-28T22:00:00.000+00:00"
                },
                {
                    "id": 4,
                    "code": "GOOGLE",
                    "name": "Google",
                    "establishedOn": "1998-09-03T22:00:00.000+00:00"
                },
                {
                    "id": 2,
                    "code": "AMZN",
                    "name": "Amazon",
                    "establishedOn": "1994-07-04T22:00:00.000+00:00"
                }
            ]
        },
        {
            "bestBuyDate": "2005-06-07",
            "bestBuyPrice": 1.305,
            "bestSellDate": "2005-06-09",
            "bestSellPrice": 1.344643,
            "profit": 0.039643000000000095,
            "maxProfit": 0.13392900000000063,
            "moreProfitableStocks": [
                {
                    "id": 5,
                    "code": "NFLX",
                    "name": "Netflix",
                    "establishedOn": "1997-08-28T22:00:00.000+00:00"
                },
                {
                    "id": 4,
                    "code": "GOOGLE",
                    "name": "Google",
                    "establishedOn": "1998-09-03T22:00:00.000+00:00"
                },
                {
                    "id": 2,
                    "code": "AMZN",
                    "name": "Amazon",
                    "establishedOn": "1994-07-04T22:00:00.000+00:00"
                }
            ]
        }
    ]
}
```

## License

This repository is **private** and for internal use only.

---
