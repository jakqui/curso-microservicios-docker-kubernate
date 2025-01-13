ARG MSVC_NAME=msvs-usuarios

#1)IMAGEN 1: CON DEPENDENCIAS, CODIGO, ETC
FROM openjdk:8-jdk-alpine as builder
ARG MSVC_NAME
WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

#Este comando utiliza el wrapper Maven (mvnw) para limpiar el proyecto, compilarlo y empaquetarlo en un archivo JAR, 
#omitiendo la ejecución de pruebas (-Dmaven.test.skip), 
#la compilación del código principal (-Dmaven.main.skip), 
#y la reempaquetación de Spring Boot (-Dspring-boot.repackage.skip).
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#OTRA FORMA INSTRUCCION ANTERIOR
#RUN ./mvnw dependency:go-offline

#Copeear el código fuente
COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTest










#2)IMAGEN 2:La imagen al final se construye de las siguientes instrucciones,
#Las anteriores sirvieron de base para la creación de la imagen final
FROM openjdk:8-jdk-alpine

WORKDIR /app

#CREAR DIRECTORIO PARA LOGS
RUN mkdir ./logs
ARG MSVC_NAME
ARG TARGET_FOLDER=/app/$MSVC_NAME/target
COPY --from=builder $TARGET_FOLDER/msvs-usuarios-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8001
ENV PORT $PORT_APP

EXPOSE $PORT

#NO PERMITE USAR EL CMD O LINEA DE COMANDOS
#ENTRYPOINT [ "java", "-jar", "msvs-usuarios-0.0.1-SNAPSHOT.jar" ]
#PARA USAR LA LINEA DE COMANDOS
CMD [ "java", "-jar", "msvs-usuarios-0.0.1-SNAPSHOT.jar" ]