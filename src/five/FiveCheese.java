package five;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
/**
 * 
 * author: loserStar
 * date: 2018年6月15日下午2:47:03
 * remarks:五子棋启动程序
 */
public  class FiveCheese extends JFrame {

	private static final long serialVersionUID = 1L;
	FiveGridPanel fgp[][]=new FiveGridPanel[13][13];//13X13的单元面板数组
	JPanel BackPanel=new JPanel();//棋盘面板
	
	static int cheese=1;//1是下黑子，2是下白子
	public static int allcheese[][]=new int[13][13];//存储棋子的位置
	static
	{
		for(int i=0;i<13;i++)
		{
			for(int j=0;j<13;j++)
			{
				allcheese[i][j]=0;
			}
		}
	}
	
	public FiveCheese(){
		getContentPane().add(BackPanel);//棋盘面板添加到窗体中
		BackPanel.setBackground(Color.PINK);//背景为黄色
		BackPanel.setLayout(new GridLayout(13,13));//将棋盘分为13*13的网格

		
		//通过循环创建13*13面板
		for(int i=0;i<13;i++){
			for(int j=0;j<13;j++){
				fgp[i][j]=new FiveGridPanel(i,j);//根据 行列创建面板
				BackPanel.add(fgp[i][j]);//自作而右，自上而下顺序添加
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FiveCheese fcheese=new FiveCheese();//创建窗体对象
		fcheese.setSize(500,500);
		fcheese.setResizable(false);//大小不能更改
		fcheese.setVisible(true);//可见
		fcheese.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
