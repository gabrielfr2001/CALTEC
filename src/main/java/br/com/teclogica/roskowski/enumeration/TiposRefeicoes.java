package br.com.teclogica.roskowski.enumeration;

public enum TiposRefeicoes {

	CAFE_DA_MANHA("Caf� da manh�"), LANCHE_DA_MANHA("Lanche da manh�"), ALMOCO("Almo�o"), LANCHE_DA_TARDE(
			"Lanche da tarde"), JANTAR("Jantar"), LANCHE_MADRUGADA("Lanche da madrugada");

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
