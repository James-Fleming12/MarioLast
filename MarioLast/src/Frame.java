import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements KeyListener, ActionListener{
	
	character test =  new character();
	stock peach1= new stock(0, 10);
	stock peach2 = new stock(50,10);
	stock peach3 = new stock(100, 10);
	stock2 mario1 = new stock2(500, 10);
	stock2 mario2 = new stock2(550, 10);
	stock2 mario3 = new stock2(600, 10);
	Music music2 = new Music("background music.wav", true);
	peach other = new peach();
	sword peachS = new sword(0, 1000);
	sword marioS = new sword(0, 1000);
	counter marioC = new counter(1000, 510);
	counter peachC = new counter(1000, 510);
	boolean marioAttack = false; 
	boolean peachAttack = false; 
	boolean hasAttackedP = false, hasAttackedM = false;
	
	boolean peachLeft = false;
	boolean marioLeft = false;
	Music music1= new Music("mario wahoo.wav", true);
	Ground gr = new Ground();
	ArrayList<Ground> groundList = new ArrayList<>();
	ArrayList<Block> blockList = new ArrayList<>();
	boolean onRight = false;
	boolean onLeft = false;
	Block block = new Block();
	boolean onBlock = false;
	int whichBlock = 0; 
	public void paint(Graphics g) {
		super.paintComponent(g);
		test.paint(g);
		other.paint(g);
		peach1.paint(g);
		peach2.paint(g);
		peach3.paint(g);
		mario1.paint(g);
		mario2.paint(g);
		mario3.paint(g);
		peachS.paint(g);
		marioS.paint(g);
		for(Ground gr : groundList) {
			gr.paint(g);
		}
		if(test.getHealth()<=300) {
			mario3.setX(-1000);
		}
		if(test.getHealth()<=150) {
			mario2.setX(-1000);
		}
		if(test.getHealth()<=0) {
			mario1.setX(-1000);
		}
		if(other.getHealth()<=300) {
			peach3.setX(-1000);
		}
		if(other.getHealth()<=150) {
			peach2.setX(-1000);
		}
		if(other.getHealth()<=0) {
			peach1.setX(-1000);
		}
		if(marioC.getY()==30) {
			marioS.setX(10000);
		}
		if(peachC.getY()==20) {
			peachAttack = false;
			peachS.setX(10000);
		}
		if(peachC.getY()==50) {
			hasAttackedP = false; 
		}
		peachC.setY(peachC.getY()+1);
		marioC.setY(marioC.getY()+1);
		
//				if(test.collisionPoint(blockList.get(i).getX(), blockList.get(i).getX()+64, blockList.get(i).getY(), blockList.get(i).getY()+64)) {
//					test.setFallSpeed(0);
//					test.setGravity(0);
//				}else {
//					test.setFallSpeed(-1);
//					
//				}
			
		
		
		
		//collision is under paint because we want to check 
		// multiple times per second ! not just a keypress
		if(test.getY()>=gr.getY()-64) {
			test.setY(gr.getY()-64);
			test.setFallSpeed(0);
			test.setGravity(0);
		}
		if(other.getY()>=gr.getY()-100) { 
			other.setY(gr.getY()-100);
			other.setFallSpeed(0);
			other.setGravity(0);
		}
		//check if there's overlap in the two objects x and y intervals
		//Collision for Left
		if(test.getX()>=400) {
			onRight=true;
		}
		if(other.getX()>=400) {
			onRight=true;
		}
		
		if(test.getX()<0) {
			
		}
		if(other.getX()<0) {
			onLeft=true;
		}
		if(peachAttack && peachLeft == false) {
			peachS.setX(other.getX()+50);
			peachS.setY(other.getY()+50);
		}
		if(marioAttack && marioLeft == false) {
			marioS.setX(test.getX()+30);
			marioS.setY(test.getY()+30);
			}
		if(peachS.getX()<=test.getX() && peachS.getX()+100 >= test.getX() && peachS.getY() <= test.getY()+100 && peachS.getY() >= test.getY()) {
			test.setHealth(test.getHealth()-15);
			System.out.println("test");
		}
		if(peachAttack && peachLeft) {
			peachS.setX(other.getX()-60);
			peachS.setY(other.getY()+50);
		}
		if(marioAttack && marioLeft) {
			marioS.setX(test.getX()-60);
			marioS.setY(test.getY()+50);
		}
	
		//if(onRight) {
			//test.setX(test.getX()-5);
			//other.setX(test.getX()-5);
			//for(Ground gr : groundList) {
			//	gr.setX(gr.getX()-5);
			//}
			//for(Block bl : blockList) {
			//	bl.setX(bl.getX()-5);
			//}
		//}
		//if(onLeft) {
			//test.setX(test.getX()+5);
			//other.setX(test.getX()+5);
			//for(Ground gr : groundList) {
				//gr.setX(gr.getX()+5);
			//}
			//for(Block bl : blockList) {
			//bl.setX(bl.getX()+5);
			//}

	}
	

	
	
	
	public Frame() {
		JFrame f = new JFrame("Mario");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.add(this);
		f.addKeyListener(this);
		t = new Timer(16, this);
		t.start();
		f.setVisible(true);
		for(int i = 0; i<3; i++) {
			Ground temp = new Ground(1000*i); 
			groundList.add(temp);
		}
		blockList.add(new Block());
		music2.play();
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();		
	}	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==39) {
			test.setSpeed(5);
			test.changePicture("/imgs/Mario running.gif");
		}
		if(arg0.getKeyCode()==38) {
			test.jump();
		}if(arg0.getKeyCode()==87) {
			other.jump();
		}
		if(arg0.getKeyCode()==37) {
			test.setSpeed(-5);
			test.changePicture("/imgs/left mario running.gif");
		}		
		if(arg0.getKeyCode()==68) {
			other.changePicture("/imgs/peach.png");
			other.setSpeed(5);
			
		}if(arg0.getKeyCode()==65) {
			other.setSpeed(-5);
			other.changePicture("/imgs/peachleft.png");
		}
		if(arg0.getKeyCode()==70 && hasAttackedP == false) {
			peachAttack = true;
			peachC.setY(0);
			hasAttackedP = true;
		}
		if(arg0.getKeyCode()==96 && hasAttackedM == false) {
			marioAttack=true;
			marioC.setY(0);
			hasAttackedM = true; 
		}



		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==39) {
			test.setSpeed(0);
			test.changePicture("/imgs/Mario standing.png");
			onRight=false;
		}
		if(arg0.getKeyCode()==37) {
			test.setSpeed(0);
			onLeft =false; 
			test.changePicture("/imgs/mario leftstanding.png");
		}
		if(arg0.getKeyCode()==68) {
			other.setSpeed(0);
			onRight=false;
			peachLeft=false;
			
			
		}if(arg0.getKeyCode()==65) {
		other.setSpeed(0);
		
			onLeft =false; 
			peachLeft= true; 
		}
			}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//	
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println("Y" + arg0.get);
//		System.out.println("X" + arg0.getX);
//	}
//	
	Timer t;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
}
