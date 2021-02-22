package com.example.tabletop.springboot.controllers;

import com.example.tabletop.springboot.domain.GSystem;
import com.example.tabletop.springboot.domain.Tabletop;
import com.example.tabletop.springboot.domain.User;
import com.example.tabletop.springboot.repo.TableRepo;
import com.example.tabletop.springboot.service.TabletopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("table")
public class TableController {
    private final TabletopService tabletopService;
    private final TableRepo tableRepo;


    @Autowired
    public TableController(TabletopService tabletopService,TableRepo tableRepo) {
        this.tabletopService = tabletopService;
        this.tableRepo = tableRepo;
    }

    @GetMapping
    public List<Tabletop> list() {
        return this.tabletopService.getAllTabletop();
    }
    @GetMapping("{id}")
    public Tabletop getOne(@PathVariable("id") Tabletop table) {
        return table;
    }

    @PostMapping
    public Tabletop create(@RequestBody Tabletop table) {
        return this.tableRepo.save(table);
    }

    @PutMapping("{id}")
    public Tabletop update(@PathVariable("id") Tabletop tableFromDB,
                           @RequestBody Tabletop table) {
        BeanUtils.copyProperties(table, tableFromDB, "id");
        return this.tabletopService.update(tableFromDB, table);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Tabletop table) {
        this.tabletopService.deleteTabletop(table);
    }

    @GetMapping("/getAllSystems")
    public List<String> getAllSystems() {
        ArrayList<String> titles = new ArrayList<>();
        for (GSystem system: GSystem.values())
            titles.add(system.title);

        return titles;
    }

    @PutMapping("{id}/sub")
    public HashMap<String, Set<User>> subscribe(@PathVariable("id") Tabletop table,
                                                @RequestBody List<User> users) {
        this.tabletopService.subscribeUsers(users, table);
        return tabletopService.getSubscribersAndNotsub(table);
    }

    @GetMapping("{id}/sub")
    public Set<User> getSubscribers(@PathVariable("id") Tabletop table) {
        var subscribers = this.tabletopService.getSubscribers(table);
        return new HashSet<>(subscribers);
    }

    @GetMapping("{id}/unsub")
    public Set<User> getUnSubscribers(@PathVariable("id") Tabletop table) {
        var subscribers = this.tabletopService.findAllUnSubscribers(table);
        return new HashSet<>(subscribers);
    }

    @PutMapping("{id}/unsub")
    public HashMap<String, Set<User>> unSubscribe(@PathVariable("id") Tabletop table,
                       @RequestBody List<User> users) {
        this.tabletopService.unSubscribeUsers(users, table);
        return tabletopService.getSubscribersAndNotsub(table);
    }
}
