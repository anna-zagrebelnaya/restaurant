package controller;

import DAO.MessagesDAO;
import beans.MessageBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            messagesDAO.newMessage(dateFormat.parse("2014/3/21 16:00:00"), "Alice", "Bob", "How do you do?");
            messagesDAO.newMessage(dateFormat.parse("2014/3/23 10:00:25"), "Bob", "Alice", "Fine, how're you?");
            messagesDAO.newMessage(dateFormat.parse("2014/3/25 12:16:39"), "Alice", "Bob", "Great!");
        } catch (ParseException e) {

        }

        messageDataModel.setWrappedData(messagesDAO.getMessageBeanList());
        filteredMessageBeanList.addAll(messagesDAO.getMessageBeanList());
    }

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
}
