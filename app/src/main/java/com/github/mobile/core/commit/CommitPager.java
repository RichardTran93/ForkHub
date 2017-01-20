/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mobile.core.commit;

import com.github.mobile.core.ResourcePager;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;

import java.util.List;

/**
 * Pager over commits
 */
public class CommitPager extends ResourcePager<RepositoryCommit> {

    private final IRepositoryIdProvider repository;

    private final CommitStore store;

    private CommitService service;

    private String last;

    private String ref;

    /**
     * Create pager
     *
     * @param repository
     * @param store
     */
    public CommitPager(final IRepositoryIdProvider repository,
            final CommitStore store, final CommitService service,
                       final String ref) {
        this.repository = repository;
        this.store = store;
        this.service = service;
        this.ref = ref;
    }

    @Override
    protected Object getId(final RepositoryCommit resource) {
        return resource.getSha();
    }

    @Override
    protected RepositoryCommit register(final RepositoryCommit resource) {
        // Store first parent of last commit registered for next page
        // lookup
        List<Commit> parents = resource.getParents();
        if (parents != null && !parents.isEmpty())
            last = parents.get(0).getSha();
        else
            last = null;

        return super.register(resource);
    }

    @Override
    public PageIterator<RepositoryCommit> createIterator(int page,
                                                         int size) {
        if (page > 1 || ref == null)
            return service.pageCommits(repository, last, null, size);
        else
            return service.pageCommits(repository, ref, null, size);
    }

    @Override
    public ResourcePager<RepositoryCommit> clear() {
        last = null;
        return super.clear();
    }
}
