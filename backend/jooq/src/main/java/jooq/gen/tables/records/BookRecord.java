/*
 * This file is generated by jOOQ.
 */
package jooq.gen.tables.records;


import jooq.gen.tables.Book;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookRecord extends UpdatableRecordImpl<BookRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>library.book.id</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>library.book.id</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>library.book.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>library.book.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>library.book.authorId</code>.
     */
    public void setAuthorid(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>library.book.authorId</code>.
     */
    public String getAuthorid() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Book.BOOK.ID;
    }

    @Override
    public Field<String> field2() {
        return Book.BOOK.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Book.BOOK.AUTHORID;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getAuthorid();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getAuthorid();
    }

    @Override
    public BookRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public BookRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public BookRecord value3(String value) {
        setAuthorid(value);
        return this;
    }

    @Override
    public BookRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BookRecord
     */
    public BookRecord() {
        super(Book.BOOK);
    }

    /**
     * Create a detached, initialised BookRecord
     */
    public BookRecord(String id, String title, String authorid) {
        super(Book.BOOK);

        setId(id);
        setTitle(title);
        setAuthorid(authorid);
    }
}
