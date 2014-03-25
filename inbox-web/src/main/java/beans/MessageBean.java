package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBean {

    private static Long lastId=1L;

    private Long id;

    private Date creationDate;
    private String from;
    private String to;
    private String subject;
    private String body;
    private List<MessageBean> messagesInThread;

    public MessageBean() {
        messagesInThread = new ArrayList<MessageBean>();
        id = lastId++;
    }

    public MessageBean(Date creationDate, String from) {
        this();
        this.creationDate = creationDate;
        this.from = from;
    }

    public MessageBean(Date creationDate, String from, String to, String subject, String body) {
        this();
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<MessageBean> getMessagesInThread() {
        return messagesInThread;
    }

    public void setMessagesInThread(List<MessageBean> messagesInThread) {
        this.messagesInThread = messagesInThread;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addReply(MessageBean messageBean) {
        messagesInThread.add(messageBean);
    }

}
