package de.kimrudolph.tutorials.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.kimrudolph.tutorials.exceptions.CatNotFoundException;

/**
 * Container holding {@link Cat}s.
 */
@XmlRootElement(name = "basket")
public class Basket {

    private List<Cat> cats = new ArrayList<Cat>();

    @XmlElement(name = "cat", type = Cat.class)
    public List<Cat> getCats() {

        return cats;
    }

    public void add(final Cat cat) {

        this.cats.add(cat);
    }

    public void remove(final String name) {

        this.cats.remove(get(name));
    }

    public Cat get(final String name) {

        for (final Cat cat : cats) {
            if (cat.getName().equals(name)) {
                return cat;
            }
        }
        throw new CatNotFoundException(name);
    }

    public void setCats(final List<Cat> cats) {

        this.cats = cats;
    }
}