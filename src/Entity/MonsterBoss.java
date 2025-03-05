package Entity;

import Weapon.Bullet;
import javafx.scene.canvas.GraphicsContext;

public class MonsterBoss extends Monster {
	
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
}
