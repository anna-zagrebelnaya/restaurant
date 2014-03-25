package controller;

import DAO.MessagesDAO;
import beans.MessageBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ConversationScoped
public class MessageController implements Serializable {

    @Inject
    private Conversation conversation;

    @Inject
    private MessagesDAO messagesDAO;

    private MessageBean messageBean;

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
        if (messageBean==null) {
            this.messageBean = messagesDAO.newMessage(new Date(),"Alice", "", "");
        }
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }
}
