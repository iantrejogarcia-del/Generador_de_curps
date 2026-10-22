# Generador de CURP

Aplicación Android que arma una **CURP aproximada** a partir de los datos personales que captura el usuario: nombre, apellidos, fecha de nacimiento, sexo y entidad de nacimiento.

> **Proyecto educativo.** El resultado no es una CURP oficial: no calcula la homoclave ni el dígito verificador (siempre agrega `00`). Para obtener la CURP real, consúltala en el sitio oficial del gobierno de México.

## Funcionalidades

- Captura de **nombre, apellido paterno y apellido materno**.
- Selector de **fecha de nacimiento** con calendario.
- Selección de **sexo** (hombre / mujer).
- Lista desplegable con las **32 entidades federativas** más la opción "Nacido en el extranjero", cada una con su clave de dos letras.
- Aviso si falta algún campo por llenar.
- Construcción de la CURP con la regla básica: primera letra y primera vocal interna del apellido paterno, primera letra del apellido materno y del nombre, fecha, sexo, clave de entidad y primeras consonantes internas de los apellidos y del nombre.

## Tecnologías

- Java
- Android SDK (AppCompat, Material Components)
- `DatePickerDialog`, `Spinner` y `RadioGroup`
- Gradle (Kotlin DSL)

## Requisitos

- Android Studio (versión reciente)
- Android SDK compatible con el proyecto (ver `app/build.gradle.kts`)

## Cómo ejecutarlo

1. Clona el repositorio:
   ```bash
   git clone https://github.com/iantrejogarcia-del/Generador_de_curps.git
   ```
2. Abre el proyecto en Android Studio y espera a que termine la sincronización de Gradle.
3. Conecta un dispositivo Android o inicia un emulador.
4. Presiona **Run**.

## Estructura del proyecto

```
app/src/main/
├── java/com/example/curp_ian/
│   └── MainActivity.java    # Captura de datos y generación de la CURP
└── res/
    ├── layout/              # Pantalla principal
    └── values/strings.xml   # Lista de entidades federativas
```

## Limitaciones conocidas y posibles mejoras

- El **mes** de nacimiento no aparece en el resultado (el texto del botón de fecha se recorta con un espacio de más y no coincide con la tabla de meses).
- Falta la homoclave y el dígito verificador.
- No contempla casos especiales de la regla oficial: nombres compuestos (José, María), palabras inconvenientes, la letra Ñ ni los apellidos con partículas ("de la", "del").
- La clave de sexo toma la primera letra del texto del botón seleccionado.

## Autor

**Ian Trejo García** — Estudiante de Ingeniería en Sistemas Computacionales
GitHub: [iantrejogarcia-del](https://github.com/iantrejogarcia-del)
