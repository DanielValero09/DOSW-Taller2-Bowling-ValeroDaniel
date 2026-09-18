# Bowling TDD

## 1. Identificación

- Nombre: Daniel Valero Quiroga
- Código estudiantil: 1000102237
- Asignatura: DOSW
- Correo institucional: daniel.valero-q@mail.escuelaing.edu.co

## 2. Descripción del proyecto

Este proyecto implementa el motor de puntuación de un juego de Bowling para un jugador. Se desarrolla mediante TDD, agregando una prueba y el código necesario para cada caso. Un juego contiene 10 frames, donde se registran los pinos derribados en cada tiro.

Los archivos actuales del repositorio no contienen una descripción de BowlTech.

Las tecnologías configuradas en `pom.xml` son Java 24, Maven, JUnit 5.13.4, JaCoCo 0.8.15 y SonarQube Maven Plugin 5.7.0.6970.

La rama de trabajo es `feature/ValeroDaniel_bowling`. El flujo de ramas del trabajo es:

```text
main → develop → feature/ValeroDaniel_bowling
```

### BowlingGame

Es el motor principal del juego. Registra los tiros con `roll()`, valida los pinos, administra los frames y delega el puntaje a `BowlingScorer` mediante `score()`. Permite un tiro bonus del frame 10 cuando hay spare y dos cuando hay strike. Antes de calcular el puntaje, `score()` valida que el juego esté completo y lanza `IllegalStateException` si no lo está. El método `isComplete()` está implementado y comparte la validación de finalización con `roll()` y `score()`.

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
| C1 | Juego recién creado: `isComplete() == false`. | `7a6880e` test: RED - juego nuevo no esta completo | `03ddb74` feat: GREEN - reporta juego inicial como incompleto | No requerido | ✅ Implementado |
| C2 | Nueve frames completos: `isComplete() == false`. Prueba agregada en `4ff2148` — test: valida juego incompleto despues de nueve frames. | No requerido: prueba pasó con lógica existente | No requerido | No requerido | ✅ Implementado |
| C3 | Diez frames normales completos: `isComplete() == true`. | `c662c9a` test: RED - diez frames normales completan juego | `40fda85` feat: GREEN - detecta final de juego normal | `99d96a1` refactor: reutiliza validacion de juego completo | ✅ Implementado |
| C4 | Spare en frame 10 y tiro bonus: `isComplete() == true`. Prueba agregada en `719e29d` — test: valida juego completo tras bonus de spare. | No requerido: prueba pasó con lógica existente | No requerido | No requerido | ✅ Implementado |
| C5 | Strike en frame 10 y dos tiros bonus: `isComplete() == true`. Prueba agregada en `2bc4ffc` — test: valida juego completo tras bonus de strike. | No requerido: prueba pasó con lógica existente | No requerido | No requerido | ✅ Implementado |
| C6 | Juego perfecto después del strike número 12: `isComplete() == true`. Prueba agregada en `4aa0741` — test: valida finalizacion de juego perfecto. | No requerido: prueba pasó con lógica existente | No requerido | No requerido | ✅ Implementado |

En B5 se conservó `getStrikeBonus(...)`: la búsqueda de los dos tiros ya estaba encapsulada y no se encontró una mejora que justificara otro refactor.

En B7, el commit `dfc8656` agregó la prueba de 12 strikes consecutivos. La prueba pasó directamente: el resultado de 300 surgió de la lógica general ya implementada. No fue necesario modificar código de producción ni realizar un ciclo RED, GREEN o REFACTOR para este caso.

En C2, C4, C5 y C6 las pruebas pasaron con la lógica existente. Sus commits únicamente agregan pruebas: no hubo RED real, GREEN nuevo ni REFACTOR. En C3, el refactor `99d96a1` extrajo `isGameComplete()` para compartir la condición de finalización entre `roll()`, `score()` e `isComplete()`.

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

La evidencia de este ciclo corresponde a los commits descritos; no hay capturas de consola RED y GREEN en `docs/evidence/`.

## 6. Casos implementados

### Módulo A - BowlingGame.roll()

**Estado del módulo A: COMPLETADO.**

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

**Estado del módulo C: COMPLETADO.**

| Caso | Resultado |
| --- | --- |
| C1 | Implementado: un juego recién creado devuelve `false`. |
| C2 | Implementado: nueve frames normales completos (18 tiros de cero) devuelven `false`. |
| C3 | Implementado: diez frames normales completos (20 tiros de cero) devuelven `true`. |
| C4 | Implementado: después de nueve frames de ceros, un spare de 5 + 5 en el frame 10 y un bonus de 3 devuelven `true`. |
| C5 | Implementado: después de nueve frames de ceros, un strike en el frame 10 y dos bonus de 3 y 4 devuelven `true`. |
| C6 | Implementado: un juego perfecto devuelve `true` después del strike número 12. |

Con el módulo C completo, `isComplete()` permite distinguir un juego recién iniciado o con nueve frames, que sigue incompleto, de un juego normal de diez frames terminado. También reconoce la finalización del frame 10 con spare y su bonus, con strike y sus dos bonus, y del juego perfecto de doce strikes.

### Estado general

- Módulo A: COMPLETADO.
- Módulo B: COMPLETADO.
- Módulo C: COMPLETADO.
- JaCoCo: COMPLETADO.
- SonarQube: COMPLETADO.
- Desarrollo funcional: COMPLETADO.
- Calidad y cobertura: COMPLETADO.

- Pull Request principal hacia `develop`: COMPLETADO (PR #1, merge el 17/09/2026).
- Documentación del PR después del merge real: COMPLETADA.

### JaCoCo

Estado: COMPLETADO.

Comando utilizado:

```bash
mvn clean verify
```

Resultados iniciales y finales comprobados en las evidencias:

| Métrica | Cobertura | Cubiertos / total |
| --- | --- | --- |
| Lines | 100% | 86/86 |
| Branches | 100% | 60/60 |
| Methods | 100% | 25/25 |
| Classes | 100% | 4/4 |
| Instructions | 100% | 408/408 |

Se cumplieron los requisitos del taller: line coverage >= 85% y branch coverage >= 70%. El POM verifica automáticamente el mínimo de líneas de 0.85 en `verify`; el cumplimiento de ramas se comprobó en el reporte.

La primera medición ya obtuvo 100% de líneas y ramas. No fue necesario agregar pruebas adicionales exclusivamente para aumentar la cobertura: los ciclos TDD previos ya ejercitaban todo el código existente.

Evidencias: [JaCoCo inicial](docs/evidence/jacoco-antes.png) y [JaCoCo final](docs/evidence/jacoco-final.png).

### SonarQube

Estado: COMPLETADO.

Versión utilizada: `sonarqube:26.9.0.129388-community`.

Resultados de Overall Code comprobados en las capturas del análisis inicial y final:

| Métrica | Análisis inicial | Análisis final |
| --- | --- | --- |
| Quality Gate | Passed | Passed |
| Coverage | 100% | 100% |
| Security | 0 open issues, Rating A | 0 open issues, Rating A |
| Reliability | 0 open issues, Rating A | 0 open issues, Rating A |
| Maintainability | 1 open issue, Rating A | 0 open issues, Rating A |
| Duplications | 0.0% | 0.0% |
| Security Hotspots | 0 | 0 |

El único issue detectado fue la regla `java:S1612` en `src/test/java/edu/eci/dosw/bowling/BowlingScorerTest.java`, con el mensaje: "Replace this lambda with method reference 'game::score'."

Código anterior:

```java
assertThrows(
    IllegalStateException.class,
    () -> game.score()
);
```

Corrección realizada en el commit `046d02e`:

```java
assertThrows(
    IllegalStateException.class,
    game::score
);
```

La referencia al método simplificó el código sin modificar el comportamiento de la prueba. Después de la corrección se volvió a ejecutar:

```bash
mvn clean verify sonar:sonar
```

El análisis final mantuvo el Quality Gate en **Passed**, la cobertura en **100%** y dejó **0 issues abiertos** en seguridad, fiabilidad y mantenibilidad.

Evidencias: [SonarQube inicial](docs/evidence/sonarqube-antes.png) y [SonarQube final](docs/evidence/sonarqube-final.png). Estos resultados corresponden al análisis ya realizado y guardado en el repositorio.

## 7. Estado actual de las pruebas

Se ejecutaron desde la raíz del proyecto las validaciones finales con JaCoCo habilitado:

```bash
mvn test
mvn clean verify
```

| Resultado | Cantidad |
| --- | --- |
| Pruebas totales | 22 |
| Pruebas exitosas | 22 |
| Failures | 0 |
| Errors | 0 |
| Pruebas omitidas (Skipped) | 0 |

`BowlingGameTest` ejecutó 14 pruebas (A1–A8 y C1–C6) y `BowlingScorerTest` ejecutó 8 (B1–B8). Ambos comandos finalizaron con **BUILD SUCCESS**, y `mvn clean verify` verificó el umbral configurado de JaCoCo.

Resumen de la salida real:

```text
[INFO] Tests run: 22, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 8. Reflexión técnica

### 1. ¿Qué caso edge del Bowling fue el más difícil de implementar con TDD y por qué?

El frame 10 fue el caso más delicado porque no sigue exactamente las mismas reglas que los primeros nueve. Hubo que manejar el juego normal, el spare con un tiro bonus y el strike con dos tiros bonus, además de comprobar el juego perfecto. Las pruebas permitieron revisar tanto el puntaje como el momento en que termina el juego.

### 2. ¿Qué parte del código cambió durante REFACTOR sin modificar el comportamiento observable?

En `ab3842c` se extrajo `validatePins(int pins)` para separar la validación del rango de pinos, conservando la condición y la excepción. En `99d96a1` se extrajo `isGameComplete()` para reutilizar la condición de finalización en `roll()`, `score()` e `isComplete()`. Estos cambios organizaron mejor el código sin cambiar sus resultados.

### 3. ¿Qué casos de prueba descubriste al revisar el reporte de cobertura de JaCoCo que no habían considerado antes?

La medición inicial ya mostró 100% de cobertura de líneas y ramas, por lo que no fue necesario agregar pruebas exclusivamente por cobertura. Los ciclos TDD desarrollados previamente ya ejercitaban todo el código existente. Sin embargo, 100% de cobertura no significa que todas las combinaciones posibles del dominio estén necesariamente probadas.

### 4. ¿Qué hallazgo de SonarQube produjo un cambio real en el código?

SonarQube detectó la regla `java:S1612` en `BowlingScorerTest`. Se reemplazó `() -> game.score()` por `game::score` en la prueba de puntaje de un juego incompleto. Fue un cambio de mantenibilidad que simplificó el código sin cambiar su comportamiento; el análisis final quedó sin issues abiertos de mantenibilidad.

## 9. Pull Requests

El PR #1 integró `feature/ValeroDaniel_bowling` en `develop` el 17/09/2026, incluyendo los módulos A, B y C, JaCoCo, SonarQube, las evidencias y la documentación del taller.

| PR | Fecha de merge | Módulo |
|---|---|---|
| [PR #1](https://github.com/DanielValero09/DOSW-Taller2-Bowling-ValeroDaniel/pull/1) | 17/09/2026 | Implementación completa TDD Bowling: módulos A, B y C, JaCoCo y SonarQube |
