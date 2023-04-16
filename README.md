# :hammer_and_wrench: Microservicios

## Arquitecturas Empresariales

### :pushpin: Daniel Felipe Hernández Mancipe

<br/>

En este proyecto se profundiza en los conceptos relacionados a los microservicios. El proceso que se sigue se describe a continuación:

1. Diseño de un API y desarrollo de un monolito con Quarkus que permite a los usuarios hacer posts de 140 caracteres e ir registrandolos en un stream único de posts (a la Twitter). Se piensa en tres entidades; usuario, hilo (stream) y post.

2. Desarrollo de una aplicación JavaScript para usar el servicio. Se despliega en S3 de AWS. Se asegura la disponibilidad web.

3. Configuración de seguridad de la aplicación usando Json Web Token con el servicio Cognito de AWS.

4. Descomposición de monolito en microservicios independientes.

5. Despliegue del servicio en AWS con EC2 (docker o 3 máquinas AWS).

[Demo](https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/g/personal/daniel_hernandez-ma_mail_escuelaing_edu_co/ERVYVKegfeBHtQNpLqxOq1oBDmaHTeHs13R_vu4DA1w6lg?e=lXZgKN)

## Getting Started

### Prerequisites

- Java >= 11.x
- Maven >= 3.x
- Git >= 2.x
- JUnit 4.x
- Quarkus 3.x

### Installing

Simplemente clone el repositorio:

```bash
git clone https://github.com/danielhndz/AREM-taller8.git
```

Luego compile uno de los dos subproyectos con maven, el ejemplo se hace con `Monolith`:

```bash
cd <project-folder>/<subproject-folder>
mvn clean install
```

![compile output](../media/monolith_mvn_compile.png?raw=true)

El ejemplo se muestra para el caso de `compile` pero la salida final para `install` debe ser la misma, un `BUILD SUCCESS` en verde.

### Using

El ejemplo se muestra para el caso del monolito, pero en el caso de microservicios es exactamente igual. Primero al entrar al index del proyecto se muestran los últimos tweets posteados:

![stream1](../media/stream1.png?raw=true)

Al hacer clic en el botón `Twittear`, se lleva al usuario a la página de inicio de sesión si este aún no se ha autenticado, en caso contrario se lleva directamente al formulario para poster un tweet:

![login](../media/login.png?raw=true)

Una vez se autentica el usuario correctamente, se redirige al usuario ahora si al formulario para postear un tweet:

![post_tweet](../media/post_tweet.png?raw=true)

Al hacer clic en el botón `Tweet`, se envía el tweet al API y se redirige al usuario al stream, donde se puede ver el tweet publicado:

![stream1](../media/stream2.png?raw=true)

## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [Git](https://git-scm.com/) - Version Management
- [JUnit4](https://junit.org/junit4/) - Unit testing framework for Java
- [Quarkus](https://quarkus.io/) - Kubernetes-native Java framework

## Design Metaphor

- Autor: Daniel Hernández
- Última modificación: 20/04/2023

### Backend Class Diagram

![Diagrama de paquetes con clases](../media/ClassDiagram1.png?raw=true)

Los nombres de los paquetes intentan ser representativos en términos de la funcionalidad que está implementada en dicho paquete.

- Las clases [TweetsEndpoint](/1.%20monolith/src/main/java/edu/escuelaing/arem/api/TweetsEndpoint.java) y [UsersEndPoint](/1.%20monolith/src/main/java/edu/escuelaing/arem/api/UsersEndPoint.java) modelan los endpoints de los usuarios y de los tweets.

- La clase [TweetController](/1.%20monolith/src/main/java/edu/escuelaing/arem/controller/TweetController.java) controla la interacción con la vista que permite postear tweets.

- La clase [InMemoryData](/1.%20monolith/src/main/java/edu/escuelaing/arem/utils/InMemoryData.java) persiste los datos en memoria y [AuthValidator](/1.%20monolith/src/main/java/edu/escuelaing/arem/utils/AuthValidator.java) valida los token de autorización de los usuarios.

## Authors

- **Daniel Hernández** - _Initial work_ - [danielhndz](https://github.com/danielhndz)

## License

This project is licensed under the GPLv3 License - see the [LICENSE.md](LICENSE.md) file for details

## Javadoc

Para generar Javadocs independientes para el proyecto en la carpeta `/target/site/apidocs` ejecute:

```bash
mvn javadoc:javadoc
```
