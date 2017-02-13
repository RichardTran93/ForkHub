package com.github.mobile.core.issue;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.RepositoryIssue;

public class RepositoryIssueBuilder {
    public RepositoryIssueBuilder() {
    }

    RepositoryIssue createRepositoryIssue(Issue issue) {
        if (issue instanceof RepositoryIssue)
            return (RepositoryIssue) issue;

        return copyIssue(new RepositoryIssue(), issue);
    }

    RepositoryIssue copyIssue(RepositoryIssue to, Issue from) {
        to.setId(from.getId());
        to.setUser(from.getUser());
        to.setAssignee(from.getAssignee());
        to.setBody(from.getBody());
        to.setBodyHtml(from.getBodyHtml());
        to.setBodyText(from.getBodyText());
        to.setHtmlUrl(from.getHtmlUrl());
        to.setClosedBy(from.getClosedBy());
        to.setClosedAt(from.getClosedAt());
        to.setCreatedAt(from.getCreatedAt());
        to.setUpdatedAt(from.getUpdatedAt());
        to.setComments(from.getComments());
        to.setNumber(from.getNumber());
        to.setLabels(from.getLabels());
        to.setMilestone(from.getMilestone());
        to.setPullRequest(from.getPullRequest());
        to.setState(from.getState());
        to.setTitle(from.getTitle());
        to.setUrl(from.getUrl());

        if (from instanceof RepositoryIssue)
            to.setRepository(((RepositoryIssue) from).getRepository());

        return to;
    }
}