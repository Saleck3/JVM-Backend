package com.jvm.lecti.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.exceptions.InvalidUserIdForPlayerException;
import com.jvm.lecti.domain.exceptions.UserNotFoundException;
import com.jvm.lecti.domain.service.PlayerService;

public class PlayerServiceTest {

   @Mock
   private PlayerDAO playerDAO;

   @Mock
   private UserDAO userDAO;

   @InjectMocks
   private PlayerService playerService;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testGetPlayersByUserId() {
      long userId = 1L;
      List<Player> players = new ArrayList<>();
      players.add(new Player("Player1", LocalDate.now(), new User(), "Alias1"));
      when(playerDAO.findByUserId(userId)).thenReturn(players);

      List<Player> result = playerService.getPlayersByUserId(userId);

      assertEquals(players, result);
      verify(playerDAO).findByUserId(userId);
   }

   @Test
   public void testGetUserPlayers() {
      long userId = 1L;
      List<Player> players = new ArrayList<>();
      players.add(new Player("Player1", LocalDate.now(), new User(), "Alias1"));
      when(playerDAO.findByUserId(userId)).thenReturn(players);

      List<Player> result = playerService.getUserPlayers(userId);

      assertEquals(players, result);
      verify(playerDAO).findByUserId(userId);
   }

   @Test
   public void testAddPlayer() {
      String playerName = "Player1";
      LocalDate birthDate = LocalDate.now();
      User user = new User();

      playerService.addPlayer(playerName, birthDate, user);

      ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
      verify(playerDAO).save(playerCaptor.capture());
      Player savedPlayer = playerCaptor.getValue();

      assertEquals(playerName, savedPlayer.getPlayerName());
      assertEquals(birthDate, savedPlayer.getBirthDate());
      assertEquals(playerName, savedPlayer.getAlias());
      assertEquals(user, savedPlayer.getUser());
   }

   @Test
   public void testCheckPermissionsUserNotFound() {
      String userEmail = "test@example.com";
      long playerId = 1L;
      when(userDAO.findByEmail(userEmail)).thenReturn(Optional.empty());

      assertThrows(UserNotFoundException.class, () -> playerService.checkPermissions(userEmail, playerId));
   }

   @Test
   public void testCheckPermissionsInvalidUserIdForPlayer() {
      String userEmail = "test@example.com";
      long playerId = 1L;
      User user = new User();
      user.setId(1L);
      when(userDAO.findByEmail(userEmail)).thenReturn(Optional.of(user));

      List<Player> players = new ArrayList<>();
      when(playerDAO.findByUserId(user.getId())).thenReturn(players);

      assertThrows(InvalidUserIdForPlayerException.class, () -> playerService.checkPermissions(userEmail, playerId));
   }

   @Test
   public void testCheckPermissionsSuccess() {
      String userEmail = "test@example.com";
      long playerId = 1L;
      User user = new User();
      user.setId(1L);
      when(userDAO.findByEmail(userEmail)).thenReturn(Optional.of(user));

      List<Player> players = new ArrayList<>();
      Player player = new Player();
      player.setId(playerId);
      players.add(player);
      when(playerDAO.findByUserId(user.getId())).thenReturn(players);

      assertDoesNotThrow(() -> playerService.checkPermissions(userEmail, playerId));
   }

}
