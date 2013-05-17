package de.kimrudolph.tutorials.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.kimrudolph.tutorials.beans.Basket;
import de.kimrudolph.tutorials.beans.Cat;
import de.kimrudolph.tutorials.exceptions.CatNotFoundException;

@Controller
@RequestMapping("/cats")
public class CatsController {

    private Basket basket;

    /**
     * Prefill the {@link Basket} with 2 cute {@link Cat}s.
     */
    public CatsController() {
        basket = new Basket();

        Cat kitty = new Cat();
        kitty.setName("kitty");
        kitty.setCuteness(11);
        basket.add(kitty);

        Cat cuddles = new Cat();
        cuddles.setName("cuddles");
        cuddles.setCuteness(5);
        basket.add(cuddles);
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=*/*")
    @ResponseBody
    public Basket getAll() {
        return basket;
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET,
            headers = "Accept=*/*")
    @ResponseBody
    public Cat get(@PathVariable String name) {

        if (null == basket.get(name)) {
            throw new CatNotFoundException();
        }

        return basket.get(name);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=*/*")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Cat create(@RequestBody Cat cat) {
        basket.add(cat);
        return cat;
    }

    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=*/*")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Cat cat) {

        if (null == basket.get(cat.getName())) {
            throw new CatNotFoundException();
        }

        basket.get(cat.getName()).setCuteness(cat.getCuteness());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name) {

        if (null == basket.get(name)) {
            throw new CatNotFoundException();
        }

        basket.remove(name);
    }
}