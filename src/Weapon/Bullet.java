package Weapon;
import Entity.Monster;
import Scene.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Bullet {
    public double x, y, dx, dy;
//    static private double speed = 5;
//    private int weaponDamage = 10;
    private boolean isHit = false;
    private String imageStr;
    private Image bulletImage; // ใส่ path ของไฟล์ PNG ที่ต้องการ
    protected double angle;

    
    
    

    public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	public Bullet(double x, double y, double targetX, double targetY,double speed) {
        this.x = x;
        this.y = y;

        angle = Math.atan2(targetY - y, targetX - x);
        this.dx = Math.cos(angle) * speed;
        this.dy = Math.sin(angle) * speed;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    abstract public void render(GraphicsContext gc, double x, double y);
//        gc.setFill(Color.ORANGE);
//        gc.fillOval(x, y , 10, 10);  // Subtract offsets to render the bullet correctly
    protected void setImageStr(String imgPath) 
    {
    	this.imageStr = imgPath;
    	try {
    		setBulletImage(new Image(imgPath));
    	} catch (Exception e) {
    		
    	}
    }
    protected Image getBulletImage()
    {
    	return this.bulletImage;
    }
    private void setBulletImage(Image bulletimage)
    {
    	this.bulletImage = bulletimage;
    }
    




    public boolean isOutOfBounds() {
    	if(x < 0 || x > GameScene.mapWidth || y < 0 || y > GameScene.mapHeight) System.out.println("bullet out of bound");
        return x < 0 || x > GameScene.mapWidth || y < 0 || y > GameScene.mapHeight;
    }

//    public double getSpeed() {
//		return speed;
//	}
//
//	public void setSpeed(double speed) {
//		this.speed = speed;
//	}
//
//	public int getWeaponDamage() {
//		return weaponDamage;
//	}
//
//	public void setWeaponDamage(int weaponDamage) {
//		this.weaponDamage = weaponDamage;
//	}

	public boolean checkCollision(Monster enemy) {
        return isOutOfBounds() || Math.hypot(x - enemy.x, y - enemy.y) < 25;

    }

}
