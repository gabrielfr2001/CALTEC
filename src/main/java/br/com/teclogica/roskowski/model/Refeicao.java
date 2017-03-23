package br.com.teclogica.roskowski.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.teclogica.roskowski.enumeration.TiposRefeicoes;

@Entity
public class Refeicao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private TiposRefeicoes tipo;
	@OneToMany
	private List<Unidade> unidades;
	private Date data;
	private long userid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TiposRefeicoes getTipo() {
		return tipo;
	}

	public void setTipo(TiposRefeicoes tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

}
