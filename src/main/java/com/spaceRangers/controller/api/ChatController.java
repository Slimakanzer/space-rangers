package com.spaceRangers.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.config.security.UserDetailsServiceImpl;
import com.spaceRangers.entities.*;
import com.spaceRangers.service.ChatService;
import com.spaceRangers.service.GameChatService;
import com.spaceRangers.service.ProfileUserService;
import com.spaceRangers.service.RegistrationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping(value = "/api/chats")
public class ChatController {
    Logger log = LogManager.getLogger(ChatController.class);

    public void logJsonObject(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info(object.getClass().getName()+" "+ objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Qualifier("chatService")
    @Autowired
    private ChatService chatService;

    @Qualifier("gameChatService")
    @Autowired
    private GameChatService gameChatService;

    @Qualifier("registrationService")
    @Autowired
    private RegistrationService registrationService;

    @Qualifier("profileUserService")
    @Autowired
    private ProfileUserService profileUserService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getListChatsUser(
            @RequestParam("id_user") int idUser,
            @AuthenticationPrincipal User user
            ){
        UserAccountEntity userAccountEntity = registrationService.getUserAccount(user.getUsername());

        if(userAccountEntity.getId()==idUser){

            List<ChatEntity> list = chatService.getChatsUser(idUser);

            return ResponseEntity.ok(list);
        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createChat(
            @RequestBody String jsonChat,
            @AuthenticationPrincipal User user
    ){
            try {

                UserAccountEntity usersEntity = registrationService.getUserAccount(user.getUsername());
                UsersEntity users = usersEntity.getUser();

                ChatEntity chat = new ObjectMapper().readValue(jsonChat, ChatEntity.class);
                chat.getUsers().add(users);
                users.getChats().add(chat);

                chatService.createChat(chat);

                profileUserService.updateUser(users);

                return ResponseEntity.ok().build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }

    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getChat(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
        try {
            try {
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){
                    return ResponseEntity.ok(chat);
                }

                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            } catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
    ResponseEntity getMessagers(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
        try {
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){
                    return ResponseEntity.ok(chat.getMessages());
                }

                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/messages",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createMessage(
        @PathVariable int id,
        @RequestBody String message,
        @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){
                    MessagesEntity messagesEntity = new ObjectMapper().readValue(message, MessagesEntity.class);

                    chat.getMessages().add(messagesEntity);
                    messagesEntity.setChat(chat);
                    chatService.createMessages(messagesEntity);
                    chatService.updateChat(chat);

                    return ResponseEntity.ok(messagesEntity);
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings",
            method = RequestMethod.GET
    )
    ResponseEntity getChatVotings(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

                    return ResponseEntity.ok(chat.getVotings());

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity createChatVotings(
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            @RequestBody String voting
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

                    VotingEntity votingEntity = new ObjectMapper().readValue(voting, VotingEntity.class);

                    votingEntity.setChat(chat);
                    chat.getVotings().add(votingEntity);
                    gameChatService.createVoting(votingEntity);
                    chatService.updateChat(chat);

                    return ResponseEntity.ok(votingEntity);

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}",
            method = RequestMethod.GET
    )
    ResponseEntity getVoting(
            @PathVariable int id,
            @PathVariable int idVoting,
            @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);
                    if (votingEntity.getChat().getId() == id){
                        return ResponseEntity.ok(votingEntity);
                    }

                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results",
            method = RequestMethod.GET
    )
    ResponseEntity getVotingResults(
            @PathVariable int id,
            @PathVariable int idVoting,
            @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){
                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);

                    if(votingEntity.getChat().getId() == id){

                        return ResponseEntity.ok(votingEntity.getResults());

                    }

                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity createVotingResults(
            @PathVariable int id,
            @PathVariable int idVoting,
            @RequestBody String result,
            @AuthenticationPrincipal User user
    ) {
        try {
            try {
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if (chatService.userInChat(users, chat)) {
                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);

                    if (votingEntity.getChat().getId() == id) {

                        ResultsEntity resultsEntity = new ObjectMapper().readValue(result, ResultsEntity.class);

                        resultsEntity.setVoting(votingEntity);
                        votingEntity.getResults().add(resultsEntity);
                        gameChatService.updateVoting(votingEntity);
                        gameChatService.createResults(resultsEntity);

                        return ResponseEntity.ok(
                                resultsEntity
                        );

                    }

                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            } catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}",
            method = RequestMethod.GET
    )
    ResponseEntity getVotingResult(
            @PathVariable int id,
            @PathVariable int idVoting,
            @PathVariable int idResult,
            @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);

                    if(votingEntity.getChat().getId() == id){
                        Iterator iterator = votingEntity.getResults().iterator();
                        while (iterator.hasNext()){
                            ResultsEntity resultsEntity = (ResultsEntity) iterator.next();
                            if (resultsEntity.getId() == idResult){
                                return ResponseEntity.ok(resultsEntity);
                            }
                        }

                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity updateVotingResult(
            @PathVariable int id,
            @PathVariable int idVoting,
            @PathVariable int idResult,
            @RequestBody String result,
            @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);

                    if(votingEntity.getChat().getId() == id){
                        Iterator iterator = votingEntity.getResults().iterator();
                        while (iterator.hasNext()){
                            ResultsEntity resultsEntity = (ResultsEntity) iterator.next();
                            if (resultsEntity.getId() == idResult){

                                ResultsEntity resultsEntity1 = new ObjectMapper().readValue(result, ResultsEntity.class);

                                if(resultsEntity1.getId() == idResult){
                                    resultsEntity1.setVoting(votingEntity);
                                    gameChatService.updateResults(resultsEntity1);
                                    return ResponseEntity.ok(resultsEntity1);
                                }
                            }
                        }

                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}/votes",
            method = RequestMethod.GET
    )
    ResponseEntity getVotingResultVotes(
            @PathVariable int id,
            @PathVariable int idVoting,
            @PathVariable int idResult,
            @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);

                    if(votingEntity.getChat().getId() == id){
                        Iterator iterator = votingEntity.getResults().iterator();
                        while (iterator.hasNext()){
                            ResultsEntity resultsEntity = (ResultsEntity) iterator.next();
                            if (resultsEntity.getId() == idResult){
                                return ResponseEntity.ok(resultsEntity.getVotes());
                            }
                        }

                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}/votes",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity createVotingResultVotes(
            @PathVariable int id,
            @PathVariable int idVoting,
            @PathVariable int idResult,
            @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);

                    if(votingEntity.getChat().getId() == id){
                        Iterator iterator = votingEntity.getResults().iterator();
                        while (iterator.hasNext()){
                            ResultsEntity resultsEntity = (ResultsEntity) iterator.next();
                            if (resultsEntity.getId() == idResult){

                                VoteEntity voteEntity = new VoteEntity();
                                voteEntity.setIdUser(users.getId());
                                voteEntity.setUser(users);
                                users.getVotes().add(voteEntity);
                                voteEntity.setIdResult(resultsEntity.getId());
                                voteEntity.setResults(resultsEntity);
                                resultsEntity.getVotes().add(voteEntity);

                                gameChatService.createVote(voteEntity);

                                return ResponseEntity.ok(voteEntity);

                            }
                        }

                    }
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}