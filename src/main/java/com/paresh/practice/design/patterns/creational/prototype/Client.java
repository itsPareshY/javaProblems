package com.paresh.practice.design.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String ... args){

        Client client = new Client();

        // Create a business letter template
        BusinessLetterTemplate businessLetterTemplate = client.createBusinessLetterTemplate();
        // Create a copy of the business letter template
        BusinessLetterTemplate businessLetterTemplateCopy = (BusinessLetterTemplate) businessLetterTemplate.clone();

        // Check if the business letter template and its copy are same object
        if(businessLetterTemplate != businessLetterTemplateCopy){
            System.out.println("Business Letter Template and its copy are not same object");
        }

        // Check if the business letter template and its copy are equal
        if(businessLetterTemplate.equals(businessLetterTemplateCopy)){
            System.out.println("Business Letter Template and its copy are equal");
        } else {
            System.out.println("Business Letter Template and its copy are not equal");
        }

    }

    public BusinessLetterTemplate createBusinessLetterTemplate(){
        BusinessLetterTemplate businessLetterTemplate = new BusinessLetterTemplate();
        businessLetterTemplate.documentType = "Business Letter";
        businessLetterTemplate.documentId = 1;
        businessLetterTemplate.sender = "Sender";
        businessLetterTemplate.receiver = "Receiver";
        businessLetterTemplate.subject = "Subject";
        businessLetterTemplate.body = "Body";
        return businessLetterTemplate;
    }
}
