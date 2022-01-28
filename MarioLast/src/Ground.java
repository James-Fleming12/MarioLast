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
	
	public class Ground {
		private Image img; 	
		private AffineTransform tx;
		private int x, y;
				private boolean groundFound; 
		//add location attributes
		public Ground() {
			x=0;
			y=500;
			img = getImage("/imgs/marioground.png"); //load the image for Tree
			tx = AffineTransform.getTranslateInstance(x, y);
			init(x, y); 				//initialize the location of the image
										//use your variables
			groundFound = true;
		}
		public Ground(int x) {
			this.x=x;
			y=500;
			img = getImage("/imgs/marioground.png"); //load the image for Tree
			tx = AffineTransform.getTranslateInstance(x, y);
			init(x, y); 				//initialize the location of the image
										//use your variables
			groundFound = true;
		}
	
		public void changePicture(String newFileName) {
			img = getImage(newFileName);
			init(x, y);
		}
		
		public void paint(Graphics g) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, tx, null);
			update();
						
		}
			
			//update the picture variable location
		private void update() {
			tx.setToTranslation(x,y);
			tx.scale(0.5, 0.5);
		}
		
		private void init(double a, double b) {
			tx.setToTranslation(a, b);
			tx.scale(0.5, 0.5);
		}

		private Image getImage(String path) {
			Image tempImage = null;
			try {
				URL imageURL = character.class.getResource(path);
				tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tempImage;
		}
		public void setFound(boolean set) {
			groundFound = set; 
		}
		public boolean getFound() {
			return groundFound; 
		}
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}	