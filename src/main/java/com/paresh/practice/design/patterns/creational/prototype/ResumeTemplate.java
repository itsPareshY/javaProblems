package com.paresh.practice.design.patterns.creational.prototype;

public class ResumeTemplate extends DocumentTemplate {
    public String name;
    public String address;
    public String email;
    public String phone;
    public String objective;
    public String experience;
    public String education;
    public String skills;

    public ResumeTemplate(ResumeTemplate targetTemplate) {
        super(targetTemplate);
        if (targetTemplate != null) {
            this.name = targetTemplate.name;
            this.address = targetTemplate.address;
            this.email = targetTemplate.email;
            this.phone = targetTemplate.phone;
            this.objective = targetTemplate.objective;
            this.experience = targetTemplate.experience;
            this.education = targetTemplate.education;
            this.skills = targetTemplate.skills;
        }
    }

    public ResumeTemplate() {
        super(null);
    }

    @Override
    public DocumentTemplate clone() {
        return new ResumeTemplate(this);
    }

    @Override
    public boolean equals(Object target) {
        if (!(target instanceof ResumeTemplate) || !super.equals(target)) {
            return false;
        }
        ResumeTemplate targetTemplate = (ResumeTemplate) target;
        return  this.name.equals(targetTemplate.name) &&
                this.address.equals(targetTemplate.address) &&
                this.email.equals(targetTemplate.email) &&
                this.phone.equals(targetTemplate.phone) &&
                this.objective.equals(targetTemplate.objective) &&
                this.experience.equals(targetTemplate.experience) &&
                this.education.equals(targetTemplate.education) &&
                this.skills.equals(targetTemplate.skills);
    }
}
