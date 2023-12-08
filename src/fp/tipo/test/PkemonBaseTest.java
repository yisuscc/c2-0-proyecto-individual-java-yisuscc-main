package fp.tipo.test;

import java.time.LocalDate;
import java.util.Set;

import fp.tipo.FactoriaPokemon;
import fp.tipo.Pokemon;
import fp.tipo.Pokemones;
import fp.tipo.Tipos;
import fp.tipo.Pokemon;

public class PkemonBaseTest {
	private static LocalDate fecha= LocalDate.now();
	private static Pokemon Bulbasaur= new Pokemon("Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,FALSE,1999-03-13,false");
	private static String ruta = "data/Pokemon.csv";
	private static String ruta2 ="data/PokemonconMenosFechas.csv";
	private static Pokemones pokedexConMenosFechas = FactoriaPokemon.leerDataset(ruta2);
	private static Pokemones pokedex = FactoriaPokemon.leerDataset(ruta);
	public static void main(String[] args) {
		System.out.println("Bienvenido al test del proyecto individual de java de Jesús  Carrascosa");
		System.out.println("Este test va sobre los 151 pokemons de la primera generación, para mas información consultar el ReadME ");
		System.out.println("--------------------------------------------------");
		testMetodosClasePokemon();

		System.out.println("Ahora vamos a probar los metodos solicitados de la clase pokemones");

		testCalcularPokemonEntre();
		testCalculaMaximoPuntosSalud();
		testCalculaPokemonConMasDefensa();
		testCalculaConjuntoNombreConMasVelocidad();
		testCalculaRegistrosOrdenadosEnRango();
		testCalculaNumeroRegistrosDeDiaMesQueComienzanPor();
		testAgrupaRegistrosConPropiedadMayorQueMedia();
		testObtenerEneavaFechaConMasPokemon();
		testobtenerNPokemonDeFechasConMasSalud();
		testObtenerFechasIncrementos();
		System.out.println("Y ahora vamos a probar los metodos voluntarios");
		testMetodosVoluntarios();
	}



	private static void testMetodosClasePokemon() {

		System.out.println("Nuestro sujeto de prueba de la clase pokemon va a ser:");
		System.out.println(Bulbasaur.toString());
		System.out.println("Su tipo primario es:"+ Bulbasaur.getTipo1()+" Y su tipo secundario es:"+ Bulbasaur.getTipo2());
		System.out.println("La abreviatura de su nombre es:" + Bulbasaur.getAbreviaturaNombre());
		System.out.println("El número de dias que han pasado desde su aparición hasta la actualidad es de:"+ Bulbasaur.getNumeroDias());

	}

	private static void testCalcularPokemonEntre(){
		System.out.println("vamos a ver que pokemon tiene el ataque entre 30 y 80 puntos"+pokedex.calcularPokemonEntre(30.0, 80.0));
	}

	private static void testCalculaMaximoPuntosSalud() {
		System.out.println("El mayor puntaje de salud es:"+ pokedex.calculaMaximoPuntosSalud());
	}

	private static void testCalculaPokemonConMasDefensa() {
		System.out.println("Los 5 pokemon con mas salud son: "+pokedex.calculaPokemonConMasDefensa(5));	
	}

	private static void testCalculaConjuntoNombreConMasVelocidad() {
		System.out.println("Estos son los nombres de los 5 pokemons con mas velocidad" +pokedex.calculaConjuntoNombreConMasVelocidad(5));
	}
	private static void testCalculaRegistrosOrdenadosEnRango() {
		System.out.println("Estos son 5 pokemons que e desvian en menos de un 30 por ciento de la media"+pokedex.calculaRegistrosOrdenadosEnRango(0.3,5));
	}

	private static void testCalculaNumeroRegistrosDeDiaMesQueComienzanPor() {
		System.out.println("Estos son los dias y las veces que se repiten de los pokemons cuyo nombre empieza por la B"+pokedex.calculaNumeroRegistrosDeDiaMesQueComienzanPor('B'));
	}

	private static void testAgrupaRegistrosConPropiedadMayorQueMedia() {
		System.out.println("Ese es un mapa cuyas claves son los tipos secundarios y sus valores el conjunto de pokemons, cuyo ataque especial es mayor estricto que la media"+pokedex.agrupaRegistrosConPropiedadMayorQueMedia());
	}


	private static void testobtenerNPokemonDeFechasConMasSalud() {
		Set<LocalDate> conjuntoFechas = pokedex.conjuntoFechas(4);
		conjuntoFechas.add(LocalDate.now());
		conjuntoFechas.add(LocalDate.of(1931, 04, 14));
		conjuntoFechas.add(LocalDate.of(1910, 11, 1));
		System.out.println("Este metodo tiene 4 versiones diferentes (ver el readme para las diferencias)");
		System.out.println("Esta es la primera version(la peor de todas):");
		System.out.println(pokedex.obtenerNPokemonDeFechasConMasSaludV1(conjuntoFechas, 6));
		System.out.println("---------------------------------------------------");
		System.out.println(" vamos a probar la 2 version que creo que se corresponde mejor con el enunciado:");
		System.out.println(pokedex.obtenerNPokemonDeFechasConMasSaludV2(conjuntoFechas, 6));
		System.out.println("----------------------------------------------------");
		System.out.println("la 3 version:");
		System.out.println(pokedex.obtenerNPokemonDeFechasConMasSaludV3(conjuntoFechas, 6));
		System.out.println("---------------------------------------------------");
		System.out.println(" y para finalizar la 4 versión la mejor de todas");
		System.out.println(pokedex.obtenerNPokemonDeFechasConMasSaludV4(conjuntoFechas,6));
	}
	private static void testObtenerEneavaFechaConMasPokemon(){
		System.out.println("Esta es la 4 fecha con mas pokemon");
		System.out.println(pokedex.obtenerEneavaFechaConMasPokemon(4));
	}
	private static void testObtenerFechasIncrementos() {
		System.out.println("Para este experimento, voy a usar un dataset con menos rango de fechas porque con el otro sale cero");
		System.out.println("Estas son las fechas que poseen incrementos con respecto a la fecha anterior");
		System.out.println(pokedexConMenosFechas.obtenerFechasIncrementos());
		System.out.println("Asi es con el dataset Normal");
		System.out.println(pokedex.obtenerFechasIncrementos());
		
	}
	
	private static void testMetodosVoluntarios() {
		System.out.println("Este es el pokemon con mas defensa" +pokedex.pokemonConMasDefensa());
		System.out.println("------------------------------------------------");
		System.out.println("Esta es una lista de map entry de los pokemon(clave) con ratio(valor) de su ataque esp entre su defensa especial ");
		System.out.println(pokedex.ratioAtaqueDefensaEspecial());
		System.out.println("Este es un diccionario de los pokemons agrupados por sus tipos(ya sea primario o secundario)");
		System.out.println(pokedex.agrupaPokemonPorTipoStream());
		System.out.println("------------------------------------------------");
		System.out.println("Estos son los pokemons que tienen como tipo (ya sea primario o secundario) el tipo agua");
		System.out.println(pokedex.filtraPorAmbosTipos(Tipos.WATER));
		System.out.println("------------------------------------------------");
		System.out.println("Para finalizar, agrupamelos pokemon según la paridad del año");
		System.out.println(pokedexConMenosFechas.agrupaPokemonPorParidadDelAño());
		
		
	}

}
