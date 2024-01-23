package tech.mgaia.transferMoney.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceCrud<T, I> {


    public List<T> create(List<T> t) {
        return t.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }


    public abstract T create(T entidade);
    public abstract T update(T entidade);
    public abstract T findById(I id);
    public abstract Page<T> findAll(T entidadeExemplo, Pageable pageable);
    public abstract void removeById(I id);

}