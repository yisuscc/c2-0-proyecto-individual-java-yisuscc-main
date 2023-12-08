package fp.tipo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pokemones {
	private SortedSet<Pokemon> pokemones;


	// constructores
	public Pokemones() {
		this.pokemones = new TreeSet<Pokemon>();
	}
	public Pokemones(Stream<Pokemon> streamPokemons) {
		this.pokemones = streamPokemons.collect(Collectors.toCollection(TreeSet::new));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pokemones == null) ? 0 : pokemones.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)// si es identico 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemones other = (Pokemones) obj;
		if (pokemones == null) {
			if (other.pokemones != null)
				return false;
		} else if (!pokemones.equals(other.pokemones))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pokemones [pokemones=" + pokemones + "]";
	}

	//derivadas
	public Boolean añadirPokemon(Pokemon pokemon) {
		return pokemones.add(pokemon);
	}
	public SortedSet<Pokemon> calcularPokemonEntre(Double limiteInferior,Double limiteSuperior){
		/*
		 * Método public SortedSet<TipoBase> calcularTipoBaseEntre(Double limiteInferior, Double limiteSuperior) 
		 * que devuelva aquellas "filas" que cumplen con el requisito de que una de sus columnas numéricas
		 *  estén comprendida en un rango. 
		 * Solo será válida una solución basada en Stream<TipoBase>, no en foreach.
		 *  Entiendo que se refiere a que con que una de las columnas numericas de todas las que tiene el tipobase este en el rango lo añada 
		 */

		SortedSet<Pokemon> res = pokemones.stream().filter( pokemon->(pokemon.getAtaque() < limiteSuperior && pokemon.getAtaque()> limiteInferior)).collect(Collectors.toCollection(TreeSet::new));
		return res;
	}
	public Integer calculaMaximoPuntosSalud() {
		/*
		 * Método Integer calculaMáximoNombreParámetro() que devuelva el mayor valor de un parámetro de tipo Integer de vuestro dataset. Solo será válida una solución basada en Stream<TipoBase>, no en foreach.
		 */

		Integer res = pokemones.stream().map(Pokemon::getPuntosSalud).max(Comparator.naturalOrder()).get(); 	
		return res ;
	}
	public List<Pokemon> calculaPokemonConMasDefensa(Integer limite){
		//Método List<TipoBase> calculaTiposBaseConMasNombreParametro(Integer limite) que devuelva una Lista de TipoBase con los limite (número) elementos con mayor XXX, siendo XXX un parámetro de tipo numérico, ordenados de mayor a menor por orden natural.
		return pokemones.stream().sorted(Comparator.comparing(Pokemon::getDefensa)).limit(limite).collect(Collectors.toList());
	}
	public Set<String> calculaConjuntoNombreConMasVelocidad(Integer n){
		/*
		 * public Set<String> calculaConjuntoPropiedadesDeLosNConMas(Integer n). 
		 * Dado un número n devuelve un conjunto con los (propiedad String que elijas) de los registros que están entre lo
		 * s n que más (propiedad Integer que elijas) tienen. 
		 * Ejemplo: Devuelve el conjunto de autores de entre los 5 conciertos con más duración.
		 */
		return pokemones.stream().sorted(Comparator.comparing(Pokemon::getVelocidad)).limit(n).map(Pokemon::getNombre).collect(Collectors.toSet());
	}
	public List<Pokemon> calculaRegistrosOrdenadosEnRango(Double porcentajeRango,Integer limite){
		/*
		 * public List<TipoBase> calculaRegistrosOrdenadosEnRango(Double porcentajeRango, Integer limite). 
		 * Devuelve una lista con los n elementos ordenados de mayor a menor por una propiedad numérica de TipoBase
		 *  aquellos registros cuya propiedad numérica no supere ni sea inferior en más de un porcentajeRango 
		 *  (en rango 0-1) la media de esa propiedad numérica. 
		 *  Ejemplo: Dada una lista de conciertos, devuelve los 5 conciertos ordenados de mayor a menor 
		 *  cuyo precio no esté ni por encima del precio medio de los conciertos + 20% ni por 
		 *  debajo del precio medio de los conciertos -20%.
		 */

		Double mediaVelocidad = pokemones.stream().mapToDouble(Pokemon::getVelocidad).average().getAsDouble();
		Double limiteInferior = mediaVelocidad * (1-porcentajeRango);
		Double limiteSuperior = mediaVelocidad * (1+porcentajeRango);
		Predicate<Pokemon> estaDentroDelRango = pokemon-> limiteInferior <= pokemon.getVelocidad()&& pokemon.getVelocidad()<= limiteSuperior;
		return pokemones.stream().filter(estaDentroDelRango).sorted(Comparator.comparing(Pokemon::getVelocidad)).limit(limite).collect(Collectors.toList());
	}

	public SortedMap<Integer, Integer> calculaNumeroRegistrosDeDiaMesQueComienzanPor(Character inicio){

		/*
		 *  Devuelve un SortedMap<Integer,Integer> que,
		 *   a cada día del mes en el que haya registros de nuestro dataset 
		 *   se le asocie el número de registros que hay en ese día del mes y 
		 *   que comiencen por el caracter indicado. 
		 *   Ejemplo: Dada una lista de conciertos, devuelve un mapa ordenado donde se asocie cada día del mes en el que hay conciertos con el número de conciertos de ese día del mes 
		 * (si es el 23, da igual que sea de mayo, junio, etc.) cuyo título comienza por 'A'.
		 */
		// 1 filtro 
		// 2 grouping by contador 

		Predicate<Pokemon> empiezaPorLaLetra = (pokemon) -> inicio.equals(pokemon.getNombre().charAt(0));
		Function<Pokemon,Integer> diaMes = pokemon -> pokemon.getFechaAparicion().getDayOfMonth();
		return pokemones.stream().filter(empiezaPorLaLetra).collect(Collectors.groupingBy(diaMes, TreeMap::new,Collectors.collectingAndThen(Collectors.counting(),(total)->(((Long)total).intValue())))) ;//como paso de long a Integer

	}
	public Map<String, Set<Pokemon> >agrupaRegistrosConPropiedadMayorQueMedia(){
		/*
		 * Devuelve un Map<String, Set<TipoBase>> que asocia a cada propiedad (de tipo String) aquellos TipoBase cuya propiedad de tipo Integer (a elegir) sea mayor que la media de los TipoBase de esa categoría. Ejemplo: Dada una lista de conciertos, asocia a cada género musical el conjunto de conciertos con aquellos 
		 * conciertos cuyo precio s
		 * ea mayor que la media de los conciertos de ese género musical.
		 */
		// voy a hacer una "trampa" voy  utilizar el tipo2 que es enum como string , porque si uso el nombre, se me queda un set con un solo elemento
		Function<Pokemon, String> tipo2String = pokemon -> pokemon.getTipo2().toString();// si
		Map<String,Double> mapaMedias = pokemones.stream()
				.collect(Collectors.groupingBy(tipo2String,Collectors.averagingDouble(Pokemon::getAtaqueEspecial)));
		Predicate<Pokemon> predicado = pokemon -> pokemon.getAtaqueEspecial() >mapaMedias.get(pokemon.getTipo2().toString());// no pongo el tipo"String porque me salta una advertencia

		return pokemones.stream().filter(predicado).collect(Collectors.groupingBy(tipo2String,Collectors.toSet()));

	}

	public Map<LocalDate, Set<Pokemon>> obtenerNPokemonDeFechasConMasSaludV1(Set<LocalDate> fechas, Integer limite){
		
		//Version mala no malisima pero finciona y es lo importante la mejor es la version 4 

		/*
		 * Dada una colección de TipoBase, un conjunto Set<LocalDate> y un entero límite, 
		 * devuelve un Map<LocalDate, Set<TipoBase> tal que a cada fecha contenida en el conjunto 
		 * le asocie los N (parámetro límite) elementos con mayor X (propiedad de tipo Integer/Double). 
		 * Si no hay ningún elemento para esa fecha, se le asociará a la fecha un conjunto vacío.
		 */


		Function<Pokemon, LocalDate> devuelveFecha = pokemon-> pokemon.getFechaAparicion();
		Comparator<Pokemon> masSaludDescendente = (Comparator.comparing(Pokemon::getPuntosSalud)).reversed();
		Map<LocalDate, Set<Pokemon>> contieneFecha = pokemones.stream().filter(pokemon -> fechas.contains(pokemon.getFechaAparicion())).
				collect(Collectors.groupingBy(Pokemon::getFechaAparicion,()->new HashMap<>(),Collectors.collectingAndThen(Collectors.toList(), lista-> lista.stream().sorted(masSaludDescendente).limit(limite).collect(Collectors.toSet()))));
		// si la fecha del pokemon no esta en el set devuelve la fecha del pokemon asociada al conjunto vacio
		Map<LocalDate, Set<Pokemon>> noContieneFecha = pokemones.stream().filter(pokemon-> !fechas.contains(pokemon.getFechaAparicion())).distinct().
				collect(Collectors.toMap(devuelveFecha,pokemon-> Collections.<Pokemon>emptySet()));
		Stream<Map.Entry<LocalDate, Set<Pokemon>>> streamConcatenado=Stream.concat(contieneFecha.entrySet().stream(),noContieneFecha.entrySet().stream());
		Map<LocalDate,Set<Pokemon>>res= streamConcatenado.collect(Collectors.toMap(Entry::getKey,Entry::getValue));

		return res;
	}
	public Map<LocalDate, Set<Pokemon>> obtenerNPokemonDeFechasConMasSaludV2(Set<LocalDate> fechas, Integer limite){
		// esta version es casi igual a la anterior excepto en el caso de que la fecha dada en el setno aparezca en los pokemon:
		Set<LocalDate> conjuntoFechasPokemon  = pokemones.stream().map(Pokemon::getFechaAparicion).collect(Collectors.toSet());

		Comparator<Pokemon> masSaludDescendente = (Comparator.comparing(Pokemon::getPuntosSalud)).reversed();
		
		Map<LocalDate, Set<Pokemon>> contieneFecha = pokemones.stream().filter(pokemon -> fechas.contains(pokemon.getFechaAparicion())).
				collect(Collectors.groupingBy(Pokemon::getFechaAparicion,()->new HashMap<>(),Collectors.collectingAndThen(Collectors.toList(), lista-> lista.stream().sorted(masSaludDescendente).limit(limite).collect(Collectors.toSet()))));
		
		// si la fecha del set  coincide con ninguna de los pokemon a esa fecha(del set) se se asocia un conjunto vacio de pokemon;
		Map<LocalDate, Set<Pokemon>> noContieneFecha = fechas.stream().filter(fecha -> !conjuntoFechasPokemon.contains(fecha)).collect(Collectors.toMap(fecha-> fecha,fecha -> Collections.<Pokemon>emptySet()));
		
		Stream<Map.Entry<LocalDate, Set<Pokemon>>> streamConcatenado=Stream.concat(contieneFecha.entrySet().stream(),noContieneFecha.entrySet().stream());
		
		Map<LocalDate,Set<Pokemon>>res= streamConcatenado.collect(Collectors.toMap(Entry::getKey,Entry::getValue));
		return res;
	}
	
	public Map<LocalDate, Set<Pokemon>> obtenerNPokemonDeFechasConMasSaludV3(Set<LocalDate> fechas, Integer limite){
		Set<LocalDate> conjuntoFechasPokemon  = pokemones.stream().map(Pokemon::getFechaAparicion).collect(Collectors.toSet());
		Comparator<Pokemon> masSaludDescendente = (Comparator.comparing(Pokemon::getPuntosSalud)).reversed();
		Map<LocalDate, Set<Pokemon>> contieneFecha = pokemones.stream().filter(pokemon -> fechas.contains(pokemon.getFechaAparicion())).
				collect(Collectors.groupingBy(Pokemon::getFechaAparicion,()->new HashMap<>(),Collectors.collectingAndThen(Collectors.toList(), lista-> lista.stream().sorted(masSaludDescendente).limit(limite).collect(Collectors.toSet()))));
	fechas.stream().filter(fecha->!conjuntoFechasPokemon.contains(fecha)).forEach(fecha-> contieneFecha.put(fecha,new HashSet<Pokemon>()));
	return contieneFecha;
	}
	
	public Map<LocalDate, Set<Pokemon>> obtenerNPokemonDeFechasConMasSaludV4(Set<LocalDate> fechas, Integer limite){
		Set<LocalDate> conjuntoFechasPokemon  = pokemones.stream().map(Pokemon::getFechaAparicion).collect(Collectors.toSet());
		Function<LocalDate, Set<Pokemon>> conjuntador = fecha -> pokemones.stream().filter(pokemon -> pokemon.getFechaAparicion().equals(fecha)).sorted((Comparator.comparing(Pokemon::getPuntosSalud)).reversed()).limit(limite).collect(Collectors.toSet());
		Function<LocalDate, Set<Pokemon>>asignador = fecha -> {if (conjuntoFechasPokemon.contains(fecha)){return conjuntador.apply(fecha);} else {
			return new HashSet<Pokemon>(); // lo puedo sustituir por uno inmutable
		}};
		return fechas.stream().collect(Collectors.toMap(fecha -> fecha, fecha -> asignador.apply(fecha)));
	}
	

	public LocalDate obtenerEneavaFechaConMasPokemon(Integer posicion) {
		/*
		 * Dada una colección de TipoBase y un parámetro con la posición a elegir, devuelva la eneava fecha con más conciertos.
Ejemplo: Dada una lista de conciertos, tal que 01/01/2021: 130 conciertos, 04/01/2021: 120 conciertos, 07/01/2021: 121 conciertos 10/01/2021: 
45 conciertos, y un parámetro Integer posicion = 3, devuelva la tercera fecha con más conciertos:  04/01/2021

		 */

		if(posicion <1||posicion > pokemones.size() )
			throw new IllegalArgumentException("La posicion solicitada no puede se menor que una ni mayor que el tamaño de pokemones");
		//el posicion-1 es porque como el index emieza en cero la primera posicion es la cero , la segunda la 1 y la tercera la 2 
		Comparator<Entry<LocalDate, Long>> comparador = (fila1,fila2)-> fila1.getValue().compareTo(fila2.getValue()); // el orden es ascendente, por eso le he pueto el reverse
		Map<LocalDate,Long > mapaFechas = pokemones.stream().collect(Collectors.groupingBy(Pokemon::getFechaAparicion,Collectors.counting()));
		LocalDate res =  mapaFechas.entrySet().stream().sorted(comparador.reversed()).map(fila-> fila.getKey()).collect(Collectors.toList()).get(posicion-1);
		return res; 

	}
	public SortedSet<LocalDate> obtenerFechasIncrementos(){

		//TODO
		/*Dada una colección de TipoBase, devuelva un SortedSet<LocalDate> con las fechas ordenadas de más antiguas a más recientes
		 *  con aquellas fechas cuyo número de registros para esa fecha suponga un incremento respecto al número de registros
		 *   de la fecha justamente anterior para la que exista un registro en el dataset.
Ejemplo: Dada una lista de conciertos, devuelva un conjunto ordenado con aquellas fechas cuyo número de conciertos s
uponga un incremento respecto al número de conciertos de la fecha justamente anterior. 
Ejemplo visual: el 01/01/2021: 130 conciertos, 04/01/2021: 120 conciertos, 07/01/2021: 121 conciertos: 
Resultado => {01/01/2021, 07/01/2021}
		 * 
		 */
		// se que tengo que hacer un mapa contador:
		// al mencionar anterior, se que teng que usar una lista 
		// un filter y un predicate  : dada una fecha busca su posicion en la lista 
		Map<LocalDate, Long>  mapaFechas = pokemones.stream().collect(Collectors.groupingBy(Pokemon::getFechaAparicion, Collectors.counting()));
		// en vez de pokemones.stream.map().distict se puede usar mapaFechas.entrySet().stream().map(Entry::getKey) o mapaFechas.keyset().stream
		List<LocalDate>  listaFechas = pokemones.stream().map(Pokemon::getFechaAparicion).distinct().sorted().collect(Collectors.toList());
		Function<LocalDate,Long> nPokemonFechaAnterior = fecha-> mapaFechas.get(listaFechas.get(listaFechas.indexOf(fecha)-1));
		Predicate<LocalDate> tieneIncremento = fecha -> mapaFechas.get(fecha).compareTo(nPokemonFechaAnterior.apply(fecha)) > 0;
		// la primera fecha de todas nos la saltamos all no tener con quien compararla
		SortedSet<LocalDate> res = listaFechas.stream().sorted().skip(1).filter(tieneIncremento).collect(Collectors.toCollection(TreeSet::new));
		return res ;
	}
public SortedSet<LocalDate> obtenerFechasIncrementosForEach() {
	// solo para propositos ilisustrativos
	Map<LocalDate, Long>  mapaFechas = pokemones.stream().collect(Collectors.groupingBy(Pokemon::getFechaAparicion, Collectors.counting()));
	List<LocalDate>  listaFechas = pokemones.stream().map(Pokemon::getFechaAparicion).distinct().sorted().collect(Collectors.toList());
	//Predicate<LocalDate> tieneIncremento = fecha -> mapaFechas.get(fecha).compareTo(nPokemonFechaAnterior.apply(fecha)) <0;
	SortedSet<LocalDate> res = new TreeSet<>();
	Function<LocalDate,Long> nPokemonFechaAnterior = fecha-> mapaFechas.get(listaFechas.get(listaFechas.indexOf(fecha)-1));
	List<LocalDate> listaFechas2 = new ArrayList<>();
	listaFechas2.addAll(listaFechas);
	listaFechas2.remove(0);
	for (LocalDate fecha: listaFechas2) {
		if(mapaFechas.get(fecha).compareTo(nPokemonFechaAnterior.apply(fecha)) > 0 )
			res.add(fecha);
		
	}
	
	return res;
}


	// metodos que son voluntarios y que no se han pedido por ahora
	public Pokemon  pokemonConMasDefensa() {
		List<Pokemon> listaAux = new ArrayList<>();
		listaAux.addAll(pokemones);
		return Collections.max(listaAux, Comparator.comparing(Pokemon::getDefensa));
	}


	public List<Entry<String, Double>> ratioAtaqueDefensaEspecial() {
	//   Devuelve una lista demapentry formada por el nombre del pokemon y  por su ataque especial entre la defensa especial
	//mIrar javatuples o mapentry 
		return pokemones.stream().collect(Collectors.toMap(Pokemon::getNombre,pokemon -> Double.valueOf(pokemon.getAtaqueEspecial())/Double.valueOf(pokemon.getDefensaEspecial()))).entrySet().stream().collect(Collectors.toList());

	}
	public Map<Tipos,Set<String>> agrupaPokemonPorTipoForEach(){
		// proposito ilustrativo, ver la version de stream
		/*
		 * Agrupa los  NOMBRES pokemon por el tipo que tengan tanto en el primario como por el secundario:
		 * Es decir si tenenemos un pokemon cuyos tipos son fuego e hielo ese pokemon ha  de estar en la collecion asocida a fuego e hielo
		 * 
		 */
		Map<Tipos,Set<String>> res = new HashMap<>();
		for(Pokemon pokemon : pokemones) {
			Tipos clave1 = pokemon.getTipo1();
			Tipos clave2 = pokemon.getTipo2();
			String valor = pokemon.getNombre();
			//se puede hacer tambien por encdenacion de else-if 
			if(res.containsKey(clave1)) {
				Set<String> setAux = res.get(clave1);
				setAux.add(valor);
				res.put(clave1,setAux);
			}else {
				Set<String> setAux = new HashSet<String>();
				setAux.add(valor);
				res.put(clave1,setAux);
			}
			if (res.containsKey(clave2)) {
				Set<String> setAux = res.get(clave2);
				setAux.add(valor);
				res.put(clave2, setAux);
			}else {
				Set<String> setAux = new HashSet<String>();
				setAux.add(valor);
				res.put(clave2,setAux);
			}
		}
		return res;
	}
	public Map<Tipos,Set<String>> agrupaPokemonPorTipoStream(){
		
		/*
		 * Agrupa los  NOMBRES pokemon por el tipo que tengan tanto en el primario como por el secundario:
		 * Es decir si tenenemos un pokemon cuyos tipos son fuego e hielo ese pokemon ha  de estar en la collecion asocida a fuego e hielo
		 * 
		 */
		
		Map<Tipos,Set<String>> mapaTipo1 = pokemones.stream().collect(Collectors.groupingBy(Pokemon::getTipo1,Collectors.mapping(Pokemon::getNombre,Collectors.toSet())));
		Map<Tipos,Set<String>> mapaTipo2 = pokemones.stream().collect(Collectors.groupingBy(Pokemon::getTipo2,Collectors.mapping(Pokemon::getNombre,Collectors.toSet())));
		Stream<Map.Entry<Tipos, Set<String>>> streamConcatenado=Stream.concat(mapaTipo1.entrySet().stream(),mapaTipo2.entrySet().stream());
		Map<Tipos,Set<String>>res= streamConcatenado.collect(Collectors.toMap(Entry::getKey,Entry::getValue,(a,b)-> {
			Set<String> setAux = new HashSet<>();
			setAux.addAll(a);
			setAux.addAll(b);
			return setAux;
		}));
		return res;
	}
	public Map<String,List<String>> agrupaPokemonPorParidadDelAño() {
		// se puede hacer por propiedad derivada de pokenon(este caso),o por una function auxiliar.
		

		return pokemones.stream().collect(Collectors.groupingBy(Pokemon::getParidadAño,Collectors.mapping(Pokemon::getNombre, Collectors.toList())));

	}
	public List<String> filtraPorAmbosTipos(Tipos tipo){
		Predicate<Pokemon>  filtroTipos = pokemon -> pokemon.getTipo1().equals(tipo)|| pokemon.getTipo2().equals(tipo);
		return pokemones.stream().filter(filtroTipos).map(Pokemon::getNombre).collect(Collectors.toList());
	}
	public List<LocalDate> listaFechas(){
		// ignora esta funcion solo es para hacer unas pruebas
		return pokemones.stream().map(Pokemon::getFechaAparicion).collect(Collectors.toList());
	}
	public List<Integer> listaNumeros(){
		// ignorar  solo para pruebas igual que la anterior
		return pokemones.stream().map(Pokemon::getAtaqueEspecial).collect(Collectors.toList());
	}
	public Set<LocalDate> conjuntoFechas(Integer n){
		// ignorar  solo para pruebas igual que la anterior
		return pokemones.stream().limit(n).map(Pokemon::getFechaAparicion).collect(Collectors.toSet());
	}
}


