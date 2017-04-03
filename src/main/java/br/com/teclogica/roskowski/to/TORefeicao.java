package br.com.teclogica.roskowski.to;

import java.util.Date;

import br.com.teclogica.roskowski.enumeration.TiposRefeicoes;
import br.com.teclogica.roskowski.model.Refeicao;

public class TORefeicao {
	private long id;
	private TiposRefeicoes tipo;
	private Date data;
	private double totalCal;
	private long userid;
	private String corTotal;
	private int totalUnit;

	public TORefeicao() {
		totalUnit = 0;
		corTotal = "0";
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

	public String getCompositeColor() {
		if (totalUnit != 0) {
			int r = (255 * (Integer.parseInt(corTotal) / totalUnit)) / 100;
			int g = (255 * (100 - (Integer.parseInt(corTotal) / totalUnit))) / 100;
			int b = 0;
			return String.format("#%02x%02x%02x", r, g, b);
		} else {
			int r = (255 * (0)) / 100;
			int g = (255 * (100 - (0))) / 100;
			int b = 0;
			return String.format("#%02x%02x%02x", r, g, b);
		}
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
		refeicao.setCorTotal(corTotal);
		refeicao.setTotalUnit(totalUnit);

		return refeicao;
	}

	public TORefeicao toTORefeicao(Refeicao u) {

		this.data = u.getData();
		this.id = u.getId();
		this.totalCal = u.getTotalCal();
		this.tipo = u.getTipo();
		this.corTotal = u.getCorTotal();
		this.userid = u.getUserid();
		this.totalUnit = u.getTotalUnit();

		return this;
	}

	public double getTotalCal() {
		return totalCal;
	}

	public void setTotalCal(double totalCal) {
		this.totalCal = totalCal;
	}

	public String getCorTotal() {
		return corTotal;
	}

	public void setCorTotal(String corTotal) {
		this.corTotal = corTotal;
	}

	public int getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(int totalUnit) {
		this.totalUnit = totalUnit;
	}
}
