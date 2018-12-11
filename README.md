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