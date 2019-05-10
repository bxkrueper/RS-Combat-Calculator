package interfaceHelpers;
/*
 * this is used for combo box cells with pictures in them
 */
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class PictureListCell extends ListCell<String>  {
    
    StringToImage stringToImage;
    public PictureListCell(StringToImage stringToImage){
        this.stringToImage = stringToImage;
    }
    protected void updateItem(String item, boolean empty){
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if(item!=null){
            ImageView imageView = new ImageView((stringToImage.getImage(item)));
            imageView.setFitWidth(stringToImage.getWidth());
            imageView.setFitHeight(stringToImage.getHeight());
            setGraphic(imageView);
            setText(item);
        }
    }
}
