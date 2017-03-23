package br.com.teclogica.roskowski.to;

import java.awt.Image;

import br.com.teclogica.roskowski.model.Usuario;
import br.com.teclogica.roskowski.util.Filer;

public class TOUsuario {
	private long id;
	private int stationaryKey;
	private String nome;
	private String usuario;
	private String email;
	private String imageLink;
	private Image foto;
	private long limiteDiario;
	private String senha;
	private long totalIngerido;

	public TOUsuario() {
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

	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
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

	public Usuario toUsuario() {
		Usuario user = new Usuario();

		user.setEmail(email);
		user.setImageLink(imageLink);
		user.setLimiteDiario(limiteDiario);
		user.setNome(nome);
		user.setUsuario(usuario);
		user.setSenha(senha);
		user.setStationaryKey(stationaryKey);
		user.setTotalIngerido(totalIngerido);

		return user;
	}

	public TOUsuario toTOUsuario(Usuario u) {
		this.email = u.getEmail();
		this.id = u.getId();
		this.limiteDiario = u.getLimiteDiario();
		this.nome = u.getNome();
		this.usuario = u.getUsuario();
		this.senha = u.getSenha();
		this.foto = Filer.loadImage(u.getImageLink());
		this.totalIngerido = u.getTotalIngerido();
		this.imageLink = u.getImageLink();
		this.stationaryKey = u.getStationaryKey();
		return this;
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
}
