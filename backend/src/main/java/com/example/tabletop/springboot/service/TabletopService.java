package com.example.tabletop.springboot.service;

import com.example.tabletop.springboot.domain.Player;
import com.example.tabletop.springboot.domain.PlayerKey;
import com.example.tabletop.springboot.domain.Tabletop;
import com.example.tabletop.springboot.domain.User;
import com.example.tabletop.springboot.repo.PlayerRepo;
import com.example.tabletop.springboot.repo.TableRepo;
import com.example.tabletop.springboot.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TabletopService {
    private final TableRepo tableRepo;
    private final PlayerRepo playerRepo;
    private final UserRepo userRepo;

    public TabletopService(TableRepo tableRepo, PlayerRepo playerRepo, UserRepo userRepo) {
        this.tableRepo = tableRepo;
        this.playerRepo = playerRepo;
        this.userRepo = userRepo;
    }

    public List<Tabletop> getAllTabletop() {
        return this.tableRepo.findAll();
    }

    public Tabletop getOne(Tabletop table) {
        return table;
    }

    public Tabletop create(Tabletop table) {
        return this.tableRepo.save(table);
    }

    public Tabletop update(Tabletop tableFromDB, Tabletop table) {
        BeanUtils.copyProperties(table, tableFromDB, "id");
        return this.tableRepo.save(table);
    }

    public void delete(Tabletop table) {
        this.tableRepo.delete(table);
    }

    public List<User> getSubscribers(Tabletop table) {
        Set<Player> players = this.playerRepo.findAllByTabletop(table);
        ArrayList<User> usersOnTable = new ArrayList<>();
        players.forEach(player -> {
            usersOnTable.add(player.getUser());
        });
        return usersOnTable;
    }

    private List<Long> allSubscribersId(Tabletop table) {
        Set<Player> players = this.playerRepo.findAllByTabletop(table);
        ArrayList<Long> usersId = new ArrayList<>();
        players.forEach(player -> {
            usersId.add(player.getUser().getId());
        });
        return usersId;
    }
    
    private Set<User> getAllUsers() {
        return new HashSet<User>(this.userRepo.findAll());
    }

    public List<Tabletop> getTabletops(User user) {
        Set<Player> players = this.playerRepo.findAllByUser(user);
        ArrayList<Tabletop> tablesOfUser = new ArrayList<>();
        players.forEach(player -> {
            tablesOfUser.add(player.getTabletop());
        });
        return tablesOfUser;
    }


    public List<Player> subscribeUsers(List<User> users, Tabletop table) {
        List<Long> subscribers = allSubscribersId(table);
        ArrayList<Player> newPlayers = new ArrayList<>();
        users.forEach(user -> {
            if ( !subscribers.contains(user.getId())) {
                Player np = new Player();
                np.setTabletop(table);
                np.setUser(user);

                PlayerKey pk = new PlayerKey();
                pk.setUserId(user.getId());
                pk.setTableId(table.getId());

                np.setId(pk);

                newPlayers.add(np);
            }
        });
        return playerRepo.saveAll(newPlayers);
    }
    
    public Set<User> findAllUnSubscribers(Tabletop tabletop) {
        var users = new HashSet<User>(getSubscribers(tabletop));
        users.add(tabletop.getOwner());

        var unSubscribers = getAllUsers();
        unSubscribers.removeAll(users);
        return unSubscribers;
    }

    public HashMap<String, Set<User>> getSubscribersAndNotsub(Tabletop table) {
        Set<User> subs = new HashSet<>(getSubscribers(table));
        Set<User> notsubs = findAllUnSubscribers(table);
        return new HashMap<String, Set<User>>() {{
            put("subscribers", subs);
            put("notsubscribers", notsubs);
        }};
    }

    public void unSubscribeUsers(List<User> users, Tabletop table) {
        List<Long> subscribers = allSubscribersId(table);

        users.forEach(user -> {
            if ( subscribers.contains(user.getId())) {
                var playerKey = new PlayerKey();
                playerKey.setTableId(table.getId());
                playerKey.setUserId(user.getId());

                playerRepo.deleteById(playerKey);
            }
        });
    }

    public void deleteTabletop(Tabletop table) {
        var toDel = this.playerRepo.findAllByTabletop(table);
        toDel.forEach(player -> this.playerRepo.deleteById(player.getId()));
        this.tableRepo.delete(table);
    }
}
