package Entity;
import javafx.scene.image.Image;

public abstract class BaseEntity {
	 	public double x, y;
	 	protected double speed;
	    private int hp;
	    public int damage;
	    private Image entityImage; // ใส่ path ของไฟล์ PNG ที่ต้องการ
	    private String imgPath;
	    
	    
	    public BaseEntity( double x , double y , double speed , int hp , int damage , String imagePath)
	    {
	    	this.x = x;
	    	this.y = y;
	    	setSpeed(speed);
	    	setHp(hp);
	    	setDamage(damage);
	    	setImgPath(imagePath);
	    	try {
	    		
	    		setEntityImage(new Image(imgPath));
	    		
	    	} catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }
	    
	    
	    
		public String getImgPath() {
			return imgPath;
		}



		public void setImgPath(String imgPath) {
			this.imgPath = imgPath;
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
			this.hp = Math.max(hp, 0);
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



}
