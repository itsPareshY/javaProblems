package com.paresh.practice.design.patterns.creational.prototype;

public abstract class DocumentTemplate {
    public String documentType;;
    public int documentId;

    public DocumentTemplate(DocumentTemplate targetTemplate) {
        if (targetTemplate == null) {
            return;
        }
        this.documentType = targetTemplate.documentType;
        this.documentId = targetTemplate.documentId;
    }

    // Prototype pattern requires a clone method to create a copy of the object
    public abstract DocumentTemplate clone();

    @Override
    public boolean  equals(Object target) {
        if (target == null) {
            return false;
        }
        DocumentTemplate targetTemplate = (DocumentTemplate) target;
        return this.documentId == targetTemplate.documentId && this.documentType.equals(targetTemplate.documentType);
    }

}
