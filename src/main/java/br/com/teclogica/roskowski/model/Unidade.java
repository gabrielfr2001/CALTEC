package br.com.teclogica.roskowski.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Unidade implements Serializable{
	
	private static final long serialVersionUID = -9067696081111186706L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private double cal;
	private double quantidade;
	@ManyToOne
	private Alimento alimento;
	private long refeicaoid;
	
	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCal() {
		return cal;
	}

	public void setCal(double cal) {
		this.cal = cal;
	}

	public long getRefeicaoId() {
		return refeicaoid;
	}

	public void setRefeicaoid(long refeicaoId) {
		this.refeicaoid = refeicaoId;
	}
}
