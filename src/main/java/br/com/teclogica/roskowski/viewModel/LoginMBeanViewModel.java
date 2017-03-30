package br.com.teclogica.roskowski.viewModel;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.to.TOUsuario;
import br.com.teclogica.roskowski.util.Conversor;
import br.com.teclogica.roskowski.util.Filer;

public class LoginMBeanViewModel {
	private static final String DIVIDER = "-";

	public LoginMBeanViewModel() {

	}

	public String salvar(TOUsuario u, UploadedFile uf, IManterUsuarioSBean imusb) throws IOException {

		if (imusb.carregarUsuario(u.getUsuario()) == null) {
			BufferedImage img = ImageIO.read(uf.getInputstream());
			u.setFoto(img);
			uf.getInputstream().close();
			u.setStationaryKey(0);
			u.setImageLink(u.getNome() + DIVIDER + u.getStationaryKey());
			Filer.saveImage(img, u.getImageLink());
			imusb.salvar(u);
			return "/pages/user/main?faces-redirect=true";
		} else {
			return "/pages/repeated?faces-redirect=true";
		}

	}

	public String logar(TOUsuario usuario, IManterUsuarioSBean imusb) {
		TOUsuario check = imusb.carregarUsuario(usuario.getUsuario());

		if (check == null) {
			check = imusb.carregarEmail(usuario.getUsuario());
		}

		if (check == null) {
			return "/pages/unautorizhed?faces-redirect=true";
		}

		if (check.getSenha().equals(usuario.getSenha())) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.setAttribute("user", check.getId());
			return "/pages/user/main?faces-redirect=true";
		}

		return "/pages/unautorizhed?faces-redirect=true";
	}

	public StreamedContent getPerfilImage(String id, IManterUsuarioSBean imusb) {
		return new DefaultStreamedContent(Conversor.toInputStream(Filer.loadImage(imusb.carregar(id).getImageLink())),
				"image/png");
	}

	public String getNomeUsuario(String id, IManterUsuarioSBean imusb) {
		return imusb.carregar(id).getNome();
	}
}
