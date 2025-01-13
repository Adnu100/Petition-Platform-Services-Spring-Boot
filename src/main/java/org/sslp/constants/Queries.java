package org.sslp.constants;

public class Queries {
    public static final String CHECK_LOGIN = "select fullname, password_hash from petitioners inner join bioid on " +
        "petitioners.petitioner_email = bioid.email where petitioner_email = ?";
    public static final String CHECK_ADMIN_LOGIN = "select fullname, password_hash from admin where email = ?";
    public static final String FETCH_PERSON_DATA = "select fullname, dob, email from bioid where code = ?";
    public static final String REGISTER_PETITIONER = "insert into petitioners(bioid, petitioner_email, password_hash) " +
        "values(?, ?, ?)";

    public static final String CREATE = "insert into petitions(petitioner_email, title, content) values(?, ?, ?)";
    public static final String SIGN = "insert into signs(petition_id, signed_by) values(?, ?)";
    public static final String SAVE = "insert into saved(petition_id, saved_by) values(?, ?)";
    public static final String UNSAVE = "delete from saved where petition_id = ? and saved_by = ?";
    public static final String CLOSE_WITH_RESPONSE = "update petitions set response = ?, status = 'closed' where " +
        "petition_id = ? and status = 'open'";
    public static final String UPDATE_CONTENT = "update petitions set content = ? where petitioner_email = ? and " +
        "petition_id = ?";

    private static final String PETITION_FETCH_JOIN = "select petitions.petition_id, title, content, status, response, " +
        "petitioner_email, fullname, code, create_timestamp, ifnull(signs.signs, 0) as signs, ifnull(saved.saved, 0) " +
        "as saved, ifnull(signed.signed, 0) as signed from petitions join bioid on petitions.petitioner_email = " +
        "bioid.email left join (select petition_id, count(signed_by) signs from signs group by petition_id) signs on " +
        "petitions.petition_id = signs.petition_id left join (select petition_id, 1 as saved from saved where saved_by " +
        "= ?) saved on petitions.petition_id = saved.petition_id left join (select petition_id, 1 as signed from " +
        "signs where signed_by = ?) signed on petitions.petition_id = signed.petition_id";
    private static final String PETITION_FETCH_ORDER_BY = " order by status desc, create_timestamp desc";
    public static final String PAGINATION = " limit ? offset ?";

    public static final String FETCH_ALL = PETITION_FETCH_JOIN + PETITION_FETCH_ORDER_BY;
    public static final String FETCH_BY_ID = PETITION_FETCH_JOIN + " where petitions.petition_id = ?";
    public static final String FETCH_BY_SAVED = PETITION_FETCH_JOIN + " where saved = 1" + PETITION_FETCH_ORDER_BY;
    public static final String FETCH_BY_SIGN = PETITION_FETCH_JOIN + " where signed = 1" + PETITION_FETCH_ORDER_BY;
    public static final String FETCH_BY_PETITIONER = PETITION_FETCH_JOIN + " where petitioner_email = ?" + PETITION_FETCH_ORDER_BY;
    public static final String FETCH_ALL_PAGINATION = PETITION_FETCH_JOIN + PETITION_FETCH_ORDER_BY + PAGINATION;
    public static final String FETCH_BY_SAVED_PAGINATION = PETITION_FETCH_JOIN + " where saved = 1" + PETITION_FETCH_ORDER_BY + PAGINATION;
    public static final String FETCH_BY_SIGN_PAGINATION = PETITION_FETCH_JOIN + " where signed = 1" + PETITION_FETCH_ORDER_BY + PAGINATION;
    public static final String FETCH_BY_PETITIONER_PAGINATION = PETITION_FETCH_JOIN + " where petitioner_email = ?" + PETITION_FETCH_ORDER_BY + PAGINATION;

}
