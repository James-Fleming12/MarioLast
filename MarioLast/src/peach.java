import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
	public class peach {
		//add location attributes
		private Image img; 	
		private AffineTransform tx;
		int x, y;
		int speed; 
		double fallSpeed; 
		boolean grounded;
		double gravity;
		int health; 
		public peach() {
			img = getImage("/imgs/peach.png"); //load the image for Tree
			tx = AffineTransform.getTranslateInstance(x, y);
			init(x, y); 				//initialize the location of the image
										//use your variables
			speed=0;
			fallSpeed=0;
			gravity=1.5;
			grounded = false; 
			health = 450; 
		
		}
	
		public void changePicture(String newFileName) {
			img = getImage(newFileName);
			init(x, y);
		}
		
		public void paint(Graphics g) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, tx, null);
			x+=speed; 
			y+=fallSpeed;
			fallSpeed+=gravity; 
			update();
			if(gravity==0) {
				grounded = true;
			}
		}
		//lll
		
		public void jump() {
			if(grounded) {
				gravity = 1.5;
				fallSpeed=50*-0.5;
				grounded=false; 
			}
		}
		public boolean collisionX(int n, int m) {
			if(x+32>n && x<m) {
				return true;
			}
			return false;
		}
		public boolean collisionY(int n, int m) {
			if(y+64>n && y<m) {
				return true;
			}
			return false;
		}
		public boolean collisionPoint(int x1, int x2, int y1, int y2) {
			if(x+32>x1 && x<x2 && y+64>y1 && y<y2) {
				return true;
			}
			return false;
		}

			//update the picture variable location
		private void update() {
			tx.setToTranslation(x,y);
			tx.scale(0.2, 0.2);
		}
		
		private void init(double a, double b) {
			tx.setToTranslation(a, b);
			tx.scale(.2, .2);
		}

		private Image getImage(String path) {
			Image tempImage = null;
			try {
				URL imageURL = peach.class.getResource(path);
				tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tempImage;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}
		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}


		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		public double getFallSpeed() {
			return fallSpeed;
		}

		public void setFallSpeed(double fallSpeed) {
			this.fallSpeed = fallSpeed;
		}

		public double getGravity() {
			return gravity;
		}

		public void setGravity(double gravity) {
			this.gravity = gravity;
		}
		public int getHealth() {
			return health;
		}

		public void setHealth(int health) {
			this.health = health;
		}




	}

