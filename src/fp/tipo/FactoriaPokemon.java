package fp.tipo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FactoriaPokemon {
	public static Pokemones leerDataset(String rutaArchivo) {
		Path ruta = Paths.get(rutaArchivo);
		  Pokemones pokemones = null;
		try {
			pokemones = new Pokemones(Files.lines(ruta).skip(1).map(FactoriaPokemon::parsearPokemon));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Pokemones pokemones =new Pokemones(streamPokemon);
		return pokemones;
		
	}
	private static Pokemon parsearPokemon(String linea) {
		Pokemon res = new Pokemon(linea);
		return res;
		
	}

}
