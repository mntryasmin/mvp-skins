package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyPreco;
import br.com.rd.mvpskins.model.entity.Preco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, CompositeKeyPreco> {
}
