package com.spaceRangers.controller.api;

import com.spaceRangers.config.security.UserDetailsServiceImpl;
import com.spaceRangers.entities.*;
import com.spaceRangers.service.ChatService;
import com.spaceRangers.service.GameChatService;
import com.spaceRangers.service.RegistrationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@RequestMapping(value = "/api/chats")
public class ChatController {
    Logger log = LogManager.getLogger(ChatController.class);

    @Qualifier("chatService")
    @Autowired
    private ChatService chatService;

    @Qualifier("gameChatService")
    @Autowired
    private GameChatService gameChatService;

    @Qualifier("registrationService")
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity createChat(@RequestBody Map<String, Object> map){
        chatService.createChat(map);
        return ResponseEntity.ok().build();
    }




    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getListChatsByUserId(
            @RequestParam("id_user") int idUser,
            @AuthenticationPrincipal User user
            ){
        UserAccountEntity userAccountEntity = registrationService.getUserAccount(user.getUsername());

        if(userAccountEntity.getId()==idUser){
            return ResponseEntity.ok(chatService.getChatsUser(idUser));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getChatById(@PathVariable("id") int idChat){
        return ResponseEntity.ok(chatService.getChat(idChat));
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    ResponseEntity getUsersOfChat(@PathVariable("id") int idChat){
        return ResponseEntity.ok(chatService.getUsersInChat(idChat));
    }


    @RequestMapping(value = "/user_chat", method = RequestMethod.POST)
    ResponseEntity createChatUser(@RequestBody ChatUserEntity chatUser){
        chatService.createChatUser(chatUser);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/user_chat", method = RequestMethod.DELETE)
    ResponseEntity dropChatUser(@RequestBody ChatUserEntity chatUser){
        chatService.dropChatUser(chatUser);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    ResponseEntity getMessagesChatById(@RequestParam("id_chat") int idChat){
        return ResponseEntity.ok(
          chatService.getMessagesOfChat(idChat)
        );
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    ResponseEntity getMessageById(@RequestParam("id_message") int idMessage){
        chatService.getMessage(idMessage);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    ResponseEntity createMessage(@RequestBody MessagesEntity messages){
        chatService.createMessages(messages);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/voting", method = RequestMethod.POST)
    ResponseEntity createVoting(@RequestBody VotingEntity voting){
        gameChatService.createVoting(voting);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/voting", method = RequestMethod.GET)
    ResponseEntity getListVotingByIdChat(@RequestParam("id_chat")int idChat){
        return ResponseEntity
                .ok(
                        gameChatService.getListVotingByIdChat(idChat)
                );
    }

    @RequestMapping(value = "/voting/{id}", method = RequestMethod.GET)
    ResponseEntity getVotingById(@PathVariable("id") int idVoting){
        return ResponseEntity
                .ok(gameChatService.getVoting(idVoting)
                );
    }

    @RequestMapping(value = "/voting/{id}/results", method = RequestMethod.GET)
    ResponseEntity getResultsVoting(@PathVariable("id") int idVoting){
        return ResponseEntity
                .ok(
                  gameChatService.getListResultsByIdVoting(idVoting)
                );
    }

    @RequestMapping(value = "/voting/{id}/results", method = RequestMethod.POST)
    ResponseEntity createResultsVoting(@PathVariable("id") int idVoting, @RequestBody ResultsEntity result){
        if (result.getVoting().getId() == idVoting) {
            gameChatService.createResults(result);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/voting/{id}/results", method = RequestMethod.DELETE)
    ResponseEntity dropResultsVoting(@PathVariable("id") int idVoting, @RequestBody ResultsEntity result){
        if (result.getVoting().getId() == idVoting) {
            gameChatService.dropResults(result);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/voting/{id}/vote", method = RequestMethod.GET)
    ResponseEntity getListVoteByIdResult(@PathVariable("id") int idVoting, @RequestParam("id_result") int idResult){
        return ResponseEntity.ok(
                gameChatService.getListVoteByIdResult(idResult)
        );
    }

    @RequestMapping(value = "/voting/{id}/vote", method = RequestMethod.POST)
    ResponseEntity dropResultsVoting(@PathVariable("id") int idVoting, @RequestBody VoteEntity vote){
        gameChatService.createVote(vote);
        return ResponseEntity.ok().build();
    }
}