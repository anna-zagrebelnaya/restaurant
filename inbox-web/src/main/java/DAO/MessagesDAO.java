package DAO;

import beans.MessageBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

    public MessageBean newMessage(Date creationDate, String from, String to, String subject) {
        MessageBean messageBean = new MessageBean(creationDate, from, to, subject);
        messageBeanList.add(messageBean);
        return messageBean;
    }
}