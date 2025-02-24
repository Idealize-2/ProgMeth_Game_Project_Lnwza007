package Entity;
import Component.Bullet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Monster extends BaseEntity{

    public Monster(double x, double y) {
        super(x,y,2,20,10,"images/yowtf.png"); // x,y,speed,hp,damage,imgPath
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
    
    public boolean checkCollision(Bullet bullet) 
    {
    	if(getHit( bullet )) // check if bullet get close(attack) Monster
    	{
    		setHp( getHp() - bullet.weaponDamage ); // setHp
    		System.out.println( "Monster got hit damage = " + bullet.weaponDamage + " Hp: " + getHp() );

    	}
        return getHp() == 0;
    }
    

    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 40 , 40);
    }
}
