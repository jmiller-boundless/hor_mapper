package gov.hor.approp.model.dao;

import gov.hor.approp.model.Agency;
import gov.hor.approp.model.Award;
import gov.hor.approp.model.Bureau;
import gov.hor.approp.model.Congress;
import gov.hor.approp.model.csv.GrantView;
import gov.hor.approp.model.Member;
import gov.hor.approp.model.Member_;
import gov.hor.approp.model.Program;
import static gov.hor.approp.model.Program.CFDA_NAME;
import gov.hor.approp.model.State;
import gov.hor.approp.model.Subcommittee;
import gov.hor.approp.model.Term;
import gov.hor.approp.model.Term_;
import gov.hor.approp.model.csv.Grant;
import static gov.hor.approp.model.csv.Grant.GRANT_NAME;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.supercsv.io.ICsvBeanWriter;

@Repository
public class FormFieldsDAO {

    private static final Logger LOGGER = Logger.getLogger(FormFieldsDAO.class.getCanonicalName());

    /**
     * Fetch size to use for scrollable results from the db.
     */
    private static final int FETCH_SIZE = 1024;

    /**
     * Value for specifying that you want all results for a particular filter.
     */
    public static final String ALL_VALUE = "All";

    @PersistenceContext
    private EntityManager entityManager;

    SessionFactory sessionFactory;

    @Autowired
    public FormFieldsDAO(EntityManagerFactory factory) {
        // Only way I could get the Hibernate SessionFactory to load up.
        // Need Hibernate for ScrollableResults so that we don't load millions of rows into memory when trying to write
        // out the CSV reports.
        // https://stackoverflow.com/a/25064080
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public List<Agency> getAgencies(List<String> subcommittee) {
        String query = "select distinct on(agency_name) agency_name from " + CFDA_NAME + " WHERE agency_name is not null ";

        if (includeList(subcommittee)) {
            query += " AND subcommittee in (:subcommittee) ";
        }

        query += " order by agency_name";

        Query q = entityManager.createNativeQuery(query, Agency.class);

        if (includeList(subcommittee)) {
            q.setParameter("subcommittee", subcommittee);
        }

        List<Agency> agencies = q.getResultList();

        Agency unspec = new Agency();
        unspec.setAgency_name(ALL_VALUE);
        agencies.add(0, unspec);

        return agencies;
    }

    public List<Bureau> getBureaus(List<String> agency, List<String> subcommittee) {
        String query = "select distinct on(bureau_name) bureau_name from " + CFDA_NAME + " where bureau_name is not null ";

        if (includeList(subcommittee)) {
            query += " AND subcommittee in (:subcommittee) ";
        }

        if (includeList(agency)) {
            query += " AND agency_name in (:agency) ";
        }

        query += " order by bureau_name";

        Query q = entityManager.createNativeQuery(query, Bureau.class);

        if (includeList(subcommittee)) {
            q.setParameter("subcommittee", subcommittee);
        }

        if (includeList(agency)) {
            q.setParameter("agency", agency);
        }

        List<Bureau> bureaus = q.getResultList();

        Bureau unspecified = new Bureau();
        unspecified.setBureau_name(ALL_VALUE);
        bureaus.add(0, unspecified);

        return bureaus;
    }

    public List<Subcommittee> getSubcommittees() {
        String query = "select distinct subcommittee from " + CFDA_NAME + " where subcommittee is not null order by subcommittee";
        Query q = entityManager.createNativeQuery(query, Subcommittee.class);
        List<Subcommittee> subcommittees = q.getResultList();
        Subcommittee unspecified = new Subcommittee();
        unspecified.setSubcommittee(ALL_VALUE);
        subcommittees.add(0, unspecified);
        return subcommittees;
    }

    public List<Program> getPrograms(List<String> bureau, List<String> subcommittee, List<String> agency) {
        String query = "select cfda, program_title from " + CFDA_NAME + " WHERE cfda is not null ";

        if (includeList(subcommittee)) {
            query += " AND subcommittee in (:subcommittee) ";
        }

        if (includeList(agency)) {
            query += " AND agency_name in (:agency) ";
        }

        if (includeList(bureau)) {
            query += " AND bureau_name in (:bureau) ";
        }

        query += " order by cfda";

        Query q = entityManager.createNativeQuery(query, Program.class);

        if (includeList(subcommittee)) {
            q.setParameter("subcommittee", subcommittee);
        }

        if (includeList(agency)) {
            q.setParameter("agency", agency);
        }

        if (includeList(bureau)) {
            q.setParameter("bureau", bureau);
        }

        return q.getResultList();
    }

    public List<State> getStates() {
        String query = "select name, stusps from tiger.state order by name";
        Query q = entityManager.createNativeQuery(query, State.class);
        List<State> states = q.getResultList();
        return states;
    }

    public List<Member> getMemberAutoComplete(String partial, Integer congress, String state) {
        String query = "select distinct on(lastname,firstname,middlename) lastname, firstname, middlename, m.id as id, st_envelope(c.the_geom) as the_geom "
                + "from members.member m, members.term t, tiger.cd c, tiger.state s "
                + "where m.id=t.memberid and c.cdfp=t.cdfp AND c.statefp=t.statefp AND s.statefp=t.statefp AND t.congress=:congress AND upper(lastname) like (:partial) "
                + "and (s.name=:state or 'unspecified'=:state)";
        Query q = entityManager.createNativeQuery(query, Member.class);
        q.setParameter("partial", "%" + partial.toUpperCase() + "%");
        q.setParameter("congress", congress);
        q.setParameter("state", state);
        return q.getResultList();
    }

    public List<Member> getMemberAutoComplete(String partial, List<Integer> congress, List<String> state) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> query = builder.createQuery(Member.class);
        Root<Member> member = query.from(Member.class);
        Join<Member, Term> terms = (Join<Member, Term>) member.fetch(Member_.terms);

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (partial != null && !partial.isEmpty()) {
            Predicate lastname = builder.like(builder.upper(member.get(Member_.lastname)), '%' + partial.toUpperCase() + '%');
            Predicate firstname = builder.like(builder.upper(member.get(Member_.firstname)), '%' + partial.toUpperCase() + '%');
            predicates.add(builder.or(lastname, firstname));
        }

        if (includeIntegerList(congress)) {
            predicates.add(terms.get(Term_.congress).in(congress));
        }

        if (includeList(state)) {
            predicates.add(terms.get(Term_.state).in(state));
        }

        Predicate[] ps = predicates.toArray(new Predicate[predicates.size()]);

        query.where(builder.and(ps)).distinct(true).orderBy(builder.asc(member.get(Member_.lastname)), builder.asc(member.get(Member_.firstname)));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Congress> getCongresses() {
        String query = "select distinct congress from members.term order by congress desc";
        Query q = entityManager.createNativeQuery(query, Congress.class);
        return q.getResultList();
    }

    public List<Program> getCfdaAutoComplete(String partial) {
        String query = "select cfda, program_title from " + CFDA_NAME + " where upper(program_title) like :partial order by program_title";
        Query q = entityManager.createNativeQuery(query, Program.class);
        q.setParameter("partial", "%" + partial.toUpperCase() + "%");
        return q.getResultList();
    }

    public List<Short> getYears() {
        String query = "select distinct fiscal_year from " + GRANT_NAME + " order by fiscal_year desc";
        Query q = entityManager.createNativeQuery(query);
        return q.getResultList();
    }

    public List<Award> getAwards(List<String> fy) {
        /*    	String query = "select gg.gid, "
         + "gg.award_date, "
         + "gg.award_amount, "
         + "gg.fiscal_year,"
         + "'Grant' as grant_or_subgrant, "
         + "g.assistance_type, "
         + "gg.cfda, "
         + "ca.subcommittee, "
         + "ca.program_title, "
         + "ca.agency_name, "
         + "ca.bureau_name, "
         + "gg.recipient_name, "
         + "gg.address, "
         + "gg.city, "
         + "gg.state, "
         + "gg.zip, "
         + "gg.zip4, "
         + "gg.state || gg.cdfp as cd_at_award, "
         + "m_hist.firstname || ' ' || coalesce(m_hist.middlename || ' ', '') || m_hist.lastname as member_at_award, "
         + "t_hist.party as party_at_award, gg.state || gg.cd_currentcdfp as cd_current, gg.member_currentid, m_current.firstname || ' ' || coalesce(m_current.middlename || ' ', '') || m_current.lastname as member_current, "
         + "t_current.party as party_current, "
         + "g.recipient_cd as usa_spending_cd "
         + "from spending.grant_geocoded_usaspending3 as gg "
         + "left join spending.grant as g on gg.gid = g.id "
         + "left join programs.cfda_account as ca on gg.cfda = ca.cfda "
         + "left join members.member as m_hist on gg.memberid = m_hist.id "
         + "left join members.member as m_current on gg.member_currentid = m_current.id "
         + "left join members.term as t_hist on gg.termid = t_hist.id "
         + "left join members.term as t_current on gg.term_currentid = t_current.id "
         + "where gg.fiscal_year in (:fy)";*/
        String query = "select * from spending.award2 where fiscal_year in (:fy)";
        Query q = entityManager.createNativeQuery(query, Award.class);
        q.setParameter("fy", fy);
        return q.getResultList();
    }

    public void getGrantViews(List<String> fiscal_year,
            List<String> subcommittee,
            List<String> agency_name,
            List<String> bureau_name,
            List<String> cfda,
            List<String> state,
            List<Integer> memberid,
            Integer congress,
            Boolean isMemberAtAward,
            ICsvBeanWriter csvWriter,
            String[] header) throws IOException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GrantView> query = builder.createQuery(GrantView.class);
        // TODO: Generate metamodel classes so these fields aren't hardcoded.
//        Metamodel m = entityManager.getMetamodel();
//        EntityType<GrantView> GrantView_ = m.entity(GrantView.class);
        Root<GrantView> grantView = query.from(GrantView.class);
        // Use fetch to join the tables in the same request. Otherwise JPA will make separate requests for each row.
        grantView.fetch("awardMember");
        grantView.fetch("awardTerm");
        grantView.fetch("currentMember");
        grantView.fetch("currentTerm");

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (includeList(fiscal_year)) {
            predicates.add(grantView.get("fiscal_year").in(fiscal_year));
        }

        if (includeList(subcommittee)) {
            predicates.add(grantView.get("subcommittee").in(subcommittee));
        }

        if (includeList(agency_name)) {
            predicates.add(grantView.get("agency_name").in(agency_name));
        }

        if (includeList(bureau_name)) {
            predicates.add(grantView.get("bureau_name").in(bureau_name));
        }

        if (includeList(cfda)) {
            predicates.add(grantView.get("cfda").in(cfda));
        }

        if (includeList(state)) {
            predicates.add(grantView.get("state").in(state));
        }

        if (memberid != null && !memberid.isEmpty()) {
            if (isMemberAtAward != null && isMemberAtAward) {
                // We want to filter by the Members at the award of the grant.
                predicates.add(grantView.get("congress").in(congress));
                predicates.add(grantView.get("awardMember").get("id").in(memberid));
            } else {
                predicates.add(grantView.get("currentMember").get("id").in(memberid));
            }
        }

        Predicate[] ps = predicates.toArray(new Predicate[predicates.size()]);

        query.where(builder.and(ps));

        writeResults(query, csvWriter, header);
    }

    public void getGrants(List<String> fiscal_year,
            List<String> subcommittee,
            List<String> agency_name,
            List<String> bureau_name,
            List<String> cfda,
            List<String> state,
            List<Integer> memberid,
            Integer congress,
            Boolean isMemberAtAward,
            ICsvBeanWriter csvWriter,
            String[] header) throws IOException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Grant> query = builder.createQuery(Grant.class);
        // TODO: Generate metamodel classes so these fields aren't hardcoded.
//        Metamodel m = entityManager.getMetamodel();
//        EntityType<GrantView> GrantView_ = m.entity(GrantView.class);
        Root<Grant> grant = query.from(Grant.class);
        // Use fetch to join the tables in the same request. Otherwise JPA will make separate requests for each row.
        grant.fetch("grantView");

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (includeList(fiscal_year)) {
            predicates.add(grant.get("grantView").<String>get("fiscal_year").in(fiscal_year));
        }

        if (includeList(subcommittee)) {
            predicates.add(grant.get("grantView").<String>get("subcommittee").in(subcommittee));
        }

        if (includeList(agency_name)) {
            predicates.add(grant.get("grantView").<String>get("agency_name").in(agency_name));
        }

        if (includeList(bureau_name)) {
            predicates.add(grant.get("grantView").<String>get("bureau_name").in(bureau_name));
        }

        if (includeList(cfda)) {
            predicates.add(grant.get("grantView").<String>get("cfda").in(cfda));
        }

        if (includeList(state)) {
            predicates.add(grant.get("grantView").<String>get("state").in(state));
        }

        if (memberid != null && !memberid.isEmpty()) {
            if (isMemberAtAward != null && isMemberAtAward) {
                // We want to filter by the Members at the award of the grant.
                predicates.add(grant.get("grantView").get("congress").in(congress));
                predicates.add(grant.get("grantView").get("awardMember").get("id").in(memberid));
            } else {
                predicates.add(grant.get("grantView").get("currentMember").get("id").in(memberid));
            }
        }

        Predicate[] ps = predicates.toArray(new Predicate[predicates.size()]);

        query.where(builder.and(ps));

        writeResults(query, csvWriter, header);
    }

    private <T> void writeResults(CriteriaQuery<T> query, ICsvBeanWriter writer, String[] header) throws IOException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Use a transaction, it's important even though no documentation mentions it.
            transaction = session.getTransaction();
            transaction.begin();

            writeResults(query, writer, header, session);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private <T> void writeResults(CriteriaQuery<T> query, ICsvBeanWriter writer, String[] header,
            Session session) throws IOException {
        org.hibernate.query.Query createQuery = session.createQuery(query);
        createQuery.setFetchSize(FETCH_SIZE).setReadOnly(true).setCacheable(false);
        try (ScrollableResults results = createQuery.scroll(ScrollMode.FORWARD_ONLY)) {

            int count = 0;
            while (results.next()) {
                if (++count > 0 && count % FETCH_SIZE == 0) {
                    LOGGER.log(Level.FINER, "Scrollable Results fetched {0} entities. Flushing and clearing the session.", count);
                    // Need to flush and clear to prevent a heap overflow.
                    session.flush();
                    session.clear();
                }

                writer.write(results.get(0), header);
            }
        }
    }

    public boolean includeList(List<String> list) {
        return list != null && list.size() > 0 && !(list.size() == 1 && list.get(0).equals(ALL_VALUE));
    }

    public boolean includeIntegerList(List<Integer> list) {
        return list != null && list.size() > 0;
    }

}
