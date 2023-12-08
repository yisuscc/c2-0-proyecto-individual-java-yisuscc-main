package fp.tipo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Pokemon implements Comparable<Pokemon> {
	//Atributos

	private String nombre;
	private Tipos tipo1;
	private Tipos tipo2;
	private Integer total;
	private Integer puntosSalud;
	private Integer ataque;
	private Integer defensa;
	private Integer ataqueEspecial;
	private Integer defensaEspecial;
	private Integer velocidad;
	private Integer estadoEvolutivo;
	private Boolean legendario;
	private LocalDate fechaAparicion;
	private Boolean tieneObjeto;

	// Constructores
	public Pokemon(String nombre, Tipos tipo1, Tipos tipo2, Integer total, Integer puntosSalud, Integer ataque,
			Integer defensa, Integer ataqueEspecial, Integer defensaEspecial, Integer velocidad,
			Integer estadoEvolutivo, Boolean legendario, LocalDate fechaAparicion, Boolean tieneObjeto)  {
		if (nombre == null) {
			throw new IllegalArgumentException("El pokemon tiene que tener un nombre");
		}
		this.nombre = nombre;
		if(tipo1 == null){// no hace falta poner una excecpcion que salte concuando esta vacio (no nul sino,,) porque dará error de compilacion
			throw new IllegalArgumentException("El tipo 1 no puede estar vacio" );
		}
		if (tipo1.equals(Tipos.NONE))
			throw new IllegalArgumentException("El tipo primario no puede ser none");
		if(tipo1.equals(tipo2)) {
			throw new IllegalArgumentException("El tipo primario no puede ser igual al secundario");
		}
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.total = total;
		this.puntosSalud = puntosSalud;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.estadoEvolutivo = estadoEvolutivo;
		this.legendario = legendario;
		this.fechaAparicion = fechaAparicion;
		this.tieneObjeto = tieneObjeto;
	}
	public Pokemon(String linea) {
		/*
		 * String lineaSinEspacio = linea.trim();
		String[] trozos = lineaSinEspacio.split(";");
		 */
		String lineaSinEspacio = linea.trim();
		String[] trozos=  lineaSinEspacio.split(",");
		if (trozos.length != 14) {
			throw new IllegalArgumentException("Un pokemon tiene que tener 14 atributos, ni uno mas ni uno menos");
		}
		if(trozos[0] == null) {
			throw new IllegalArgumentException("El pokemon tiene que tener un nombre");
		}
		this.nombre = trozos[0].trim();//el trim creo que es "innecesario" solo por si acaso lo dejo

		if(trozos[1].equals(null)||trozos[1] ==""|| trozos[1].trim() == Tipos.NONE.toString()) {// el null creo que es inecesario y no se por que no salta la excepcion 
			throw new IllegalArgumentException("El pokemon tiene que tener un tipo primario");
		}
		this.tipo1 = Tipos.valueOf(trozos[1].trim().toUpperCase());// el upper case no se si es necesario lo pongo por si acaso

		if (trozos[2] == tipo1.toString()) {
			throw new IllegalArgumentException("El tipo primario no puede ser igual al secundario");
		}
		if (trozos[2] == null || trozos[2]=="") {
			this.tipo2 = Tipos.NONE;
		}else {
			this.tipo2 = Tipos.valueOf(trozos[2].trim().toUpperCase());
		}
		this.total = Integer.valueOf(trozos[3].trim());
		this.puntosSalud = Integer.valueOf(trozos[4].trim());
		this.ataque = Integer.valueOf(trozos[5].trim());
		this.defensa = Integer.valueOf(trozos[6].trim());
		this.ataqueEspecial =Integer.valueOf(trozos[7].trim());
		this.defensaEspecial = Integer.valueOf(trozos[8].trim());
		this.velocidad = Integer.valueOf(trozos[9].trim());
		this.estadoEvolutivo =Integer.valueOf(trozos[10].trim());
		this.legendario = Boolean.valueOf(trozos[11].trim());
		this.fechaAparicion = LocalDate.parse(trozos[12], DateTimeFormatter.ISO_LOCAL_DATE);//al ser iso no hce falta usar el  DateTimeFormatter.ofPattern("yyyy-MM-dd")
		this.tieneObjeto= Boolean.valueOf(trozos[13].trim().toLowerCase());//  creo que el valueOf se encarga de pasarlo a minusculas pero por si acaso 
	}


	// getters and setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("El pokemon tiene que tener un nombre");
		}
		this.nombre = nombre;
	}

	public Tipos getTipo1() {
		return tipo1;
	}

	public void setTipo1(Tipos tipo1) {
		if(tipo1.toString()== "" || tipo1 == Tipos.NONE || tipo1 == tipo2) {
			throw new IllegalArgumentException("El tipo 1 no puede estar vacio o ser el mismo que el tipo 2");
		}
		this.tipo1 = tipo1;
	}

	public Tipos getTipo2() {
		return tipo2;
	}

	public void setTipo2(Tipos tipo2) {

		this.tipo2 = tipo2;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPuntosSalud() {
		return puntosSalud;
	}

	public void setPuntosSalud(Integer puntosSalud) {
		this.puntosSalud = puntosSalud;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefensa() {
		return defensa;
	}

	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}

	public Integer getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public void setAtaqueEspecial(Integer ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public Integer getDefensaEspecial() {
		return defensaEspecial;
	}

	public void setDefensaEspecial(Integer defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	public Integer getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Integer velocidad) {
		this.velocidad = velocidad;
	}

	public Integer getEstadoEvolutivo() {
		return estadoEvolutivo;
	}

	public void setEstadoEvolutivo(Integer estadoEvolutivo) {
		this.estadoEvolutivo = estadoEvolutivo;
	}

	public Boolean getLegendario() {
		return legendario;
	}

	public void setLegendario(Boolean legendario) {
		this.legendario = legendario;
	}

	public LocalDate getFechaAparicion() {
		return fechaAparicion;
	}

	public void setFechaAparicion(LocalDate fechaAparicion) {
		this.fechaAparicion = fechaAparicion;
	}

	public Boolean getTieneObjeto() {
		return tieneObjeto;
	}

	public void setTieneObjeto(Boolean tieneObjeto) {
		this.tieneObjeto = tieneObjeto;
	}
	// propiedades derivadas:

	public String getAbreviaturaNombreConCHAr() {
		/*parte de lo solicitado para el 6 de abril
		 * Deberá también tener una propiedad derivada 
		 * que devuelva los 3 primeros caracteres de una columna de tipo string que se encuentre en vuestro dataset.
		 */

		String primero = String.valueOf(nombre.charAt(0));
		String segundo = String.valueOf(nombre.charAt(1));
		String tercero = String.valueOf(nombre.charAt(2));
		String cadena = primero + segundo + tercero;
		return cadena;
	}
	@Override
	public String toString() {
		return "Pokemon [nombre=" + nombre + ", tipo1=" + tipo1 + ", tipo2=" + tipo2 + ", total=" + total
				+ ", puntosSalud=" + puntosSalud + ", ataque=" + ataque + ", defensa=" + defensa + ", ataqueEspecial="
				+ ataqueEspecial + ", defensaEspecial=" + defensaEspecial + ", velocidad=" + velocidad
				+ ", estadoEvolutivo=" + estadoEvolutivo + ", legendario=" + legendario + ", fechaAparicion="
				+ fechaAparicion + ", tieneObjeto=" + tieneObjeto + "]";
	}
	public String getAbreviaturaNombre() {
		//si es como sublist, idoneaamente crearia una string res  y volcaria lleo scontenidos , para que asi no le afectase 
		return this.getNombre().substring(0, 3);
	}
	
	public Integer getNumeroDias() {
		Integer res = Period.between(getFechaAparicion(), LocalDate.now()).getDays();
		return res;

	}
	public String getParidadAño() {
		if(this.getFechaAparicion().getYear() %2 == 0)
			return "Par";
		else {
			return "Impar";
		}
	}
	
	// hashcode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ataque == null) ? 0 : ataque.hashCode());
		result = prime * result + ((ataqueEspecial == null) ? 0 : ataqueEspecial.hashCode());
		result = prime * result + ((defensa == null) ? 0 : defensa.hashCode());
		result = prime * result + ((defensaEspecial == null) ? 0 : defensaEspecial.hashCode());
		result = prime * result + ((estadoEvolutivo == null) ? 0 : estadoEvolutivo.hashCode());
		result = prime * result + ((fechaAparicion == null) ? 0 : fechaAparicion.hashCode());
		result = prime * result + ((legendario == null) ? 0 : legendario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((puntosSalud == null) ? 0 : puntosSalud.hashCode());
		result = prime * result + ((tieneObjeto == null) ? 0 : tieneObjeto.hashCode());
		result = prime * result + ((tipo1 == null) ? 0 : tipo1.hashCode());
		result = prime * result + ((tipo2 == null) ? 0 : tipo2.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((velocidad == null) ? 0 : velocidad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (ataque == null) {
			if (other.ataque != null)
				return false;
		} else if (!ataque.equals(other.ataque))
			return false;
		if (ataqueEspecial == null) {
			if (other.ataqueEspecial != null)
				return false;
		} else if (!ataqueEspecial.equals(other.ataqueEspecial))
			return false;
		if (defensa == null) {
			if (other.defensa != null)
				return false;
		} else if (!defensa.equals(other.defensa))
			return false;
		if (defensaEspecial == null) {
			if (other.defensaEspecial != null)
				return false;
		} else if (!defensaEspecial.equals(other.defensaEspecial))
			return false;
		if (estadoEvolutivo == null) {
			if (other.estadoEvolutivo != null)
				return false;
		} else if (!estadoEvolutivo.equals(other.estadoEvolutivo))
			return false;
		if (fechaAparicion == null) {
			if (other.fechaAparicion != null)
				return false;
		} else if (!fechaAparicion.equals(other.fechaAparicion))
			return false;
		if (legendario == null) {
			if (other.legendario != null)
				return false;
		} else if (!legendario.equals(other.legendario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (puntosSalud == null) {
			if (other.puntosSalud != null)
				return false;
		} else if (!puntosSalud.equals(other.puntosSalud))
			return false;
		if (tieneObjeto == null) {
			if (other.tieneObjeto != null)
				return false;
		} else if (!tieneObjeto.equals(other.tieneObjeto))
			return false;
		if (tipo1 != other.tipo1)
			return false;
		if (tipo2 != other.tipo2)
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (velocidad == null) {
			if (other.velocidad != null)
				return false;
		} else if (!velocidad.equals(other.velocidad))
			return false;
		return true;
	}


	//compareTO 
	@Override
	public int compareTo(Pokemon o) {
		// ordena por puntos de salud y en caso de empate por la fecha de aparicion
		int res = this.getPuntosSalud().compareTo(o.getPuntosSalud());
		if (res == 0) {
			res = this.getFechaAparicion().compareTo(o.getFechaAparicion());
		}
		return res;
	}

}










