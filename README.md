# Proyecto: Consumo de API con Retrofit y Jetpack Compose

## Descripcion general
Este proyecto demuestra el uso de **Retrofit** para consumir una API publica dentro de una aplicacion Android desarrollada con **Kotlin** y **Jetpack Compose**.

El objetivo es comprender como integrar Retrofit para obtener datos desde Internet y mostrarlos dinamicamente en una interfaz moderna con Compose.

---

## Objetivo del proyecto
- Investigar y comprender que es **Retrofit** y como se usa en Android.
- Implementar un ejemplo funcional que consuma una API publica (PokeAPI en este caso).
- Mostrar los resultados en pantalla con Jetpack Compose.
- Comparar Retrofit con otras librerias como Volley y explorar alternativas como Ktor.
- Realizar pruebas basicas de consumo de API en Kotlin.

---

## Tecnologias utilizadas
- **Kotlin** (version 1.9.20)
- **Retrofit 2** + **Gson Converter**
- **Jetpack Compose**
- **Coroutines / Flow**
- **Material Design 3**
- **ViewModel (MVVM)**
- **JUnit / Strikt** para pruebas de API

---

## API utilizada
**PokeAPI:**  
`https://pokeapi.co/api/v2/pokemon`

Se consume el endpoint principal para obtener una lista de Pokemon, mostrando su nombre e imagen en tarjetas dentro de la interfaz Compose.

---

## Lo que se espera que funcione
Cuando ejecutes la aplicacion:

1. La app mostrara una pantalla principal con el titulo **“Pokémon List **.  
2. Retrofit se conectara a la **PokeAPI** y descargara la lista de Pokémon.
3. Cada Pokémon aparecera con:
   - Su **nombre**
   - Su **imagen**
4. Los datos se mostraran automaticamente usando **Jetpack Compose**.
5. Si hay error de red, se mostrara un mensaje de error.

*Ejemplo esperado:*
- Lista vertical con tarjetas de Pokémon.
- Cada tarjeta tiene la imagen a la izquierda y el nombre a la derecha.
