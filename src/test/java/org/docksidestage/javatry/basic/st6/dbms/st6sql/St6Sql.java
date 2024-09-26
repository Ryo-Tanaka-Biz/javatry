package org.docksidestage.javatry.basic.st6.dbms.st6sql;

public abstract class St6Sql {

    protected int offset;

    protected void doBuildPagingQuery(int pageSize, int pageNumber) {
        this.offset = pageSize * (pageNumber - 1);
    }

    public abstract String buildPagingQuery(int pageSize, int pageNumber);
}
