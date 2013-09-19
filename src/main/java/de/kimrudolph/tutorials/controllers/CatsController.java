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

@Controller
@RequestMapping(
    value = "/cats",
    headers = { "Accept=application/xml,application/json" })
public class CatsController {

    private final Basket basket;

    /**
     * Prefill the {@link Basket} with 2 cute {@link Cat}s.
     */
    public CatsController() {

        basket = new Basket();

        final Cat kitty = new Cat();
        kitty.setName("kitty");
        kitty.setCuteness(11);
        basket.add(kitty);

        final Cat cuddles = new Cat();
        cuddles.setName("cuddles");
        cuddles.setCuteness(5);
        basket.add(cuddles);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Basket getAll() {

        return basket;
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @ResponseBody
    public Cat get(@PathVariable final String name) {

        return basket.get(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Cat create(@RequestBody final Cat cat) {

        basket.add(cat);
        return cat;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody final Cat cat) {

        basket.get(cat.getName()).setCuteness(cat.getCuteness());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable final String name) {

        basket.remove(name);
    }
}