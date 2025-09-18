# devsu-project

Microservicios spring boot

## Indice
1. [Descripcion](#descripcion)
2. [Tecnologias](#tecnologias)
3. [Swagger](#swagger)
4. [Kafka](#kafka)
5. [Instalacion](#instalacion)

### Descripcion
***
Servicios que implementacion la administracion de clientes, cuentas y bancos
## Tecnologias
***
* [Spring framework]: Version 2.7.9
* [Java]: Version 17

## Swagger
***
[Link Interfaz swagger](http://localhost:8080/swagger-ui/index.html#/)
[Link Interfaz swagger](http://localhost:8081/swagger-ui/index.html#/)

## Kafka
***
[Link Kafdrop](http://localhost:9000/)
## Instalacion
***
Pasos para su instalacion:

Descargar codigo fuente de bitbucket
```
git clone https://github.com/AbrahamEsca/devsu-project.git
```
Ejecutar comando Docker
```
docker-compose up --build
```
Consumir servicio
```
curl --location 'http://localhost:8080/api/clients'
curl --location 'http://localhost:8080/api/accounts'
curl --location 'http://localhost:8080/api/movements'
curl --location 'http://localhost:8080/api/reports'
```