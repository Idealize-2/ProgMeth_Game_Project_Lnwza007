package Item;

import javafx.scene.image.Image;

public class Item {
        private String name;
        private String iconStr;
        private int price;
        private Image itemIcon;
        private int itemCount = 0;
        

        public Item(String name, int price, String itemIconStr) {
        	setName(name);
        	setPrice(price);
        	setIconStr(itemIconStr);
        }
        
        
		

        private void setIconStr(String iconStr) {
            this.iconStr = iconStr;
            try {
                // ใช้ getClass().getClassLoader().getResource() เพื่อโหลดไฟล์จาก classpath
                setItemIcon(new Image(getClass().getClassLoader().getResource(iconStr).toString()));
            } catch (Exception e) {
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

		public int getPrice() {
			return price;
		}

		private void setPrice(int price) {
			this.price = price;
		}

		public int getItemCount() {
			return itemCount;
		}

		public void setItemCount(int itemCount) {
			this.itemCount = Math.max(itemCount, 0);
		}
		




        
}