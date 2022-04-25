# desafio-klok

## 🔧 Como executar

### ✅ Executar os containers dos bancos de dados
-> Basta digitar <code>docker-compose up -d</code><br>

### ✅ Compilar a bilbioteca necessária para as APIs

-> Entrar na raiz do projeto <code>library_rabbitmq</code><br>
-> Executar <code>mvn install</code> para criar a biblioteca localmente


### ✅ Executar as APIs

-> Entrar na pasta do projeto <code>api-adesoes</code> e executar <code>mvn spring-boot:run</code><br>
-> Entrar na pasta do projeto <code>cobranca-consumer-api</code> e executar <code>mvn spring-boot:run</code><br>

### ✅ Acessar o swagger
-> Entrar no endereço <code>http://localhost:8080/swagger-ui/index.html</code> para a <code>api-adesoes</code><br>
-> Entrar no endereço <code>http://localhost:8080/swagger-ui/index.html</code> para a <code>cobranca-consumer-api</code><br>

### ✅ Acessar as rotas

-> Gerar o token na rota <code>/authenticate</code> usando o usuário e senha padrão, ambos <code>foo</code><br>
-> Ao gerar o token, deve-se coloca-lo no campo <code>authorize</code> do swagger no seguinte formato: <br>
<code>Bearer tokenqualquer</code><br>
-> E então, as demais rotas poderão ser acessadas

### :pencil: Como funciona?

-> Ao agendar uma cobrança na <code>api-adesoes</code>, a <code>cobranca-consumer-api</code> a consome usando o <code>RabbitMQ</code> e a guarda no seu banco de dados, e então
é possível realizar o seu pagamento ao fazer uma chamata HTTP na rota <code>/cobrancas/pagamento/{idDaCobranca}</code><br>

