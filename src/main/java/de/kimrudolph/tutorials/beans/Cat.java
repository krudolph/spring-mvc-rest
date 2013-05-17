package de.kimrudolph.tutorials.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cat")
public class Cat {

    private String name;
    private Integer cuteness;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCuteness() {
        return cuteness;
    }

    public void setCuteness(Integer cuteness) {
        this.cuteness = cuteness;
    }

}
