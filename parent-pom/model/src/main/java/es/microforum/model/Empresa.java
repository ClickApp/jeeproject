package es.microforum.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

//

@Entity
@Table(name = "empresa", catalog = "jee")
public class Empresa implements java.io.Serializable {

	private String nif;
	private Integer version;
	private String nombre;
	private String direccionFiscal;
	private Date fechaInicioActividades;
	private Set<Empleado> empleados = new HashSet<Empleado>(0);

	public Empresa() {
	}

	public Empresa(String nif) {
		this.nif = nif;
	}

	public Empresa(String nif, String nombre, String direccionFiscal,
			Date fechaInicioActividades, Set<Empleado> empleados) {
		this.nif = nif;
		this.nombre = nombre;
		this.direccionFiscal = direccionFiscal;
		this.fechaInicioActividades = fechaInicioActividades;
		this.empleados = empleados;
	}

	@Id
	@Column(name = "nif", unique = true, nullable = false, length = 20)
	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "direccionFiscal", length = 45)
	public String getDireccionFiscal() {
		return this.direccionFiscal;
	}

	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaInicioActividades", length = 19)
	public Date getFechaInicioActividades() {
		return this.fechaInicioActividades;
	}

	public void setFechaInicioActividades(Date fechaInicioActividades) {
		this.fechaInicioActividades = fechaInicioActividades;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
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
		Empresa other = (Empresa) obj;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [nif=" + nif + ", version=" + version + ", nombre="
				+ nombre + ", direccionFiscal=" + direccionFiscal
				+ ", fechaInicioActividades=" + fechaInicioActividades
				+ ", empleados=" + empleados + "]";
	}	
}
