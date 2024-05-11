package com.jvm.lecti.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jvm.lecti.entity.Player;
import com.jvm.lecti.entity.SecurityUser;
import com.jvm.lecti.repository.PlayerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   private final PlayerRepository playerRepository;

   public CustomUserDetailsService(PlayerRepository playerRepository) {
      this.playerRepository = playerRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Optional<Player> playerOptional = playerRepository.findByUserEmail(email);
      if (playerOptional.isPresent()) {
         return new SecurityUser(playerOptional.get().getUser(), playerOptional.get());
      }
      return null;
   }

}
