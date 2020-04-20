package touk.cinema.domain;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VoucherRepository {

    Voucher getByCode(String code);

    void save(Voucher voucher);

}
