package br.com.teclogica.roskowski.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.teclogica.roskowski.enumeration.TiposRefeicoes;
import br.com.teclogica.roskowski.model.Refeicao;
import br.com.teclogica.roskowski.model.Unidade;

public class TORefeicao {
	private long id;
	private TiposRefeicoes tipo;
	private Date data;
	private double totalCal;
	private long userid;

	public TORefeicao() {
	}

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


	public Refeicao toRefeicao() {
		Refeicao refeicao = new Refeicao();

		refeicao.setData(data);
		refeicao.setId(id);
		refeicao.setTipo(tipo);
		refeicao.setTotalCal(totalCal);
		refeicao.setUserid(userid);

		return refeicao;
	}

	public TORefeicao toTORefeicao(Refeicao u) {

		this.data = u.getData();
		this.id = u.getId();
		this.totalCal = u.getTotalCal();
		this.tipo = u.getTipo();

		this.userid = u.getUserid();

		return this;
	}

	public double getTotalCal() {
		return totalCal;
	}

	public void setTotalCal(double totalCal) {
		this.totalCal = totalCal;
	}
}
