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