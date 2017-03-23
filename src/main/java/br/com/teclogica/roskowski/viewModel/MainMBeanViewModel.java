package br.com.teclogica.roskowski.viewModel;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.teclogica.roskowski.interfaces.IManterRefeicaoSBean;
import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.IManterAlimentoSBean;
import br.com.teclogica.roskowski.to.TOUsuario;
import br.com.teclogica.roskowski.util.Conversor;
import br.com.teclogica.roskowski.util.Filer;

public class MainMBeanViewModel {
	private static final String DIVIDER = "-";

	public String loadFood(String str,IManterAlimentoSBean imusb){
return imusb.
	}
	public String salvar(TOUsuario u, UploadedFile uf, IManterRefeicaoSBean imusb) throws IOException {

		if (imusb.carregarUsuario(u.getUsuario()) == null) {
			BufferedImage img = ImageIO.read(uf.getInputstream());
			u.setFoto(img);
			uf.getInputstream().close();
			u.setStationaryKey(0);
			u.setImageLink(u.getNome() + DIVIDER + u.getStationaryKey());
			Filer.saveImage(img, u.getImageLink());
			imusb.salvar(u);
			return "/pages/user/main";
		} else {
			return "/pages/repeated";
		}

	}

	public StreamedContent getPerfilImage(String id, IManterUsuarioSBean imusb) {
		return new DefaultStreamedContent(Conversor.toInputStream(Filer.loadImage(imusb.carregar(id).getImageLink())),
				"image/png");
	}

	public String getNomeUsuario(String id, IManterUsuarioSBean imusb) {
		return imusb.carregar(id).getNome();
	}

}
