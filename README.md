# Solemne 3

**TALLER DE DESARROLLO DE APLICACIONES EMPRESARIALES I**

**PROFESOR:** DAVID ENAMORADO GUZMAN

**CODIGO:** CI206IECIRE-6

**SECCION:** 6

**Alumno:** Samuel Barrera Bastidas

## Trabajo para nota Solemne 3

El trabajo debe ser realizado utilizando Payara Micro para ser desplegado en Heroku.

Desarrollar una API que implemente un CRUD. 

* La API se debe desarrollar bajo los principios arquitectónicos REST.(protocolo, verbos, estado, etc.)
* Los Request y los Response de la API deberán ser en formato  JSON.
* La persistencia deberá ser implementando  JPA. (Entity, em, emf, etc).
* La base de datos debe ser en Postgresql y desplegada en Heroku.
* La base de datos debe contener mínimo 2 (Dos) tablas las cuales deberàn estar relacionadas de forma 1:N (maestro detalle).
* Los valores de  configuración de la conexión a la base datos se deben pasar por medio de variables de entorno. (environment variable).
* Haciendo uso de un cliente REST (Postman), las operaciones CRUD correspondientes a las 2 (Dos) tablas relacionadas (1:N, maestro detalle) deben ser efectuadas con el mismo Request, es decir se deben enviar los datos de las dos tablas relacionadas en una sola petición a la API.


Ejemplo de JSON para Request con un Objeto anidado para operaciones CRUD con tablas relacionadas 1:N

```json
{
    "id": "10",
    "montototal": 200,
    "detallecompra":[
        {"id":"10", "idprod":"1", "nombre":"lapiz", "cantidad":2},
        {"id":"10", "idprod":"2", "nombre":"borrador", "cantidad":1}
    ]
}
```

Nota: 
Trabajos que implementen, o tomen los ejemplos realizados por el profesor en clase o por otro medio no serán tenidos en cuenta para la calificación.  
Se debe enviar por correo el enlace de la aplicación desplegada en Heroku, adicionalmente se debe enviar este mismo enlace a través del aula virtual.
Recuerden que me deben agregar como colaborador en Heroku. 

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
    public static final String MetodoNoImplementado = "{ 'Error' : 'Este metodo aun no se implementa.' }";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaCursos() {
        return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCursoPorId(@PathParam("id") String id) {
        return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCurso(DBCurso curso) {
        return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaCurso(DBCurso curso) {
        return Response.serverError().entity(MetodoNoImplementado).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminaCurso(@PathParam("id") String id) {
        return Response.serverError().entity(MetodoNoImplementado).build();
    }
}

```

***Esto solo sera la cascara de los metodos que construiremos, esta incompleta y tiene errores, pero sera la base de lo que haremos.***



---

# 5.- Creacion y ejecucion de los script

En este proyecto dispondremos de dos tablas, estas seran **TBL_CURSO** y **TBL_ALUMNO**.

Iniciaremos editando sus scripts.

## TBL_CURSO

| Campo | Tipo |
| ----- | ---- |
| id | integer |
| nombre | character varying(300) |
| profesor | character varying(300) |
| descripcion | character varying(1000) |
| nivel | int |
| basica | BOOLEAN |
| diurno | BOOLEAN |
| crtd_fecha | TIMESTAMP |
| lupd_fecha | TIMESTAMP |

```sql
CREATE SEQUENCE tbl_curso_seq;

CREATE TABLE public.tbl_curso
(
    id integer NOT NULL DEFAULT nextval('tbl_curso_seq'),
    nombre character varying(300) NOT NULL,
    profesor character varying(300) NOT NULL,
    descripcion character varying(1000),
    nivel int NOT NULL,
    basica BOOLEAN NOT NULL DEFAULT TRUE,
    diurno BOOLEAN NOT NULL DEFAULT TRUE,
    crtd_fecha TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    lupd_fecha TIMESTAMP,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

comment on table tbl_curso is 'Tabla destinada a contener el listado de cursos.';
comment on column tbl_curso.id is 'ID del registro';
comment on column tbl_curso.nombre is 'Nombre/Codigo del curso';
comment on column tbl_curso.profesor is 'Nombre del profesor jefe del curso';
comment on column tbl_curso.descripcion is 'Descripcion del curso';
comment on column tbl_curso.nivel is 'Nivel del curso';
comment on column tbl_curso.basica is 'Determina si es un curso de basica o media';
comment on column tbl_curso.diurno is 'Determina si el curso es diurno o nocturno';
comment on column tbl_curso.crtd_fecha is 'Fecha de creacion del registro';
comment on column tbl_curso.lupd_fecha is 'Ultima fecha de actualizacion del registro';

create index on tbl_curso(id);

ALTER TABLE public.tbl_curso
    OWNER to zxrksxbuvmwrjt;

ALTER SEQUENCE tbl_curso_seq
    OWNED BY tbl_curso.id;
```


## TBL_ALUMNO

| Campo | Tipo |
| ----- | ---- |
| id | integer |
| id_curso | integer |
| rut | character varying(20) |
| nombres | character varying(300) |
| apellidos | character varying(300) |
| fono | character varying(100) |
| correo | character varying(300) |
| fecnac | TIMESTAMP |
| sexo | BOOLEAN |
| crtd_fecha | TIMESTAMP |
| lupd_fecha | TIMESTAMP |

```sql
CREATE SEQUENCE tbl_alumno_seq;

CREATE TABLE public.tbl_alumno
(
    id integer NOT NULL DEFAULT nextval('tbl_alumno_seq'),
    id_curso integer NOT NULL REFERENCES tbl_curso(id) ON DELETE CASCADE,
    rut character varying(20) NOT NULL,
    nombres character varying(300) NOT NULL,
    apellidos character varying(300) NOT NULL,
    fono character varying(100) NOT NULL,
    correo character varying(300) NOT NULL,
    fecnac TIMESTAMP NOT NULL,
    sexo BOOLEAN NOT NULL,
    crtd_fecha TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    lupd_fecha TIMESTAMP,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

comment on table tbl_alumno is 'Tabla destinada a contener el listado de alumnos.';
comment on column tbl_alumno.id is 'ID del registro';
comment on column tbl_alumno.id_curso is 'ID del curso';
comment on column tbl_alumno.rut is 'RUT del alumno';
comment on column tbl_alumno.nombres is 'Nombres del Alumno';
comment on column tbl_alumno.apellidos is 'Apellidos del Alumno';
comment on column tbl_alumno.fono is 'Fono de contacto del Alumno';
comment on column tbl_alumno.correo is 'Correo de contacto del Alumno';
comment on column tbl_alumno.fecnac is 'Fecha de necimiento del Alumno';
comment on column tbl_alumno.sexo is 'Sexo del Alumno, TRUE: Masculino - FALSE: Femenino';
comment on column tbl_alumno.crtd_fecha is 'Fecha de creacion del registro';
comment on column tbl_alumno.lupd_fecha is 'Ultima fecha de actualizacion del registro';

create index on tbl_alumno(id);

ALTER TABLE public.tbl_alumno
    OWNER to zxrksxbuvmwrjt;

ALTER SEQUENCE tbl_alumno_seq
    OWNED BY tbl_alumno.id;
```



---

# 6.- Manos a la obra

Ya en este punto tenemos lo basico para iniciar el proyecto con nuestro ide, en este caso opto por **NetBeans**, asi que abrimos el proyecto desde la ruta donde lo tenemos.

Al abrise vemos que tenemos algunos errores, esto es debido a la ausencia de las Clases de entidad que persistiran nuestros datos, para ello lo primero es crear la conexion.

Tras esto: 
* Seleccionamos las tablas
* Renombramos las clases
* Seleccionamos el package de destino, en este caso **cl.ciisa.data**
* Seleccionamos el tipo de coleccion como **java.util.List**


| Tabla | Clase |
| ----- | ----- |
| TBL_ALUMNO | DBAlumno |
| TBL_CURSO | DBCurso |

Ahora que anexamos esto, estamos en condiciones de actualizar el repositorio y habilitar el Deploy desde Heroku.

Este proceso toma unos minutos, actualmente dejamos cinco metodos, estos son:

| Metodo | Verbo | Url  |
| :----- | :---: | :--- |
| Listar todos los cursos | GET | https://aesolemne3.herokuapp.com/api/curso |
| Listar un curso por su ID | GET | https://aesolemne3.herokuapp.com/api/curso/{id} |
| Guardar un curso | POST | https://aesolemne3.herokuapp.com/api/curso |
| Actualizar un curso | PUT | https://aesolemne3.herokuapp.com/api/curso |
| Elimina un curso por su ID | DELETE | https://aesolemne3.herokuapp.com/api/curso/{id} |

Para cada metodo, dejamos definido un retorno erroneo con el mensaje: **"Este metodo aun no se implementa."**

Para validar estos metodos, podemos hacerlo desde la consola por medio de un script (shell), este script requiere algunos archivos mas, la idea es la siguiente:

| Acrhivo | Proposito |
| ------- | --------- |
| EjecutaServicios.sh | Script que ejecutaremos |
| config.config | Aqui pondremos una plantilla para ejecucion del servicio |
| m[NN]_[NombreMetodo].config | Por cada metodo a probar, tendremos un archivo como estos |

Los metodos que por el minuto probaremos son:

| Metodo | Archivo de configuracion |
| :----- | -----------------------: |
| Listar todos los cursos | m01_listarCursos.config |
| Listar un curso por su ID | m02_listarCursoPorID.config |
| Guardar un curso | m03_guardaCurso.config |
| Actualizar un curso | m04_actualizaCurso.config |
| Elimina un curso por su ID | m05_eliminaCurso.config |

Con esto, el script **EjecutaServicios.sh** sera similar a lo siguiente:

```shell
#!/bin/bash
declare -a METODOS=(
    "m01_listarCursos"
	"m02_listarCursoPorID"
	"m03_guardaCurso"
	"m04_actualizaCurso"
	"m05_eliminaCurso")

EjecutarServicio(){
    Metodo=$1
    Fecha=$2
    Configuracion="$Metodo.config"
    Salida="Json_$Metodo$Fecha.json"

    NowDate=$(date +"%d/%m/%Y %H:%M:%S")
    echo "Se ejecuta el metodo [$Metodo]." > $Salida
    echo "Fecha: [$NowDate]." >> $Salida
    echo " " >> $Salida
    echo "Respuesta:" >> $Salida
    echo " " >> $Salida
    curl -K $Configuracion >> $Salida
}

Fecha=$(date +"%Y%m%d_%H%M%S")
Contador=0

for Metodo in "${METODOS[@]}"
do
    Contador=$((Contador+1))
    echo "$Contador.- Se ejecuta el metodo [$Metodo]."
    EjecutarServicio "$Metodo" "$Fecha"
done
```

Para ejecutarlo, simplemente vamos a la carpeta donde este el archivo **EjecutaServicios.sh** y lo ejecutamos desde la consola:

```shell
sh EjecutaServicios.sh
```

El resultado del proceso son una serie de archivos con el formato **Json_[NombreArchivoConfiguracion][Fecha].json** que quedaran en la misma ruta, el contenido de estos archivos sera similar a lo siguiente:


```
Se ejecuta el metodo [m01_listarCursos].
Fecha: [11/12/2018 19:37:30].
 
Respuesta:
 
{ "Error" : "Este metodo aun no se implementa." }




---------------------------------------------
        Codigo HTTP: 500
---------------------------------------------
    time_namelookup:  0,109
       time_connect:  0,281
    time_appconnect:  0,843
   time_pretransfer:  0,843
      time_redirect:  0,000
 time_starttransfer:  1,046
                    ----------
         time_total:  1,046
---------------------------------------------
      Byte Download: 49 bytes
       Byte Request: 167 bytes
        Byte Upload: 0 bytes
---------------------------------------------
```

Gracias a esto validamos rapidamente la lista de metodos publicados.

Por un tema de evitar tener basura en nuestro repositorio, opto por no versionar el resultado de las pruebas, por ello edito el archivo **.gitignore** anexando la siguiente linea:

```shell
ScriptShell/*.json
```


# 7.- Codificando el primer metodo

Ahora que sabemos que nuestro proceso de CI/CD esta operando y tambien sabemos que la publicacion fue exitosa y los servicios estan operando, es el minuto de implementer algo de codigo.

Iniciamos por el metodo que lista todos los cursos, para ello crearemos una clase que realize esta accion.

Los pasos para esto:
* Creamos un package para contener estas clases, en este caso **cl.ciisa.negocio**
* En el package creamos la clase **Curso**, aqui implementaremos la logica para esta tabla.
* Implementamos el metodo **listar**

## CURSO.java

```java
package cl.ciisa.negocio;

import cl.ciisa.data.DBCurso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

public class Curso {
    public static final String DBCURSO = "DBCurso";

    private enum MensajeError{
        listaCursos("Error al listar los curso - "),
        buscarCursoPorId("Error, no se encontro el curso - "),
        guardarCurso("Error al guardar el curso - "),
        actualizaCurso("Error al actualizar el curso - "),
        eliminaCurso("Error al eliminar el curso - ");

        private String mensaje;

        MensajeError(String mensaje){
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return this.mensaje;
        }
    }
    
    public Response listar(){
        Response retorno = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DBCURSO);
        EntityManager em = emf.createEntityManager();

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
        return retorno;
    }
}
```

Ya con esto listo, actualizamos el servicio, especificamente el metodo **listaCursos**

## SERVICIO.java - metodo:listaCursos

```java
@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaCursos() {
        return new Curso().listar();
    }
```

Con este cambio y la base sin datos, debemos esperar un codigo 204 (Sin datos).

Ejecutamos nuestras pruebas y vemos el resultado:

```
Se ejecuta el metodo [m01_listarCursos].
Fecha: [11/12/2018 20:10:49].
 
Respuesta:
 





---------------------------------------------
        Codigo HTTP: 204
---------------------------------------------
    time_namelookup:  0,000
       time_connect:  0,187
    time_appconnect:  0,734
   time_pretransfer:  0,734
      time_redirect:  0,000
 time_starttransfer:  0,953
                    ----------
         time_total:  0,953
---------------------------------------------
      Byte Download: 0 bytes
       Byte Request: 167 bytes
        Byte Upload: 0 bytes
---------------------------------------------
```

Esto se acomoda perfectamente a lo que esperabamos, asi que ahora anexaremos un par de registros de pruebas, para ello ejecutaremos el siguiente script:

```sql
INSERT INTO public.tbl_curso(
nombre, profesor, descripcion, nivel, basica, diurno, crtd_fecha)
VALUES ('1A', 'DAVID ENAMORADO GUZMAN', 'Curso 1A', 1, TRUE, TRUE, CURRENT_TIMESTAMP);


INSERT INTO public.tbl_curso(
nombre, profesor, descripcion, nivel, basica, diurno, crtd_fecha)
VALUES ('1B', 'SAMUEL BARRERA BASTIDAS', 'Curso 1B', 1, TRUE, TRUE, CURRENT_TIMESTAMP);
```

Tras la insercion de registros, volvemos a ejecutar la prueba, ahora el resultado es diferente:

```json
Se ejecuta el metodo[m01_listarCursos].
Fecha: [11 / 12 / 2018 20: 20: 20].

Respuesta:

        [{
                "DBAlumnoList": [],
                "basica": true,
                "crtdFecha": "2018-12-11T23:20:04.473Z[UTC]",
                "descripcion": "Curso 1A",
                "diurno": true,
                "id": 1,
                "nivel": 1,
                "nombre": "1A",
                "profesor": "DAVID ENAMORADO GUZMAN"
        }, {
                "DBAlumnoList": [],
                "basica": true,
                "crtdFecha": "2018-12-11T23:20:04.473Z[UTC]",
                "descripcion": "Curso 1B",
                "diurno": true,
                "id": 2,
                "nivel": 1,
                "nombre": "1B",
                "profesor": "SAMUEL BARRERA BASTIDAS"
        }]




-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
Codigo HTTP: 200
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
time_namelookup: 0, 188
time_connect: 0, 375
time_appconnect: 0, 953
time_pretransfer: 0, 953
time_redirect: 0, 000
time_starttransfer: 1, 359
-- -- -- -- --
time_total: 1, 359
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
Byte Download: 370 bytes
Byte Request: 167 bytes
Byte Upload: 0 bytes
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
```

Ahora validemos el proceso al insertar **Alumnos** en la base, la logica nos dice que deberian listarse junto a sus cursos.

