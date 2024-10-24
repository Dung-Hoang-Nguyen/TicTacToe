package simpleGames;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ttt {
	public static void main( String args[] ){
		new Ttt(4,4,90);
	}
	private char[][] mat;
	   JFrame window;
	   static JButton button, restart;
	   Font mono;
	   JTextArea endScreen;
	   Font big;
	   static int p=1;
	   int winlength,winsize,butsize;
	   String thewinner;
	   public Ttt(int size,int win,int but){
	      winlength=win;winsize=size;butsize=but;
	      mat=new char[winsize][winsize];
	      mono = new Font(Font.MONOSPACED,Font.PLAIN,butsize/3);
	      big = new Font(Font.MONOSPACED,Font.PLAIN,5+winsize*3*(butsize/30));
	      window = new JFrame("Tic Tac Toe");
	      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      window.setBounds(965,250,(int)((winsize+1*(double)2/(butsize/15))*butsize-14),(int)((winsize+1*(double)2/(butsize/15))*butsize+9));//-14,+9
	      window.setLayout(null);
	      TicTacToe();
	      window.setVisible(true);
	   }
	   public void TicTacToe(){
		   for (int i=0;i<mat.length;i++){
			   for(int j=0;j<mat.length;j++){
				   button=initiateButton(i,j);
			   }
		   }
		   mat=new char[winsize][winsize];
	   }

	   private JButton initiateButton(int x,int y){
	      JButton n = new JButton();
	      n.setBounds(x*butsize,y*butsize, butsize, butsize);
	      window.add(n);
	      n.addActionListener( new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               char player;
	               if(p%2==0){player='x';}
	               else{player='o';}
	               p++;
	               switch (player){
	                  case 'x':mat[y][x]='X';
	                  JTextField j=initiateText(x,y);
	                  j.setText("  X");
	                  break;
	                  case 'o':mat[y][x]='O';
	                  JTextField k=initiateText(x,y);
	                  k.setText("  O");
	                  break;
	               }
	               n.setVisible(false);
	               Winner(y,x);
	            }
	        });
	      n.setActionCommand("x");
	      return n;
	   }
	   private JTextField initiateText(int x,int y){
	      JTextField k=new JTextField();
	      k.setBounds(x*butsize, y*butsize, butsize, butsize);
	      window.add(k);
	      k.setFont(mono);
	      k.setEditable(false);
	      return k;
	   }
		public Ttt(String game)
		{
	      int a=0;
	      for (int i=0;i<3;i++){
	         for (int j=0;j<3;j++){
	            mat[i][j]=game.charAt(a);
	            a++;
	         }
	      }
		}
	   public void Winner(int x,int y){
	      for (int i=0;i>=-winlength+1;i--){
	         if (exists(x,y+i)&&exists(x,y+i+winlength-1)){
	            for (int j=i;j<=i+winlength-1;j++){
	               if (mat[x][y]!=mat[x][y+j]){
	                  break;
	               }
	               if (j==i+winlength-1){
	                  thewinner = mat[x][y]+" wins horizontally!";
	                  end();
	                  endScreen.setText("\n    "+mat[x][y]+" wins \n horizontally!");
	                  return;
	               }
	            }
	         }
	      }
	      for (int i=0;i>=-winlength+1;i--){
	         if (exists(x+i,y)&&exists(x+i+winlength-1,y)){
	            for (int j=i;j<=i+winlength-1;j++){
	               if (mat[x][y]!=mat[x+j][y]){
	                  break;
	               }
	               if (j==i+winlength-1){
	                  thewinner = mat[x][y]+" wins vertically!";
	                  end();
	                  endScreen.setText("\n    "+mat[x][y]+" wins \n  vertically!");
	                  return;
	               }
	            }
	         }
	      }
	      for (int i=0;i>=-winlength+1;i--){
	         if (exists(x+i,y+i)&&exists(x+i+winlength-1,y+i+winlength-1)){
	            for (int j=i;j<=i+winlength-1;j++){
	               if (mat[x][y]!=mat[x+j][y+j]){
	                  break;
	               }
	               if (j==i+winlength-1){
	                  thewinner = mat[x][y]+" wins diagonally!";
	                  end();
	                  endScreen.setText("\n    "+mat[x][y]+" wins \n  diagonally!");
	                  return;
	               }
	            }
	         }
	      }
	      for (int i=0;i>=-winlength+1;i--){
	         if (exists(x+i+winlength-1,y-i-winlength+1)&&exists(x+i,y-i)){
	            for (int j=i;j<=i+winlength-1;j++){
	               if (mat[x][y]!=mat[x+j][y-j]){
	                  break;
	               }
	               if (j==i+winlength-1){
	                  thewinner = mat[x][y]+" wins diagonally!";
	                  end();
	                  endScreen.setText("\n    "+mat[x][y]+" wins \n  diagonally!");
	                  return;
	               }
	            }
	         }
	      }
	      for(int i=0;i<mat.length;i++){
	         for(int j=0;j<mat.length;j++){
	            if(mat[i][j]=='\u0000'){return;}
	         }
	      }
	      thewinner="Cat's game! No winner!";
	      end();
	      endScreen.setText("\n  Cat's game!\n   No winner!");
	      return;
		}
	   public boolean exists(int x, int y){
	      if (x>=0&&x<winsize&&y>=0&&y<winsize){return true;}
	      return false;
	   }
	   public void end(){
	      System.out.println(print());
	      window.getContentPane().removeAll();
	      window.repaint();
	      restart=new JButton("Again?");
	      endScreen=new JTextArea();
	      endScreen.setBounds(0,0,(winsize+1)*butsize-14,((winsize+1)*butsize+9)/5*3);
	      endScreen.setFont(big);
	      restart.setBounds(((winsize+1)*butsize-14-88)/2,(winsize+1)*butsize+9-(((winsize+1)*butsize+9)/5*2)/2-45,90,45);
	      window.add(endScreen);
	      window.add(restart);
	      endScreen.setEditable(false);
	      restart.addActionListener( new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                window.getContentPane().removeAll();
	      	      	window.repaint();
	                TicTacToe();
	            }
	        });
	   }
		public String print()
		{
			String output="";
	      int row1=-1;int row2=-1;int col1=-1;int col2=-1;
	      for (int i=0;i<mat.length;i++){
	         for (int j=0;j<mat.length;j++){
	            if(mat[i][j]!='\u0000'&&row1==-1){
	               row1=i;
	            }
	            if(mat[j][i]!='\u0000'&&col1==-1){
	               col1=i;
	            }
	         }
	      }
	      for (int i=mat.length-1;i>=0;i--){
	         for (int j=mat.length-1;j>=0;j--){
	            if(mat[i][j]!='\u0000'&&row2==-1){
	               row2=i;
	            }
	            if(mat[j][i]!='\u0000'&&col2==-1){
	               col2=i;
	            }
	         }
	      }
	      for (int i=row1;i<=row2;i++){
	         for (int j=col1;j<=col2;j++){
	            System.out.print(mat[i][j]+" ");
	         }
	         System.out.println();
	      }
			return output+thewinner+"\n";
		}
}
