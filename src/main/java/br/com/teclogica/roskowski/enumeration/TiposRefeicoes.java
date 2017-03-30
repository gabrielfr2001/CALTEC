package br.com.teclogica.roskowski.enumeration;

public enum TiposRefeicoes {

	CAFE_DA_MANHA("CAFE_DA_MANHA"), LANCHE_DA_MANHA("LANCHE_DA_MANHA"), ALMOCO("ALMOCO"), LANCHE_DA_TARDE(
			"LANCHE_DA_TARDE"), JANTAR("JANTAR"), LANCHE_MADRUGADA("LANCHE_MADRUGADA");

	private String nome;

	TiposRefeicoes(String str) {
		setNome(str);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
