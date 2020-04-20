package touk.cinema.infrastructure.hibernate.repository;

import org.springframework.stereotype.Repository;
import touk.cinema.domain.Voucher;
import touk.cinema.domain.VoucherRepository;

import javax.persistence.EntityManager;

@Repository
public class HibernateVoucherRepository implements VoucherRepository {

    private EntityManager entityManager;

    public HibernateVoucherRepository(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Voucher getByCode(String code) {
        return entityManager
                .createQuery("SELECT v FROM touk.cinema.domain.Voucher v WHERE v.code = :code", Voucher.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public void save(Voucher voucher) {
        entityManager.persist(voucher);
    }
}
