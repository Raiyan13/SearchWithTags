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
public class Album {

    private String name;
    private String condition;
    private ImageManager manager;

    // Constructor
    public Album(String name, String condition, ImageManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }

    // Return the name of the album
    public String getName() {
        return this.name;
    }

    // Return the condition associated with the album
    public String getCondition() {
        return this.condition;
    }

    // Return the manager
    public ImageManager getManager() {
        return this.manager;
    }

    // Return all images that satisfy the album condition
    public LinkedList<Image> getImage() {
        //LinkedList for selected images as conditions
        LinkedList<Image> selectedImages = new LinkedList<Image>();

        this.manager.getTagsBst().setCountComparison(0);
        if (this.condition.equals("")) {
            return this.manager.getImageList();
        } else {
            // spliting the condition
            String conditions[] = this.condition.split(" AND ");

            for (int i = 0; i < conditions.length; i++) {
                conditions[i] = conditions[i].trim();
            }

            //getting the images in selectedImages
            for (int i = 0; i < conditions.length; i++) {
                //if the condition is present in the BST
                if (this.manager.getTagsBst().findKey(conditions[i])) {

                    //getting node (LinkedList) of BST
                    LinkedList<Image> currentNodeImages = this.manager.getTagsBst().retrieve();

                    //when selectedImage is empty no need to compare
                    if (i == 0) {
                        currentNodeImages.findFirst();
                        while (true) {

                            selectedImages.insert(currentNodeImages.retrieve());

                            if (currentNodeImages.last()) {
                                break;
                            } else {
                                currentNodeImages.findNext();
                            }
                        }
                    } else { //when there is data in selected image then need to compare current and recent linkedlist
                        LinkedList<Image> temporarySelectedImages = new LinkedList<Image>();
                        currentNodeImages.findFirst();
                        while (true) {

                            temporarySelectedImages.insert(currentNodeImages.retrieve());

                            if (currentNodeImages.last()) {
                                break;
                            } else {
                                currentNodeImages.findNext();
                            }
                        }
                        //inserting common images in selectedImages by comparing 2 linkedlist
                        selectedImages = getExpectedImages(selectedImages, temporarySelectedImages);
                    }
                }
            }
        }
        return selectedImages; //returning selectedImages as condition
    }
    // Return the number of tag comparisons used to find all images of the album

    public int getNbComps() {
        return this.getManager().getTagsBst().getCountComparison();
    }

    //method for comparing two LinkedList
    private LinkedList<Image> getExpectedImages(LinkedList<Image> selectedImages, LinkedList<Image> temporarySelectedImages) {

        LinkedList<Image> returnImages = new LinkedList<Image>();

        selectedImages.findFirst();

        while (true) {

            temporarySelectedImages.findFirst();

            while (true) {
                
                if (selectedImages.retrieve() == temporarySelectedImages.retrieve()) {
                    returnImages.insert(selectedImages.retrieve());
                }

                if (temporarySelectedImages.last()) {
                    break;
                } else {
                    temporarySelectedImages.findNext();
                }
            }

            if (selectedImages.last()) {
                break;
            } else {
                selectedImages.findNext();
            }
        }
        return returnImages;
    }
}
