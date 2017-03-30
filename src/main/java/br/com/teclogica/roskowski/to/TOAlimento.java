package br.com.teclogica.roskowski.to;

import java.io.Serializable;

import br.com.teclogica.roskowski.interfaces.BaseInterface;
import br.com.teclogica.roskowski.model.Alimento;

public class TOAlimento implements Serializable, BaseInterface {

	private static final long serialVersionUID = -6894371188017900703L;
	
	private long id;
	private String nome;
	private String color;
	private double gramas;
	private double calorias;
	private String imageLink;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getGramas() {
		return gramas;
	}

	public void setGramas(double gramas) {
		this.gramas = gramas;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Alimento toAlimento() {
		Alimento alimento= new Alimento();

		alimento.setCalorias(calorias);
		alimento.setColor(color);
		alimento.setId(id);
		alimento.setImageLink(imageLink);
		alimento.setGramas(gramas);
		alimento.setNome(nome);
		
		return alimento;
	}

	public TOAlimento toTOAlimento	(Alimento u) {

		this.setCalorias(u.getCalorias());
		this.setColor(u.getColor());
		this.setGramas(u.getGramas());
		this.setId(u.getId());
		this.setImageLink(u.getImageLink());
		this.setNome(u.getNome());
		
		return this;
	}

	@Override
	public String getKey() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
