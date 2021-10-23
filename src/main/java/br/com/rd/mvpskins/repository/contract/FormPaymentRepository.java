package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.FormPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {
}
