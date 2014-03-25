package controller;

import DAO.MessagesDAO;
import beans.Account;
import beans.MessageBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class InboxController implements Serializable {

    private static final String DATE_FORMAT_STRING = "yyyy/MM/dd HH:mm:ss";

    public String getDateFormatString() {
        return DATE_FORMAT_STRING;
    }

    @Inject
    private MessagesDAO messagesDAO;

    private LazyMessageDataModel messageDataModel;
    private List<MessageBean> filteredMessageBeanList;

    public InboxController() {
        messageDataModel = new LazyMessageDataModel();
        filteredMessageBeanList = new ArrayList<MessageBean>();
    }

    @PostConstruct
    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        try {
            MessageBean messageBean = messagesDAO.newMessageInList(dateFormat.parse("2014/3/21 16:00:00"), Account.getAuthor(), Account.getFirstContact(), "subject","How do you do?");
            messageBean.addReply(messagesDAO.newMessage(dateFormat.parse("2014/3/21 16:20:00"), Account.getFirstContact(), Account.getAuthor(), "Re: subject","Fine, thank you"));
            messagesDAO.newMessageInList(dateFormat.parse("2014/3/25 12:16:39"), Account.getFirstContact(), Account.getAuthor(), "your name", "What is your name?");
            messagesDAO.newMessageInList(dateFormat.parse("2014/3/25 12:16:39"), Account.getSecondContact(), Account.getAuthor(), "your age", "How old are you?");
        } catch (ParseException e) {

        }

        updateModel();
    }

    //getters and setters

    public LazyMessageDataModel getMessageDataModel() {
        return messageDataModel;
    }

    public void setMessageDataModel(LazyMessageDataModel messageDataModel) {
        this.messageDataModel = messageDataModel;
    }

    public List<MessageBean> getFilteredMessageBeanList() {
        return filteredMessageBeanList;
    }

    public void setFilteredMessageBeanList(List<MessageBean> filteredMessageBeanList) {
        this.filteredMessageBeanList = filteredMessageBeanList;
    }

    //...getters and setters

    public MessageBean initNewMessage() {
        return messagesDAO.newMessage(new Date(), Account.getAuthor());
    }

    public void saveMessageToInbox(MessageBean messageBean) {
        messagesDAO.saveMessage(messageBean);
        updateModel();
    }

    public void updateModel() {
        messageDataModel.setWrappedData(messagesDAO.getMessageBeanList());
        filteredMessageBeanList.clear();
        filteredMessageBeanList.addAll(messagesDAO.getMessageBeanList());
    }

    private String cutTextToNSymbols(String subject, int n) {
        if (subject.length()<=n) {
            return subject;
        }
        else {
            return subject.substring(0, n-1) + "...";
        }
    }

    public String getShortBody(MessageBean messageBean) {
        return cutTextToNSymbols(messageBean.getBody(), 10);
    }

}
