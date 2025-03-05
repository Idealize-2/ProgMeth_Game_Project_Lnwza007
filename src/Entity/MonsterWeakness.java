package Entity;

import Weapon.Bullet;
import Weapon.Croissant;
import Weapon.Pizza;
import Weapon.Sushi;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MonsterWeakness extends Monster {
	
	public MonsterWeakness(double x, double y)
	{
		 // x,y,speed,hp,damage,imgPath
		super(x , y);
		setReward(20);
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
    		int bulletDamage;
    		if(bullet instanceof Sushi)bulletDamage = Sushi.getWeaponDamage();
    		else if(bullet instanceof Croissant)bulletDamage = Croissant.getWeaponDamage()*2;
    		else bulletDamage = Pizza.getWeaponDamage();
    		bullet.setHit(true);
    		setHp( getHp() - ( bulletDamage + player.getDamage() ) ); // setHp
    		if(bullet instanceof Croissant) System.out.println("Critical hit!!");
    		System.out.println( "Monster got hit damage = " + bulletDamage + " Hp: " + getHp() );

    	}
        return getHp() <= 0;
    }
    public void render(GraphicsContext gc , double x , double y) {
        gc.drawImage(getEntityImage() , x , y , 40 , 40);
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
