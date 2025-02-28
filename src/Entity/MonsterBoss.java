package Entity;

import Component.Bullet;
import javafx.scene.canvas.GraphicsContext;

public class MonsterBoss extends Monster {
	
	public MonsterBoss(double x, double y)
	{
		 // x,y,speed,hp,damage,imgPath
		super(x , y);
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
    
    public boolean getHit(Bullet bullet) {
    	
        return Math.hypot(x - bullet.x, y - bullet.y) < 100;
    }
    
    public boolean checkCollision(Bullet bullet) 
    {
    	if(getHit( bullet ) && !bullet.isHit()) // check if bullet get close(attack) Monster
    	{
    		bullet.setHit(true);
    		setHp( getHp() - bullet.weaponDamage ); // setHp
    		System.out.println( "Monster got hit damage = " + bullet.weaponDamage + " Hp: " + getHp() );

    	}
        return getHp() == 0;
    }
    

    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 1000 , 1000);
    }
}
