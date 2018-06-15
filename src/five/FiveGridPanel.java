package five;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.*;
public  class FiveGridPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	int row,column;//面板所属在棋盘上的网格位置
	public  FiveGridPanel(int row,int column)//构造函数获得当前面板所在网格位置
	{
		this.row =row;
		this.column=column;
		setBackground(Color.yellow);
		this.addMouseListener(new PutCheese());
	}
	
	public void paintComponent(Graphics g){//画网格线
		if(row==0&&column==0){//左上角
			g.drawLine(getWidth()/2, getHeight()/2, getWidth(),getHeight()/2);
			g.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getWidth());
		}
		if(row==0&&column==12){//右上角
			g.drawLine(0, getHeight()/2, getWidth()/2,getHeight()/2);
			g.drawLine(getWidth()/2, getHeight()/2,getWidth()/2,getHeight());
		}
		if(row==12&&column==0){//左下角
			g.drawLine(getWidth()/2, 0, getWidth()/2,getHeight()/2);
			g.drawLine(getWidth()/2, getHeight()/2, getWidth(), getHeight()/2);
			
		}
		if(row==12&&column==12){//右下角
			g.drawLine(getWidth()/2,0,getWidth()/2,getHeight()/2);
			g.drawLine(0, getHeight()/2, getWidth()/2, getHeight()/2);
		}
		if(row==0&&column!=0&&column!=12){//最上行
			g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
			g.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getHeight());
		}
		if(column==0&&row!=0&&row!=12){//最左列
			g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
			g.drawLine(getWidth()/2, getHeight()/2, getWidth(), getHeight()/2);
		}
		if(row==12&&column!=0&&column!=12){//最下行
			g.drawLine(getWidth()/2,0,getWidth()/2,getHeight()/2);
			g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
		}
		if(column==12&&row!=0&&row!=12){//最右列
			g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
			g.drawLine(0, getHeight()/2, getWidth()/2, getHeight()/2);
		}
		if(row!=0&&row!=12&&column!=0&&column!=12){
			g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
			g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
			
			
			//重画棋子
			if(1==FiveCheese.allcheese[row][column]){
				g.setColor(Color.black);
				g.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
			}
			else if(2==FiveCheese.allcheese[row][column]){
				g.setColor(Color.white);
				g.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
			}
	}
	}

	
	public void mesageboxblack(int cheese){//弹出消息
		if(1==cheese){
			JOptionPane.showMessageDialog(this,"黑方获胜！","游戏结果", JOptionPane.INFORMATION_MESSAGE);
		}else if(2==cheese){
			JOptionPane.showMessageDialog(this,"白方获胜！","游戏结果", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	
	class PutCheese extends MouseAdapter//落子的方法
	{
		public void mouseClicked(MouseEvent e)
		{
			if(0!=FiveCheese.allcheese[row][column]){//如果此格已经落过子，就不再落子覆盖
				System.out.println("此位置已落过子");
				return ;
				}
			System.out.println("落子位置:"+"["+row+"]"+"["+column+"}");
			if(1==FiveCheese.cheese)//如果是1，下黑子
			{
			Graphics g=getGraphics();
			g.setColor(Color.black);
			g.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
			FiveCheese.allcheese[row][column]=1;
			
			FiveCheese.cheese=2;//设为2，下次落子是白子
			//判断黑子的五子情况
			//判断
			Choose();

			
			}
			else if(2==FiveCheese.cheese)//如果是2，下白子
			{
				Graphics g=getGraphics();
				g.setColor(Color.white);
				g.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
				FiveCheese.allcheese[row][column]=2;
				FiveCheese.cheese=1;//下次落子为黑子
				//横坐标为column,纵坐标为row
				
				//判断
				Choose();
			}
			System.out.println("---将黑白棋的当前棋盘情况进行显示----");
			for(int i=0;i<13;i++)//输出棋子的位置
			{
				for(int j=0;j<13;j++)
				{
					System.out.print(FiveCheese.allcheese[i][j]);
				}
				System.out.println();
			}
		}
	}

	public void Choose()
	{
		int black_count=0;
		int white_count=0;
		for(int i=0;i<13;i++)  //横向判断
		{
			for(int j=0;j<13;j++)
			{
				if(FiveCheese.allcheese[i][j]==1)
				{
					black_count++;
					if(black_count==5)
					{
						JOptionPane.showMessageDialog(this, "黑方获胜");
						return;
					}
				}
				else
				{
					black_count=0;
				}
				if(FiveCheese.allcheese[i][j]==2)
				{
					white_count++;
					if(white_count==5)
					{
						JOptionPane.showMessageDialog(this, "白方获胜");
						return;
					}
				}
				else
				{
					white_count=0;
				}
			}
		}
		
		for(int i=0;i<13;i++)  //竖向判断
		{
			for(int j=0;j<13;j++)
			{
				if(FiveCheese.allcheese[j][i]==1)
				{
					black_count++;
					if(black_count==5)
					{
						JOptionPane.showMessageDialog(this, "黑方获胜");
						return;
					}
				}
				else
				{
					black_count=0;
				}
				if(FiveCheese.allcheese[j][i]==2)
				{
					white_count++;
					if(white_count==5)
					{
						JOptionPane.showMessageDialog(this, "白方获胜");
						return;
					}
				}
				else
				{
					white_count=0;
				}
			}
		}
		
		for(int i=0;i<7;i++)  //右斜判断
		{
			for(int j=0;j<7;j++)
			{
				for(int k=0;k<5;k++)
				{
					if(FiveCheese.allcheese[i+k][j+k]==1)
					{
						black_count++;
						if(black_count==5)
						{
							JOptionPane.showMessageDialog(this, "黑方获胜");
							return;
						}
					}
					else
					{
						black_count=0;
					}
					if(FiveCheese.allcheese[i+k][j+k]==2)
					{
						white_count++;
						if(white_count==5)
						{
							JOptionPane.showMessageDialog(this, "白方获胜");
							return;
						}
					}
					else
					{
						white_count=0;
					}
				}
			}
		}
		
		for(int i=4;i<13;i++)  //左斜判断
		{
			for(int j=6;j>=0;j--)
			{
				for(int k=0;k<5;k++)
				{
					if(FiveCheese.allcheese[i-k][j+k]==1)
					{
						black_count++;
						if(black_count==5)
						{
							JOptionPane.showMessageDialog(this, "黑方获胜");
							return;
						}
					}
					else
					{
						black_count=0;
					}
					if(FiveCheese.allcheese[i-k][j+k]==2)
					{
						white_count++;
						if(white_count==5)
						{
							JOptionPane.showMessageDialog(this, "白方获胜");
							return;
						}
					}
					else
					{
						white_count=0;
					}
				}
			}
		}
		

	}
}
	/*
	public void determinant(int cheese){//判断
		int count=0;	



		
		
		for(int i=0;i<12;i++){//判断垂直
			if(cheese==FiveCheese.allcheese[i][column]){
				count++;
			}
			else
			{
				count=0;
			}
			if(5==count){
				mesageboxblack(cheese);
			}
		}
		for(int i=0;i<12;i++){//判断横向
			if(cheese==FiveCheese.allcheese[row][i]){
				count++;
			}
			else
			{
				count=0;
			}
			if(5==count){
				mesageboxblack(cheese);
			}
		}
		
		
		
		//判断左斜，左上上到右下遍历
		if(row<column){//右上角区域
			for(int i=0,lr_1=0,lc_1=column-row;i<12;i++,lr_1++,lc_1++){//判断左斜
				if(cheese==FiveCheese.allcheese[lr_1][lc_1]){
					count++;
				}
				else{
					count=0;
				}
				if(5==count){
					mesageboxblack(cheese);
				}
			}
		}
		
		else if(row>=column){//左下角区域包括左斜线
			for(int i=0,lr_2=row-column,lc_2=0;i<12;i++,lr_2++,lc_2++){//判断左斜
				if(cheese==FiveCheese.allcheese[lr_2][lc_2]){
					count++;
				}
				else{
					count=0;
				}
				if(5==count){
					mesageboxblack(cheese);
				}
			}
		}
			
			

		//判断右斜，右上到左下
		
		
			for(int i=0,rr_1=column-(12-row),rc_1=row+column;i<12;i++,rr_1++,rc_1--){
				if(rr_1<0){rr_1=0;}//如果rr_1<0就让它等于0
				if(rc_1>12){rc_1=12;}//如果rc_1>12就让它等于12
				if(cheese==FiveCheese.allcheese[rr_1][rc_1]){
					count++;
				}
				else{
					count=0;
				}
				if(5==count){
					mesageboxblack(cheese);
				}
			}
			
		
	
	}
	}
*/