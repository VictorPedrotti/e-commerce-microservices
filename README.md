# 🛒 Spring Boot Microservices Shop

Este projeto é uma aplicação e-commerce simplificada desenvolvida com arquitetura de microsserviços. Cada serviço é independente, escalável e comunicante por meio de APIs REST e mensagens assíncronas com Kafka.

---

## 🧩 Arquitetura
![image](https://github.com/user-attachments/assets/d239685a-f3c8-448a-aeeb-5634f3ef37d6)

### Serviços Principais

- **Product Service**  
  Gerencia os produtos cadastrados. Utiliza MongoDB como base de dados.

- **Order Service**  
  Responsável pelo processamento de pedidos. Comunica-se de forma síncrona com o Inventory Service e de forma assíncrona com o Notification Service via Kafka. Utiliza MySQL.

- **Inventory Service**  
  Verifica e atualiza o estoque em tempo real, com base nos pedidos.

- **Notification Service**  
  Envia notificações por e-mail após pedidos serem processados (via Kafka).

- **API Gateway**  
  Implementado com Spring Cloud Gateway MVC, centraliza e roteia todas as requisições para os microsserviços.

- **Auth Server (Keycloak)**  
  Gerencia autenticação e autorização baseada em OAuth2/OpenID Connect.

- **Frontend (Angular 18)**  
  Interface web do e-commerce que consome os serviços através do API Gateway.

---

## ⚙️ Tecnologias Utilizadas

| Camada          | Tecnologias / Ferramentas                           |
|-----------------|-----------------------------------------------------|
| Backend         | Spring Boot, Spring Cloud Gateway MVC               |
| Frontend        | Angular 18                                          |
| Banco de Dados  | MongoDB (Product), MySQL (Order e Inventory)        |
| Mensageria      | Apache Kafka                                        |
| Segurança       | Keycloak (OAuth2 / OpenID Connect)                  |
| Testes          | Testcontainers, WireMock                            |
| Observabilidade | Prometheus, Grafana, Loki, Tempo                    |
| Resiliência     | Resilience4J                                        |
| Infraestrutura  | Docker, Kubernetes                                  |

---

## 🔍 Observabilidade

Todo o ecossistema está preparado para observabilidade com a stack Grafana:

- **Prometheus**: coleta métricas de serviços.
- **Grafana Tempo**: rastreamento distribuído.
- **Loki**: coleta e consulta de logs.
- **Grafana**: dashboard central com todos os dados integrados.

---

## 📦 Estrutura de Diretórios (Simplificada)
```
├── api-gateway/
├── product-service/
├── order-service/
├── inventory-service/
├── notification-service/
├── frontend/ (Angular)
└── k8s/ (manifests do Kubernetes)
```
