# Proyecto de Servicios Client y Account

## Pasos para configurar y ejecutar el proyecto

### 1. Descargar el Proyecto

Descarga el repositorio del proyecto a tu máquina local.

### 2. Compilar el Servicio de Client

1. Ubícate en el root del proyecto:
    ```sh
    cd /path/to/project-root
    ```

2. Navega a la carpeta `./client`:
    ```sh
    cd ./client
    ```

3. Compila el proyecto:
    ```sh
    gradlew clean build
    ```

### 3. Compilar el Servicio de Account

1. Regresa al root del proyecto:
    ```sh
    cd ..
    ```

2. Navega a la carpeta `./account`:
    ```sh
    cd ./account
    ```

3. Compila el proyecto:
    ```sh
    gradlew clean build
    ```

### 4. Compilar las Imágenes Docker

1. Regresa al root del proyecto:
    ```sh
    cd ..
    ```

2. Ejecuta el comando para construir las imágenes Docker:
    ```sh
    docker-compose build
    ```

### 5. Ejecutar los Contenedores

1. Levanta los contenedores en modo detached:
    ```sh
    docker-compose up -d
    ```

## Instrucciones Postman

Cuando ya los servicios estén arriba, ejecutar los servicios de Postman.

Para esto, debemos importar el JSON que se encuentra en la raíz del proyecto llamado `nttdataTest.postman_collection.json`.

Luego ejecutar los servicios en el siguiente orden:
- Creación de clientes
- Creación de cuentas
- Creación de transacciones
- Por último, generar el reporte

**Nota:** Se han añadido servicios de creación masiva para facilitar la creación de los registros. También se puede ejecutar la creación individual de los registros. En los servicios de registro masivo se encuentran los objetos JSON con todos los registros solicitados.

**Nota sobre los reportes:** Para crear los reportes, el formato de la fecha debe ser de la siguiente manera: `yyyy-MM-dd-HH:mm:ss`.

¡Y eso es todo! Ahora deberías tener tus servicios ejecutándose en contenedores Docker listos para ser utilizados. 🚀
