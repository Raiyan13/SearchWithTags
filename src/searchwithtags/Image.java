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
public class Image {
    String path;
    LinkedList<String> tags;
    
    // Constructor
    public Image(String path, LinkedList<String> tags) {
        this.path = path;
        this.tags = tags;
    }

    // Return the path (full file name) of the image. A image is uniquely identified by its path.
    public String getPath() {
        return this.path;
    }

    // Return all tags associated with the image
    public LinkedList<String> getTags() {
        return this.tags;
    }
}
