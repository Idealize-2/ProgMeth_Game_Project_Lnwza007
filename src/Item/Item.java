package Item;

import javafx.scene.image.Image;

public class Item {
        private String name;
        private String description;
        private String iconStr;
        private int price;
        private Image itemIcon;
        

        public Item(String name, int price, String des,String itemIconStr) {
        	setName(name);
        	setPrice(price);
        	setDescription(des);
        	setIconStr(itemIconStr);
        }
        
        
		

		private void setIconStr(String iconStr) {
			this.iconStr = iconStr;
			try {
				 setItemIcon( new Image( getIconStr() ) );
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public String getIconStr() {
			return iconStr;
		}
		public Image getItemIcon() {
			return itemIcon;
		}

		private void setItemIcon(Image itemIcon) {
			this.itemIcon = itemIcon;
		}

		public String getName() {
			return name;
		}

		private void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		private void setDescription(String description) {
			this.description = description;
		}

		public int getPrice() {
			return price;
		}

		private void setPrice(int price) {
			this.price = price;
		}
        
}