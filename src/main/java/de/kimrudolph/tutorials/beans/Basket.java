package de.kimrudolph.tutorials.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Container holding {@link Cat}s.
 */
@XmlRootElement(name = "basket")
public class Basket {

    @JsonProperty("cats")
    private List<Cat> cats = new ArrayList<Cat>();

    public List<Cat> getCats() {
        return cats;
    }

    public void add(Cat cat) {
        this.cats.add(cat);
    }

    public void remove(String name) {
        this.cats.remove(get(name));
    }

    public Cat get(String name) {
        for (Cat cat : cats) {
            if (cat.getName().equals(name)) {
                return cat;
            }
        }
        return null;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
}