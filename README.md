# API Documentação

## Recursos

### Usuário (User)
- **ID**: Identificador único do usuário
- **Nome**: Nome do usuário
- **Email**: Email do usuário
- **Senha**: Senha do usuário (armazenada de forma segura)
- **Endereço**: Endereço de entrega do usuário
- **Tipo de Usuário**: Cliente, Proprietário de Loja, Administrador

### Loja (Store)
- **ID**: Identificador único da loja
- **Nome**: Nome da loja
- **Proprietário**: ID do usuário proprietário da loja
- **Descrição**: Descrição da loja
- **Endereço**: Endereço físico da loja
- **Telefone**: Telefone de contato da loja
- **Moeda**: Moeda utilizada pela loja
- **Idioma**: Idioma principal da loja

### Produto (Product)
- **ID**: Identificador único do produto
- **Nome**: Nome do produto
- **Descrição**: Descrição detalhada do produto
- **Preço**: Preço do produto
- **Quantidade em Estoque**: Quantidade disponível em estoque
- **Categoria**: Categoria do produto
- **Loja**: ID da loja que vende o produto
- **Imagens**: URLs das imagens do produto
- **Especificações**: Especificações técnicas do produto

### Carrinho de Compras (Shopping Cart)
- **ID**: Identificador único do carrinho
- **Usuário**: ID do usuário dono do carrinho
- **Produtos**: Lista de produtos no carrinho (ID do produto e quantidade)

### Pedido (Order)
- **ID**: Identificador único do pedido
- **Usuário**: ID do usuário que fez o pedido
- **Carrinho de Compras**: ID do carrinho de compras associado ao pedido
- **Endereço de Entrega**: Endereço para entrega do pedido
- **Status**: Status do pedido (Pendente, Processando, Enviado, Entregue, Cancelado)
- **Data de Criação**: Data em que o pedido foi criado
- **Data de Atualização**: Data da última atualização do pedido

### Pagamento (Payment)
- **ID**: Identificador único do pagamento
- **Pedido**: ID do pedido associado ao pagamento
- **Método de Pagamento**: Método utilizado para o pagamento (Cartão de Crédito, PayPal, etc.)
- **Status**: Status do pagamento (Pendente, Concluído, Falhou)
- **Data de Pagamento**: Data em que o pagamento foi realizado
- **Valor**: Valor pago

### Entrega (Delivery)
- **ID**: Identificador único da entrega
- **Pedido**: ID do pedido associado à entrega
- **Status**: Status da entrega (Pendente, Em Trânsito, Entregue, Falhou)
- **Data de Envio**: Data em que a entrega foi enviada
- **Data de Entrega**: Data estimada para entrega
- **Serviço de Entrega**: Serviço utilizado para a entrega (FedEx, UPS, etc.)
- **Número de Rastreamento**: Número de rastreamento do pacote

### Avaliação (Review)
- **ID**: Identificador único da avaliação
- **Produto**: ID do produto avaliado
- **Usuário**: ID do usuário que fez a avaliação
- **Classificação**: Classificação do produto (1 a 5 estrelas)
- **Comentário**: Comentário do usuário sobre o produto
- **Data de Criação**: Data em que a avaliação foi criada

## Endpoints

### Autenticação
#### POST /auth/signup
Cria um novo usuário.

```json
{
  "username": "string",
  "password": "string",
  "email": "string"
}
```
#### POST /auth/login
Autentica um usuário e retorna um token JWT.
```json
{
  "username": "string",
  "password": "string"
}
```
#### POST /auth/social
Autentica um usuário usando OAuth2 (Google, Facebook, etc.) e retorna um token JWT.
```json
{
  "provider": "string",
  "token": "string"
}
```
### Lojas
#### GET /stores
Retorna uma lista de todas as lojas.
```json 
🔒 Headers: Authorization: Bearer <token>
```
#### GET /stores/{id}
Retorna detalhes de uma loja específica.
```json 
🔒 Headers: Authorization: Bearer <token>
```
#### POST /stores
Cria uma nova loja.
```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "name": "string",
  "owner_id": "int",
  "description": "string",
  "address": "string",
  "phone": "string",
  "currency": "string",
  "language": "string"
}
```
#### PUT /stores/{id}
Atualiza informações de uma loja específica.
```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "name": "string",
  "description": "string",
  "address": "string",
  "phone": "string",
  "currency": "string",
  "language": "string"
}
```
#### DELETE /stores/{id}
Deleta uma loja específica.

```json 
🔒 Headers: Authorization: Bearer <token>
```
Produtos
#### GET /products
Retorna uma lista de todos os produtos.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### GET /products/{id}
Retorna detalhes de um produto específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### POST /products
Cria um novo produto.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "name": "string",
  "description": "string",
  "price": "float",
  "stock_quantity": "int",
  "category": "string",
  "store_id": "int",
  "images": ["string"],
  "specifications": "string"
}
```
#### PUT /products/{id}
Atualiza informações de um produto específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "name": "string",
  "description": "string",
  "price": "float",
  "stock_quantity": "int",
  "category": "string",
  "images": ["string"],
  "specifications": "string"
}
```
#### DELETE /products/{id}
Deleta um produto específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```

Carrinho de Compras
#### GET /carts/{user_id}
Retorna o carrinho de compras do usuário especificado.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### POST /carts
Adiciona um produto ao carrinho de compras do usuário.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "user_id": "int",
  "product_id": "int",
  "quantity": "int"
}
```
#### PUT /carts/{id}
Atualiza a quantidade de um produto no carrinho de compras.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "quantity": "int"
}
```
#### DELETE /carts/{id}
Remove um produto do carrinho de compras.

```json 
🔒 Headers: Authorization: Bearer <token>
```

Pedidos
#### GET /orders
Retorna uma lista de todos os pedidos.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### GET /orders/{id}
Retorna detalhes de um pedido específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### POST /orders
Cria um novo pedido.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "user_id": "int",
  "cart_id": "int",
  "shipping_address": "string"
}
```
#### PUT /orders/{id}
Atualiza informações de um pedido específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "status": "string",
  "shipping_address": "string"
}
```
#### DELETE /orders/{id}
Cancela um pedido específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```

Pagamentos
#### GET /payments
Retorna uma lista de todos os pagamentos.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### GET /payments/{id}
Retorna detalhes de um pagamento específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### POST /payments
Cria um novo pagamento.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "order_id": "int",
  "payment_method": "string",
  "amount": "float"
}
```
#### PUT /payments/{id}
Atualiza informações de um pagamento específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "status": "string"
}
```
#### DELETE /payments/{id}
Deleta um pagamento específico.

```json 
🔒 Headers: Authorization: Bearer <token>
```

Entregas
#### GET /deliveries
Retorna uma lista de todas as entregas.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### GET /deliveries/{id}
Retorna detalhes de uma entrega específica.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### POST /deliveries
Cria uma nova entrega.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "order_id": "int",
  "shipping_service": "string",
  "tracking_number": "string"
}
```
#### PUT /deliveries/{id}
Atualiza informações de uma entrega específica.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "status": "string",
  "shipping_service": "string",
  "tracking_number": "string"
}
```
#### DELETE /deliveries/{id}
Deleta uma entrega específica.

```json 
🔒 Headers: Authorization: Bearer <token>
```

Avaliações
#### GET /reviews
Retorna uma lista de todas as avaliações.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### GET /reviews/{id}
Retorna detalhes de uma avaliação específica.

```json 
🔒 Headers: Authorization: Bearer <token>
```

#### POST /reviews
Cria uma nova avaliação.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "product_id": "int",
  "user_id": "int",
  "rating": "int",
  "comment": "string"
}
```
#### PUT /reviews/{id}
Atualiza informações de uma avaliação específica.

```json 
🔒 Headers: Authorization: Bearer <token>
```
```json
{
  "rating": "int",
  "comment": "string"
}
```
#### DELETE /reviews/{id}
Deleta uma avaliação específica.

```json 
🔒 Headers: Authorization: Bearer <token>
```
## Segurança

### Autenticação
A autenticação será realizada utilizando JWT (JSON Web Tokens) para garantir a segurança das transações. Os tokens JWT serão gerados após o login bem-sucedido e devem ser enviados em cada requisição subsequente nos headers.

### Autorização
A autorização será baseada em níveis de acesso de usuários:

- **Cliente**: Pode visualizar e comprar produtos, e fazer avaliações.
- **Proprietário de Loja**: Pode gerenciar suas lojas e produtos.
- **Administrador**: Pode gerenciar todos os recursos do sistema.

### Internacionalização e Suporte a Múltiplas Moedas
O sistema deve suportar múltiplos idiomas e moedas para permitir a operação em diferentes países. A conversão de moedas pode ser realizada utilizando APIs de terceiros, como Open Exchange Rates.

## Integrações Externas (Opcional)

### Pagamentos
A API deve integrar com serviços de pagamento como Stripe e PayPal para processar pagamentos. Cada integração deve permitir a criação de pagamentos, verificação de status e tratamento de falhas.

### Envios
A integração com serviços de envio como FedEx, UPS e DHL deve permitir a criação de etiquetas de envio, rastreamento de pacotes e estimativa de custos de envio.

### Relatórios e Análise de Dados
A API deve fornecer endpoints para gerar relatórios e realizar análises de dados como:

- Relatórios de vendas por loja e período
- Relatórios de estoque
- Relatórios de desempenho de produtos
- Análise de avaliações e feedback dos clientes

### Notificações em Tempo Real
A API deve enviar notificações em tempo real para os usuários sobre atualizações de pedidos, entregas e outras atividades relevantes.

## Deploy e Escalabilidade
A API deve ser projetada para suportar escalabilidade horizontal e vertical utilizando tecnologias como Docker e Kubernetes para orquestração de contêineres.

## Testes
Testes unitários e de integração devem ser escritos para garantir a qualidade do código e a estabilidade da API. Ferramentas como PyTest e Django Test Framework podem ser utilizadas para esse propósito.