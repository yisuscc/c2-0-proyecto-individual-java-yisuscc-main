package fp.tipo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import fp.tipo.test.PkemonBaseTest;
public class pruebas {
	private static String ruta = "data/Pokemon.csv";
	private static Pokemones pokedex = new FactoriaPokemon().leerDataset(ruta);
	public static void main(String[] args) {
		 List<LocalDate> fchos= pokedex.listaFechas();
		 fchos.sort(Comparator.naturalOrder());
		System.out.println(fchos);
		List<Integer> numericos = pokedex.listaNumeros();
			System.out.println(numericos);
			numericos.sort(Comparator.naturalOrder());
System.out.println(numericos);		

		   }
	}


