/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchwithtags;

/**
 *
 * @author Raiyan
 */
public class ImageManager {

    private BST<LinkedList<Image>> tagsBst;
    private LinkedList<Image> ImageList;

    public BST<LinkedList<Image>> getTagsBst() {
        return tagsBst;
    }

    public void setTagsBst(BST<LinkedList<Image>> tagsBst) {
        this.tagsBst = tagsBst;
    }
    

    public LinkedList<Image> getImageList() {
        return ImageList;
    }

    public void setImageList(LinkedList<Image> ImageList) {
        this.ImageList = ImageList;
    }
    
    // Constructor
    public ImageManager() {
        tagsBst = new BST<LinkedList<Image>>();
        ImageList = new LinkedList<Image>();
    }

    // Add a image
    public void addImage(Image image) {
        LinkedList<String> tags = image.getTags();
        tags.findFirst();
        ImageList.findFirst();
        ImageList.insert(image);
        /// need to retreive tags and insert the path in the linkedlist for tag bst
        if (!tags.empty()) {
            while (true) {
                String currentTag = tags.retrieve();

                if (tagsBst.findKey(currentTag)) {
                    LinkedList<Image> tagNode = tagsBst.retrieve();
                    tagNode.insert(image);
                } else {
                    LinkedList<Image> tagNode = new LinkedList<Image>();
                    tagNode.insert(image);
                    tagsBst.insert(currentTag, tagNode);
                }
                if (tags.last()) {
                    break;
                } else {
                    tags.findNext();
                }
            }
        }
    }

    public void printImagesUnderTag(String k) {
        if (tagsBst.findKey(k)) {
            LinkedList<Image> myLL = tagsBst.retrieve();
            myLL.findFirst();
            while (true) {
                System.out.println(myLL.retrieve().path);
                if (myLL.last()) {
                    break;
                } else {
                    myLL.findNext();
                }
            }
        }
    }
    
    // Delete a image
    public void deleteImage(String path) {

    }
    
    // Return the inverted index of all managed images
    public BST<LinkedList<Image>> getImages() {
        return tagsBst;
    }
}
