package AnimationEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Entity.Player;
import Item.BuffItem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class UsePotionEffect {
	public static boolean isHealing = false;
	public static boolean isbuffBerserkAvailable = false;
	public static boolean isbuffSpecialAvailable = false;
	public static boolean isbuffAvailable = false;
	
	 private static ArrayList<Particle> Particles = new ArrayList<>();

	 
	 private static int healingParticleCooldown = 0;
	 private static int BerserkParticleCooldown = 0;
	 private static int SpecialParticleCooldown = 0;
	 
	
	
	static public void renderBerserkEffect(GraphicsContext gc,double x, double y) {
		if (BerserkParticleCooldown <= 0) {
	        // Player dimensions
	        double playerWidth = 40;
	        double playerHeight = 40;

	        // Generate a random offset within the player's area
	        double offsetX = (Math.random() * playerWidth) - (playerWidth / 2)+20;  // Between -20 and 20
	        double offsetY = (Math.random() * playerHeight) - (playerHeight / 2)+20; // Between -20 and 20

	        // Create a new particle at the randomized position around the player
	        Particles.add(new BerserkParticle(x + offsetX, y + offsetY));

	        // Reset cooldown
	        BerserkParticleCooldown = 50; // Generates a particle every 5 frames (adjustable)
	    } else {
	    	BerserkParticleCooldown--; // Decrease cooldown each frame
	    }
	}
	
	static public void renderSpecialEffect(GraphicsContext gc,double x, double y) {
		if (SpecialParticleCooldown <= 0) {
	        // Player dimensions
	        double playerWidth = 40;
	        double playerHeight = 40;

	        // Generate a random offset within the player's area
	        double offsetX = (Math.random() * playerWidth) - (playerWidth / 2)+20;  // Between -20 and 20
	        double offsetY = (Math.random() * playerHeight) - (playerHeight / 2)+20; // Between -20 and 20

	        // Create a new particle at the randomized position around the player
	        Particles.add(new SpecialParticle(x + offsetX, y + offsetY));

	        // Reset cooldown
	        SpecialParticleCooldown = 50; // Generates a particle every 5 frames (adjustable)
	    } else {
	    	SpecialParticleCooldown--; // Decrease cooldown each frame
	    }
	}
	

	public static void renderHealingEffect(GraphicsContext gc, double x, double y) {
	    if (healingParticleCooldown <= 0) {
	        // Player dimensions
	        double playerWidth = 40;
	        double playerHeight = 40;

	        // Generate a random offset within the player's area
	        double offsetX = (Math.random() * playerWidth) - (playerWidth / 2)+20;  // Between -20 and 20
	        double offsetY = (Math.random() * playerHeight) - (playerHeight / 2)+20; // Between -20 and 20

	        // Create a new particle at the randomized position around the player
	        Particles.add(new HealingParticle(x + offsetX, y + offsetY));

	        // Reset cooldown
	        healingParticleCooldown = 30; // Generates a particle every 5 frames (adjustable)
	    } else {
	        healingParticleCooldown--; // Decrease cooldown each frame
	    }
	}
	public static void updateParticles(GraphicsContext gc) {
	    Iterator<Particle> iterator = Particles.iterator();
	    while (iterator.hasNext()) {
	        Particle particle = iterator.next();
	        particle.update();
	        
	        if (particle.lifespan > 0) {
	            particle.render(gc);
	        } else {
	            iterator.remove(); // Remove dead particles
	        }
	    }
	}
	
	//// get set Healing

	public static void setHealing(boolean isHealing) {
		UsePotionEffect.isHealing = isHealing;
	}

	
	///get set isbuff

	public static void setIsbuffAvailable(boolean isbuffAvailable) {
		UsePotionEffect.isbuffAvailable = isbuffAvailable;
	}

	//setter is which buff
	

	public static void setIsbuffBerserkAvailable(boolean isbuffBerserkAvailable) {
		UsePotionEffect.isbuffBerserkAvailable = isbuffBerserkAvailable;
	}


	public static void setIsbuffSpecialAvailable(boolean isbuffSpecialAvailable) {
		UsePotionEffect.isbuffSpecialAvailable = isbuffSpecialAvailable;
	}
	
	
   

	
}
