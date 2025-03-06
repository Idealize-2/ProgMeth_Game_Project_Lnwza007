package Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class BaseEntity {
	 	public double x, y;
	 	protected double speed;
	    protected int hp;
	    private int maxHp;
	    private int damage;
	    private Image entityImage; // ใส่ path ของไฟล์ PNG ที่ต้องการ
	    private String imgPath;
	    
	    private int width = 40 , height = 40;
	    
	    
	    public BaseEntity( double x , double y , double speed , int hp , int damage , String imagePath)
	    {
	    	this.x = x;
	    	this.y = y;
	    	setSpeed(speed);
	    	setMaxHp(hp);
	    	setHp(hp);
	    	setDamage(damage);
	    	setImgPath(imagePath);
	        
	    }
	    
	    
	    
		public String getImgPath() {
			return imgPath;
		}



		public void setImgPath(String imgPath) {
			this.imgPath = imgPath;
			try {
	    		
	    		setEntityImage(new Image(imgPath));
	    		
	    	} catch (Exception e) {
	            e.printStackTrace();
	        }
		}

		public int getWidth() {
			return width;
		}



		public void setWidth(int width) {
			this.width = width;
		}



		public int getHeight() {
			return height;
		}



		public void setHeight(int height) {
			this.height = height;
		}

		
		

		public int getMaxHp() {
			return maxHp;
		}



		public void setMaxHp(int maxHp) {
			this.maxHp = maxHp;
		}



		public double getSpeed() {
			return speed;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public int getHp() {
			return this.hp;
		}
		public void setHp(int hp) {
			this.hp = Math.min(hp, maxHp);
		}
		public int getDamage() {
			return damage;
		}
		public void setDamage(int damage) {
			this.damage = damage;
		}
		public Image getEntityImage() {
			return entityImage;
		}
		public void setEntityImage(Image entityImage) {
			this.entityImage = entityImage;
		}
		
		public void render(GraphicsContext gc , double x , double y) {
	        gc.drawImage(getEntityImage(), x, y, width, height);
	    }




}
