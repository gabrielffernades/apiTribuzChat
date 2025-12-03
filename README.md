# API TribuzChat - Backend

API REST desenvolvida com Spring Boot para o sistema de chat e comunidades (tribos).

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## 📦 Pré-requisitos

- Java 21 ou superior
- PostgreSQL instalado e rodando
- Maven (ou use o Maven Wrapper incluído)

## 🔧 Configuração

### 1. Banco de Dados

Crie o banco de dados PostgreSQL:

```sql
CREATE DATABASE tribuz_chat;
```

### 2. Application Properties

Configure `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tribuz_chat
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

## 🏃 Executando o Projeto

### Usando Maven Wrapper

```bash
# Dar permissão de execução (primeira vez)
chmod +x ./mvnw

# Executar
./mvnw spring-boot:run
```

### Usando Scripts

```bash
# Parar processos na porta 8080
./stop.sh

# Iniciar servidor
./start.sh
```

O servidor estará disponível em `http://localhost:8080`

## 📋 Endpoints da API

### Usuários

#### `POST /usuarios`
Cria um novo usuário.

**Request Body:**
```json
{
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao@email.com",
  "senha": "senha123",
  "data_nascimento": "1990-01-15",
  "icone": "person"
}
```

**Nota:** O campo `icone` é opcional. Se não fornecido, um ícone aleatório será atribuído automaticamente entre 30 opções disponíveis (person, face, account_circle, music_note, code, etc.).

**Response:**
- `201 Created` - Usuário criado com sucesso
- `400 Bad Request` - Erro de validação ou CPF duplicado

#### `GET /usuarios`
Lista todos os usuários.

**Response:**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "cpf": "12345678900",
    "email": "joao@email.com",
    "icone": "person",
    "data_nascimento": "1990-01-15",
    "data_cadastroUsuario": "2025-12-02T10:30:00"
  }
]
```

#### `POST /usuarios/login`
Autentica um usuário.

**Request Body:**
```json
{
  "nome": "João Silva",
  "senha": "senha123"
}
```

**Response:**
- `200 OK` - Usuário autenticado (retorna objeto Usuario)
- `401 Unauthorized` - Credenciais inválidas

#### `POST /usuarios/redefinir-senha`
Redefine a senha de um usuário pelo CPF.

**Request Body:**
```json
{
  "cpf": "12345678900",
  "novaSenha": "novaSenha123"
}
```

**Response:**
- `200 OK` - Senha redefinida com sucesso
- `400 Bad Request` - CPF ou senha não fornecidos
- `404 Not Found` - CPF não encontrado

### Tribos

#### `GET /tribos`
Lista todas as tribos.

**Response:**
```json
[
  {
    "id": 1,
    "nome": "Design Enthusiasts",
    "descricao": "Comunidade para amantes de design",
    "data_cadastro": "2025-12-02",
    "usuarios": []
  }
]
```

#### `POST /tribos`
Cria uma nova tribo.

**Request Body:**
```json
{
  "nome": "Design Enthusiasts",
  "descricao": "Comunidade para amantes de design"
}
```

**Response:**
- `201 Created` - Tribo criada com sucesso
- `400 Bad Request` - Erro de validação

### Grupos

#### `GET /grupos`
Lista todos os grupos.

#### `POST /grupos`
Cria um novo grupo.

## 🗄️ Modelos de Dados

### Usuario
```java
- id: Long (PK, auto-incremento)
- nome: String (obrigatório)
- cpf: String (obrigatório, único)
- email: String (opcional)
- senha: String (obrigatório)
- icone: String (Material Icons - gerado automaticamente se não fornecido)
- data_nascimento: LocalDate (obrigatório)
- data_cadastroUsuario: LocalDate (auto-preenchido)
- grupos: List<Grupo> (ManyToMany)
- tribos: List<Tribo> (ManyToMany)
- posts: List<Post> (ManyToMany)
- comentarios: List<Comentario> (ManyToMany)
```

### Tribo
```java
- id: Long (PK, auto-incremento)
- nome: String (obrigatório)
- descricao: String (opcional)
- data_cadastro: LocalDate (auto-preenchido)
- usuarios: List<Usuario> (ManyToMany)
```

## 🔧 Configurações Especiais

### Jackson (Serialização de Datas)

O projeto usa uma configuração global do Jackson para formatar datas:

- **Formato:** `yyyy-MM-dd`
- **Configuração:** `com.tribuzchat.api.config.JacksonConfig`

### Geração Automática de Ícones

O sistema atribui automaticamente um ícone Material Icons para cada usuário criado:

- **30 ícones disponíveis:** person, face, account_circle, sentiment_satisfied, mood, person_outline, face_3, face_4, face_5, face_6, tag_faces, waving_hand, self_improvement, sports_esports, music_note, palette, code, fitness_center, book, camera_alt, restaurant, flight, school, business, science, psychology, favorite, star, celebration
- **Atribuição:** Aleatória se o campo `icone` não for fornecido no cadastro
- **Implementação:** `com.tribuzchat.api.service.UsuarioService.criarUsuario()`

### Tratamento de Erros

Os controllers retornam erros estruturados:

```json
{
  "message": "Mensagem de erro",
  "error": "TipoDoErro"
}
```

## 🐛 Troubleshooting

### Porta 8080 já em uso

```bash
# Verificar processo
lsof -i :8080

# Parar processo
kill -9 <PID>

# Ou use o script
./stop.sh
```

### Erro de sequência do PostgreSQL

Se houver erro de chave duplicada:

```sql
SELECT setval(
  pg_get_serial_sequence('tb_usuario', 'id'), 
  COALESCE((SELECT MAX(id) FROM tb_usuario), 0) + 1, 
  false
);
```

### Erro de compilação

```bash
# Limpar e recompilar
./mvnw clean compile

# Ou reinstalar dependências
./mvnw clean install
```

## 📝 Notas de Desenvolvimento

- As datas são tratadas como `LocalDate` no formato `yyyy-MM-dd`
- CPF é armazenado sem formatação (apenas números)
- A senha é armazenada em texto plano (considerar hash em produção)
- O Hibernate cria/atualiza as tabelas automaticamente (`ddl-auto=update`)
- Ícones são armazenados como strings (nomes dos Material Icons)
- Cada usuário recebe um ícone aleatório se não especificado no cadastro
- O frontend usa os ícones para exibir avatares personalizados

## 🔒 Segurança

⚠️ **Nota:** Este é um projeto de desenvolvimento. Para produção, considere:

- Hash de senhas (BCrypt)
- Autenticação JWT
- Validação de entrada mais rigorosa
- CORS configurado adequadamente
- Rate limiting

## 📄 Licença

Este projeto é desenvolvido para fins educacionais.

---

**Desenvolvido para UNIESP** 🎓

