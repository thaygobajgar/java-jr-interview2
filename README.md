# Pré-requisitos

Antes de iniciar, certifique-se de ter os seguintes softwares instalados em sua máquina:

Java JDK 11 ou superior
Maven 3.6.0 ou superior
MySQL (ou qualquer banco de dados que você esteja usando)
Configuração do Ambiente
Banco de Dados

Crie um banco de dados no MySQL usando o seguinte comando:
CREATE DATABASE nome_do_seu_banco;

## Variáveis de Ambiente

Renomeio o arquivo .env.example na raiz do seu projeto para .env e faça as alterações das variaves de ambiente:

DB_URL=jdbc:mysql://localhost:5423/nome_do_seu_banco
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
EMAIL_HOST=smtp.exemplo.com
EMAIL_PORT=587
EMAIL_USERNAME=seu_email@exemplo.com
EMAIL_PASSWORD=sua_senha_de_email

## Executando o Projeto

Para rodar o projeto localmente, siga estas etapas:

1. Clone o Repositório
   git clone url_do_seu_repositorio
   cd nome_do_projeto

2. Carregue as Variáveis de Ambiente
   As variáveis de ambiente definidas no arquivo .env serão carregadas automaticamente quando o projeto iniciar.

3. Construa e Execute o Aplicativo
   No diretório raiz do projeto, execute:

mvn clean install
mvn spring-boot:run
O aplicativo será iniciado e estará acessível em http://localhost:8080.

Acessando a Aplicação
Após iniciar a aplicação, você pode acessar http://localhost:8080 no seu navegador para interagir com o projeto.
