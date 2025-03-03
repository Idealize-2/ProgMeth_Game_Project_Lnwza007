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
	 private static ArrayList<Particle> healingParticles = new ArrayList<>();
	 private static int healingParticleCooldown = 0;
	public static boolean isHealing() {
		return isHealing;
	}

	public static void setHealing(boolean isHealing) {
		UsePotionEffect.isHealing = isHealing;
	}

	public static boolean isbuffAvailable = false;
	public static boolean isIsbuffAvailable() {
		return isbuffAvailable;
	}

	public static void setIsbuffAvailable(boolean isbuffAvailable) {
		UsePotionEffect.isbuffAvailable = isbuffAvailable;
	}
	
	static public void renderBerserkEffect(GraphicsContext gc,double x, double y) {
	    // Example effect: Draw a red circle at (x, y)
	    gc.setFill(Color.RED);
	    gc.fillOval(x - 10, y - 10, 20, 20); // Adjust for centering
	    
	    // Example glow effect using shadow
	    gc.setEffect(new DropShadow(20, Color.ORANGE));
	    
	    // Reset effect after rendering
	    gc.setEffect(null);
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
	        healingParticles.add(new Particle(x + offsetX, y + offsetY));

	        // Reset cooldown
	        healingParticleCooldown = 30; // Generates a particle every 5 frames (adjustable)
	    } else {
	        healingParticleCooldown--; // Decrease cooldown each frame
	    }
	}
	public static void updateParticles(GraphicsContext gc) {
	    Iterator<Particle> iterator = healingParticles.iterator();
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
   

	
}
