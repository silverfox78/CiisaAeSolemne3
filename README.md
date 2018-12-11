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

## POM.xml

Este archivo esta en la raiz del proyecto y editamos su contenido para que sea como el siguiente texto:

```xml
```