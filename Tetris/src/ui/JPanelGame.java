package ui;

import java.awt.Graphics;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import config.ConfigFactory;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import dto.Player;
import service.GameService;

public class JPanelGame extends JPanel {

	private List<Layer> layers = null;

	private GameDto dto = null;

	public JPanelGame(GameDto dto) {
		//获得dto对象
		this.dto = dto;
		// 初始化组件
		initComponent();
		// 初始化层
		initLayer();
		// lays = new Layer[] {
		// // 硬编码是非常不好的开发习惯，
		// // 我们要尽量将数字或字符串定义为常量，或者写入配置文件。
		// new LayerBackground(0, 0, 0, 0), new LayerDataBase(40, 32, 334, 279), new
		// LayerDisk(40, 343, 334, 279),
		// new LayerGame(414, 32, 334, 590), new LayerButton(788, 32, 334, 124), new
		// LayerNext(788, 188, 176, 148),
		// new LayerLevel(964, 188, 158, 148), new Layerpoint(788, 368, 334, 200) };
	}

	/**
	 * 安装玩家控制器
	 * 
	 * @param control
	 */
	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}

	/**
	 * 初始化组件
	 */
	private void initComponent() {

	}

	/**
	 * 层初始化
	 */
	private void initLayer() {
		try {
			// 获得游戏配置
			GameConfig cfg = ConfigFactory.getGameConfig();
			// 获得层配置
			List<LayerConfig> layersCfg = cfg.getLayersConfig();
			// 创建游戏层数组
			layers = new ArrayList<Layer>(layersCfg.size());
			// 创建所有层对象
			for (LayerConfig layerCfg : layersCfg) {
				// 获得类对象
				Class<?> cls = Class.forName(layerCfg.getClassName());
				// 获得构造函数
				Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
				// 调用构造函数创建对象
				Layer layer = (Layer) ctr.newInstance(layerCfg.getX(), layerCfg.getY(), layerCfg.getW(), layerCfg.getH());
				//设置游戏数据对象
				layer.setDto(this.dto);
				// 把创建的Layer对象放入集合
				layers.add(layer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// 调用基类方法
		super.paintComponent(g);
		// 绘制游戏界面
		for (int i = 0; i < layers.size(); layers.get(i++).paint(g))
			;
		// 返回焦点
		this.requestFocus();
	}
}
