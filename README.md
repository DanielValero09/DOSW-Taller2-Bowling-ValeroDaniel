# Bowling TDD

## 1. Identificación

- Nombre: Daniel Valero Quiroga
- Código estudiantil: 1000102237
- Asignatura: DOSW
- Correo institucional: daniel.valero-q@mail.escuelaing.edu.co

## 2. Descripción del proyecto

Este proyecto implementa el motor de puntuación de un juego de Bowling para un jugador. Se desarrolla mediante TDD, agregando una prueba y el código necesario para cada caso. Un juego contiene 10 frames, donde se registran los pinos derribados en cada tiro.

La descripción de BowlTech está pendiente de agregar: los archivos actuales del repositorio no contienen información sobre qué es.

Las tecnologías configuradas en `pom.xml` son Java 24, Maven, JUnit 5.13.4, JaCoCo 0.8.15 y SonarQube Maven Plugin 5.7.0.6970.

La rama de trabajo es `feature/ValeroDaniel_bowling`. El flujo de ramas del trabajo es:

```text
main → develop → feature/ValeroDaniel_bowling
```

### BowlingGame

Es el motor principal del juego. Registra los tiros con `roll()`, valida los pinos, administra los frames y delega el puntaje a `BowlingScorer` mediante `score()`. Permite el tiro bonus del frame 10 cuando hay spare o strike. Antes de calcular el puntaje, `score()` valida que el juego esté completo y lanza `IllegalStateException` si no lo está. El método `isComplete()` todavía está pendiente.

### Frame

Almacena los tiros de un frame y su tipo. Cada frame se crea con el tipo `NORMAL`. Permite agregar tiros, consultar una copia de la lista de tiros y consultar o cambiar su tipo.

### FrameType

Es un enum con los siguientes valores:

| Tipo | Significado |
| --- | --- |
| `NORMAL` | Frame sin spare ni strike; también es el tipo inicial de un frame. |
| `SPARE` | Se derriban los 10 pinos entre dos tiros. |
| `STRIKE` | Se derriban los 10 pinos en un tiro. |
| `TENTH` | Valor previsto para representar el décimo frame. Está declarado, pero el código actual no lo asigna. |

### BowlingScorer

Calcula el puntaje a partir de los frames. Suma los pinos registrados y agrega los bonos de spare y strike. Los casos B1–B8 están implementados, incluyendo el juego completo de spares y el juego perfecto.

`getSpareBonus(...)` obtiene el primer tiro del frame siguiente. `getStrikeBonus(...)` recorre los frames siguientes, toma los tiros en orden y retorna el bono al llegar a dos tiros. Así puede calcular el bono cuando hay dos strikes consecutivos.

## 3. Metodología TDD

### RED

Primero se escribe una prueba que falla y muestra el comportamiento que hace falta implementar.

### GREEN

Se implementa únicamente el código mínimo necesario para hacer pasar esa prueba.

### REFACTOR

Se mejora la claridad o el diseño sin modificar el comportamiento. Luego se comprueba que las pruebas sigan pasando.

Cada fase se maneja mediante commits separados cuando existe un refactor real. Si no hace falta mejorar el código, no se crea un commit de REFACTOR solo para completar el ciclo.

## 4. Evidencia TDD

Los hashes cortos y los mensajes de esta tabla se obtuvieron del historial del repositorio con `git log --oneline --decorate`. También se revisaron los cambios de los commits.

| Caso | Descripción | RED | GREEN | REFACTOR | Estado |
| --- | --- | --- | --- | --- | --- |
| A1 | Registrar `roll(0)`. | `6d074b4` test: RED - registra tiro de cero pinos | `53ede50` feat: GREEN - permite registrar cero pinos | No requerido | ✅ Implementado |
| A2 | Rechazar `roll(-1)`. | `e6eb973` test: RED - roll rechaza pines negativos | `ffa46a3` feat: GREEN - valida pines negativos | No requerido | ✅ Implementado |
| A3 | Rechazar `roll(11)`. | `22c9122` test: RED - roll rechaza pines mayores a diez | `28f6592` feat: GREEN - valida limite maximo de pines | `ab3842c` refactor: extrae validacion de rango de pines | ✅ Implementado |
| A4 | Rechazar una suma mayor a 10 en dos tiros del frame. | `f2e2c33` test: RED - rechaza suma mayor a diez en un frame | `666058a` feat: GREEN - valida suma de tiros del frame | `ab1e551` refactor: separa validacion de tiros del frame | ✅ Implementado |
| A5 | Rechazar tiros después de terminar el juego. | `6a9991f` test: RED - rechaza tiros despues de terminar juego | `cad7c59` feat: GREEN - bloquea tiros con juego terminado | `9a6cb33` refactor: separa avance de frames normales | ✅ Implementado |
| A6 | Detectar strike y avanzar de frame. | `b97bdf3` test: RED - detecta strike y avanza frame | `e235088` feat: GREEN - registra strike y avanza frame | `d86bfc2` refactor: separa manejo de strike | ✅ Implementado |
| A7 | Detectar spare. | `32e2228` test: RED - detecta spare | `a6d772e` feat: GREEN - registra spare | `f8a9925` refactor: separa deteccion de spare | ✅ Implementado |
| A8 | Permitir tres tiros en el frame 10 con strike. | `262e04e` test: RED - permite tres tiros en frame diez con strike | `d258d6a` feat: GREEN - implementa tiros bonus del frame diez | `19c86f2` refactor: separa manejo del frame diez | ✅ Implementado |
| B1 | Obtener 0 en un juego de ceros. | `b56b0be` test: RED - juego de ceros obtiene puntaje cero | `dcdf7a3` feat: GREEN - calcula puntaje de juego en cero | No requerido | ✅ Implementado |
| B2 | Sumar los tiros de un juego sin bonos. | `2f68a44` test: RED - calcula juego sin bonos | `d618139` feat: GREEN - suma tiros de frames normales | No requerido | ✅ Implementado |
| B3 | Agregar el bono de spare. | `83fd1da` test: RED - calcula bono de spare | `a1a79fe` feat: GREEN - implementa bono de spare | `97606f9` refactor: separa calculo de bono de spare | ✅ Implementado |
| B4 | Agregar el bono de strike. | `4130eea` test: RED - calcula bono de strike | `87dfd54` feat: GREEN - implementa bono de strike | `a5a8b7d` refactor: separa calculo de bono de strike | ✅ Implementado |
| B5 | Calcular el bono entre dos strikes consecutivos. | `0400444` test: RED - calcula strikes consecutivos | `843999e` feat: GREEN - calcula bono entre strikes consecutivos | No requerido | ✅ Implementado |
| B6 | Obtener 150 con diez spares de 5 + 5 y un tiro bonus de 5. | `0dbffd8` test: RED - todos los spares obtienen 150 | `db1c59c` feat: GREEN - calcula juego completo con spares | `b9ad885` refactor: mejora manejo de spare en frame diez | ✅ Implementado |
| B7 | Obtener 300 con 12 strikes. Prueba agregada en `dfc8656` — test: valida juego perfecto de 300 puntos. | No requerido: la prueba pasó con la lógica existente | No requerido | No requerido | ✅ Implementado |
| B8 | Rechazar `score()` en un juego incompleto. | `73f1402` test: RED - score rechaza juego incompleto | `c13a4db` feat: GREEN - valida juego completo antes de calcular score | `8528778` refactor: separa validacion de juego completo para score | ✅ Implementado |

En B5 se conservó `getStrikeBonus(...)`: la búsqueda de los dos tiros ya estaba encapsulada y no se encontró una mejora que justificara otro refactor.

En B7, el commit `dfc8656` agregó la prueba de 12 strikes consecutivos. La prueba pasó directamente: el resultado de 300 surgió de la lógica general ya implementada. No fue necesario modificar código de producción ni realizar un ciclo RED, GREEN o REFACTOR para este caso.

Además de los ciclos de la tabla, el historial incluye `03e1a08` — `chore: configura estructura inicial del proyecto Bowling` y `d329c64` — `chore: ajusta stubs para preservar flujo TDD`. Este último dejó `score()` e `isComplete()` lanzando `UnsupportedOperationException` mientras estaban pendientes. Más adelante, B1 implementó la delegación de `score()`.

## 5. Evidencia de un ciclo RED → GREEN → REFACTOR

Se tomó el caso A3, que verifica el límite máximo de pinos por tiro. La explicación se basa en la prueba y el código guardados en sus tres commits.

### RED

- Se agregó una prueba que espera `IllegalArgumentException` al ejecutar `roll(11)`.
- En ese punto, `roll()` solo rechazaba valores negativos. Aceptaba 11 y no lanzaba la excepción esperada, por lo que la prueba fallaba.
- Commit: `22c9122` — `test: RED - roll rechaza pines mayores a diez`.

### GREEN

- Se amplió la condición de validación a `pins < 0 || pins > 10`.
- Ese cambio agregó el rechazo de valores mayores a 10 y mantuvo la validación de negativos.
- Commit: `28f6592` — `feat: GREEN - valida limite maximo de pines`.

### REFACTOR

- Se extrajo la validación al método privado `validatePins(int pins)`.
- `roll()` pasó a llamar ese método. La condición y la excepción se conservaron, dejando más clara la responsabilidad de validar el rango.
- Commit: `ab3842c` — `refactor: extrae validacion de rango de pines`.

> Capturas de consola RED y GREEN: pendientes de agregar en docs/evidence/.

## 6. Casos implementados

### Módulo A - BowlingGame.roll()

Los ocho casos del módulo A están implementados y tienen una prueba cada uno.

| Caso | Resultado |
| --- | --- |
| A1 | Implementado: `roll(0)` registra un tiro de cero pinos. |
| A2 | Implementado: `roll(-1)` lanza `IllegalArgumentException`. |
| A3 | Implementado: `roll(11)` lanza `IllegalArgumentException`. |
| A4 | Implementado: después de un tiro de 7, un tiro de 6 en el mismo frame lanza `IllegalArgumentException`. |
| A5 | Implementado: después de 20 tiros de cero, otro tiro lanza `IllegalStateException`. |
| A6 | Implementado: un tiro de 10 marca `STRIKE` y el siguiente tiro se registra en otro frame. |
| A7 | Implementado: dos tiros de 5 marcan `SPARE`. |
| A8 | Implementado: después de nueve frames de ceros, el frame 10 acepta y guarda los tiros 10, 3 y 4. |

### Módulo B - BowlingScorer

**Estado del módulo B: COMPLETADO.**

En los ejemplos de B3–B5, los frames restantes se completan con ceros.

| Caso | Resultado |
| --- | --- |
| B1 | Implementado: 20 tiros de cero dan un puntaje de 0. |
| B2 | Implementado: 20 tiros de un pino dan un puntaje de 20. |
| B3 | Implementado: un spare de 5 y 5, seguido de 3 y 0, da un puntaje de 16. |
| B4 | Implementado: un strike seguido de 4 y 3 da un puntaje de 24. |
| B5 | Implementado: dos strikes consecutivos seguidos de 5 y 0 dan un puntaje de 45. |
| B6 | Implementado: diez spares de 5 + 5 y un tiro bonus final de 5 dan `score() == 150`. |
| B7 | Implementado: 12 strikes consecutivos dan `score() == 300`. |
| B8 | Implementado: después de un solo tiro de 5, `score()` lanza `IllegalStateException` porque el juego está incompleto. |

Con el módulo B completo, `BowlingScorer` suma los tiros normales y calcula los bonos de spare, strike y strikes consecutivos, incluso cuando el bono atraviesa más de un frame. También soporta el juego completo de spares y el juego perfecto. La validación de `score()` en un juego incompleto se realiza en `BowlingGame`, antes de delegar el cálculo a `BowlingScorer`.

### Módulo C - BowlingGame.isComplete()

Estado: Pendiente.

`isComplete()` todavía lanza `UnsupportedOperationException`. Los casos C1–C6 no están implementados ni tienen pruebas en el repositorio actual.

### Próximos pasos

- Desarrollar los casos C1–C6 del módulo C.
- Realizar la validación final de cobertura con JaCoCo. El comando `mvn test` ya ejecuta `prepare-agent` y `report` por la configuración del proyecto; la comprobación `check` está asociada a la fase `verify`. El reporte generado durante las pruebas no se presenta como la validación final.
- Ejecutar SonarQube. El plugin está configurado, pero no hay resultados de un análisis documentados en el repositorio.
- Agregar las evidencias finales, incluidas las capturas de consola del ciclo TDD.
- Completar la reflexión técnica final; las preguntas 3 y 4 siguen pendientes.
- Realizar el Pull Request final hacia `develop`. El historial revisado todavía mantiene `main` y `develop` en el commit inicial, sin la integración de la rama de trabajo.

## 7. Estado actual de las pruebas

Se ejecutó el siguiente comando desde la raíz del proyecto antes de actualizar este README:

```bash
mvn test
```

| Resultado | Cantidad |
| --- | --- |
| Pruebas totales | 16 |
| Pruebas exitosas | 16 |
| Failures | 0 |
| Errors | 0 |
| Pruebas omitidas (Skipped) | 0 |

`BowlingGameTest` ejecutó 8 pruebas y `BowlingScorerTest` ejecutó 8. El resultado fue **BUILD SUCCESS**.

Resumen de la salida real:

```text
[INFO] Tests run: 16, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 8. Reflexión técnica

La reflexión técnica final sigue pendiente. Las preguntas 3 y 4 se mantienen pendientes hasta contar con la validación final de JaCoCo y los resultados de SonarQube.
