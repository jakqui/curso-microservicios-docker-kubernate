#1)IMAGEN 1: CON DEPENDENCIAS, CODIGO, ETC
FROM openjdk:8-jdk-alpine as builder

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

#Este comando utiliza el wrapper Maven (mvnw) para limpiar el proyecto, compilarlo y empaquetarlo en un archivo JAR, 
#omitiendo la ejecución de pruebas (-Dmaven.test.skip), 
#la compilación del código principal (-Dmaven.main.skip), 
#y la reempaquetación de Spring Boot (-Dspring-boot.repackage.skip).
#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#OTRA FORMA INSTRUCCION ANTERIOR
RUN ./mvnw dependency:go-offline

#Copeear el código fuente
COPY ./msvc-cursos/src ./src

RUN ./mvnw clean package -DskipTest








#2)IMAGEN 2:La imagen al final se construye de las siguientes instrucciones,
#Las anteriores sirvieron de base para la creación de la imagen final
FROM openjdk:8-jdk-alpine

WORKDIR /app

#CREAR DIRECTORIO PARA LOGS
RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/msvc-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

#NO PERMITE USAR EL CMD O LINEA DE COMANDOS
#ENTRYPOINT [ "java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar" ]
#PARA USAR LA LINEA DE COMANDOS
CMD [ "java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar" ]