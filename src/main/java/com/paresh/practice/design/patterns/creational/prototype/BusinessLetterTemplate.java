package com.paresh.practice.design.patterns.creational.prototype;

public class BusinessLetterTemplate extends DocumentTemplate {

    public String sender;
    public String receiver;
    public String subject;
    public String body;

    public BusinessLetterTemplate() {
        super(null);
    }

    // Copy constructor requires a target template to copy from
    public BusinessLetterTemplate(BusinessLetterTemplate targetTemplate) {
        super(targetTemplate);
        if (targetTemplate == null) {
            return;
        }
        this.sender = targetTemplate.sender;
        this.receiver = targetTemplate.receiver;
        this.subject = targetTemplate.subject;
        this.body = targetTemplate.body;
    }

    @Override
    public DocumentTemplate clone() {
        return new BusinessLetterTemplate(this);
    }

    @Override
    public boolean equals(Object target) {
        if (target == null || !(target instanceof BusinessLetterTemplate) || !super.equals(target)) {
            return false;
        }
        BusinessLetterTemplate targetTemplate = (BusinessLetterTemplate) target;
        return this.sender.equals(targetTemplate.sender) && this.receiver.equals(targetTemplate.receiver) && this.subject.equals(targetTemplate.subject) && this.body.equals(targetTemplate.body);
    }
}
