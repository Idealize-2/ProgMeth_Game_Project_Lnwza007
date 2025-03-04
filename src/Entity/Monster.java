package Entity;
import Weapon.Bullet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Monster extends BaseEntity{

    public Monster(double x, double y) {
    	//     x, y, speed,hp,damage,    imgPath
        super( x , y , 1 , 40 , 10 ,"images/Enemy1.png");
    }
    public int monsterDMG() {
    	return this.damage;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }
    
    public boolean getHit(Bullet bullet) {
    	
        return Math.hypot(x - bullet.x, y - bullet.y) < 25;
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
	
	public void setHp(int hp) {
		this.hp = hp;
	}
    
    

    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 60 , 60);
    }
}
