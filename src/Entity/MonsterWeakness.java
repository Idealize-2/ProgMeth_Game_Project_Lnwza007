package Entity;

import Component.Bullet;
import Component.Croissant;
import javafx.scene.canvas.GraphicsContext;

public class MonsterWeakness extends Monster {
	
	public MonsterWeakness(double x, double y)
	{
		 // x,y,speed,hp,damage,imgPath
		super(x , y);
		setSpeed(1.5);
		setHp(50);
		setDamage(15);
		setImgPath("/images/MonsterWeakness.jpg");
	}
	
	public int monsterDMG() {
    	return this.damage;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }

    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 40 , 40);
    }
}
