# Solemne 3

**TALLER DE DESARROLLO DE APLICACIONES EMPRESARIALES I**

**PROFESOR:** DAVID ENAMORADO GUZMAN

**CODIGO:** CI206IECIRE-6

**SECCION:** 6

**Alumno:** Samuel Barrera Bastidas

---

# 1.- Creacion del proyecto base

**Paso I** - Determinamos el nombre del proyecto, en este caso **aesolemne3**

**Paso II** - Ejecutamos los siguientes comandos:

```shell
mvn archetype:generate -DgroupId=cl.ciisa.web -DartifactId=aesolemne3 -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

cd aesolemne3
echo "# Solemne 3" >> README.md
echo "target/" >> .gitignore 
echo 'web: java -jar target/*.jar --port $PORT' >> Procfile

mkdir src/main/java
mkdir src/main/java/cl
mkdir src/main/java/cl/ciisa
mkdir src/main/java/cl/ciisa/services
echo "" >> src/main/java/cl/ciisa/services/AppConfig.java
echo "" >> src/main/java/cl/ciisa/services/Servicio.java

mkdir src/main/java/cl/ciisa/data

mkdir scripts
echo "" >> scripts/tbl_cursos.sql
echo "" >> scripts/tbl_alumnos.sql

echo "" >> nb-configuration.xml
echo "" >> app.json

mkdir src/main/resources/META-INF
echo "" >> src/main/resources/META-INF/persistence.xml

echo "" >> src/main/webapp/WEB-INF/glassfish-resources.xml
```

Con esto creamos la estructura basica del proyecto y los principales elementos que dispondremos


---

# 2.- Versionamos nuestro proyecto

Para este paso es necesario disponer de un repositorio, en este caso opto por Github y aqui creo un repositorio, para iniciar el versionamiento usamos el siguiente comando:

```shell
git init
git add .
git commit -am "Solemne 3"
git remote add origin https://github.com/silverfox78/CiisaAeSolemne3.git
git push -u origin master
```

Si deseamos guardar nuestras credenciales (por comodidad), podemos usar este comando:

```shell
git remote -v
git config credential.helper store
git push https://github.com/silverfox78/CiisaAeSolemne3.git
Username: <-- su correo -->
Password: <-- su contraseña -->
```


---

# 3.- Creamos nuestro aplicacion en Heroku

En este caso usaremos un **Pipeline** que deployara nuestra aplicacion, esta la enlazamos al repositorio de GitHub.

Aqui creamos una aplicacion asociada al flujo, en este caso la llamare **aesolemne3**, esta debe quedar como **Produccion**

Con esto ya sabemos que la ruta de nuestra aplicacion sera: **https://aesolemne3.herokuapp.com/**

Tambien anexamos como recurso a la aplicacion nuestra BD, en este caso trabajaremos con Postgress.

Vamos a las configuraciones de la aplicacion y anexamos esta variable:

**MAVEN_CUSTOM_GOALS : install payara-micro:bundle**

***Por el minuto no habilitaremos el **Deploy Automatico*****


---

# 4.- Edicion de los archivos principales

Antes de trabajar o abrir nuestro proyecto como tal, primero nos aseguramos de editar algunos archivos del proyecto.

## POM.xml

Este archivo esta en la raiz del proyecto y editamos su contenido para que sea como el siguiente texto:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>cl.ciisa.web</groupId>
  <artifactId>aesolemne3</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>CursoWeb</name>
  <url>https://aesolemne3.herokuapp.com/</url>

  <properties>
    <endorsed.dir>${project.build.directory}/endorsed</endorsed.dir>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.eclipse.persistence</groupId>
      <artifactId>eclipselink</artifactId>
      <version>2.5.2</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.eclipse.persistence</groupId>
      <artifactId>org.eclipse.persistence.jpa.modelgen.processor</artifactId>
      <version>2.5.2</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>javax.ws.rs</groupId>
      <artifactId>javax.ws.rs-api</artifactId>
      <version>2.0.1</version>
      <type>jar</type>
    </dependency>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.4</version>
      <type>jar</type>
    </dependency>
    <dependency>
      <groupId>javax</groupId>
      <artifactId>javaee-web-api</artifactId>
      <version>7.0</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
      <version>9.4-1203-jdbc4</version>
    </dependency>
  </dependencies>
  <build>
    <finalName>aesolemne3</finalName>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
          <compilerArguments>
            <endorseddirs>${endorsed.dir}</endorseddirs>
          </compilerArguments>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>2.3</version>
        <configuration>
          <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>2.6</version>
        <executions>
          <execution>
            <phase>validate</phase>
            <goals>
              <goal>copy</goal>
            </goals>
            <configuration>
              <outputDirectory>${endorsed.dir}</outputDirectory>
              <silent>true</silent>
              <artifactItems>
                <artifactItem>
                  <groupId>javax</groupId>
                  <artifactId>javaee-endorsed-api</artifactId>
                  <version>7.0</version>
                  <type>jar</type>
                </artifactItem>
              </artifactItems>
            </configuration>
          </execution>
        </executions>
      </plugin>

      <plugin>
        <groupId>fish.payara.maven.plugins</groupId>
        <artifactId>payara-micro-maven-plugin</artifactId>
        <version>1.0.2</version>
      </plugin>
    </plugins>
  </build>
</project>
```



## APP.json

Este archivo esta en la raiz del proyecto y editamos su contenido para que sea como el siguiente texto:

```json
{
  "name": "aesolemne3",
  "description": "Proyecto CIISA - Taller de desarrollo de aplicaciones empresariales I - Solemne 3",
  "scripts": {
  },
  "env": {
    "MAVEN_CUSTOM_GOALS": "install payara-micro:bundle"
  },
  "formation": {
  },
  "addons": [

  ],
  "buildpacks": [

  ]
}

```



## GLASSFISH-RESOURCES.XML

Este archivo esta en la ruta: ** src\main\webapp\WEB-INF\ ** del proyecto y editamos su contenido para que sea como el siguiente texto:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE resources PUBLIC "-//GlassFish.org//DTD GlassFish Application Server 3.1 Resource Definitions//EN" "http://glassfish.org/dtds/glassfish-resources_1_5.dtd">
<resources>
    <jdbc-connection-pool allow-non-component-callers="false" associate-with-thread="false" connection-creation-retry-attempts="0" connection-creation-retry-interval-in-seconds="10" connection-leak-reclaim="false" connection-leak-timeout-in-seconds="0" connection-validation-method="auto-commit" datasource-classname="org.postgresql.ds.PGSimpleDataSource" fail-all-connections="false" idle-timeout-in-seconds="300" is-connection-validation-required="false" is-isolation-level-guaranteed="true" lazy-connection-association="false" lazy-connection-enlistment="false" match-connections="false" max-connection-usage-count="0" max-pool-size="32" max-wait-time-in-millis="60000" name="post-gre-pooldeconexion" non-transactional-connections="false" pool-resize-quantity="2" res-type="javax.sql.DataSource" statement-timeout-in-seconds="-1" steady-pool-size="8" validate-atmost-once-period-in-seconds="0" wrap-jdbc-objects="false">
        <property name="serverName" value="ec2-23-21-188-236.compute-1.amazonaws.com"/>
        <property name="portNumber" value="5432"/>
        <property name="databaseName" value="d5ue3bghpdgqks"/>
        <property name="User" value="zxrksxbuvmwrjt"/>
        <property name="Password" value="12b1972a5926ee9e29c1695eeed490915186cde0f0cb06511d32c128380ba652"/>
        <property name="driverClass" value="org.postgresql.Driver"/>
        <property name="sslmode" value="require"/>
    </jdbc-connection-pool>
    <jdbc-resource enabled="true" jndi-name="java:app/DBCurso" object-type="user" pool-name="post-gre-pooldeconexion"/>
</resources>
```

Para la edicion de este archivo, requerimos primero ver los datos de nuestra base de datos en Heroku, este este caso buscamos saber los siguientes valores:

| Campo Heroku | Propiedad a editar |
| :----------: | :----------------: |
| Host         | serverName         |
| Database     | databaseName       |
| User         | User               |
| Port         | portNumber         |
| Password     | Password           |




## PERSISTENCE.xml

Este archivo esta en la ruta: ** src\main\resources\META-INF\ ** del proyecto y editamos su contenido para que sea como el siguiente texto:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
  <persistence-unit name="DBCurso" transaction-type="RESOURCE_LOCAL">
    <non-jta-data-source>java:app/DBCurso</non-jta-data-source>
    <class>cl.ciisa.data.DBCurso</class>
    <class>cl.ciisa.data.DBAlumno</class>
    <exclude-unlisted-classes>false</exclude-unlisted-classes>
    <properties/>
  </persistence-unit>
</persistence>
```

En este caso, usamos el nombre de la conexion que especificamos en el archivo **GLASSFISH-RESOURCES.XML**, tambien listamos las clases que usaremos, en este caso **DBCurso** y **DBAlumno**, estas representaran a las tablas que crearemos.




## INDEX.jsp

Este archivo esta en la ruta: ** src\main\webapp\ ** del proyecto y editamos su contenido para que sea como el siguiente texto:

```jsp
<html>
<body>
<h1>Servicio de Cursos y Alumnos</h1>
<hr>
</body>
</html>
```



## APPCONFIG.java

Este archivo esta en la ruta: ** src\main\java\cl\ciisa\services\ ** del proyecto y editamos su contenido para que sea como el siguiente texto:

```java

package cl.ciisa.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class AppConfig extends Application{
    
}

```



## Servicio.java

Este archivo esta en la ruta: ** src\main\java\cl\ciisa\services\ ** del proyecto y editamos su contenido para que sea como el siguiente texto:

```java
package cl.ciisa.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cl.ciisa.data.DBCurso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

@Path("/curso")
public class Servicio {
    public static final String DBCURSO = "DBCurso";
    public static final String MetodoNoImplementado = "Este metodo aun no se implemente";

    private enum MensajeError{
        listaCursos("Error al listar los curso - "),
        buscarCursoPorId("Error, no se encontro el curso - "),
        guardarCurso("Error al guardar el curso - "),
        actualizaCurso("Error al actualizar el curso - ");
        eliminaCurso("Error al eliminar el curso - ");

        private String mensaje;

        Mensaje(String mensaje){
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return this.mensaje;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaCursos() {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        Response retorno = null;

        try {
            List<DBCurso> lista = em.createNamedQuery(DBCURSO + ".findAll").getResultList();
            if (lista.size() <= 0) {
                retorno = Response.noContent().build();
            } else {
                retorno = Response.ok().entity(lista).build();
            }
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.listaCursos.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;*/
        Response.serverError().entity(MetodoNoImplementado).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCursoPorId(@PathParam("id") String id) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        Response retorno = null;

        try {
            DBCurso curso = em.find(DBCurso.class, Integer.parseInt(id));
            retorno = Response.ok(curso).build();
        } catch (Exception e) {
            retorno = Response.serverError().entity(MensajeError.buscarCursoPorId.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }

        return retorno;*/
        Response.serverError().entity(MetodoNoImplementado).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCurso(DBCurso curso) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            tx.begin();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            curso.setCrtdFecha(date);
            em.persist(curso);
            tx.commit();
            
            retorno = Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.guardarCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;*/
        Response.serverError().entity(MetodoNoImplementado).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaCurso(DBCurso curso) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            tx.begin();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            curso.setLupdFecha(date);
            curso = em.merge(curso);
            tx.commit();
            
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.actualizaCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;*/
        Response.serverError().entity(MetodoNoImplementado).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminaCurso(@PathParam("id") String id) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            tx.begin();
            DBCurso curso = em.getReference(DBCurso.class, Integer.parseInt(id));
            em.remove(curso);
            tx.commit();
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity(MensajeError.eliminaCurso.getMensaje() + e.getMessage()).build();
        } finally {
            em.close();
        }

        return retorno;*/
        Response.serverError().entity(MetodoNoImplementado).build();
    }
}

```

***Esto solo sera la cascara de los metodos que construiremos, esta incompleta y tiene errores, pero sera la base de lo que haremos.***