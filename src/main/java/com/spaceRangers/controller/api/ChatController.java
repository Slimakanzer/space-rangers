package com.spaceRangers.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.*;
import com.spaceRangers.service.ChatService;
import com.spaceRangers.service.GameChatService;
import com.spaceRangers.service.ProfileUserService;
import com.spaceRangers.service.RegistrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Date;
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

    @ApiOperation("Get user's list of available chats")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully got list chats"),
            @ApiResponse(code = 403, message = "Request denied. Resource is not available")
    })
    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity getListChatsUser(
            @ApiParam("Users id") @RequestParam(name = "id_user", defaultValue = "1") int idUser,
            @ApiIgnore @AuthenticationPrincipal User user
            ){
        UserAccountEntity userAccountEntity = registrationService.getUserAccount(user.getUsername());

        if(userAccountEntity.getId()==idUser){

            List<ChatEntity> list = chatService.getChatsUser(idUser);

            return ResponseEntity.ok(list);
        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @ApiOperation("Create chat by user")
    @ApiResponses(
            {
                   @ApiResponse(code = 200, message = "Successfully created chat"),
                   @ApiResponse(code = 400, message = "Error with create chat")
            }
    )
    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createChat(
            @ApiParam("Chat entity") @RequestBody ChatEntity chat,
            @ApiIgnore @AuthenticationPrincipal User user
    ){
            try {

                UserAccountEntity usersEntity = registrationService.getUserAccount(user.getUsername());
                UsersEntity users = usersEntity.getUser();

                chat.getUsers().add(users);
                chat.setDate(new Date(new java.util.Date().getTime()));

                chatService.createChat(chat);

                profileUserService.updateUser(users);

                return ResponseEntity.ok(chat);
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }

    }


    @ApiOperation("Get user's chat by id chat")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Successfully got chat"),
        @ApiResponse(code = 400, message = "Error by create ChatEntity"),
        @ApiResponse(code = 403, message = "Request denied. Resource is not available")
    })
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getChat(
            @ApiParam(name = "id chat", defaultValue = "1") @PathVariable int id,
            @ApiIgnore @AuthenticationPrincipal User user
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


    @ApiOperation("Get chat's message")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
    ResponseEntity getMessagers(
            @ApiParam(name = "id chat", defaultValue = "1")@PathVariable int id,
            @ApiIgnore @AuthenticationPrincipal User user
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

    @ApiOperation("Create chat message")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/messages",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createMessage(
            @ApiParam(name = "chat id", defaultValue = "1")@PathVariable int id,
        @ApiParam("Message entity") @RequestBody MessagesEntity messagesEntity,
        @ApiIgnore @AuthenticationPrincipal User user
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

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

    @ApiOperation("Get chat's voting")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings",
            method = RequestMethod.GET
    )
    ResponseEntity getChatVotings(
            @ApiParam(name = "chat id", defaultValue = "1") @PathVariable int id,
            @ApiIgnore @AuthenticationPrincipal User user
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

    @ApiOperation("Create voting in chat")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity createChatVotings(
            @ApiParam(name = "chat id", defaultValue = "1") @PathVariable int id,
            @ApiIgnore @AuthenticationPrincipal User user,
            @ApiParam("Voting entity") @RequestBody VotingEntity votingEntity
    ){
        try{
            try{
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if(chatService.userInChat(users, chat)){

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

    @ApiOperation("Get chat voting by id voting")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}",
            method = RequestMethod.GET
    )
    ResponseEntity getVoting(
            @ApiParam(name = "chat id", defaultValue = "1") @PathVariable int id,
            @ApiParam("voting id")@PathVariable int idVoting,
            @ApiIgnore @AuthenticationPrincipal User user
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


    @ApiOperation("Get voting results")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results",
            method = RequestMethod.GET
    )
    ResponseEntity getVotingResults(
            @ApiParam(name = "chat id", defaultValue = "1") @PathVariable int id,
            @ApiParam("voting id") @PathVariable int idVoting,
            @ApiIgnore @AuthenticationPrincipal User user
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

    @ApiOperation("Create voting result")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity createVotingResults(
            @ApiParam(name = "chat id") @PathVariable int id,
            @ApiParam(name = "voting id", defaultValue = "1") @PathVariable int idVoting,
            @ApiParam("Result entity") @RequestBody ResultsEntity resultsEntity,
            @ApiIgnore @AuthenticationPrincipal User user
    ) {
        try {
            try {
                UsersEntity users = registrationService.getUser(user);
                ChatEntity chat = chatService.getChat(id);

                if (chatService.userInChat(users, chat)) {
                    VotingEntity votingEntity = gameChatService.getVoting(idVoting);

                    if (votingEntity.getChat().getId() == id) {

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

    @ApiOperation("get voting result by id result")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}",
            method = RequestMethod.GET
    )
    ResponseEntity getVotingResult(
            @ApiParam(name = "chat id", defaultValue = "1") @PathVariable int id,
            @ApiParam(name = "voting id", defaultValue = "1") @PathVariable int idVoting,
            @ApiParam(name = "result id", defaultValue = "1")@PathVariable int idResult,
            @ApiIgnore @AuthenticationPrincipal User user
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

    @ApiOperation("Update voting result")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity updateVotingResult(
            @ApiParam("chat id") @PathVariable int id,
            @ApiParam("voting id") @PathVariable int idVoting,
            @ApiParam("result id") @PathVariable int idResult,
            @ApiParam("Result entity") @RequestBody ResultsEntity resultsEntity1,
            @ApiIgnore @AuthenticationPrincipal User user
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


    @ApiOperation("Get votes of result")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}/votes",
            method = RequestMethod.GET
    )
    ResponseEntity getVotingResultVotes(
            @ApiParam("chat id") @PathVariable int id,
            @ApiParam("voting id") @PathVariable int idVoting,
            @ApiParam("result id") @PathVariable int idResult,
            @ApiIgnore@AuthenticationPrincipal User user
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

    @ApiOperation("Create vote for result")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/{id}/votings/{idVoting}/results/{idResult}/votes",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity createVotingResultVotes(
            @ApiParam("chat id") @PathVariable int id,
            @ApiParam("voting id") @PathVariable int idVoting,
            @ApiParam("result id") @PathVariable int idResult,
            @ApiIgnore @AuthenticationPrincipal User user
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