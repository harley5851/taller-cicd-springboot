# Taller CI/CD – CRUD de Productos (Spring Boot)

Proyecto base sencillo para el **Taller de CI/CD con Spring Boot**. Es un CRUD de
`Producto` (crear, listar, obtener, actualizar y eliminar), pensado como cualquier
otro dominio simple — puedes cambiar "Producto" por lo que quieras.

## Stack

- Java 21
- Spring Boot 3.3.4 (Web + Data JPA + Validation)
- Base de datos H2 en memoria (no requiere instalar nada)
- JUnit 5 + MockMvc para las pruebas
- Maven

## Cómo correrlo localmente

```bash
mvn clean package
mvn spring-boot:run
```

La API queda en `http://localhost:8080`.

## Endpoints

| Método | Ruta                  | Descripción              |
|--------|-----------------------|---------------------------|
| GET    | /api/productos        | Listar todos              |
| GET    | /api/productos/{id}   | Obtener uno                |
| POST   | /api/productos        | Crear                      |
| PUT    | /api/productos/{id}   | Editar                     |
| DELETE | /api/productos/{id}   | Eliminar                   |
| GET    | /api/estado           | Endpoint de salud (Paso 5) |

Ejemplo de body para crear/editar:

```json
{
  "nombre": "Extensión Clip-in 20\"",
  "descripcion": "Cabello 100% natural",
  "precio": 45000,
  "stock": 30
}
```

## Cómo correr las pruebas

```bash
mvn test
```

## Cómo generar el reporte de cobertura (JaCoCo)

```bash
mvn clean test jacoco:report
```

El reporte queda en `target/site/jacoco/index.html`.

## Siguientes pasos (según la guía del taller)

1. **Sube este proyecto a un repositorio en GitHub** (ya trae `.gitignore` correcto
   y el workflow en `.github/workflows/ci.yml`, así que puedes empezar directo
   desde el Paso 2 de la guía).
   ```bash
   git init
   git add .
   git commit -m "chore: proyecto base CRUD de productos"
   git branch -M main
   git remote add origin https://github.com/<tu-usuario>/<tu-repo>.git
   git push -u origin main
   ```
2. Verifica en la pestaña **Actions** de GitHub que el pipeline corre en verde.
3. Sigue el Paso 5 de la guía: crea la rama `feature/endpoint-saludo`
   (el endpoint `GET /api/estado` ya está incluido en este proyecto) y abre el
   Pull Request contra `main` con la protección de rama activada.
4. Sigue el Paso 6: crea el secreto `APP_ENV_DEMO` en
   `Settings → Secrets and variables → Actions` — el workflow ya lo referencia.
