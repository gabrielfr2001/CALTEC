package br.com.teclogica.roskowski.mbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.teclogica.roskowski.enumeration.TiposRefeicoes;
import br.com.teclogica.roskowski.interfaces.IManterAlimentoSBean;
import br.com.teclogica.roskowski.interfaces.IManterRefeicaoSBean;
import br.com.teclogica.roskowski.interfaces.IManterUnidadeSBean;
import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.ManterAlimentoSBean;
import br.com.teclogica.roskowski.sbean.ManterRefeicaoSBean;
import br.com.teclogica.roskowski.sbean.ManterUnidadeSBean;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;
import br.com.teclogica.roskowski.to.TORefeicao;
import br.com.teclogica.roskowski.viewModel.GraphicMBeanViewModel;

@ManagedBean(name = GraphicMBean.MBEAN)
@ViewScoped
public class GraphicMBean extends AbstractCommonMBean implements Serializable {

	private static final long serialVersionUID = -9047022416096648915L;
	public static final String MBEAN = "graphicMBean";
	public static final String BUNDLE = MAIN_BUNDLE + "graphicPage";

	@EJB
	private IManterUsuarioSBean userBean = new ManterUsuarioSBean();
	@EJB
	private IManterAlimentoSBean alimentoBean = new ManterAlimentoSBean();
	@EJB
	private IManterUnidadeSBean unitBean = new ManterUnidadeSBean();
	@EJB
	private IManterRefeicaoSBean refeicaoBean = new ManterRefeicaoSBean();

	@SuppressWarnings("unused")
	private GraphicMBeanViewModel gmbvw;

	private BarChartModel graphic01;

	public GraphicMBean() {

	}

	@PostConstruct
	public void init() {
		create();
	}

	private void create() {
		graphic01 = initBarModel();
		graphic01.setTitle("Distribuição de alimentação");
		graphic01.setAnimate(true);
		graphic01.setLegendPosition("ne");
		Axis yAxis = graphic01.getAxis(AxisType.Y);
		yAxis = graphic01.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(120);
	}

	public void updateGraphic01() {

	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries healthy = new ChartSeries();
		healthy.setLabel("Nível de achacosidade");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		try {
			date = (Date) dateFormat.parse(dateFormat.format(date));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			for (int i = 0; i < 7; i++) {

				TORefeicao ref = refeicaoBean.carregarData(getUsuarioSessao(), calendar.getTime(),
						TiposRefeicoes.CAFE_DA_MANHA.getNome());
				int total = 0;
				try {
					total += Integer.parseInt(ref.getCorTotal()) / ref.getTotalUnit();
				} catch (Exception e) {

				}
				try {
					ref = refeicaoBean.carregarData(getUsuarioSessao(), date, TiposRefeicoes.LANCHE_DA_MANHA.getNome());
					total += Integer.parseInt(ref.getCorTotal()) / ref.getTotalUnit();
				} catch (Exception e) {

				}
				try {
					ref = refeicaoBean.carregarData(getUsuarioSessao(), date, TiposRefeicoes.ALMOCO.getNome());
					total += Integer.parseInt(ref.getCorTotal()) / ref.getTotalUnit();
				} catch (Exception e) {

				}
				try {
					ref = refeicaoBean.carregarData(getUsuarioSessao(), date, TiposRefeicoes.LANCHE_DA_TARDE.getNome());
					total += Integer.parseInt(ref.getCorTotal()) / ref.getTotalUnit();
				} catch (Exception e) {

				}
				try {
					ref = refeicaoBean.carregarData(getUsuarioSessao(), date, TiposRefeicoes.JANTAR.getNome());
					total += Integer.parseInt(ref.getCorTotal()) / ref.getTotalUnit();
				} catch (Exception e) {

				}
				try {
					ref = refeicaoBean.carregarData(getUsuarioSessao(), date,
							TiposRefeicoes.LANCHE_MADRUGADA.getNome());
					total += Integer.parseInt(ref.getCorTotal()) / ref.getTotalUnit();
				} catch (Exception e) {

				}
				total /= 6;
				healthy.set(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH), total);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addSeries(healthy);

		return model;
	}

	@Override
	public String getBundleDir() {
		return BUNDLE;
	}

	public String getUsuarioSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		return sessionMap.get("user").toString();
	}

	public BarChartModel getGraphic01() {
		return graphic01;
	}

	public void setGraphic01(BarChartModel graphic01) {
		this.graphic01 = graphic01;
	}

}
