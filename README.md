# copia del readme que esta en la carpeta fp.doc

# Proyecto individual de Java de Jesús Carrascosa Carro: Primera Generación de Pokemon:
##1 Descripción del dataset:
Este dataset es una modificación de otro dataset sacado de kaggle(https://www.kaggle.com/ecapelari/pokemon-gen1). En él, se recogen a todos los pokemons de la primera generación junto con sus atributos base.
El dataset consta de 13 columnas, de las cuales 8 son de tipo Integer, una es de tipo String, dos de tipo enum Tipo, 2 de tipo Boolean(una en mayusculas y otra en minusculas)y una de tipo LocalDate.

##2 Modificaciones hechas al dataset:
-Añadida una columna de LocalDate llamada  fecha aparicion.

-Añadida una columna de Boolean llamada  objeto.

-Eliminada una columna que indicaba el orden de la pokedex.

- Creado un dataset idéntico al de pokemon(PokemonconMenosfechas) excepto por el rango de fechas 

## 3. Clases:
### 3.1 Pokemon:

Es la clase base que "trabaja" con los atributos del dataset(descritos anteriormente), tiene orden natural, e implemnenta a equals . Presenta 2 constructores, uno que recibido un string, devuelve un pokemon, y otro que dado  los atributos devuelve un pokemon, las excepciones son comunes y son las siguientes:

- A ningún pokemon le puede faltar el nombre.
- El tipo primario no puede estar vacío ni coincidir con el secundario.

Sus propiedades derivadas son:

- **getAbreviaturaNombre:** Cuando se aplica sobre un pokemon, devuelve las tres primeras letras del nombre (string).

- **getNumerosDias:** Aplicado a un pokemon calcula el tiempo transcurrido desde la aparición del pokemon hasta la fecha actual y devuelve los dias.

- **getParidadAño:** De la fecha de aparicion de un pokemon, extrae el año y si es par devuelve  string Par, en caso contrario devuelve Impar. Se utiliza como "funcion auxiliar".

## 3.1 Clase Pokemones:
 Es una clase que se compone de un SortedSet de Pokemonde dos constructores (linea de string y un stream)que implementa a equals y presenta las siguientes propiedades:e
 - **agrupaPokemonPorParidadDelAño (voluntario):** Devuelve un diccionario cuyas claves son los String Par o Impar (de la funcion getParidadAño) y cuyos valores son una listacon los nombres.
 
 - **agrupaPokemonPorTipo(Voluntario):** Devuelve un diccionario apgrupador que clasifica los pokemon por ambos tipos(primario y secundario) y cuyos valores son un conjunto con los nombres de los pokemons que tengan ese tipo, es decir si un pokemon tiene los tipos fuego e hielo, el pokemon estara presente en el conjunto perteneciente a hielo y al conjunto perteneciente a fuego.

- **agrupaRegistrosConPropiedadMayorQueMedia:**   Esta función filtra los pokemons por su atque especial y se queda con aquellos que superen la media de su tipo secundario, y a continuación los clasifica por su tiposecundario(la unica propiedad string en el csv es nombre, y es "unico", por lo tanto he converitdo el tipo secundario a string) agrupandolos en un conjunto de pokemons.

- **añadirPokemon:** añade un pokemon al conjunto ordenado pokemones, y devuelve true si lo añade y false en caso contrario.

- **calculaconjuntNombreConMasVelocidad:** dado un integer (el limite) devuelve un conjunto de tamaño maenor o igual que el limite, del nombre de pokemons con mas velocidad(desosrdenados).

- **calculaMaximoPuntosSalud:** Devulve de todo el conjunto ordenados de pokemones el el puntaje mas alto de salud como Integer.

- **calculaNumeroRegistrosDeDiaMesQueComienzanPor:**  Dado un caracter, los fitra por ese caracter,y los agrupa por el dia del mes (en un diccionario agrupado)  siendo los valores el contador asociado a ese dia del mes.

- **calculaPokemonConMasDefensa:** Devuelve una lista de pokemons con mayor defensa ordenados de forma descendente cuyo tamaño(de  es menor o igual al especificado).

- **calculaRegistrosOrdenadosEnRango:**  Dados un porcentaje y un limite devuelve una lista del os pokemosn cuya velocidad este comprendidad entre el el rango de la media, de tamaño no superior al especificado.

- **calculaPokemonEntre:** Devuelve un conjunto ordenado(por el orden natural) de los pokemos cuyo ataque esta comprendido entre los limites dados.

- **pokemonConMasDefensa (voluntario):** Devuelve el pokemon cuya defensa sea la mayor de todos los pokemones.

- **obtenerEneavaFechaConMasPokemon:** Dado un posicion(en este caso al ser posicion(primera, segunda ...)va del 1 al tamaño de pokemones, si no salta un IllegalArgumentException) devuelve la fecha correspondiente a sea posicion de una lista de las fechas con mas pokemons.

- **obtenerNPokemonDeFechasConMasSalud:** Dado un set de fechas y un límite, devuelve un mapa ordenado  cuyas claves son las fechas y cuyos valores son un conjunto de pokemons de tamaño menor o igual que el limite. Según como se interprete el enunciado, hay 4 versiones. La primera, si la fecha del pokemon no está en el conjunto ,a esa fecha(del pokemon) le asocia un conjunto vacío. La segunda y tercera versión si la fecha del set de fechas no la tiene ningún pokemon, a esa fecha(del set) le asocia un conjunto vacío de pokemon (inmutable (v2) o mutable(v3)). La 4 version(la mejor de todas ellas) utiliza las function para realizar la asignación y la "conjuntacion"(en este caso el conjunto vacio es mutable.

NOTA: considero que la versión 4 es la mas interesante de las 4.

- **obtenerFechasIncrementos:** Devuelve un sortedSet de fechas ordenado de más antiguo a mas reciente, que poseen un incremento respecto a la fecha anterior. La primera fecha al no tener con quien compararla se salta.
Esta funcion, con este dataset al tener un numero muy reducido de filas, las fechas son casi unicas y apenas hay agrupación, por lo que devuelve un SortedSet vacio. Para solucionarlo, he creado un dataset identico al de pokemon excepto por un rango menor de fechas(no llega a la semana), pese a esto  se ve muy limitado .






