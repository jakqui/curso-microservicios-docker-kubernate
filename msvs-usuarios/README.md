docker network create spring
docker pull mysql:8.0.36

## ** COMANDOS PARA INICIAR CONTENEDORES**
# ** Crear contenedor mysql para ususarios**
docker run -d -p 3308:3306 --name mysql8users --network spring -e MYSQL_ROOT_PASSWORD=admin1234 -e MYSQL_DATABASE=msvc_usuarios -v data-mysql_users:/var/lib/mysql --restart=always mysql:8.0.36

# ** Crear contenedor mysql para cursos **
docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=admin1234 -e MYSQL_DATABASE=msvc_cursos -v data-mysql_cursos:/var/lib/mysql --restart=always mysql:8.0.36


# ** Crear imagen y contenedor para cursos**
* docker build -t cursos . -f .\msvc-cursos\Dockerfile
* docker run --name msvc-cursos -p 8002:8002 --env-file .\msvc-cursos\.env -d --rm --network spring cursos


# ** Crear imagen y contenedor para usuarios**
* docker build -t usuarios . -f .\msvs-usuarios\Dockerfile --build-arg PORT_APP=8001
* docker run --name msvc-usuarios -p 8001:8001 --env-file .\msvs-usuarios\.env -d --rm --network spring usuarios


# ** Docker compose**
docker compose up -d
docker compose down
docker compose up --build -d    FORZAR CONSTRUIR LAS IMAGENES, POR SI HACEMOS CAMBIOS
docker-compose --help           AYUDA
docker-compose build            FORZAR RECONSTRUIR IMAGENES, PERO NO LEVANTA CONTENEDORES

# ** END POINTS**