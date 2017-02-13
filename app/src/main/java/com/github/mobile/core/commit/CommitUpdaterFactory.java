package com.github.mobile.core.commit;

import com.github.mobile.api.model.Repository;
import com.github.mobile.core.ItemStore;

import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.RepositoryCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by henrikmnm on 13/02/2017.
 */

public class CommitUpdaterFactory extends ItemStore{

    private RepositoryCommit currentCommit;

    private ItemReferences<RepositoryCommit> currentRepositoryCommits;

    private String currentRepositoryId;

    public CommitUpdaterFactory(IRepositoryIdProvider repo){
        currentRepositoryId = repo.generateId();

    }

    public void updateExistingCommit(RepositoryCommit commit){
        currentCommit.setAuthor(commit.getAuthor());
        currentCommit.setCommit(commit.getCommit());
        currentCommit.setCommitter(commit.getCommitter());
        currentCommit.setFiles(commit.getFiles());
        currentCommit.setParents(commit.getParents());
        currentCommit.setSha(commit.getSha());
        currentCommit.setStats(commit.getStats());
        currentCommit.setUrl(commit.getUrl());
    }

    public void restoreExistingCommit(Map<String, ItemReferences<RepositoryCommit>> commits, RepositoryCommit commit){
        currentRepositoryCommits = commits.get(currentRepositoryId);

        if (currentRepositoryCommits == null){
            currentRepositoryCommits = new ItemReferences<RepositoryCommit>();
            commits.put(currentRepositoryId, currentRepositoryCommits);
        }

        currentRepositoryCommits.put(commit.getSha(), commit);

        currentCommit = commit;
    }

    public RepositoryCommit getCurrentCommit(){
        return this.currentCommit;
    }
}
