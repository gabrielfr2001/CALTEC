package br.com.teclogica.roskowski.to;

import java.io.Serializable;

import br.com.teclogica.roskowski.model.Unidade;

public class TOUnidade implements Serializable {

	private static final long serialVersionUID = -4387925106586076089L;

	private int id;
	private double quantidade;
	private TOAlimento alimento;
	private long refeicaoId;
	private double cal;

	public TOUnidade() {
		this.alimento = new TOAlimento();
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public TOAlimento getAlimento() {
		return alimento;
	}

	public void setAlimento(TOAlimento alimento) {
		this.alimento = alimento;
	}

	public Unidade toUnidade() {
		Unidade unidade = new Unidade();
		unidade.setAlimento(alimento.toAlimento());
		unidade.setQuantidade(quantidade);
		unidade.setId(id);
		unidade.setCal(cal);
		unidade.setRefeicaoid(refeicaoId);

		return unidade;
	}

	public TOUnidade toTOUnidade(Unidade unidade) {

		this.alimento = new TOAlimento().toTOAlimento(unidade.getAlimento());
		this.quantidade = unidade.getQuantidade();
		this.id = unidade.getId();
		this.cal = unidade.getCal();
		this.refeicaoId = (unidade.getRefeicaoId());
		
		return this;
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
		return refeicaoId;
	}

	public void setRefeicaoId(long refeicaoId) {
		this.refeicaoId = refeicaoId;
	}
}
