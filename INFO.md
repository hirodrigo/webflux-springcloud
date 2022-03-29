# Webflux

Webflux é um módulo do Spring, baseado no projeto Reactor, que possibilita aplicações que usam o framework serem 
programadas de uma forma reativa. Isso faz com que as chamadas sejam processadas de forma assíncrona e não bloqueantes,
garantindo uma melhor performance e escalabidade para a aplicação.

https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html

# Config

O Spring Cloud Config fornece suporte para armazenar configurações externamente. Com o Config Server,
há uma aplicação central para gerir propriedades que podem ser acessadas por outros sistemas. Por isso, o 
Cloud Config permite que aplicações que usam suas propriedades para fluxos internos (ex. feature toggle) 
possam ter esses valores alterados sem a necessidade de reinicia-la por completo; seria somente necessário
acessar a URL de refresh da aplicação e ela buscaria os novos valores das propriedades no servidor.

https://spring.io/projects/spring-cloud-config

# Vault

O Spring Cloud Vault faz uma integração com o Vault da HashiCorp, que pode ser usado como um centralizador
para gerir propriedades secretas externas de aplicações. O Vault pode guardar segredos, como nome de usuário/senha
e fornecer credenciais para serviços externos. Também é possível fazer a integração do Cloud Config com o Cloud Vault,
para que as propriedades sejam guardadas no cofre e não fiquem num repositório.

https://www.vaultproject.io/
https://spring.io/projects/spring-cloud-vault

# Consul

O Spring Cloud Consul fornece integração com o Consul para aplicativos Spring Boot. Com algumas anotações simples, 
você pode criar um registro central que rastreie serviços, atualizações e situação health em tempo real. Além de poder
referenciar aplicações externas usando nomes definidos, sem a necessidade de saber o endereço de acesso delas.

https://www.consul.io/
https://spring.io/projects/spring-cloud-consul

# SpringFox

SpringFox tem como objetivo automatizar a geração de documentações de APIs JSON escritas usando as bibliotecas do Spring.
O Springfox funciona examinando um aplicativo em tempo de execução para documentar a semântica da API com base nas 
configurações, estrutura de classe e anotações Java.

https://springfox.github.io/springfox/