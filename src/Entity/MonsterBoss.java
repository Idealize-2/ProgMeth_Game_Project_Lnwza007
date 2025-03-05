package Entity;

import AnimationEffect.Animationable;
import Weapon.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MonsterBoss extends Monster{
	
	public MonsterBoss(double x, double y)
	{
		 // x,y,speed,hp,damage,imgPath
		super(x , y);
		setReward(100);
		setSpeed(0.75);
		setHp(200);
		setDamage(40);
		setImgPath("/images/Boss.jpg");
	}
	
	public int monsterDMG() {
    	return this.damage;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }
    

    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 1000 , 1000);
    }
    
    private Image pFrame1 = new Image("images/Airi_plush.jpg");
	private Image pFrame2 = new Image("images/Airi2.jpg");
	private Image pFrame3 = new Image("images/Airi3.png");
	
	private long frameDelay = 100 * 3;
	private long currentframe = 0;
    
    @Override
	public void renderAnimation(GraphicsContext gc, double x, double y) {
		// TODO Auto-generated method stub
		
	}
}
