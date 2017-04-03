package br.com.teclogica.roskowski.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private String usuario;
	private String email;
	private String imageLink;
	private long limiteDiario;
	private String senha;
	private int stationaryKey;
	private long totalIngerido;
	private String tipo;

	public Usuario() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getLimiteDiario() {
		return limiteDiario;
	}

	public void setLimiteDiario(long limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getTotalIngerido() {
		return totalIngerido;
	}

	public void setTotalIngerido(long totalIngerido) {
		this.totalIngerido = totalIngerido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getStationaryKey() {
		return stationaryKey;
	}

	public void setStationaryKey(int stationaryKey) {
		this.stationaryKey = stationaryKey;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
