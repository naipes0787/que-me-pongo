# 2019-vi-no-group-22

### Creación de la base de datos para probar
El proyecto utiliza como motor de base de datos [PostgreSQL](https://www.postgresql.org/download/).

Tener en cuenta que el persistence.xml tiene la propiedad hibernate.hbm2ddl.auto en update. Para hacer pruebas podríamos pasarlo a create-drop.

Estando logueado en postgreSQL (Ya sea con interfaz gráfica o por consola) se crea la base de datos y luego el usuario.
Por último se dan todos los permisos a dicho usuario para la base de datos en cuestión:

`create database quemepongo;`

`create user userdb with encrypted password 'mypass';`

`grant all privileges on database quemepongo to userdb;`


---

### Versión entregable del diagrama de clases:
https://www.lucidchart.com/invitations/accept/aeea09b0-f948-43b8-b9ac-f52843991a38


### Diagrama de clases en progreso:
https://www.lucidchart.com/invitations/accept/24ede451-befc-456d-b021-42805038e22e
