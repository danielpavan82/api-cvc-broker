# api-cvc-broker
Seguem os passos para a instalação e execução do projeto
Requisitos: Java 8
            Maven
Entre no endereço https://github.com/danielpavan82/api-cvc-broker.git, clique no botão clone or download e escolha a opção Download Zip
Após baixar os fontes do projeto para um diretório escolhido na sua máquina, descompacte o zip usando a opção Extract Here.
Abra o prompt e acesse o diretório api-cvc-broker, dentro desse diretório execute os comandos do Maven abaixo:
            mvn clean
            mvn dependency:tree
            mvn package
            mvn spring-boot:run
            
O projeto está configurado para o tomcat ser executado na porta default 8080.
Foi usado o Swagger para documentar os serviços disponíveis, acessar o endereço http://localhost:8080/swagger-ui.html

Foi utilizado o Lombok para gerar os getter/setter/toString automáticos via anotação. Lembrando que para funcionar é necessário fazer a instalação de acordo com a IDE utilizada. Para configurar, basta executar o lombok.jar e selecionar a pasta da IDE instalada. Para mais informações: https://projectlombok.org/setup/eclipse

O projeto possui teste unitário que pode ser executado pelo maven: mvn test
Também é possível executar os testes usando a página do swagger, abaixo seguem exemplos que devem ser utilizados para cada serviço:

-> Lista o valor da viagem de todos os hoteis de uma determinada cidade (/rest/v1/prices/city) - Conforme documentação esse serviço responde pelo HTTP GET deve ser enviado os paramêtros cityCode, checkin, checkout, qtdeAdultos e qtdeCriancas como querystring, caso contrário será lançado uma exception.

-> Lista o valor da viagem de um determinado hotel (/rest/v1/prices/hotel) - Conforme documentação esse serviço responde pelo HTTP GET deve ser enviado os paramêtros hotelCode, checkin, checkout, qtdeAdultos e qtdeCriancas como querystring, caso contrário será lançado uma exception.

Com o intuito de auxiliar nos testes integrados, foi criado uma classe RestClientUtil.java, ela possui recursos para execução de testes dos serviços criados, para isso é necessário acertar os paramêtros válidos enviados para os serviços.
