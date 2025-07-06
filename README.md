# Product Catalog Demo

Sistema de catálogo de produtos com backend Java 21 e frontend Angular 20.

## 🛠️ Tecnologias

- **Backend**: Java 21 + Spring Boot
- **Frontend**: Angular 20
- **Banco**: Oracle XE 21c (Docker)

## 🚀 Como Executar

### 1. Banco de Dados

```bash
docker run -d \
  --name oracle-xe \
  -p 1521:1521 \
  -p 8080:8080 \
  gvenzl/oracle-xe:21-slim
```

### 2. Scripts do Banco

Execute os scripts em `product-catalog-api/src/main/resources/db/`:
1. `DDL_CATEGORY.sql` - Cria categorias (inclui carga de 10 categorias)
2. `DDL_PRODUCT.sql` - Cria produtos (inclui carga de 1000 produtos)

### 3. Backend (Porta 8081)

```bash
cd product-catalog-api
mvn spring-boot:run
```

### 4. Frontend (Porta 4200)

```bash
cd product-catalog-ui
npm install
npm run start
```

## 📝 URLs

- **Frontend**: http://localhost:4200
- **Backend**: http://localhost:8081
- **Oracle**: http://localhost:8080
- **Swagger**: http://localhost:8081/swagger-ui/index.html

## 🔧 Configuração

### application.properties
```properties
server.port=8081
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=system
spring.datasource.password=oracle
```

## ✨ Funcionalidades

- Listagem de produtos com scroll infinito
- Filtros por categoria, preço e avaliação
- Cards responsivos com rating visual
- API REST documentada com Swagger

## 📁 Estrutura

```
product-catalog-demo/
├── product-catalog-api/     # Backend Java 21
└── product-catalog-ui/      # Frontend Angular