package controller;

import DAO.MessagesDAO;
import beans.Account;
import beans.MessageBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class MessageController implements Serializable {

    @Inject
    private InboxController inboxController;

    @Inject
    private MessagesDAO messagesDAO;

    @Inject
    private Conversation conversation;

    private MessageBean messageBean;
    private boolean isNew;
    private String reply;

    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    @PostConstruct
    public void init() {
        beginConversation();
    }

    //getters and setters

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    //...getters and setters

    public String sendMessage() {
        messagesDAO.saveMessage(messageBean);
        inboxController.updateModel();
        return back();
    }

    public void replyMessage() {
        messagesDAO.replyMessage(messageBean, reply);
        inboxController.updateModel();
        reply = "";
    }

    public String back() {
        endConversation();
        return "inbox?faces-redirect=true";
    }
}
