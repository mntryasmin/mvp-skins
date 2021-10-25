package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.CompositeKeySubcategoria;
import br.com.rd.mvpskins.model.entity.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, CompositeKeySubcategoria> {
}
