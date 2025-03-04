package Entity;

import Weapon.Bullet;
import Weapon.Croissant;
import javafx.scene.canvas.GraphicsContext;

public class MonsterWeakness extends Monster {
	
	public MonsterWeakness(double x, double y)
	{
		 // x,y,speed,hp,damage,imgPath
		super(x , y);
		setSpeed(1.5);
		setHp(100);
		setDamage(20);
		setImgPath("/images/MonsterWeakness.jpg");
	}
	
	public int monsterDMG() {
    	return this.damage;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }
    
    public boolean checkCollision(Bullet bullet,Player player) 
    {
    	if(getHit( bullet ) && !bullet.isHit()) // check if bullet get close(attack) Monster
    	{
    		bullet.setHit(true);
    		setHp( getHp() - ( bullet.weaponDamage + player.getDamage() ) ); // setHp
    		System.out.println( "Monster got hit damage = " + bullet.weaponDamage + " Hp: " + getHp() );

    	}
        return getHp() <= 0;
    }
    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 40 , 40);
    }
}
