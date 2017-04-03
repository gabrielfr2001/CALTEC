package br.com.teclogica.roskowski.mbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.teclogica.roskowski.interfaces.IManterAlimentoSBean;
import br.com.teclogica.roskowski.interfaces.IManterRefeicaoSBean;
import br.com.teclogica.roskowski.interfaces.IManterUnidadeSBean;
import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.ManterAlimentoSBean;
import br.com.teclogica.roskowski.sbean.ManterRefeicaoSBean;
import br.com.teclogica.roskowski.sbean.ManterUnidadeSBean;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;
import br.com.teclogica.roskowski.viewModel.GraphicMBeanViewModel;

@ManagedBean(name = GraphicMBean.MBEAN)
@SessionScoped
public class GraphicMBean extends AbstractCommonMBean implements Serializable {

	private static final long serialVersionUID = -9047022416096648915L;
	public static final String MBEAN = "graphicMBean";
	public static final String BUNDLE = MAIN_BUNDLE + "graphicPage";

	@EJB
	private IManterUsuarioSBean sBean = new ManterUsuarioSBean();
	@EJB
	private IManterAlimentoSBean ssBean = new ManterAlimentoSBean();
	@EJB
	private IManterUnidadeSBean sssBean = new ManterUnidadeSBean();
	@EJB
	private IManterRefeicaoSBean ssssBean = new ManterRefeicaoSBean();

	@SuppressWarnings("unused")
	private GraphicMBeanViewModel gmbvw;

	private BarChartModel graphic01;

	public GraphicMBean() {
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
		yAxis.setMax(200);
	}

	public void updateGraphic01() {

	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries healthy = new ChartSeries();
		healthy.setLabel("Saudável");
		
		healthy.set("2004", 120);
		healthy.set("2005", 100);
		healthy.set("2006", 44);
		healthy.set("2007", 150);
		healthy.set("2008", 25);

		ChartSeries junky = new ChartSeries();
		junky.setLabel("Achacoso");
		
		junky.set("2004", 52);
		junky.set("2005", 60);
		junky.set("2006", 110);
		junky.set("2007", 135);
		junky.set("2008", 120);

		model.addSeries(healthy);
		model.addSeries(junky);

		return model;
	}

	@Override
	public String getBundleDir() {
		return BUNDLE;
	}

	public BarChartModel getGraphic01() {
		return graphic01;
	}

	public void setGraphic01(BarChartModel graphic01) {
		this.graphic01 = graphic01;
	}

}
