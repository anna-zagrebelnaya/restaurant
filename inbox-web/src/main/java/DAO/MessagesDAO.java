package DAO;

import beans.Account;
import beans.MessageBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class MessagesDAO implements Serializable {

    private List<MessageBean> messageBeanList;

    public MessagesDAO() {
        messageBeanList = new ArrayList<MessageBean>();
    }

    public List<MessageBean> getMessageBeanList() {
        return messageBeanList;
    }

    public void setMessageBeanList(List<MessageBean> messageBeanList) {
        this.messageBeanList = messageBeanList;
    }

    public MessageBean newMessage(Date creationDate, String from) {
        MessageBean messageBean = new MessageBean(creationDate, from);
        return messageBean;
    }

    public MessageBean newMessage(Date creationDate, String from, String to, String subject, String body) {
        MessageBean messageBean = new MessageBean(creationDate, from, to, subject, body);
        return messageBean;
    }

    public void saveMessage(MessageBean messageBean) {
        messageBeanList.add(messageBean);
    }

    public MessageBean newMessageInList(Date creationDate, String from, String to, String subject, String body) {
        MessageBean messageBean = new MessageBean(creationDate, from, to, subject, body);
        messageBeanList.add(messageBean);
        return messageBean;
    }

    public void replyMessage(MessageBean messageBean, String reply) {
        MessageBean newMessageBean = newMessage(new Date(), Account.getAuthor(), messageBean.getFrom(), "Re: " + messageBean.getSubject(), reply);
        messageBean.addReply(newMessageBean);
    }
}
