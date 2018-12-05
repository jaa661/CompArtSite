package Design.CampusConnect;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class OffsetPageable implements Pageable {

    private int limit = 0;
    private int offset = 0;

    public OffsetPageable(int skip, int offset) {
        if (skip < 0)
            throw new IllegalArgumentException("Skip must not be less than zero!");

        if (offset < 0)
            throw new IllegalArgumentException("Offset must not be less than zero!");

        this.limit = offset;
        this.offset = skip;
    }

    @Override
    public boolean isPaged() {
        return false;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    @Override
    public int getPageNumber() {
        return 0;
    }


    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }


    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return this;
    }

    @Override
    public Pageable first() {
        return this;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Optional.empty();
    }

}