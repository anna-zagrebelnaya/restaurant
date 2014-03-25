GivenStories: base_stories/openDefaultPage.story
Scenario: send new message

When clicks on element with id/name/className 'formInbox:sendNewMessage'
Then should open page with 'Message' title
Then element id/name/className 'formMessage:fromOutput' has text 'Alice'
When the user fills 'formMessage:toInput' field with 'Bob'
When the user fills 'formMessage:subjectInput' field with 'important'
When the user fills 'formMessage:body' field with 'Please, let me know, when you are ready to go home.'
When clicks on element with id/name/className 'formMessage:send'
Then should open page with 'Inbox' title

Scenario: check
Meta: @subject         important
GivenStories: base_stories/openMessage.story
Then element id/name/className 'formMessage:toOutput' has text 'Bob'
Then element id/name/className 'formMessage:subjectOutput' has text 'important'
Then element id/name/className 'formMessage:body' has text 'Please, let me know, when you are ready to go home.'

Scenario: reply
When the user fills 'formMessage:reply' field with 'it's a reply'
When clicks on element with id/name/className 'formMessage:replyBtn'
Then wait until all animations on page completed
Then element id/name/className 'formMessage:replyMessages:0:replyBody' has text 'it's a reply'
When clicks on element with id/name/className 'formMessage:backBtn'
Then should open page with 'Inbox' title