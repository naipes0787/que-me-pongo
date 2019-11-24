# 2019-vi-no-group-22

### Creación de la base de datos para probar
El proyecto utiliza como motor de base de datos [PostgreSQL](https://www.postgresql.org/download/).

Tener en cuenta que el persistence.xml tiene la propiedad `hibernate.hbm2ddl.auto` en update. Para hacer pruebas podríamos pasarlo a create-drop.

Estando logueado en postgreSQL (Ya sea con interfaz gráfica o por consola) se crea la base de datos y luego el usuario.
Por último se dan todos los permisos a dicho usuario para la base de datos en cuestión:

`create database quemepongo;`

`create user userdb with encrypted password 'mypass';`

`grant all privileges on database quemepongo to userdb;`


---

### Deploy en Heroku
Para poder realizar el deploy en Heroku se deberán seguir los siguientes pasos:

1. En caso de no tener Maven instalado, [instalarlo](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html). Asegurarse de que esté asociado al path del sistema operativo ejecutando por consola `mvn -v` (Lo cual debería darnos la versión de maven)
1. Crear usuario en Heroku desde [aquí](https://signup.heroku.com/dc)
1. Instalar la command line interface de Heroku siguiendo [estas instrucciones](https://devcenter.heroku.com/articles/heroku-cli)
1. Ejecutar `heroku login` para loguearnos
1. Situarse en el directorio del proyecto y ejecutar `heroku create que-me-pongo-grupo-22` para crear la aplicación en Heroku
1. Luego ejecutar `heroku addons:create heroku-postgresql` para asociar una base de datos postgreSQL a nuestro proyecto
1. Configurar el archivo `persistence.xml` para que apunte a dicha base de datos (Podemos ver la URL, usuario y contraseña de la base de datos creada desde la página de [Heroku](https://dashboard.heroku.com/apps))
1. Ahora, para deployar, desde el mismo directorio ejecutar `mvn heroku:deploy`
1. Una vez que termine de ejecutar todo, listo! Ya está levantado en la URL que se muestra en la misma consola al finalizar. En caso de querer ver los logs puede ejecutarse `heroku logs --tail`

---

### DER
https://www.lucidchart.com/invitations/accept/ff3ffe0e-75bf-4900-a7fc-a4db119df8ee

### Diagrama de clases:
https://www.lucidchart.com/invitations/accept/24ede451-befc-456d-b021-42805038e22e
